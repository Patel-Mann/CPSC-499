import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.*;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import japa.parser.ast.stmt.*;
import japa.parser.ast.expr.*;

import java.io.FileInputStream;
import java.io.File;

//graph imports
import org.jgrapht.*;
import org.jgrapht.graph.*;

import java.io.*;
import java.util.*;

import CFGGraph.CFGNode;
import CFGGraph.CFGEdge;

public class LCA_JP1_0_0 {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java LCA_JP1_0_0 <JavaSourceFile>");
            return;
        }
        FileInputStream in = new FileInputStream(new File(args[0]));
        CompilationUnit cu = JavaParser.parse(in);
        in.close();

        CFGVisitor visitor = new CFGVisitor();
        visitor.visit(cu, null);
    }

    static class CFGVisitor extends VoidVisitorAdapter<Void> {
        private Graph<CFGNode, CFGEdge> currCFG;
        private CFGNode currentBlock;
        private CFGNode entryNode;
        private CFGNode exitNode;
        private Map<String, Integer> constantValues;
        public void visit(MethodDeclaration n, Void arg) {
            // Initialize new CFG for this method
            currCFG = new DefaultDirectedGraph<>(CFGEdge.class);
            constantValues = new HashMap<String, Integer>();
            
            // Create entry and exit nodes
            entryNode = new CFGNode("ENTRY_" + n.getName());
            exitNode = new CFGNode("EXIT_" + n.getName());
            
            currCFG.addVertex(entryNode);
            currCFG.addVertex(exitNode);
            
            System.out.println("CFG Building Root: " + n.getName());
            
            currentBlock = entryNode;
            
            // Visit method body
            BlockStmt body = n.getBody();
            if (body != null) {
                visitBlockStmt(body);
            }
            
            // Connect last block to exit if not already connected
            if (currentBlock != null && currentBlock != exitNode) {
                addEdge(currentBlock, exitNode, "");
            }
            
            // Print CFG
            printCFG(n.getName());
        }

        private void visitBlockStmt(BlockStmt block) {
            List<Statement> stmts = block.getStmts();
            if (stmts == null) return;
            
            for (Statement stmt : stmts) {
                visitStatement(stmt);
            }
        }

        private void visitStatement(Statement stmt) {
            if (stmt instanceof ExpressionStmt) {
                visitExpressionStmt((ExpressionStmt) stmt);
            } else if (stmt instanceof IfStmt) {
                visitIfStmt((IfStmt) stmt);
            } else if (stmt instanceof ForStmt) {
                visitForStmt((ForStmt) stmt);
            } else if (stmt instanceof WhileStmt) {
                visitWhileStmt((WhileStmt) stmt);
            } else if (stmt instanceof ReturnStmt) {
                visitReturnStmt((ReturnStmt) stmt);
            } else if (stmt instanceof BlockStmt) {
                visitBlockStmt((BlockStmt) stmt);
            } else {
                // Handle other statement types generically
                CFGNode stmtNode = new CFGNode(stmt.toString().trim());
                currCFG.addVertex(stmtNode);
                addEdge(currentBlock, stmtNode, "");
                currentBlock = stmtNode;
            }
        }

        private void visitExpressionStmt(ExpressionStmt stmt) {
            CFGNode exprNode = new CFGNode(stmt.toString().trim());
            currCFG.addVertex(exprNode);
            addEdge(currentBlock, exprNode, "");
            currentBlock = exprNode;
            
            // Track constant like "int v = 5" or "v = 5"
            String stmtStr = stmt.toString().trim();
            if (stmtStr.contains("=") && !stmtStr.contains("==")) {
                try {
                    // Simple pattern: extract variable and value
                    String[] parts = stmtStr.split("=");
                    if (parts.length == 2) {
                        String varPart = parts[0].trim();
                        String valuePart = parts[1].trim().replace(";", "").trim();
                        
                        // Get variable name (handle "int v" or just "v")
                        String[] varTokens = varPart.split("\\s+");
                        String varName = varTokens[varTokens.length - 1];
                        
                        // Try to parse the value as integer
                        try {
                            int value = Integer.parseInt(valuePart);
                            constantValues.put(varName, value);
                        } catch (NumberFormatException e) {
                            constantValues.remove(varName); // Not a constant, remove from tracking
                        }
                    }
                } catch (Exception e) {
                    // Parsing failed, ignore
                }
            }
        }

        private void visitIfStmt(IfStmt stmt) {
            // Evaluate condition if possible
            String condStr = stmt.getCondition().toString();
            String edgeLabel = evaluateConditionForEdge(condStr);
            
            // Create condition node
            CFGNode condNode = new CFGNode("if (" + condStr + ")");
            currCFG.addVertex(condNode);
            addEdge(currentBlock, condNode, "");
            
            CFGNode mergeNode = new CFGNode("MERGE_IF");
            currCFG.addVertex(mergeNode);
            
            CFGNode savedCond = condNode;
            
            // Then branch
            currentBlock = condNode;
            Statement thenStmt = stmt.getThenStmt();
            if (thenStmt != null) {
                visitStatement(thenStmt);
            }
            
            CFGNode thenEnd = currentBlock;
            if (thenEnd != null && thenEnd != savedCond) {
                addEdge(thenEnd, mergeNode, "");
            }
            
            // Label then branch edge if we evaluated it
            if (!edgeLabel.isEmpty()) {
                Set<CFGEdge> edges = new HashSet<CFGEdge>(currCFG.outgoingEdgesOf(savedCond));
                for (CFGEdge e : edges) {
                    CFGNode target = currCFG.getEdgeTarget(e);
                    if (target != mergeNode) {
                        currCFG.removeEdge(e);
                        addEdge(savedCond, target, edgeLabel);
                        break;
                    }
                }
            }
            
            // Else branch
            currentBlock = savedCond;
            Statement elseStmt = stmt.getElseStmt();
            if (elseStmt != null) {
                visitStatement(elseStmt);
                CFGNode elseEnd = currentBlock;
                if (elseEnd != null && elseEnd != savedCond) {
                    addEdge(elseEnd, mergeNode, "");
                }
            } else {
                addEdge(savedCond, mergeNode, "F");
            }
            
            currentBlock = mergeNode;
        }
        
        private String evaluateConditionForEdge(String condition) {
            try {
                String[] operators = {"==", "!=", ">=", "<=", ">", "<"};
                
                for (String op : operators) {
                    if (condition.contains(op)) {
                        String[] parts = condition.split(op);
                        if (parts.length == 2) {
                            String leftStr = parts[0].trim();
                            String rightStr = parts[1].trim();
                            
                            Integer leftVal = constantValues.get(leftStr);
                            Integer rightVal = constantValues.get(rightStr);
                            
                            if (leftVal == null) {
                                try { leftVal = Integer.parseInt(leftStr); } 
                                catch (NumberFormatException e) {}
                            }
                            
                            if (rightVal == null) {
                                try { rightVal = Integer.parseInt(rightStr); } 
                                catch (NumberFormatException e) {}
                            }
                            
                            if (leftVal != null && rightVal != null) {
                                boolean result = false;
                                if (op.equals("==")) result = leftVal.equals(rightVal);
                                else if (op.equals("!=")) result = !leftVal.equals(rightVal);
                                else if (op.equals(">")) result = leftVal > rightVal;
                                else if (op.equals("<")) result = leftVal < rightVal;
                                else if (op.equals(">=")) result = leftVal >= rightVal;
                                else if (op.equals("<=")) result = leftVal <= rightVal;
                                
                                return result ? "T" : "F";
                            }
                        }
                        break;
                    }
                }
            } catch (Exception e) {}
            return "";
        }

        private void visitForStmt(ForStmt stmt) {
            // Initialization
            List<Expression> inits = stmt.getInit();
            if (inits != null && !inits.isEmpty()) {
                for (Expression init : inits) {
                    CFGNode initNode = new CFGNode(init.toString());
                    currCFG.addVertex(initNode);
                    addEdge(currentBlock, initNode, "");
                    currentBlock = initNode;
                }
            }
            
            // Condition node
            Expression compare = stmt.getCompare();
            String condStr = compare != null ? compare.toString() : "true";
            CFGNode condNode = new CFGNode("for (" + condStr + ")");
            currCFG.addVertex(condNode);
            addEdge(currentBlock, condNode, "");
            
            CFGNode exitLoopNode = new CFGNode("EXIT_FOR");
            currCFG.addVertex(exitLoopNode);
            
            // Save condition node
            CFGNode savedCond = condNode;
            
            // Loop body
            CFGNode bodyEntry = null;
            Statement body = stmt.getBody();
            if (body != null) {
                currentBlock = condNode;
                visitStatement(body);
                bodyEntry = currentBlock;
            }
            
            // Update expressions
            List<Expression> updates = stmt.getUpdate();
            if (updates != null && !updates.isEmpty()) {
                for (Expression update : updates) {
                    CFGNode updateNode = new CFGNode(update.toString());
                    currCFG.addVertex(updateNode);
                    addEdge(currentBlock, updateNode, "");
                    currentBlock = updateNode;
                }
            }
            
            // Back edge to condition
            addEdge(currentBlock, savedCond, "");
            
            // True edge from condition to body (will be added via body visit)
            // False edge from condition to exit
            addEdge(savedCond, exitLoopNode, "F");
            
            currentBlock = exitLoopNode;
        }

        private void visitWhileStmt(WhileStmt stmt) {
            // Condition node
            CFGNode condNode = new CFGNode("while (" + stmt.getCondition() + ")");
            currCFG.addVertex(condNode);
            addEdge(currentBlock, condNode, "");
            
            CFGNode exitLoopNode = new CFGNode("EXIT_WHILE");
            currCFG.addVertex(exitLoopNode);
            
            CFGNode savedCond = condNode;
            
            // Loop body
            currentBlock = condNode;
            Statement body = stmt.getBody();
            if (body != null) {
                visitStatement(body);
            }
            
            // Back edge to condition
            addEdge(currentBlock, savedCond, "");
            
            // True edge through body (added via visit)
            // False edge to exit
            addEdge(savedCond, exitLoopNode, "F");
            
            currentBlock = exitLoopNode;
        }

        private void visitReturnStmt(ReturnStmt stmt) {
            Expression expr = stmt.getExpr();
            String returnStr = "return" + (expr != null ? " " + expr.toString() : "");
            CFGNode returnNode = new CFGNode(returnStr);
            currCFG.addVertex(returnNode);
            addEdge(currentBlock, returnNode, "");
            addEdge(returnNode, exitNode, "");
            currentBlock = null; // No more statements should follow
        }

        private void addEdge(CFGNode from, CFGNode to, String label) {
            if (from == null || to == null) return;
            
            CFGEdge edge = new CFGEdge(from, to);
            if (label != null && label.length() > 0) {
                edge.setLabel(label);
            }
            
            try {
                currCFG.addEdge(from, to, edge);
            } catch (IllegalArgumentException e) {
                // Edge already exists, ignore
            }
        }

        private void printCFG(String methodName) {
            System.out.print("CFG ");
            System.out.print(methodName);
            System.out.print(":");
            System.out.println();
            
            Set<CFGNode> vertexSet = currCFG.vertexSet();
            List<CFGNode> nodes = new ArrayList<CFGNode>(vertexSet);
            Collections.sort(nodes, new Comparator<CFGNode>() {
                public int compare(CFGNode n1, CFGNode n2) {
                    return n1.getID() - n2.getID();
                }
            });
            
            for (CFGNode node : nodes) {
                System.out.print("\t");
                System.out.print("node ");
                System.out.print(node.getID());
                System.out.print(": ");
                System.out.print(node.getLabel());
                System.out.print(" ");
                
                Set<CFGEdge> outEdges = currCFG.outgoingEdgesOf(node);
                for (CFGEdge edge : outEdges) {
                    CFGNode target = currCFG.getEdgeTarget(edge);
                    String edgeLabel = edge.getLabel();
                    if (edgeLabel == null) {
                        edgeLabel = "";
                    }
                    System.out.print("edge ");
                    System.out.print(edgeLabel);
                    System.out.print(" ");
                    System.out.print(target.getID());
                    System.out.print("; ");
                }
                System.out.println();
            }
        }
    }
}
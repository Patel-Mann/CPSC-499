
import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.*;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.File;

//graph imports
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.traverse.*;

import java.io.*;
import java.net.*;
import java.util.*;


import japa.parser.ast.stmt.ExpressionStmt;

import CFGGraph.CFGNode;
import CFGGraph.CFGEdge;

public class LCA_JP1_0_0 {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java LCA_JP1_0_0 <JavaSourceFile>");
            return;
        }
        System.out.println("WELCOME TO THE JUNGLE");
        FileInputStream in = new FileInputStream(new File(args[0]));
        CompilationUnit cu = JavaParser.parse(in);
        in.close();

        CFGVisitor visitor = new CFGVisitor();
        visitor.visit(cu, null);
    }

    static class CFGVisitor extends VoidVisitorAdapter<Void> {
        private Graph<CFGNode, CFGEdge> currCFG = new DefaultDirectedGraph<>(CFGEdge.class);
        private CFGNode currentBlock;

        @Override
        public void visit(MethodDeclaration n, Void arg) {
            CFGNode funcNode = new CFGNode(n.getName());

            System.out.println("CFG Building Root: " + n.getName());
            //we assign root node
            currCFG.addVertex(funcNode);
            //update curr node
            currentBlock = funcNode;
            super.visit(n, arg);
            System.out.println(currCFG.toString());
        }

        @Override
        public void visit(ExpressionStmt n, Void arg) {
//            CFGNode exprNode = new CFGNode(n.toString())
//            currCFG.addVertex(exprNode)
//            //link nodes
//            currCFG.addEdge(currentBlock, exprNode);
//            currentBlock = exprNode;
//            super.visit(n, arg);
        }

    }

}
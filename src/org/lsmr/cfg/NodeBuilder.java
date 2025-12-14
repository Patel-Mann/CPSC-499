package org.lsmr.cfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ForkJoinWorkerThread;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.lsmr.cfg.Edge.EdgeLabel;
import org.lsmr.cfg.Java1_4Parser.ArgumentsContext;
import org.lsmr.cfg.Java1_4Parser.ArrayCreatorRestContext;
import org.lsmr.cfg.Java1_4Parser.ArrayInitializerContext;
import org.lsmr.cfg.Java1_4Parser.AssertStatementContext;
import org.lsmr.cfg.Java1_4Parser.AssignmentOperatorContext;
import org.lsmr.cfg.Java1_4Parser.BasicTypeContext;
import org.lsmr.cfg.Java1_4Parser.BlockContext;
import org.lsmr.cfg.Java1_4Parser.BlockStatementContext;
import org.lsmr.cfg.Java1_4Parser.BreakStatementContext;
import org.lsmr.cfg.Java1_4Parser.CatchClauseContext;
import org.lsmr.cfg.Java1_4Parser.CatchesContext;
import org.lsmr.cfg.Java1_4Parser.ClassBodyContext;
import org.lsmr.cfg.Java1_4Parser.ClassBodyDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.ClassCreatorRestContext;
import org.lsmr.cfg.Java1_4Parser.ClassDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.ClassOrInterfaceDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.CompilationUnitContext;
import org.lsmr.cfg.Java1_4Parser.ConstantDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.ConstantDeclaratorContext;
import org.lsmr.cfg.Java1_4Parser.ConstantExpressionContext;
import org.lsmr.cfg.Java1_4Parser.ConstructorBodyContext;
import org.lsmr.cfg.Java1_4Parser.ConstructorDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.ContinueStatementContext;
import org.lsmr.cfg.Java1_4Parser.CreatorContext;
import org.lsmr.cfg.Java1_4Parser.DimsContext;
import org.lsmr.cfg.Java1_4Parser.DoStatementContext;
import org.lsmr.cfg.Java1_4Parser.ElseClauseContext;
import org.lsmr.cfg.Java1_4Parser.EmptyDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.EmptyStatementContext;
import org.lsmr.cfg.Java1_4Parser.ExplicitConstructorInvocationContext;
import org.lsmr.cfg.Java1_4Parser.Expression1Context;
import org.lsmr.cfg.Java1_4Parser.Expression1RestContext;
import org.lsmr.cfg.Java1_4Parser.Expression2Context;
import org.lsmr.cfg.Java1_4Parser.Expression2RestContext;
import org.lsmr.cfg.Java1_4Parser.Expression3Context;
import org.lsmr.cfg.Java1_4Parser.ExpressionContext;
import org.lsmr.cfg.Java1_4Parser.ExpressionStatementContext;
import org.lsmr.cfg.Java1_4Parser.ExtendsInterfacesContext;
import org.lsmr.cfg.Java1_4Parser.FieldDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.FinallyClauseContext;
import org.lsmr.cfg.Java1_4Parser.ForInitContext;
import org.lsmr.cfg.Java1_4Parser.ForStatementContext;
import org.lsmr.cfg.Java1_4Parser.ForUpdateContext;
import org.lsmr.cfg.Java1_4Parser.FormalParameterContext;
import org.lsmr.cfg.Java1_4Parser.FormalParametersContext;
import org.lsmr.cfg.Java1_4Parser.IdentifierContext;
import org.lsmr.cfg.Java1_4Parser.IdentifierSuffixContext;
import org.lsmr.cfg.Java1_4Parser.IfStatementContext;
import org.lsmr.cfg.Java1_4Parser.ImportDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.InfixOpContext;
import org.lsmr.cfg.Java1_4Parser.InitializerContext;
import org.lsmr.cfg.Java1_4Parser.InnerCreatorContext;
import org.lsmr.cfg.Java1_4Parser.InterfaceBodyContext;
import org.lsmr.cfg.Java1_4Parser.InterfaceBodyDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.InterfaceDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.InterfaceMemberDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.InterfaceMethodDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.LabeledStatementContext;
import org.lsmr.cfg.Java1_4Parser.LiteralContext;
import org.lsmr.cfg.Java1_4Parser.LocalVariableDeclarationStatementContext;
import org.lsmr.cfg.Java1_4Parser.MemberDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.MethodDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.ModifierContext;
import org.lsmr.cfg.Java1_4Parser.ParenthesizedExpressionContext;
import org.lsmr.cfg.Java1_4Parser.PostfixOpContext;
import org.lsmr.cfg.Java1_4Parser.PrefixOpContext;
import org.lsmr.cfg.Java1_4Parser.PrimaryContext;
import org.lsmr.cfg.Java1_4Parser.QualifiedIdentifierContext;
import org.lsmr.cfg.Java1_4Parser.QualifiedIdentifiersContext;
import org.lsmr.cfg.Java1_4Parser.ResultContext;
import org.lsmr.cfg.Java1_4Parser.ReturnStatementContext;
import org.lsmr.cfg.Java1_4Parser.SelectorContext;
import org.lsmr.cfg.Java1_4Parser.StatementContext;
import org.lsmr.cfg.Java1_4Parser.StatementExpressionContext;
import org.lsmr.cfg.Java1_4Parser.StaticInitializerContext;
import org.lsmr.cfg.Java1_4Parser.SuperSuffixContext;
import org.lsmr.cfg.Java1_4Parser.SuperclassContext;
import org.lsmr.cfg.Java1_4Parser.SuperinterfacesContext;
import org.lsmr.cfg.Java1_4Parser.SwitchBlockStatementGroupContext;
import org.lsmr.cfg.Java1_4Parser.SwitchLabelContext;
import org.lsmr.cfg.Java1_4Parser.SwitchStatementContext;
import org.lsmr.cfg.Java1_4Parser.SynchronizedStatementContext;
import org.lsmr.cfg.Java1_4Parser.ThrowStatementContext;
import org.lsmr.cfg.Java1_4Parser.Throws_Context;
import org.lsmr.cfg.Java1_4Parser.TryStatementContext;
import org.lsmr.cfg.Java1_4Parser.TypeContext;
import org.lsmr.cfg.Java1_4Parser.TypeDeclarationContext;
import org.lsmr.cfg.Java1_4Parser.TypeListContext;
import org.lsmr.cfg.Java1_4Parser.VariableDeclaratorContext;
import org.lsmr.cfg.Java1_4Parser.VariableDeclaratorIdContext;
import org.lsmr.cfg.Java1_4Parser.VariableDeclaratorsContext;
import org.lsmr.cfg.Java1_4Parser.VariableInitializerContext;
import org.lsmr.cfg.Java1_4Parser.WhileStatementContext;

/**
 * This class will visit the nodes in a parse tree to: (1) find individual
 * methods to process, (2) process each method to construct the CFG nodes for
 * it, (3) determine the edge locations for each node, and (4) add the edges to
 * each graph.
 * <p>
 * Given the root of an AST and an instance of this class, call
 * {@code root.accept(instance}} to visit the pertinent nodes therein to
 * construct a set of CFGs.
 * <p>
 * At the end of the complete visit, use the {@link #getCFGs() getCFGs()} method
 * to obtain the constructed CFGs.
 * <p>
 * The resulting CFG will be based on statements, ignoring finer-grained
 * considerations.
 */
public class NodeBuilder implements Java1_4ParserVisitor<WorkingGraph> {
	private Map<RuleContext, ControlFlowGraph> graphMap = new HashMap<>();
	private Stack<String> nameStack = new Stack<>();
	private int nodeCounter = 0;

	private String getCurrentName() {
		StringBuilder sb = new StringBuilder();

		for (String name : nameStack) {
			if (sb.length() == 0)
				sb.append(name);
			else {
				sb.append('.');
				sb.append(name);
			}
		}

		return sb.toString();
	}

	/**
	 * Obtains a list of the CFGs that have been constructed from the visit. It
	 * makes most sense to call this method once the visit has been completed.
	 * 
	 * @return An unmodifiable list of the constructed CFGs for the visited AST.
	 */
	public List<ControlFlowGraph> getCFGs() {
		return Collections.unmodifiableList(new ArrayList<>(graphMap.values()));
	}

	@Override
	public WorkingGraph visit(ParseTree arg0) {
		return arg0.accept(this);
	}

	@Override
	public WorkingGraph visitChildren(RuleNode arg0) {
		int childCount = arg0.getChildCount();
		WorkingGraph result = null;

		for (int i = 0; i < childCount; i++)
			result = visit(arg0.getChild(i));

		return result;
	}

	@Override
	public WorkingGraph visitErrorNode(ErrorNode arg0) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitTerminal(TerminalNode arg0) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitIdentifier(IdentifierContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitQualifiedIdentifier(QualifiedIdentifierContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitLiteral(LiteralContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitType(TypeContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitBasicType(BasicTypeContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitExpression(ExpressionContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitAssignmentOperator(AssignmentOperatorContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitExpression1(Expression1Context ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitExpression1Rest(Expression1RestContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitExpression2(Expression2Context ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitExpression2Rest(Expression2RestContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitInfixOp(InfixOpContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitExpression3(Expression3Context ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitPrefixOp(PrefixOpContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitPostfixOp(PostfixOpContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitPrimary(PrimaryContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitIdentifierSuffix(IdentifierSuffixContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitSelector(SelectorContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitSuperSuffix(SuperSuffixContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitArguments(ArgumentsContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitCreator(CreatorContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitInnerCreator(InnerCreatorContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitArrayCreatorRest(ArrayCreatorRestContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitClassCreatorRest(ClassCreatorRestContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitArrayInitializer(ArrayInitializerContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitVariableInitializer(VariableInitializerContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitBlock(BlockContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": block");
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		List<BlockStatementContext> statements = ctx.blockStatement();

		for (int i = 0, size = statements.size(); i < size; i++)
			s.connect(visitBlockStatement(statements.get(i)));

		return s;
	}

	@SuppressWarnings("javadoc")
	public WorkingGraph visitTryBlock(BlockContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": block");
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		List<BlockStatementContext> statements = ctx.blockStatement();

		for (int i = 0, size = statements.size(); i < size; i++) {
			WorkingGraph bs = visitBlockStatement(statements.get(i));

			bs.edges.add(g.buildEdge(bs.node, null, EdgeLabel.THROWN));
			s.connect(bs);
		}

		return s;
	}

	@SuppressWarnings("javadoc")
	public WorkingGraph visitFinallyBlock(BlockContext ctx, boolean delayedThrown) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": block");
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		if (delayedThrown)
			s.edges.add(g.buildEdge(s.node, null, EdgeLabel.THROWN_DELAYED));

		List<BlockStatementContext> statements = ctx.blockStatement();

		for (int i = 0, size = statements.size(); i < size; i++) {
			WorkingGraph bs = visitBlockStatement(statements.get(i));

			if (delayedThrown)
				if (i == size - 1)
					bs.edges.add(g.buildEdge(bs.node, null, EdgeLabel.THROWN));
				else
					bs.edges.add(g.buildEdge(bs.node, null, EdgeLabel.THROWN_DELAYED));

			s.connect(bs);
		}

		return s;
	}

	@Override
	public WorkingGraph visitBlockStatement(BlockStatementContext ctx) {
		return visitChildren(ctx);
	}

	ControlFlowGraph getGraph(RuleContext ctx) {
		RuleContext current = ctx;

		while (current != null && !(current instanceof MemberDeclarationContext)
				&& !(current instanceof ClassBodyDeclarationContext) && !(current instanceof InterfaceBodyContext))
			current = current.getParent();

		if (current != null)
			return graphMap.get(current);

		return null;
	}

	@Override
	public WorkingGraph visitLocalVariableDeclarationStatement(LocalVariableDeclarationStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": " + ctx.accept(new TreePrinter()));
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		return s;
	}

	WorkingGraph visitLinearStatement(RuleContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": " + ctx.accept(new TreePrinter()));
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		return s;
	}

	@Override
	public WorkingGraph visitAssertStatement(AssertStatementContext ctx) {
		return visitLinearStatement(ctx);
	}

	@Override
	public WorkingGraph visitBreakStatement(BreakStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": " + ctx.accept(new TreePrinter()));
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());

		IdentifierContext ic = ctx.identifier();
		String label = "";

		if (ic != null)
			label = ic.getText().intern();

		List<Edge> list = s.breakEdges.get(label);

		if (list == null) {
			list = new ArrayList<>();
			s.breakEdges.put(label, list);
		}

		list.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		return s;
	}

	@Override
	public WorkingGraph visitContinueStatement(ContinueStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": " + ctx.accept(new TreePrinter()));
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());

		IdentifierContext ic = ctx.identifier();
		String label = "";

		if (ic != null)
			label = ic.getText();

		List<Edge> list = s.continueEdges.get(label);

		if (list == null) {
			list = new ArrayList<>();
			s.continueEdges.put(label, list);
		}

		list.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		return s;
	}

	@Override
	public WorkingGraph visitDoStatement(DoStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": do");
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());

		WorkingGraph doBody = visitStatement(ctx.statement());

		g.buildEdge(s.node, doBody.node, EdgeLabel.BLANK);

		Node whileExpression = g
				.buildNode(nodeCounter++ + ": while " + ctx.parenthesizedExpression().accept(new TreePrinter()) + "; ");

		g.buildEdge(whileExpression, s.node, EdgeLabel.TRUE);

		for (Edge edge : doBody.edges) {
			if (edge.label() == EdgeLabel.THROWN)
				s.edges.add(edge);
			else
				edge.target = whileExpression;
		}

		// Labelled edges will be handled by labelled statement

		List<Edge> nonLabelledBreakEdges = doBody.breakEdges.get("");

		if (nonLabelledBreakEdges != null) {
			s.edges.addAll(nonLabelledBreakEdges);
			s.breakEdges.remove("");
		}

		List<Edge> nonLabelledContinueEdges = doBody.continueEdges.get("");

		if (nonLabelledContinueEdges != null) {
			for (Edge edge : nonLabelledContinueEdges) {
				edge.setTarget(whileExpression);
				s.edges.add(edge);
			}

			s.continueEdges.remove("");
		}

		s.edges.add(g.buildEdge(whileExpression, null, EdgeLabel.FALSE));

		return s;
	}

	@Override
	public WorkingGraph visitIfStatement(IfStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": if " + ctx.parenthesizedExpression().accept(new TreePrinter()));
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());

		WorkingGraph trueNode = visitStatement(ctx.statement());

		s.edges.add(g.buildEdge(s.node, trueNode.node, EdgeLabel.TRUE));
		s.edges = trueNode.edges;

		ElseClauseContext elseClause = ctx.elseClause();

		if (elseClause != null) {
			WorkingGraph elseNode = visitElseClause(elseClause);

			s.edges.add(g.buildEdge(s.node, elseNode.node, EdgeLabel.FALSE));
			s.connect(elseNode);
		} else
			s.edges.add(g.buildEdge(s.node, null, EdgeLabel.FALSE));

		return s;
	}

	@Override
	public WorkingGraph visitLabeledStatement(LabeledStatementContext ctx) {
		String label = ctx.identifier().getText();
		StatementContext sc = ctx.statement();
		WorkingGraph s = visitStatement(sc);
		List<Edge> breakEdges = s.breakEdges.get(label);

		if (breakEdges != null) {
			s.breakEdges.remove(label);
			s.edges.addAll(breakEdges);
		}

		List<Edge> continueEdges = s.continueEdges.get(label);

		if (continueEdges != null) {
			s.continueEdges.remove(label);

			if (sc.forStatement() != null || sc.whileStatement() != null || sc.doStatement() != null)
				for (Edge continueEdge : continueEdges)
					continueEdge.setTarget(s.node);
		}

		return s;
	}

	@Override
	public WorkingGraph visitTryStatement(TryStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		BlockContext tryBlock = ctx.block();
		CatchesContext catches = ctx.catches();
		List<CatchClauseContext> catchClauses = catches == null ? new ArrayList<>() : catches.catchClause();
		FinallyClauseContext finallyClause = ctx.finallyClause();

		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": try");
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		WorkingGraph tryBody = visitTryBlock(tryBlock);

		s.connect(tryBody);

		for (CatchClauseContext catch_ : catchClauses) {
			WorkingGraph catchClause = visitCatchClause(catch_);

			for (Edge edge : s.edges) {
				if (edge.label() == EdgeLabel.THROWN && edge.target() == null)
					edge.setTarget(catchClause.node);
			}

			s.edges.addAll(catchClause.edges);
		}

		if (finallyClause != null) {
			boolean delayedThrown = false;

			for (Edge edge : s.edges)
				if (edge.label() == EdgeLabel.THROWN && edge.target() == null) {
					delayedThrown = true;
					break;
				}

			WorkingGraph finallyBody = visitFinallyClause(finallyClause, delayedThrown);

			for (Edge edge : s.edges)
				if (edge.target() == null)
					edge.setTarget(finallyBody.node);

			s.edges.addAll(finallyBody.edges);
		}

		return s;
	}

	@Override
	public WorkingGraph visitForStatement(ForStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();
		ForInitContext forInit = ctx.forInit();
		ExpressionContext expression = ctx.expression();
		ForUpdateContext forUpdate = ctx.forUpdate();
		StringBuilder sb = new StringBuilder();

		sb.append(nodeCounter++);
		sb.append(": for ( ");
		sb.append(forInit == null ? "; " : forInit.accept(new TreePrinter()) + "; ");
		sb.append(expression == null ? "; " : expression.accept(new TreePrinter()) + "; ");

		if (forUpdate != null)
			sb.append(forUpdate.accept(new TreePrinter()));

		sb.append(")");
		s.node = g.buildNode(sb.toString());
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());

		WorkingGraph forBody = visitStatement(ctx.statement());

		s.connect(forBody);
		s.edges.add(g.buildEdge(s.node, forBody.node, EdgeLabel.TRUE));
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.FALSE));

		for (Edge edge : forBody.edges) {
			if (edge.target() == null && edge.label() != EdgeLabel.THROWN)
				edge.setTarget(s.node);
		}
		// s.edges.addAll(forBody.edges);

		// Labelled edges will be handled by labelled statement

		List<Edge> nonLabelledBreakEdges = forBody.breakEdges.get("");

		if (nonLabelledBreakEdges != null) {
			s.edges.addAll(nonLabelledBreakEdges);
			s.breakEdges.remove("");
		}

		List<Edge> nonLabelledContinueEdges = forBody.continueEdges.get("");

		if (nonLabelledContinueEdges != null) {
			for (Edge edge : nonLabelledContinueEdges) {
				edge.setTarget(s.node);
				s.edges.add(edge);
			}

			s.continueEdges.remove("");
		}

		return s;
	}

	@Override
	public WorkingGraph visitWhileStatement(WhileStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();
		ParenthesizedExpressionContext condition = ctx.parenthesizedExpression();

		s.node = g.buildNode(nodeCounter++ + ": while " + condition.accept(new TreePrinter()));
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());

		WorkingGraph whileBody = visitStatement(ctx.statement());

		s.connect(whileBody);
		g.buildEdge(s.node, whileBody.node, EdgeLabel.TRUE);
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.FALSE));

		for (Edge edge : whileBody.edges) {
			if (edge.label() == EdgeLabel.THROWN)
				s.edges.add(edge);
			else
				edge.target = s.node;
		}

		// Labelled edges will be handled by labelled statement

		List<Edge> nonLabelledBreakEdges = whileBody.breakEdges.get("");

		if (nonLabelledBreakEdges != null) {
			s.edges.addAll(nonLabelledBreakEdges);
			s.breakEdges.remove("");
		}

		List<Edge> nonLabelledContinueEdges = whileBody.continueEdges.get("");

		if (nonLabelledContinueEdges != null) {
			for (Edge edge : nonLabelledContinueEdges) {
				edge.setTarget(s.node);
				s.edges.add(edge);
			}

			s.continueEdges.remove("");
		}

		return s;
	}

	@Override
	public WorkingGraph visitSwitchStatement(SwitchStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();
		List<SwitchBlockStatementGroupContext> statementGroups = ctx.switchBlockStatementGroup();

		s.node = g.buildNode(nodeCounter++ + ": switch " + ctx.parenthesizedExpression().accept(new TreePrinter()));
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());

		if (!statementGroups.isEmpty())
			for (SwitchBlockStatementGroupContext group : statementGroups) {
				List<SwitchLabelContext> labels = group.switchLabel();
				List<BlockStatementContext> statements = group.blockStatement();

				WorkingGraph first = visitBlockStatement(statements.get(0));
				TreePrinter printer = new TreePrinter();

				for (SwitchLabelContext label : labels)
					s.edges.add(g.buildEdge(s.node, first.node, EdgeLabel.CASE, label.accept(printer)));

				for (int i = 1, size = statements.size(); i < size; i++) {
					WorkingGraph later = visitBlockStatement(statements.get(i));

					for (List<Edge> list : later.breakEdges.values())
						first.edges.addAll(list);

					later.breakEdges.clear();
					first.connect(later);
				}

				s.connect(first);
			}
		else
			s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		List<SwitchLabelContext> extraLabels = ctx.switchLabel();

		for (SwitchLabelContext label : extraLabels)
			s.edges.add(g.buildEdge(s.node, null, EdgeLabel.CASE, label.accept(new TreePrinter())));

		return s;
	}

	@Override
	public WorkingGraph visitSynchronizedStatement(SynchronizedStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g
				.buildNode(nodeCounter++ + ": synchronized " + ctx.parenthesizedExpression().accept(new TreePrinter()));
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		WorkingGraph block = visitBlock(ctx.block());

		s.connect(block);

		return s;
	}

	@Override
	public WorkingGraph visitReturnStatement(ReturnStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": " + ctx.accept(new TreePrinter()));
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		return s;
	}

	@Override
	public WorkingGraph visitThrowStatement(ThrowStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": " + ctx.accept(new TreePrinter()));
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.THROWN));

		return s;
	}

	@Override
	public WorkingGraph visitEmptyStatement(EmptyStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": *EMPTY* ; ");
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		return s;
	}

	@Override
	public WorkingGraph visitExpressionStatement(ExpressionStatementContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": " + ctx.accept(new TreePrinter()));
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		return s;
	}

	@Override
	public WorkingGraph visitStatementExpression(StatementExpressionContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitConstantExpression(ConstantExpressionContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitCatches(CatchesContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitCatchClause(CatchClauseContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g
				.buildNode(nodeCounter++ + ": catch ( " + ctx.formalParameter().type().accept(new TreePrinter()) + ")");
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());

		WorkingGraph body = visitBlock(ctx.block());

		s.edges.add(g.buildEdge(s.node, body.node, EdgeLabel.CAUGHT));
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.THROWN));
		s.edges.addAll(body.edges);

		return s;
	}

	@Override
	public WorkingGraph visitSwitchBlockStatementGroup(SwitchBlockStatementGroupContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitSwitchLabel(SwitchLabelContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitForInit(ForInitContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitForUpdate(ForUpdateContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitModifier(ModifierContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitVariableDeclarators(VariableDeclaratorsContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitVariableDeclarator(VariableDeclaratorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitVariableDeclaratorId(VariableDeclaratorIdContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitConstantDeclarator(ConstantDeclaratorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitCompilationUnit(CompilationUnitContext ctx) {
		QualifiedIdentifierContext pkg = ctx.qualifiedIdentifier();

		if (pkg != null)
			nameStack.push(pkg.accept(new TreePrinter()));

		for (TypeDeclarationContext typeDeclaration : ctx.typeDeclaration())
			typeDeclaration.accept(this);

		return null;
	}

	@Override
	public WorkingGraph visitImportDeclaration(ImportDeclarationContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitTypeDeclaration(TypeDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitClassOrInterfaceDeclaration(ClassOrInterfaceDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitClassDeclaration(ClassDeclarationContext ctx) {
		nameStack.push(ctx.identifier().getText());
		visitClassBody(ctx.classBody());

		return null;
	}

	@Override
	public WorkingGraph visitInterfaceDeclaration(InterfaceDeclarationContext ctx) {
		// Possible nested classes
		nameStack.push(ctx.identifier().getText());
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitTypeList(TypeListContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitClassBody(ClassBodyContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitInterfaceBody(InterfaceBodyContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitMemberDeclaration(MemberDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	private String getSignature(String identifier, FormalParametersContext ctx) {
		StringBuilder sb = new StringBuilder();

		sb.append(identifier);
		sb.append("(");

		List<FormalParameterContext> formals = ctx.formalParameter();
		int size = formals.size();

		if (size > 0) {
			sb.append(formals.get(0).type().accept(new TreePrinter()));

			for (int i = 1; i < size; i++) {
				sb.append(", ");
				sb.append(formals.get(i).type().accept(new TreePrinter()));
			}
		}

		sb.append(")");

		return sb.toString();
	}

	@Override
	public WorkingGraph visitMethodDeclaration(MethodDeclarationContext ctx) {
		if (ctx.block() != null) {
			String identifier = ctx.identifier().getText();
			String signature = getSignature(identifier, ctx.formalParameters());
			ControlFlowGraph g = new ControlFlowGraph(getCurrentName() + "." + signature);

			graphMap.put(ctx.parent, g);

			WorkingGraph s = visitBlock(ctx.block());

			g.buildEdge(g.entry, s.node, EdgeLabel.BLANK);

			for (Edge edge : s.edges) {
				EdgeLabel label = edge.label();

				if (edge.target() == null) {
					if (label == EdgeLabel.THROWN)
						edge.setTarget(g.abruptExit);
					else
						edge.setTarget(g.normalExit);
				}
			}

			if (!s.breakEdges.isEmpty())
				throw new IllegalStateException("WorkingGraph still contains break edges: " + s);

			if (!s.continueEdges.isEmpty())
				throw new IllegalStateException("WorkingGraph still contains continue edges: " + s);
		}

		return null;
	}

	@Override
	public WorkingGraph visitInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
		// Potentially nested classes
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitFormalParameters(FormalParametersContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitFormalParameter(FormalParameterContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitDims(DimsContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitParenthesizedExpression(ParenthesizedExpressionContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitStatement(StatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitSuperclass(SuperclassContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitSuperinterfaces(SuperinterfacesContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitExtendsInterfaces(ExtendsInterfacesContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitEmptyDeclaration(EmptyDeclarationContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitInitializer(InitializerContext ctx) {
		ControlFlowGraph g = new ControlFlowGraph(getCurrentName() + ".<init>");

		graphMap.put(ctx.parent, g);

		WorkingGraph s = visitBlock(ctx.block());

		g.buildEdge(g.entry, s.node, EdgeLabel.BLANK);

		for (Edge edge : s.edges) {
			EdgeLabel label = edge.label();

			if (edge.target() == null) {
				if (label == EdgeLabel.THROWN)
					edge.setTarget(g.abruptExit);
				else
					edge.setTarget(g.normalExit);
			}
		}

		if (!s.breakEdges.isEmpty())
			throw new IllegalStateException("WorkingGraph still contains break edges: " + s);

		if (!s.continueEdges.isEmpty())
			throw new IllegalStateException("WorkingGraph still contains continue edges: " + s);

		return null;
	}

	@Override
	public WorkingGraph visitStaticInitializer(StaticInitializerContext ctx) {
		ControlFlowGraph g = new ControlFlowGraph(getCurrentName() + ".<clinit>");

		graphMap.put(ctx.parent, g);

		WorkingGraph s = visitBlock(ctx.block());

		g.buildEdge(g.entry, s.node, EdgeLabel.BLANK);

		for (Edge edge : s.edges) {
			EdgeLabel label = edge.label();

			if (edge.target() == null) {
				if (label == EdgeLabel.THROWN)
					edge.setTarget(g.abruptExit);
				else
					edge.setTarget(g.normalExit);
			}
		}

		if (!s.breakEdges.isEmpty())
			throw new IllegalStateException("WorkingGraph still contains break edges: " + s);

		if (!s.continueEdges.isEmpty())
			throw new IllegalStateException("WorkingGraph still contains continue edges: " + s);

		return null;
	}

	@Override
	public WorkingGraph visitResult(ResultContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitThrows_(Throws_Context ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitFieldDeclaration(FieldDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitConstructorDeclaration(ConstructorDeclarationContext ctx) {
		String identifier = ctx.identifier().getText();
		String signature = getSignature(identifier, ctx.formalParameters());
		ControlFlowGraph g = new ControlFlowGraph(getCurrentName() + "." + signature);

		graphMap.put(ctx.parent, g);

		WorkingGraph s = visitConstructorBody(ctx.constructorBody());

		g.buildEdge(g.entry, s.node, EdgeLabel.BLANK);

		for (Edge edge : s.edges) {
			EdgeLabel label = edge.label();

			if (edge.target() == null) {
				if (label == EdgeLabel.THROWN)
					edge.setTarget(g.abruptExit);
				else
					edge.setTarget(g.normalExit);
			}
		}

		if (!s.breakEdges.isEmpty())
			throw new IllegalStateException("WorkingGraph still contains break edges: " + s);

		if (!s.continueEdges.isEmpty())
			throw new IllegalStateException("WorkingGraph still contains continue edges: " + s);

		return null;
	}

	@Override
	public WorkingGraph visitConstructorBody(ConstructorBodyContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": body");
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		WorkingGraph body = null;
		ExplicitConstructorInvocationContext eci = ctx.explicitConstructorInvocation();

		if (eci != null) {
			body = visitExplicitConstructorInvocation(eci);
			s.connect(body);
		}

		for (BlockStatementContext statement : ctx.blockStatement()) {
			body = visitBlockStatement(statement);
			s.connect(body);
		}

		return s;
	}

	@Override
	public WorkingGraph visitExplicitConstructorInvocation(ExplicitConstructorInvocationContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": " + ctx.accept(new TreePrinter()));
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());
		s.edges.add(g.buildEdge(s.node, null, EdgeLabel.BLANK));

		return s;
	}

	@Override
	public WorkingGraph visitInterfaceMemberDeclaration(InterfaceMemberDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public WorkingGraph visitInterfaceMethodDeclaration(InterfaceMethodDeclarationContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitConstantDeclaration(ConstantDeclarationContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitQualifiedIdentifiers(QualifiedIdentifiersContext ctx) {
		// Ignore
		return null;
	}

	@Override
	public WorkingGraph visitElseClause(ElseClauseContext ctx) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": else");
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());

		WorkingGraph body = visitStatement(ctx.statement());

		s.edges.add(g.buildEdge(s.node, body.node, EdgeLabel.BLANK));
		s.connect(body);

		return s;
	}

	@Override
	public WorkingGraph visitFinallyClause(FinallyClauseContext ctx) {
		// Ignore
		return null;
	}

	@SuppressWarnings("javadoc")
	public WorkingGraph visitFinallyClause(FinallyClauseContext ctx, boolean delayedThrown) {
		ControlFlowGraph g = getGraph(ctx);
		WorkingGraph s = new WorkingGraph();

		s.node = g.buildNode(nodeCounter++ + ": finally");
		// addlinenumber
		s.node.setLineNumber(ctx.getStart().getLine());

		WorkingGraph body = visitFinallyBlock(ctx.block(), delayedThrown);

		s.edges.add(g.buildEdge(s.node, body.node, EdgeLabel.BLANK));

		if (delayedThrown)
			s.edges.add(g.buildEdge(s.node, body.node, EdgeLabel.THROWN_DELAYED));

		return s;
	}
}

class WorkingGraph {
	public List<Edge> edges = new ArrayList<>();
	public Node node;
	public Map<String, List<Edge>> breakEdges = new HashMap<>();
	public Map<String, List<Edge>> continueEdges = new HashMap<>();

	public void connect(WorkingGraph s) {
		if (s == null)
			return;

		for (Edge edge : edges) {
			if (edge.label() != EdgeLabel.THROWN && edge.target == null)
				edge.setTarget(s.node);
		}

		edges.addAll(s.edges);

		for (String label : s.breakEdges.keySet()) {
			List<Edge> sEdges = s.breakEdges.get(label);
			List<Edge> thisEdges = breakEdges.get(label);

			if (thisEdges == null)
				breakEdges.put(label, sEdges);
			else
				thisEdges.addAll(sEdges);
		}

		for (String label : s.continueEdges.keySet()) {
			List<Edge> sEdges = s.continueEdges.get(label);
			List<Edge> thisEdges = continueEdges.get(label);

			if (thisEdges == null)
				continueEdges.put(label, sEdges);
			else
				thisEdges.addAll(sEdges);
		}
	}
}

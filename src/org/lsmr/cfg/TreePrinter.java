package org.lsmr.cfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
 * This class will visit the nodes in a parse tree to print the resulting code.
 */
public class TreePrinter implements Java1_4ParserVisitor<String> {
	@Override
	public String visit(ParseTree arg0) {
		return arg0.accept(this);
	}

	@Override
	public String visitChildren(RuleNode arg0) {
		StringBuilder sb = new StringBuilder();
		int childCount = arg0.getChildCount();

		for(int i = 0; i < childCount; i++) {
			sb.append(visit(arg0.getChild(i)));
			
			if(sb.charAt(sb.length() - 1) != ' ')
				sb.append(" ");
		}

		return sb.toString();
	}

	@Override
	public String visitErrorNode(ErrorNode arg0) {
		return null;
	}

	@Override
	public String visitTerminal(TerminalNode arg0) {
		return arg0.getText();
	}

	@Override
	public String visitIdentifier(IdentifierContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitQualifiedIdentifier(QualifiedIdentifierContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitLiteral(LiteralContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitType(TypeContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitBasicType(BasicTypeContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitExpression(ExpressionContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitAssignmentOperator(AssignmentOperatorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitExpression1(Expression1Context ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitExpression1Rest(Expression1RestContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitExpression2(Expression2Context ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitExpression2Rest(Expression2RestContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitInfixOp(InfixOpContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitExpression3(Expression3Context ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitPrefixOp(PrefixOpContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitPostfixOp(PostfixOpContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitPrimary(PrimaryContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitIdentifierSuffix(IdentifierSuffixContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitSelector(SelectorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitSuperSuffix(SuperSuffixContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitArguments(ArgumentsContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitCreator(CreatorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitInnerCreator(InnerCreatorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitArrayCreatorRest(ArrayCreatorRestContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitClassCreatorRest(ClassCreatorRestContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitArrayInitializer(ArrayInitializerContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitVariableInitializer(VariableInitializerContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitBlock(BlockContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitBlockStatement(BlockStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitLocalVariableDeclarationStatement(LocalVariableDeclarationStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitAssertStatement(AssertStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitBreakStatement(BreakStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitContinueStatement(ContinueStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitDoStatement(DoStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitIfStatement(IfStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitTryStatement(TryStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitForStatement(ForStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitWhileStatement(WhileStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitSwitchStatement(SwitchStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitSynchronizedStatement(SynchronizedStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitReturnStatement(ReturnStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitThrowStatement(ThrowStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitEmptyStatement(EmptyStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitExpressionStatement(ExpressionStatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitStatementExpression(StatementExpressionContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitConstantExpression(ConstantExpressionContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitCatches(CatchesContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitCatchClause(CatchClauseContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitSwitchBlockStatementGroup(SwitchBlockStatementGroupContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitSwitchLabel(SwitchLabelContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitForInit(ForInitContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitForUpdate(ForUpdateContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitModifier(ModifierContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitVariableDeclarators(VariableDeclaratorsContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitVariableDeclarator(VariableDeclaratorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitVariableDeclaratorId(VariableDeclaratorIdContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitConstantDeclarator(ConstantDeclaratorContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitCompilationUnit(CompilationUnitContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitImportDeclaration(ImportDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitTypeDeclaration(TypeDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitClassOrInterfaceDeclaration(ClassOrInterfaceDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitClassDeclaration(ClassDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitInterfaceDeclaration(InterfaceDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitTypeList(TypeListContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitClassBody(ClassBodyContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitInterfaceBody(InterfaceBodyContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitClassBodyDeclaration(ClassBodyDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitMemberDeclaration(MemberDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitMethodDeclaration(MethodDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitInterfaceBodyDeclaration(InterfaceBodyDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitFormalParameters(FormalParametersContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitFormalParameter(FormalParameterContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitDims(DimsContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitParenthesizedExpression(ParenthesizedExpressionContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitStatement(StatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitSuperclass(SuperclassContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitSuperinterfaces(SuperinterfacesContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitExtendsInterfaces(ExtendsInterfacesContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitEmptyDeclaration(EmptyDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitInitializer(InitializerContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitStaticInitializer(StaticInitializerContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitResult(ResultContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitThrows_(Throws_Context ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitFieldDeclaration(FieldDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitConstructorDeclaration(ConstructorDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitConstructorBody(ConstructorBodyContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitExplicitConstructorInvocation(ExplicitConstructorInvocationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitInterfaceMemberDeclaration(InterfaceMemberDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitInterfaceMethodDeclaration(InterfaceMethodDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitConstantDeclaration(ConstantDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitQualifiedIdentifiers(QualifiedIdentifiersContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitElseClause(ElseClauseContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitFinallyClause(FinallyClauseContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public String visitLabeledStatement(LabeledStatementContext ctx) {
		return visitChildren(ctx);
	}
}

// Generated from Java1_4Parser.g4 by ANTLR 4.13.2

	package org.lsmr.cfg;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Java1_4Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Java1_4ParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(Java1_4Parser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedIdentifier(Java1_4Parser.QualifiedIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(Java1_4Parser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(Java1_4Parser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#basicType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicType(Java1_4Parser.BasicTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#dims}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDims(Java1_4Parser.DimsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(Java1_4Parser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(Java1_4Parser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#expression1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression1(Java1_4Parser.Expression1Context ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#expression1Rest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression1Rest(Java1_4Parser.Expression1RestContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#expression2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression2(Java1_4Parser.Expression2Context ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#expression2Rest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression2Rest(Java1_4Parser.Expression2RestContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#infixOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixOp(Java1_4Parser.InfixOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#expression3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression3(Java1_4Parser.Expression3Context ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#prefixOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixOp(Java1_4Parser.PrefixOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#postfixOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixOp(Java1_4Parser.PostfixOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(Java1_4Parser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#identifierSuffix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierSuffix(Java1_4Parser.IdentifierSuffixContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector(Java1_4Parser.SelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#superSuffix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperSuffix(Java1_4Parser.SuperSuffixContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(Java1_4Parser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreator(Java1_4Parser.CreatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#innerCreator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInnerCreator(Java1_4Parser.InnerCreatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreatorRest(Java1_4Parser.ArrayCreatorRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#classCreatorRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassCreatorRest(Java1_4Parser.ClassCreatorRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(Java1_4Parser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#variableInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInitializer(Java1_4Parser.VariableInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(Java1_4Parser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(Java1_4Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(Java1_4Parser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVariableDeclarationStatement(Java1_4Parser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(Java1_4Parser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(Java1_4Parser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#elseClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseClause(Java1_4Parser.ElseClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(Java1_4Parser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(Java1_4Parser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#doStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoStatement(Java1_4Parser.DoStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#tryStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTryStatement(Java1_4Parser.TryStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#switchStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStatement(Java1_4Parser.SwitchStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#synchronizedStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSynchronizedStatement(Java1_4Parser.SynchronizedStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(Java1_4Parser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#throwStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrowStatement(Java1_4Parser.ThrowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(Java1_4Parser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(Java1_4Parser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#emptyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(Java1_4Parser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(Java1_4Parser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#assertStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssertStatement(Java1_4Parser.AssertStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#labeledStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabeledStatement(Java1_4Parser.LabeledStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#statementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementExpression(Java1_4Parser.StatementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(Java1_4Parser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#catches}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatches(Java1_4Parser.CatchesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#catchClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchClause(Java1_4Parser.CatchClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#finallyClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinallyClause(Java1_4Parser.FinallyClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchBlockStatementGroup(Java1_4Parser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#switchLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchLabel(Java1_4Parser.SwitchLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(Java1_4Parser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(Java1_4Parser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifier(Java1_4Parser.ModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#variableDeclarators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarators(Java1_4Parser.VariableDeclaratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#variableDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarator(Java1_4Parser.VariableDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorId(Java1_4Parser.VariableDeclaratorIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#constantDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclarator(Java1_4Parser.ConstantDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(Java1_4Parser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#importDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDeclaration(Java1_4Parser.ImportDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclaration(Java1_4Parser.TypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#classOrInterfaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassOrInterfaceDeclaration(Java1_4Parser.ClassOrInterfaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(Java1_4Parser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#superclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperclass(Java1_4Parser.SuperclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#superinterfaces}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperinterfaces(Java1_4Parser.SuperinterfacesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceDeclaration(Java1_4Parser.InterfaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#extendsInterfaces}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendsInterfaces(Java1_4Parser.ExtendsInterfacesContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#typeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeList(Java1_4Parser.TypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(Java1_4Parser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#interfaceBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceBody(Java1_4Parser.InterfaceBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBodyDeclaration(Java1_4Parser.ClassBodyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#emptyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyDeclaration(Java1_4Parser.EmptyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializer(Java1_4Parser.InitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#staticInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaticInitializer(Java1_4Parser.StaticInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#memberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberDeclaration(Java1_4Parser.MemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(Java1_4Parser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#result}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResult(Java1_4Parser.ResultContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#throws_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrows_(Java1_4Parser.Throws_Context ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#fieldDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDeclaration(Java1_4Parser.FieldDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#constructorDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDeclaration(Java1_4Parser.ConstructorDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#constructorBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorBody(Java1_4Parser.ConstructorBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#explicitConstructorInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplicitConstructorInvocation(Java1_4Parser.ExplicitConstructorInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceBodyDeclaration(Java1_4Parser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#interfaceMemberDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceMemberDeclaration(Java1_4Parser.InterfaceMemberDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#interfaceMethodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceMethodDeclaration(Java1_4Parser.InterfaceMethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#constantDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDeclaration(Java1_4Parser.ConstantDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#qualifiedIdentifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedIdentifiers(Java1_4Parser.QualifiedIdentifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#formalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameters(Java1_4Parser.FormalParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link Java1_4Parser#formalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameter(Java1_4Parser.FormalParameterContext ctx);
}
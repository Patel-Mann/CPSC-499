// Generated from ExprParser.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ExprParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ExprParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(ExprParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(ExprParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedIdentifier(ExprParser.QualifiedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedIdentifier(ExprParser.QualifiedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ExprParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ExprParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ExprParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ExprParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#conditionalExpr}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpr(ExprParser.ConditionalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#conditionalExpr}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpr(ExprParser.ConditionalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#logicalOrExpr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpr(ExprParser.LogicalOrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#logicalOrExpr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpr(ExprParser.LogicalOrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#logicalAndExpr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpr(ExprParser.LogicalAndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#logicalAndExpr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpr(ExprParser.LogicalAndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(ExprParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(ExprParser.EqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(ExprParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(ExprParser.RelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(ExprParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(ExprParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr(ExprParser.MultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr(ExprParser.MultiplicativeExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(ExprParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#unaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(ExprParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpr(ExprParser.PostfixExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpr(ExprParser.PostfixExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(ExprParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(ExprParser.PrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(ExprParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(ExprParser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ExprParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ExprParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpression(ExprParser.StatementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpression(ExprParser.StatementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(ExprParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(ExprParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#identifierSuffix}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierSuffix(ExprParser.IdentifierSuffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#identifierSuffix}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierSuffix(ExprParser.IdentifierSuffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#postfixOp}.
	 * @param ctx the parse tree
	 */
	void enterPostfixOp(ExprParser.PostfixOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#postfixOp}.
	 * @param ctx the parse tree
	 */
	void exitPostfixOp(ExprParser.PostfixOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(ExprParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(ExprParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#argumentsOpt}.
	 * @param ctx the parse tree
	 */
	void enterArgumentsOpt(ExprParser.ArgumentsOptContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#argumentsOpt}.
	 * @param ctx the parse tree
	 */
	void exitArgumentsOpt(ExprParser.ArgumentsOptContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(ExprParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(ExprParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#bracketsOpt}.
	 * @param ctx the parse tree
	 */
	void enterBracketsOpt(ExprParser.BracketsOptContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#bracketsOpt}.
	 * @param ctx the parse tree
	 */
	void exitBracketsOpt(ExprParser.BracketsOptContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterCreator(ExprParser.CreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitCreator(ExprParser.CreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void enterInnerCreator(ExprParser.InnerCreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#innerCreator}.
	 * @param ctx the parse tree
	 */
	void exitInnerCreator(ExprParser.InnerCreatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreatorRest(ExprParser.ArrayCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#arrayCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreatorRest(ExprParser.ArrayCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void enterClassCreatorRest(ExprParser.ClassCreatorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#classCreatorRest}.
	 * @param ctx the parse tree
	 */
	void exitClassCreatorRest(ExprParser.ClassCreatorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(ExprParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(ExprParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(ExprParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(ExprParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(ExprParser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(ExprParser.ParExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ExprParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ExprParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#blockStatements}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatements(ExprParser.BlockStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#blockStatements}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatements(ExprParser.BlockStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(ExprParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(ExprParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableDeclarationStatement(ExprParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#localVariableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableDeclarationStatement(ExprParser.LocalVariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ExprParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ExprParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#statementIncompleteIf}.
	 * @param ctx the parse tree
	 */
	void enterStatementIncompleteIf(ExprParser.StatementIncompleteIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#statementIncompleteIf}.
	 * @param ctx the parse tree
	 */
	void exitStatementIncompleteIf(ExprParser.StatementIncompleteIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#completeIf}.
	 * @param ctx the parse tree
	 */
	void enterCompleteIf(ExprParser.CompleteIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#completeIf}.
	 * @param ctx the parse tree
	 */
	void exitCompleteIf(ExprParser.CompleteIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#catches}.
	 * @param ctx the parse tree
	 */
	void enterCatches(ExprParser.CatchesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#catches}.
	 * @param ctx the parse tree
	 */
	void exitCatches(ExprParser.CatchesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void enterCatchClause(ExprParser.CatchClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void exitCatchClause(ExprParser.CatchClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(ExprParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(ExprParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#switchBlockStatementGroups}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroups(ExprParser.SwitchBlockStatementGroupsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#switchBlockStatementGroups}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroups(ExprParser.SwitchBlockStatementGroupsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroup(ExprParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroup(ExprParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabel(ExprParser.SwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabel(ExprParser.SwitchLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#moreStatementExpressions}.
	 * @param ctx the parse tree
	 */
	void enterMoreStatementExpressions(ExprParser.MoreStatementExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#moreStatementExpressions}.
	 * @param ctx the parse tree
	 */
	void exitMoreStatementExpressions(ExprParser.MoreStatementExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(ExprParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(ExprParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(ExprParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(ExprParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#modifiersOpt}.
	 * @param ctx the parse tree
	 */
	void enterModifiersOpt(ExprParser.ModifiersOptContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#modifiersOpt}.
	 * @param ctx the parse tree
	 */
	void exitModifiersOpt(ExprParser.ModifiersOptContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(ExprParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(ExprParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarators(ExprParser.VariableDeclaratorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#variableDeclarators}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarators(ExprParser.VariableDeclaratorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#variableDeclaratorsRest}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorsRest(ExprParser.VariableDeclaratorsRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#variableDeclaratorsRest}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorsRest(ExprParser.VariableDeclaratorsRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#constantDeclaratorsRest}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclaratorsRest(ExprParser.ConstantDeclaratorsRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#constantDeclaratorsRest}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclaratorsRest(ExprParser.ConstantDeclaratorsRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarator(ExprParser.VariableDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#variableDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarator(ExprParser.VariableDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclarator(ExprParser.ConstantDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#constantDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclarator(ExprParser.ConstantDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#variableDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorRest(ExprParser.VariableDeclaratorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#variableDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorRest(ExprParser.VariableDeclaratorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#constantDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void enterConstantDeclaratorRest(ExprParser.ConstantDeclaratorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#constantDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void exitConstantDeclaratorRest(ExprParser.ConstantDeclaratorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorId(ExprParser.VariableDeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorId(ExprParser.VariableDeclaratorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(ExprParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(ExprParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterImportDeclaration(ExprParser.ImportDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#importDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitImportDeclaration(ExprParser.ImportDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(ExprParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(ExprParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#classOrInterfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassOrInterfaceDeclaration(ExprParser.ClassOrInterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#classOrInterfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassOrInterfaceDeclaration(ExprParser.ClassOrInterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(ExprParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(ExprParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDeclaration(ExprParser.InterfaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#interfaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDeclaration(ExprParser.InterfaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#typeList}.
	 * @param ctx the parse tree
	 */
	void enterTypeList(ExprParser.TypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#typeList}.
	 * @param ctx the parse tree
	 */
	void exitTypeList(ExprParser.TypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(ExprParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(ExprParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBody(ExprParser.InterfaceBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#interfaceBody}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBody(ExprParser.InterfaceBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassBodyDeclaration(ExprParser.ClassBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#classBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassBodyDeclaration(ExprParser.ClassBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#memberDecl}.
	 * @param ctx the parse tree
	 */
	void enterMemberDecl(ExprParser.MemberDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#memberDecl}.
	 * @param ctx the parse tree
	 */
	void exitMemberDecl(ExprParser.MemberDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#methodOrFieldDecl}.
	 * @param ctx the parse tree
	 */
	void enterMethodOrFieldDecl(ExprParser.MethodOrFieldDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#methodOrFieldDecl}.
	 * @param ctx the parse tree
	 */
	void exitMethodOrFieldDecl(ExprParser.MethodOrFieldDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#methodOrFieldRest}.
	 * @param ctx the parse tree
	 */
	void enterMethodOrFieldRest(ExprParser.MethodOrFieldRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#methodOrFieldRest}.
	 * @param ctx the parse tree
	 */
	void exitMethodOrFieldRest(ExprParser.MethodOrFieldRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceBodyDeclaration(ExprParser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#interfaceBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceBodyDeclaration(ExprParser.InterfaceBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#interfaceMemberDecl}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMemberDecl(ExprParser.InterfaceMemberDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#interfaceMemberDecl}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMemberDecl(ExprParser.InterfaceMemberDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#interfaceMethodOrFieldDecl}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodOrFieldDecl(ExprParser.InterfaceMethodOrFieldDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#interfaceMethodOrFieldDecl}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodOrFieldDecl(ExprParser.InterfaceMethodOrFieldDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#interfaceMethodOrFieldRest}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodOrFieldRest(ExprParser.InterfaceMethodOrFieldRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#interfaceMethodOrFieldRest}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodOrFieldRest(ExprParser.InterfaceMethodOrFieldRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#methodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaratorRest(ExprParser.MethodDeclaratorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#methodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaratorRest(ExprParser.MethodDeclaratorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#voidMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void enterVoidMethodDeclaratorRest(ExprParser.VoidMethodDeclaratorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#voidMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void exitVoidMethodDeclaratorRest(ExprParser.VoidMethodDeclaratorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#interfaceMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceMethodDeclaratorRest(ExprParser.InterfaceMethodDeclaratorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#interfaceMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceMethodDeclaratorRest(ExprParser.InterfaceMethodDeclaratorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#voidInterfaceMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void enterVoidInterfaceMethodDeclaratorRest(ExprParser.VoidInterfaceMethodDeclaratorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#voidInterfaceMethodDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void exitVoidInterfaceMethodDeclaratorRest(ExprParser.VoidInterfaceMethodDeclaratorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#constructorDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDeclaratorRest(ExprParser.ConstructorDeclaratorRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#constructorDeclaratorRest}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDeclaratorRest(ExprParser.ConstructorDeclaratorRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#qualifiedIdentifierList}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedIdentifierList(ExprParser.QualifiedIdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#qualifiedIdentifierList}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedIdentifierList(ExprParser.QualifiedIdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(ExprParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(ExprParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(ExprParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(ExprParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(ExprParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(ExprParser.MethodBodyContext ctx);
}
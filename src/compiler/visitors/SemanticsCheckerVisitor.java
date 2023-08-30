package compiler.visitors;

import compiler.errors.SemanticsErrorsUtils;
import compiler.semantics.SymbolTableManager;
import compiler.semantics.memory.*;
import compiler.syntax.models.classes.ClassDeclaration;
import compiler.syntax.models.expression.Expression;
import compiler.syntax.models.expression.conditional.BooleanExpression;
import compiler.syntax.models.expression.conditional.ComparisonExpression;
import compiler.syntax.models.expression.conditional.ConditionalExpression;
import compiler.syntax.models.expression.conditional.LogicalExpression;
import compiler.syntax.models.expression.operation.IdentifierOperationExpression;
import compiler.syntax.models.expression.operation.NumberOperationExpression;
import compiler.syntax.models.expression.operation.OperationExpression;
import compiler.syntax.models.expression.operation.ParamCall;
import compiler.syntax.models.expression.operation.functioncall.FunctionCall;
import compiler.syntax.models.expression.operation.literal.Literal;
import compiler.syntax.models.function.FunctionDeclaration;
import compiler.syntax.models.function.argument.Arguments;
import compiler.syntax.models.function.argument.NamedArgument;
import compiler.syntax.models.function.buildfunction.BuildFunctionDeclaration;
import compiler.syntax.models.function.parameter.Parameter;
import compiler.syntax.models.function.parameter.Parameters;
import compiler.syntax.models.program.Program;
import compiler.syntax.models.statement.Statement;
import compiler.syntax.models.statement.Statements;
import compiler.syntax.models.statement.nonsemicolon.IfStatement;
import compiler.syntax.models.statement.nonsemicolon.NonSemiColonStatement;
import compiler.syntax.models.statement.nonsemicolon.WhileStatement;
import compiler.syntax.models.statement.semicolon.*;
import compiler.syntax.models.variable.InitVariable;
import compiler.syntax.models.variable.MutateVariable;
import compiler.syntax.models.widget.*;
import compiler.syntax.models.widget.anonymousfunction.AnonymousFunctionDeclaration;
import compiler.syntax.models.widget.attribute.*;
import compiler.types.LanguageType;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SemanticsCheckerVisitor {

    private final SymbolTableManager symbolTableManager = new SymbolTableManager();

    private final ArrayList<String> errors = new ArrayList<>();

    public List<String> getErrors() {
        return errors;
    }

    public void visitProgram(Program program) {
        symbolTableManager.pushScope("Program");
        ArrayList<String> classes = new ArrayList<>();
        for (ClassDeclaration classDeclaration : program.getClassDeclarations()) {
            classes.add(classDeclaration.getName());
            visitClass(classDeclaration);
        }
        if (!classes.contains("Home")) { // Home must be written with a capital H.
            errors.add(SemanticsErrorsUtils.homeClassNotDefinedMessage(0));
        }
        symbolTableManager.removeCurrentScopeEntriesAndPopScope();
    }

    public void visitClass(ClassDeclaration classDeclaration) {
        if (symbolTableManager.entryExists(classDeclaration.getName())) {
            String message = SemanticsErrorsUtils.classAlreadyDefinedMessage(
                    classDeclaration.getName(),
                    classDeclaration.getLineNumber()
            );
            errors.add(message);
            return;
        }
        symbolTableManager.addEntry(
                classDeclaration.getName(),
                new ClassMemoryEntry(
                        classDeclaration.getName(),
                        classDeclaration.getLineNumber(),
                        symbolTableManager.getCurrentScope()
                )
        );

        symbolTableManager.pushScope(classDeclaration.getName());

        for (InitVariable var : classDeclaration.getDeclarationBlock().getInitVariables()) {
            visitInitVariable(var);
        }

        for (FunctionDeclaration func : classDeclaration.getDeclarationBlock().getFunctionDeclarations()) {
            visitFunction(func);
        }

        visitBuildFunction(classDeclaration.getDeclarationBlock().getBuildFunctionDeclaration());

        symbolTableManager.removeCurrentScopeEntriesAndPopScope();
    }

    public void visitFunction(FunctionDeclaration functionDeclaration) {
        if (symbolTableManager.entryExists(functionDeclaration.getSignature().getName())) {
            String message = SemanticsErrorsUtils.functionAlreadyDefinedMessage(
                    functionDeclaration.getSignature().getName(),
                    functionDeclaration.getLineNumber()
            );
            errors.add(message);
            return;
        }
        symbolTableManager.addEntry(
                functionDeclaration.getSignature().getName(),
                new FunctionMemoryEntry(
                        functionDeclaration.getSignature().getName(),
                        functionDeclaration.getLineNumber(),
                        symbolTableManager.getCurrentScope(),
                        functionDeclaration.getSignature().getType().getType(),
                        functionDeclaration
                                .getSignature()
                                .getParameters()
                                .getParameters()
                                .stream()
                                .map(parameter ->
                                        new ParameterMemoryEntry(
                                                parameter.getName(),
                                                parameter.getType().getType()
                                        )
                                ).collect(Collectors.toList())
                )
        );
        symbolTableManager.pushScope(functionDeclaration.getSignature().getName());

        visitParameters(functionDeclaration.getSignature().getParameters());
        visitStatements(functionDeclaration.getStatements().getStatements());

        Expression returnExpression = functionDeclaration.getStatements().getReturnExpression();
        if (returnExpression == null) {
            if (functionDeclaration.getSignature().getType().getType() != LanguageType.VOID) {
                String message = SemanticsErrorsUtils.returnStatementNotFound(
                        functionDeclaration.getSignature().getName(),
                        functionDeclaration.getLineNumber()
                );
                errors.add(message);
            }
        } else {
            LanguageType type = visitExpression(returnExpression);
            if (type != functionDeclaration.getSignature().getType().getType()) {
                String message = SemanticsErrorsUtils.functionReturnTypeAndReturnExpDoNotMatch(
                        functionDeclaration.getSignature().getName(),
                        functionDeclaration.getLineNumber()
                );
                errors.add(message);
            }
        }
        symbolTableManager.removeCurrentScopeEntriesAndPopScope();
    }

    public void visitBuildFunction(BuildFunctionDeclaration buildFunctionDeclaration) {
        symbolTableManager.addEntry(
                buildFunctionDeclaration.getSignature().getName(),
                new FunctionMemoryEntry(
                        buildFunctionDeclaration.getSignature().getName(),
                        buildFunctionDeclaration.getLineNumber(),
                        symbolTableManager.getCurrentScope(),
                        LanguageType.WIDGET,
                        new ArrayList<>() // Build function doesn't have parameters.
                )
        );
        symbolTableManager.pushScope(buildFunctionDeclaration.getSignature().getName());

        visitStatements(buildFunctionDeclaration.getStatements().getStatements());
        visitWidget(buildFunctionDeclaration.getStatements().getReturnWidget());

        symbolTableManager.removeCurrentScopeEntriesAndPopScope();
    }

    public void visitParameters(Parameters parameters) {
        for (Parameter parameter : parameters.getParameters()) {
            visitParameter(parameter);
        }
    }

    public void visitParameter(Parameter parameter) {
        if (symbolTableManager.entryExists(parameter.getName())) {
            String message = SemanticsErrorsUtils.variableAlreadyDefinedMessage(
                    parameter.getName(),
                    parameter.getLineNumber()
            );
            errors.add(message);
            return;
        }
        symbolTableManager.addEntry(
                parameter.getName(),
                new VariableMemoryEntry(
                        parameter.getName(),
                        parameter.getLineNumber(),
                        symbolTableManager.getCurrentScope(),
                        parameter.getType().getType()
                )
        );
    }

    public void visitStatements(Statements statements) {
        for (Statement statement : statements.getStatements()) {
            visitStatement(statement);
        }
    }

    public void visitStatement(Statement statement) {
        if (statement instanceof SemiColonStatement) {
            visitSemiColonStatement((SemiColonStatement) statement);
        } else if (statement instanceof NonSemiColonStatement) {
            visitNonSemiColonStatement((NonSemiColonStatement) statement);
        }
    }

    public void visitSemiColonStatement(SemiColonStatement semiColonStatement) {
        if (semiColonStatement instanceof InitVariable) {
            visitInitVariable((InitVariable) semiColonStatement);
        } else if (semiColonStatement instanceof MutateVariable) {
            visitMutateVariable((MutateVariable) semiColonStatement);
        } else if (semiColonStatement instanceof PrintStatement) {
            visitPrintStatement((PrintStatement) semiColonStatement);
        } else if (semiColonStatement instanceof PushStatement) {
            visitPushStatement((PushStatement) semiColonStatement);
        } else if (semiColonStatement instanceof PopStatement) {
            visitPopStatement((PopStatement) semiColonStatement);
        } else if (semiColonStatement instanceof StateStatement) {
            visitStateStatement((StateStatement) semiColonStatement);
        }
    }

    public void visitNonSemiColonStatement(NonSemiColonStatement nonSemiColonStatement) {
        if (nonSemiColonStatement instanceof IfStatement) {
            visitIfStatement((IfStatement) nonSemiColonStatement);
        } else if (nonSemiColonStatement instanceof WhileStatement) {
            visitWhileStatement((WhileStatement) nonSemiColonStatement);
        }
    }

    public void visitIfStatement(IfStatement ifStatement) {
        visitExpression(ifStatement.getCondition());
        visitStatements(ifStatement.getStatements());
        if (ifStatement.getElseStatements() != null) {
            visitStatements(ifStatement.getElseStatements());
        }
    }

    public void visitWhileStatement(WhileStatement whileStatement) {
        visitExpression(whileStatement.getCondition());
        visitStatements(whileStatement.getStatements());
    }

    public void visitPopStatement(PopStatement popStatement) {}

    public void visitPrintStatement(PrintStatement printStatement) {
        visitExpression(printStatement.getExpression());
    }

    public void visitPushStatement(PushStatement pushStatement) {
        String classIdentifier = pushStatement.getIdentifier();
        if (!symbolTableManager.entryExists(classIdentifier)) {
            String message = SemanticsErrorsUtils.classIsNotDefinedMessage(
                    pushStatement.getIdentifier(),
                    pushStatement.getLineNumber()
            );
            errors.add(message);
        } else {
            MemoryEntry classMemoryEntry = symbolTableManager.getEntry(classIdentifier);
            if (!(classMemoryEntry instanceof ClassMemoryEntry)) {
                String message = SemanticsErrorsUtils.identifierIsNotClass(
                        pushStatement.getIdentifier(),
                        pushStatement.getLineNumber()
                );
                errors.add(message);
            }
        }
        visitArguments(pushStatement.getArguments());
    }

    public void visitStateStatement(StateStatement stateStatement) {}

    public LanguageType visitExpression(Expression expression) {
        if (expression instanceof ConditionalExpression) {
            return visitConditionalExpression((ConditionalExpression) expression);
        }
        if (expression instanceof OperationExpression) {
            return visitOperationalExpression((OperationExpression) expression);
        }
        return LanguageType.NONE;
    }

    public LanguageType visitConditionalExpression(ConditionalExpression conditionalExpression) {
        if (conditionalExpression instanceof ComparisonExpression) {
            return visitComparisonExpression((ComparisonExpression) conditionalExpression);
        } else if (conditionalExpression instanceof BooleanExpression) {
            return visitBooleanExpression((BooleanExpression) conditionalExpression);
        } else if (conditionalExpression instanceof LogicalExpression) {
            return visitLogicalExpression((LogicalExpression) conditionalExpression);
        }
        return LanguageType.NONE;
    }

    public LanguageType visitComparisonExpression(ComparisonExpression comparisonExpression) {
        LanguageType leftType = visitOperationalExpression(comparisonExpression.getLeft());
        LanguageType rightType = visitOperationalExpression(comparisonExpression.getRight());
        if (leftType != rightType) {
            String message = SemanticsErrorsUtils.canNotOperateOnDifferentTypes(comparisonExpression.getLineNumber());
            errors.add(message);
            return LanguageType.NONE;
        }
        return LanguageType.BOOLEAN;
    }

    public LanguageType visitBooleanExpression(BooleanExpression booleanExpression) {
        return LanguageType.BOOLEAN;
    }

    public LanguageType visitLogicalExpression(LogicalExpression logicalExpression) {
        visitConditionalExpression(logicalExpression.getLeft());
        visitConditionalExpression(logicalExpression.getRight());
        return LanguageType.BOOLEAN;
    }

    public LanguageType visitOperationalExpression(OperationExpression operationExpression) {
        if (operationExpression instanceof IdentifierOperationExpression) {
            return visitIdentifierOperationExpression((IdentifierOperationExpression) operationExpression);
        }
        if (operationExpression instanceof NumberOperationExpression) {
            return visitNumberOperationExpression((NumberOperationExpression) operationExpression);
        }
        if (operationExpression instanceof Literal) {
            return visitLiteral((Literal) operationExpression);
        }
        if (operationExpression instanceof FunctionCall) {
            return visitFunctionCall((FunctionCall) operationExpression);
        }
        if (operationExpression instanceof ParamCall) {
            return visitParamCall((ParamCall) operationExpression);
        }
        return LanguageType.NONE;
    }

    public LanguageType visitIdentifierOperationExpression(IdentifierOperationExpression identifierOperationExpression) {
        if (!symbolTableManager.entryExists(identifierOperationExpression.getIdentifier())) {
            String message = SemanticsErrorsUtils.variableIsNotDefinedMessage(
                    identifierOperationExpression.getIdentifier(),
                    identifierOperationExpression.getLineNumber()
            );
            errors.add(message);
            return LanguageType.NONE;
        } else {
            MemoryEntry memoryEntry = symbolTableManager.getEntry(identifierOperationExpression.getIdentifier());
            if (!(memoryEntry instanceof VariableMemoryEntry)) {
                String message = SemanticsErrorsUtils.identifierIsNotVariable(
                        identifierOperationExpression.getIdentifier(),
                        identifierOperationExpression.getLineNumber()
                );
                errors.add(message);
                return LanguageType.NONE;
            } else {
                VariableMemoryEntry variableMemoryEntry = (VariableMemoryEntry) memoryEntry;
                return variableMemoryEntry.getType();
            }
        }
    }

    public LanguageType visitNumberOperationExpression(NumberOperationExpression numberOperationExpression) {
        LanguageType leftType = visitOperationalExpression(numberOperationExpression.getLeft());
        LanguageType rightType = visitOperationalExpression(numberOperationExpression.getRight());
        if (leftType != rightType) {
            String message = SemanticsErrorsUtils.canNotOperateOnDifferentTypes(numberOperationExpression.getLineNumber());
            errors.add(message);
            return LanguageType.NONE;
        }
        return leftType;
    }

    public void visitInitVariable(InitVariable initVariable) {
        if (symbolTableManager.entryExists(initVariable.getIdentifier())) {
            String message = SemanticsErrorsUtils.variableAlreadyDefinedMessage(
                    initVariable.getIdentifier(),
                    initVariable.getLineNumber()
            );
            errors.add(message);
            return;
        }
        symbolTableManager.addEntry(
                initVariable.getIdentifier(),
                new VariableMemoryEntry(
                        initVariable.getIdentifier(),
                        initVariable.getLineNumber(),
                        symbolTableManager.getCurrentScope(),
                        initVariable.getType().getType()
                )
        );
        LanguageType type = visitExpression(initVariable.getExpression());
        if (initVariable.getType().getType() != type) {
            String message = SemanticsErrorsUtils.typeMismatch(initVariable.getLineNumber());
            errors.add(message);
        }
    }

    public void visitMutateVariable(MutateVariable mutateVariable) {
        if (!symbolTableManager.entryExists(mutateVariable.getIdentifier())) {
            String message = SemanticsErrorsUtils.variableIsNotDefinedMessage(
                    mutateVariable.getIdentifier(),
                    mutateVariable.getLineNumber()
            );
            errors.add(message);
            return;
        }
        MemoryEntry memoryEntry = symbolTableManager.getEntry(mutateVariable.getIdentifier());
        if (!(memoryEntry instanceof VariableMemoryEntry)) {
            String message = SemanticsErrorsUtils.identifierIsNotVariable(
                    mutateVariable.getIdentifier(),
                    mutateVariable.getLineNumber()
            );
            errors.add(message);
            return;
        }
        VariableMemoryEntry variableMemoryEntry = (VariableMemoryEntry) memoryEntry;
        LanguageType type = visitExpression(mutateVariable.getExpression());
        if (type != variableMemoryEntry.getType()) {
            String message = SemanticsErrorsUtils.typeMismatch(mutateVariable.getLineNumber());
            errors.add(message);
        }
    }

    public LanguageType visitParamCall(ParamCall paramCall) {
        LanguageType type = visitExpression(paramCall.getKey());
        if (type != LanguageType.STRING) {
            String message = SemanticsErrorsUtils.typeMismatch(paramCall.getLineNumber());
            errors.add(message);
        }
        return LanguageType.STRING;
    }

    public LanguageType visitLiteral(Literal literal) {
        return literal.getType();
    }

    // TODO: Refactor Pairs.
    public LanguageType visitFunctionCall(FunctionCall functionCall) {
        if (!symbolTableManager.entryExists(functionCall.getName())) {
            String message = SemanticsErrorsUtils.functionIsNotDefinedMessage(
                    functionCall.getName(),
                    functionCall.getLineNumber()
            );
            errors.add(message);
            return LanguageType.NONE;
        } else {
            MemoryEntry memoryEntry = symbolTableManager.getEntry(functionCall.getName());
            if (!(memoryEntry instanceof FunctionMemoryEntry)) {
                String message = SemanticsErrorsUtils.identifierIsNotFunction(
                        functionCall.getName(),
                        functionCall.getLineNumber()
                );
                errors.add(message);
                return LanguageType.NONE;
            }
            FunctionMemoryEntry functionMemoryEntry = (FunctionMemoryEntry) memoryEntry;
            List<Pair<String, LanguageType>> argumentsKeysAndTypes = visitArguments(functionCall.getArguments());
            for (ParameterMemoryEntry parameter : functionMemoryEntry.getParameters()) {
                boolean match = argumentsKeysAndTypes.stream()
                        .anyMatch(
                                (argumentKeyAndType) -> Objects.equals(parameter.getIdentifier(), argumentKeyAndType.a)
                                        && parameter.getType() == argumentKeyAndType.b
                        );
                if (!match) {
                    String message = SemanticsErrorsUtils.argumentsDoNotMatchParametersMessage(
                            functionCall.getName(),
                            functionCall.getLineNumber()
                    );
                    errors.add(message);
                    return LanguageType.NONE;
                }
            }
            return functionMemoryEntry.getReturnType();
        }
    }

    // TODO: Refactor Pairs.
    public List<Pair<String, LanguageType>> visitArguments(Arguments arguments) {
        return arguments.getArgumentsList().stream().map(this::visitNamedArgument).collect(Collectors.toList());
    }

    // TODO: Refactor Pairs.
    public Pair<String, LanguageType> visitNamedArgument(NamedArgument namedArgument) {
        return new Pair<>(namedArgument.getKey(), visitExpression(namedArgument.getExpression()));
    }

    public void visitAnonymousFunction(AnonymousFunctionDeclaration anonymousFunctionDeclaration) {
        visitStatements(anonymousFunctionDeclaration.getAnonymousFunctionBlock().getStatements());
    }

    public void visitWidget(Widget widget) {
        visitWidth(widget.getWidth());
        visitHeight(widget.getHeight());
        visitPadding(widget.getPadding());
        visitMargin(widget.getMargin());

        if (widget instanceof Row) visitRow((Row) widget);
        else if (widget instanceof Column) visitColumn((Column) widget);
        else if (widget instanceof Center) visitCenter((Center) widget);
        else if (widget instanceof Text) visitText((Text) widget);
        else if (widget instanceof Container) visitContainer((Container) widget);
        else if (widget instanceof Image) visitImage((Image) widget);
        else if (widget instanceof Button) visitButton((Button) widget);
        else if (widget instanceof Scaffold) visitScaffold((Scaffold) widget);
        else if (widget instanceof SizedBox) visitSizedBox((SizedBox) widget);
        else if (widget instanceof AppBar) visitAppBar((AppBar) widget);
    }

    public void visitRow(Row row) {
        for (Widget widget : row.getChildren().getWidgets()) {
            visitWidget(widget);
        }
    }

    public void visitColumn(Column column) {
        for (Widget widget : column.getChildren().getWidgets()) {
            visitWidget(widget);
        }
    }

    public void visitCenter(Center center) {
        visitWidget(center.getChild().getWidget());
    }

    public void visitText(Text text) {
        visitTextAttr(text.getTextAttr());
        visitFontSize(text.getFontSize());
    }

    public void visitContainer(Container container) {
        visitWidget(container.getChild().getWidget());
    }

    public void visitImage(Image image) {
        visitLink(image.getLink());
    }

    public void visitButton(Button button) {
        visitTextAttr(button.getTextAttr());
        visitEnabled(button.getEnabled());
        visitOnPress(button.getOnPress());
    }

    public void visitScaffold(Scaffold scaffold) {
        visitAppBar(scaffold.getAppBarAttr().getAppBar());
        visitWidget(scaffold.getBody().getWidget());
    }

    public void visitSizedBox(SizedBox sizedBox) {}

    public void visitAppBar(AppBar appBar) {
        visitWidget(appBar.getLeading().getWidget());
        visitWidget(appBar.getCenterAttr().getWidget());
        visitWidget(appBar.getTrailing().getWidget());
    }

    public void visitWidth(Width width) {
        LanguageType type = visitExpression(width.getExpression());
        if (type != LanguageType.INT) {
            String message = SemanticsErrorsUtils.typeMismatch(width.getLineNumber());
            errors.add(message);
        }
    }

    public void visitHeight(Height height) {
        LanguageType type = visitExpression(height.getExpression());
        if (type != LanguageType.INT) {
            String message = SemanticsErrorsUtils.typeMismatch(height.getLineNumber());
            errors.add(message);
        }
    }

    public void visitPadding(Padding padding) {
        LanguageType type = visitExpression(padding.getExpression());
        if (type != LanguageType.INT) {
            String message = SemanticsErrorsUtils.typeMismatch(padding.getLineNumber());
            errors.add(message);
        }
    }

    public void visitMargin(Margin margin) {
        LanguageType type = visitExpression(margin.getExpression());
        if (type != LanguageType.INT) {
            String message = SemanticsErrorsUtils.typeMismatch(margin.getLineNumber());
            errors.add(message);
        }
    }

    public void visitTextAttr(TextAttr textAttr) {
        LanguageType type = visitExpression(textAttr.getExpression());
        if (type != LanguageType.STRING && type != LanguageType.INT && type != LanguageType.BOOLEAN) {
            String message = SemanticsErrorsUtils.typeMismatch(textAttr.getLineNumber());
            errors.add(message);
        }
    }

    public void visitFontSize(FontSize fontSize) {
        LanguageType type = visitExpression(fontSize.getExpression());
        if (type != LanguageType.INT) {
            String message = SemanticsErrorsUtils.typeMismatch(fontSize.getLineNumber());
            errors.add(message);
        }
    }

    public void visitLink(Link link) {
        LanguageType type = visitExpression(link.getExpression());
        if (type != LanguageType.STRING) {
            String message = SemanticsErrorsUtils.typeMismatch(link.getLineNumber());
            errors.add(message);
        }
    }

    public void visitEnabled(Enabled enabled) {
        LanguageType type = visitExpression(enabled.getExpression());
        if (type != LanguageType.BOOLEAN) {
            String message = SemanticsErrorsUtils.typeMismatch(enabled.getLineNumber());
            errors.add(message);
        }
    }

    public void visitOnPress(OnPress onPress) {
        visitAnonymousFunction(onPress.getAnonymousFunctionDeclaration());
    }
}

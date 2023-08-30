package compiler.visitors;

import compiler.syntax.models.classes.ClassDeclaration;
import compiler.syntax.models.classes.ClassDeclarationBlock;
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
import compiler.syntax.models.function.FunctionSignature;
import compiler.syntax.models.function.FunctionStatements;
import compiler.syntax.models.function.argument.Arguments;
import compiler.syntax.models.function.argument.NamedArgument;
import compiler.syntax.models.function.buildfunction.BuildFunctionDeclaration;
import compiler.syntax.models.function.buildfunction.BuildFunctionSignature;
import compiler.syntax.models.function.buildfunction.BuildFunctionStatements;
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
import compiler.syntax.models.variable.type.Type;
import compiler.syntax.models.widget.*;
import compiler.syntax.models.widget.anonymousfunction.AnonymousFunctionBlock;
import compiler.syntax.models.widget.anonymousfunction.AnonymousFunctionDeclaration;
import compiler.syntax.models.widget.attribute.*;
import compiler.syntax.models.widget.attribute.Color;
import compiler.syntax.models.widget.enums.*;
import compiler.types.LanguageType;
import gen.XFlutterLexer;
import gen.XFlutterParser;
import gen.XFlutterParserBaseVisitor;

import java.util.ArrayList;

public class ASTBuilderVisitor extends XFlutterParserBaseVisitor<Object> {

    @Override
    public Program visitProgram(XFlutterParser.ProgramContext ctx) {
        ArrayList<ClassDeclaration> classDeclarations = new ArrayList<>();
        for (XFlutterParser.ClassDeclarationContext classDeclarationContext : ctx.classDeclaration()) {
            classDeclarations.add((ClassDeclaration) visit(classDeclarationContext));
        }
        return new Program(classDeclarations);
    }

    @Override
    public Statement visitStatement(XFlutterParser.StatementContext ctx) {
        return (Statement) super.visitStatement(ctx);
    }

    @Override
    public NonSemiColonStatement visitNonSemiColonStatement(XFlutterParser.NonSemiColonStatementContext ctx) {
        return (NonSemiColonStatement) super.visitNonSemiColonStatement(ctx);
    }

    @Override
    public SemiColonStatement visitSemiColonStatement(XFlutterParser.SemiColonStatementContext ctx) {
        return (SemiColonStatement) super.visitSemiColonStatement(ctx);
    }

    @Override
    public Type visitType(XFlutterParser.TypeContext ctx) {
        switch (ctx.TYPE().getText()) {
            case "int":
                return new Type(LanguageType.INT);
            case "string":
                return new Type(LanguageType.STRING);
            case "boolean":
                return new Type(LanguageType.BOOLEAN);
            case "void":
                return new Type(LanguageType.VOID);
            case "Widget":
                return new Type(LanguageType.WIDGET);
            default:
                return new Type(LanguageType.NONE);
        }
    }

    @Override
    public InitVariable visitInitVariable(XFlutterParser.InitVariableContext ctx) {
        return new InitVariable(
                ctx.start.getLine(),
                (Type) visit(ctx.type()),
                ctx.IDENTIFIER().getText(),
                (Expression) visit(ctx.expression())
        );
    }

    @Override
    public MutateVariable visitMutateVariable(XFlutterParser.MutateVariableContext ctx) {
        return new MutateVariable(
                ctx.start.getLine(),
                ctx.IDENTIFIER().getText(),
                (Expression) visit(ctx.expression())
        );
    }

    @Override
    public BuildFunctionSignature visitBuildFunctionSignature(XFlutterParser.BuildFunctionSignatureContext ctx) {
        return new BuildFunctionSignature(
                ctx.BUILD().getText()
        );
    }

    @Override
    public BuildFunctionStatements visitBuildFunctionBlock(XFlutterParser.BuildFunctionBlockContext ctx) {
        ArrayList<Statement> statements = new ArrayList<>();
        for (XFlutterParser.StatementContext item : ctx.statement()) {
            statements.add((Statement) visit(item));
        }
        return new BuildFunctionStatements(
                new Statements(statements),
                (Widget) visit(ctx.widget())
        );
    }

    @Override
    public FunctionSignature visitFunctionSignature(XFlutterParser.FunctionSignatureContext ctx) {
        return new FunctionSignature(
                (Type) visit(ctx.type()),
                ctx.IDENTIFIER().getText(),
                (Parameters) visit(ctx.parameters())
        );
    }

    @Override
    public FunctionStatements visitFunctionBlock(XFlutterParser.FunctionBlockContext ctx) {
        ArrayList<Statement> statements = new ArrayList<>();
        for (XFlutterParser.StatementContext item : ctx.statement()) {
            statements.add((Statement) visit(item));
        }
        Expression returnExpression = null;
        if (ctx.expression() != null) {
            returnExpression = (Expression) visit(ctx.expression());
        }
        return new FunctionStatements(
                new Statements(statements),
                returnExpression
        );
    }

    @Override
    public FunctionDeclaration visitFunctionDeclaration(XFlutterParser.FunctionDeclarationContext ctx) {
        return new FunctionDeclaration(
                (FunctionSignature) visit(ctx.functionSignature()),
                (FunctionStatements) visit(ctx.functionBlock()),
                ctx.start.getLine()
        );
    }

    @Override
    public Parameters visitParameters(XFlutterParser.ParametersContext ctx) {
        ArrayList<Parameter> parameters = new ArrayList<>();
        for (XFlutterParser.ParameterContext parameter : ctx.parameter()) {
            parameters.add((Parameter) visit(parameter));
        }
        return new Parameters(parameters);
    }

    @Override
    public Parameter visitParameter(XFlutterParser.ParameterContext ctx) {
        return new Parameter(
                (Type) visit(ctx.type()),
                ctx.IDENTIFIER().getText(),
                ctx.start.getLine()
        );
    }

    @Override
    public IdentifierOperationExpression visitIdentifierOp(XFlutterParser.IdentifierOpContext ctx) {
        return new IdentifierOperationExpression(
                ctx.start.getLine(),
                ctx.IDENTIFIER().getText()
        );
    }

    @Override
    public Literal visitLiteralOp(XFlutterParser.LiteralOpContext ctx) {
        return (Literal) super.visitLiteralOp(ctx);
    }

    @Override
    public BooleanExpression visitBooleanExp(XFlutterParser.BooleanExpContext ctx) {
        return new BooleanExpression(
                ctx.start.getLine(),
                ctx.value.getType() == XFlutterLexer.TRUE_
        );
    }

    @Override
    public ClassDeclaration visitClassDeclaration(XFlutterParser.ClassDeclarationContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        return new ClassDeclaration(name, (ClassDeclarationBlock) visit(ctx.classDeclarationBlock()), ctx.start.getLine());
    }

    @Override
    public FunctionCall visitFunctionCall(XFlutterParser.FunctionCallContext ctx) {
        return new FunctionCall(
                ctx.start.getLine(),
                ctx.IDENTIFIER().getText(),
                (Arguments) visit(ctx.arguments())
        );
    }

    @Override
    public Arguments visitArguments(XFlutterParser.ArgumentsContext ctx) {
        if (ctx.namedArguments() != null) {
            return (Arguments) visit(ctx.namedArguments());
        }
        return new Arguments(new ArrayList<>());
    }

    @Override
    public Arguments visitNamedArguments(XFlutterParser.NamedArgumentsContext ctx) {
        ArrayList<NamedArgument> arguments = new ArrayList<>();
        for (XFlutterParser.NamedArgumentContext namedArgumentContext : ctx.namedArgument()) {
            arguments.add((NamedArgument) visit(namedArgumentContext));
        }
        return new Arguments(arguments);
    }

    @Override
    public NamedArgument visitNamedArgument(XFlutterParser.NamedArgumentContext ctx) {
        return new NamedArgument(
                ctx.IDENTIFIER().getText(),
                (Expression) visit(ctx.expression())
        );
    }

    @Override
    public Expression visitExpression(XFlutterParser.ExpressionContext ctx) {
        return (Expression) super.visitExpression(ctx);
    }

    @Override
    public FunctionCall visitFunOp(XFlutterParser.FunOpContext ctx) {
        return (FunctionCall) super.visitFunOp(ctx);
    }


    @Override
    public ParamCall visitParamOp(XFlutterParser.ParamOpContext ctx) {
        return (ParamCall) super.visitParamOp(ctx);
    }

    @Override
    public NumberOperationExpression visitNumberOp(XFlutterParser.NumberOpContext ctx) {
        return new NumberOperationExpression(
                ctx.start.getLine(),
                (OperationExpression) visit(ctx.left),
                (OperationExpression) visit(ctx.right),
                ctx.op.getType()
        );
    }

    @Override
    public OperationExpression visitPerensOp(XFlutterParser.PerensOpContext ctx) {
        return (OperationExpression) super.visitPerensOp(ctx);
    }

    @Override
    public ComparisonExpression visitComparisonExp(XFlutterParser.ComparisonExpContext ctx) {
        OperationExpression left = (OperationExpression) visit(ctx.left);
        OperationExpression right = (OperationExpression) visit(ctx.right);
        return new ComparisonExpression(
                ctx.start.getLine(),
                left,
                right,
                ctx.op.getType()
        );
    }

    @Override
    public LogicalExpression visitLogicalExp(XFlutterParser.LogicalExpContext ctx) {
        ConditionalExpression left = (ConditionalExpression) visit(ctx.left);
        ConditionalExpression right = (ConditionalExpression) visit(ctx.right);
        return new LogicalExpression(
                ctx.start.getLine(),
                left,
                right,
                ctx.op.getType()
        );
    }

    @Override
    public Literal visitLiteral(XFlutterParser.LiteralContext ctx) {
        LanguageType type;
        switch (ctx.lit.getType()) {
            case XFlutterLexer.NUMBER:
                type = LanguageType.INT;
                break;
            case XFlutterLexer.STRING:
                type = LanguageType.STRING;
                break;
            case XFlutterLexer.VOID:
                type = LanguageType.VOID;
                break;
            case XFlutterLexer.FALSE_:
            case XFlutterLexer.TRUE_:
                type = LanguageType.BOOLEAN;
                break;
            default:
                type = LanguageType.NONE;
        }
        return new Literal(
                ctx.start.getLine(),
                type,
                ctx.lit.getText()
        );
    }

    @Override
    public IfStatement visitIfStatement(XFlutterParser.IfStatementContext ctx) {
        Statements elseBlockStatements = null;
        if (ctx.statementsBlock(1) != null) {
            elseBlockStatements = (Statements) visit(ctx.statementsBlock(1));
        }
        return new IfStatement(
                ctx.start.getLine(),
                (ConditionalExpression) visit(ctx.conditionalExpression()),
                (Statements) visit(ctx.statementsBlock(0)),
                elseBlockStatements
        );
    }

    @Override
    public WhileStatement visitWhileStatement(XFlutterParser.WhileStatementContext ctx) {
        return new WhileStatement(
                ctx.start.getLine(),
                (ConditionalExpression) visit(ctx.conditionalExpression()),
                (Statements) visit(ctx.statementsBlock())
        );
    }

    @Override
    public Statements visitStatementsBlock(XFlutterParser.StatementsBlockContext ctx) {
        ArrayList<Statement> statements = new ArrayList<>();
        for (XFlutterParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }
        return new Statements(statements);
    }

    @Override
    public ParamCall visitParamCall(XFlutterParser.ParamCallContext ctx) {
        return new ParamCall(ctx.start.getLine(), (Expression) visit(ctx.expression()));
    }

    @Override
    public PrintStatement visitPrintStatement(XFlutterParser.PrintStatementContext ctx) {
        return new PrintStatement(
                ctx.start.getLine(),
                (Expression) visit(ctx.expression())
        );
    }

    @Override
    public PushStatement visitPushStatement(XFlutterParser.PushStatementContext ctx) {
        return new PushStatement(
                ctx.start.getLine(),
                ctx.functionCall().IDENTIFIER().getText(),
                visitArguments(ctx.functionCall().arguments())
        );
    }

    @Override
    public PopStatement visitPopStatement(XFlutterParser.PopStatementContext ctx) {
        return new PopStatement(ctx.start.getLine());
    }

    @Override
    public StateStatement visitStateStatement(XFlutterParser.StateStatementContext ctx) {
        return new StateStatement(ctx.start.getLine());
    }

    @Override
    public BuildFunctionDeclaration visitBuildFunctionDeclaration(XFlutterParser.BuildFunctionDeclarationContext ctx) {
        return new BuildFunctionDeclaration(
                (BuildFunctionSignature) visit(ctx.buildFunctionSignature()),
                (BuildFunctionStatements) visit(ctx.buildFunctionBlock()),
                ctx.start.getLine()
        );
    }

    @Override
    public ClassDeclarationBlock visitClassDeclarationBlock(XFlutterParser.ClassDeclarationBlockContext ctx) {
        ArrayList<InitVariable> initVariables = new ArrayList<>();
        ArrayList<FunctionDeclaration> functionDeclarations = new ArrayList<>();
        for (XFlutterParser.InitVariableContext initVariableContext : ctx.initVariable()) {
            initVariables.add((InitVariable) visit(initVariableContext));
        }
        for (XFlutterParser.FunctionDeclarationContext functionDeclarationContext : ctx.functionDeclaration()) {
            functionDeclarations.add((FunctionDeclaration) visit(functionDeclarationContext));
        }
        return new ClassDeclarationBlock(
                initVariables,
                functionDeclarations,
                (BuildFunctionDeclaration) visit(ctx.buildFunctionDeclaration())
        );
    }

    @Override
    public AnonymousFunctionDeclaration visitAnonymousFunctionDeclaration(XFlutterParser.AnonymousFunctionDeclarationContext ctx) {
        return new AnonymousFunctionDeclaration((AnonymousFunctionBlock) visit(ctx.anonymousFunctionBlock()));
    }

    @Override
    public AnonymousFunctionBlock visitAnonymousFunctionBlock(XFlutterParser.AnonymousFunctionBlockContext ctx) {
        ArrayList<Statement> statements = new ArrayList<>();
        for (XFlutterParser.StatementContext statement : ctx.statement()) {
            statements.add((Statement) visit(statement));
        }
        return new AnonymousFunctionBlock(new Statements(statements));
    }

    @Override
    public Child visitChild(XFlutterParser.ChildContext ctx) {
        return new Child((Widget) visit(ctx.widget()), ctx.start.getLine());
    }

    @Override
    public Body visitBody(XFlutterParser.BodyContext ctx) {
        return new Body((Widget) visit(ctx.widget()), ctx.start.getLine());
    }

    @Override
    public Children visitChildren(XFlutterParser.ChildrenContext ctx) {
        ArrayList<Widget> widgets = new ArrayList<>();
        for (XFlutterParser.WidgetContext widgetContext : ctx.widget()) {
            widgets.add((Widget) visit(widgetContext));
        }
        return new Children(widgets, ctx.start.getLine());
    }

    @Override
    public Width visitWidth(XFlutterParser.WidthContext ctx) {
        return new Width((Expression) visit(ctx.expression()), ctx.start.getLine());
    }

    @Override
    public Height visitHeight(XFlutterParser.HeightContext ctx) {
        return new Height((Expression) visit(ctx.expression()), ctx.start.getLine());
    }

    @Override
    public Color visitColor(XFlutterParser.ColorContext ctx) {
        LanguageColor languageColor;
        switch (ctx.value.getType()) {
            case XFlutterLexer.RED:
                languageColor = LanguageColor.RED;
                break;
            case XFlutterLexer.BLUE:
                languageColor = LanguageColor.BLUE;
                break;
            case XFlutterLexer.BLACK:
                languageColor = LanguageColor.BLACK;
                break;
            case XFlutterLexer.GREEN:
                languageColor = LanguageColor.GREEN;
                break;
            case XFlutterLexer.YELLOW:
                languageColor = LanguageColor.YELLOW;
                break;
            case XFlutterLexer.ORANGE:
                languageColor = LanguageColor.ORANGE;
                break;
            case XFlutterLexer.WHITE:
                languageColor = LanguageColor.WHITE;
                break;
            default:
                languageColor = LanguageColor.GRAY;
        }
        return new Color(languageColor, ctx.start.getLine());
    }

    @Override
    public TextAttr visitTextAttr(XFlutterParser.TextAttrContext ctx) {
        return new TextAttr((Expression) visit(ctx.expression()), ctx.start.getLine());
    }

    @Override
    public TextAlignmentAttr visitTextAlignment(XFlutterParser.TextAlignmentContext ctx) {
        TextAlignment textAlignment;
        switch (ctx.value.getType()) {
            case XFlutterLexer.START:
                textAlignment = TextAlignment.START;
                break;
            case XFlutterLexer.END:
                textAlignment = TextAlignment.END;
                break;
            default:
                textAlignment = TextAlignment.CENTER;
        }
        return new TextAlignmentAttr(textAlignment, ctx.start.getLine());
    }

    @Override
    public FontSize visitFontSize(XFlutterParser.FontSizeContext ctx) {
        return new FontSize((Expression) visit(ctx.expression()), ctx.start.getLine());
    }

    @Override
    public FontWeightAttr visitFontWeight(XFlutterParser.FontWeightContext ctx) {
        FontWeight fontWeight;
        switch (ctx.value.getType()) {
            case XFlutterLexer.LIGHT:
                fontWeight = FontWeight.LIGHT;
                break;
            case XFlutterLexer.MEDIUM:
                fontWeight = FontWeight.MEDIUM;
                break;
            case XFlutterLexer.REGULAR:
                fontWeight = FontWeight.REGULAR;
                break;
            default:
                fontWeight = FontWeight.BOLD;
        }
        return new FontWeightAttr(fontWeight, ctx.start.getLine());
    }

    @Override
    public Link visitLink(XFlutterParser.LinkContext ctx) {
        return new Link((Expression) visit(ctx.expression()), ctx.start.getLine());
    }

    @Override
    public OnPress visitOnPress(XFlutterParser.OnPressContext ctx) {
        return new OnPress((AnonymousFunctionDeclaration) visit(ctx.anonymousFunctionDeclaration()), ctx.start.getLine());
    }

    @Override
    public MainAxisAlignmentAttr visitMainAxisAlignment(XFlutterParser.MainAxisAlignmentContext ctx) {
        MainAxisAlignment mainAxisAlignment;
        switch (ctx.value.getType()) {
            case XFlutterLexer.START:
                mainAxisAlignment = MainAxisAlignment.START;
                break;
            case XFlutterLexer.CENTER_:
                mainAxisAlignment = MainAxisAlignment.CENTER;
                break;
            case XFlutterLexer.END:
                mainAxisAlignment = MainAxisAlignment.END;
                break;
            case XFlutterLexer.SPACEAROUND:
                mainAxisAlignment = MainAxisAlignment.SPACE_AROUND;
                break;
            case XFlutterLexer.SPACEBETWEEN:
                mainAxisAlignment = MainAxisAlignment.SPACE_BETWEEN;
                break;
            default:
                mainAxisAlignment = MainAxisAlignment.SPACE_EVENLY;
        }
        return new MainAxisAlignmentAttr(mainAxisAlignment, ctx.start.getLine());
    }

    @Override
    public CrossAxisAlignmentAttr visitCrossAxisAlignment(XFlutterParser.CrossAxisAlignmentContext ctx) {
        CrossAxisAlignment crossAxisAlignment;
        switch (ctx.value.getType()) {
            case XFlutterLexer.START:
                crossAxisAlignment = CrossAxisAlignment.START;
                break;
            case XFlutterLexer.CENTER_:
                crossAxisAlignment = CrossAxisAlignment.CENTER;
                break;
            default:
                crossAxisAlignment = CrossAxisAlignment.END;
        }
        return new CrossAxisAlignmentAttr(crossAxisAlignment, ctx.start.getLine());
    }

    @Override
    public Enabled visitEnabled(XFlutterParser.EnabledContext ctx) {
        return new Enabled((Expression) visit(ctx.expression()), ctx.start.getLine());
    }

    @Override
    public Margin visitMargin(XFlutterParser.MarginContext ctx) {
        return new Margin((Expression) visit(ctx.expression()), ctx.start.getLine());
    }

    @Override
    public Padding visitPadding(XFlutterParser.PaddingContext ctx) {
        return new Padding((Expression) visit(ctx.expression()), ctx.start.getLine());
    }

    @Override
    public Leading visitLeading(XFlutterParser.LeadingContext ctx) {
        return new Leading((Widget) visit(ctx.widget()), ctx.start.getLine());
    }

    @Override
    public Trailing visitTrailing(XFlutterParser.TrailingContext ctx) {
        return new Trailing((Widget) visit(ctx.widget()), ctx.start.getLine());
    }

    @Override
    public CenterAttr visitCenterAttr(XFlutterParser.CenterAttrContext ctx) {
        return new CenterAttr((Widget) visit(ctx.widget()), ctx.start.getLine());
    }

    @Override
    public AppBarAttr visitAppBarAttr(XFlutterParser.AppBarAttrContext ctx) {
        return new AppBarAttr((AppBar) visit(ctx.appBar()), ctx.start.getLine());
    }

    @Override
    public Widget visitWidget(XFlutterParser.WidgetContext ctx) {
        return (Widget) super.visitWidget(ctx);
    }

    @Override
    public Row visitRow(XFlutterParser.RowContext ctx) {
        return new Row(
                (Width) visit(ctx.width()),
                (Height) visit(ctx.height()),
                (Margin) visit(ctx.margin()),
                (Padding) visit(ctx.padding()),
                (MainAxisAlignmentAttr) visit(ctx.mainAxisAlignment()),
                (CrossAxisAlignmentAttr) visit(ctx.crossAxisAlignment()),
                (Children) visit(ctx.children())
        );
    }

    @Override
    public Column visitColumn(XFlutterParser.ColumnContext ctx) {
        return new Column(
                (Width) visit(ctx.width()),
                (Height) visit(ctx.height()),
                (Margin) visit(ctx.margin()),
                (Padding) visit(ctx.padding()),
                (MainAxisAlignmentAttr) visit(ctx.mainAxisAlignment()),
                (CrossAxisAlignmentAttr) visit(ctx.crossAxisAlignment()),
                (Children) visit(ctx.children())
        );
    }

    @Override
    public Center visitCenter(XFlutterParser.CenterContext ctx) {
        return new Center(
                (Width) visit(ctx.width()),
                (Height) visit(ctx.height()),
                (Margin) visit(ctx.margin()),
                (Padding) visit(ctx.padding()),
                (Child) visit(ctx.child())
        );
    }

    @Override
    public Text visitText(XFlutterParser.TextContext ctx) {
        return new Text(
                (Width) visit(ctx.width()),
                (Height) visit(ctx.height()),
                (Margin) visit(ctx.margin()),
                (Padding) visit(ctx.padding()),
                (TextAttr) visit(ctx.textAttr()),
                (TextAlignmentAttr) visit(ctx.textAlignment()),
                (FontSize) visit(ctx.fontSize()),
                (FontWeightAttr) visit(ctx.fontWeight()),
                (Color) visit(ctx.color())
        );
    }

    @Override
    public Container visitContainer(XFlutterParser.ContainerContext ctx) {
        return new Container(
                (Width) visit(ctx.width()),
                (Height) visit(ctx.height()),
                (Margin) visit(ctx.margin()),
                (Padding) visit(ctx.padding()),
                (Child) visit(ctx.child()),
                (Color) visit(ctx.color())
        );
    }

    @Override
    public Image visitImage(XFlutterParser.ImageContext ctx) {
        return new Image(
                (Width) visit(ctx.width()),
                (Height) visit(ctx.height()),
                (Margin) visit(ctx.margin()),
                (Padding) visit(ctx.padding()),
                (Link) visit(ctx.link())
        );
    }

    @Override
    public Button visitButton(XFlutterParser.ButtonContext ctx) {
        return new Button(
                (Width) visit(ctx.width()),
                (Height) visit(ctx.height()),
                (Margin) visit(ctx.margin()),
                (Padding) visit(ctx.padding()),
                (TextAttr) visit(ctx.textAttr()),
                (OnPress) visit(ctx.onPress()),
                (Color) visit(ctx.color()),
                (Enabled) visit(ctx.enabled())
        );
    }

    @Override
    public Scaffold visitScaffold(XFlutterParser.ScaffoldContext ctx) {
        return new Scaffold(
                (Width) visit(ctx.width()),
                (Height) visit(ctx.height()),
                (Margin) visit(ctx.margin()),
                (Padding) visit(ctx.padding()),
                (AppBarAttr) visit(ctx.appBarAttr()),
                (Body) visit(ctx.body())
        );
    }

    @Override
    public SizedBox visitSizedBox(XFlutterParser.SizedBoxContext ctx) {
        return new SizedBox(
                (Width) visit(ctx.width()),
                (Height) visit(ctx.height()),
                (Margin) visit(ctx.margin()),
                (Padding) visit(ctx.padding()),
                (Color) visit(ctx.color())
        );
    }

    @Override
    public AppBar visitAppBar(XFlutterParser.AppBarContext ctx) {
        return new AppBar(
                (Width) visit(ctx.width()),
                (Height) visit(ctx.height()),
                (Margin) visit(ctx.margin()),
                (Padding) visit(ctx.padding()),
                (Color) visit(ctx.color()),
                (Leading) visit(ctx.leading()),
                (CenterAttr) visit(ctx.centerAttr()),
                (Trailing) visit(ctx.trailing())
        );
    }
}

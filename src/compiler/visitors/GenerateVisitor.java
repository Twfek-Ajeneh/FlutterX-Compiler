package compiler.visitors;

import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.JsAst;
import com.google.javascript.jscomp.SourceFile;
import compiler.generate.GenerateUtils;
import compiler.syntax.models.classes.ClassDeclaration;
import compiler.syntax.models.expression.Expression;
import compiler.syntax.models.expression.conditional.BooleanExpression;
import compiler.syntax.models.expression.conditional.ComparisonExpression;
import compiler.syntax.models.expression.conditional.ConditionalExpression;
import compiler.syntax.models.expression.conditional.LogicalExpression;
import compiler.syntax.models.expression.operation.IdentifierOperationExpression;
import compiler.syntax.models.expression.operation.NumberOperationExpression;
import compiler.syntax.models.expression.operation.OperationExpression;
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
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GenerateVisitor {

    private final File generateFolder;
    private String currentPath;
    private int classNumber = 0;

    public GenerateVisitor(String path) throws IOException {
        File folder = new File(path);
        FileUtils.forceMkdir(folder);
        FileUtils.cleanDirectory(folder);
        this.generateFolder = folder;
    }

    public void visitProgram(Program program) throws IOException {
        for (ClassDeclaration classDeclaration : program.getClassDeclarations()) {
            visitClass(classDeclaration);
        }
    }

    public void visitClass(ClassDeclaration classDeclaration) throws IOException {
        String name = classDeclaration.getName();
        String folderPath = generateFolder.getPath() + "/" + name;
        String filePath = generateFolder.getPath() + "/" + name + "/" + name;
        this.currentPath = filePath;

        File classFolder = new File(folderPath);
        File htmlFile = new File(filePath + ".html");
        File styleFile = new File(filePath + ".css");
        File jsFile = new File(filePath + ".js");

        FileUtils.forceMkdir(classFolder);
        FileUtils.touch(htmlFile);
        FileUtils.touch(styleFile);
        FileUtils.touch(jsFile);

        FileUtils.writeStringToFile(htmlFile, GenerateUtils.initHtmlFileData(name), "UTF-8", false);
        FileUtils.writeStringToFile(styleFile, GenerateUtils.initStyleFileData(), "UTF-8", false);
        FileUtils.writeStringToFile(jsFile, GenerateUtils.initJsFileData(), "UTF-8", false);

        StringBuilder globalVariables = new StringBuilder();
        for (InitVariable variable : classDeclaration.getDeclarationBlock().getInitVariables()) {
            globalVariables.append(visitInitVariable(variable));
        }

        FileUtils.writeStringToFile(jsFile, globalVariables.toString(), "UTF-8", true);

        for (FunctionDeclaration functionDeclaration : classDeclaration.getDeclarationBlock().getFunctionDeclarations()) {
            visitFunction(functionDeclaration);
        }

        visitBuildFunction(classDeclaration.getDeclarationBlock().getBuildFunctionDeclaration());

        Compiler compiler = new Compiler();
        CompilerOptions options = new CompilerOptions();
        options.setPrettyPrint(true);
        options.setPreserveTypeAnnotations(true);

        SourceFile inputFile = SourceFile.fromCode(jsFile.getPath(), FileUtils.readFileToString(jsFile, "UTF-8"));
        compiler.compile(SourceFile.fromCode("externs.js", ""), inputFile, options);

        String formattedCode = compiler.toSource();

        Files.write(Paths.get(jsFile.getPath()), formattedCode.getBytes());
    }

    public void visitFunction(FunctionDeclaration functionDeclaration) throws IOException {
        String name = functionDeclaration.getSignature().getName();
        String parameters = visitParameters(functionDeclaration.getSignature().getParameters());

        String functionSignature = GenerateUtils.functionSignatureData(name, parameters);

        String body = visitStatements(functionDeclaration.getStatements().getStatements());

        String returnExp = "";
        if(functionDeclaration.getStatements().getReturnExpression() != null) {
            returnExp = GenerateUtils.functionReturnData(functionDeclaration.getStatements().getReturnExpression().toString());
        }

        String function = GenerateUtils.functionData(functionSignature, body, returnExp);

        File jsFile = new File(currentPath + ".js");
        FileUtils.writeStringToFile(jsFile, function, "UTF-8", true);
    }

    public void visitBuildFunction(BuildFunctionDeclaration buildFunctionDeclaration) throws IOException {
        String name = buildFunctionDeclaration.getSignature().getName();
        String functionSignature = GenerateUtils.functionSignatureData(name, "");
        StringBuffer functionBody = new StringBuffer();
        functionBody.append(visitStatements(buildFunctionDeclaration.getStatements().getStatements()));

        Element widget = visitWidget(buildFunctionDeclaration.getStatements().getReturnWidget(), functionBody);
        File htmlFile = new File(currentPath + ".html");
        Document doc = Jsoup.parse(htmlFile, "UTF-8", "");
        Element body = doc.select("body").first();
        body.prependChild(widget);
        doc.outputSettings().charset("UTF-8").prettyPrint();
        String html = doc.html();
        FileUtils.writeStringToFile(htmlFile, html, "UTF-8", false);

        String function = GenerateUtils.buildFunctionData(functionSignature, functionBody.toString());
        File jsFile = new File(currentPath + ".js");
        FileUtils.writeStringToFile(jsFile, function, "UTF-8", true);
    }

    public String visitParameters(Parameters parameters) {
        StringBuilder parametersString = new StringBuilder();
        for (Parameter parameter : parameters.getParameters()) {
            parametersString.append(visitParameter(parameter));
            parametersString.append(", ");
        }
        return parametersString.toString();
    }

    public String visitParameter(Parameter parameter) {
        return parameter.getName();
    }

    public String visitStatements(Statements statements) {
        StringBuilder statementsString = new StringBuilder();
        for (Statement statement : statements.getStatements()) {
            statementsString.append(visitStatement(statement));
        }
        return statementsString.toString();
    }

    public String visitStatement(Statement statement) {
        if (statement instanceof SemiColonStatement) {
            return visitSemiColonStatement((SemiColonStatement) statement);
        } else if (statement instanceof NonSemiColonStatement) {
            return visitNonSemiColonStatement((NonSemiColonStatement) statement);
        } else {
            return "";
        }
    }

    public String visitSemiColonStatement(SemiColonStatement semiColonStatement) {
        if (semiColonStatement instanceof InitVariable) {
            return visitInitVariable((InitVariable) semiColonStatement);
        } else if (semiColonStatement instanceof MutateVariable) {
            return visitMutateVariable((MutateVariable) semiColonStatement);
        } else if (semiColonStatement instanceof PrintStatement) {
            return visitPrintStatement((PrintStatement) semiColonStatement);
        } else if (semiColonStatement instanceof PushStatement) {
            return visitPushStatement((PushStatement) semiColonStatement);
        } else if (semiColonStatement instanceof PopStatement) {
            return visitPopStatement((PopStatement) semiColonStatement);
        } else if (semiColonStatement instanceof StateStatement) {
            return visitStateStatement((StateStatement) semiColonStatement);
        } else {
            return "";
        }
    }

    public String visitNonSemiColonStatement(NonSemiColonStatement nonSemiColonStatement) {
        if (nonSemiColonStatement instanceof IfStatement) {
            return visitIfStatement((IfStatement) nonSemiColonStatement);
        } else if (nonSemiColonStatement instanceof WhileStatement) {
            return visitWhileStatement((WhileStatement) nonSemiColonStatement);
        } else {
            return "";
        }
    }

    public String visitIfStatement(IfStatement ifStatement) {
        String condition = ifStatement.getCondition().toString();
        String body = visitStatements(ifStatement.getStatements());
        String elseBody = visitStatements(ifStatement.getStatements());
        return GenerateUtils.ifStatementData(condition, body, elseBody);
    }

    public String visitWhileStatement(WhileStatement whileStatement) {
        String condition = whileStatement.getCondition().toString();
        String body = visitStatements(whileStatement.getStatements());
        return GenerateUtils.whileStatementData(condition, body);
    }

    public String visitPopStatement(PopStatement popStatement) {
        return GenerateUtils.popStatementData();
    }

    public String visitPrintStatement(PrintStatement printStatement) {
        String value = printStatement.getExpression().toString();
        return GenerateUtils.printStatementData(value);
    }

    public String visitPushStatement(PushStatement pushStatement) {
        String name = pushStatement.getIdentifier();
        String data = pushStatement.toString();
        return GenerateUtils.pushStatementData(name, data);
    }

    public String visitStateStatement(StateStatement stateStatement) {
        return GenerateUtils.stateStatementData();
    }

    public void visitExpression(Expression expression) {}

    public void visitConditionalExpression(ConditionalExpression conditionalExpression) {}

    public void visitComparisonExpression(ComparisonExpression comparisonExpression) {}

    public void visitBooleanExpression(BooleanExpression booleanExpression) {}

    public void visitLogicalExpression(LogicalExpression logicalExpression) {}

    public void visitOperationalExpression(OperationExpression operationExpression) {}

    public void visitIdentifierOperationExpression(IdentifierOperationExpression identifierOperationExpression) {}

    public void visitNumberOperationExpression(NumberOperationExpression numberOperationExpression) {}

    public void visitLiteral(Literal literal) {}

    public void visitFunctionCall(FunctionCall functionCall) {}

    public void visitArguments(Arguments arguments) {}

    public void visitNamedArgument(NamedArgument namedArgument) {}

    public String visitInitVariable(InitVariable initVariable) {
        String name = initVariable.getIdentifier();
        String value = initVariable.getExpression().toString();
        return GenerateUtils.initVariableData(name, value);
    }

    public String visitMutateVariable(MutateVariable mutateVariable) {
        String name = mutateVariable.getIdentifier();
        String value = mutateVariable.getExpression().toString();
        return GenerateUtils.mutateVariableData(name, value);
    }

    public String visitAnonymousFunction(AnonymousFunctionDeclaration anonymousFunctionDeclaration) {
        String body = visitStatements(anonymousFunctionDeclaration.getAnonymousFunctionBlock().getStatements());
        return GenerateUtils.anonymousFunctionData(body);
    }

    public Element visitWidget(Widget widget, StringBuffer functionBody) {
        if (widget instanceof Row) return visitRow((Row) widget, functionBody);
        else if (widget instanceof Column) return visitColumn((Column) widget, functionBody);
        else if (widget instanceof Center) return visitCenter((Center) widget, functionBody);
        else if (widget instanceof Text) return visitText((Text) widget, functionBody);
        else if (widget instanceof Container) return visitContainer((Container) widget, functionBody);
        else if (widget instanceof Image) return visitImage((Image) widget, functionBody);
        else if (widget instanceof Button) return visitButton((Button) widget, functionBody);
        else if (widget instanceof Scaffold) return visitScaffold((Scaffold) widget, functionBody);
        else if (widget instanceof SizedBox) return visitSizedBox((SizedBox) widget, functionBody);
        else if (widget instanceof AppBar) return visitAppBar((AppBar) widget, functionBody);
        else return new Element(Tag.valueOf("div"), "");
    }

    public Element visitRow(Row row, StringBuffer functionBody) {
        Element rowElement = new Element(Tag.valueOf("div"), "");

        rowElement.addClass("row");
        rowElement.addClass("row" + classNumber);
        rowElement.attr(
                "style",
                "justify-content: " + row.getMainAxisAlignmentAttr().toString() + " ;" +
                        "align-items: " + row.getCrossAxisAlignmentAttr().toString() + " ;"
        );
        functionBody.append(GenerateUtils.setCommonStyleCallData(
                "row" + classNumber,
                row.getWidth().getExpression().toString(),
                row.getHeight().getExpression().toString(),
                row.getMargin().getExpression().toString(),
                row.getPadding().getExpression().toString()
        ));
        classNumber++;

        ArrayList<Element> children = new ArrayList<>();
        for (Widget widget : row.getChildren().getWidgets()) {
            children.add(visitWidget(widget, functionBody));
        }
        rowElement.appendChildren(children);

        return rowElement;
    }

    public Element visitColumn(Column column, StringBuffer functionBody) {
        Element columnElement = new Element(Tag.valueOf("div"), "");

        columnElement.addClass("column");
        columnElement.addClass("column" + classNumber);
        columnElement.attr(
                "style",
                "justify-content: " + column.getCrossAxisAlignmentAttr().toString() + " ;" +
                        "align-items: " + column.getMainAxisAlignmentAttr().toString() + " ;"
        );
        functionBody.append(GenerateUtils.setCommonStyleCallData(
                "column" + classNumber,
                column.getWidth().getExpression().toString(),
                column.getHeight().getExpression().toString(),
                column.getMargin().getExpression().toString(),
                column.getPadding().getExpression().toString()
        ));
        classNumber++;


        ArrayList<Element> children = new ArrayList<>();
        for (Widget widget : column.getChildren().getWidgets()) {
            children.add(visitWidget(widget, functionBody));
        }
        columnElement.appendChildren(children);

        return columnElement;
    }

    public Element visitCenter(Center center, StringBuffer functionBody) {
        Element centerElement = new Element(Tag.valueOf("div"), "");

        centerElement.addClass("center");
        centerElement.addClass("center" + classNumber);
        functionBody.append(GenerateUtils.setCommonStyleCallData(
                "center" + classNumber,
                center.getWidth().getExpression().toString(),
                center.getHeight().getExpression().toString(),
                center.getMargin().getExpression().toString(),
                center.getPadding().getExpression().toString()
        ));
        classNumber++;

        centerElement.appendChild(visitWidget(center.getChild().getWidget(), functionBody));

        return centerElement;
    }

    public Element visitText(Text text, StringBuffer functionBody) {
        Element textElement = new Element(Tag.valueOf("p"), "");

        textElement.addClass("p" + classNumber);
        textElement.attr(
                "style",
                "text-align: " + text.getTextAlignmentAttr().toString() + ";" +
                        "font-weight: " + text.getFontWeightAttr().toString() + ";" +
                        "color: " + text.getColor().toString() + ";"
        );
        functionBody.append(GenerateUtils.setCommonStyleCallData(
                "p" + classNumber,
                text.getWidth().getExpression().toString(),
                text.getHeight().getExpression().toString(),
                text.getMargin().getExpression().toString(),
                text.getPadding().getExpression().toString()
        ));
        functionBody.append(GenerateUtils.setTextAttrData(
                "p" + classNumber,
                text.getTextAttr().getExpression().toString(),
                text.getFontSize().getExpression().toString()
        ));
        classNumber++;

        return textElement;
    }

    public Element visitContainer(Container container, StringBuffer functionBody) {
        Element containerElement = new Element(Tag.valueOf("div"), "");

        containerElement.addClass("container" + classNumber);
        containerElement.attr(
                "style",
                "background-color: " + container.getColor().toString() + ";"
        );
        functionBody.append(GenerateUtils.setCommonStyleCallData(
                "container" + classNumber,
                container.getWidth().getExpression().toString(),
                container.getHeight().getExpression().toString(),
                container.getMargin().getExpression().toString(),
                container.getPadding().getExpression().toString()
        ));
        classNumber++;

        containerElement.appendChild(visitWidget(container.getChild().getWidget(), functionBody));

        return containerElement;
    }

    public Element visitImage(Image image, StringBuffer functionBody) {
        Element imageElement = new Element(Tag.valueOf("img"), "");

        imageElement.addClass("img" + classNumber);
        functionBody.append(GenerateUtils.setCommonStyleCallData(
                "img" + classNumber,
                image.getWidth().getExpression().toString(),
                image.getHeight().getExpression().toString(),
                image.getMargin().getExpression().toString(),
                image.getPadding().getExpression().toString()
        ));
        functionBody.append(GenerateUtils.setImageLinkData(
                "img" + classNumber,
                image.getLink().getExpression().toString()
        ));
        classNumber++;

        return imageElement;
    }

    public Element visitButton(Button button, StringBuffer functionBody) {
        Element buttonElement = new Element(Tag.valueOf("button"), "");

        buttonElement.addClass("button");
        buttonElement.addClass("button" + classNumber);
        buttonElement.attr(
                "style",
                "background-color: " + button.getColor().toString() + ";"
        );
        functionBody.append(GenerateUtils.setButtonAttrData(
                "button" + classNumber,
                button.getTextAttr().getExpression().toString(),
                button.getEnabled().getExpression().toString(),
                visitAnonymousFunction(button.getOnPress().getAnonymousFunctionDeclaration())
        ));
        functionBody.append(GenerateUtils.setCommonStyleCallData(
                "button" + classNumber,
                button.getWidth().getExpression().toString(),
                button.getHeight().getExpression().toString(),
                button.getMargin().getExpression().toString(),
                button.getPadding().getExpression().toString()
        ));
        classNumber++;

        return buttonElement;
    }

    public Element visitScaffold(Scaffold scaffold, StringBuffer functionBody) {
        Element scaffoldElement = new Element(Tag.valueOf("div"), "");

        scaffoldElement.addClass("scaffold");
        scaffoldElement.addClass("scaffold" + classNumber);
        functionBody.append(GenerateUtils.setCommonStyleCallData(
                "scaffold" + classNumber,
                scaffold.getWidth().getExpression().toString(),
                scaffold.getHeight().getExpression().toString(),
                scaffold.getMargin().getExpression().toString(),
                scaffold.getPadding().getExpression().toString()
        ));
        classNumber++;

        scaffoldElement.appendChild(visitAppBar(scaffold.getAppBarAttr().getAppBar(), functionBody));
        scaffoldElement.appendChild(visitWidget(scaffold.getBody().getWidget(), functionBody));

        return scaffoldElement;
    }

    public Element visitSizedBox(SizedBox sizedBox, StringBuffer functionBody) {
        Element sizedBoxElement = new Element(Tag.valueOf("span"), "");

        sizedBoxElement.addClass("sized-box");
        sizedBoxElement.addClass("sized-box" + classNumber);
        sizedBoxElement.attr(
                "style",
                "background-color: " + sizedBox.getColor().toString() + ";"
        );
        functionBody.append(GenerateUtils.setCommonStyleCallData(
                "sized-box" + classNumber,
                sizedBox.getWidth().getExpression().toString(),
                sizedBox.getHeight().getExpression().toString(),
                sizedBox.getMargin().getExpression().toString(),
                sizedBox.getPadding().getExpression().toString()
        ));
        classNumber++;
        return sizedBoxElement;
    }

    public Element visitAppBar(AppBar appBar, StringBuffer functionBody) {
        Element appBarElement = new Element(Tag.valueOf("header"), "");

        appBarElement.addClass("app-bar");
        appBarElement.addClass("app-bar" + classNumber);
        appBarElement.attr(
                "style",
                "background-color: " + appBar.getColor().toString() + ";"
        );
        functionBody.append(GenerateUtils.setCommonStyleCallData(
                "app-bar" + classNumber,
                appBar.getWidth().getExpression().toString(),
                appBar.getHeight().getExpression().toString(),
                appBar.getMargin().getExpression().toString(),
                appBar.getPadding().getExpression().toString()
        ));
        classNumber++;

        Element leading = visitWidget(appBar.getLeading().getWidget(), functionBody);
        Element center = visitWidget(appBar.getCenterAttr().getWidget(), functionBody);
        Element trilling = visitWidget(appBar.getTrailing().getWidget(), functionBody);

        center.addClass("app-bar-center");

        appBarElement.appendChild(leading);
        appBarElement.appendChild(center);
        appBarElement.appendChild(trilling);

        return appBarElement;
    }
}

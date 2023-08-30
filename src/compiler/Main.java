package compiler;

import compiler.errors.XFlutterErrorListener;
import compiler.syntax.models.program.Program;
import compiler.visitors.ASTBuilderVisitor;
import compiler.visitors.GenerateVisitor;
import compiler.visitors.SemanticsCheckerVisitor;
import gen.XFlutterLexer;
import gen.XFlutterParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Twfek Ajeneh\\Desktop\\Collage\\Forth year\\Chapter two\\Compiler Project\\flutterx\\src\\compiler\\tests\\state_and_navigation.dart";

        CharStream input = CharStreams.fromFileName(filePath);

        XFlutterErrorListener xFlutterErrorListener = new XFlutterErrorListener();

        XFlutterLexer xFlutterLexer = new XFlutterLexer(input);
        xFlutterLexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        xFlutterLexer.addErrorListener(xFlutterErrorListener);
        CommonTokenStream tokens = new CommonTokenStream(xFlutterLexer);

        XFlutterParser xFlutterParser = new XFlutterParser(tokens);
        xFlutterParser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        xFlutterParser.addErrorListener(xFlutterErrorListener);

        try {
            ParseTree parseTree = xFlutterParser.program();
            if (!xFlutterErrorListener.getErrors().isEmpty()) {
                for (String error : xFlutterErrorListener.getErrors()) {
                    System.err.println(error);
                }
                return;
            }

            ASTBuilderVisitor ASTBuilderVisitor = new ASTBuilderVisitor();
            Program root = (Program) ASTBuilderVisitor.visit(parseTree);

            SemanticsCheckerVisitor semanticsCheckerVisitor = new SemanticsCheckerVisitor();
            semanticsCheckerVisitor.visitProgram(root);

            if (!semanticsCheckerVisitor.getErrors().isEmpty()) {
                for (String error : semanticsCheckerVisitor.getErrors()) {
                    System.err.println(error);
                }
                return;
            }

            GenerateVisitor generateVisitor = new GenerateVisitor("./generate");
            generateVisitor.visitProgram(root);

        } catch (Exception ex) {
            // TODO: Remove this at production
            ex.printStackTrace();
        }
    }
}

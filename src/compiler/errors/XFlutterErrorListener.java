package compiler.errors;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public class XFlutterErrorListener extends BaseErrorListener {

    private final ArrayList<String> errors = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        errors.add("Error at line " + line + ": " + msg);
    }

    public List<String> getErrors() {
        return errors;
    }
}

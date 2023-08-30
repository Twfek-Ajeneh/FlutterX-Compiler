package compiler.syntax.models.expression;


public abstract class Expression {
    private final int lineNumber;

    public Expression(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}


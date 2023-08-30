package compiler.syntax.models.statement;

public abstract class Statement {
    private final int lineNumber;

    public Statement(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}

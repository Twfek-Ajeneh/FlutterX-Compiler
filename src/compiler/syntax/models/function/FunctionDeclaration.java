package compiler.syntax.models.function;

public class FunctionDeclaration {
    private final int lineNumber;
    private final FunctionSignature signature;
    private final FunctionStatements statements;

    public FunctionDeclaration(FunctionSignature signature, FunctionStatements statements , int lineNumber) {
        this.signature = signature;
        this.statements = statements;
        this.lineNumber = lineNumber;
    }

    public FunctionSignature getSignature() {
        return signature;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public FunctionStatements getStatements(){ return statements; }
}

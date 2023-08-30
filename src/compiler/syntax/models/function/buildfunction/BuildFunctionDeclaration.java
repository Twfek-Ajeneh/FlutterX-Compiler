package compiler.syntax.models.function.buildfunction;

public class BuildFunctionDeclaration {
    private final int lineNumber;
    private final BuildFunctionSignature signature;
    private final BuildFunctionStatements statements;

    public BuildFunctionDeclaration(BuildFunctionSignature signature, BuildFunctionStatements statements , int lineNumber) {
        this.signature = signature;
        this.statements = statements;
        this.lineNumber = lineNumber;
    }

    public BuildFunctionSignature getSignature() {
        return signature;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public BuildFunctionStatements getStatements() {
        return statements;
    }
}

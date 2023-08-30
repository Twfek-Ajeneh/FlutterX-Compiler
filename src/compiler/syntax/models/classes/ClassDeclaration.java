package compiler.syntax.models.classes;

public class ClassDeclaration {
    private final String name;
    private final int lineNumber;
    private final ClassDeclarationBlock declarationBlock;

    public ClassDeclaration(String name, ClassDeclarationBlock declarationBlock , int lineNumber) {
        this.name = name;
        this.declarationBlock = declarationBlock;
        this.lineNumber = lineNumber;
    }

    public String getName() {
        return name;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public ClassDeclarationBlock getDeclarationBlock() {
        return declarationBlock;
    }
}

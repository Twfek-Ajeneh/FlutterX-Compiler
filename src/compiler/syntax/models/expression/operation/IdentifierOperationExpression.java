package compiler.syntax.models.expression.operation;

public class IdentifierOperationExpression extends OperationExpression {
    private final String identifier;

    public IdentifierOperationExpression(int lineNumber, String identifier) {
        super(lineNumber);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return identifier;
    }
}

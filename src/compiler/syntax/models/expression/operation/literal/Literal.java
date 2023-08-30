package compiler.syntax.models.expression.operation.literal;

import compiler.syntax.models.expression.operation.OperationExpression;
import compiler.types.LanguageType;

public class Literal extends OperationExpression {
    private final LanguageType type;
    private final String value;

    public Literal(int lineNumber, LanguageType type, String value) {
        super(lineNumber);
        this.type = type;
        this.value = value;
    }

    public LanguageType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}

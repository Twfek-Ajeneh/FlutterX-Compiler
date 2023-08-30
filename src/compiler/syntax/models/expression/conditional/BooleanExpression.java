package compiler.syntax.models.expression.conditional;

public class BooleanExpression extends ConditionalExpression {
    private final boolean value;

    public BooleanExpression(int lineNumber, boolean value) {
        super(lineNumber);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}

package compiler.syntax.models.expression.operation;

import compiler.syntax.models.expression.Expression;

public class ParamCall extends OperationExpression {
    private final Expression key;

    public ParamCall(int lineNumber, Expression key) {
        super(lineNumber);
        this.key = key;
    }

    public Expression getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "window[" + key.toString() + "]";
    }
}

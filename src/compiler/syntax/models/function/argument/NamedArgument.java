package compiler.syntax.models.function.argument;

import compiler.syntax.models.expression.Expression;

public class NamedArgument {
    private final String key;
    private final Expression expression;

    public NamedArgument(String key, Expression expression) {
        this.key = key;
        this.expression = expression;
    }

    public String getKey() {
        return key;
    }

    public Expression getExpression() {
        return expression;
    }
}

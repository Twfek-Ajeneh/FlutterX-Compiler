package compiler.syntax.models.widget.attribute;

import compiler.syntax.models.expression.Expression;

public class Enabled extends WidgetAttribute {
    private final Expression expression;

    public Enabled(Expression expression, int lineNumber) {
        super(lineNumber);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}

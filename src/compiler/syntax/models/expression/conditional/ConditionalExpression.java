package compiler.syntax.models.expression.conditional;

import compiler.syntax.models.expression.Expression;

public abstract class ConditionalExpression extends Expression {
    public ConditionalExpression(int lineNumber) {
        super(lineNumber);
    }
}

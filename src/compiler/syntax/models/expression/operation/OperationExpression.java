package compiler.syntax.models.expression.operation;

import compiler.syntax.models.expression.Expression;

public abstract class OperationExpression extends Expression {
    public OperationExpression(int lineNumber) {
        super(lineNumber);
    }
}

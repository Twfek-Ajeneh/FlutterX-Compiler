package compiler.syntax.models.statement.semicolon;

import compiler.syntax.models.expression.Expression;

public class PrintStatement extends SemiColonStatement {
    private final Expression expression;

    public PrintStatement(int lineNumber, Expression expression) {
        super(lineNumber);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}

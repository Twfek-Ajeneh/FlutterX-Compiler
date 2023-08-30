package compiler.syntax.models.variable;

import compiler.syntax.models.expression.Expression;
import compiler.syntax.models.statement.semicolon.SemiColonStatement;

public class MutateVariable extends SemiColonStatement {
    private final String identifier;
    private final Expression expression;

    public MutateVariable(int lineNumber, String identifier, Expression expression) {
        super(lineNumber);
        this.identifier = identifier;
        this.expression = expression;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Expression getExpression() {
        return expression;
    }
}

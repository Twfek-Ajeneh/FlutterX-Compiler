package compiler.syntax.models.variable;

import compiler.syntax.models.expression.Expression;
import compiler.syntax.models.statement.semicolon.SemiColonStatement;
import compiler.syntax.models.variable.type.Type;

public class InitVariable extends SemiColonStatement {
    private final Type type;
    private final String identifier;
    private final Expression expression;

    public InitVariable(int lineNumber, Type type, String identifier, Expression expression) {
        super(lineNumber);
        this.type = type;
        this.identifier = identifier;
        this.expression = expression;
    }

    public Type getType() {
        return type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Expression getExpression() {
        return expression;
    }
}

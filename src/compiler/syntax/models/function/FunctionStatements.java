package compiler.syntax.models.function;

import compiler.syntax.models.expression.Expression;
import compiler.syntax.models.statement.Statement;
import compiler.syntax.models.statement.Statements;

import java.util.List;

public class FunctionStatements {
    private final Statements statements;
    private final Expression returnExpression;

    public FunctionStatements(Statements statements, Expression returnExpression) {
        this.statements = statements;
        this.returnExpression = returnExpression;
    }

    public Statements getStatements() {
        return statements;
    }

    public Expression getReturnExpression() {
        return returnExpression;
    }
}

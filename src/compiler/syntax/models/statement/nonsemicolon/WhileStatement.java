package compiler.syntax.models.statement.nonsemicolon;

import compiler.syntax.models.expression.conditional.ConditionalExpression;
import compiler.syntax.models.statement.Statements;

public class WhileStatement extends NonSemiColonStatement {
    private final ConditionalExpression condition;
    private final Statements statements;

    public WhileStatement(int lineNumber, ConditionalExpression condition, Statements statements) {
        super(lineNumber);
        this.condition = condition;
        this.statements = statements;
    }

    public ConditionalExpression getCondition() {
        return condition;
    }

    public Statements getStatements() {
        return statements;
    }
}

package compiler.syntax.models.statement.nonsemicolon;

import compiler.syntax.models.expression.conditional.ConditionalExpression;
import compiler.syntax.models.statement.Statements;

public class IfStatement extends NonSemiColonStatement {
    private final ConditionalExpression condition;
    private final Statements statements;
    private final Statements elseStatements;

    public IfStatement(int lineNumber, ConditionalExpression condition, Statements statements, Statements elseStatements) {
        super(lineNumber);
        this.condition = condition;
        this.statements = statements;
        this.elseStatements = elseStatements;
    }

    public ConditionalExpression getCondition() {
        return condition;
    }

    public Statements getStatements() {
        return statements;
    }

    public Statements getElseStatements() {
        return elseStatements;
    }
}

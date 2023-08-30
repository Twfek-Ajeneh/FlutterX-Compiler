package compiler.syntax.models.statement.nonsemicolon;

import compiler.syntax.models.statement.Statement;

public abstract class NonSemiColonStatement extends Statement {
    public NonSemiColonStatement(int lineNumber) {
        super(lineNumber);
    }
}

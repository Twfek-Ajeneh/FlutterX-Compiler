package compiler.syntax.models.statement.semicolon;

import compiler.syntax.models.statement.Statement;

public abstract class SemiColonStatement extends Statement {
    public SemiColonStatement(int lineNumber) {
        super(lineNumber);
    }
}

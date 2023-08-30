package compiler.syntax.models.widget.anonymousfunction;

import compiler.syntax.models.statement.Statements;

public class AnonymousFunctionBlock {
    private final Statements statements;

    public AnonymousFunctionBlock(Statements statements) {
        this.statements = statements;
    }

    public Statements getStatements() {
        return statements;
    }
}

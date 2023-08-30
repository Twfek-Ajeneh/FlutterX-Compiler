package compiler.syntax.models.statement;

import java.util.List;

public class Statements {
    private final List<Statement> statements;

    public Statements(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return statements;
    }
}

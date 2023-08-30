package compiler.syntax.models.function.buildfunction;

import compiler.syntax.models.statement.Statements;
import compiler.syntax.models.widget.Widget;

public class BuildFunctionStatements {
    private final Statements statements;
    private final Widget returnWidget;

    public BuildFunctionStatements(Statements statements, Widget returnWidget) {
        this.statements = statements;
        this.returnWidget = returnWidget;
    }

    public Statements getStatements() {
        return statements;
    }

    public Widget getReturnWidget() {
        return returnWidget;
    }
}

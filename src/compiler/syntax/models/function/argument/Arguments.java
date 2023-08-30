package compiler.syntax.models.function.argument;

import java.util.List;

public class Arguments {
    private final List<NamedArgument> argumentsList;

    public Arguments(List<NamedArgument> argumentsList) {
        this.argumentsList = argumentsList;
    }

    public List<NamedArgument> getArgumentsList() {
        return argumentsList;
    }
}

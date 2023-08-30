package compiler.syntax.models.statement.semicolon;

import compiler.syntax.models.function.argument.Arguments;
import compiler.syntax.models.function.argument.NamedArgument;

public class PushStatement extends SemiColonStatement {
    private final String identifier;
    private final Arguments arguments;

    public PushStatement(int lineNumber, String identifier, Arguments arguments) {
        super(lineNumber);
        this.arguments = arguments;
        this.identifier = identifier;
    }

    public Arguments getArguments() {
        return arguments;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder();
        for (NamedArgument namedArgument : arguments.getArgumentsList()) {
            value.append(", ");
            value.append(namedArgument.getKey()).append(": ").append(namedArgument.getExpression().toString());
        }
        return value.toString();
    }
}

package compiler.syntax.models.expression.operation.functioncall;

import compiler.syntax.models.expression.operation.OperationExpression;
import compiler.syntax.models.function.argument.Arguments;
import compiler.syntax.models.function.argument.NamedArgument;

public class FunctionCall extends OperationExpression {
    private final String name;
    private final Arguments arguments;

    public FunctionCall(int lineNumber, String name, Arguments arguments) {
        super(lineNumber);
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public Arguments getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder(name + "({");
        for (NamedArgument namedArgument : arguments.getArgumentsList()) {
            value.append(namedArgument.getKey()).append(": ").append(namedArgument.getExpression().toString());
            value.append(", ");
        }
        value.append("})");
        return value.toString();
    }
}

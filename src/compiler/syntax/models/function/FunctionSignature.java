package compiler.syntax.models.function;

import compiler.syntax.models.function.parameter.Parameters;
import compiler.syntax.models.variable.type.Type;

public class FunctionSignature {
    private final Type type;
    private final String name;
    private final Parameters parameters;

    public FunctionSignature(Type type, String name, Parameters parameters) {
        this.type = type;
        this.name = name;
        this.parameters = parameters;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Parameters getParameters() {
        return parameters;
    }
}

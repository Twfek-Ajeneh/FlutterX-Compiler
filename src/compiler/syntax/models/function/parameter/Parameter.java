package compiler.syntax.models.function.parameter;

import compiler.syntax.models.variable.type.Type;

public class Parameter {
    private final Type type;
    private final String name;
    private final int lineNumber;

    public Parameter(Type type, String name, int lineNumber) {
        this.type = type;
        this.name = name;
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}

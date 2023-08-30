package compiler.syntax.models.function.buildfunction;

import compiler.syntax.models.function.parameter.Parameters;
import compiler.syntax.models.variable.type.Type;

public class BuildFunctionSignature {
    private final String name;

    public BuildFunctionSignature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

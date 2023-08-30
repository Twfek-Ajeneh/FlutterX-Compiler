package compiler.syntax.models.function.parameter;

import java.util.List;

public class Parameters {
    private final List<Parameter> parameters;

    public Parameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }
}

package compiler.semantics.memory;

import compiler.types.LanguageType;

import java.util.List;

public class FunctionMemoryEntry extends MemoryEntry {
    private final LanguageType returnType;
    private final List<ParameterMemoryEntry> parameters;

    public FunctionMemoryEntry(String name, int lineNumber, String scope, LanguageType returnType, List<ParameterMemoryEntry> parameters) {
        super(name, lineNumber, scope);
        this.returnType = returnType;
        this.parameters = parameters;
    }

    public LanguageType getReturnType() {
        return returnType;
    }

    public List<ParameterMemoryEntry> getParameters() {
        return parameters;
    }
}

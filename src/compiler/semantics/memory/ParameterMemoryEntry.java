package compiler.semantics.memory;

import compiler.types.LanguageType;

public class ParameterMemoryEntry {
    private final String identifier;
    private final LanguageType type;

    public ParameterMemoryEntry(String identifier, LanguageType type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public LanguageType getType() {
        return type;
    }
}

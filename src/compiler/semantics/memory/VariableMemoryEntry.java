package compiler.semantics.memory;

import compiler.types.LanguageType;

public class VariableMemoryEntry extends MemoryEntry {
    private final LanguageType type;

    public VariableMemoryEntry(String name, int lineNumber, String scope, LanguageType type) {
        super(name, lineNumber, scope);
        this.type = type;
    }

    public LanguageType getType() {
        return type;
    }
}

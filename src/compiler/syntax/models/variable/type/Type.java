package compiler.syntax.models.variable.type;

import compiler.types.LanguageType;

public class Type {
    private final LanguageType type;

    public Type(LanguageType type) {
        this.type = type;
    }

    public LanguageType getType() {
        return type;
    }
}

package compiler.semantics.memory;

public abstract class MemoryEntry {
    private final String name;
    private final int lineNumber;
    private final String scope;

    public MemoryEntry(String name, int lineNumber, String scope) {
        this.name = name;
        this.lineNumber = lineNumber;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getScope() {
        return scope;
    }

    @Override
    public String toString() {
        return "MemoryEntry{" +
                "name='" + name + '\'' +
                ", lineNumber=" + lineNumber +
                ", scope='" + scope + '\'' +
                '}';
    }
}

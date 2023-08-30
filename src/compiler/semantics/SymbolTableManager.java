package compiler.semantics;

import compiler.semantics.memory.MemoryEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SymbolTableManager {
    private final Map<String, MemoryEntry> symbolTable = new HashMap<>();
    private final Stack<String> scopeStack = new Stack<>();

    public void pushScope(String scope) {
        scopeStack.push(scope);
    }

    public String getCurrentScope() {
        return scopeStack.peek();
    }

    public void addEntry(String key, MemoryEntry info) {
        symbolTable.put(key, info);
    }

    public MemoryEntry getEntry(String key) {
        return symbolTable.get(key);
    }

    public boolean entryExists(String key) {
        return symbolTable.containsKey(key);
    }

    public void removeCurrentScopeEntriesAndPopScope() {
        String scope = scopeStack.pop();
        removeScopeEntries(scope);
    }

    private void removeScopeEntries(String scope) {
        ArrayList<String> toRemoveKeys = new ArrayList<>();
        for (String key : symbolTable.keySet()) {
            if (symbolTable.get(key).getScope().equals(scope)) toRemoveKeys.add(key);
        }
        for (String key : toRemoveKeys) symbolTable.remove(key);
    }

    public void printSymbolTable() {
        for (String key : symbolTable.keySet()) {
            System.out.println(getEntry(key));
        }
        System.out.println("=================================");
    }
}

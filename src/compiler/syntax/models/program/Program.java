package compiler.syntax.models.program;

import compiler.syntax.models.classes.ClassDeclaration;

import java.util.List;

public class Program {
    private final List<ClassDeclaration> classDeclarations;

    public Program(List<ClassDeclaration> classDeclarations) {
        this.classDeclarations = classDeclarations;
    }

    public List<ClassDeclaration> getClassDeclarations() {
        return classDeclarations;
    }
}

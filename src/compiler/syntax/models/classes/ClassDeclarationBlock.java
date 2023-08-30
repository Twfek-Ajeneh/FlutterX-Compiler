package compiler.syntax.models.classes;

import compiler.syntax.models.function.FunctionDeclaration;
import compiler.syntax.models.function.buildfunction.BuildFunctionDeclaration;
import compiler.syntax.models.variable.InitVariable;

import java.util.List;

public class ClassDeclarationBlock {
    private final List<InitVariable> initVariables;
    private final List<FunctionDeclaration> functionDeclarations;
    private final BuildFunctionDeclaration buildFunctionDeclaration;

    public ClassDeclarationBlock(List<InitVariable> initVariables, List<FunctionDeclaration> functionDeclarations, BuildFunctionDeclaration buildFunctionDeclaration) {
        this.initVariables = initVariables;
        this.functionDeclarations = functionDeclarations;
        this.buildFunctionDeclaration = buildFunctionDeclaration;
    }

    public List<InitVariable> getInitVariables() {
        return initVariables;
    }

    public List<FunctionDeclaration> getFunctionDeclarations() {
        return functionDeclarations;
    }

    public BuildFunctionDeclaration getBuildFunctionDeclaration() {
        return buildFunctionDeclaration;
    }
}

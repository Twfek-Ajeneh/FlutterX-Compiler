package compiler.errors;

public class SemanticsErrorsUtils {
    public static String homeClassNotDefinedMessage(int lineNumber) {
        return "Error at line " + lineNumber + ": 'Home' class is not defined";
    }

    public static String classAlreadyDefinedMessage(String className, int lineNumber) {
        return "Error at line " + lineNumber + ": class '" + className + "' is already defined in the scope";
    }

    public static String classIsNotDefinedMessage(String className, int lineNumber) {
        return "Error at line " + lineNumber + ": class '" + className + "' is not defined";
    }

    public static String returnStatementNotFound(String functionName, int lineNumber) {
        return "Error at line " + lineNumber + ": function '" + functionName + "' doesn't have return statement";
    }

    public static String identifierIsNotClass(String identifier, int lineNumber) {
        return "Error at line " + lineNumber + ": '" + identifier + "' is not a class";
    }

    public static String identifierIsNotFunction(String identifier, int lineNumber) {
        return "Error at line " + lineNumber + ": '" + identifier + "' is not a function";
    }

    public static String identifierIsNotVariable(String identifier, int lineNumber) {
        return "Error at line " + lineNumber + ": '" + identifier + "' is not a variable";
    }

    public static String canNotOperateOnDifferentTypes(int lineNumber) {
        return "Error at line " + lineNumber + ": can not operate on different types";
    }

    public static String variableAlreadyDefinedMessage(String variableName, int lineNumber) {
        return "Error at line " + lineNumber + ": variable '" + variableName + "' is already defined in the scope";
    }

    public static String functionReturnTypeAndReturnExpDoNotMatch(String functionName, int lineNumber) {
        return "Error at line " + lineNumber + ": function '" + functionName + "' return type and return expression don't match";
    }

    public static String typeMismatch(int lineNumber) {
        return "Error at line " + lineNumber + ": type mismatch";
    }

    public static String argumentsDoNotMatchParametersMessage(String functionName, int lineNumber) {
        return "Error at line " + lineNumber + ": arguments do not match parameters in function " + functionName;
    }

    public static String functionAlreadyDefinedMessage(String functionName, int lineNumber) {
        return "Error at line " + lineNumber + ": function '" + functionName + "' is already defined in the scope";
    }

    public static String functionIsNotDefinedMessage(String functionName, int lineNumber) {
        return "Error at line " + lineNumber + ": function " + functionName + " is not defined in the scope";
    }

    public static String variableIsNotDefinedMessage(String variableName, int lineNumber) {
        return "Error at line " + lineNumber + ": variable '" + variableName + "' is not defined in the scope";
    }
}

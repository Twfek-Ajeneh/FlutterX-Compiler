package compiler.syntax.models.expression.conditional;

import gen.XFlutterParser;

public class LogicalExpression extends ConditionalExpression {
    private final ConditionalExpression right;
    private final ConditionalExpression left;
    private final int operation;

    public LogicalExpression(int lineNumber, ConditionalExpression right, ConditionalExpression left, int operation) {
        super(lineNumber);
        this.right = right;
        this.left = left;
        this.operation = operation;
    }

    public ConditionalExpression getRight() {
        return right;
    }

    public ConditionalExpression getLeft() {
        return left;
    }

    public String getOperation(){
        String op;
        switch (operation){
            case XFlutterParser.AA:
                op = "&&";
                break;
            default:
                op = "||";
        }
        return op;
    }

    @Override
    public String toString() {
        return left.toString() + " " + getOperation() + " " + right.toString();
    }
}

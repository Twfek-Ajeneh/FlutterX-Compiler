package compiler.syntax.models.expression.conditional;

import compiler.syntax.models.expression.operation.OperationExpression;
import gen.XFlutterParser;

public class ComparisonExpression extends ConditionalExpression {
    private final OperationExpression right;
    private final OperationExpression left;
    private final int operation;

    public ComparisonExpression(int lineNumber, OperationExpression right, OperationExpression left, int operation) {
        super(lineNumber);
        this.right = right;
        this.left = left;
        this.operation = operation;
    }

    public OperationExpression getRight() {
        return right;
    }

    public OperationExpression getLeft() {
        return left;
    }

    public String getOperation(){
        String op;
        switch (operation){
            case XFlutterParser.EE:
                op = "==";
                break;
            case XFlutterParser.GT:
                op = ">";
                break;
            case XFlutterParser.LT:
                op = "<";
                break;
            case XFlutterParser.LTE:
                op = "<=";
                break;
            case XFlutterParser.GTE:
                op = ">=";
                break;
            default:
                op = "!=";
        }

        return op;
    }

    @Override
    public String toString() {
        return left.toString() + " " + getOperation() + " " + right.toString();
    }
}

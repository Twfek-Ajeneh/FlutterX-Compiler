package compiler.syntax.models.expression.operation;

import gen.XFlutterParser;

public class NumberOperationExpression extends OperationExpression {
    private final OperationExpression left;
    private final OperationExpression right;
    private final int operation;

    public NumberOperationExpression(int lineNumber, OperationExpression left, OperationExpression right, int operation) {
        super(lineNumber);
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    public OperationExpression getLeft() {
        return left;
    }

    public OperationExpression getRight() {
        return right;
    }

    public char getOperation(){
        char op;
        switch (operation){
            case XFlutterParser.ST:
                op = '*';
                break;
            case XFlutterParser.SL:
                op = '/';
                break;
            case XFlutterParser.PC:
                op = '%';
                break;
            case XFlutterParser.PL:
                op  = '+';
                break;
            default:
                op = '-';
        }
        return op;
    }

    @Override
    public String toString() {
        return left.toString() + " " +  getOperation() + " " + right.toString();
    }
}

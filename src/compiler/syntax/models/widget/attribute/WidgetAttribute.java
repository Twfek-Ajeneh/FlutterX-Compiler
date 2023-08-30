package compiler.syntax.models.widget.attribute;

public abstract class WidgetAttribute {
    private final int lineNumber;

    public WidgetAttribute(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}

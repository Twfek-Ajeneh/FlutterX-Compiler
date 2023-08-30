package compiler.syntax.models.widget.attribute;

import compiler.syntax.models.widget.enums.CrossAxisAlignment;

public class CrossAxisAlignmentAttr extends WidgetAttribute {
    private final CrossAxisAlignment crossAxisAlignment;

    public CrossAxisAlignmentAttr(CrossAxisAlignment crossAxisAlignment, int lineNumber) {
        super(lineNumber);
        this.crossAxisAlignment = crossAxisAlignment;
    }

    public CrossAxisAlignment getCrossAxisAlignment() {
        return crossAxisAlignment;
    }

    @Override
    public String toString() {
        String value;
        switch (crossAxisAlignment) {
            case CENTER:
                value = "center";
                break;
            case START:
                value = "flex-start";
                break;
            default:
                value = "flex-end";
        }
        return value;
    }
}

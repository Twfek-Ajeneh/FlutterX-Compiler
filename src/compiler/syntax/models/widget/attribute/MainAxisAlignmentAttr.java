package compiler.syntax.models.widget.attribute;

import compiler.syntax.models.widget.enums.MainAxisAlignment;

public class MainAxisAlignmentAttr extends WidgetAttribute {
    private final MainAxisAlignment mainAxisAlignment;

    public MainAxisAlignmentAttr(MainAxisAlignment mainAxisAlignment, int lineNumber) {
        super(lineNumber);
        this.mainAxisAlignment = mainAxisAlignment;
    }

    public MainAxisAlignment getMainAxisAlignment() {
        return mainAxisAlignment;
    }

    @Override
    public String toString() {
        String value;
        switch (mainAxisAlignment) {
            case CENTER:
                value = "center";
                break;
            case START:
                value = "flex-start";
                break;
            case END:
                value = "flex-end";
                break;
            case SPACE_AROUND:
                value = "space-around";
                break;
            case SPACE_BETWEEN:
                value = "space-between";
                break;
            default:
                value = "space-evenly";
        }
        return value;
    }
}

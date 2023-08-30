package compiler.syntax.models.widget.attribute;

import compiler.syntax.models.widget.enums.TextAlignment;

public class TextAlignmentAttr extends WidgetAttribute {
    private final TextAlignment textAlignment;

    public TextAlignmentAttr(TextAlignment textAlignment, int lineNumber) {
        super(lineNumber);
        this.textAlignment = textAlignment;
    }

    public TextAlignment getTextAlignment() {
        return textAlignment;
    }

    @Override
    public String toString() {
        String value;
        switch (textAlignment) {
            case CENTER:
                value = "center";
                break;
            case START:
                value = "start";
                break;
            default:
                value = "end";
        }
        return value;
    }

}

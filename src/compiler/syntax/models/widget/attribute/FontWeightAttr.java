package compiler.syntax.models.widget.attribute;

import compiler.syntax.models.widget.enums.FontWeight;

public class FontWeightAttr extends WidgetAttribute {
    private final FontWeight fontWeight;

    public FontWeightAttr(FontWeight fontWeight, int lineNumber) {
        super(lineNumber);
        this.fontWeight = fontWeight;
    }

    public FontWeight getFontWeight() {
        return fontWeight;
    }

    @Override
    public String toString() {
        String value;
        switch (fontWeight) {
            case LIGHT:
                value = "300";
                break;
            case MEDIUM:
                value = "400";
                break;
            case REGULAR:
                value = "500";
                break;
            default:
                value = "700";
        }
        return value;
    }
}

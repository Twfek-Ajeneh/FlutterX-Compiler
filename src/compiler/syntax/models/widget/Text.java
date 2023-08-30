package compiler.syntax.models.widget;

import compiler.syntax.models.widget.attribute.*;

public class Text extends Widget {
    private final TextAttr textAttr;
    private final TextAlignmentAttr textAlignmentAttr;
    private final FontSize fontSize;
    private final FontWeightAttr fontWeightAttr;
    private final Color color;

    public Text(Width width, Height height, Margin margin, Padding padding, TextAttr textAttr, TextAlignmentAttr textAlignmentAttr, FontSize fontSize, FontWeightAttr fontWeightAttr, Color color) {
        super(width, height, margin, padding);
        this.textAttr = textAttr;
        this.textAlignmentAttr = textAlignmentAttr;
        this.fontSize = fontSize;
        this.fontWeightAttr = fontWeightAttr;
        this.color = color;
    }

    public TextAttr getTextAttr() {
        return textAttr;
    }

    public TextAlignmentAttr getTextAlignmentAttr() {
        return textAlignmentAttr;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public FontWeightAttr getFontWeightAttr() {
        return fontWeightAttr;
    }

    public Color getColor() {
        return color;
    }
}

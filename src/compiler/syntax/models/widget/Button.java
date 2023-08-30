package compiler.syntax.models.widget;

import compiler.syntax.models.widget.attribute.*;

public class Button extends Widget {
    private final TextAttr textAttr;
    private final OnPress onPress;
    private final Color color;
    private final Enabled enabled;

    public Button(Width width, Height height, Margin margin, Padding padding, TextAttr textAttr, OnPress onPress, Color color, Enabled enabled) {
        super(width, height, margin, padding);
        this.textAttr = textAttr;
        this.onPress = onPress;
        this.color = color;
        this.enabled = enabled;
    }

    public TextAttr getTextAttr() {
        return textAttr;
    }

    public OnPress getOnPress() {
        return onPress;
    }

    public Color getColor() {
        return color;
    }

    public Enabled getEnabled() {
        return enabled;
    }
}

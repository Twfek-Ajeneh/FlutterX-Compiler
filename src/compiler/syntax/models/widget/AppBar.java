package compiler.syntax.models.widget;

import compiler.syntax.models.widget.attribute.*;

public class AppBar extends Widget {
    private final Color color;
    private final Leading leading;
    private final CenterAttr centerAttr;
    private final Trailing trailing;

    public AppBar(Width width, Height height, Margin margin, Padding padding, Color color, Leading leading, CenterAttr centerAttr, Trailing trailing) {
        super(width, height, margin, padding);
        this.color = color;
        this.leading = leading;
        this.centerAttr = centerAttr;
        this.trailing = trailing;
    }

    public Color getColor() {
        return color;
    }

    public Leading getLeading() {
        return leading;
    }

    public CenterAttr getCenterAttr() {
        return centerAttr;
    }

    public Trailing getTrailing() {
        return trailing;
    }
}

package compiler.syntax.models.widget;

import compiler.syntax.models.widget.attribute.*;

public class SizedBox extends Widget {
    private final Color color;

    public SizedBox(Width width, Height height, Margin margin, Padding padding, Color color) {
        super(width, height, margin, padding);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

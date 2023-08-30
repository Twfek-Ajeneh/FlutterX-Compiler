package compiler.syntax.models.widget;

import compiler.syntax.models.widget.attribute.*;

public class Container extends Widget {
    private final Child child;
    private final Color color;

    public Container(Width width, Height height, Margin margin, Padding padding, Child child, Color color) {
        super(width, height, margin, padding);
        this.child = child;
        this.color = color;
    }

    public Child getChild() {
        return child;
    }

    public Color getColor() {
        return color;
    }
}

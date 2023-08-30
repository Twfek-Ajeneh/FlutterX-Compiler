package compiler.syntax.models.widget;

import compiler.syntax.models.widget.attribute.*;

public class Center extends Widget {
    private final Child child;

    public Center(Width width, Height height, Margin margin, Padding padding, Child child) {
        super(width, height, margin, padding);
        this.child = child;
    }

    public Child getChild() {
        return child;
    }
}

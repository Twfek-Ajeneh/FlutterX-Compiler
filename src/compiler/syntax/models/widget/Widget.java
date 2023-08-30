package compiler.syntax.models.widget;

import compiler.syntax.models.widget.attribute.Height;
import compiler.syntax.models.widget.attribute.Margin;
import compiler.syntax.models.widget.attribute.Padding;
import compiler.syntax.models.widget.attribute.Width;

public abstract class Widget {
    private final Width width;
    private final Height height;
    private final Margin margin;
    private final Padding padding;

    public Widget(Width width, Height height, Margin margin, Padding padding) {
        this.width = width;
        this.height = height;
        this.margin = margin;
        this.padding = padding;
    }

    public Width getWidth() {
        return width;
    }

    public Height getHeight() {
        return height;
    }

    public Margin getMargin() {
        return margin;
    }

    public Padding getPadding() {
        return padding;
    }
}

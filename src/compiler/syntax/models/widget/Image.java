package compiler.syntax.models.widget;

import compiler.syntax.models.widget.attribute.*;

public class Image extends Widget {
    private final Link link;

    public Image(Width width, Height height, Margin margin, Padding padding, Link link) {
        super(width, height, margin, padding);
        this.link = link;
    }

    public Link getLink() {
        return link;
    }
}

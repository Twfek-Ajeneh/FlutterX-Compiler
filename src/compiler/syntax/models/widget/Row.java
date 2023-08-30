package compiler.syntax.models.widget;

import compiler.syntax.models.widget.attribute.*;

public class Row extends Widget {

    private final MainAxisAlignmentAttr mainAxisAlignmentAttr;
    private final CrossAxisAlignmentAttr crossAxisAlignmentAttr;
    private final Children children;

    public Row(Width width, Height height, Margin margin, Padding padding, MainAxisAlignmentAttr mainAxisAlignmentAttr, CrossAxisAlignmentAttr crossAxisAlignmentAttr, Children children) {
        super(width, height, margin, padding);
        this.mainAxisAlignmentAttr = mainAxisAlignmentAttr;
        this.crossAxisAlignmentAttr = crossAxisAlignmentAttr;
        this.children = children;
    }

    public MainAxisAlignmentAttr getMainAxisAlignmentAttr() {
        return mainAxisAlignmentAttr;
    }

    public CrossAxisAlignmentAttr getCrossAxisAlignmentAttr() {
        return crossAxisAlignmentAttr;
    }

    public Children getChildren() {
        return children;
    }
}

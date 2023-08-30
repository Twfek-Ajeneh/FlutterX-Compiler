package compiler.syntax.models.widget;

import compiler.syntax.models.widget.attribute.*;

public class Scaffold extends Widget {
    private final AppBarAttr appBarAttr;
    private final Body body;

    public Scaffold(Width width, Height height, Margin margin, Padding padding, AppBarAttr appBarAttr, Body body) {
        super(width, height, margin, padding);
        this.appBarAttr = appBarAttr;
        this.body = body;
    }

    public AppBarAttr getAppBarAttr() {
        return appBarAttr;
    }

    public Body getBody() {
        return body;
    }
}

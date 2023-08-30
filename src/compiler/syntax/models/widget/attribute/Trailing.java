package compiler.syntax.models.widget.attribute;

import compiler.syntax.models.widget.Widget;

public class Trailing extends WidgetAttribute {
    private final Widget widget;

    public Trailing(Widget widget, int lineNumber) {
        super(lineNumber);
        this.widget = widget;
    }

    public Widget getWidget() {
        return widget;
    }
}

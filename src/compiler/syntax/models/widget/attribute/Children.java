package compiler.syntax.models.widget.attribute;

import compiler.syntax.models.widget.Widget;

import java.util.List;

public class Children extends WidgetAttribute {
    private final List<Widget> widgets;

    public Children(List<Widget> widgets, int lineNumber) {
        super(lineNumber);
        this.widgets = widgets;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }
}

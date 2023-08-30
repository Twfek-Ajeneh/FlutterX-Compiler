package compiler.syntax.models.widget.attribute;

import compiler.syntax.models.widget.AppBar;

public class AppBarAttr extends WidgetAttribute {
    private final AppBar appBar;

    public AppBarAttr(AppBar appBar, int lineNumber) {
        super(lineNumber);
        this.appBar = appBar;
    }

    public AppBar getAppBar() {
        return appBar;
    }
}

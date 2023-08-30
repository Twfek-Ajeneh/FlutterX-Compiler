package compiler.syntax.models.widget.attribute;

import compiler.syntax.models.widget.anonymousfunction.AnonymousFunctionDeclaration;

public class OnPress extends WidgetAttribute {
    private final AnonymousFunctionDeclaration anonymousFunctionDeclaration;

    public OnPress(AnonymousFunctionDeclaration anonymousFunctionDeclaration, int lineNumber) {
        super(lineNumber);
        this.anonymousFunctionDeclaration = anonymousFunctionDeclaration;
    }

    public AnonymousFunctionDeclaration getAnonymousFunctionDeclaration() {
        return anonymousFunctionDeclaration;
    }
}

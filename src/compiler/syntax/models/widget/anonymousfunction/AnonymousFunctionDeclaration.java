package compiler.syntax.models.widget.anonymousfunction;

public class AnonymousFunctionDeclaration {
    private final AnonymousFunctionBlock anonymousFunctionBlock;

    public AnonymousFunctionDeclaration(AnonymousFunctionBlock anonymousFunctionBlock) {
        this.anonymousFunctionBlock = anonymousFunctionBlock;
    }

    public AnonymousFunctionBlock getAnonymousFunctionBlock() {
        return anonymousFunctionBlock;
    }
}

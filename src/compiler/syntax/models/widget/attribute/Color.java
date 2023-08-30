package compiler.syntax.models.widget.attribute;

import compiler.syntax.models.widget.enums.LanguageColor;

public class Color extends WidgetAttribute {
    private final LanguageColor languageColor;

    public Color(LanguageColor languageColor, int lineNumber) {
        super(lineNumber);
        this.languageColor = languageColor;
    }

    public LanguageColor getLanguageColor() {
        return languageColor;
    }

    @Override
    public String toString() {
        String value;
        switch (languageColor) {
            case RED:
                value = "tomato";
                break;
            case GREEN:
                value = "green";
                break;
            case BLUE:
                value = "steelblue";
                break;
            case WHITE:
                value = "white";
                break;
            case BLACK:
                value = "black";
                break;
            case YELLOW:
                value = "yellow";
                break;
            case ORANGE:
                value = "orange";
                break;
            default:
                value = "gray";
        }
        return value;
    }
}

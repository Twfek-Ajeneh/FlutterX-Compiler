package compiler.generate;

public class GenerateUtils {

    public static String initHtmlFileData(String name) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" href=\"./" + name + ".css\" class=\"style\">\n" +
                "    <title>" + name + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    \n" +
                "    <script src=\"./" + name + ".js\"></script>\n" +
                "</body>\n" +
                "</html>";
    }

    public static String initStyleFileData() {
        return "*{\n" +
                "    -webkit-box-sizing: border-box; \n" +
                "\t-moz-box-sizing: border-box;\n" +
                "\tbox-sizing: border-box;\n" +
                "}\n" +
                "\n" +
                ".row{\n" +
                "    width: 100%;\n" +
                "    display: flex;\n" +
                "    justify-content: flex-start;\n" +
                "    align-items: center;\n" +
                "}\n" +
                "\n" +
                ".column{\n" +
                "    height: 100%;\n" +
                "    display: flex;\n" +
                "    flex-direction: column;\n" +
                "    justify-content: center;\n" +
                "    align-items: flex-start;\n" +
                "}\n" +
                "\n" +
                ".center{\n" +
                "    display: flex;\n" +
                "    justify-content: center;\n" +
                "    align-items: center;\n" +
                "}\n" +
                "\n" +
                ".button{\n" +
                "    width: 60px;\n" +
                "    height: 30px;\n" +
                "    font-size: 16px;\n" +
                "    border-radius: 6px;\n" +
                "    cursor: pointer;\n" +
                "}\n" +
                "\n" +
                ".scaffold{\n" +
                "    position: relative;\n" +
                "    font-family: sans-serif;\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "    width: 100%;\n" +
                "    min-height: 100vh;\n" +
                "    background-color: aliceblue;\n" +
                "}\n" +
                "\n" +
                ".sized-box{\n" +
                "    width: 10px;\n" +
                "    height: 10px;\n" +
                "}\n" +
                "\n" +
                ".app-bar{\n" +
                "    position: absolute;\n" +
                "    top: 0;\n" +
                "    left: 0;\n" +
                "    width: 100%;\n" +
                "    height: 40px;\n" +
                "    display: flex;\n" +
                "    align-items: center;\n" +
                "    padding-left: 20px;\n" +
                "    padding-right: 20px;\n" +
                "}\n" +
                "\n" +
                ".app-bar-center{\n" +
                "    flex: 1;\n" +
                "    display: flex;\n" +
                "    justify-content: center;\n" +
                "    align-items: center;\n" +
                "}";
    }

    public static String initJsFileData() {
        return "function getData() {\n" +
                "    const pageHref = location.search;\n" +
                "    const searchParams = new URLSearchParams(pageHref.substring(pageHref.indexOf('?')));\n" +
                "    const data = JSON.parse(searchParams.get(\"data\"));\n" +
                "    if(data){\n" +
                "        console.log(data);\n" +
                "        for(let key in data){\n" +
                "            window[key] = data[key];\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "function handlePush ({pageName , ...data}) {\n" +
                "    window.location.href = `../${pageName}/${pageName}.html?data=${JSON.stringify(data)}`;\n" +
                "}\n" +
                "\n" +
                "function setCommonStyle({className , width , height , margin , padding}){\n" +
                "    const element = document.querySelector(`.${className}`);\n" +
                "    element.style.width = `${width}px`;\n" +
                "    element.style.height = `${height}px`;\n" +
                "    element.style.margin = `${margin}px`;\n" +
                "    element.style.padding = `${padding}px`;\n" +
                "}\n" +
                "\n" +
                "function setImageLink({className , link}){\n" +
                "    const image = document.querySelector(`.${className}`);\n" +
                "    image.setAttribute(\"src\" , `${link}`);\n" +
                "}\n" +
                "\n" +
                "function setTextAttr({className , text , fontSize}){\n" +
                "    const p = document.querySelector(`.${className}`);\n" +
                "    p.style.fontSize = `${fontSize}px`; \n" +
                "    p.innerHTML = \"\"; \n" +
                "    p.appendChild(document.createTextNode(`${text}`));\n" +
                "}\n" +
                "\n" +
                "function setButtonAttr({className , text , enabled , onPress}){\n" +
                "    const button = document.querySelector(`.${className}`);\n" +
                "    button.innerHTML = \"\";\n" +
                "    button.appendChild(document.createTextNode(`${text}`));\n" +
                "    button.disabled = !enabled;\n" +
                "    button.onclick = onPress;\n" +
                "}\n"
                + "getData();\n"
                ;
    }

    public static String initVariableData(String name, String value) {
        return "let " + name + " = " + value + ";\n";
    }

    public static String mutateVariableData(String name, String value) {
        return name + " = " + value + ";\n";
    }

    public static String functionSignatureData(String name, String parameters) {
        return "function " + name + "({" + parameters + "})";
    }

    public static String functionReturnData(String value) {
        return "return " + value + ";";
    }

    public static String functionData(String functionSignature, String body, String returnExp) {
        return functionSignature + "{ \n"
                + body + returnExp
                + "\n}\n";
    }

    public static String buildFunctionData(String functionSignature, String body) {
        return functionSignature + "{ \n"
                + body
                + "\n}\n"
                + "build({});\n";
    }

    public static String setCommonStyleCallData(String className, String width, String height, String margin, String padding) {
        return "setCommonStyle({className: \"" + className + "\" , width: " + width + ", height: " + height + ", margin: " + margin + ", padding: " + padding + "});\n";
    }

    public static String setImageLinkData(String className, String link) {
        return "setImageLink({className: \"" + className + "\" , link: " + link + "});\n";
    }

    public static String setTextAttrData(String className, String text, String fontSize) {
        return "setTextAttr({className: \"" + className + "\", text: " + text + ", fontSize: " + fontSize + "});\n";
    }

    public static String setButtonAttrData(String className, String text, String enabled, String onPress) {
        return "setButtonAttr({className:\"" + className + "\" , text: " + text + ", enabled:" + enabled + " , onPress: " + onPress + "});\n";
    }

    public static String printStatementData(String value) {
        return "console.log(" + value + ");\n";
    }

    public static String pushStatementData(String name, String data) {
        return "handlePush({pageName: \"" + name + "\" " + data + "});\n";
    }

    public static String popStatementData() {
        return "window.history.back();\n";
    }

    public static String stateStatementData() {
        return "build({});\n";
    }

    public static String ifStatementData(String condition, String body, String elseBody) {
        return "if(" + condition + ") {\n"
                + body + "}\n"
                + "else {\n"
                + elseBody + "}\n";
    }

    public static String whileStatementData(String condition, String body) {
        return "while(" + condition + "){\n"
                + body
                + "}\n";
    }

    public static String anonymousFunctionData(String body) {
        return "() => {" +
                body +
                "}";
    }
}

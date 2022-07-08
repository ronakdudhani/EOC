import java.util.ArrayList;
import java.util.Arrays;

public class Library {
    static ArrayList<String> keyword = new ArrayList<>(Arrays.asList(
            "class",
            "constructor",
            "int",
            "void",
            "char",
            "boolean",
            "do",
            "else",
            "false",
            "field",
            "function",
            "if",
            "let",
            "method",
            "null",
            "return",
            "static",
            "this",
            "true",
            "var",
            "while"));

    public static ArrayList<String> symbols = new ArrayList<>(Arrays.asList(
            "{",
            "}",
            "(",
            ")",
            "[",
            "]",
            ",",
            ".",
            ";",
            "+",
            "-",
            "*",
            "/",
            "&",
            "^",
            "|",
            "<",
            ">",
            "=",
            "~",
            """
                    \
                    """));

    public static String token(String token) {

        // if it is a digit
        if (token.matches("[0-9]+")) {
            return "integerConstant";
        }
        // if it is a symbol
        else if (symbols.contains(token)) {
            return "symbol";
        }
        // if it is a string
        else if (token.matches("\"[^\"\n]*\"")) {
            return "stringConstant";
        }
        // if it is a keyword
        else if (keyword.contains(token)) {
            return "keyword";
        }
        // if it is an identifier
        else if (token.matches("[\\w_]+")) {
            return "identifier";
        }
        return null;
    }

}

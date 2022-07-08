import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ParserAndCodeWriter extends Library {

    public void Parser(ArrayList<String> arr, String filename) throws IOException {

        String file = filename.substring(0, filename.indexOf("."));
        FileWriter fw = new FileWriter(file + ".xml");

        fw.append("<tokens>\n");

        for (String string : arr) {

            int length = string.length();
            String word = "";

            for (int i = 0; i < length; i++) {
                String s = Character.toString(string.charAt(i));
                //For String Constant
                if (s.equals("\"")) {
                    String some = s;
                    i++;
                    s = Character.toString(string.charAt(i));
                    while (!(s.equals("\""))) {
                        s = Character.toString(string.charAt(i));
                        i++;
                        some = some + s;
                    }
                    i--;
                    fw.append(CodeWriter(some));
                //To break word if symbol or space comes
                } else if (symbols.contains(s) || string.charAt(i) == ' ') {
                    if (word != "")
                        fw.append(CodeWriter(word));
                    if (symbols.contains(s)) {
                        fw.append(CodeWriter(s));
                    }
                    word = "";
                } else {
                    word = word + s;
                }
            }

        }
        fw.append("</tokens>");
        fw.close();

    }

    public String CodeWriter(String word) {

        String s = Character.toString(word.charAt(0));
        String token = token(word);
        //To remove " if word is String constant
        if (s.equals("\"")) {
            word = word.substring(1, word.length() - 1);
        }
        String out = "<" + token + "> " + word + " </" + token + ">\n";
        return out;
    }
}

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        ParserAndCodeWriter write = new ParserAndCodeWriter();
        String fileName = "Square.jack";
        write.Parser(reader.Reader(fileName), fileName);

    }

}

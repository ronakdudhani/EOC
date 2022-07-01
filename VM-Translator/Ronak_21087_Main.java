import java.io.IOException;

public class Ronak_21087_Main {

    public static void main(String[] args) throws IOException {
        
        Ronak_21087_ParserAndCodeWriter translator = new Ronak_21087_ParserAndCodeWriter();

        String FileName = "StackTest.vm";

        translator.codeWriter(translator.parser(FileName), FileName);


    }

}
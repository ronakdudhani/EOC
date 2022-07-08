package Question1.Assembler;

import java.io.IOException;

public class Ronak_87_Assembler {
    public static void main(String[] args) throws IOException {

        // Making new object of assembler
        Ronak_87_Method assembler = new Ronak_87_Method();
        // Removing Spaces
        assembler.remove( "./Question1/Input/RectL.asm", "./Question1/Output/no_space.asm");

        assembler.convert_to_binary("./Question1/Output/no_space.asm", "./Question1/Output/binary/RectL.hack");
    }
}

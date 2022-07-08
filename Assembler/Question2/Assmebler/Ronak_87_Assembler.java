package Question2.Assmebler;
import java.io.IOException;

public class Ronak_87_Assembler {
    public static void main(String[] args) throws IOException {

        // Making new object of assembler
        Ronak_87_Methods assembler = new Ronak_87_Methods();
        
        // Removing Spaces
        assembler.remove( "./Question2/Input/Pong.asm", "./Question2/Output/no_space.asm");
        assembler.generate_symbol_table();
        assembler.make_label_table("./Question2/Output/no_space.asm");
        assembler.remove_label("./Question2/Output/no_space.asm", "./Question2/Output/no_label.asm");
        assembler.make_variable_table("./Question2/Output/no_space.asm");
        assembler.replace_symbols("./Question2/Output/no_label.asm", "./Question2/Output/convert.asm");
        assembler.convert_to_binary("./Question2/Output/convert.asm", "./Question2/Output/binary/Pong.hack");
    }
}

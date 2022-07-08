package Question2.Assmebler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Ronak_87_Methods {

    public void remove(String file_input, String file_output) throws IOException{
        // removes the whitespaces and comment

        File file = new File( file_input);
        FileReader read = new FileReader(file);
        BufferedReader buffer = new BufferedReader(read);
        FileWriter write = new FileWriter(file_output);
        String line;
        
        while((line = buffer.readLine()) != null){
            if(!(line.isEmpty())){
                // removing comments
                if(!(line.charAt(0) == '/')){
                    String[] splitline = line.split("/");
                    StringBuilder nline = new StringBuilder();
                    // removing spaces
                    for( int i = 0; i < splitline[0].length();i++){
                        if(splitline[0].charAt(i) != ' '){
                            nline.append(splitline[0].charAt(i));
                        }   
                    }
                    write.write(nline.toString());
                    write.write("\n");
                }
            }
        }
        buffer.close();
        write.close();
    }
    HashMap<String, Integer> Symbol_table = new HashMap<>();
    void print() {
        System.out.println(Symbol_table);
        
        }

    public void generate_symbol_table() {
        String [] pre_defined_symbol = new String[] {"R0=0","R1=1","R2=2","R3=3","R4=4","R5=5","R6=6","R7=7","R8=8",
        "R9=9","R10=10","R11=11","R12=12","R13=13","R14=14","R15=15","SCREEN=16384","KBD=24576","SP=0","LCL=1","ARG=2","THIS=3",
        "THAT=4"};

        for (int i=0;i < pre_defined_symbol.length;i++) 
        {
            String x = pre_defined_symbol[i].split("=")[0];
            int y =  Integer.parseInt(pre_defined_symbol[i].split("=")[1]);
            Symbol_table.put(x, y);
        }       
    }
    // Calculating the positions of labels 

    void make_label_table(String input) throws IOException {

        File fp = new File(input);
        FileReader file = new FileReader(fp);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line;
        // line no of label
        int lineno = 0;
        while((line = bufferedReader.readLine()) != null) { 
            
            if(line.charAt(0) == '(' ) {
                String label_name;

                //recording label name in table with its line no 
                label_name =  line.substring(1, line.length()-1);
                Symbol_table.put(label_name, lineno);
                lineno = lineno-1;
                }
                lineno += 1;

            }       
                    
              
        bufferedReader.close();
        file.close();

    }
    // putting variable into symbol table
    void make_variable_table(String input_file_name) throws IOException {
        File new_file = new File(input_file_name);
        FileReader fr = new FileReader(new_file);
        BufferedReader br = new BufferedReader(fr);
    
        String line;
        int count = 16;
        while((line = br.readLine()) != null) {
            if(line.charAt(0) == '@') {
    
                String a = line.split("@")[1];
                if(!Symbol_table.containsKey(a) && !Character.isDigit(a.charAt(0)) ) {
                    Symbol_table.put(a, count);
                    count += 1;
                }
            }
        }
        br.close();
        fr.close();
    
    }
    //removing labels
    public void remove_label(String input, String output) throws IOException {
        File  no_space_file = new File(input);
        FileReader file = new FileReader(no_space_file);
        BufferedReader buffer = new BufferedReader(file);
        FileWriter write = new FileWriter(output);

        String line;
        // ignoring the lines staring with ()
        while((line = buffer.readLine()) != null) {
            if(!(line.charAt(0) == '(') ) {

                write.write(line);
                write.write("\n");
            }                       
        }
        buffer.close();
        file.close();
        write.close();
        
    } 
    // replacing symbols with their address using symbol table
     public void replace_symbols(String input, String output) throws IOException {
        File  no_space_file = new File(input);
            FileReader file = new FileReader(no_space_file);
            BufferedReader buffer = new BufferedReader(file);
            FileWriter write = new FileWriter(output);
           String line,x;

    
            while((line = buffer.readLine()) != null) {
                if((line.charAt(0) == '@') ) {
                
                    x = line.substring(1, line.length());
                    if( Symbol_table.containsKey(x) ) {
                        write.write("@" + Symbol_table.get(x));
                        write.write("\n");
                    }
                    else{
                        write.write(line);
                        write.write("\n");
                    }
    
                } 
                else{
                    write.write(line);
                    write.write("\n");
                }          
                        
    
            }
            buffer.close();
            file.close();
            write.close();
    
    }

    public void convert_to_binary(String input, String output) throws IOException {

        File  asmFile = new File(input);
        FileReader file = new FileReader(asmFile);
        BufferedReader buffer = new BufferedReader(file);
        FileWriter write2 = new FileWriter(output);
        String line;
        while((line = buffer.readLine()) != null) {
            write2.write( get_binary(line) );
            write2.write("\n");
        }
        buffer.close();
        write2.close();

    }

    public String C(String a)  {
        String [] op1 = new String[]{"null=0000000","0=0101010","1=0111111","-1=0111010","D=0001100","A=0110000","!D=0001101","!A=0110011",
        "D+1=0011111","A+1=0110111","D-1=0001110","A-1=0110010","D+A=0000010","D-A=0010011","A-D=0000111","D&A=0000000",
        "D|A=0010101","M=1110000", "!M=1110001","M+1=1110111","M-1=1110010","D+M=1000010","D-M=1010011","M-D=1000111",
        "D&M=1000000","D|M=1010101", "-D=0001111" , "-A=0110011", "-M=1110011"};

        HashMap <String,String> Ctable = new HashMap <String,String>();

        for (int i=0 ; i< op1.length;i++)
        {
            String x = op1[i].split("=")[0];
            String y = op1[i].split("=")[1];
            Ctable.put(x, y);
        }

           return Ctable.get(a);
        }

        public String D( String a )
        {
            String[] dest = new String[]{"null=000","M=001","D=010","MD=011","A=100","AM=101","AD=110","AMD=111"};
            HashMap <String,String> Dtable = new HashMap <String,String>();
            for (int i=0 ; i< dest.length;i++)
        {
            String x = dest[i].split("=")[0];
            String y = dest[i].split("=")[1];
            Dtable.put(x, y);
        }
          return Dtable.get(a);
        }
        public String J( String a )
        {
            String[] jmp = new String[]{"null=000","JGT=001","JEQ=010","JGE=011","JLT=100","JNE=101","JLE=110","JMP=111"};
            HashMap <String,String> Jtable = new HashMap <String,String>();
            for (int i=0 ; i< jmp.length;i++)
        {
            String x = jmp[i].split("=")[0];
            String y = jmp[i].split("=")[1];
            Jtable.put(x, y);
        }
          return Jtable.get(a);

        }
        public String get_binary(String line){

            String binary = "";
    
            if(line.charAt(0) == '@' ) {
                
                binary = A_binary(line.substring(1));
    
            }
            else {
                binary = C_binary(line);
            }
    
            return binary;
        }
        public String C_binary(String line) {

            String dest = "null";
            String comp = "null";
            String jump = "null";
    
            //Jump Statement
            if(line.indexOf("=") == -1) {
                String[] arr = line.split(";");
                comp = arr[0];
                jump = arr[1];
            }
            //Computational Statement
            else if(line.indexOf(";") == -1 ){
                String[] arr = line.split("=");
                dest = arr[0];
                comp = arr[1];     
            }
            // Case if there is both 
            else {
                String[] arr = line.split("=");
                dest = arr[0];
                String[] arr2 = arr[1].split(";");
                comp = arr2[0];
                jump = arr2[1];
             }
             
            return "111" +  C(comp) + D(dest) + J(jump); 
    
        }
        public String A_binary (String line) {

            String binary = Integer.toBinaryString(Integer.parseInt(line));
    
            int extra_zeroes = 16-binary.length();
    
            for(int i = 0; i < extra_zeroes; i++) {
                binary = 0 + binary;
            }
    
            return binary;
        }
    
}

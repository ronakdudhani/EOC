package Question1.Assembler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Ronak_87_Method {

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
    
    public void convert_to_binary(String input_file_name, String output_file_name) throws IOException {

        File  asmFile = new File(input_file_name);
        FileReader fre = new FileReader(asmFile);
        BufferedReader br = new BufferedReader(fre);
        FileWriter write2 = new FileWriter(output_file_name);
    
        String line;
    
    
        while((line = br.readLine()) != null) {
            write2.write( get_binary(line) );
            write2.write("\n");
        }
        br.close();
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

            String eq_binary = "";
    
            if(line.charAt(0) == '@' ) {
                
                eq_binary = A_binary(line.substring(1));
    
            }
            else {
                eq_binary = C_binary(line);
            }
    
            return eq_binary;
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

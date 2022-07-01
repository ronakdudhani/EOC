public class Ronak_21087_Translator {

    static int counter = 0;

    public String ch_operation(String line[]){
        String convert ="";
        //label and end for if else condition
        //counter to make every label name different, so it doesn't jump to previous labels
        String lab = "label."+counter;
        String end = "End."+counter;

        //Pops the top element and stores it in D registor And selects the 2nd element
        String pop = """
                @SP
                AM=M-1
                D=M
                @SP
                AM=M-1
                """;
        // Increase the stack pointer
        String inc = """
                @SP
                M=M+1
                """;
        //Decrease the stack pointer
        String dec ="""
                @SP
                AM=M-1
                """;

        //Code for IF true part of boolean operation
        String tr="\n("+lab+")"+"""
                \n@SP
                A=M
                M=-1
                """+"("+end+")\n"+inc;
        //Code for If false part of boolean operation
        String fal ="""
                \n@SP
                A=M
                M=0
                """
                +"@"+end+"\n0;JMP";

        switch(line[0]){
             //Adds the top 2 element
            case "add" -> convert = pop + "M=M+D\n"+inc;
            //subtracts the top 2 element
            case "sub" -> convert = pop + "M=M-D\n"+inc;
            //negates the top element
            case "neg" -> convert = dec + "M=-M\n"+inc;
            //and the top two element
            case "and" -> convert = pop+"M=D&M\n"+inc;
            //or the top two element
            case "or" -> convert = pop+"M=D|M\n"+inc;
            //not the top element
            case "not" -> convert = dec + "M=!M\n"+inc;
            //return boolean value of (a==b)
            case "eq" ->{convert = pop+"D=M-D\n@"+lab+"\nD;JEQ"+fal+tr;
                        counter++;
            }
            //return boolean value of (a<b)
            case "lt" ->{convert = pop+"D=M-D\n@"+lab+"\nD;JLT"+fal+tr;
            counter++;
            }
            //return boolean value of (a>b)
             case "gt" ->{convert = pop+"D=M-D\n@"+lab+"\nD;JGT"+fal+tr;
                        counter++;
            }
        }
        return convert;
    }

    public String ch_Command(String line[],String filename){  
        String file = filename.substring(0, filename.indexOf("."));
        //code for @i
        String index = "@"+line[2]+"\nD=A\n";
        //general code for push
        String push ="""
                @SP
                A=M
                M=D
                @SP
                M=M+1
                """;
        // general code for pop using general purpose registors
        String pop = """
                @R13
                M=D
                @SP
                AM=M-1
                D=M
                @R13
                A=M
                M=D
                """;
        // code for sp--
        String dec_sp = """
                @SP
                AM=M-1
                D=M
                """;
        String convert = "";
        switch(line[0]){
            case "push":
            //Handles push segment i
            switch(line[1]){
                //pushing segment i into stack
                case "local" ->convert = index +"@LCL\nA=D+M\nD=M\n"+push;
                case "argument" -> convert = index +"@ARG\nA=D+M\nD=M\n"+push;
                case "this" -> convert = index +"@THIS\nA=D+M\nD=M\n"+push;
                case "that" -> convert = index +"@THAT\nA=D+M\nD=M\n"+push;
                case "temp" -> convert = index +"@5\nA=D+A\nD=M\n"+push;
                case "constant" -> convert = index + push;
                case "pointer" ->{
                    switch(line[2]){
                        case "0" -> convert = "@THIS\nD=M\n"+push;
                        case "1" -> convert = "@THAT\nD=M\n"+push;
                    }
                }
                case "static" -> convert = "@"+file+"."+line[2]+"\nD=M\n"+push;
            }
            break;
            case "pop":
            // Handles pop segment i
            switch(line[1]){
                //poping from stack and pushing it into segment i
                case "local" -> convert = index +"@LCL\nD=D+M\n"+pop;
                case "argument" -> convert = index +"@ARG\nD=D+M\n"+pop;
                case "this" -> convert = index +"@THIS\nD=D+M\n"+pop;
                case "that" -> convert = index +"@THAT\nD=D+M\n"+pop;
                case "temp" -> convert = index +"@5\nD=D+A\n"+pop;
                case "pointer" ->{
                    switch(line[2]){
                        case "0" -> convert = dec_sp+"@THIS\nM=D\n";
                        case "1" -> convert = dec_sp+"@THAT\nM=D\n";
                    }
                }
                case "static" -> convert = dec_sp+"@"+file+"."+line[2]+"\nM=D\n";
            }
        }

        return convert;

    }
}

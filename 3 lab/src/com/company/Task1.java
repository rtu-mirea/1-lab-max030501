package com.company;


public class Task1 {
    protected static String txt;
    Task1(){
        txt = "package com.company;\n" +
                "\n" +
                "public class Main {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "\tint a = 345;\n" +
                "\tfloat b = 0x15AB;\n" +
                "\tdouble c = 02345;\n" +
                "\tint d = 0b01001;\n" +
                "\tSystem.out.println(a+b);\n" +
                "    }\n" +
                "}\n";
    }
    Task1(String text){
        txt = text;
    }
    public String outputVar(){
        char[] buffArr = txt.toCharArray();
        String res = "";
        String [] buffVar = new String[]{"\tint ", "\tfloat ", "\tdouble ", "\tlong "};
        int indexSt;
        int indexEnd;
        for(int i = 0; i< buffVar.length; i++) {
            indexSt = 0;
            while (txt.indexOf(buffVar[i], indexSt) != -1) {
                indexSt = txt.indexOf(buffVar[i],indexSt);
                indexEnd = indexSt + buffVar[i].length();
                while (buffArr[indexEnd] >= '0' & buffArr[indexEnd] <= '9' | buffArr[indexEnd] >= 'a' & buffArr[indexEnd] <= 'z' | buffArr[indexEnd] >= 'A' & buffArr[indexEnd] <= 'Z' | buffArr[indexEnd] == '_') {
                    indexEnd++;
                }
                res += txt.substring(indexSt, indexEnd) + " "+";";
                indexSt++;
            }
        }
        return res;
    }
    public String outputOper(){
        char buffArr[] = txt.toCharArray();
        String oper = "=";
        String res = "";
        String buffer = "";
        int indexSt;
        String[] buffVar = outputVar().split(";");
        for(int i = 0; i < buffVar.length; i++){
            buffer = "";
            indexSt = txt.indexOf(buffVar[i]) + buffVar[i].length();
            while(oper.contains(buffer)){
                buffer+= buffArr[indexSt];
                indexSt++;
            }
            res+=buffer.substring(0,buffer.length()-1) + " " + ";";
        }
        return res;
    }
    public String maxVal(){
        int max = -1;
        String maxType = "Null";
        String buffType;
        char[] buffArr;
        String buffer;
        int buffCounter;
        int cs;
        int indexSt;
        int indexEnd;
        String[] buffOper = outputOper().split(";");
        String[] buffVar = outputVar().split(";");
        for(int i = 0; i < buffVar.length; i++){
            buffer = "";
            buffType = buffVar[i].substring(buffVar[i].lastIndexOf("\t")+1, buffVar[i].indexOf(" "));
            indexSt = txt.indexOf(buffVar[i]) + buffVar[i].length() +buffOper[i].length();

            indexEnd = txt.indexOf(";",indexSt)+1;
            buffArr= txt.substring(indexSt,indexEnd).toCharArray();
            buffCounter=0;
            if(buffArr[0] == '0' )
                switch (buffArr[1]){
                    case 'x':
                        cs=16;
                        buffCounter = 2;
                        break;
                    case 'b':
                        cs=2;
                        buffCounter = 2;
                        break;
                    default:
                        cs=8;
                        buffCounter = 1;
                        break;
                }
            else cs = 10;
            while(buffArr[buffCounter] != ';'){
                buffer+=buffArr[buffCounter];
                buffCounter++;
            }
            if(Integer.parseInt(buffer,cs) > max) {
                max = Integer.parseInt(buffer, cs);
                maxType = buffType;


            }


        }
        return max+ " " + maxType;
    }
}

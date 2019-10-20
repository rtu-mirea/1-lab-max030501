package com.company;
import java.lang.StringBuilder;
import java.lang.StringBuffer;

public class Task2 extends Task1{

    Task2(){
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
    Task2(String text){
        txt = text;
    }


public String oper() {
    int indexSt = 0;
    String[] buffOper = outputOper().split(";");
    StringBuffer sb = new StringBuffer(txt);
    for (int i = 0; i < buffOper.length; i++) {
        sb.delete(indexSt, sb.indexOf(buffOper[i],indexSt));
        indexSt += buffOper[i].length();
    }
    sb.delete(indexSt, sb.length());
    return sb.toString();
}
public String sum() {
    StringBuffer res = new StringBuffer(txt);
    String[] buffVar = outputVar().split(";");
    int indexSt = 0;
    int indexMax= -1;
    String buffer =buffVar[0].substring(0,buffVar[0].lastIndexOf("\t")+1) + buffVar[0].substring(buffVar[0].indexOf(" ")+1) + "= ";
    for(int i = 0; i < buffVar.length; i++){
        if (txt.indexOf(buffVar[i])> indexMax)
            indexMax = txt.indexOf(buffVar[i]);
    }
    indexMax = txt.indexOf("\n",indexMax)+1;
    for (int i = 0; i < buffVar.length; i++) {
        indexSt = txt.indexOf(buffVar[i]) + buffVar[i].length() + outputOper().split(";")[i].length();
        buffer += txt.substring(indexSt, txt.indexOf(";",indexSt));
        if(i!= buffVar.length - 1)
            buffer+= " + ";
    }
    res.insert(indexMax,buffer+";\n");

    return res.toString();
}
public String oper2(){
        StringBuffer res = new StringBuffer(txt);
        int indexSt = txt.indexOf(outputVar().split(";")[0]);
        indexSt = txt.indexOf(";", indexSt);
        res.insert(indexSt, "*2");
        return res.toString();

}

}

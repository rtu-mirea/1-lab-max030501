package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    String text, text1;
    Task3(){
        text = "0xABC3267F";
        text1="This This is text text";
    }
    Task3(String text, String text1){
        this.text = text;
        this.text1 = text1;
    }
    public boolean check16() {
        Pattern pattern = Pattern.compile("^0x(\\d|[a-fA-F]){1,8}$");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public String delete() {
        Pattern pattern = Pattern.compile("\\b([a-z]+)\\b(?:\\s+\\1\\b)+");
        Matcher matcher = pattern.matcher(text1);
        String res = "";
        if (!matcher.find())
            return text1;
        else {
            do{
                if (res == "")
                    res = text1.replaceFirst(matcher.group(), matcher.group(1));
                else
                    res = res.replaceFirst(matcher.group(), matcher.group(1));
            }
            while (matcher.find());
        }

        return res;
    }
}

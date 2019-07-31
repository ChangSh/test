package com.chang.own.algorithm;

/**
 * @author: changsh
 * @date: 2019/2/15 0015
 **/

/*请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。*/

public class ReplaceBlank {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("we are happy");
        String s = sb.toString();
        System.out.println(s.replaceAll(" ", "%20"));
    }
}

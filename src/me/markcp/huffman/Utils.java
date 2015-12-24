package me.markcp.huffman;

/**
 * Created by Mark on 12/23/2015.
 */
public class Utils {

    public static void msg(String input){
        System.out.println("[MCompress]: " + input);
    }

    public static char[] mergeArr(char[] a, char[] b) {
        if (a==null) return b;
        if (b==null) return a;
        int aLen = a.length;
        int bLen = b.length;
        char[] c= new char[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

}

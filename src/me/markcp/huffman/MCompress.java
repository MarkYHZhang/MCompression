package me.markcp.huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class MCompress {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Utils.msg("compress <inputFile> <outputFile> OR decompress <inputFile> <outputFile>");
        String argument = br.readLine();
        if (argument.replace(" ", "").equalsIgnoreCase("")){
            Utils.msg("Invalid Input");
            return;
        }
        String[] input = argument.split(" ");
        if (input.length!=3) {
            Utils.msg("Invalid Input");
            return;
        }
        String option = input[0];
        String fileName = input[1];
        String result = input[2];
        if (option.equalsIgnoreCase("compress")){
            File file = new File(fileName+".txt");
            if (file.exists()){
                new Compress(file, result).compress();
            }else{
                Utils.msg("File does not exist! Program shall terminate!");
                System.exit(0);
            }
        }else if(option.equalsIgnoreCase("decompress")){
            File file = new File(fileName+".mco");
            if (file.exists()){
                new Decompress(file, result).decompress();
            }else{
                Utils.msg("File does not exist! Program shall terminate!");
                System.exit(0);
            }
        }else{
            Utils.msg("Invalid arguments: Program shall terminate!");
            System.exit(0);
        }
    }

}
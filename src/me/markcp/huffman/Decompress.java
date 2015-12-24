package me.markcp.huffman;

import java.io.*;

/**
 * Created by Mark on 12/23/2015.
 */
public class Decompress {

    private File file;
    private String output;

    public Decompress(File file, String output){
        this.file = file;
        this.output = output;
    }

    public void decompress()throws Exception{
        if (file.canRead()){
            BufferedReader br = new BufferedReader(new FileReader(file));
            int count = Integer.parseInt(br.readLine());
            char[] letter = new char [count];
            String[] code = new String [count];
            for (int i = 0; i < count; i++) {
                String line = br.readLine();
                char c = line.charAt(0);
                String b = line.substring(1);
                letter[i] = c;
                code[i] = b;
            }
            String answer = "";
            String binary = br.readLine();
            while (binary!=null){
                while (binary.length () > 0)
                {
                    int i = 0;
                    while (!binary.startsWith (code [i]))
                        i++;
                    answer = answer + letter [i];
                    binary = binary.substring (code [i].length ());
                }
                answer=answer+System.lineSeparator();
                binary = br.readLine();
            }
            File compressedFile = new File(output+".txt");
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(compressedFile)));
            out.println(answer);
            out.close();
        }else{
            Utils.msg("File not readable: Program shall terminate!");
            System.exit(0);
        }
    }

}

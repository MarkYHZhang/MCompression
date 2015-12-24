package me.markcp.huffman;

import java.io.*;
import java.util.*;

/**
 * Created by Mark on 12/23/2015.
 */
public class Compress {

    private File file;
    private String output;
    private Map<Character, String> codes;

    public Compress(File file, String output){
        this.file = file;
        this.output = output;
        codes = new HashMap<Character, String>();
    }

    public void compress()throws Exception{
        if (file.canRead()){
            HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            if (line==null){
                Utils.msg("Empty file! Program terminated!");
                return;
            }
            char[] data = null;
            List<Integer> newLine = new ArrayList<Integer>();
            while (line!=null){
                if (data!=null){
                    newLine.add(data.length);
                }
                data=Utils.mergeArr(data, line.toCharArray());
                line=br.readLine();
            }
            for (char val : data) {
                if (dict.containsKey(val)) {
                    dict.put(val, dict.get(val) + 1);
                } else {
                    dict.put(val, 1);
                }
            }
            List<Node> nodes = new ArrayList<Node>();
            Map<Node, Boolean> visited = new HashMap<Node, Boolean>();
            for (Character c : dict.keySet()) {
                Node node = new Node(true, null, null, c, dict.get(c));
                nodes.add(node);
                visited.put(node, false);
            }
            while (true) {
                Node left = null, leftRe = null;
                Node right = null, rightRe = null;
                int leftFre = Integer.MAX_VALUE, rightFre = Integer.MAX_VALUE;
                for (Node tmpNode : nodes) {
                    if (!visited.get(tmpNode)) {
                        int tmpL = leftFre;
                        leftFre = Math.min(tmpNode.getFreq(), leftFre);
                        if (leftFre != tmpL) {
                            left = tmpNode.clone();
                            leftRe = tmpNode;
                        }
                    }
                }

                nodes.remove(leftRe);
                visited.remove(leftRe);
                visited.put(left, true);

                for (Node tmpNode : nodes) {
                    if (!visited.get(tmpNode) && !tmpNode.equals(left)) {
                        int tmpR = rightFre;
                        rightFre = Math.min(tmpNode.getFreq(), rightFre);
                        if (rightFre != tmpR) {
                            right = tmpNode.clone();
                            rightRe = tmpNode;
                        }
                    }
                }
                nodes.remove(rightRe);
                visited.remove(rightRe);
                visited.put(right, true);

                boolean shouldBreak = false;
                boolean leftNull = false;
                boolean rightNull = false;
                Node current = null;
                if (left == null && right == null) {
                    break;
                } else if (left == null) {
                    shouldBreak = true;
                    leftNull = true;
                } else if (right == null) {
                    shouldBreak = true;
                    rightNull = true;
                } else {
                    current = new Node(false, left, right, '~', leftFre + rightFre);
                }
                if (current != null) {
                    nodes.add(current);
                    visited.put(current, false);
                }
                if (!leftNull) {
                    nodes.add(left);
                }
                if (!rightNull) {
                    nodes.add(right);
                }
                if (shouldBreak) break;
            }
            Node topNode = null;
            int fre = -1;
            for (Node node : nodes) {
                fre = Math.max(node.getFreq(), fre);
                if (fre == node.getFreq()) {
                    topNode = node.clone();
                }
            }

            encode(topNode, "");

            File compressedFile = new File(output+".mco");
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(compressedFile)));
            out.println(codes.size());
            for (Character c : codes.keySet()) {
                out.println(c+codes.get(c));
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.length; i++) {
                if (newLine.contains(i)) sb.append(System.lineSeparator());
                Character c = data[i];
                sb.append(codes.get(c));
            }
            out.write(sb.toString());
            out.close();
        }else{
            Utils.msg("File not readable: Program shall terminate!");
            System.exit(0);
        }
    }

    private void encode(Node node, String code){
        if (node.getLeft()==null && node.getRight()==null){
            codes.put(node.getVal(), code);
        }
        if (node.getRight()!=null){
            encode(node.getRight(), code+"1");
        }
        if (node.getLeft()!=null){
            encode(node.getLeft(), code+"0");
        }
    }

}

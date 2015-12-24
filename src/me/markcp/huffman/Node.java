package me.markcp.huffman;

/**
 * Created by Mark on 12/23/2015.
 */
public class Node implements Cloneable{
    private boolean isBase;
    private char val;
    private int freq;
    private Node left, right;
    public Node(boolean isBase, Node left, Node right, char val, int freq){
        this.isBase = isBase;
        if (!isBase){
            this.left = left;
            this.right = right;
            if (left==null){
                this.freq=right.freq;
            }else if (right==null){
                this.freq=left.freq;
            }else{
                this.freq = left.freq+right.freq;
            }
        }else{
            this.val = val;
            this.freq = freq;
        }
    }

    public boolean isBase() {
        return isBase;
    }

    public void setBase(boolean base) {
        isBase = base;
    }

    public char getVal() {
        return val;
    }

    public void setVal(char val) {
        this.val = val;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "isBase=" + isBase +
                ", val=" + val +
                ", freq=" + freq +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    protected Node clone() throws CloneNotSupportedException {
        return (Node) super.clone();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Node)) {
            return false;
        }

        Node that = (Node) other;

        // Custom equality check here.
        return this.isBase==that.isBase
                && this.val==that.val
                && this.freq==that.freq
                && this.left==that.left
                && this.right==that.right;
    }
}

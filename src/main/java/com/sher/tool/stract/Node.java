package com.sher.tool.stract;

/**
 * Created by Administrator on 2016/10/18.
 */
public class Node {

    private Object obj;

    Node(Object obj){
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    class BinaryNode{
        private int size;
        private Node leftNode;
        private Node rightNode;

        public void add(Node node){
            if(node == null){
                return ;
            }
            BinaryNode n1 = loop(this);
            n1.leftNode = node;
        }

        public BinaryNode loop(BinaryNode node){
            if(node.leftNode != null){
                loop(node);
            }
            return node;
        }

        public void add(int[] arr){
            if(size == 0){

            }
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }
    }

    class BinaryTree{
        private BinaryNode binaryNode;
        private int index = 0;
        private int modCount = 0;
        private int defaultSize = 16;

    }


    public static void main(String args[]){
        test();
    }

    public static void test(){
        int arr[] = {4,7,1,3,5,8,9,2};

    }
}

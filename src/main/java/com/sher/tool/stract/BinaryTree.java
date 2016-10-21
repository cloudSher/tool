package com.sher.tool.stract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */
public class BinaryTree {

    private List<Node> list = new ArrayList<>();


    public void createTree(){
        
    }



    class Node{
        private Node left;
        private Node right;
        private Object data;

        Node(Object data){
            this.data = data;
            left = null;
            right = null;
        }

    }
}

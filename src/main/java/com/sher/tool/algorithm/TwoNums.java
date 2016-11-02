package com.sher.tool.algorithm;

/**
 * Created by Administrator on 2016/10/31.
 */
public class TwoNums {


    /**
     * two list add
     * @param node1  2-> 4 -> 3
     * @param node2  5-> 6 -> 4
     * @return
     */
    public static ListNode addTwoNums(ListNode node1,ListNode node2){
        ListNode head = null;
        ListNode tail = null;
        int radix = 0;
        while(node1!= null && node2 != null){
            int sum = node1.diget +  node2.diget;
            if(radix > 0){
                sum = sum + radix;
                radix = 0;
            }
            if(sum > 9){
                radix++;
                sum = sum - 10;
            }
            if(head == null){
                head = new ListNode(sum);
                tail = head;
            }else{
                tail.next = new ListNode(sum);
                tail = tail.next;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return head;
    }

    public static void main(String args[]){
        ListNode n1 = new ListNode(9);
        n1.next = new ListNode(4);
        n1.next.next = new ListNode(3);
        ListNode n2 = new ListNode(5);
        n2.next = new ListNode(6);
        n2.next.next = new ListNode(4);
        ListNode n3 = new ListNode(5);
        println(addTwoNums(n3,n3));
    }

    static class ListNode{
        public int diget;
        public ListNode next;
        ListNode(int n){
            this.diget = n;
        }
    }

    public static void println(ListNode n1){
        if(n1 != null){
            while(n1!=null){
                System.out.println(n1.diget);
                n1 = n1.next;
            }
        }
    }


}

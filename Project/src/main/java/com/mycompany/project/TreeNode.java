/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.project;

/**
 *
 * @author 90554
 */
public class TreeNode {
 String data;
    LinkedList list;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(String data) {
        this.data = data;
        this.list = new LinkedList();
        this.left = null;
        this.right = null;
    }
    
    public int getTotalCount() {
        ListNode current = list.head;
        int count = 0;
        while (current != null) {
            count += current.wordCount;
            current = current.next;
        }
        return count;
    }
    
}

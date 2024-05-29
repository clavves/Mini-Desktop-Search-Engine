/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

/**
 *
 * @author 90554
 */
public class LinkedList {

    ListNode head;

    void add(String fileName) {
        if (search(fileName) == null) {
            ListNode newNode = new ListNode(fileName);

            if (head == null) {
                head = newNode;
            } else {
                ListNode temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }

                temp.next = newNode;
            }
        } else {
            ListNode current = search(fileName);
            current.wordCount++;
        }
    }

    public ListNode search(String fileName) {
        ListNode current = head;

        while (current != null) {
            if (current.fileName.equals(fileName)) {
                return current;
            } else {
                current = current.next;
            }
        }

        return null;
    }

}

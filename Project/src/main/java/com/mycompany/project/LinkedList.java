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

    Sözcü_Cücük_ListNode head;

    void add(String fileName) {
        if (search(fileName) == null) {
            Sözcü_Cücük_ListNode newNode = new Sözcü_Cücük_ListNode(fileName);

            if (head == null) {
                head = newNode;
            } else {
                Sözcü_Cücük_ListNode temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }

                temp.next = newNode;
            }
        } else {
            Sözcü_Cücük_ListNode current = search(fileName);
            current.wordCount++;
        }
    }

    public Sözcü_Cücük_ListNode search(String fileName) {
        Sözcü_Cücük_ListNode current = head;

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

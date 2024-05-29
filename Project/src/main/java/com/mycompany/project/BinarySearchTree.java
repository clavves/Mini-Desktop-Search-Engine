/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;



/**
 *
 * @author 90554
 */
public class BinarySearchTree {

   TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    void insert(String word, String fileName) {
        if (search(word) == null) {
            TreeNode newNode = new TreeNode(word);

            if (root == null) {
                root = newNode;
                root.list.add(fileName);
            } else {
                TreeNode current = root;
                TreeNode parent;
                while (true) {
                    parent = current;
                    if (word.compareTo(current.data) < 0) {
                        current = current.left;
                        if (current == null) {
                            parent.left = newNode;
                            newNode.list.add(fileName);
                            return;
                        }
                    } else {
                        current = current.right;
                        if (current == null) {
                            parent.right = newNode;
                            newNode.list.add(fileName);
                            return;
                        }
                    }
                }
            }
        } else {
            TreeNode current = search(word);
            current.list.add(fileName);
        }
    }

    public TreeNode search(String word) {

        TreeNode current = root;

        while (current != null) {
            if (current.data.equals(word)) {
                return current;
            }

            if (word.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public void clear() {
        clearTree(root);
        root = null;
    }

    public void clearTree(TreeNode node) {
        if (node == null) {
            return;
        }

        clearTree(node.left);
        clearTree(node.right);

    node=null;
}

    
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author 90554
 */
public class MiniDesktopSearchEngine extends javax.swing.JFrame {

    public static File[] fileArray;
    public static File[] ignoredArray;
    ArrayList<String> ignoredWordsList = new ArrayList<>();

    Sözcü_Cücük_BinarySearchTree tree = new Sözcü_Cücük_BinarySearchTree();
    private DefaultListModel<String> fileListModel = new DefaultListModel();
    private DefaultListModel<String> ignoredListModel = new DefaultListModel();
    private DefaultListModel<String> wordListModel = new DefaultListModel();
    private DefaultListModel<String> searchListModel = new DefaultListModel();

    public MiniDesktopSearchEngine() {
        initComponents();

        fileList.setModel(fileListModel);
        wordsList.setModel(wordListModel);
        searchList.setModel(searchListModel);
        ignoredList.setModel(ignoredListModel);

        fileArray = new File[100];

        tree = new Sözcü_Cücük_BinarySearchTree();

        ButtonGroup group = new ButtonGroup();
        group.add(yesRadioBttn);
        group.add(noRadioBttn);

        chooseFileBttn.setVisible(false);
        fileList.setVisible(false);

        chooseIgnoredBttn.setVisible(false);
        ignoredList.setVisible(false);

        startBttn.setVisible(false);
        overBttn.setVisible(false);

        preBttn.setVisible(false);
        inBttn.setVisible(false);
        postBttn.setVisible(false);
        wordsList.setVisible(false);

        searchTextField.setVisible(false);
        searchBttn.setVisible(false);
        searchList.setVisible(false);

        resetBttn.setVisible(false);

    }

    private void processFiles() {
        for (File file : fileArray) {
            try ( BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {

                    line = line.replaceAll("<[^>]+>", "");

                    String[] words = line.split("\\s+"); 
                    for (String word : words) {

                        word = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
                        if (!word.isEmpty()) {
                            boolean ignoreWord = false;
                            if (ignoredWordsList != null) {
                                for (String ignoredword : ignoredWordsList) {
                                    if (word.equals(ignoredword)) {
                                        ignoreWord = true;
                                        break;
                                    }
                                }
                            }
                            if (!ignoreWord) {
                                tree.insert(word, file.getName());
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void continueProgram() {
        chooseFileBttn.setVisible(true);
        fileList.setVisible(true);

        if (yesRadioBttn.isSelected()) {
            chooseIgnoredBttn.setVisible(true);
            ignoredList.setVisible(true);
        }

        startBttn.setVisible(true);
        overBttn.setVisible(true);

        questionLabel.setVisible(false);
        yesRadioBttn.setVisible(false);
        noRadioBttn.setVisible(false);
        continueBttn.setVisible(false);
    }

    void over() {
        
        tree.clear();
        ignoredWordsList.clear();
        fileArray = null;
        ignoredArray = null;
        
        chooseFileBttn.setVisible(false);
        fileListModel.clear();
        fileList.setVisible(false);
        
        chooseIgnoredBttn.setVisible(false);
        ignoredListModel.clear();
        ignoredList.setVisible(false);
        
        overBttn.setVisible(false);
        startBttn.setVisible(false);
        questionLabel.setVisible(true);

        yesRadioBttn.setVisible(true);
        yesRadioBttn.setSelected(false);

        noRadioBttn.setVisible(true);
        noRadioBttn.setSelected(false);

        continueBttn.setVisible(true);
    }

    void start() {
        chooseFileBttn.setVisible(false);
        chooseIgnoredBttn.setVisible(false);
        overBttn.setVisible(false);
        startBttn.setVisible(false);
        
        preBttn.setVisible(true);
        inBttn.setVisible(true);
        postBttn.setVisible(true);
        wordsList.setVisible(true);
        
        wordListModel.clear();
        inorder(tree.root);

        searchTextField.setVisible(true);
        searchBttn.setVisible(true);
        searchList.setVisible(true);

        resetBttn.setVisible(true);
    }
    
    void reset() {
        
        tree.clear();
        ignoredWordsList.clear();
        fileArray = null;
        ignoredArray = null;
        
        
        questionLabel.setVisible(true);
        
        yesRadioBttn.setVisible(true);
        yesRadioBttn.setSelected(false);
        noRadioBttn.setVisible(true);
        noRadioBttn.setSelected(false);
        continueBttn.setVisible(true);
        
        
        fileListModel.clear();
        fileList.setVisible(false);
        
        ignoredListModel.clear();
        ignoredList.setVisible(false);
        
        preBttn.setVisible(false);
        inBttn.setVisible(false);
        postBttn.setVisible(false);
        
        wordListModel.clear();
        wordsList.setVisible(false);

        searchTextField.setText("");
        searchTextField.setVisible(false);
        searchBttn.setVisible(false);
        
        searchListModel.clear();
        searchList.setVisible(false);

        resetBttn.setVisible(false);
    }

    void inorder(Sözcü_Cücük_BinarySearchTree tree) {
        inorder(tree.root);
    }

    private void inorder(Sözcü_Cücük_Node node) {
        if (node != null) {
            inorder(node.left);
            wordListModel.addElement(node.data + " (" + node.getTotalCount() + ")");
            inorder(node.right);
        }
    }

    void preorder(Sözcü_Cücük_BinarySearchTree tree) {
        preorder(tree.root);
    }

    private void preorder(Sözcü_Cücük_Node node) {
        if (node != null) {
            wordListModel.addElement(node.data + " (" + node.getTotalCount() + ")");
            preorder(node.left);
            preorder(node.right);
        }
    }

    void postorder(Sözcü_Cücük_BinarySearchTree tree) {
        postorder(tree.root);
    }

    private void postorder(Sözcü_Cücük_Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            wordListModel.addElement(node.data + " (" + node.getTotalCount() + ")");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        chooseFileBttn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        fileList = new javax.swing.JList<>();
        chooseIgnoredBttn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ignoredList = new javax.swing.JList<>();
        preBttn = new javax.swing.JButton();
        inBttn = new javax.swing.JButton();
        postBttn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        wordsList = new javax.swing.JList<>();
        searchTextField = new javax.swing.JTextField();
        searchBttn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        searchList = new javax.swing.JList<>();
        yesRadioBttn = new javax.swing.JRadioButton();
        noRadioBttn = new javax.swing.JRadioButton();
        questionLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        startBttn = new javax.swing.JButton();
        resetBttn = new javax.swing.JButton();
        continueBttn = new javax.swing.JButton();
        overBttn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chooseFileBttn.setText("CHOOSE FILE");
        chooseFileBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFileBttnActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(fileList);

        chooseIgnoredBttn.setText("CHOOSE IGNORED FILE");
        chooseIgnoredBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseIgnoredBttnActionPerformed(evt);
            }
        });

        ignoredList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(ignoredList);

        preBttn.setText("PRE-ORDER");
        preBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preBttnActionPerformed(evt);
            }
        });

        inBttn.setText("IN-ORDER");
        inBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inBttnActionPerformed(evt);
            }
        });

        postBttn.setText("POST-ORDER");
        postBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postBttnActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(wordsList);

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        searchBttn.setText("SEARCH");
        searchBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBttnActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(searchList);

        yesRadioBttn.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        yesRadioBttn.setText("Yes");
        yesRadioBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesRadioBttnActionPerformed(evt);
            }
        });

        noRadioBttn.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        noRadioBttn.setText("No");
        noRadioBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noRadioBttnActionPerformed(evt);
            }
        });

        questionLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        questionLabel.setText("Would You Like To Add A File For  Words That Will Be Ignored?");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 55)); // NOI18N
        jLabel2.setText("WELCOME!");

        startBttn.setText("START");
        startBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBttnActionPerformed(evt);
            }
        });

        resetBttn.setText("RESET");
        resetBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBttnActionPerformed(evt);
            }
        });

        continueBttn.setText("CONTINUE");
        continueBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueBttnActionPerformed(evt);
            }
        });

        overBttn.setText("RESET");
        overBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overBttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(overBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(startBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(chooseFileBttn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                    .addComponent(chooseIgnoredBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 503, Short.MAX_VALUE)
                        .addComponent(resetBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(postBttn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(preBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(searchTextField)
                                .addGap(18, 18, 18)
                                .addComponent(searchBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(yesRadioBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(noRadioBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(187, 187, 187)
                                .addComponent(continueBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(questionLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yesRadioBttn)
                    .addComponent(noRadioBttn)
                    .addComponent(continueBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchBttn))
                            .addComponent(preBttn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inBttn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(postBttn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chooseIgnoredBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chooseFileBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(overBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chooseFileBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileBttnActionPerformed
        if (ignoredArray == null && chooseIgnoredBttn.isVisible()) {
            JOptionPane.showMessageDialog(null, "Please choose the ignored file first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tree.clear();
        fileListModel.clear();
        fileArray=null;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {

            File[] selectedFiles = fileChooser.getSelectedFiles();

            if (selectedFiles.length > 0) {
                fileArray = selectedFiles;

                for (File file : fileArray) {
                    fileListModel.addElement(file.getName());
                }

                processFiles();
            }
        }
    }//GEN-LAST:event_chooseFileBttnActionPerformed

    private void preBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preBttnActionPerformed
        if (!yesRadioBttn.isSelected() && !noRadioBttn.isSelected()) {
            JOptionPane.showMessageDialog(null, "Choose whether you want to ignore certaion words!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (yesRadioBttn.isSelected() && ignoredArray == null) {
            JOptionPane.showMessageDialog(null, "Choose ignored files first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fileListModel.getSize() > 0) {
            wordListModel.clear();
            preorder(tree.root);
        } else {
            JOptionPane.showMessageDialog(null, "No choosen files!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_preBttnActionPerformed

    private void inBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inBttnActionPerformed
        if (!yesRadioBttn.isSelected() && !noRadioBttn.isSelected()) {
            JOptionPane.showMessageDialog(null, "Choose whether you want to ignore certaion words!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (yesRadioBttn.isSelected() && ignoredArray == null) {
            JOptionPane.showMessageDialog(null, "Choose ignored files first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fileListModel.getSize() > 0) {
            wordListModel.clear();
            inorder(tree.root);
        } else {
            JOptionPane.showMessageDialog(null, "No choosen files!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_inBttnActionPerformed

    private void postBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postBttnActionPerformed
        if (!yesRadioBttn.isSelected() && !noRadioBttn.isSelected()) {
            JOptionPane.showMessageDialog(null, "Choose whether you want to ignore certaion words!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (yesRadioBttn.isSelected() && ignoredArray == null) {
            JOptionPane.showMessageDialog(null, "Choose ignored files first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fileListModel.getSize() > 0) {
            wordListModel.clear();
            postorder(tree.root);
        } else {
            JOptionPane.showMessageDialog(null, "No choosen files!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_postBttnActionPerformed

    private void searchBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBttnActionPerformed
        if (!yesRadioBttn.isSelected() && !noRadioBttn.isSelected()) {
            JOptionPane.showMessageDialog(null, "Choose whether you want to ignore certaion words!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (yesRadioBttn.isSelected() && ignoredArray == null) {
            JOptionPane.showMessageDialog(null, "Choose ignored files first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        searchListModel.clear();
        if (fileListModel.getSize() > 0) {
            if (searchTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "There is no word written!\nPlease write a word.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String word = searchTextField.getText().toLowerCase();
                if (tree.search(word) != null) {
                    Sözcü_Cücük_ListNode node = tree.search(word).list.head;
                    while (node != null) {
                        searchListModel.addElement(node.fileName + ": " + node.wordCount);
                        node = node.next;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "This word doesn't exist in files!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "No choosen files!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_searchBttnActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void chooseIgnoredBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseIgnoredBttnActionPerformed

        ignoredListModel.clear();
        ignoredWordsList.clear();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            ignoredArray = fileChooser.getSelectedFiles(); 
            for (File file : ignoredArray) {
                ignoredListModel.addElement(file.getName());
            }
            ignoredprocessFiles();
        }


    }//GEN-LAST:event_chooseIgnoredBttnActionPerformed

    private void yesRadioBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesRadioBttnActionPerformed

    }//GEN-LAST:event_yesRadioBttnActionPerformed

    private void noRadioBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noRadioBttnActionPerformed

    }//GEN-LAST:event_noRadioBttnActionPerformed

    private void startBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBttnActionPerformed
        if (!yesRadioBttn.isSelected() && !noRadioBttn.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please answer the question first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (yesRadioBttn.isSelected()) {
            if (ignoredListModel.getSize() == 0) {
                JOptionPane.showMessageDialog(null, "Please choose the file for ignored words!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (fileListModel.getSize() == 0) {
            JOptionPane.showMessageDialog(null, "Please choose the file(s) that will be searched!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        start();


    }//GEN-LAST:event_startBttnActionPerformed

    private void continueBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueBttnActionPerformed
        if (yesRadioBttn.isSelected() || noRadioBttn.isSelected()) {
            continueProgram();
        } else {
            JOptionPane.showMessageDialog(null, "Please answer the question first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_continueBttnActionPerformed

    private void overBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overBttnActionPerformed
        over();
    }//GEN-LAST:event_overBttnActionPerformed

    private void resetBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBttnActionPerformed
        reset();
    }//GEN-LAST:event_resetBttnActionPerformed

    private void ignoredprocessFiles() {
        for (File ignoredFile : ignoredArray) {
            try ( BufferedReader br = new BufferedReader(new FileReader(ignoredFile))) {
                String line;
                while ((line = br.readLine()) != null) {

                    String[] words = line.split("\\s+"); 
                    for (String word : words) {
                       
                        word = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
                        if (!word.isEmpty()) {
                            ignoredWordsList.add(word);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MiniDesktopSearchEngine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiniDesktopSearchEngine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiniDesktopSearchEngine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiniDesktopSearchEngine.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MiniDesktopSearchEngine().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton chooseFileBttn;
    private javax.swing.JButton chooseIgnoredBttn;
    private javax.swing.JButton continueBttn;
    private javax.swing.JList<String> fileList;
    private javax.swing.JList<String> ignoredList;
    private javax.swing.JButton inBttn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JRadioButton noRadioBttn;
    private javax.swing.JButton overBttn;
    private javax.swing.JButton postBttn;
    private javax.swing.JButton preBttn;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JButton resetBttn;
    private javax.swing.JButton searchBttn;
    private javax.swing.JList<String> searchList;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton startBttn;
    private javax.swing.JList<String> wordsList;
    private javax.swing.JRadioButton yesRadioBttn;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournamentapp;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Yogi
 */
public class SimpleMatchPane extends javax.swing.JPanel {
    
    public static final Color END_COLOR = Color.GREEN;
    public static final Color DEFAULT_COLOR = Color.WHITE;
    public static final Color ERROR_COLOR = Color.PINK;
    public static final Color WAIT_COLOR = Color.YELLOW;
    
    private Match match;

    public JLabel getPlayerTextField1() {
        return PlayerTextField1;
    }

    public void setPlayerTextField1(JLabel PlayerTextField1) {
        this.PlayerTextField1 = PlayerTextField1;
    }

    public JLabel getPlayerTextField2() {
        return PlayerTextField2;
    }

    public void setPlayerTextField2(JLabel PlayerTextField2) {
        this.PlayerTextField2 = PlayerTextField2;
    }

    public JTextField getScore1Field() {
        return Score1Field;
    }

    public void setScore1Field(JTextField Score1Field) {
        this.Score1Field = Score1Field;
    }

    public JTextField getScore2Field() {
        return Score2Field;
    }

    public void setScore2Field(JTextField Score2Field) {
        this.Score2Field = Score2Field;
    }

    public Match getMatch() {
        return match;
    }

    
    
    /**
     * Creates new form SimpleMatchPane
     */
    public SimpleMatchPane(Match match) {
        initComponents();
        this.match = match;
        
        // zablokowanie wpisywania meczu bez wybrania tarczy
        this.Score1Field.setEditable(false);
        this.Score2Field.setEditable(false);
        
        PlayerTextField1.setText(match.getPlayer1().toString());
        PlayerTextField2.setText(match.getPlayer2().toString());
        
        boardComboBox.addItem("0");
        for(Integer board : Tournament.boardList){
            boardComboBox.addItem(String.valueOf(board));
        }
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PlayerTextField1 = new javax.swing.JLabel();
        PlayerTextField2 = new javax.swing.JLabel();
        Score1Field = new javax.swing.JTextField();
        Score2Field = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        boardComboBox = new javax.swing.JComboBox<>();

        PlayerTextField1.setText("jLabel1");
        PlayerTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        PlayerTextField2.setText("jLabel2");
        PlayerTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Score1Field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Score2Field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VS");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        boardComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boardComboBoxItemStateChanged(evt);
            }
        });
        boardComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boardComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PlayerTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Score1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Score2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PlayerTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boardComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(boardComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Score2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PlayerTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(Score1Field, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PlayerTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void boardComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boardComboBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_boardComboBoxItemStateChanged

    private void boardComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boardComboBoxActionPerformed
        int selectedItem = Integer.parseInt((String)boardComboBox.getSelectedItem());
        if(selectedItem != 0){
            Score1Field.setBackground(WAIT_COLOR);
            Score2Field.setBackground(WAIT_COLOR);
            this.Score1Field.setEditable(true);
            this.Score2Field.setEditable(true);
        }else{
            Score1Field.setBackground(DEFAULT_COLOR);
            Score2Field.setBackground(DEFAULT_COLOR);
            this.Score1Field.setEditable(false);
            this.Score2Field.setEditable(false);
        }
    }//GEN-LAST:event_boardComboBoxActionPerformed

    // Ustawienie koloru pola po sprawdzeniu wyniku
    public void setScore(){ 
        //brak zabezpieczeń
        try{
            match.setScore1(Integer.parseInt(Score1Field.getText()));
            match.setScore2(Integer.parseInt(Score2Field.getText()));
            if(match.isEnd()){
                Score1Field.setBackground(END_COLOR);
                Score2Field.setBackground(END_COLOR);
            }else {
                Score1Field.setBackground(ERROR_COLOR);
                Score2Field.setBackground(ERROR_COLOR);
            }
        }catch(NumberFormatException e){
        
    }
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PlayerTextField1;
    private javax.swing.JLabel PlayerTextField2;
    private javax.swing.JTextField Score1Field;
    private javax.swing.JTextField Score2Field;
    private javax.swing.JComboBox<String> boardComboBox;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

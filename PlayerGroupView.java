
package tournamentapp;

public class PlayerGroupView extends javax.swing.JPanel {

    // statystyka
    public PlayerGroupView(Statistic statistic) {
        initComponents();
        PlayerLabel.setText(statistic.getPlayer().toString());
        MatchCountLabel.setText(String.valueOf(statistic.getNrMatches()));
        LegPlusLabel.setText(String.valueOf(statistic.getLegsPlus()));
        LegMinusLabel.setText(String.valueOf(statistic.getLegsMinus()));
        PlusMinusLabel.setText(String.valueOf(statistic.getLegsPlusMinus()));
        PointsLabel.setText(String.valueOf(statistic.getPoints()));
        if(!String.valueOf(statistic.getNrPlace()).isEmpty()){
            PlaceLabel.setText(String.valueOf(statistic.getNrPlace()));
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

        PlayerLabel = new javax.swing.JLabel();
        MatchCountLabel = new javax.swing.JLabel();
        LegPlusLabel = new javax.swing.JLabel();
        LegMinusLabel = new javax.swing.JLabel();
        PlusMinusLabel = new javax.swing.JLabel();
        PointsLabel = new javax.swing.JLabel();
        PlaceLabel = new javax.swing.JLabel();

        PlayerLabel.setText("jLabel1");
        PlayerLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        MatchCountLabel.setText("jLabel1");
        MatchCountLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LegPlusLabel.setText("jLabel1");
        LegPlusLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        LegMinusLabel.setText("jLabel1");
        LegMinusLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        PlusMinusLabel.setText("jLabel1");
        PlusMinusLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        PointsLabel.setText("jLabel1");
        PointsLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        PlaceLabel.setText("jLabel1");
        PlaceLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MatchCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LegPlusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LegMinusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PlusMinusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PointsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(PlaceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(MatchCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(LegPlusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(LegMinusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(PlusMinusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(PointsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(PlaceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LegMinusLabel;
    private javax.swing.JLabel LegPlusLabel;
    private javax.swing.JLabel MatchCountLabel;
    private javax.swing.JLabel PlaceLabel;
    private javax.swing.JLabel PlayerLabel;
    private javax.swing.JLabel PlusMinusLabel;
    private javax.swing.JLabel PointsLabel;
    // End of variables declaration//GEN-END:variables
}
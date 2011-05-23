/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MatrixPanel.java
 *
 * Created on May 6, 2011, 2:14:33 PM
 */

package view;

import javax.swing.JScrollPane;

/**
 *
 * @author andre
 */
public class MatrixPanel extends javax.swing.JPanel {

    /* ordre de la matrice */
    private static final int DEFAULT_MATRIX_ORDER = 5;
    private int matrixOrder = DEFAULT_MATRIX_ORDER;

    /*
     * Liste de noms des taches
     */
    private String tasks[];

    /*
     * Liste des ressources
     */
    String resources[];

    /* default matrix for tests */
    private Integer[][] DEFAULT_MATRIX = {
        {1, 2, 3, 4, 5},
        {1, 4, 2, 5, 3},
        {3, 2, 1, 5, 4},
        {1, 2, 3, 5, 4},
        {2, 1, 4, 3, 5}
    };


    /** Creates new form MatrixPanel */
    public MatrixPanel() {
        initComponents();

        // setTableModel(matrixOrder);

        setTableModel(DEFAULT_MATRIX); // this is for testing purpose only
    }

    /*
     * returns an int matrix from the JTable values
     */
    public int[][] getIntMatrix()
    {
        int rowCount = jTable1.getModel().getRowCount();
        int columnCount = jTable1.getModel().getColumnCount();
        
        int matrix[][] = new int[columnCount][rowCount];
        // Object matrix[][] = new Object[columnCount][rowCount];

        /*
         * Si l'utilisateur est en cours d'edition un probleme de pointer
         * a null peut se produire
         */
        if (jTable1.isEditing())
        {
            // JOptionPane.showMessageDialog(this, "Finish your editing first.");
            // on force la fin de l'edition
            if (jTable1.getCellEditor() != null)
            {
             jTable1.getCellEditor().stopCellEditing();
            }
        }
        
        for(int row_index=0; row_index<rowCount; row_index++)
        {
            for(int col_index=0; col_index<columnCount; col_index++)
            {
                matrix[row_index][col_index] =
                        Integer.parseInt(
                                jTable1.getModel().getValueAt(row_index, col_index).toString());
            }
        }

        return matrix;
    }

    /*
     * Set la matrice a zero
     */
    private void clearTableMatrix(javax.swing.JTable jTableMatrix)
    {
        int rowCount = jTableMatrix.getModel().getRowCount();
        int columnCount = jTableMatrix.getModel().getColumnCount();
        
        for(int row_index=0; row_index<rowCount; row_index++)
        {
            for(int col_index=0; col_index<columnCount; col_index++)
            {
                jTableMatrix.getModel().setValueAt(0, row_index, col_index);
            }
        }
    }
    
    private void setTableModel(int matrixOrder)
    {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Integer [matrixOrder][matrixOrder],
            new String [matrixOrder]
        ));
        // clearTableMatrix(jTable1);

        /*
         * Update related views
         */
        setMatrixOrder(matrixOrder, false);
    }

    /*
     * Set jTable a partir de la matrice passee en param et update les vues concernees
     * TODO: give task-n as header
     */
    public void setTableModel(Integer[][] matrix)
    {
        tasks = new String[matrix.length];
        resources = new String[matrix.length];

        for(int i=0; i<matrix.length; i++)
        {
            tasks[i] = "Task-" + (i+1);
            resources[i] = String.valueOf(i+1);
        }

        javax.swing.JTable rowTable = new RowNumberTable(jTable1);
        jScrollPane1.setRowHeaderView(rowTable);
        jScrollPane1.setCorner(JScrollPane.UPPER_LEFT_CORNER,
            rowTable.getTableHeader());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            matrix,
            tasks
        ));

        /*
         * Update related views
         */
        setMatrixOrder(matrixOrder, false);
    }

    /*
     * converts int[][] to Integer[][ and call up setTableModel(Integer[][])
     */
    public void setTableModel(int[][] matrix)
    {
        Integer matrixInteger[][] = new Integer[matrix.length][matrix.length];
        for(int i=0; i<matrix.length; i++)
        {
            for(int j=0; j<matrix.length; j++)
            {
                matrixInteger[i][j] = matrix[i][j];
            }
        }

        setTableModel(matrixInteger);
    }

    /*
     * Set matrixOrder et update les vues concernees
     */
    private void setMatrixOrder(int matrixOrder)
    {
        setMatrixOrder(matrixOrder, true);
    }

    /*
     * Set matrixOrder et update les vues concernees
     */
    private void setMatrixOrder(int matrixOrder, boolean updateTable)
    {
        this.matrixOrder = matrixOrder;
        valueTextField.setText(String.valueOf(matrixOrder));
        if (updateTable)
        {
            setTableModel(matrixOrder);
        }
    }

    public String[] getTasks()
    {
        return tasks;
    }

    public String[] getResources()
    {
        return resources;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        valueTextField = new javax.swing.JTextField();

        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(hungarian.HungarianApp.class).getContext().getResourceMap(MatrixPanel.class);
        addButton.setText(resourceMap.getString("addButton.text")); // NOI18N
        addButton.setName("addButton"); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText(resourceMap.getString("deleteButton.text")); // NOI18N
        deleteButton.setName("deleteButton"); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        valueTextField.setText(resourceMap.getString("valueTextField.text")); // NOI18N
        valueTextField.setName("valueTextField"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valueTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(valueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteButtonActionPerformed
    {//GEN-HEADEREND:event_deleteButtonActionPerformed
        setMatrixOrder(matrixOrder-1);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addButtonActionPerformed
    {//GEN-HEADEREND:event_addButtonActionPerformed
        setMatrixOrder(matrixOrder+1);
    }//GEN-LAST:event_addButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField valueTextField;
    // End of variables declaration//GEN-END:variables

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SolutionPanel.java
 *
 * Created on May 11, 2011, 8:34:25 PM
 */

package view;

/**
 *
 * @author andre
 */
public class SolutionPanel extends javax.swing.JPanel
{
    /*
     * Liste de nom de taches
     */
    String tasks[];

    /*
     * Liste de nom de taches
     */
    String resources[];

    /** Creates new form SolutionPanel */
    public SolutionPanel() {
        initComponents();
    }

    public SolutionPanel(String tasks[])
    {
        this();
        this.tasks = tasks;
    }
    

    // TODO: setup some tasks/resources by default if none where specified
    public void displaySolutionFromBooleanSolutionMatrix(Boolean[][] solutionMatrix)
    {
        // 3 pour ressource, tache, cout total
        String[][] solutionMatrixForDisplay =
                new String[solutionMatrix.length][3];
        // TODO: finish up
        for(int i=0; i<solutionMatrix.length; i++)
        {
          for(int j=0; j<solutionMatrix.length; j++)
          {
              if(solutionMatrix[i][j])
              {
                  solutionMatrixForDisplay[i][0] = "ressource"+i;
                  solutionMatrixForDisplay[i][1] = getTaskNameFromIndex(j);
                  solutionMatrixForDisplay[i][2] = "total cost";
              }
          }
        }
        /*
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            solutionMatrix,
            new String [solutionMatrix.length]
        ));
         * 
         */
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            solutionMatrixForDisplay,
            new String [solutionMatrix.length]
        ));
    }

    /*
     * Retourne le nom de la tache pour un index donne
     */
    private String getTaskNameFromIndex(int i)
    {
        return tasks[i];
    }



    public void setResources(String[] resources)
    {
        this.resources = resources;
    }

    public void setTasks(String[] tasks)
    {
        this.tasks = tasks;
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}

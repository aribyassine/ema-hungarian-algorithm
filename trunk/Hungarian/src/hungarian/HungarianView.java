/*
 * HungarianView.java
 */

package hungarian;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.AlgoInterface;
import model.AlgoModelListener;
import view.MatrixPanel;
import view.MultipleSolutionPanel;
import view.SolutionPanel;

/**
 * The application's main frame.
 */
public class HungarianView extends FrameView {
    
    /*
     * Reference to the algo so we can access current steps
     * as well as steps descriptions
     */
    private AlgoInterface algo;
    

    public HungarianView(SingleFrameApplication app) {
        super(app);

        initComponents();
        addListeners();
        widgetSetup();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = HungarianApp.getApplication().getMainFrame();
            aboutBox = new HungarianAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        HungarianApp.getApplication().show(aboutBox);
    }

    /*
     * Updates the table panels
     */
    private void onModelChange()
    {
        System.out.println("Model changes");
        stepNumLabel.setText("Step" +
                String.valueOf(algo.getStepNum()) +
                ":");
        stepDescLabel.setText(algo.getStepShortDescription());
        matrixPanel1.setTableModel(algo.getTab());
    }

    private void addListeners()
    {
        matrixPanel1.addValueTextFieldListener(new MatrixSizeTextFieldListener());
    }
    
    private void widgetSetup()
    {
        multipleSolutionPanel1.setResourcesNumber(
                    matrixPanel1.getMatrixOrder());
    }

    class MatrixSizeTextFieldListener implements DocumentListener
    {

        public void actionPerformed(DocumentEvent de)
        {
            multipleSolutionPanel1.setResourcesNumber(
                    matrixPanel1.getMatrixOrder());
        }

        public void changedUpdate(DocumentEvent de)
        {
            actionPerformed(de);
        }

        public void insertUpdate(DocumentEvent de)
        {
            actionPerformed(de);
        }

        public void removeUpdate(DocumentEvent de)
        {
            // actionPerformed(de);
        }

    }


    public MatrixPanel getMatrixPanel()
    {
        return matrixPanel1;
    }

    public MultipleSolutionPanel getMultipleSolutionPanel()
    {
        return multipleSolutionPanel1;
    }

    public void addResolveButtonListener(ActionListener al)
    {
        resolveButton.addActionListener(al);
    }

    public void addNextStepButtonListener(ActionListener al)
    {
        nextStepButton.addActionListener(al);
    }

    public void addResetButtonListener(ActionListener al)
    {
        resetButton.addActionListener(al);
    }

    public void setAlgo(AlgoInterface algo)
    {
        this.algo = algo;
        addAlgoModelListener(algo);
    }

    /*
     * Returns true if minimize (combobox) was selected
         * else otherwise
     */
    public boolean minimize()
    {
        return miniMaxiComboBox.getSelectedItem().toString().equals("Minimize");
        // (String)miniMaxiComboBox.getSelectedItem().toString();
        // return true;
    }

    /*
     * Returns true if maximize (combobox) was selected
     */
    public boolean maximize()
    {
        return (!minimize());
    }

    private void addAlgoModelListener(AlgoInterface algo)
    {
        if (algo != null)
        {
            algo.addAlgoModelListener(new AlgoModelListener()
            {
                public void algoModelChanged(final AlgoInterface algo)
                {
                    onModelChange();
                }
            });
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        resolveButton = new javax.swing.JButton();
        matrixPanel1 = new view.MatrixPanel();
        nextStepButton = new javax.swing.JButton();
        stepDescLabel = new javax.swing.JLabel();
        stepNumLabel = new javax.swing.JLabel();
        multipleSolutionPanel1 = new view.MultipleSolutionPanel();
        miniMaxiComboBox = new javax.swing.JComboBox();
        resetButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(473, 410));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(hungarian.HungarianApp.class).getContext().getResourceMap(HungarianView.class);
        resolveButton.setText(resourceMap.getString("resolveButton.text")); // NOI18N
        resolveButton.setName("resolveButton"); // NOI18N

        matrixPanel1.setName("matrixPanel1"); // NOI18N

        nextStepButton.setText(resourceMap.getString("nextStepButton.text")); // NOI18N
        nextStepButton.setName("nextStepButton"); // NOI18N

        stepDescLabel.setText(resourceMap.getString("stepDescLabel.text")); // NOI18N
        stepDescLabel.setName("stepDescLabel"); // NOI18N

        stepNumLabel.setText(resourceMap.getString("stepNumLabel.text")); // NOI18N
        stepNumLabel.setName("stepNumLabel"); // NOI18N

        multipleSolutionPanel1.setName("multipleSolutionPanel1"); // NOI18N

        miniMaxiComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Minimize", "Maximize" }));
        miniMaxiComboBox.setName("miniMaxiComboBox"); // NOI18N

        resetButton.setText(resourceMap.getString("resetButton.text")); // NOI18N
        resetButton.setName("resetButton"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(matrixPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(multipleSolutionPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(stepNumLabel)
                                .addGap(18, 18, 18)
                                .addComponent(stepDescLabel))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(resetButton)
                        .addGap(18, 18, 18)
                        .addComponent(miniMaxiComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nextStepButton)
                        .addGap(18, 18, 18)
                        .addComponent(resolveButton)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(matrixPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(multipleSolutionPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stepNumLabel)
                    .addComponent(stepDescLabel))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resolveButton)
                    .addComponent(nextStepButton)
                    .addComponent(miniMaxiComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(hungarian.HungarianApp.class).getContext().getActionMap(HungarianView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private view.MatrixPanel matrixPanel1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JComboBox miniMaxiComboBox;
    private view.MultipleSolutionPanel multipleSolutionPanel1;
    private javax.swing.JButton nextStepButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton resolveButton;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel stepDescLabel;
    private javax.swing.JLabel stepNumLabel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}

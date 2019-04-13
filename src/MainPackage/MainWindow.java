/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.iharder.dnd.FileDrop;

/**
 *
 * @author BalaH-RiG
 */
public class MainWindow extends javax.swing.JFrame implements KeyListener, FocusListener, ActionListener {

    /**
     * Creates new form gameWindow
     */
    boolean noteMode = false;
    static boolean changed = false;
    BebTexFields bibFields = new BebTexFields();
    public static PaperBuilder paperBuilder;
    public File[] files;
    public static int fileIndexCnt;
    DefaultTableModel dm;
    TableRowSorter<DefaultTableModel> sorter;
    

    private void createPaperColumns() {
        dm = (DefaultTableModel) jTable1.getModel();
        String[] columns = {"#", "EntryType", "Author", "Title", "Category", "Year", "Date Added", "Rating", "Keywords"};
        for (String column : columns) {
            dm.addColumn(column);
        }
        jTable1.getColumnModel().getColumn(8).setMinWidth(0);
        jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(8).setWidth(0);
        noteMode = false;

    }

        private void createNoteColumns() {
        dm = (DefaultTableModel) jTable1.getModel();
        String[] columns = {"#","Paper","Title", "Category","Content","Keywords"};
        for (String column : columns) {
            dm.addColumn(column);
        }
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setWidth(0);
        jTable1.getColumnModel().getColumn(4).setMinWidth(0);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(4).setWidth(0);
        jTable1.getColumnModel().getColumn(5).setMinWidth(0);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(5).setWidth(0);
        noteMode = true;
    }

    public MainWindow() {

        initComponents();
        initializeIcon();
        createPaperColumns();
        PapersManager.load();
        populatePaperUI();
        buildComboBox();
        sorter = new TableRowSorter<DefaultTableModel>(dm);
        jTable1.setRowSorter(sorter);
    }
    private void buildComboBox(){
        jComboBox1.removeAllItems();
        jComboBox1.addItem("All Columns");
        for(int i = 0; i<jTable1.getColumnCount() ; i++){
            jComboBox1.addItem(jTable1.getColumnName(i));
        }
    }

    private void search(String query) {
        if(jComboBox1.getSelectedIndex()==0){
            sorter.setRowFilter(RowFilter.regexFilter(query));
        }
        else{
            sorter.setRowFilter(RowFilter.regexFilter(query, jComboBox1.getSelectedIndex()-1));
        }
        
    }

    public void populatePaperUI() {

        dm = (DefaultTableModel) jTable1.getModel();
        dm.setRowCount(0);
        int i = 0;
        for (Paper paper : PapersManager.papers) {
            String[] array = paper.getTableArray();
            array[0] = i + "";

            dm.addRow(array);
            i++;
        }

    }
    public void populateNoteUI(){
        
            dm = (DefaultTableModel) jTable1.getModel();
            dm.setRowCount(0);
           int i=0;
           for(Paper paper: PapersManager.papers){
               
               int j=0;
               for(Note note : paper.getNotes()){
                   ArrayList<String> noteColumns = new ArrayList<String>();
                   noteColumns.add(i+":"+j);
                   String[] array = note.getTableArray();
                   array[0]= paper.getTitle();
                   noteColumns.addAll(Arrays.asList(array));
                   dm.addRow(noteColumns.toArray());
                   j++;
               }
               i++;
             
          
           }
            
            
        }

    public void saveValidPapers() {
        if (files.length > 1) {
            JOptionPane.showMessageDialog(this, "Error, please enter 1 file only.");
            return;
        }
        new Runnable() {
            @Override
            public void run() {

                fileIndexCnt = 0;
                for (File file : files) {
                    bibFields = new BebTexFields();
                    bibFields.setVisible(true);
                    PaperBuilder.RenameFile(file);
                    System.out.println(".run()XXXXXXXXXXXXXXXX");
                    bibFields.getTvAuthor().setText(PaperBuilder.getTmp().getAuthor());
                    bibFields.getTvYear().setText(PaperBuilder.getTmp().getDate());
                    bibFields.getTvCategory().setText(PaperBuilder.getTmp().getCategory());
                    bibFields.getTvYear().setText(PaperBuilder.getTmp().getDate());
                    bibFields.getJButton().addActionListener(new BibTexButtonListener());
                    bibFields.getTvTittle().setText(file.getName());
                    bibFields.setTitle(file.getName());

                    PaperBuilder.setFile(file);
                    System.out.println(file.getAbsoluteFile() + " file exist=" + file.exists());

                }
            }
        }.run();

    }

    private void initializeIcon() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("mrresearch.png")));
    }

    class BibTexButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("MainPackage.MainWindow.BibTexButtonListener.actionPerformed() Save Button Clicked");

            PaperBuilder.setFile(files[fileIndexCnt]);
            PaperBuilder.setNewFileName(bibFields.getTvTittle().getText());

            Paper paper = PaperBuilder.buildPaper(files[fileIndexCnt]);

            PapersManager.getPapers().add(PaperBuilder.buildPaper(files[fileIndexCnt]));
            populateUI();
            bibFields.dispose();
            fileIndexCnt++;

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

        jMenu4 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Mr Researcher");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                MainWindow.this.windowClosing(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel1.setText("Search");

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Open");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("View Notes");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Switch Mode");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        new  FileDrop(  System.out,jScrollPane2, new FileDrop.Listener()
            {   public void  filesDropped(java.io.File[] addedFiles )
                {
                    // handle file drop
                    files = addedFiles;
                    saveValidPapers();
                }   // end filesDropped
            });

            jTable1.setModel(new javax.swing.table.DefaultTableModel()
                {public boolean isCellEditable(int row, int column){return false;}}
            );
            jScrollPane2.setViewportView(jTable1);

            jMenu2.setText("File");

            jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainPackage/database.png"))); // NOI18N
            jMenuItem2.setText("Load Database");
            jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem2ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem2);

            jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
            jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainPackage/save.png"))); // NOI18N
            jMenuItem3.setText("Save Database");
            jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem3ActionPerformed(evt);
                }
            });
            jMenu2.add(jMenuItem3);

            jMenuBar1.add(jMenu2);

            jMenu5.setText("Edit");

            jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainPackage/edit.png"))); // NOI18N
            jMenuItem6.setText("Change BibTeX");
            jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem6ActionPerformed(evt);
                }
            });
            jMenu5.add(jMenuItem6);

            jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainPackage/reference.png"))); // NOI18N
            jMenuItem9.setText("Add Reference");
            jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem9ActionPerformed(evt);
                }
            });
            jMenu5.add(jMenuItem9);

            jMenuBar1.add(jMenu5);

            jMenu1.setText("Themes");

            jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainPackage/icons8_Operating_System_32px_2.png"))); // NOI18N
            jMenuItem4.setText("Operating System");
            jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem4ActionPerformed(evt);
                }
            });
            jMenu1.add(jMenuItem4);

            jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainPackage/icons8_Java_32px.png"))); // NOI18N
            jMenuItem5.setText("Dark");
            jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem5ActionPerformed(evt);
                }
            });
            jMenu1.add(jMenuItem5);

            jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainPackage/icons8_Settings_Backup_Restore_32px.png"))); // NOI18N
            jMenuItem7.setText("Restore Default");
            jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem7ActionPerformed(evt);
                }
            });
            jMenu1.add(jMenuItem7);

            jMenuBar1.add(jMenu1);

            jMenu3.setText("Help");

            jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainPackage/icons8_Help_32px.png"))); // NOI18N
            jMenuItem1.setText("Help Contents");
            jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem1ActionPerformed(evt);
                }
            });
            jMenu3.add(jMenuItem1);

            jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainPackage/icons8_About_32px_1.png"))); // NOI18N
            jMenuItem8.setText("About");
            jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem8ActionPerformed(evt);
                }
            });
            jMenu3.add(jMenuItem8);

            jMenuBar1.add(jMenu3);

            setJMenuBar(jMenuBar1);

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addContainerGap())
            );

            getAccessibleContext().setAccessibleDescription("");

            pack();
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
            HelpWindow x = new HelpWindow();
            x.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            x.setVisible(true);
	}//GEN-LAST:event_jMenuItem1ActionPerformed

    //Listeners
	private void windowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosing
            if (changed) {
                SaveWindow save = new SaveWindow();
                save.setVisible(true);
            } else {
                System.exit(0);
            }
	}//GEN-LAST:event_windowClosing

	private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

            MyProgram.systemTheme();
            SwingUtilities.updateComponentTreeUI(this);
	}//GEN-LAST:event_jMenuItem4ActionPerformed

	private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed

            MyProgram.defaultTheme();
            SwingUtilities.updateComponentTreeUI(this);
	}//GEN-LAST:event_jMenuItem5ActionPerformed

	private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
            MyProgram.restoreTheme();
            SwingUtilities.updateComponentTreeUI(this);
	}//GEN-LAST:event_jMenuItem7ActionPerformed

	private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
            AboutWindow about = new AboutWindow();
            about.setVisible(true);
	}//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        PapersManager.load();
        populatePaperUI();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String query = jTextField1.getText();
        search(query);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        PapersManager.save();
        populatePaperUI();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int index;
        try {
            index = Integer.parseInt((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Error: no paper was selected.");
            Note selectedNote = paper.getNotes().remove(Integer.parseInt(noteIndex[1]));
            return;
        }
        PapersManager.getPapers().remove(index);

        populatePaperUI();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(noteMode){
            String s = (String)jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            String [] noteIndex = s.split(":");
            Paper paper = PapersManager.papers.get(Integer.parseInt(noteIndex[0]));
            Note selectedNote = paper.getNotes().get(Integer.parseInt(noteIndex[1]));
            NoteAdd noteAddJframe = new NoteAdd();
            noteAddJframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            noteAddJframe.getjTextArea1().setText(selectedNote.getContent());
            noteAddJframe.getjTextArea1().setEditable(false);
            noteAddJframe.getjTextField1().setText(selectedNote.getTitle());
            noteAddJframe.getjTextField1().setEditable(false);
            noteAddJframe.getjTextField2().setText(selectedNote.getKeywords());
            noteAddJframe.getjTextField2().setEditable(false);
            noteAddJframe.getjButton1().setVisible(false);
            noteAddJframe.setVisible(true);
        }
        else{
            int index = Integer.parseInt((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            Paper paper = PapersManager.papers.get(index);
            openPaper(paper);
        }
        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(!noteMode){
            int index = Integer.parseInt((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            Paper paper = PapersManager.papers.get(index);
            NotesWindow x = new NotesWindow(paper);
            x.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            x.setVisible(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dm.setColumnCount(0);
        if(noteMode){
            jButton3.setVisible(true);
            createPaperColumns();
            populatePaperUI();
            buildComboBox();
        }
        else{
            jButton3.setVisible(false);
            createNoteColumns();
            populateNoteUI();
            buildComboBox();
        }    
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        bibFields = new BebTexFields();
        bibFields.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        RefSelect refWindow = new RefSelect(Integer.parseInt((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
        refWindow.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed
    public void openPaper(Paper paper) {
        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop is not supported");
            JOptionPane.showMessageDialog(this, "Error Desktop not supported");
            return;
        }

        File file = new File(paper.getAbsolutePath());
        Desktop desktop = Desktop.getDesktop();

        if (file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        fileIndexCnt = 0;
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private void updateList() {
        for (Paper p : PapersManager.getPapers()) {
            System.out.println(p.toString());
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusGained(FocusEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}

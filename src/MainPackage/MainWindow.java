/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
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
    ArrayList<BebTexFields> bibFields = new ArrayList<BebTexFields>();
    public static PaperBuilder paperBuilder;
    public File[] files;
    public static int fileIndexCnt;
    DefaultTableModel dm;

    private void createColumns() {
        dm = (DefaultTableModel) jTable1.getModel();
        String[] columns = {"#", "EntryType", "Author", "Title", "Category", "Year", "Date Added", "Rating", "Keywords"};
        for (String column : columns) {
            dm.addColumn(column);
        }
        jTable1.getColumnModel().getColumn(8).setMinWidth(0);
        jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(8).setWidth(0);

    }
	public MainWindow() {
            
		
		initComponents();
		initializeIcon();
                createColumns();
                PapersManager.load();
                poulateUI();
	}
        private void search(String query){
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dm);
            jTable1.setRowSorter(sorter);
            sorter.setRowFilter(RowFilter.regexFilter(query));
    }
         public void poulateUI(){
        
            dm = (DefaultTableModel) jTable1.getModel();
            dm.setRowCount(0);
            int i=0;
           for(Paper paper: PapersManager.papers){
             String[] array = paper.getTableArray();
             array[0]= i+"";
            
          dm.addRow(array);
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
                    bibFields.add(fileIndexCnt, new BebTexFields());
                    bibFields.get(fileIndexCnt).setVisible(true);
                    bibFields.get(fileIndexCnt).getJButton().addActionListener(new BibTexButtonListener());
                    bibFields.get(fileIndexCnt).getTvTittle().setText(file.getName());
                    bibFields.get(fileIndexCnt).setTitle(file.getName());

                    PaperBuilder.setFile(file);
                    System.out.println(file.getAbsoluteFile() + " file exist=" + file.exists());

                }
            }
        }.run();

    }

    private void initializeIcon() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("cube1.png")));
    }

    class BibTexButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("MainPackage.MainWindow.BibTexButtonListener.actionPerformed() Save Button Clicked");

            PaperBuilder.setFile(files[fileIndexCnt]);
            PaperBuilder.setNewFileName(bibFields.get(fileIndexCnt).getTvTittle().getText());
            PapersManager.papers.add(PaperBuilder.buildPaper(files[fileIndexCnt]));
             poulateUI();
            bibFields.get(fileIndexCnt).dispose();
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

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Magic Square Game");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
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
            jMenuItem5.setText("Java");
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
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
        if (true) {
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
       poulateUI();
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
        poulateUI();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.UsuarioDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo Martini
 */
public class DlgBuscaUsuario extends javax.swing.JDialog {

    public int cod_usuario = 0;
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    /**
     * Creates new form DlgBuscaAutor
     */
    public DlgBuscaUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Selecionar Usuário");
        setLocationRelativeTo(parent);
        setResizable(false);
        usuarioDAO.popularTabela(tblUsuario, txfBusca.getText(), "nome");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txfBusca = new javax.swing.JTextField();
        btnSelecionar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        cbxTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/select.png"))); // NOI18N
        btnSelecionar.setText("Selecionar");
        btnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarActionPerformed(evt);
            }
        });

        jLabel2.setText("Procure o usuário que deseja, clique na linha correspondente da tabela e aperte o botão \"Selecionar\".");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loupe.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód.", "Nome", "Sobrenome", "Endereço", "CPF", "E-mail", "Data Nasc.", "Telefone", "Perfil", "Situação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblUsuario.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblUsuario);

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Sobrenome", "CPF" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSelecionar)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txfBusca)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSelecionar)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarActionPerformed
        try {
            String idString = String.valueOf(tblUsuario.getValueAt(tblUsuario.getSelectedRow(), 0));

            cod_usuario = Integer.parseInt(idString);
            
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum usuário selecionado.");
            System.out.println("Erro: " + e.toString());
        }
    }//GEN-LAST:event_btnSelecionarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (cbxTipo.getSelectedIndex() == 0) {
            usuarioDAO.popularTabela(tblUsuario, txfBusca.getText(), "nome");
        } else if (cbxTipo.getSelectedIndex() == 1) {
            usuarioDAO.popularTabela(tblUsuario, txfBusca.getText(), "sobrenome");
        } else if (cbxTipo.getSelectedIndex() == 2) {
            usuarioDAO.popularTabela(tblUsuario, txfBusca.getText(), "cpf");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(DlgBuscaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgBuscaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgBuscaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgBuscaUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgBuscaUsuario dialog = new DlgBuscaUsuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txfBusca;
    // End of variables declaration//GEN-END:variables
}

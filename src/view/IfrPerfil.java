/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.PerfilDAO;
import javax.swing.JOptionPane;
import entities.Perfil;
import java.awt.Color;

/**
 *
 * @author gustavo
 */
public class IfrPerfil extends javax.swing.JInternalFrame {

    PerfilDAO perfilDAO = new PerfilDAO();
    Perfil perfil;
    int id = 0;

    /**
     * Creates new form IfrPerfil
     */
    public IfrPerfil() {
        initComponents();
        setTitle("Perfil");
        setClosable(true);
        limparCadastro();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbpPerfil = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        lblPerfil = new javax.swing.JLabel();
        txfPerfil = new javax.swing.JTextField();
        lblPrazo = new javax.swing.JLabel();
        lblLimite = new javax.swing.JLabel();
        lblQtdeRenovacoes = new javax.swing.JLabel();
        lblSituacao = new javax.swing.JLabel();
        cbxSituacao = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ftfPrazo = new javax.swing.JFormattedTextField();
        ftfLimite = new javax.swing.JFormattedTextField();
        ftfQtdeRenovacoes = new javax.swing.JFormattedTextField();
        pblConsulta = new javax.swing.JPanel();
        lblCriterio = new javax.swing.JLabel();
        txfBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPerfil = new javax.swing.JTable();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();

        tbpPerfil.setPreferredSize(new java.awt.Dimension(681, 316));
        tbpPerfil.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbpPerfilStateChanged(evt);
            }
        });

        lblPerfil.setText("Perfil do usuário:");

        txfPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txfPerfilMouseClicked(evt);
            }
        });

        lblPrazo.setText("Prazo para devolução:");

        lblLimite.setText("Limite de livros (empréstimo):");

        lblQtdeRenovacoes.setText("Quantidade de renovações (empréstimo):");

        lblSituacao.setText("Situação:");

        cbxSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("*");

        jLabel2.setText("* Campo Obrigatório");

        jLabel3.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("*");

        jLabel4.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        jLabel5.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        ftfPrazo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        ftfLimite.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        ftfQtdeRenovacoes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQtdeRenovacoes)
                    .addComponent(lblPerfil, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPrazo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLimite, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSituacao, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCadastroLayout.createSequentialGroup()
                                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxSituacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCadastroLayout.createSequentialGroup()
                                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ftfLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ftfPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ftfQtdeRenovacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)))
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addComponent(txfPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPerfil)
                    .addComponent(txfPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrazo)
                    .addComponent(jLabel3)
                    .addComponent(ftfPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLimite)
                    .addComponent(jLabel4)
                    .addComponent(ftfLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQtdeRenovacoes)
                    .addComponent(jLabel5)
                    .addComponent(ftfQtdeRenovacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSituacao)
                    .addComponent(cbxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        tbpPerfil.addTab("Cadastro", pnlCadastro);

        lblCriterio.setText("Critério:");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loupe.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblPerfil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Perfil", "Prazo", "Limite", "Qtde. Ren.", "Situação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblPerfil.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblPerfil);
        if (tblPerfil.getColumnModel().getColumnCount() > 0) {
            tblPerfil.getColumnModel().getColumn(0).setMinWidth(50);
            tblPerfil.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblPerfil.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout pblConsultaLayout = new javax.swing.GroupLayout(pblConsulta);
        pblConsulta.setLayout(pblConsultaLayout);
        pblConsultaLayout.setHorizontalGroup(
            pblConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblConsultaLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(pblConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                    .addGroup(pblConsultaLayout.createSequentialGroup()
                        .addComponent(lblCriterio)
                        .addGap(18, 18, 18)
                        .addComponent(txfBusca)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)))
                .addGap(65, 65, 65))
        );
        pblConsultaLayout.setVerticalGroup(
            pblConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblConsultaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pblConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCriterio)
                    .addComponent(txfBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tbpPerfil.addTab("Consulta", pblConsulta);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/floppy-disk.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/plus.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addGap(18, 18, 18)
                .addComponent(btnExcluir)
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbpPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnEditar)
                    .addComponent(btnSalvar)
                    .addComponent(btnNovo))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limparCadastro() {
        txfPerfil.setText("");
        ftfLimite.setText("");
        ftfPrazo.setText("");
        ftfQtdeRenovacoes.setText("");
        cbxSituacao.setSelectedIndex(0);
        txfPerfil.requestFocus();
        id = 0;
        tbpPerfil.setSelectedIndex(0);

    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        perfil = new Perfil();
        perfil.setId(id);
        String nome = txfPerfil.getText().trim();
        int prazo = ftfPrazo.getText().isEmpty() ? 0 : Integer.parseInt(ftfPrazo.getText());
        int limite = ftfLimite.getText().isEmpty() ? 0 : Integer.parseInt(ftfLimite.getText());
        int qtde_renovacoes = ftfQtdeRenovacoes.getText().isEmpty() ? 0 : Integer.parseInt(ftfQtdeRenovacoes.getText());
        String situacao = cbxSituacao.getSelectedItem().toString().toLowerCase();

        String retorno = null;

        if (nome.isEmpty()) {
            pnlCadastro.setVisible(true);
            tbpPerfil.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Preencha o campo \"Perfil do usuário\"!");
            txfPerfil.requestFocus();
            return;
        }

        if (perfilDAO.perfilExiste(nome, id)) {
            txfPerfil.setForeground(Color.red);
            JOptionPane.showMessageDialog(null, "O perfil informado já está cadastrado no sistema! Escolha outro nome!");
            new PerfilDAO().popularTabela(tblPerfil, "");
            return;
        }

        if (prazo == 0) {
            JOptionPane.showMessageDialog(null, "Informe o prazo para devolução de empréstimos!");
            return;
        }
        
        if (limite == 0) {
            JOptionPane.showMessageDialog(null, "Informe o prazo para devolução de empréstimos!");
            return;
        }
        
        if (qtde_renovacoes == 0) {
            JOptionPane.showMessageDialog(null, "Informe o prazo para devolução de empréstimos!");
            return;
        }

        perfil.setPerfil(nome);
        perfil.setPrazo(prazo);
        perfil.setLimite(limite);
        perfil.setQtde_renovacoes(qtde_renovacoes);
        perfil.setSituacao(situacao);

        if (perfil.getId() == 0) {
            retorno = perfilDAO.salvar(perfil);
        } else {
            retorno = perfilDAO.atualizar(perfil);
        }

        if (retorno == null) {
            JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");

            limparCadastro();

            new PerfilDAO().popularTabela(tblPerfil, "");
        } else {
            JOptionPane.showMessageDialog(null, "Problemas ao salvar registro!\n\n"
                    + "Mensagem técnica:\n"
                    + "Erro: " + retorno);
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            String idString = String.valueOf(tblPerfil.getValueAt(tblPerfil.getSelectedRow(), 0));

            id = Integer.parseInt(idString);

            Perfil perfil = new PerfilDAO().consultarId(id);

            tbpPerfil.setSelectedIndex(0);

            txfPerfil.setText(perfil.getPerfil());

            ftfLimite.setText(perfil.getLimite() + "");

            ftfPrazo.setText(perfil.getPrazo() + "");
            
            ftfQtdeRenovacoes.setText(perfil.getQtde_renovacoes() + "");

            if (perfil.getSituacao().equals("ativo")) {
                cbxSituacao.setSelectedIndex(0);
            } else if (perfil.getSituacao().equals("inativo")) {
                cbxSituacao.setSelectedIndex(1);
            }

            txfPerfil.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum perfil selecionado.");
            System.out.println("Erro: " + e.toString());
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            PerfilDAO perfilDAO = new PerfilDAO();
            Perfil perfil = new Perfil();

            String idString = String.valueOf(tblPerfil.getValueAt(tblPerfil.getSelectedRow(), 0));
            id = Integer.parseInt(idString);

            perfil = perfilDAO.consultarId(id);

            if (perfil.getSituacao().equals("inativo")) {
                JOptionPane.showMessageDialog(null, "Este registro já está inativo!");
            } else {
                int opcao = JOptionPane.showConfirmDialog(null, "Desejar realmente excluir?");

                System.out.println("Opção = " + opcao);

                if (opcao == JOptionPane.OK_OPTION) {

                    String retorno = new PerfilDAO().excluir(id);

                    if (retorno == null) {
                        JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");
                        new PerfilDAO().popularTabela(tblPerfil, "");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ops, problemas ao excluir registro.");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum perfil selecionado.");
            System.out.println("Erro: " + e.toString());
        }


    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        new PerfilDAO().popularTabela(tblPerfil, txfBusca.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCadastro();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void tbpPerfilStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpPerfilStateChanged
        btnExcluir.setEnabled(false);

        if (tbpPerfil.getSelectedIndex() == 1) {
            btnExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_tbpPerfilStateChanged

    private void txfPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txfPerfilMouseClicked
        txfPerfil.setForeground(Color.black);
    }//GEN-LAST:event_txfPerfilMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxSituacao;
    private javax.swing.JFormattedTextField ftfLimite;
    private javax.swing.JFormattedTextField ftfPrazo;
    private javax.swing.JFormattedTextField ftfQtdeRenovacoes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCriterio;
    private javax.swing.JLabel lblLimite;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblPrazo;
    private javax.swing.JLabel lblQtdeRenovacoes;
    private javax.swing.JLabel lblSituacao;
    private javax.swing.JPanel pblConsulta;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JTable tblPerfil;
    private javax.swing.JTabbedPane tbpPerfil;
    private javax.swing.JTextField txfBusca;
    private javax.swing.JTextField txfPerfil;
    // End of variables declaration//GEN-END:variables
}

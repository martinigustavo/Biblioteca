/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.AutorDAO;
import javax.swing.JOptionPane;
import entities.Autor;
import java.awt.Color;
import utils.Validacao;

/**
 *
 * @author gustavo
 */
public class IfrAutor extends javax.swing.JInternalFrame {

    int id = 0;

    /**
     * Creates new form IfrAutor
     */
    public IfrAutor() {
        initComponents();
        setTitle("Autor");
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

        tbpAutor = new javax.swing.JTabbedPane();
        pnlCadastro = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblSobrenome = new javax.swing.JLabel();
        txfNome = new javax.swing.JTextField();
        txfSobrenome = new javax.swing.JTextField();
        lblSituacao = new javax.swing.JLabel();
        cbxSituacao = new javax.swing.JComboBox<>();
        hglNome = new javax.swing.JLabel();
        hglSobrenome = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pnlConsulta = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txfBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAutor = new javax.swing.JTable();
        cbxTipo = new javax.swing.JComboBox<>();
        lblTipo = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();

        tbpAutor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbpAutorStateChanged(evt);
            }
        });

        lblNome.setText("Nome:");

        lblSobrenome.setText("Sobrenome:");

        txfNome.setName("nome"); // NOI18N
        txfNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txfNomeMouseClicked(evt);
            }
        });

        txfSobrenome.setName("sobrenome"); // NOI18N
        txfSobrenome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txfSobrenomeMouseClicked(evt);
            }
        });

        lblSituacao.setText("Situação:");

        cbxSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));

        hglNome.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        hglNome.setForeground(new java.awt.Color(255, 0, 51));
        hglNome.setText("*");

        hglSobrenome.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        hglSobrenome.setForeground(new java.awt.Color(255, 0, 51));
        hglSobrenome.setText("*");

        jLabel2.setText("* Campo obrigatório");

        javax.swing.GroupLayout pnlCadastroLayout = new javax.swing.GroupLayout(pnlCadastro);
        pnlCadastro.setLayout(pnlCadastroLayout);
        pnlCadastroLayout.setHorizontalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCadastroLayout.createSequentialGroup()
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(pnlCadastroLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSituacao)
                            .addComponent(lblSobrenome)
                            .addComponent(lblNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txfNome)
                            .addComponent(txfSobrenome)
                            .addComponent(cbxSituacao, 0, 435, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hglNome)
                    .addComponent(hglSobrenome))
                .addGap(52, 52, 52))
        );
        pnlCadastroLayout.setVerticalGroup(
            pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastroLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hglNome))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSobrenome)
                    .addComponent(txfSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hglSobrenome))
                .addGap(18, 18, 18)
                .addGroup(pnlCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSituacao)
                    .addComponent(cbxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        tbpAutor.addTab("Cadastro", pnlCadastro);

        jLabel1.setText("Critério:");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/loupe.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblAutor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód.", "Nome", "Sobrenome", "Situação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblAutor.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblAutor);
        if (tblAutor.getColumnModel().getColumnCount() > 0) {
            tblAutor.getColumnModel().getColumn(0).setMinWidth(50);
            tblAutor.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblAutor.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Sobrenome" }));

        lblTipo.setText("Tipo:");

        javax.swing.GroupLayout pnlConsultaLayout = new javax.swing.GroupLayout(pnlConsulta);
        pnlConsulta.setLayout(pnlConsultaLayout);
        pnlConsultaLayout.setHorizontalGroup(
            pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultaLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                    .addGroup(pnlConsultaLayout.createSequentialGroup()
                        .addComponent(lblTipo)
                        .addGap(18, 18, 18)
                        .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txfBusca)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)))
                .addGap(65, 65, 65))
        );
        pnlConsultaLayout.setVerticalGroup(
            pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txfBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipo))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        tbpAutor.addTab("Consulta", pnlConsulta);

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

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new.png"))); // NOI18N
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
            .addComponent(tbpAutor)
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
                .addComponent(tbpAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        txfNome.setText("");
        txfSobrenome.setText("");
        cbxSituacao.setSelectedItem(cbxSituacao.getItemAt(0));
        txfNome.requestFocus();
        id = 0;
        tbpAutor.setSelectedIndex(0);

    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        AutorDAO autorDAO = new AutorDAO();
        Autor autor = new Autor();
        Validacao validar = new Validacao();

        autor.setId(id);

        String nome = txfNome.getText().trim();
        String sobrenome = txfSobrenome.getText().trim();
        String situacao = cbxSituacao.getSelectedItem().toString().toLowerCase();

        String retorno = null;

        // VALIDAÇÃO DE CAMPOS EM BRANCO
        if (nome.isEmpty() || sobrenome.isEmpty()) {
            pnlCadastro.setVisible(true);
            tbpAutor.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios!");
            return;
        }
        
        // VALIDAÇÃO DE CAMPOS INVÁLIDOS
        if (validar.validarNome(nome) == false
                || validar.validarNome(sobrenome) == false) {

            if (validar.validarNome(nome) == false) {
                txfNome.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, "Digite um nome válido!");
                txfNome.requestFocus();
            }

            if (validar.validarNome(sobrenome) == false) {
                txfSobrenome.setForeground(Color.red);
                JOptionPane.showMessageDialog(null, "Digite um sobrenome válido!");
                txfSobrenome.requestFocus();
            }

            return;
        }
        
        // VALIDAÇÃO DE AUTOR JÁ CADASTRADO (com nome e sobrenome igual)
        if (autorDAO.autorExiste(nome, sobrenome) && id == 0) {
            JOptionPane.showMessageDialog(null, "Este autor já está cadastrado no sistema!");
            new AutorDAO().popularTabela(tblAutor, "", "nome", "");
            return;
        }

        autor.setNome(nome);
        autor.setSobrenome(sobrenome);
        autor.setSituacao(situacao);

        if (autor.getId() == 0) {
            retorno = autorDAO.salvar(autor);
        } else {
            retorno = autorDAO.atualizar(autor);
        }

        if (retorno == null) {
            JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");

            limparCadastro();

            new AutorDAO().popularTabela(tblAutor, "", "nome", "");
        } else {
            JOptionPane.showMessageDialog(null, "Problemas ao salvar registro!\n\n"
                    + "Mensagem técnica:\n"
                    + "Erro: " + retorno);
        }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            String idString = String.valueOf(tblAutor.getValueAt(tblAutor.getSelectedRow(), 0));

            id = Integer.parseInt(idString);

            Autor autor = new AutorDAO().consultarId(id);

            tbpAutor.setSelectedIndex(0);

            txfNome.setText(autor.getNome());
            txfSobrenome.setText(autor.getSobrenome());

            if (autor.getSituacao().equals("ativo")) {
                cbxSituacao.setSelectedIndex(0);
            } else if (autor.getSituacao().equals("inativo")) {
                cbxSituacao.setSelectedIndex(1);
            }

            txfNome.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum autor selecionado.");
            System.out.println("Erro: " + e.toString());
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            AutorDAO autorDAO = new AutorDAO();
            Autor autor = new Autor();

            String idString = String.valueOf(tblAutor.getValueAt(tblAutor.getSelectedRow(), 0));
            id = Integer.parseInt(idString);

            autor = autorDAO.consultarId(id);

            if (autor.getSituacao().equals("inativo")) {
                JOptionPane.showMessageDialog(null, "Este registro já está inativo!");
            } else {
                int opcao = JOptionPane.showConfirmDialog(null, "Desejar realmente excluir?");

                System.out.println("Opção = " + opcao);

                if (opcao == JOptionPane.OK_OPTION) {

                    String retorno = autorDAO.excluir(id);

                    if (retorno == null) {
                        JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");
                        autorDAO.popularTabela(tblAutor, "", "nome", "");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ops, problemas ao excluir registro.");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum autor selecionado.");
            System.out.println("Erro: " + e.toString());
        }

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (cbxTipo.getSelectedItem().toString().equals("Nome")) {
            new AutorDAO().popularTabela(tblAutor, txfBusca.getText(), "nome", "");
        } else if (cbxTipo.getSelectedItem().toString().equals("Sobrenome"))
            new AutorDAO().popularTabela(tblAutor, txfBusca.getText(), "sobrenome", "");
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCadastro();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void tbpAutorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpAutorStateChanged
        btnExcluir.setEnabled(false);

        if (tbpAutor.getSelectedIndex() == 1) {
            btnExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_tbpAutorStateChanged

    private void txfNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txfNomeMouseClicked
        txfNome.setForeground(Color.black);
    }//GEN-LAST:event_txfNomeMouseClicked

    private void txfSobrenomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txfSobrenomeMouseClicked
        txfSobrenome.setForeground(Color.black);
    }//GEN-LAST:event_txfSobrenomeMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxSituacao;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JLabel hglNome;
    private javax.swing.JLabel hglSobrenome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSituacao;
    private javax.swing.JLabel lblSobrenome;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel pnlCadastro;
    private javax.swing.JPanel pnlConsulta;
    private javax.swing.JTable tblAutor;
    private javax.swing.JTabbedPane tbpAutor;
    private javax.swing.JTextField txfBusca;
    private javax.swing.JTextField txfNome;
    private javax.swing.JTextField txfSobrenome;
    // End of variables declaration//GEN-END:variables
}

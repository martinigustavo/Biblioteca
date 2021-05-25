/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.EmprestimoDAO;
import dao.EmprestimoExemplarDAO;
import dao.ExemplarDAO;
import dao.LivroDAO;
import dao.PerfilDAO;
import dao.UsuarioDAO;
import entities.Emprestimo;
import entities.EmprestimoExemplar;
import entities.Exemplar;
import entities.Perfil;
import entities.Usuario;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import utils.Data;

/**
 *
 * @author Gustavo Martini
 */
public class IfrEmprestimoDevolucao extends javax.swing.JInternalFrame {

    int cod_usuario = 0;
    int cod_emprestimoPendente = 0;
    int prazo = 0;
    boolean atrasado = false;
    boolean podeRenovar = true;

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    LivroDAO livroDAO = new LivroDAO();
    PerfilDAO perfilDAO = new PerfilDAO();
    EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    EmprestimoExemplarDAO emprestimoExemplarDAO = new EmprestimoExemplarDAO();
    ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    Perfil perfil = null;
    Usuario usuario = null;
    Emprestimo emprestimo = null;

    /**
     * Creates new form IfrEmprestimo
     */
    public IfrEmprestimoDevolucao() {
        initComponents();
        setTitle("Devolver ou Renovar Empréstimo");
        setClosable(true);

        btnMultas.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuario = new javax.swing.JLabel();
        txfUsuario = new javax.swing.JTextField();
        btnBuscarUsuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmprestimos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnMultas = new javax.swing.JButton();
        btnEmprestimoInfos = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnDevolver = new javax.swing.JButton();
        btnRenovar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        lblUsuarioNome = new javax.swing.JLabel();
        btnLimpar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCod = new javax.swing.JLabel();
        lblDataDevolucao = new javax.swing.JLabel();
        lblAtrasado = new javax.swing.JLabel();

        lblUsuario.setText("Usuário:");

        btnBuscarUsuario.setText("Buscar");
        btnBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUsuarioActionPerformed(evt);
            }
        });

        tblEmprestimos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód.", "Data Retirada", "Data Devolução", "Renovações", "Devolvido", "Usuário", "Funcionário"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblEmprestimos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblEmprestimos);
        if (tblEmprestimos.getColumnModel().getColumnCount() > 0) {
            tblEmprestimos.getColumnModel().getColumn(0).setMinWidth(50);
            tblEmprestimos.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblEmprestimos.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jLabel2.setText("Empréstimos:");

        btnMultas.setText("Multas");
        btnMultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultasActionPerformed(evt);
            }
        });

        btnEmprestimoInfos.setText("Informações do empréstimo");
        btnEmprestimoInfos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmprestimoInfosActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        jLabel3.setText("Empréstimo pendente");

        btnDevolver.setText("Devolver");
        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverActionPerformed(evt);
            }
        });

        btnRenovar.setText("Renovar");
        btnRenovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenovarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome:");

        lblUsuarioNome.setText(" ");

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        jLabel4.setText("Cód.:");

        jLabel5.setText("Data para devolução:");

        jLabel6.setText("Atrasado:");

        lblCod.setText(" ");

        lblDataDevolucao.setText(" ");

        lblAtrasado.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnEmprestimoInfos)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblUsuario)
                                    .addGap(18, 18, 18)
                                    .addComponent(txfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnBuscarUsuario)
                                    .addGap(39, 39, 39)
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblUsuarioNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(93, 93, 93))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator2)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnMultas)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnCancelar))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnRenovar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnDevolver)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCod, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAtrasado, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarUsuario)
                    .addComponent(jLabel1)
                    .addComponent(lblUsuarioNome))
                .addGap(22, 22, 22)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEmprestimoInfos)
                .addGap(22, 22, 22)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblCod))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblDataDevolucao))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblAtrasado))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDevolver)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRenovar)
                        .addComponent(btnMultas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnLimpar))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limparCadastro() {
        txfUsuario.setText("");
        lblUsuarioNome.setText("");
        lblAtrasado.setText("");
        lblCod.setText("");
        lblDataDevolucao.setText("");
        emprestimoDAO.popularTabela(tblEmprestimos, 0, "", "");
//        emprestimoDAO.popularTabela(tblEmprestimoPendente, 0, "", "");

        cod_usuario = 0;

        txfUsuario.requestFocus();
    }

    private void btnBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsuarioActionPerformed
        try {
            lblAtrasado.setText("");
            lblCod.setText("");
            lblDataDevolucao.setText("");
            emprestimo = null;
            btnRenovar.setEnabled(false);
            podeRenovar = true;
            atrasado = false;
            cod_emprestimoPendente = 0;

            if (txfUsuario.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Nenhum usuário informado!");
                return;
            }
            cod_usuario = Integer.parseInt(txfUsuario.getText());
            usuario = usuarioDAO.consultarId(cod_usuario);
            emprestimos = emprestimoDAO.consultar(cod_usuario + "");
            perfil = perfilDAO.consultarId(usuario.getCod_perfil());
            prazo = perfil.getPrazo();

            emprestimoDAO.popularTabela(tblEmprestimos, cod_usuario, "", "");
//            emprestimoDAO.popularTabela(tblEmprestimoPendente, cod_usuario, "AND devolvido = false ", "");
            if (emprestimos.size() > 0) {

                for (Emprestimo emp : emprestimos) {
                    if (emp.isDevolvido() == false) {
                        emprestimo = emp;
                        cod_emprestimoPendente = emp.getId();
                    }
                }

                if (emprestimo != null) {
                    if (emprestimo.getRenovacoes() < perfil.getQtde_renovacoes()) {
                        System.out.println("renovacoes emprestimo" + emprestimo.getRenovacoes());
                        System.out.println("renovacoes perfil" + perfil.getQtde_renovacoes());
                        btnRenovar.setEnabled(true);
                    }

                    String data_retirada = emprestimo.getData_retirada();
                    Date data_retiradaDate = Data.stringToDate(data_retirada);

                    prazo = emprestimo.getRenovacoes() != 0 ? prazo * emprestimo.getRenovacoes() : prazo;

                    Date data_diasSomados = Data.somarDia(data_retiradaDate, prazo);
                    long diferenca_datas = Data.compareDates(Data.dataAtual(), data_diasSomados);
                    lblCod.setText(emprestimo.getId() + "");
                    lblDataDevolucao.setText(Data.dateToString(data_diasSomados));
                    atrasado = diferenca_datas < 0;
                    String lblAtrasadoString = atrasado ? "Sim" : "Não";
                    lblAtrasado.setText(lblAtrasadoString);
                    if (atrasado) {
                        btnMultas.setEnabled(true);
                        btnRenovar.setEnabled(false);
                    }
                }
            }
            lblUsuarioNome.setText(usuario.getNome() + " " + usuario.getSobrenome());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Usuário não existe!");
            System.err.println("Erro: " + e.toString());
        }
    }//GEN-LAST:event_btnBuscarUsuarioActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCadastro();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnEmprestimoInfosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmprestimoInfosActionPerformed
        try {
            String idString = String.valueOf(tblEmprestimos.getValueAt(tblEmprestimos.getSelectedRow(), 0));
            int cod_emprestimo = Integer.parseInt(idString);

            Frame parentFrame = (Frame) SwingUtilities.getAncestorOfClass(Window.class, this);
            DlgEmprestimoInfos dlgEmprestimoInfos = new DlgEmprestimoInfos(parentFrame, true, cod_emprestimo, cod_usuario);
            dlgEmprestimoInfos.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Nenhum empréstimo selecionado.");
            System.out.println("Erro: " + e.toString());
        }
    }//GEN-LAST:event_btnEmprestimoInfosActionPerformed

    private void btnRenovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenovarActionPerformed
        try {
            if (!atrasado) {
                String retorno = "";

                if (emprestimo.getRenovacoes() == perfil.getQtde_renovacoes()) {
                    JOptionPane.showMessageDialog(null, "Este empréstimo não pode mais ser renovado!");
                    return;
                }

                String data_retirada = emprestimo.getData_retirada();
                Date data_retiradaDate = Data.stringToDate(data_retirada);
                Date data_diasSomados = Data.somarDia(data_retiradaDate, prazo);

                Date data_renovada = Data.somarDia(data_diasSomados, prazo);

                emprestimo.setRenovacoes(emprestimo.getRenovacoes() + 1);

                retorno = emprestimoDAO.atualizar(emprestimo);

                if (retorno == null) {
                    lblDataDevolucao.setText(Data.dateToString(data_renovada));
                    JOptionPane.showMessageDialog(null, "Empréstimo renovado com sucesso.");

                    emprestimoDAO.popularTabela(tblEmprestimos, cod_usuario, "", "");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problema ao renovar empréstimo.");
            System.out.println("Erro: " + e.toString());
        }
    }//GEN-LAST:event_btnRenovarActionPerformed

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed
        try {
            if (atrasado) {
                String retorno = "";

                ArrayList<Exemplar> exemplares = new ArrayList<>();
                exemplares = new EmprestimoExemplarDAO().consultar(cod_emprestimoPendente + "");

                for (Exemplar ex : exemplares) {
//                    retorno = new EmprestimoExemplarDAO().excluir(cod_emprestimoPendente, ex.getId());
                    ex.setCod_estado(1);
                    retorno = new ExemplarDAO().atualizar(ex);
                }

                retorno = emprestimoDAO.excluir(cod_emprestimoPendente);

                if (retorno == null) {
                    JOptionPane.showMessageDialog(null, "Empréstimo devolvido com sucesso.");

                    limparCadastro();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problema ao devolver empréstimo.");
            System.out.println("Erro: " + e.toString());
        }
    }//GEN-LAST:event_btnDevolverActionPerformed

    private void btnMultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMultasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarUsuario;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnEmprestimoInfos;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnMultas;
    private javax.swing.JButton btnRenovar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAtrasado;
    private javax.swing.JLabel lblCod;
    private javax.swing.JLabel lblDataDevolucao;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblUsuarioNome;
    private javax.swing.JTable tblEmprestimos;
    private javax.swing.JTextField txfUsuario;
    // End of variables declaration//GEN-END:variables
}

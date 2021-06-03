/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Emprestimo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import utils.ConexaoBD;
import utils.Data;

/**
 *
 * @author gustavo
 */
public class EmprestimoDAO implements IDAOT<Emprestimo> {

    public int returnId = 0;

    @Override
    public String salvar(Emprestimo emprestimo) {
        try {

            Connection conn = ConexaoBD.getInstance().getConnection();

            System.out.println("CHECK 2");
            int id = emprestimo.getId();
            Date data_retirada = Data.stringToDate(emprestimo.getData_retirada());
            Date data_devolucao = emprestimo.getData_devolucao().equals("")
                    ? null : Data.stringToDate(emprestimo.getData_devolucao());
            Integer renovacoes = emprestimo.getRenovacoes();
            boolean devolvido = data_devolucao == null ? false : true;
            Integer cod_usuario = emprestimo.getCod_usuario();
            Integer cod_func = emprestimo.getCod_func();

            System.out.println("CHECK3");
            String sql = "";
            if (data_devolucao == null) {
                sql = "Insert into emprestimo (data_retirada, renovacoes, devolvido, cod_usuario, cod_func) values "
                        + " ('" + data_retirada + "',"
                        + " '" + renovacoes + "',"
                        + " '" + devolvido + "',"
                        + " '" + cod_usuario + "',"
                        + " '" + cod_func + "') RETURNING cod_emprestimo";
            } else {
                sql = "Insert into emprestimo (data_retirada, data_devolucao, renovacoes, devolvido, cod_usuario, cod_func) values "
                        + " ('" + data_retirada + "',"
                        + " '" + data_devolucao + "',"
                        + " '" + renovacoes + "',"
                        + " '" + devolvido + "',"
                        + " '" + cod_usuario + "',"
                        + " '" + cod_func + "') RETURNING cod_emprestimo";
            }

            PreparedStatement ps = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            returnId = generatedKey;

            System.out.println("Inserted record's ID: " + generatedKey);

            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao salvar empréstimo!\n"
                    + "Erro: " + e.getMessage());
            return "";
        }
    }

    @Override
    public String atualizar(Emprestimo emprestimo) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            int id = emprestimo.getId();
            int renovacoes = emprestimo.getRenovacoes();

            String sql = "UPDATE emprestimo "
                    + "SET renovacoes = " + renovacoes + " "
//                    + "data_devolucao = " + data_devolucao + " "
                    + "where cod_emprestimo = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar empréstimo: " + e);
            return e.toString();
        }
    }

    @Override
    public Emprestimo consultarId(int id) {
        Emprestimo emprestimo = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from emprestimo "
                    + "where cod_emprestimo = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                emprestimo = new Emprestimo();

                emprestimo.setId(retorno.getInt("cod_emprestimo"));
                emprestimo.setData_retirada(Data.dateToString(retorno.getDate("data_retirada")));
                
                Date data_devolucao = retorno.getDate("data_devolucao");
                String data_devolucaoString = data_devolucao == null ? "" : Data.dateToString(data_devolucao);
                emprestimo.setData_devolucao(data_devolucaoString);
                emprestimo.setRenovacoes(retorno.getInt("renovacoes"));
                emprestimo.setDevolvido(retorno.getBoolean("devolvido"));
                emprestimo.setCod_usuario(retorno.getInt("cod_usuario"));
                emprestimo.setCod_func(retorno.getInt("cod_func"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar empréstimo: " + e);
        }

        return emprestimo;
    }

    @Override
    public ArrayList<Emprestimo> consultar(String cod_usuario) {
        ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        Emprestimo emprestimo = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM emprestimo WHERE cod_usuario = '" + cod_usuario + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                emprestimo = new EmprestimoDAO().consultarId(retorno.getInt("cod_emprestimo"));
                emprestimos.add(emprestimo);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todos em emprestimo: " + e);
        }

        return emprestimos;
    }

    public void popularTabela(JTable tabela, int cod_usuario, String criterio, String order) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[7];
        cabecalho[0] = "Cód.";
        cabecalho[1] = "Data Retirada";
        cabecalho[2] = "Data Devolução";
        cabecalho[3] = "Renovações";
        cabecalho[4] = "Devolvido";
        cabecalho[5] = "Usuário";
        cabecalho[6] = "Funcionário";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM emprestimo "
                    + "WHERE cod_usuario = " + cod_usuario + "");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][7];

        } catch (Exception e) {
            System.out.println("Erro ao consultar emprestimos: " + e);
        }

        int lin = 0;

        order = order.equals("") ? "data_retirada" : order;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM emprestimo "
                    + "WHERE cod_usuario = " + cod_usuario + " "
                    + criterio
                    + "ORDER BY " + order);

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("cod_emprestimo");
                dadosTabela[lin][1] = Data.dateToString(resultadoQ.getDate("data_retirada"));
                String data_devolucao = resultadoQ.getDate("data_devolucao") == null
                        ? "" : Data.dateToString(resultadoQ.getDate("data_devolucao"));
                dadosTabela[lin][2] = data_devolucao;
                dadosTabela[lin][3] = resultadoQ.getInt("renovacoes");
                boolean devolvido = resultadoQ.getBoolean("devolvido");
                dadosTabela[lin][4] = devolvido ? "devolvido" : "pendente";
                dadosTabela[lin][5] = resultadoQ.getInt("cod_usuario");
                dadosTabela[lin][6] = resultadoQ.getInt("cod_func");

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
                    //return ImageIcon.class;
                    //return Boolean.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            column.setCellRenderer(centerRenderer);
        }

        column = tabela.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
        column.setMaxWidth(50);
        column.setMinWidth(50);
    }
    
    public void popularTabelaBusca(JTable tabela, String sql, String order) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[7];
        cabecalho[0] = "Cód.";
        cabecalho[1] = "Data Empréstimo";
        cabecalho[2] = "Data Devolução";
        cabecalho[3] = "Renovações";
        cabecalho[4] = "Devolvido";
        cabecalho[5] = "Usuário";
        cabecalho[6] = "Funcionário";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM emprestimo "
                    + sql
                    + "");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][7];

        } catch (Exception e) {
            System.out.println("Erro ao consultar emprestimos: " + e);
        }

        int lin = 0;

        order = order.equals("") ? "data_retirada" : order;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM emprestimo "
                    + sql
                    + order);

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("cod_emprestimo");
                dadosTabela[lin][1] = Data.dateToString(resultadoQ.getDate("data_retirada"));
                String data_devolucao = resultadoQ.getDate("data_devolucao") == null
                        ? "" : Data.dateToString(resultadoQ.getDate("data_devolucao"));
                dadosTabela[lin][2] = data_devolucao;
                dadosTabela[lin][3] = resultadoQ.getInt("renovacoes");
                boolean devolvido = resultadoQ.getBoolean("devolvido");
                dadosTabela[lin][4] = devolvido ? "devolvido" : "pendente";
                dadosTabela[lin][5] = resultadoQ.getInt("cod_usuario");
                dadosTabela[lin][6] = resultadoQ.getInt("cod_func");

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela...");
            System.out.println(e);
        }

        // configuracoes adicionais no componente tabela
        tabela.setModel(new DefaultTableModel(dadosTabela, cabecalho) {
            @Override
            // quando retorno for FALSE, a tabela nao é editavel
            public boolean isCellEditable(int row, int column) {
                return false;
                /*  
                 if (column == 3) {  // apenas a coluna 3 sera editavel
                 return true;
                 } else {
                 return false;
                 }
                 */
            }

            // alteracao no metodo que determina a coluna em que o objeto ImageIcon devera aparecer
            @Override
            public Class getColumnClass(int column) {

                if (column == 2) {
                    //return ImageIcon.class;
                    //return Boolean.class;
                }
                return Object.class;
            }
        });

        // permite seleção de apenas uma linha da tabela
        tabela.setSelectionMode(0);

        // redimensiona as colunas de uma tabela
        TableColumn column = null;
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            column = tabela.getColumnModel().getColumn(i);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            column.setCellRenderer(centerRenderer);
        }

        column = tabela.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
        column.setMaxWidth(50);
        column.setMinWidth(50);
    }
    
    @Override
    public String excluir(int id) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update emprestimo "
                    + "set data_devolucao = '" + Data.stringToDate(Data.dateToString(Data.dataAtual())) + "', "
                    + "devolvido = true "
                    + "where cod_emprestimo = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir/inativar emprestimo: " + e);
            return e.toString();
        }
    }

    @Override
    public ArrayList<Emprestimo> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import entities.Editora;
import utils.ConexaoBD;

/**
 *
 * @author gustavo
 */
public class EditoraDAO implements IDAOT<Editora> {

    @Override
    public String salvar(Editora editora) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String nome = editora.getNome();
            String situacao = editora.getSituacao();

            String sql = "Insert into editora values "
                    + "(default,"
                    + " '" + nome + "',"
                    + " '" + situacao + "')";
            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar editora: " + e.toString());
            return e.toString();
        }
    }

    @Override
    public String atualizar(Editora editora) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            int id = editora.getId();
            String nome = editora.getNome();
            String situacao = editora.getSituacao();

            String sql = "UPDATE editora "
                    + "SET nome = '" + nome + "', "
                    + "situacao = '" + situacao + "' "
                    + "where cod_editora = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar editora: " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update editora "
                    + "set situacao = 'inativo' "
                    + "where cod_editora = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir/inativar: " + e);
            return e.toString();
        }
    }

    @Override
    public Editora consultarId(int id) {
        Editora editora = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from editora "
                    + "where "
                    + "cod_editora = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                editora = new Editora();

                editora.setId(retorno.getInt("cod_editora"));
                editora.setNome(retorno.getString("nome"));
                editora.setSituacao(retorno.getString("situacao"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar editora: " + e);
        }

        return editora;
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[3];
        cabecalho[0] = "Cód.";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Situação";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM editora "
                    + "WHERE nome ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][3];

        } catch (Exception e) {
            System.out.println("Erro ao consultar editora: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM editora "
                    + "WHERE nome ILIKE '%" + criterio + "%' "
                    + "ORDER BY nome");

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("cod_editora");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getString("situacao");

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

    public boolean editoraExiste(String nome) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from editora "
                    + "where nome = '" + nome + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                if (retorno.getString("situacao").equals("inativo")) {
                    atualizar(new Editora(retorno.getInt("cod_editora"),
                            retorno.getString("nome"),
                            "ativo"));
                }
                return true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar editora existente: " + e);
        }

        return false;
    }

    @Override
    public ArrayList<Editora> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Editora> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

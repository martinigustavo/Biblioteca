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
import entities.Autor;
import utils.ConexaoBD;

/**
 *
 * @author gustavo
 */
public class AutorDAO implements IDAOT<Autor> {

    @Override
    public String salvar(Autor autor) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String nome = autor.getNome();
            String sobrenome = autor.getSobrenome();
            String situacao = autor.getSituacao();

            String sql = "Insert into autor values "
                    + "(default,"
                    + " '" + nome + "',"
                    + " '" + sobrenome + "',"
                    + " '" + situacao + "')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar autor: " + e.toString());
            return e.toString();
        }
    }

    @Override
    public String atualizar(Autor autor) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            int id = autor.getId();
            String nome = autor.getNome();
            String sobrenome = autor.getSobrenome();
            String situacao = autor.getSituacao();

            String sql = "UPDATE autor "
                    + "SET nome = '" + nome + "', "
                    + "sobrenome = '" + sobrenome + "', "
                    + "situacao = '" + situacao + "' "
                    + "where cod_autor = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar autor: " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update autor "
                    + "set situacao = 'inativo' "
                    + "where cod_autor = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir/inativar: " + e);
            return e.toString();
        }
    }

    @Override
    public Autor consultarId(int id) {
        Autor autor = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from autor "
                    + "where "
                    + "cod_autor = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                autor = new Autor();

                autor.setId(retorno.getInt("cod_autor"));
                autor.setNome(retorno.getString("nome"));
                autor.setSobrenome(retorno.getString("sobrenome"));
                autor.setSituacao(retorno.getString("situacao"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar autor: " + e);
        }

        return autor;
    }

    public void popularTabela(JTable tabela, String criterio, String tipo, String situacao) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[4];
        cabecalho[0] = "Cód.";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Sobrenome";
        cabecalho[3] = "Situação";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM autor "
                    + "WHERE " + tipo + " ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][4];

        } catch (Exception e) {
            System.out.println("Erro ao consultar autor: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM autor "
                    + "WHERE " + tipo + " ILIKE '%" + criterio + "%' and situacao like '" + situacao + "%' "
                    + "ORDER BY " + tipo);

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("cod_autor");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getString("sobrenome");
                dadosTabela[lin][3] = resultadoQ.getString("situacao");

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

    public boolean autorExiste(String nome, String sobrenome) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from autor "
                    + "where nome = '" + nome + "' AND "
                    + "sobrenome = '" + sobrenome + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                if (retorno.getString("situacao").equals("inativo")) {
                    atualizar(new Autor(retorno.getInt("cod_autor"),
                            retorno.getString("nome"),
                            retorno.getString("sobrenome"),
                            "ativo"));
                }
                return true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar autor existente: " + e);
        }

        return false;
    }

    @Override
    public ArrayList<Autor> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Autor> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

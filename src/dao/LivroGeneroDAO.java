/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Genero;
import entities.LivroGenero;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import utils.ConexaoBD;

/**
 *
 * @author Gustavo Martini
 */
public class LivroGeneroDAO {

    public String salvar(LivroGenero livroGenero) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            Integer cod_genero = livroGenero.getCod_genero();
            Integer cod_livro = livroGenero.getCod_livro();

            String sql = "Insert into livro_genero values "
                    + "('" + cod_genero + "',"
                    + " '" + cod_livro + "')";
            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar livro_genero: " + e.toString());
            return e.toString();
        }
    }

    public String atualizar(LivroGenero o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String excluir(int cod_livro, int cod_genero) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "delete from livro_genero "
                    + "where cod_genero = " + cod_genero + " "
                    + "AND cod_livro = " + cod_livro;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir livro_genero: " + e);
            return e.toString();
        }
    }
    
    public void popularTabela(JTable tabela, int idLivro) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[2];
        cabecalho[0] = "id";
        cabecalho[1] = "Gênero";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM livro_genero "
                    + "WHERE cod_livro = " + idLivro + "");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][2];

        } catch (Exception e) {
            System.out.println("Erro ao consultar livro_genero: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM livro_genero "
                    + "WHERE cod_livro = '" + idLivro + "' "
                    + "ORDER BY cod_genero");

            while (resultadoQ.next()) {
                int cod_genero = resultadoQ.getInt("cod_genero");
                String genero = new GeneroDAO().consultarId(cod_genero).getGenero();
                dadosTabela[lin][0] = cod_genero;
                dadosTabela[lin][1] = genero;

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
        column.setPreferredWidth(70);
        column.setMaxWidth(70);
        column.setMinWidth(70);
    }
    
    public boolean livroGeneroExiste(int livroId, int generoId) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from livro_genero "
                    + "where "
                    + "cod_livro = " + livroId
                    + " AND cod_genero = " + generoId;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar se livroGenero existe: " + e);
        }

        return false;
    }

    public LivroGenero consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<LivroGenero> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Genero> consultar(int idLivro) {
        ArrayList<Genero> generos = new ArrayList<Genero>();
        Genero genero = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM livro_genero WHERE cod_livro = '" + idLivro + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                genero = new GeneroDAO().consultarId(retorno.getInt("cod_genero"));
                generos.add(genero);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todos em livro_genero: " + e);
        }

        return generos;
    }
    
    
}

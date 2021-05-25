/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Exemplar;
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
public class ExemplarDAO {

    public String salvar(Exemplar exemplar) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            int cod_livro = exemplar.getCod_livro();
            int cod_estado = exemplar.getCod_estado();

            String sql = "Insert into exemplar values "
                    + "(default,"
                    + " '" + cod_livro + "',"
                    + " '" + cod_estado + "')";
            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar exemplar: " + e.toString());
            return e.toString();
        }
    }

    public String atualizar(Exemplar exemplar) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            int id = exemplar.getId();
//            int cod_livro = exemplar.getLivro().getId();
            int cod_estado = exemplar.getCod_estado();

            String sql = "UPDATE exemplar "
                    + "SET cod_estado = '" + cod_estado + "' "
                    //                    + "situacao = '" + situacao + "' "
                    + "where cod_exemplar = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar exemplar: " + e);
            return e.toString();
        }
    }

    public String excluir(int id) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update exemplar "
                    + "set cod_estado = 4 "
                    + "where cod_editora = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir/inativar exemplar: " + e);
            return e.toString();
        }
    }

    public Exemplar consultarId(int id) {
        Exemplar exemplar = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from exemplar "
                    + "where "
                    + "cod_exemplar = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                exemplar = new Exemplar();

                exemplar.setId(retorno.getInt("cod_exemplar"));
                exemplar.setCod_livro(retorno.getInt("cod_livro"));
                exemplar.setCod_estado(retorno.getInt("cod_estado"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar exemplar: " + e);
        }

        return exemplar;
    }

    public void popularTabela(JTable tabela, int cod_livro) {
//        int cod_livroInt = Integer.parseInt(cod_livro); 
        
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[2];
        cabecalho[0] = "Cód.";
        cabecalho[1] = "Estado";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM exemplar "
                    + "WHERE cod_livro = " + cod_livro);
//                    + " AND cod_exemplar = " + cod_exemplar
//                    + " AND cod_estado != 2");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][2];

        } catch (Exception e) {
            System.out.println("Erro ao consultar exemplar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM exemplar "
                    + "WHERE cod_livro = " + cod_livro
//                    + " AND cod_exemplar = " + cod_exemplar
//                    + " AND cod_estado != 2"
                    + " ORDER BY cod_exemplar");

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("cod_exemplar");
                int cod_estado = resultadoQ.getInt("cod_estado");
                switch (cod_estado) {
                    case 1:
                        dadosTabela[lin][1] = "disponível";
                        break;

                    case 2:
                        dadosTabela[lin][1] = "emprestado";
                        break;
                        
                    case 3:
                        dadosTabela[lin][1] = "danificado";
                        break;

                    case 4:
                        dadosTabela[lin][1] = "indisponível";
                        break;
                }

                lin++;
            }
        } catch (Exception e) {
            System.out.println("problemas para popular tabela de exemplar...");
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

    public ArrayList<Exemplar> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Exemplar> consultar(int idLivro) {
        ArrayList<Exemplar> exemplares = new ArrayList<>();
        Exemplar exemplar = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM exemplar WHERE cod_livro = '" + idLivro + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                exemplar = consultarId(retorno.getInt("cod_exemplar"));
                exemplares.add(exemplar);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todos em exemplares: " + e);
        }

        return exemplares;
    }

}

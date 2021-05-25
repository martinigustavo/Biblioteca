/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.EmprestimoExemplar;
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
public class EmprestimoExemplarDAO {

    public String salvar(EmprestimoExemplar emprestimoExemplar) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            Integer cod_emprestimo = emprestimoExemplar.getCod_emprestimo();
            Integer cod_exemplar = emprestimoExemplar.getCod_exemplar();

            String sql = "Insert into emprestimo_exemplar values "
                    + "('" + cod_emprestimo + "',"
                    + " '" + cod_exemplar + "')";
            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar emprestimo_exemplar: " + e.toString());
            return e.toString();
        }
    }

    public String atualizar(EmprestimoExemplar o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String excluir(int cod_emprestimo, int cod_exemplar) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "delete from emprestimo_exemplar "
                    + "where cod_emprestimo = " + cod_emprestimo + " "
                    + "AND cod_exemplar = " + cod_exemplar;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir emprestimo_exemplar: " + e);
            return e.toString();
        }
    }

    public void popularTabela(JTable tabela, int cod_emprestimo) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[2];
        cabecalho[0] = "id";
        cabecalho[1] = "Exemplar";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM emprestimo_exemplar "
                    + "WHERE cod_emprestimo = " + cod_emprestimo + "");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][2];

        } catch (Exception e) {
            System.out.println("Erro ao consultar emprestimo_exemplar: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "select e.cod_exemplar, l.titulo "
                    + "from livro l "
                    + "left join exemplar e on e.cod_livro = l.cod_livro "
                    + "left join emprestimo_exemplar ee on ee.cod_exemplar = e.cod_exemplar "
                    + "left join emprestimo e2 on e2.cod_emprestimo = ee.cod_emprestimo "
                    + "where e2.cod_emprestimo = " + cod_emprestimo + " "
                    + "order by l.titulo");

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("cod_exemplar");
                dadosTabela[lin][1] = resultadoQ.getString("titulo");

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

    public ArrayList<EmprestimoExemplar> consultarTodos() {
        ArrayList<EmprestimoExemplar> emprestimoExemplares = new ArrayList<EmprestimoExemplar>();
        EmprestimoExemplar emprestimoExemplar;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM emprestimo_exemplar";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                emprestimoExemplar = new EmprestimoExemplar();
                emprestimoExemplar.setCod_emprestimo(retorno.getInt("cod_emprestimo"));
                emprestimoExemplar.setCod_exemplar(retorno.getInt("cod_exemplar"));
                emprestimoExemplares.add(emprestimoExemplar);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todos em emprestimo_exemplar: " + e);
        }

        return emprestimoExemplares;
    }

    public ArrayList<Exemplar> consultar(String cod_emprestimo) {
        ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
        Exemplar exemplar = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM emprestimo_exemplar WHERE cod_emprestimo = '" + cod_emprestimo + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                exemplar = new ExemplarDAO().consultarId(retorno.getInt("cod_exemplar"));
                exemplares.add(exemplar);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todos em emprestimo_exemplar: " + e);
        }

        return exemplares;
    }

    public EmprestimoExemplar consultarId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
//                    + "select e.cod_exemplar, l.titulo "
//                    + "from livro l "
//                    + "left join exemplar e on e.cod_livro = l.cod_livro "
//                    + "left join emprestimo_exemplar ee on ee.cod_exemplar = e.cod_exemplar "
//                    + "left join emprestimo e2 on e2.cod_emprestimo = ee.cod_emprestimo "
//                    + "where e2.cod_emprestimo = " + cod_emprestimo + " "
//                    + "order by l.titulo");
    }

}

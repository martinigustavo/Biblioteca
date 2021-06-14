/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Emprestimo;
import entities.Multa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import utils.ConexaoBD;
import utils.Data;

/**
 *
 * @author Gustavo Martini
 */
public class MultaDAO implements IDAOT<Multa> {

    public int returnId = 0;

    @Override
    public String salvar(Multa multa) {
        try {
            Connection conn = ConexaoBD.getInstance().getConnection();

//            Double valor = multa.getValor();
//            boolean pago = multa.isPago();
//            Date data_pgto = multa.getData_pgto() == ""
//                    ? null : Data.stringToDate(multa.getData_pgto());
            int cod_emprestimo = multa.getEmprestimo().getId();

            String sql = "INSERT INTO multa "
                    + "(cod_emprestimo) VALUES "
                    + "("
                    //                    + " '" + valor + "',"
                    //                    + " '" + pago + "',"
                    //                    + " '" + data_pgto + "',"
                    + " '" + cod_emprestimo + "') RETURNING cod_multa";

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
            System.out.println("Erro ao salvar multa: " + e.toString());
            return e.toString();
        }
    }

    @Override
    public String atualizar(Multa multa) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            int id = multa.getId();
//            Double valor = multa.getValor();
            boolean pago = multa.isPago();
            Date data_pgto = Data.stringToDate(multa.getData_pgto());

            String sql = "UPDATE multa "
                    //                    + "SET valor = " + valor + ", "
                    + "SET pago = '" + pago + "', "
                    + "data_pgto = '" + data_pgto + "' "
                    + "where cod_multa = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar multa: " + e);
            return e.toString();
        }
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[5];
        cabecalho[0] = "Cód.";
        cabecalho[1] = "Valor";
        cabecalho[2] = "Pago";
        cabecalho[3] = "Data Pgto";
        cabecalho[4] = "Empréstimo";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(m.*) "
                    + "FROM multa m "
                    + "JOIN emprestimo e on m.cod_emprestimo = e.cod_emprestimo "
                    + "WHERE e.cod_usuario = " + criterio);

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][6];

        } catch (Exception e) {
            System.out.println("Erro ao consultar perfil: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT m.* "
                    + "FROM multa m "
                    + "JOIN emprestimo e on m.cod_emprestimo = e.cod_emprestimo "
                    + "WHERE e.cod_usuario = " + criterio
                    + " ORDER BY m.pago");

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("cod_multa");
                dadosTabela[lin][1] = resultadoQ.getDouble("valor");
                String isPago = resultadoQ.getBoolean("pago") == true ? "Pago" : "Pendente";
                dadosTabela[lin][2] = isPago;
                dadosTabela[lin][3] = resultadoQ.getDate("data_pgto");
                dadosTabela[lin][4] = resultadoQ.getInt("cod_emprestimo");

                // caso a coluna precise exibir uma imagem
//                if (resultadoQ.getBoolean("Situacao")) {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_ativo.png"));
//                } else {
//                    dadosTabela[lin][2] = new ImageIcon(getClass().getClassLoader().getResource("Interface/imagens/status_inativo.png"));
//                }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Multa> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Multa> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean consultar(int cod_usuario) {
        Multa multa = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * "
                    + "FROM multa m "
                    + "join emprestimo e on m.cod_emprestimo = e.cod_emprestimo "
                    + "where m.pago = 'false' and e.cod_usuario = " + cod_usuario;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todas multas de um usuário: " + e);
        }

        return false;
    }

    @Override
    public Multa consultarId(int id) {
        Multa multa = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from multa "
                    + "where cod_multa = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                multa = new Multa();

                multa.setId(retorno.getInt("cod_multa"));
                multa.setValor(retorno.getDouble("valor"));
                multa.setPago(retorno.getBoolean("pago"));

                int cod_emprestimo = retorno.getInt("cod_emprestimo");
                Emprestimo emprestimo = new EmprestimoDAO().consultarId(cod_emprestimo);
                multa.setEmprestimo(emprestimo);

//                long diferencaDias = new Data().compareDates(
//                        Data.stringToDate(emprestimo.getData_devolucao()),
//                        Data.stringToDate(emprestimo.getData_devolvido())
//                );
                Date data_pgto = retorno.getDate("data_pgto");
                String data_pgtoString = data_pgto == null ? "" : Data.dateToString(data_pgto);
                multa.setData_pgto(data_pgtoString);

                System.out.println(multa.toString());
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar empréstimo: " + e);
        }

        return multa;
    }

}

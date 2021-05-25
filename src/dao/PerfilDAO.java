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
import entities.Perfil;
import utils.ConexaoBD;

/**
 *
 * @author gustavo
 */
public class PerfilDAO implements IDAOT<Perfil> {
    
    @Override
    public String salvar(Perfil perfil) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String nome = perfil.getPerfil();
            int prazo = perfil.getPrazo();
            int limite = perfil.getLimite();
            int qtde_renovacoes = perfil.getQtde_renovacoes();
            String situacao = perfil.getSituacao();

            String sql = "Insert into perfil values "
                    + "(default,"
                    + " '" + nome + "',"
                    + " '" + prazo + "',"
                    + " '" + limite + "',"
                    + " '" + qtde_renovacoes + "',"
                    + " '" + situacao + "')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar perfil: " + e.toString());
            return e.toString();
        }
    }

    @Override
    public String atualizar(Perfil perfil) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            int id = perfil.getId();
            String nome = perfil.getPerfil();
            int prazo = perfil.getPrazo();
            int limite = perfil.getLimite();
            int qtde_renovacoes = perfil.getQtde_renovacoes();
            String situacao = perfil.getSituacao();

            String sql = "UPDATE perfil "
                    + "SET perfil = '" + nome + "', "
                    + "prazo = '" + prazo + "', "
                    + "limite = '" + limite + "', "
                    + "qtde_renovacoes = '" + qtde_renovacoes + "', "
                    + "situacao = '" + situacao + "' "
                    + "where cod_perfil = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar perfil: " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update perfil "
                    + "set situacao = 'inativo' "
                    + "where cod_perfil = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao excluir/inativar: " + e);
            return e.toString();
        }
    }
    
    public boolean perfilExiste(String perfil, int idPerfil) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from perfil "
                    + "where perfil = '" + perfil + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                
                if (idPerfil != 0 && retorno.getInt("cod_perfil") == idPerfil) {
                    return false;
                }
                
                if (retorno.getString("situacao").equals("inativo")) {
                    atualizar(new Perfil(retorno.getInt("cod_perfil"),
                            retorno.getString("perfil"),
                            retorno.getInt("prazo"),
                            retorno.getInt("limite"),
                            retorno.getInt("qtde_renovacoes"),
                            "ativo"));
                }
                
                
                return true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar perfil existente: " + e);
        }

        return false;
    }

    @Override
    public Perfil consultarId(int id) {
        Perfil perfil = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from perfil "
                    + "where "
                    + "cod_perfil = " + id;

//            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                perfil = new Perfil();

                perfil.setId(retorno.getInt("cod_perfil"));
                perfil.setPerfil(retorno.getString("perfil"));
                perfil.setPrazo(retorno.getInt("prazo"));
                perfil.setLimite(retorno.getInt("limite"));
                perfil.setQtde_renovacoes(retorno.getInt("qtde_renovacoes"));
                perfil.setSituacao(retorno.getString("situacao"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultr perfil: " + e);
        }

        return perfil;
    }

    public void popularTabela(JTable tabela, String criterio) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[6];
        cabecalho[0] = "Cód.";
        cabecalho[1] = "Perfil";
        cabecalho[2] = "Prazo";
        cabecalho[3] = "Limite";
        cabecalho[4] = "Qtde. Ren.";
        cabecalho[5] = "Situação";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM perfil "
                    + "WHERE perfil ILIKE '%" + criterio + "%'");

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
                    + "SELECT * FROM perfil "
                    + "WHERE perfil ILIKE '%" + criterio + "%' "
                    + "ORDER BY perfil");

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("cod_perfil");
                dadosTabela[lin][1] = resultadoQ.getString("perfil");
                dadosTabela[lin][2] = resultadoQ.getInt("prazo");
                dadosTabela[lin][3] = resultadoQ.getInt("limite");
                dadosTabela[lin][4] = resultadoQ.getInt("qtde_renovacoes");
                dadosTabela[lin][5] = resultadoQ.getString("situacao");

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
    public ArrayList<Perfil> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Perfil> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

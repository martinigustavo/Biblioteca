/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import entities.Usuario;
import utils.ConexaoBD;
import utils.Data;

/**
 *
 * @author gustavo
 */
public class UsuarioDAO implements IDAOT<Usuario> {

    @Override
    public String salvar(Usuario user) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String nome = user.getNome();
            String sobrenome = user.getSobrenome();
            String endereco = user.getEndereco();
            String cpf = user.getCpf();
            String email = user.getEmail();
            Date data_nasc = Data.stringToDate(user.getData_nasc());
            Date data_cadastro = Data.dataAtual();
            String telefone = user.getTelefone();
            String situacao = user.getSituacao();
            int cod_perfil = user.getCod_perfil();

            String sql = "INSERT INTO usuario VALUES "
                    + "(default,"
                    + " '" + nome + "',"
                    + " '" + sobrenome + "',"
                    + " '" + endereco + "',"
                    + " '" + cpf + "',"
                    + " '" + email + "',"
                    + " '" + data_nasc + "',"
                    + " '" + data_cadastro + "',"
                    + " '" + telefone + "',"
                    + " '" + situacao + "',"
                    + " '" + cod_perfil + "')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário: " + e.toString());
            return e.toString();
        }
    }

    @Override
    public String atualizar(Usuario usuario) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            int id = usuario.getId();
            String nome = usuario.getNome();
            String sobrenome = usuario.getSobrenome();
            String endereco = usuario.getEndereco();
            String cpf = usuario.getCpf();
            String email = usuario.getEmail();
            Date data_nasc = Data.stringToDate(usuario.getData_nasc());
            Date data_cadastro = Data.stringToDate(consultarId(id).getData_cadastro());
            String telefone = usuario.getTelefone();
            String situacao = usuario.getSituacao();
            int cod_perfil = usuario.getCod_perfil();

            String sql = "UPDATE usuario "
                    + "SET nome = '" + nome + "', "
                    + "sobrenome = '" + sobrenome + "', "
                    + "endereco = '" + endereco + "', "
                    + "cpf = '" + cpf + "', "
                    + "email = '" + email + "', "
                    + "data_nasc = '" + data_nasc + "', "
                    + "data_cadastro = '" + data_cadastro + "', "
                    + "telefone = '" + telefone + "', "
                    + "situacao = '" + situacao + "', "
                    + "cod_perfil = '" + cod_perfil + "' "
                    + "where cod_usuario = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar funcionário: " + e);
            return e.toString();
        }
    }

    @Override
    public String excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean emailExiste(String email, int id) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from usuario "
                    + "where email = '" + email + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            int retornoId = 0;
            String retornoEmail = "";

            if (retorno.next()) {
                retornoId = retorno.getInt("cod_usuario");
                retornoEmail = retorno.getString("email");
            }

            if ((id == 0 && retornoEmail.equals(email))
                    || retornoId != id && retornoEmail.equals(email)) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar email existente de usuário: " + e);
        }

        return false;
    }

    public boolean cpfExiste(String cpf, int id) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from usuario "
                    + "where cpf = '" + cpf + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            int retornoId = 0;
            String retornoCpf = "";

            if (retorno.next()) {
                retornoId = retorno.getInt("cod_usuario");
                retornoCpf = retorno.getString("cpf");
            }

            if ((id == 0 && retornoCpf.equals(cpf))
                    || retornoId != id && retornoCpf.equals(cpf)) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar cpf existente de usuário: " + e);
        }

        return false;
    }

    public void popularTabela(JTable tabela, String criterio, String tipo) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[10];
        cabecalho[0] = "Cód.";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Sobrenome";
        cabecalho[3] = "Endereço";
        cabecalho[4] = "CPF";
        cabecalho[5] = "Email";
        cabecalho[6] = "Data Nasc.";
        cabecalho[7] = "Telefone";
        cabecalho[8] = "Perfil";
        cabecalho[9] = "Situação";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM usuario "
                    + "WHERE " + tipo + " ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][10];

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuário: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM usuario "
                    + "WHERE " + tipo + " ILIKE '%" + criterio + "%' "
                    + "ORDER BY " + tipo);

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("cod_usuario");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getString("sobrenome");
                dadosTabela[lin][3] = resultadoQ.getString("endereco");
                dadosTabela[lin][4] = resultadoQ.getString("cpf");
                dadosTabela[lin][5] = resultadoQ.getString("email");
                dadosTabela[lin][6] = Data.dateToString(resultadoQ.getDate("data_nasc"));
                dadosTabela[lin][7] = resultadoQ.getString("telefone");
                dadosTabela[lin][8] = new PerfilDAO().consultarId(resultadoQ.getInt("cod_perfil")).getPerfil();
                dadosTabela[lin][9] = resultadoQ.getString("situacao");

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

            column = tabela.getColumnModel().getColumn(0);
            column.setPreferredWidth(50);
            column.setMaxWidth(50);
            column.setMinWidth(50);

            column = tabela.getColumnModel().getColumn(9);
            column.setPreferredWidth(60);
            column.setMaxWidth(70);
            column.setMinWidth(80);

//            switch (i) {
//                case 0:
//                    column = tabela.getColumnModel().getColumn(0);
//                    column.setPreferredWidth(50);
//                    column.setMaxWidth(50);
//                    column.setMinWidth(50);
//                    break;
//
//                case 1:
//                    column = tabela.getColumnModel().getColumn(1);
//                    column.setPreferredWidth(60);
//                    column.setMaxWidth(70);
//                    column.setMinWidth(50);
//                    break;
//
//                case 2:
//                    column = tabela.getColumnModel().getColumn(2);
//                    column.setPreferredWidth(90);
//                    column.setMaxWidth(100);
//                    column.setMinWidth(80);
//                    break;
//
//                case 4:
//                    column = tabela.getColumnModel().getColumn(4);
//                    column.setPreferredWidth(80);
//                    column.setMaxWidth(90);
//                    column.setMinWidth(70);
//                    break;
//
//                case 5:
//                    column = tabela.getColumnModel().getColumn(5);
//                    column.setPreferredWidth(80);
//                    column.setMaxWidth(90);
//                    column.setMinWidth(70);
//                    break;
//            }
        }
    }

    @Override
    public Usuario consultarId(int id) {
        Usuario usuario = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from usuario "
                    + "where "
                    + "cod_usuario = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                usuario = new Usuario();

                usuario.setId(retorno.getInt("cod_usuario"));
                usuario.setNome(retorno.getString("nome"));
                usuario.setSobrenome(retorno.getString("sobrenome"));
                usuario.setEndereco(retorno.getString("endereco"));
                usuario.setCpf(retorno.getString("cpf"));
                usuario.setEmail(retorno.getString("email"));
                usuario.setData_nasc(Data.dateToString(retorno.getDate("data_nasc")));
                usuario.setData_cadastro(Data.dateToString(retorno.getDate("data_cadastro")));
                usuario.setTelefone(retorno.getString("telefone"));
                usuario.setSituacao(retorno.getString("situacao"));
                usuario.setCod_perfil(retorno.getInt("cod_perfil"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuário: " + e);
        }

        return usuario;
    }

    @Override
    public ArrayList<Usuario> consultarTodos() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        Usuario usuario = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM usuario";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                usuario = new Usuario();

                usuario.setId(retorno.getInt("cod_usuario"));
                usuario.setNome(retorno.getString("nome"));
                usuario.setSobrenome(retorno.getString("sobrenome"));
                usuario.setEndereco(retorno.getString("endereco"));
                usuario.setCpf(retorno.getString("cpf"));
                usuario.setEmail(retorno.getString("email"));
                usuario.setData_nasc(Data.dateToString(retorno.getDate("data_nasc")));
                usuario.setData_cadastro(Data.dateToString(retorno.getDate("data_cadastro")));
                usuario.setTelefone(retorno.getString("telefone"));
                usuario.setSituacao(retorno.getString("situacao"));
                usuario.setCod_perfil(retorno.getInt("cod_perfil"));

                usuarios.add(usuario);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todos os usuarios: " + e);
        }

        return usuarios;
    }

    @Override
    public ArrayList<Usuario> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

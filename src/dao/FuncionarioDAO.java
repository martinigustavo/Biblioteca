/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import entities.Funcionario;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import utils.ConexaoBD;
import utils.PasswordHash;

/**
 *
 * @author gustavo
 */
public class FuncionarioDAO implements IDAOT<Funcionario> {

    private static Funcionario funcionarioLogado;

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
    }

    public Funcionario consultarUsuario(String criterio) {
        Funcionario func = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from funcionario "
                    + "where "
                    + "usuario = '" + criterio + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                func = new Funcionario();

                func.setId(retorno.getInt("cod_func"));
                func.setNome(retorno.getString("nome"));
                func.setSobrenome(retorno.getString("sobrenome"));
                func.setEmail(retorno.getString("email"));
                func.setUsuario(retorno.getString("usuario"));
                func.setSenha(retorno.getString("senha"));
                func.setSalt(retorno.getString("salt"));
                func.setSituacao(retorno.getString("situacao"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuário: " + e);
        }

        return func;
    }

    // AUTENTICAÇÃO DA TELA DE LOGIN
    public String autenticar(String usuario, String senhaSemHash) {

        Funcionario func = consultarUsuario(usuario);

        if (func != null) {
            String salt = func.getSalt();
            String senha = func.getSenha();

            if (func.getSituacao().equals("inativo")) {
                JOptionPane.showMessageDialog(null, "Este usuário está inativo."
                        + "\nFale com o responsável para reativá-lo!");
                return "";
            }

            if (PasswordHash.verifyPassword(senhaSemHash, senha, salt)) {
                setFuncionarioLogado(func);
                System.out.println(func.getUsuario() + "\n"
                        + func.getSenha() + "\n"
                        + func.getSalt() + "\n");

                System.out.println("FUNCIONARIO LOGADO: "
                        + getFuncionarioLogado().getNome());
                if (usuario.equals("admin")) {
                    JOptionPane.showMessageDialog(null, "Administrador autenticado!");
                    return null;
                }

                JOptionPane.showMessageDialog(null, "Usuário autenticado!");
                return null;
            }

        }

        JOptionPane.showMessageDialog(null, "Informações de usuário incorretas.");
        return "";

    }

    @Override
    public String salvar(Funcionario func) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            int id = func.getId();
            String nome = func.getNome();
            String sobrenome = func.getSobrenome();
            String email = func.getEmail();
            String usuario = func.getUsuario();
            String senha = func.getSenha();
            String salt = PasswordHash.generateSalt(10);
            String situacao = "ativo";

            String hashedPass = PasswordHash.hashPassword(senha, salt);
            String sql = "Insert into funcionario values "
                    + "(default,"
                    + " '" + nome + "',"
                    + " '" + sobrenome + "',"
                    + " '" + email + "',"
                    + " '" + usuario + "',"
                    + " '" + hashedPass + "',"
                    + " '" + salt + "',"
                    + " '" + situacao + "')";

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao salvar funcionário!\n"
                    + "Erro: " + e.getMessage());
            return "";
        }
    }

    public boolean emailExiste(String email, int id) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from funcionario "
                    + "where email = '" + email + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            int retornoId = 0;
            String retornoEmail = "";

            if (retorno.next()) {
                retornoId = retorno.getInt("cod_func");
                retornoEmail = retorno.getString("email");
            }

            if ((id == 0 && retornoEmail.equals(email))
                    || retornoId != id && retornoEmail.equals(email)) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar email existente de funcionário: " + e);
        }

        return false;
    }

    public boolean usuarioExiste(String usuario, int id) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from funcionario "
                    + "where usuario = '" + usuario + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            int retornoId = 0;
            String retornoUsuario = "";

            if (retorno.next()) {
                retornoId = retorno.getInt("cod_func");
                retornoUsuario = retorno.getString("usuario");
            }

            if ((id == 0 && retornoUsuario.equals(usuario))
                    || retornoId != id && retornoUsuario.equals(usuario)) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar usuário existente: " + e);
        }

        return false;
    }

    @Override
    public String atualizar(Funcionario func) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            int id = func.getId();
            String nome = func.getNome();
            String sobrenome = func.getSobrenome();
            String email = func.getEmail();
            String usuario = func.getUsuario();
            String senha = func.getSenha();
            String salt = func.getSalt();
            String situacao = func.getSituacao();

            if (senha.equals("")) {
                senha = consultarId(id).getSenha();
                salt = consultarId(id).getSalt();
            }

            String sql = "UPDATE funcionario "
                    + "SET nome = '" + nome + "', "
                    + "sobrenome = '" + sobrenome + "', "
                    + "email = '" + email + "', "
                    + "usuario = '" + usuario + "', "
                    + "senha = '" + senha + "', "
                    + "salt = '" + salt + "', "
                    + "situacao = '" + situacao + "' "
                    + "where cod_func = " + id;

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
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "update funcionario "
                    + "set situacao = 'inativo' "
                    + "where cod_func = " + id;

            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao inativar funcionário: " + e);
            return e.toString();
        }
    }

    @Override
    public Funcionario consultarId(int id) {
        Funcionario funcionario = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from funcionario "
                    + "where "
                    + "cod_func = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                funcionario = new Funcionario();

                funcionario.setId(retorno.getInt("cod_func"));
                funcionario.setNome(retorno.getString("nome"));
                funcionario.setSobrenome(retorno.getString("sobrenome"));
                funcionario.setEmail(retorno.getString("email"));
                funcionario.setUsuario(retorno.getString("usuario"));
                funcionario.setSenha(retorno.getString("senha"));
                funcionario.setSalt(retorno.getString("salt"));
                funcionario.setSituacao(retorno.getString("situacao"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar funcionário: " + e);
        }

        return funcionario;
    }

    public void popularTabela(JTable tabela, String criterio, String tipo) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[6];
        cabecalho[0] = "Cód.";
        cabecalho[1] = "Nome";
        cabecalho[2] = "Sobrenome";
        cabecalho[3] = "Email";
        cabecalho[4] = "Usuário";
        cabecalho[5] = "Situação";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM funcionario "
                    + "WHERE " + tipo + " ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][6];

        } catch (Exception e) {
            System.out.println("Erro ao consultar funcionário: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM funcionario "
                    + "WHERE " + tipo + " ILIKE '%" + criterio + "%' "
                    + "ORDER BY " + tipo);

            while (resultadoQ.next()) {
                dadosTabela[lin][0] = resultadoQ.getInt("cod_func");
                dadosTabela[lin][1] = resultadoQ.getString("nome");
                dadosTabela[lin][2] = resultadoQ.getString("sobrenome");
                dadosTabela[lin][3] = resultadoQ.getString("email");
                dadosTabela[lin][4] = resultadoQ.getString("usuario");
                dadosTabela[lin][5] = resultadoQ.getString("situacao");

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

            switch (i) {
                case 0:
                    column = tabela.getColumnModel().getColumn(0);
                    column.setPreferredWidth(50);
                    column.setMaxWidth(50);
                    column.setMinWidth(50);
                    break;

                case 1:
                    column = tabela.getColumnModel().getColumn(1);
                    column.setPreferredWidth(60);
                    column.setMaxWidth(70);
                    column.setMinWidth(50);
                    break;

                case 2:
                    column = tabela.getColumnModel().getColumn(2);
                    column.setPreferredWidth(90);
                    column.setMaxWidth(100);
                    column.setMinWidth(80);
                    break;

                case 4:
                    column = tabela.getColumnModel().getColumn(4);
                    column.setPreferredWidth(80);
                    column.setMaxWidth(90);
                    column.setMinWidth(70);
                    break;

                case 5:
                    column = tabela.getColumnModel().getColumn(5);
                    column.setPreferredWidth(80);
                    column.setMaxWidth(90);
                    column.setMinWidth(70);
                    break;
            }
        }
    }

    @Override
    public ArrayList<Funcionario> consultarTodos() {
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        Funcionario func = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "SELECT * FROM funcionario";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            while (retorno.next()) {
                func = new Funcionario();

                func.setId(retorno.getInt("cod_func"));
                func.setNome(retorno.getString("nome"));
                func.setSobrenome(retorno.getString("sobrenome"));
                func.setEmail(retorno.getString("email"));
                func.setUsuario(retorno.getString("usuario"));
                func.setSenha(retorno.getString("senha"));
                func.setSalt(retorno.getString("salt"));
                func.setSituacao(retorno.getString("situacao"));

                funcionarios.add(func);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar todos os funcionarios: " + e);
        }

        return funcionarios;
    }

    @Override
    public ArrayList<Funcionario> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

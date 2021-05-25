/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Autor;
import entities.Exemplar;
import entities.Genero;
import entities.Livro;
import entities.LivroAutor;
import entities.LivroGenero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import utils.ConexaoBD;

/**
 *
 * @author gustavo
 */
public class LivroDAO {

    public int returnId;
    LivroAutorDAO livroAutorDAO = new LivroAutorDAO();
    LivroAutor livroAutor;
    LivroGeneroDAO livroGeneroDAO = new LivroGeneroDAO();
    LivroGenero livroGenero;
    ExemplarDAO exemplarDAO = new ExemplarDAO();
    ArrayList<Autor> autores = new ArrayList<>();
    ArrayList<Genero> generos = new ArrayList<>();
    ArrayList<Exemplar> exemplares = new ArrayList<>();

    public String salvar(Livro livro) {
        try {

            Connection conn = ConexaoBD.getInstance().getConnection();

            int id = livro.getId();
            String isbn = livro.getIsbn();
            String titulo = livro.getTitulo();
            String ano = livro.getAno();
            String edicao = livro.getEdicao();
            String volume = livro.getVolume();
            Integer numDeExemplares = livro.getNumDeExemplares();
            Integer cod_editora = livro.getCod_editora();
            Integer cod_tipolivro = livro.getCod_tipolivro();

            String sql = "Insert into livro values "
                    + "(default,"
                    + " '" + isbn + "',"
                    + " '" + titulo + "',"
                    + " '" + ano + "',"
                    + " '" + edicao + "',"
                    + " '" + volume + "',"
                    + " '" + numDeExemplares + "',"
                    + " '" + cod_tipolivro + "',"
                    + " '" + cod_editora + "') RETURNING cod_livro";

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
            JOptionPane.showMessageDialog(null, "Problemas ao salvar livro!\n"
                    + "Erro: " + e.getMessage());
            return "";
        }
    }

    public String atualizar(Livro livro) {
        try {
            Statement stm = ConexaoBD.getInstance().getConnection().createStatement();

            int id = livro.getId();
            String isbn = livro.getIsbn();
            String titulo = livro.getTitulo();
            String ano = livro.getAno();
            String edicao = livro.getEdicao();
            String volume = livro.getVolume();
            Integer numDeExemplares = livro.getNumDeExemplares();
            Integer cod_editora = livro.getCod_editora();
            Integer cod_tipolivro = livro.getCod_tipolivro();

            String sql = "UPDATE livro "
                    + "SET isbn = '" + isbn + "', "
                    + "titulo = '" + titulo + "', "
                    + "ano = '" + ano + "', "
                    + "edicao = '" + edicao + "', "
                    + "volume = '" + volume + "', "
                    + "exemplares = '" + numDeExemplares + "', "
                    + "cod_tipolivro = '" + cod_tipolivro + "', "
                    + "cod_editora = '" + cod_editora + "' "
                    + "where cod_livro = " + id;
            
            System.out.println("SQL: " + sql);

            int resultado = stm.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar livro: " + e);
            return e.toString();
        }
    }

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

    public boolean livroExiste(String isbn, int id) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * from livro "
                    + "where isbn = '" + isbn + "'";

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            String retornoIsbn = "";

            if (retorno.next()) {
                retornoIsbn = retorno.getString("isbn");
            }

            if (id == 0 && retornoIsbn == isbn) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar livro existente: " + e);
        }

        return false;
    }

    public Livro consultarId(int id) {
        Livro livro = null;

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "select * "
                    + "from livro "
                    + "where "
                    + "cod_livro = " + id;

            System.out.println("SQL: " + sql);

            ResultSet retorno = st.executeQuery(sql);

            if (retorno.next()) {
                livro = new Livro();

                livro.setId(retorno.getInt("cod_livro"));
                livro.setIsbn(retorno.getString("isbn"));
                livro.setTitulo(retorno.getString("titulo"));
                livro.setAno(retorno.getString("ano"));
                livro.setEdicao(retorno.getString("edicao"));
                livro.setVolume(retorno.getString("volume"));
                livro.setNumDeExemplares(retorno.getInt("exemplares"));
                livro.setCod_editora(retorno.getInt("cod_editora"));
                livro.setCod_tipolivro(retorno.getInt("cod_tipolivro"));

                autores = new LivroAutorDAO().consultar(id);
                generos = new LivroGeneroDAO().consultar(id);
                exemplares = new ExemplarDAO().consultar(id);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar funcionário: " + e);
        }

        return livro;
    }

    public void popularTabela(JTable tabela, String criterio, String tipo) {
        // dados da tabela
        Object[][] dadosTabela = null;

        // cabecalho da tabela
        Object[] cabecalho = new Object[11];
        cabecalho[0] = "Cód.";
        cabecalho[1] = "ISBN";
        cabecalho[2] = "Título";
        cabecalho[3] = "Ano";
        cabecalho[4] = "Autor(es)";
        cabecalho[5] = "Edição";
        cabecalho[6] = "Volume";
        cabecalho[7] = "Num. Ex.";
        cabecalho[8] = "Editora";
        cabecalho[9] = "Gênero(s)";
        cabecalho[10] = "Tipo";

        ResultSet resultadoQ;

        // cria matriz de acordo com nº de registros da tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT count(*) FROM livro "
                    + "WHERE " + tipo + " ILIKE '%" + criterio + "%'");

            resultadoQ.next();

            // instancia da matrzi de acordo com o nº de linhas do ResultSet
            dadosTabela = new Object[resultadoQ.getInt(1)][11];

        } catch (Exception e) {
            System.out.println("Erro ao consultar livro: " + e);
        }

        int lin = 0;

        // efetua consulta na tabela
        try {
            resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(""
                    + "SELECT * FROM livro "
                    + "WHERE " + tipo + " ILIKE '%" + criterio + "%' "
                    + "ORDER BY " + tipo);

            while (resultadoQ.next()) {
                int idLivro = 0;
                String allAutores = "";
                String allGeneros = "";
                String editora = "";
                String tipoLivro = "";

                idLivro = resultadoQ.getInt("cod_livro");
                autores = new LivroAutorDAO().consultar(idLivro);
                generos = new LivroGeneroDAO().consultar(idLivro);

                for (Autor autor : autores) {
                    allAutores += autor.getNome() + " " + autor.getSobrenome() + ", ";
                }

                for (Genero genero : generos) {
                    allGeneros += genero.getGenero() + ", ";
                }

                editora = new EditoraDAO()
                        .consultarId(resultadoQ.getInt("cod_editora")).getNome();

                int codTipoLivro = resultadoQ.getInt("cod_tipolivro");

                switch (codTipoLivro) {
                    case 1:
                        tipoLivro = "livro";
                        break;

                    case 2:
                        tipoLivro = "enciclopédia";
                        break;

                    case 3:
                        tipoLivro = "revista";
                        break;

                    case 4:
                        tipoLivro = "dicionário";
                        break;
                }

                dadosTabela[lin][0] = resultadoQ.getInt("cod_livro");
                dadosTabela[lin][1] = resultadoQ.getString("isbn");
                dadosTabela[lin][2] = resultadoQ.getString("titulo");
                dadosTabela[lin][3] = resultadoQ.getString("ano");
                dadosTabela[lin][4] = allAutores;
                dadosTabela[lin][5] = resultadoQ.getString("edicao");
                dadosTabela[lin][6] = resultadoQ.getString("volume");
                dadosTabela[lin][7] = resultadoQ.getInt("exemplares");
                dadosTabela[lin][8] = editora;
                dadosTabela[lin][9] = allGeneros;
                dadosTabela[lin][10] = tipoLivro;

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
                case 0: //cod
                    column = tabela.getColumnModel().getColumn(0);
                    column.setPreferredWidth(50);
                    column.setMaxWidth(50);
                    column.setMinWidth(50);
                    break;

                case 1: //isbn
                    column = tabela.getColumnModel().getColumn(1);
                    column.setPreferredWidth(160);
                    column.setMaxWidth(200);
                    column.setMinWidth(120);
                    break;

                case 2: //titulo
                    column = tabela.getColumnModel().getColumn(2);
                    column.setPreferredWidth(400);
                    column.setMaxWidth(1000);
                    column.setMinWidth(100);
                    break;

                case 3: //ano
                    column = tabela.getColumnModel().getColumn(3);
                    column.setPreferredWidth(60);
                    column.setMaxWidth(60);
                    column.setMinWidth(60);
                    break;

                case 4: //autores
                    column = tabela.getColumnModel().getColumn(4);
                    column.setPreferredWidth(200);
                    column.setMaxWidth(1000);
                    column.setMinWidth(100);
                    break;

                case 5: //edicao
                    column = tabela.getColumnModel().getColumn(5);
                    column.setPreferredWidth(60);
                    column.setMaxWidth(60);
                    column.setMinWidth(60);
                    break;

                case 6: //volume
                    column = tabela.getColumnModel().getColumn(6);
                    column.setPreferredWidth(70);
                    column.setMaxWidth(70);
                    column.setMinWidth(70);
                    break;

                case 7: //num.ex.
                    column = tabela.getColumnModel().getColumn(7);
                    column.setPreferredWidth(70);
                    column.setMaxWidth(70);
                    column.setMinWidth(70);
                    break;

                case 8: //editora
                    column = tabela.getColumnModel().getColumn(8);
                    column.setPreferredWidth(150);
                    column.setMaxWidth(1000);
                    column.setMinWidth(100);
                    break;

                case 9: //genero
                    column = tabela.getColumnModel().getColumn(9);
                    column.setPreferredWidth(250);
                    column.setMaxWidth(1000);
                    column.setMinWidth(100);
                    break;

                case 10: //tipolivro
                    column = tabela.getColumnModel().getColumn(10);
                    column.setPreferredWidth(70);
                    column.setMaxWidth(70);
                    column.setMinWidth(70);
                    break;
            }
        }
    }

    public ArrayList<Livro> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Livro> consultar(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import utils.ComboItem;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import utils.ConexaoBD;

/**
 *
 * @author pretto
 */
public class CombosDAO {

    ResultSet resultado = null;

    // construtor 1
    public void popularCombo(String tabela, JComboBox combo) {

        combo.removeAllItems();

        ComboItem item = new ComboItem();
        item.setCodigo(0);
        item.setDescricao("Selecione");
        combo.addItem(item);

        try {
            resultado = new ConexaoBD().getConnection().createStatement()
                    .executeQuery("select * from " + tabela);

            while (resultado.next()) {
                item = new ComboItem();
                item.setCodigo(resultado.getInt(1));
                item.setDescricao(resultado.getString(2));

                combo.addItem(item);
            }

        } catch (Exception e) {
            System.out.println("Erro ao popular Combo = " + e.toString());
        }
    }

    // construtor 2
    public void popularCombo(String tabela, String campo1, String campo2,
            JComboBox combo, String complementoSQL) {

        combo.removeAllItems();

        ComboItem item = new ComboItem();
        item.setCodigo(0);
        item.setDescricao("Selecione");
        combo.addItem(item);

        try {
            resultado = new ConexaoBD().getConnection().createStatement()
                    .executeQuery("select * from " + tabela + " " + complementoSQL);

            while (resultado.next()) {
                item = new ComboItem();
                item.setCodigo(resultado.getInt(campo1));
                item.setDescricao(resultado.getString(campo2).toUpperCase());

                combo.addItem(item);
            }

        } catch (Exception e) {
            System.out.println("Erro ao popular Combo = " + e.toString());
        }
    }

    public void definirItemCombo(JComboBox combo, ComboItem item) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (((ComboItem) combo.getItemAt(i)).getCodigo() == (item.getCodigo())) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }
}

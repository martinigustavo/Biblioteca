/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import utils.VisualConfig;
import view.FrmLogin;

/**
 *
 * @author gustavo
 */
public class BibliotecaRunner {
    
    public static void appRunner() {
        VisualConfig.createLookAndFeel();
        new FrmLogin().setVisible(true);
    }
}

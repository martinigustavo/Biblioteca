/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Gustavo Martini
 */
public class EmprestimoExemplar {
    
    private int cod_emprestimo;
    private int cod_exemplar;

    public EmprestimoExemplar() {
    }

    public EmprestimoExemplar(int cod_emprestimo, int cod_exemplar) {
        this.cod_emprestimo = cod_emprestimo;
        this.cod_exemplar = cod_exemplar;
    }

    public int getCod_emprestimo() {
        return cod_emprestimo;
    }

    public void setCod_emprestimo(int cod_emprestimo) {
        this.cod_emprestimo = cod_emprestimo;
    }

    public int getCod_exemplar() {
        return cod_exemplar;
    }

    public void setCod_exemplar(int cod_exemplar) {
        this.cod_exemplar = cod_exemplar;
    }
}

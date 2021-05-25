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
public class LivroGenero {
    
    private int cod_genero;
    private int cod_livro;

    public LivroGenero() {
    }

    public LivroGenero(int cod_genero, int cod_livro) {
        this.cod_genero = cod_genero;
        this.cod_livro = cod_livro;
    }

    public int getCod_genero() {
        return cod_genero;
    }

    public void setCod_genero(int cod_genero) {
        this.cod_genero = cod_genero;
    }

    public int getCod_livro() {
        return cod_livro;
    }

    public void setCod_livro(int cod_livro) {
        this.cod_livro = cod_livro;
    }

    
}

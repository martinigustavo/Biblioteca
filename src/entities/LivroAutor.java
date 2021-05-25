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
public class LivroAutor {
    
    private int cod_autor;
    private int cod_livro;

    public LivroAutor() {
    }

    public LivroAutor(int cod_autor, int cod_livro) {
        this.cod_autor = cod_autor;
        this.cod_livro = cod_livro;
    }

    public int getCod_autor() {
        return cod_autor;
    }

    public void setCod_autor(int cod_autor) {
        this.cod_autor = cod_autor;
    }

    public int getCod_livro() {
        return cod_livro;
    }

    public void setCod_livro(int cod_livro) {
        this.cod_livro = cod_livro;
    }
}

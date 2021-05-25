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
public class Exemplar {
    
    private int id;
    private int cod_livro;
    private int cod_estado;

    public Exemplar() {
    }

    public Exemplar(int id, int cod_livro, int cod_estado) {
        this.id = id;
        this.cod_livro = cod_livro;
        this.cod_estado = cod_estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCod_livro() {
        return cod_livro;
    }

    public void setCod_livro(int cod_livro) {
        this.cod_livro = cod_livro;
    }

    public int getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(int cod_estado) {
        this.cod_estado = cod_estado;
    }

    
    
}

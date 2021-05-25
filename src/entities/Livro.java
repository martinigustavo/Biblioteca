/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author gustavo
 */
public class Livro {
    
    private Integer id;
    private String isbn;
    private String titulo;
    private String ano;
    private String edicao;
    private String volume;
    private Integer numDeExemplares;
    private Integer cod_tipolivro;
    private Integer cod_editora;

    public Livro() {
    }

    public Livro(Integer id, String isbn, String titulo, String ano, String edicao, String volume, Integer numDeExemplares, Integer cod_tipolivro, Integer cod_editora) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano = ano;
        this.edicao = edicao;
        this.volume = volume;
        this.numDeExemplares = numDeExemplares;
        this.cod_tipolivro = cod_tipolivro;
        this.cod_editora = cod_editora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Integer getNumDeExemplares() {
        return numDeExemplares;
    }

    public void setNumDeExemplares(Integer numDeExemplares) {
        this.numDeExemplares = numDeExemplares;
    }

    public Integer getCod_tipolivro() {
        return cod_tipolivro;
    }

    public void setCod_tipolivro(Integer cod_tipolivro) {
        this.cod_tipolivro = cod_tipolivro;
    }

    public Integer getCod_editora() {
        return cod_editora;
    }

    public void setCod_editora(Integer cod_editora) {
        this.cod_editora = cod_editora;
    }
    
}

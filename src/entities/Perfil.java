/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author gustavo
 */
public class Perfil {
    
    private int id;
    private String perfil;
    private int prazo;
    private int limite;
    private int qtde_renovacoes;
    private String situacao;

    public Perfil() {
    }

    public Perfil(int id, String perfil, int prazo, int limite, int qtde_renovacoes, String situacao) {
        this.id = id;
        this.perfil = perfil;
        this.prazo = prazo;
        this.limite = limite;
        this.qtde_renovacoes = qtde_renovacoes;
        this.situacao = situacao;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public int getQtde_renovacoes() {
        return qtde_renovacoes;
    }

    public void setQtde_renovacoes(int qtde_renovacoes) {
        this.qtde_renovacoes = qtde_renovacoes;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}

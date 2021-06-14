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
public class Emprestimo {
    
    private int id;
    private String data_retirada;
    private String data_devolucao;
    private String data_devolvido;
    private int renovacoes;
    private boolean devolvido;
    private int cod_usuario;
    private int cod_func;

    // lista exemplares
    
    public Emprestimo() {
    }

    public Emprestimo(int id, String data_retirada, String data_devolucao, String data_devolvido, int renovacoes, boolean devolvido, int cod_usuario, int cod_func) {
        this.id = id;
        this.data_retirada = data_retirada;
        this.data_devolucao = data_devolucao;
        this.data_devolvido = data_devolvido;
        this.renovacoes = renovacoes;
        this.devolvido = devolvido;
        this.cod_usuario = cod_usuario;
        this.cod_func = cod_func;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData_retirada() {
        return data_retirada;
    }

    public void setData_retirada(String data_retirada) {
        this.data_retirada = data_retirada;
    }

    public String getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(String data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public String getData_devolvido() {
        return data_devolvido;
    }

    public void setData_devolvido(String data_devolvido) {
        this.data_devolvido = data_devolvido;
    }

    public int getRenovacoes() {
        return renovacoes;
    }

    public void setRenovacoes(int renovacoes) {
        this.renovacoes = renovacoes;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public int getCod_func() {
        return cod_func;
    }

    public void setCod_func(int cod_func) {
        this.cod_func = cod_func;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Emprestimo{id=").append(id);
        sb.append(", data_retirada=").append(data_retirada);
        sb.append(", data_devolucao=").append(data_devolucao);
        sb.append(", data_devolvido=").append(data_devolvido);
        sb.append(", renovacoes=").append(renovacoes);
        sb.append(", devolvido=").append(devolvido);
        sb.append(", cod_usuario=").append(cod_usuario);
        sb.append(", cod_func=").append(cod_func);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}

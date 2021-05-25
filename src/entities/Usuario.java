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
public class Usuario {
    
    private int id;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String cpf;
    private String email;
    private String data_nasc;
    private String data_cadastro;
    private String telefone;
    private String situacao;
    private int cod_perfil;
    
    // lista emprestimos
    ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(int id, String nome, String sobrenome, String endereco, 
            String cpf, String email, String data_nasc, String data_cadastro, 
            String telefone, String situacao, int cod_perfil) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.cpf = cpf;
        this.email = email;
        this.data_nasc = data_nasc;
        this.data_cadastro = data_cadastro;
        this.telefone = telefone;
        this.situacao = situacao;
        this.cod_perfil = cod_perfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getCod_perfil() {
        return cod_perfil;
    }

    public void setCod_perfil(int cod_perfil) {
        this.cod_perfil = cod_perfil;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
    
}

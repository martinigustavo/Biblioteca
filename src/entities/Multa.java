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
public class Multa {
    
    private int id;
    private Double valor;
    private boolean pago;
    private String data_pgto;
    private Emprestimo emprestimo;

    public Multa() {
        pago = false;
    }

    public Multa(int id, Double valor, boolean pago, String data_pgto, Emprestimo emprestimo) {
        this.id = id;
        this.valor = valor;
        this.pago = pago;
        this.data_pgto = data_pgto;
        this.emprestimo = emprestimo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public String getData_pgto() {
        return data_pgto;
    }

    public void setData_pgto(String data_pgto) {
        this.data_pgto = data_pgto;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    @Override
    public String toString() {
        return "Multa{" + "id=" + id + ", valor=" + valor + ", pago=" + pago + ", data_pgto=" + data_pgto + ", emprestimo=" + emprestimo + '}';
    }
    
    
}

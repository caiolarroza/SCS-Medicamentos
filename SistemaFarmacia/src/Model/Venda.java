/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author caiol
 */
public class Venda {
    private int codVenda,porcentagemDesconto;
    private Cliente cliente = new Cliente();
    private Caixa caixa = new Caixa();
    private TipoPagamento tipoPagamento = new TipoPagamento();
    private ArrayList<MedQtd> medQtd;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    public int getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(int porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public ArrayList<MedQtd> getMedQtd() {
        return medQtd;
    }

    public void setMedQtd(ArrayList<MedQtd> medQtd) {
        this.medQtd = medQtd;
    }

 
    
}

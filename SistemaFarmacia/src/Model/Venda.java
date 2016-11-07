/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author caiol
 */
public class Venda {
    private int codVenda,porcentagemDesconto;
    private Cliente cliente;
    private Caixa caixa;
    private TipoPagamento tipoPagamento;
    private ArrayList<Medicamento> medicamento = new ArrayList();
    private ArrayList<List> quantidade = new ArrayList();

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

    public ArrayList<Medicamento> getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(ArrayList<Medicamento> medicamento) {
        this.medicamento = medicamento;
    }

    public ArrayList<List> getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(ArrayList<List> quantidade) {
        this.quantidade = quantidade;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mslda
 */
public class MedQtd {
    private Medicamento medicamento = new Medicamento();
    private int quantidade, codVendaMed;
    private Venda venda = new Venda();

    public int getCodVendaMed() {
        return codVendaMed;
    }

    public void setCodVendaMed(int codVendaMed) {
        this.codVendaMed = codVendaMed;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

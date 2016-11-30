/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.math.BigDecimal;

/**
 *
 * @author caiol
 */
public class TipoPagamento {
    private int codPagamento;
    private BigDecimal valor, valorDescontado;
    private Dinheiro dinheiro;
    private CartaoCredito cartao;

    public BigDecimal getValorDescontado() {
        return valorDescontado;
    }

    public void setValorDescontado(BigDecimal valorDescontado) {
        this.valorDescontado = valorDescontado;
    }

    
    
    public Dinheiro getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Dinheiro dinheiro) {
        this.dinheiro = dinheiro;
    }

    public CartaoCredito getCartao() {
        return cartao;
    }

    public void setCartao(CartaoCredito cartao) {
        this.cartao = cartao;
    }

    public int getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(int codPagamento) {
        this.codPagamento = codPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
}

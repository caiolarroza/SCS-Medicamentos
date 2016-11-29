/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author caiol
 */
public class Caixa {
    private int codCaixa, qtdDinheiro, qtdCartao;
    private static String senhaAbertura = "123", senhaFechamento = "321";
    private Date dataAbertura, dataFechamento;
    private Time horaAbertura, horaFechamento;
    private Moedas moedas = new Moedas();
    private Notas notas = new Notas();
    private boolean status;
    private BigDecimal valorTotalDinheiro, valorTotalCartao;

    public BigDecimal getValorTotalDinheiro() {
        return valorTotalDinheiro;
    }

    public void setValorTotalDinheiro(BigDecimal valorTotalDinheiro) {
        this.valorTotalDinheiro = new BigDecimal(valorTotalDinheiro.toString());
    }

    public BigDecimal getValorTotalCartao() {
        return valorTotalCartao;
    }

    public void setValorTotalCartao(BigDecimal valorTotalCartao) {
        this.valorTotalCartao = new BigDecimal(valorTotalCartao.toString());
    }

    

    public int getQtdDinheiro() {
        return qtdDinheiro;
    }

    public void setQtdDinheiro(int qtdDinheiro) {
        this.qtdDinheiro = qtdDinheiro;
    }

    public int getQtdCartao() {
        return qtdCartao;
    }

    public void setQtdCartao(int qtdCartao) {
        this.qtdCartao = qtdCartao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
    public int getCodCaixa() {
        return codCaixa;
    }

    public void setCodCaixa(int codCaixa) {
        this.codCaixa = codCaixa;
    }

    public String getSenhaAbertura() {
        return senhaAbertura;
    }

    public void setSenhaAbertura(String senhaAbertura) {
        this.senhaAbertura = senhaAbertura;
    }

    public String getSenhaFechamento() {
        return senhaFechamento;
    }

    public void setSenhaFechamento(String senhaFechamento) {
        this.senhaFechamento = senhaFechamento;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Time getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(Time horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public Time getHoraFechamento() {
        return horaFechamento;
    }

    public void setHoraFechamento(Time horaFechamento) {
        this.horaFechamento = horaFechamento;
    }

    public Moedas getMoedas() {
        return moedas;
    }

    public void setMoedas(Moedas moedas) {
        this.moedas = moedas;
    }

    public Notas getNotas() {
        return notas;
    }

    public void setNotas(Notas notas) {
        this.notas = notas;
    }


    
    
}

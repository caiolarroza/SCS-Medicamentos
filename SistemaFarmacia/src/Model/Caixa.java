/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author caiol
 */
public class Caixa {
    private int codCaixa;
    private String senhaAbertura, senhaFechamento;
    private Date dataAbertura, dataFechamento;
    private Time horaAbertura, horaFechamento;
    private Moedas moedas;
    private Notas notas;
    private Usuario usuarioAbriu, usuarioFechou;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Caixa(Moedas moedas, Notas notas) {
        this.moedas = moedas;
        this.notas = notas;
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

    public Usuario getUsuarioAbriu() {
        return usuarioAbriu;
    }

    public void setUsuarioAbriu(Usuario usuarioAbriu) {
        this.usuarioAbriu = usuarioAbriu;
    }

    public Usuario getUsuarioFechou() {
        return usuarioFechou;
    }

    public void setUsuarioFechou(Usuario usuarioFechou) {
        this.usuarioFechou = usuarioFechou;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Banco;
import DAO.CaixaDAO;
import Model.Caixa;
import Model.Moedas;
import Model.Notas;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mslda
 */
public class CtrlCaixa {
    CaixaDAO dao = new CaixaDAO(new Banco());
    
    /*private boolean verificarCaixas(){
        ArrayList<Caixa> caixas = dao.listar();
        String codigos = null;
        for(Caixa x : caixas){
            codigos += x.getCodCaixa() + "  ";
        }
        
        int resp = JOptionPane.showConfirmDialog(null, "Caixas abertos: " + codigos + "\n"
                + "Deseja abrir um novo caixa?", "Caixa", JOptionPane.YES_NO_OPTION);
        
        if(resp == JOptionPane.YES_OPTION){
            return true;
        }else if(resp == JOptionPane.NO_OPTION){
            int op = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual caixa deseja usar?\n"
                    + "Caixas abertos: " + codigos));
            
        }
    }*/
    
    public Caixa buscarCaixa(){
        Caixa caixa = (Caixa)dao.pesquisar(new Caixa());
        
        if(caixa == null){
            JOptionPane.showMessageDialog(null, "Caixa não existe no sistema!");
            return null;
        }else{
            
            return caixa;
        }
    }
    
    public boolean abrirCaixa(String senha, Caixa caixa){
        
        if(senha.equals(caixa.getSenhaAbertura())){
            Caixa aux = dao.pesquisar(caixa);
            if(aux.isStatus()){
                JOptionPane.showMessageDialog(null, "Caixa já está aberto!");
                return false;
            }else{
                if(dao.alterar(caixa)){
                    JOptionPane.showMessageDialog(null, "Caixa aberto!");
                    return true;
                }
                return false;
            } 
        }else{
            JOptionPane.showMessageDialog(null, "Senha errada!");
            return false;
        }         
    }
    
    public void fecharCaixa(String senha, Caixa caixa){
        
        if(senha.equals(caixa.getSenhaFechamento())){
            if(dao.alterar(caixa)){
                JOptionPane.showMessageDialog(null, "Caixa fechado!");
            }
        }
    }
    
    public Caixa inserirDinheiro(Moedas moedas, Notas notas, Caixa caixa){
        
        caixa.getMoedas().setQtd5(moedas.getQtd5());
        caixa.getMoedas().setQtd10(moedas.getQtd10());
        caixa.getMoedas().setQtd25(moedas.getQtd25());
        caixa.getMoedas().setQtd50(moedas.getQtd50());
        caixa.getMoedas().setQtd1Real(moedas.getQtd1Real());
        
        caixa.getNotas().setQtd2(notas.getQtd2());
        caixa.getNotas().setQtd5(notas.getQtd5());
        caixa.getNotas().setQtd10(notas.getQtd10());
        caixa.getNotas().setQtd20(notas.getQtd20());
        caixa.getNotas().setQtd50(notas.getQtd50());
        caixa.getNotas().setQtd100(notas.getQtd100());
        
        return caixa;
    }
    
    public Caixa contabilizarVendas(Caixa caixa){
        double valor = 0;
        valor += (caixa.getMoedas().getQtd5() * 0.05);
        valor += (caixa.getMoedas().getQtd10() * 0.1);
        valor += (caixa.getMoedas().getQtd25() * 0.25);
        valor += (caixa.getMoedas().getQtd50() * 0.5);
        valor += (caixa.getMoedas().getQtd1Real() * 1);
        
        valor += (caixa.getNotas().getQtd2() * 2);
        valor += (caixa.getNotas().getQtd5() * 5);
        valor += (caixa.getNotas().getQtd10() * 10);
        valor += (caixa.getNotas().getQtd20() * 20);
        valor += (caixa.getNotas().getQtd50() * 50);
        valor += (caixa.getNotas().getQtd100() * 100);
        
        caixa.setValorTotalDinheiro(BigDecimal.valueOf(valor));
        caixa.setQtdDinheiro(dao.contarVendas("dinheiro"));
        caixa.setQtdCartao(dao.contarVendas("cartaoCredito"));
        return caixa;
    }
    
    public void exibirRelatorio(Caixa caixa){
        
    }
}
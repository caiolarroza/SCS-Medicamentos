/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Banco;
import DAO.CartaoCreditoDAO;
import DAO.DinheiroDAO;
import DAO.VendaDAO;
import Model.CartaoCredito;
import Model.Dinheiro;
import Model.MedQtd;
import Model.Venda;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mslda
 */
public class CtrlVenda {
    VendaDAO dao = new VendaDAO(new Banco());
    
    private boolean validarQtdMedicamento(Venda venda){
        for(MedQtd x : venda.getMedQtd()){
            if(x.getQuantidade() > x.getMedicamento().getQtdEstoque()){
                return false;
            }
        }
        return true;
    }
    
    private int calcularDesconto(Venda venda){
        if(venda.getCliente().isAposentado()){
            return 20;
        }else if(venda.getTipoPagamento() instanceof Dinheiro){
            return 5;
        }else{
            return 0;
        }
    }
    
    private void obterPagamento(Venda venda){
        if(venda.getTipoPagamento() instanceof Dinheiro){
            //gambi para acessar a subclasse de TipoPagamento
            Dinheiro d = (Dinheiro)venda.getTipoPagamento();
            DinheiroDAO dD = new DinheiroDAO(new Banco());
            dD.inserir(d);
        }else if(venda.getTipoPagamento() instanceof CartaoCredito){
            //gambi para acessar a subclasse de TipoPagamento
            CartaoCredito cc = (CartaoCredito)venda.getTipoPagamento();
            CartaoCreditoDAO ccD = new CartaoCreditoDAO(new Banco());
            ccD.inserir(cc);
        }
    }
    
    private boolean efetuarBaixaEstoque(Venda venda){
        CtrlMedicamento med = new CtrlMedicamento();
        for(MedQtd x : venda.getMedQtd()){
            if(med.atualizarMedicamento(x.getMedicamento()) == false){
                return false;
            }
        }
            return true;
    }
    
    public void emitirNotaFiscal(){
        try {
            Banco bd = new Banco();
            bd.conectar();
            String src="../Relatorios/notaFiscal.jasper";
            
            JasperPrint jasperPrint = null;
            jasperPrint = JasperFillManager.fillReport(src, null, bd.getConexao());
            
            JasperViewer view = new JasperViewer(jasperPrint, false);
            
            view.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void cadastrarVenda(Venda venda){
            if(dao.inserir(venda)){
                JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
            }
    }
    
    public void apagarVenda(Venda venda){
        if(dao.excluir(venda)){
            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
        }
    }
    
    public List<Venda> listarVenda(Venda venda){
        return dao.listar(venda);
    }
    

}

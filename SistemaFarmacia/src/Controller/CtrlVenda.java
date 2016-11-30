/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Banco;
import DAO.CartaoCreditoDAO;
import DAO.DinheiroDAO;
import DAO.MedQtdDAO;
import DAO.MoedasDAO;
import DAO.NotasDAO;
import DAO.TipoPagamentoDAO;
import DAO.VendaDAO;
import Model.CartaoCredito;
import Model.Dinheiro;
import Model.MedQtd;
import Model.Medicamento;
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
    Banco bd = new Banco();
    MedQtdDAO medQtd = new MedQtdDAO(bd);
    CtrlMedicamento ctrlMedic = new CtrlMedicamento();
    CtrlCliente ctrlCliente = new CtrlCliente();
    TipoPagamentoDAO tpDao = new TipoPagamentoDAO(bd);
    
    
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
    
    private boolean obterPagamento(Venda venda){
        if(venda.getTipoPagamento().getDinheiro() != null){
            //gambi para acessar a subclasse de TipoPagamento
            
            venda.getTipoPagamento().getDinheiro().setCodPagamento(venda.getTipoPagamento().getCodPagamento());
            DinheiroDAO dD = new DinheiroDAO(bd);
            NotasDAO nD = new NotasDAO(bd);
            MoedasDAO mD = new MoedasDAO(bd);
            nD.inserir(venda.getTipoPagamento().getDinheiro().getNotas());
            mD.inserir(venda.getTipoPagamento().getDinheiro().getMoedas());
            venda.getTipoPagamento().getDinheiro().getNotas().setCodNotas(nD.proxCodigoExterno());
            venda.getTipoPagamento().getDinheiro().getMoedas().setCodMoedas(nD.proxCodigoExterno());
            dD.inserir(venda.getTipoPagamento().getDinheiro());
            return true;
        }else if(venda.getTipoPagamento().getCartao() != null){
            //gambi para acessar a subclasse de TipoPagamento
            venda.getTipoPagamento().getCartao().setCodPagamento(venda.getTipoPagamento().getCodPagamento());
            CartaoCreditoDAO ccD = new CartaoCreditoDAO(bd);
            ccD.inserir(venda.getTipoPagamento().getCartao());
            return true;
        }
        return false;
    }
    
    private boolean efetuarBaixaEstoque(Venda venda){
        
        for(MedQtd x : venda.getMedQtd()){
            Medicamento aux = ctrlMedic.buscarMedicamento(x.getMedicamento());
            aux.setQtdEstoque(aux.getQtdEstoque() - x.getQuantidade());
            if(ctrlMedic.atualizarMedicamento(aux) == false){
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
        
        
        if(obterPagamento(venda) && dao.inserir(venda)){
            for(MedQtd x : venda.getMedQtd()){
                x.getVenda().setCodVenda(dao.proxCodigoExterno());
                x.setMedicamento(ctrlMedic.buscarMedicamento(x.getMedicamento()));
                medQtd.inserir(x);
                
                
            }
            efetuarBaixaEstoque(venda);
            emitirNotaFiscal();
            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
        }
    }
    
    public Venda buscarVenda(Venda venda){
        Venda aux = dao.pesquisar(venda);
        if(aux == null){
            JOptionPane.showMessageDialog(null, "Venda não existe no sistema!");
            return null;
        }else{
            venda = aux;
            venda.setCliente(ctrlCliente.buscarClienteCod(venda.getCliente()));
            venda.setTipoPagamento(tpDao.pesquisar(venda.getTipoPagamento()));
            return venda;
        }
    }
    
    public void apagarVenda(Venda venda){
        if(dao.excluir(venda)){
            JOptionPane.showMessageDialog(null, "Venda apagada com sucesso!");
        }
    }
    
    public List<Venda> listarVenda(Venda venda){
        return dao.listar(venda);
    }
    
    public List<Venda> listarTodas(){
        return dao.listarTodas();
    }
}

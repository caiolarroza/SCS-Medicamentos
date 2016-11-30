/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Banco;
import DAO.MedicamentoDAO;
import Model.Medicamento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mslda
 */
public class CtrlMedicamento {
    Banco bd = new Banco();
    MedicamentoDAO dao = new MedicamentoDAO(bd);
    CtrlFornecedor forne = new CtrlFornecedor();
    
    public boolean validarDataValidade(Medicamento medic){
        try{
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date data = formato.parse(medic.getDataValidade());
            java.util.Date dataNow = new java.util.Date();
            
            return dataNow.compareTo(data) < 0;
        }catch(ParseException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
    public boolean cadastrarMedicamento(Medicamento medic){
        Medicamento aux = (Medicamento)dao.pesquisar(medic);
        if(aux == null){
            if(validarDataValidade(medic)){
                if(forne.buscarFornecedor(medic.getFornecedor()) != null){
                    if(dao.inserir(medic)){
                        JOptionPane.showMessageDialog(null, "Medicamento cadastrado com sucesso!");
                        return true;
                    }
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Medicamento com prazo de validade vencido!");
                return false;
            }
            
        }
        return false;
    }
    
    public Medicamento buscarMedicamento(Medicamento medic){
        medic = (Medicamento)dao.pesquisar(medic);
        if(medic == null){
            JOptionPane.showMessageDialog(null, "Medicamento não existe no sistema!");
            return null;
        }else{
            medic.setFornecedor(forne.buscarFornecedor(medic.getFornecedor()));
            return medic;
        }
    }
    
    public Medicamento buscarMedicamentoCod(Medicamento medic){
        medic = (Medicamento)dao.pesquisarCOD(medic);
        if(medic == null){
            JOptionPane.showMessageDialog(null, "Medicamento não existe no sistema!");
            return null;
        }else{
            medic.setFornecedor(forne.buscarFornecedor(medic.getFornecedor()));
            return medic;
        }
    }
    
    public boolean atualizarMedicamento(Medicamento medic){
        if(validarDataValidade(medic)){
            if(dao.alterar(medic)){
                //JOptionPane.showMessageDialog(null, "Medicamento atualizado com sucesso!");
                return true;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Medicamento com prazo de validade vencido!");
            return false;
        }
        return false;
    }
    
    public boolean apagarMedicamento(Medicamento medic){
        if(dao.excluir(medic)){
            JOptionPane.showMessageDialog(null, "Medicamento excluido com sucesso!");
            return true;
        }
        return false;
    }
    
    public List<Medicamento> listarMedicamento(){
        return dao.listar();
    }
}

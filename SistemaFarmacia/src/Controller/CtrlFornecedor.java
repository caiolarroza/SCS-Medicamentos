/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Banco;
import DAO.DAO;
import DAO.EnderecoDAO;
import DAO.FornecedorDAO;
import Model.Fornecedor;
import javax.swing.JOptionPane;

/**
 *
 * @author mslda
 */
public class CtrlFornecedor {
    Banco bd = new Banco();
    FornecedorDAO dao = new FornecedorDAO(bd);
    EnderecoDAO endereco = new EnderecoDAO(bd);
    
    public void cadastrarFornecedor(Fornecedor forne){
        Fornecedor aux = (Fornecedor)dao.pesquisar(forne);
        if(aux == null){
            if(endereco.inserir(forne.getEndereco())){
                if(dao.inserir(forne)){
                    JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Fornecedor já cadastrado no sistema!");
        }
    }
    
    public Fornecedor buscarFornecedor(Fornecedor forne){
        forne = (Fornecedor)dao.pesquisar(forne);
        if(forne == null){
            JOptionPane.showMessageDialog(null, "Fornecedor não existe no sistema!");
            return null;
        }else{
            forne.setEndereco(endereco.pesquisar(forne.getEndereco()));
            
            return forne;
        }
    }
    
    public void atualizarFornecedor(Fornecedor forne){
        if(dao.alterar(forne)){
            if(endereco.alterar(forne.getEndereco())){
                JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!");
            }
            
        }
    }
    
    public void apagarFornecedor(Fornecedor forne){
        if(dao.excluir(forne) && endereco.excluir(forne.getEndereco())){
            JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso!");
        }
    }
}

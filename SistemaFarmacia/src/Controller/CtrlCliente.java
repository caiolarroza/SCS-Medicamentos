/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Banco;
import Model.Cliente;
import DAO.ClienteDAO;
import DAO.EnderecoDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author mslda
 */
public class CtrlCliente {
    Banco bd = new Banco();
    ClienteDAO dao = new ClienteDAO(bd);
    EnderecoDAO endereco = new EnderecoDAO(bd);
    
    public void cadastrarCliente(Cliente cliente){
        Cliente aux = (Cliente)dao.pesquisar(cliente);
        if(aux == null){
            if(endereco.inserir(cliente.getEndereco())){
                
                if(dao.inserir(cliente)){
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "CPF já cadastrado no sistema!");
        }
    }
    
    public Cliente buscarCliente(Cliente cliente){
        cliente = (Cliente)dao.pesquisar(cliente);
        if(cliente == null){
            JOptionPane.showMessageDialog(null, "CPF não existe no sistema!");
            return null;
        }else{
            
            cliente.setEndereco(endereco.pesquisar(cliente.getEndereco()));
            return cliente;
        }
    }
    
    public Cliente buscarClienteCod(Cliente cliente){
        cliente = (Cliente)dao.pesquisarCod(cliente);
        if(cliente == null){
            JOptionPane.showMessageDialog(null, "Codigo não existe no sistema!");
            return null;
        }else{
            
            
            return cliente;
        }
    }
    
    public void atualizarCliente(Cliente cliente){
        if(dao.alterar(cliente)){
            if(endereco.alterar(cliente.getEndereco())){
                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            }else{
                JOptionPane.showMessageDialog(null, "Não foi");
            }
            
        }
    }
    
    public void apagarCliente(Cliente cliente){
        if(dao.excluir(cliente) && endereco.excluir(cliente.getEndereco())){
            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
            
        }
    }
    
    
}

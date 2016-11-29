/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.Banco;
import DAO.UsuarioDAO;
import Model.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author mslda
 */
public class CtrlUsuario {
    
    UsuarioDAO dao = new UsuarioDAO(new Banco());
    
    //verifica se o usuário é gerente ou não
    public boolean verificarUsuario(Usuario usuario){
        if(usuario.getTipoUsuario().equals("GERENTE")){
            return true;
        }
        return false;
    }
    
    public void cadastrarUsuario(Usuario usuario){
        Usuario aux = (Usuario)dao.pesquisar(usuario);
        if(aux == null){
            if(dao.inserir(usuario)){
                JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Usuario já cadastrado no sistema!");
        }
    }
    
    public void atualizarUsuario(Usuario usuario){
        if(dao.alterar(usuario)){
            JOptionPane.showMessageDialog(null, "Usuario atualizado com sucesso!");
        }
    }
    
    public Usuario buscarUsuario(Usuario usuario){
        usuario = (Usuario)dao.pesquisar(usuario);
        if(usuario == null){
            JOptionPane.showMessageDialog(null, "Usuario não existe no sistema!");
            return null;
        }else{
            return usuario;
        }
    }
    
    public void apagarUsuario(Usuario usuario){
        if(dao.excluir(usuario)){
            JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso!");
        }else{
            JOptionPane.showMessageDialog(null, "Usuario não pode ser excluido!");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class UsuarioDAO implements DAO<Usuario>{

    //variaveis auxiliares
    Banco bd;
    PreparedStatement pst;
    ResultSet rs;
    
    @Override
    public boolean inserir(Usuario obj) {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into usuario values (?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setString(2, obj.getLogin());
            pst.setString(3, obj.getSenha());
            pst.setString(4, obj.getTipoUsuario());

            //verifica se o update foi efetuado e retorna true ou false
            return pst.executeUpdate() > 0;
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro na inserção\n"
                     + ex.getMessage());
             return false;
        }finally{
            bd.fechaConexao();
        }
    }

    @Override
    public boolean alterar(Usuario obj) {
        try{
            bd.conectar();
            pst = bd.getConexao().prepareStatement(
            "update usuario set login = ?, senha = ?,"
                    + "tipoUsuario = ? WHERE codUsuario = ?");
            pst.setString(1, obj.getLogin());
            pst.setString(2, obj.getSenha());
            pst.setString(3, obj.getTipoUsuario());
            pst.setInt(4, obj.getCodUsuario());
            return pst.executeUpdate() > 0;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro no Update\n"
                     + ex.getMessage());
             return false;
        }finally {
            bd.fechaConexao();
        }
    }

    @Override
    public boolean excluir(Usuario obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "DELETE FROM usuario WHERE codUsuario = ?");
            pst.setInt(1, obj.getCodUsuario());
            return pst.executeUpdate() > 0;
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro no Delete\n"
                     + ex.getMessage());
             return false;
        } finally {
            bd.fechaConexao();
        }
    }

    @Override
    public Usuario pesquisar(Usuario obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*@Override
    public List<Usuario> listar(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public int proxCodigo() {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(//comando SQL
            "select ifnull(max(codUsuario), 0) + 1 as numero from usuario");
                
            rs = pst.executeQuery();//Executa o comando
                    
            rs.next();//retorna o valor do BD
            
            return rs.getInt("numero");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro na Pesquisa\n"
                     + ex.getMessage());
             return -1;
        }finally{
            bd.fechaConexao();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Endereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class EnderecoDAO implements DAO<Endereco>{
    //variaveis auxiliares
    Banco bd;
    PreparedStatement pst;
    ResultSet rs;
    @Override
    public boolean inserir(Endereco obj) {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into endereco values (?, ?, ?, ?, ?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setString(2, obj.getLogradouro());
            pst.setString(3, obj.getNumero());
            pst.setString(4, obj.getComplemento());
            pst.setString(5, obj.getBairro());
            pst.setString(6, obj.getCep());
            pst.setString(7, obj.getCidade());
            pst.setString(8, obj.getEstado());
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
    public boolean alterar(Endereco obj) {
        try{
            bd.conectar();
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "update endereco set logradouro = ?, numero = ?,"
                    + "complemento = ?, bairro = ?, cep = ?, cidade = ?,"
                    + "estado = ? WHERE codEndereco = ?");

            pst.setString(1, obj.getLogradouro());
            pst.setString(2, obj.getNumero());
            pst.setString(3, obj.getComplemento());
            pst.setString(4, obj.getBairro());
            pst.setString(5, obj.getCep());
            pst.setString(6, obj.getCidade());
            pst.setString(7, obj.getEstado());
            pst.setInt(8, obj.getCodEndereco());
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
    public boolean excluir(Endereco obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "DELETE FROM endereco WHERE codEndereco = ?");
            pst.setInt(1, obj.getCodEndereco());
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
    public Endereco pesquisar(Endereco obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM endereco WHERE codEndereco = ?");
            pst.setInt(1, obj.getCodEndereco());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setLogradouro("logradouro");
                obj.setNumero("numero");
                obj.setComplemento("complemento");
                obj.setBairro("bairro");
                obj.setCep("cep");
                obj.setCidade("cidade");
                obj.setEstado("estado");
                return obj;
            } else
                return null;            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro na Pesquisa\n"
                     + ex.getMessage());
             return null;
        } finally {
            bd.fechaConexao();
        }
    }

    /*@Override
    public List<Endereco> listar(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public int proxCodigo() {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(//comando SQL
            "select ifnull(max(codEndereco), 0) + 1 as numero from endereco");
                
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

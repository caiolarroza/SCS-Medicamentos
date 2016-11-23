/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Fornecedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class FornecedorDAO implements DAO<Fornecedor>{

    //variaveis auxiliares
    Banco bd;
    PreparedStatement pst;
    ResultSet rs;
    
    @Override
    public boolean inserir(Fornecedor obj) {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into fornecedor values (?, ?, ?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setString(2, obj.getNome());
            pst.setString(3, obj.getTelefone());
            pst.setString(4, obj.getCelular());
            pst.setString(5, obj.getCnpj());
            pst.setInt(6, obj.getEndereco().getCodEndereco());

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
    public boolean alterar(Fornecedor obj) {
        try{
            bd.conectar();
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "update fornecedor set nome = ?, telefone = ?,"
                    + "celular = ?, cnpj = ? WHERE codFornecedor = ?");

            pst.setInt(1, proxCodigo());
            pst.setString(2, obj.getNome());
            pst.setString(3, obj.getTelefone());
            pst.setString(4, obj.getCelular());
            pst.setString(5, obj.getCnpj());
            pst.setInt(6, obj.getCodFornecedor());
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
    public boolean excluir(Fornecedor obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "DELETE FROM fornecedor WHERE codFornecedor = ?");
            pst.setInt(1, obj.getCodFornecedor());
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
    public Fornecedor pesquisar(Fornecedor obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM fornecedor WHERE cnpj = ?");
            pst.setString(1, obj.getCnpj());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setCodFornecedor(rs.getInt("codFornecedor"));
                obj.setNome("nome");
                obj.setTelefone("telefone");
                obj.setCelular("celular");
                obj.setCnpj("cnpj");
                obj.getEndereco().setCodEndereco(rs.getInt("codEndereco"));
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
    public List<Fornecedor> listar(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public int proxCodigo() {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(//comando SQL
            "select ifnull(max(codFornecedor), 0) + 1 as numero from fornecedor");
                
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

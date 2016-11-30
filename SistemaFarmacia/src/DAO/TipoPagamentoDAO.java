/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.TipoPagamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class TipoPagamentoDAO implements DAO<TipoPagamento>{

    //variaveis auxiliares
    Banco bd;
    
    ResultSet rs;

    public TipoPagamentoDAO(Banco bd) {
        this.bd = bd;
    }
    
    
    
    @Override
    public boolean inserir(TipoPagamento obj) {
        try{
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into tipoPagamento values (?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setBigDecimal(2, obj.getValor());
         
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
    public boolean alterar(TipoPagamento obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(TipoPagamento obj) {
        try {
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "DELETE FROM tipoPagamento WHERE codPagamento = ?");
            pst.setInt(1, obj.getCodPagamento());
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
    public TipoPagamento pesquisar(TipoPagamento obj) {
        try {
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM tipoPagamento WHERE codPagamento = ?");
            pst.setInt(1, obj.getCodPagamento());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setValor(rs.getBigDecimal("valorTotal"));
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
    public List<TipoPagamento> listar(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public int proxCodigo() {
        try{
            PreparedStatement pst;
            
            pst = bd.getConexao().prepareStatement(//comando SQL
            "select ifnull(max(codPagamento), 0) + 1 as numero from tipoPagamento");
                
            rs = pst.executeQuery();//Executa o comando
                    
            rs.next();//retorna o valor do BD
            
            return rs.getInt("numero");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro na Pesquisa\n"
                     + ex.getMessage());
             return -1;
        }finally{
            
        }
    }
    
    public int proxCodigoExterno(){
        try{
            PreparedStatement pst;
            bd.conectar();
            pst = bd.getConexao().prepareStatement(//comando SQL
            "select ifnull(max(codPagamento), 0) + 1 as numero from tipoPagamento");
                
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
    


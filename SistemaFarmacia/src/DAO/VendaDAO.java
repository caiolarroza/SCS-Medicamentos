/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Venda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class VendaDAO implements DAO<Venda>{

    //variaveis auxiliares
    Banco bd;
    PreparedStatement pst;
    ResultSet rs;
    
    @Override
    public boolean inserir(Venda obj) {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into venda values (?, ?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setInt(2, obj.getPorcentagemDesconto());
            pst.setInt(3, obj.getCliente().getCodCliente());
            pst.setInt(4, obj.getTipoPagamento().getCodPagamento());
            pst.setInt(5, obj.getCaixa().getCodCaixa());
            
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
    public boolean alterar(Venda obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(Venda obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "DELETE FROM venda WHERE codVenda = ?");
            pst.setInt(1, obj.getCodVenda());
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
    public Venda pesquisar(Venda obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM venda WHERE codVenda = ?");
            pst.setInt(1, obj.getCodVenda());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setPorcentagemDesconto(rs.getInt("qtd5"));
                obj.getCliente().setCodCliente(rs.getInt("qtd10"));
                obj.getTipoPagamento().setCodPagamento(rs.getInt("qtd25"));
                obj.getCaixa().setCodCaixa(rs.getInt("qtd50"));
               
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
    public List<Venda> listar(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public int proxCodigo() {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(//comando SQL
            "select ifnull(max(codVenda), 0) + 1 as numero from venda");
                
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

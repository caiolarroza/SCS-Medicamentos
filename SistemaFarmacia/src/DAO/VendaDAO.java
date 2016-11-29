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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class VendaDAO implements DAO<Venda>{

    //variaveis auxiliares
    Banco bd;
    
    ResultSet rs;

    public VendaDAO(Banco bd) {
        this.bd = bd;
    }
    
    
    
    private java.sql.Date getData(){
        //data formatada para ir para o BD
        Date data = new Date();
        java.sql.Date dataSQL = new java.sql.Date(data.getTime());
        return dataSQL;
    }
    
    private String converteDataView(String dataV){
        try {
            //data formatada para ir para a View
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date data = formato.parse(dataV);
            formato.applyPattern("dd/MM/yyyy");
            dataV = formato.format(data);
            return dataV;
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    @Override
    public boolean inserir(Venda obj) {
        try{
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into venda values (?, ?, ?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setInt(2, obj.getPorcentagemDesconto());
            pst.setInt(3, obj.getCliente().getCodCliente());
            pst.setInt(4, obj.getTipoPagamento().getCodPagamento());
            pst.setInt(5, obj.getCaixa().getCodCaixa());
            pst.setDate(6, getData());
            
            
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
            PreparedStatement pst;
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
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM venda WHERE codVenda = ?");
            pst.setInt(1, obj.getCodVenda());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setPorcentagemDesconto(rs.getInt("porcentagemDesconto"));
                obj.getCliente().setCodCliente(rs.getInt("codCliente"));
                obj.getTipoPagamento().setCodPagamento(rs.getInt("codPagamento"));
                obj.getCaixa().setCodCaixa(rs.getInt("codCaixa"));
                obj.setData(converteDataView(rs.getDate("data").toString()));
               
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

    
    public List<Venda> listar(Venda obj) {
        List vendas = new ArrayList();
        try {
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM venda WHERE codCliente = ?");
            pst.setInt(1, obj.getCliente().getCodCliente());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            while(rs.next()) { //achou
                Venda aux = new Venda();
                aux.setCodVenda(rs.getInt("CodVenda"));
                aux.setPorcentagemDesconto(rs.getInt("porcentagemDesconto"));
                aux.getCliente().setCodCliente(rs.getInt("codCliente"));
                aux.getTipoPagamento().setCodPagamento(rs.getInt("codPagamento"));
                aux.getCaixa().setCodCaixa(rs.getInt("codCaixa"));
                aux.setData(converteDataView(rs.getDate("data").toString()));
                vendas.add(aux);
            } 
            return vendas;
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro na Pesquisa\n"
                     + ex.getMessage());
             return null;
        } finally {
            bd.fechaConexao();
        }
    }

    @Override
    public int proxCodigo() {
        try{
            PreparedStatement pst;
            
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
            
        }
    }
    
}

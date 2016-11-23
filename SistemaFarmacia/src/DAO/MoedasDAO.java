/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Moedas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class MoedasDAO implements DAO<Moedas>{
    
    //variaveis auxiliares
    Banco bd;
    PreparedStatement pst;
    ResultSet rs;
    
    @Override
    public boolean inserir(Moedas obj) {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into moedas values (?, ?, ?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setInt(2, obj.getQtd5());
            pst.setInt(3, obj.getQtd10());
            pst.setInt(4, obj.getQtd25());
            pst.setInt(5, obj.getQtd50());
            pst.setInt(6, obj.getQtd1Real());
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
    public boolean alterar(Moedas obj) {
        try{
            bd.conectar();
            pst = bd.getConexao().prepareStatement(
            "update moedas set qtd5 = ?, qtd10 = ?,"
                    + "qtd25 = ?, qtd50 = ?, qtd1Real = ? WHERE codMoedas = ?");
            pst.setInt(1, obj.getQtd5());
            pst.setInt(2, obj.getQtd10());
            pst.setInt(3, obj.getQtd25());
            pst.setInt(4, obj.getQtd50());
            pst.setInt(5, obj.getQtd1Real());
            pst.setInt(6, obj.getCodMoedas());
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
    public boolean excluir(Moedas obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "DELETE FROM moedas WHERE codMoedas = ?");
            pst.setInt(1, obj.getCodMoedas());
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
    public Moedas pesquisar(Moedas obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM moedas WHERE codMoedas = ?");
            pst.setInt(1, obj.getCodMoedas());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setQtd5(rs.getInt("qtd5"));
                obj.setQtd10(rs.getInt("qtd10"));
                obj.setQtd25(rs.getInt("qtd25"));
                obj.setQtd50(rs.getInt("qtd50"));
                obj.setQtd1Real(rs.getInt("qtd1Real"));
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
    public List<Moedas> listar(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public int proxCodigo() {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(//comando SQL
            "select ifnull(max(codMoedas), 0) + 1 as numero from moedas");
                
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

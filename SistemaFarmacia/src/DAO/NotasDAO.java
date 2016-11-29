/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Notas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class NotasDAO implements DAO<Notas>{

    //variaveis auxiliares
    Banco bd;
    
    ResultSet rs;

    public NotasDAO(Banco bd) {
        this.bd = bd;
    }
    
    
    
    @Override
    public boolean inserir(Notas obj) {
        try{
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into notas values (?, ?, ?, ?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setInt(2, obj.getQtd2());
            pst.setInt(3, obj.getQtd5());
            pst.setInt(4, obj.getQtd10());
            pst.setInt(5, obj.getQtd20());
            pst.setInt(6, obj.getQtd50());
            pst.setInt(7, obj.getQtd100());
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
    public boolean alterar(Notas obj) {
        try{
            PreparedStatement pst;
            bd.conectar();
            pst = bd.getConexao().prepareStatement(
            "update notas set qtd2 = ?, qtd5 = ?,"
                    + "qtd10 = ?, qtd20 = ?, qtd50 = ?, qtd100 = ? WHERE codNotas = ?");
            pst.setInt(1, obj.getQtd2());
            pst.setInt(2, obj.getQtd5());
            pst.setInt(3, obj.getQtd10());
            pst.setInt(4, obj.getQtd20());
            pst.setInt(5, obj.getQtd50());
            pst.setInt(6, obj.getQtd100());
            pst.setInt(7, obj.getCodNotas());
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
    public boolean excluir(Notas obj) {
        try {
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "DELETE FROM notas WHERE codNotas = ?");
            pst.setInt(1, obj.getCodNotas());
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
    public Notas pesquisar(Notas obj) {
        try {
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM notas WHERE codNotas = ?");
            pst.setInt(1, obj.getCodNotas());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setQtd2(rs.getInt("qtd2"));
                obj.setQtd5(rs.getInt("qtd5"));
                obj.setQtd10(rs.getInt("qtd10"));
                obj.setQtd20(rs.getInt("qtd20"));
                obj.setQtd50(rs.getInt("qtd50"));
                obj.setQtd100(rs.getInt("qtd100"));
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
    public List<Notas> listar(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public int proxCodigo() {
        try{
            PreparedStatement pst;
            
            pst = bd.getConexao().prepareStatement(//comando SQL
            "select ifnull(max(codNotas), 0) + 1 as numero from notas");
                
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.MedQtd;
import Model.Medicamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mslda
 */
public class MedQtdDAO implements DAO<MedQtd>{

    //variaveis auxiliares
    Banco bd;
    
    ResultSet rs;

    public MedQtdDAO(Banco bd) {
        this.bd = bd;
    }
    
    @Override
    public boolean inserir(MedQtd obj) {
        
        try{
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into vendaMedicamento values (?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setInt(2, obj.getVenda().getCodVenda());
            pst.setInt(3, obj.getMedicamento().getCodMedicamento());
            pst.setInt(4, obj.getQuantidade());
           
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
    public boolean alterar(MedQtd obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluir(MedQtd obj) {
        try {
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "DELETE FROM vendaMedicamento WHERE codVenda = ?");
            pst.setInt(1, obj.getVenda().getCodVenda());
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
    public MedQtd pesquisar(MedQtd obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<MedQtd> listar(MedQtd obj) {
        try {
            PreparedStatement pst;
            ArrayList<MedQtd> meds = new ArrayList<>();
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM vendaMedicamento where codVenda = ?");
            pst.setInt(1, obj.getVenda().getCodVenda());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            while(rs.next()) { //achou
                MedQtd aux = new MedQtd();
                aux.setCodVendaMed(rs.getInt("codVendaMed"));
                aux.getVenda().setCodVenda(rs.getInt("codVenda"));
                aux.getMedicamento().setCodMedicamento(rs.getInt("codMedicamento"));
                aux.setQuantidade(rs.getInt("quantidade"));
                
                
                
                meds.add(aux);
            }           
            
            
            
            return meds;
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
            "select ifnull(max(codVendaMed), 0) + 1 as numero from vendaMedicamento");
                
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

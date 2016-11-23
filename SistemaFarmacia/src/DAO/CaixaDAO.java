/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Caixa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class CaixaDAO implements DAO<Caixa>{

    //variaveis auxiliares
    Banco bd;
    PreparedStatement pst;
    ResultSet rs;
    
    Date data;
    
    public java.sql.Date getData(){
        //data formatada para ir para o BD
        data = new Date();
        java.sql.Date dataSQL = new java.sql.Date(data.getTime());
        return dataSQL;
    }
    
    public java.sql.Time getHora(){
        //hora formatada para ir para o BD
        data = new Date();
        java.sql.Time horaSQL = new java.sql.Time(data.getTime());
        return horaSQL;
    }
    
    
    @Override
    public boolean inserir(Caixa obj) {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into caixa values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setDate(2, getData());
            pst.setDate(3, null);
            pst.setTime(4, getHora());
            pst.setTime(5, null);
            pst.setInt(6, obj.getNotas().getCodNotas());
            pst.setInt(7, obj.getMoedas().getCodMoedas());
            pst.setInt(8, obj.getUsuarioAbriu().getCodUsuario());
            pst.setInt(9, obj.getUsuarioFechou().getCodUsuario());
            pst.setBoolean(10, obj.isStatus());
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
    public boolean alterar(Caixa obj) {
        try{
            bd.conectar();
            pst = bd.getConexao().prepareStatement(
            "update caixa set dataFechamento = ?, horaFechamento = ?,"
                    + "usuarioFechou = ?, status = ? where codCaixa = ?");
            pst.setDate(1, getData());
            pst.setTime(2, getHora());
            pst.setInt(3, obj.getUsuarioFechou().getCodUsuario());
            pst.setBoolean(4, obj.isStatus());
            pst.setInt(5, obj.getCodCaixa());
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
    public boolean excluir(Caixa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Caixa pesquisar(Caixa obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM caixa WHERE codCaixa = ?");
            pst.setInt(1, obj.getCodCaixa());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setDataAbertura(rs.getDate("dataAbertura"));
                obj.setDataFechamento(rs.getDate("dataFechamento"));
                obj.setHoraAbertura(rs.getTime("horaAbertura"));
                obj.setHoraAbertura(rs.getTime("horaFechamento"));
                obj.getNotas().setCodNotas(rs.getInt("codNotas"));
                obj.getMoedas().setCodMoedas(rs.getInt("codMoedas"));
                obj.getUsuarioAbriu().setCodUsuario(rs.getInt("usuarioAbriu"));
                obj.getUsuarioFechou().setCodUsuario(rs.getInt("usuarioFechou"));
                obj.setStatus(rs.getBoolean("status"));
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
    public List<Caixa> listar(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public int proxCodigo() {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(//comando SQL
            "select ifnull(max(codCidade), 0) + 1 as numero from cidade");
                
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

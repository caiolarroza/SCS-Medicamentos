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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class CaixaDAO implements DAO<Caixa>{

    //variaveis auxiliares
    Banco bd;
    
    ResultSet rs;
    
    Date data;

    public CaixaDAO(Banco bd) {
        this.bd = bd;
    }
    
    
    private java.sql.Date getData(){
        //data formatada para ir para o BD
        data = new Date();
        java.sql.Date dataSQL = new java.sql.Date(data.getTime());
        return dataSQL;
    }
    
    private java.sql.Time getHora(){
        //hora formatada para ir para o BD
        data = new Date();
        java.sql.Time horaSQL = new java.sql.Time(data.getTime());
        return horaSQL;
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
    
    public double valorTotalCartao(){
        try{
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            
            pst = bd.getConexao().prepareStatement(//comando SQL
                    "select sum(tp.valorTotal) as valor from tipoPagamento tp\n" +
                        "inner join cartaoCredito cc on tp.codPagamento = cc.codPagamento\n" +
                        "inner join venda v on v.codPagamento = tp.codPagamento\n" +
                        "where v.data = curdate()");
            

            rs = pst.executeQuery();//Executa o comando
                    
            rs.next();//retorna o valor do BD
            
            return rs.getDouble("valor");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro na Pesquisa\n"
                     + ex.getMessage());
             return -1;
        }finally{
            bd.fechaConexao();
        }
    }
    
    public int contarVendas(String tipo){
        try{
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            if(tipo.equals("dinheiro")){
                pst = bd.getConexao().prepareStatement(//comando SQL
                    "select count(d.codPagamento) as qtd from dinheiro d \n" +
                    "inner join venda v on v.data = curdate()\n" +
                    "where d.codPagamento = v.codPagamento;");
            }else{
                pst = bd.getConexao().prepareStatement(//comando SQL
                    "select count(cc.codPagamento) as qtd from cartaoCredito cc \n" +
                    "inner join venda v on v.data = curdate()\n" +
                    "where cc.codPagamento = v.codPagamento;");
            }

            rs = pst.executeQuery();//Executa o comando
                    
            rs.next();//retorna o valor do BD
            
            return rs.getInt("qtd");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro na Pesquisa\n"
                     + ex.getMessage());
             return -1;
        }finally{
            bd.fechaConexao();
        }
    }
    
    @Override
    public boolean inserir(Caixa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(Caixa obj) {
        try{
            if(obj.isStatus()){
                obj.setDataFechamento(getData());
                obj.setHoraFechamento(getHora());
                obj.setStatus(false);
            }else{
                obj.setDataAbertura(getData());
                obj.setHoraAbertura(getHora());
                obj.setDataFechamento(null);
                obj.setHoraFechamento(null);
                obj.setStatus(true);
            }
            
            PreparedStatement pst;
            bd.conectar();
            pst = bd.getConexao().prepareStatement(
            "update caixa set dataAbertura = ?, horaAbertura = ?, dataFechamento = ?, horaFechamento = ?,"
                    + "status = ? where codCaixa = 1");
            pst.setDate(1, obj.getDataAbertura());
            pst.setTime(2, obj.getHoraAbertura());
            pst.setDate(3, obj.getDataFechamento());
            pst.setTime(4, obj.getHoraFechamento());       
            pst.setBoolean(5, obj.isStatus());
            
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
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM caixa WHERE codCaixa = 1");
            
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setDataAbertura(rs.getDate("dataAbertura"));
                obj.setDataFechamento(rs.getDate("dataFechamento"));
                obj.setHoraAbertura(rs.getTime("horaAbertura"));
                obj.setHoraFechamento(rs.getTime("horaFechamento"));
                obj.getNotas().setCodNotas(rs.getInt("codNotas"));
                obj.getMoedas().setCodMoedas(rs.getInt("codMoedas"));
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

    
    /*public ArrayList<Caixa> listar() {
        try {
            PreparedStatement pst;
            ArrayList<Caixa> caixas = new ArrayList<>();
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM caixa WHERE status = true");
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                Caixa obj = new Caixa();
                obj.setCodCaixa(rs.getInt("codCaixa"));
                obj.setDataAbertura(rs.getDate("dataAbertura"));
                obj.setDataFechamento(rs.getDate("dataFechamento"));
                obj.setHoraAbertura(rs.getTime("horaAbertura"));
                obj.setHoraAbertura(rs.getTime("horaFechamento"));
                obj.getNotas().setCodNotas(rs.getInt("codNotas"));
                obj.getMoedas().setCodMoedas(rs.getInt("codMoedas"));

                obj.setStatus(rs.getBoolean("status"));
                caixas.add(obj);
            } 
            return caixas;
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro na Pesquisa\n"
                     + ex.getMessage());
             return null;
        } finally {
            bd.fechaConexao();
        }
    }*/

    @Override
    public int proxCodigo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}

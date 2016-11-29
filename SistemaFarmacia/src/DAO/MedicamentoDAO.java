/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Medicamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
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
public class MedicamentoDAO implements DAO<Medicamento>{
    
    //variaveis auxiliares
    Banco bd;
    
    ResultSet rs;

    public MedicamentoDAO(Banco bd) {
        this.bd = bd;
    }
    
    
    private java.sql.Date getData(){
        //data formatada para ir para o BD
        Date data = new Date();
        java.sql.Date dataSQL = new java.sql.Date(data.getTime());
        return dataSQL;
    }
    
    private java.sql.Date converteDataSQL(String dataS){
        //data formatada para ir para o BD
        try{
            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date data = formato.parse(dataS);
            java.sql.Date dataSQL = new java.sql.Date(data.getTime());
            return dataSQL;
        }catch(ParseException ex){
            System.out.println(ex.getMessage());
            return null;
        }
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
    public boolean inserir(Medicamento obj) {
        try{
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into medicamento values (?, ?, ?, ?, ?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setString(2, obj.getNome());
            pst.setInt(3, obj.getFornecedor().getCodFornecedor());
            pst.setDate(4, converteDataSQL(obj.getDataValidade()));
            pst.setBigDecimal(5, obj.getPreco());
            pst.setInt(6, obj.getQtdEstoque());
            pst.setInt(7, obj.getLote());
            pst.setDate(8, getData());
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
    public boolean alterar(Medicamento obj) {
        try{
            PreparedStatement pst;
            bd.conectar();
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "update medicamento set nome = ?, codFornecedor = ?,"
                    + "dataValidade = ?, preco = ?, qtdEstoque = ?, lote = ?"
                            + " WHERE codMedicamento = ?");

            pst.setString(1, obj.getNome());
            pst.setInt(2, obj.getFornecedor().getCodFornecedor());
            pst.setDate(3, converteDataSQL(obj.getDataValidade()));
            pst.setBigDecimal(4, obj.getPreco());
            pst.setInt(5, obj.getQtdEstoque());
            pst.setInt(6, obj.getLote());
            pst.setInt(7, obj.getCodMedicamento());
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
    public boolean excluir(Medicamento obj) {
        try {
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "DELETE FROM medicamento WHERE codMedicamento = ?");
            pst.setInt(1, obj.getCodMedicamento());
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
    public Medicamento pesquisar(Medicamento obj) {
        try {
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM medicamento WHERE nome = ?");
            pst.setString(1, obj.getNome());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setCodMedicamento(rs.getInt("codMedicamento"));
                obj.setNome(rs.getString("nome"));
                obj.getFornecedor().setCodFornecedor(rs.getInt("codFornecedor"));
                obj.setDataValidade(converteDataView(rs.getDate("dataValidade").toString()));
                obj.setPreco(rs.getBigDecimal("preco"));
                obj.setQtdEstoque(rs.getInt("qtdEstoque"));
                obj.setLote(rs.getInt("lote"));
                obj.setDataEntrada(converteDataView(rs.getDate("dataEntrada").toString()));
                
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

    
    public List<Medicamento> listar() {
        try {
            PreparedStatement pst;
            ArrayList<Medicamento> meds = new ArrayList<>();
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM medicamento");
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            while(rs.next()) { //achou
                Medicamento obj = new Medicamento();
                obj.setCodMedicamento(rs.getInt("codMedicamento"));
                obj.setNome(rs.getString("nome"));
                obj.getFornecedor().setCodFornecedor(rs.getInt("codFornecedor"));
                obj.setDataValidade(converteDataView(rs.getDate("dataValidade").toString()));
                obj.setPreco(rs.getBigDecimal("preco"));
                obj.setQtdEstoque(rs.getInt("qtdEstoque"));
                obj.setLote(rs.getInt("lote"));
                obj.setDataEntrada(converteDataView(rs.getDate("dataEntrada").toString()));
                
                
                meds.add(obj);
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
            "select ifnull(max(codMedicamento), 0) + 1 as numero from medicamento");
                
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

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
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class MedicamentoDAO implements DAO<Medicamento>{
    
    //variaveis auxiliares
    Banco bd;
    PreparedStatement pst;
    ResultSet rs;
    
    public java.sql.Date getData(String dataS){
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
    
    @Override
    public boolean inserir(Medicamento obj) {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into medicamento values (?, ?, ?, ?, ?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setString(2, obj.getNome());
            pst.setInt(3, obj.getFornecedor().getCodFornecedor());
            pst.setDate(4, getData(obj.getDataValidade()));
            pst.setBigDecimal(5, obj.getPreco());
            pst.setInt(6, obj.getQtdEstoque());
            pst.setInt(7, obj.getLote());
            pst.setDate(8, getData(obj.getDataEntrada()));
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
            bd.conectar();
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "update medicamento set nome = ?, codFornecedor = ?,"
                    + "dataValidade = ?, preco = ?, qtdEstoque = ?, lote = ?"
                            + " WHERE codMedicamento = ?");

            pst.setString(1, obj.getNome());
            pst.setInt(2, obj.getFornecedor().getCodFornecedor());
            pst.setDate(3, getData(obj.getDataValidade()));
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
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM medicamento WHERE nome = ?");
            pst.setString(1, obj.getNome());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setCodMedicamento(rs.getInt("codMedicamento"));
                obj.setNome("nome");
                obj.getFornecedor().setCodFornecedor(rs.getInt("codFornecedor"));
                obj.setDataValidade(rs.getDate("dataValidade").toString());
                obj.setPreco(rs.getBigDecimal("preco"));
                obj.setQtdEstoque(rs.getInt("qtdEstoque"));
                obj.setLote(rs.getInt("lote"));
                obj.setDataEntrada(rs.getDate("dataEntrada").toString());
                
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
    public List<Medicamento> listar(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public int proxCodigo() {
        try{
            bd.conectar(); //abre o banco
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
            bd.fechaConexao();
        }
    }
    
}

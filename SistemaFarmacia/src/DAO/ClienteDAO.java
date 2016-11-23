/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class ClienteDAO implements DAO<Cliente>{

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
    public boolean inserir(Cliente obj) {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into cliente values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                
            pst.setInt(1, proxCodigo());
            pst.setString(2, obj.getNome());
            pst.setString(3, obj.getTelefone());
            pst.setString(4, obj.getCelular());
            pst.setString(5, obj.getRg());
            pst.setString(6, obj.getCpf());
            pst.setDate(7, getData(obj.getDataNasc()));
            pst.setBoolean(8, obj.isAposentado());
            pst.setInt(9, obj.getEndereco().getCodEndereco());
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
    public boolean alterar(Cliente obj) {
        try{
            bd.conectar();
            pst = bd.getConexao().prepareStatement(
            "update cliente set nome = ?, telefone = ?,"
                    + "celular = ?, rg = ?, cpf = ?, dataNascimento = ?,"
                    + "aposentado = ? WHERE codCliente = ?");
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getTelefone());
            pst.setString(3, obj.getCelular());
            pst.setString(4, obj.getRg());
            pst.setString(5, obj.getCpf());
            pst.setDate(6, getData(obj.getDataNasc()));
            pst.setBoolean(7, obj.isAposentado());
            pst.setInt(8, obj.getCodCliente());
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
    public boolean excluir(Cliente obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "DELETE FROM cliente WHERE codCliente = ?");
            pst.setInt(1, obj.getCodCliente());
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
    public Cliente pesquisar(Cliente obj) {
        try {
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM cliente WHERE cpf = ?");
            pst.setString(1, obj.getCpf());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setCodCliente(rs.getInt("codCliente"));
                obj.setNome("nome");
                obj.setTelefone("telefone");
                obj.setCelular("celular");
                obj.setRg("rg");
                obj.setDataNasc(rs.getDate("dataNascimento").toString());
                obj.setAposentado(rs.getBoolean("aposentado"));
                obj.getEndereco().setCodEndereco(rs.getInt("codEndereco"));
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
    public List<Cliente> listar(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public int proxCodigo() {
        try{
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(//comando SQL
            "select ifnull(max(codCliente), 0) + 1 as numero from cliente");
                
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

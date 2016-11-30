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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author caiol
 */
public class ClienteDAO implements DAO<Cliente>{

    //variaveis auxiliares
    Banco bd;
    
    ResultSet rs;

    public ClienteDAO(Banco bd) {
        this.bd = bd;
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
    //busca o codigo do endereco no BD
    private int getEnderecoBD(){
        try {
            PreparedStatement pst;
           // bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT max(codEndereco) as codigo from endereco");
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                return rs.getInt("codigo");
                
            } else{
               return -1;   
            }
                          
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro na Pesquisa\n"
                     + ex.getMessage());
             return -1;
        } finally {
           // bd.fechaConexao();
        }
    }
    
    @Override
    public boolean inserir(Cliente obj) {
        try{
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement( //comando SQL
                    "insert into cliente values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            obj.getEndereco().setCodEndereco(getEnderecoBD());
            
            pst.setInt(1,proxCodigo());
            pst.setString(2,obj.getNome());
            pst.setString(3, obj.getTelefone());
            pst.setString(4, obj.getCelular());
            pst.setString(5, obj.getRg());
            pst.setString(6, obj.getCpf());
            pst.setDate(7, converteDataSQL(obj.getDataNasc()));
            pst.setBoolean(8, obj.isAposentado());
            pst.setInt(9, obj.getEndereco().getCodEndereco());
            //verifica se o update foi efetuado e retorna true ou false
            return pst.executeUpdate() > 0;
            
        }catch(SQLException ex){
            ex.printStackTrace();
             return false;
        }finally{
            bd.fechaConexao();
        }
    }

    @Override
    public boolean alterar(Cliente obj) {
        try{
            PreparedStatement pst;
            bd.conectar();
            pst = bd.getConexao().prepareStatement(
            "update cliente set nome = ?, telefone = ?,"
                    + "celular = ?, rg = ?, dataNascimento = ?,"
                    + "aposentado = ? WHERE codCliente = ?");
            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getTelefone());
            pst.setString(3, obj.getCelular());
            pst.setString(4, obj.getRg());
            pst.setDate(5, converteDataSQL(obj.getDataNasc()));
            pst.setBoolean(6, obj.isAposentado());
            pst.setInt(7, obj.getCodCliente());
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
            PreparedStatement pst;
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
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM cliente WHERE cpf = ?");
            pst.setString(1, obj.getCpf());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setCodCliente(rs.getInt("codCliente"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setDataNasc(converteDataView(rs.getDate("dataNascimento").toString()));
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

     public Cliente pesquisarCod(Cliente obj) {
        try {
            PreparedStatement pst;
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM cliente WHERE codCliente = ?");
            pst.setInt(1, obj.getCodCliente());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            if(rs.next()) { //achou
                obj.setCodCliente(rs.getInt("codCliente"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setDataNasc(converteDataView(rs.getDate("dataNascimento").toString()));
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
    
    
    /*public List<Cliente> listar(Cliente obj) {
        try {
            PreparedStatement pst;
            ArrayList<Cliente> clientes = new ArrayList<>();
            bd.conectar(); //abre o banco
            pst = bd.getConexao().prepareStatement(
                      "SELECT * FROM cliente WHERE cpf = ?");
            pst.setString(1, obj.getCpf());
            //executa o select
            rs = pst.executeQuery();
            //verifica se achou alguem
            while(rs.next()) { //achou
                obj.setCodCliente(rs.getInt("codCliente"));
                obj.setNome(rs.getString("nome"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setDataNasc(converteDataView(rs.getDate("dataNascimento").toString()));
                obj.setAposentado(rs.getBoolean("aposentado"));
                obj.getEndereco().setCodEndereco(rs.getInt("codEndereco"));
                
                clientes.add(obj);
            } 
                return clientes;            
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
        try{
            PreparedStatement pst;
            pst = bd.getConexao().prepareStatement(//comando SQL
            "select ifnull(max(codCliente), 0) + 1 as numero from cliente");
                
            rs = pst.executeQuery();//Executa o comando
                    
            rs.next();//retorna o valor do BD
            
            int x = rs.getInt("numero");
            return x;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro na Pesquisa\n"
                     + ex.getMessage());
             return -1;
        }finally{
            
        }
    }
    
}

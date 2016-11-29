/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author mslda
 */
public class Banco {
    private String usuario, senha, bdDados, servidor;
    private int porta;
    private Connection conexao;

    public Banco() {
        this.usuario = "root";
        this.senha = "";
        this.bdDados = "farmacia";
        this.servidor = "localhost";
        this.porta = 3306; //fixa para mysql
    }

    public Connection getConexao() {
        return conexao;
    }
    
    public boolean conectar() {
        try {
            //carrega o driver
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(
                    "jdbc:mysql://"
                    + getServidor() + ":"
                    + getPorta() + "/"
                    + getBdDados(), getUsuario(), getSenha());
                    
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Abertura: " + 
                    ex.getMessage());
            return false;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver: " +
                    ex.getMessage());
            return false;
        }
    }

    public void fechaConexao() {
        try {
            conexao.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fechar: " +
                    ex.getMessage());
        }
    } 

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getBdDados() {
        return bdDados;
    }

    public void setBdDados(String bdDados) {
        this.bdDados = bdDados;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
}

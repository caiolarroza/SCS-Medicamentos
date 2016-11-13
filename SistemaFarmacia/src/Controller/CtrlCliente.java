/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mslda
 */
public class CtrlCliente {
    
    public void cadastrarCliente(Cliente cliente){
        
    }
    
    public boolean validarIdade(Cliente cliente){
        
        
            LocalDate hoje = LocalDate.now();
            LocalDate nascimento = LocalDate.parse(cliente.getDataNasc());
            Period p = Period.between(nascimento, hoje);
            if(p.getYears() >= 18){
                return true;
            }else{
                return false;
            }
            
        /*try {   
            /*Formatar a data vinda da View
            String dataV = "06/11/1997";
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date da = sdf.parse(dataV);
            sdf.applyPattern("yyyy-MM-dd");
            dataV = sdf.format(da);
        } catch (ParseException ex) {
            Logger.getLogger(CtrlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import java.util.Currency;

/**
 *
 * @author CltControl
 */
public class cuentaAdapter {
    protected Account cuenta;
    protected  Currency moneda;

    public cuentaAdapter(int id, double amount) {
        Account cuenta = new Account(id,amount);
        
    }
    
    
    
    
}

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
public class CuentaAdapter implements Cuenta{
    protected Account cuenta;
    protected  Currency moneda;

    public CuentaAdapter(int id, double amount) {
         cuenta = new Account(id,amount);
        
    }

    @Override
    public double Balance() {
        return cuenta.getAmount();
    }

    @Override
    public boolean Retirar(double monto) {
        if(cuenta.withdraw(monto).startsWith("Error:"))
                return true;
        return false;
    }

    @Override
    public boolean Depositar(int n, double denominacion) {
        double total = n*denominacion;
        cuenta.deposit(total);
        return true;
    }
    
    
    
    
}

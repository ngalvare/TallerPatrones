/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

/**
 *
 * @author Charles
 */
public interface Cuenta {
    public double Balance();
    public boolean Retirar(int monto);
    public boolean Depositar(int n,int denominacion);
    
}

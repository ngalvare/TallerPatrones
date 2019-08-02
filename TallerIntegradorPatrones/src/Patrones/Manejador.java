/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

/**
 *
 * @author CltControl
 */
public interface Manejador {
    public void setNext(Manejador manejador);
    public boolean retirar(double monto);
    public boolean depositar(int n ,double denominacion);
    public String balance();
    public Manejador getNext();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        //Manejadores Concretos
        Manejador m = new ManejadorDinero(100,20);
        Manejador m2 = m.crearManejador(100, 10);
        m.setNext(m2);
        
        
        Account cuenta = new Account(2017,3000);
        //m20.setManejador(m10);
        
        AtmUK cajero = AtmUK.getInstance();
        cajero.addManejador(m);
        cajero.transaction(cuenta);
        
        
        
        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10,  CREAR MANEJADORES DE ESTOS TIPOS
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05                                    EL PRIMERO DEBE SER DE DENOMINACION 20 LATAS

        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.         
        //Utilizar el adapter para convertir libras MONEDA UK a dolares MONEDA US
        
        // Menú principal para seleccionar una de las 10 cuentas solo con el id
        
        // Mostrar el menú para realizar transacciones en el cajero automático
    }

    
}

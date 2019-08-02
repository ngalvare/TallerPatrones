
package Patrones;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(System.in);
        //Cuentas
        ArrayList<Cuenta> cuentasLista = new ArrayList<>();
        for(int i = 1; i<11;i++){
            Cuenta cuenta = new CuentaAdapter(i, i*100);
            cuentasLista.add(cuenta);
        }
        
        //Manejadores Concretos
        Manejador m20 = new ManejadorDinero(100,20);
        Manejador m10 = new ManejadorDinero(100,10);
    
         
        Manejador p50 = new ManejadorDinero(10,0.5);
        Manejador p20 = new ManejadorDinero(10,0.25);
        Manejador p10 = new ManejadorDinero(1000,0.05);
        m20.setNext(m10);
        m10.setNext(p50);
        p50.setNext(p20);
        p20.setNext(p10);
      
        
        AtmUK cajero = AtmUK.getInstance();
        cajero.addManejador(m20);
        
        /* MENU IMAGINARIO DE LAS CUENTAS */
        System.out.println("****************************************\n"
                + "\t\tBIENVENIDO!\n"
                + "****************************************\n");
        int cuentaOpcion;
        System.out.print("Ingrese el ID de su cuenta (los ID disponibles son del 1 al 10): ");
        cuentaOpcion = in.nextInt();
        for(int i = 0; i<cuentasLista.size();i++){
            if(cuentaOpcion == cuentasLista.get(i).cuentaID()){
                cajero.transaction(cuentasLista.get(i));
            }
        }
        
        
        
        
        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10,  
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05                                    

        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.         
        //Utilizar el adapter para convertir libras MONEDA UK a dolares MONEDA US
        
        // Menú principal para seleccionar una de las 10 cuentas solo con el id
        
        // Mostrar el menú para realizar transacciones en el cajero automático
    }

    
}

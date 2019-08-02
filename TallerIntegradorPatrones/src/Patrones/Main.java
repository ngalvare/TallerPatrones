
package Patrones;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(System.in);
        //Cuentas US
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
        
        
        
    }

    
}

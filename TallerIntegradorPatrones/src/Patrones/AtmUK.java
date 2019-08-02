
package Patrones;

import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class AtmUK {

    private static AtmUK AtmUK;
    private final Currency moneda = Currency.getInstance(Locale.UK);
    private double dinero = 0;
    private Manejador manejador = null; // Cada manejador puede entregar dinero de una sola denominación

    private AtmUK() {

    }

    public static AtmUK getInstance() {
        if (AtmUK == null) {
            AtmUK = new AtmUK();
        } else {
            System.out.println("No se puede crear una lista de manejadores porque ya existe");
        }
        return AtmUK;
    }

    public double getTotal() {
        return this.dinero;
    }

    public boolean sacarDinero(double dinero) {
        if (manejador.retirar(dinero)){
            this.dinero -= dinero;
            return true;
        }
        return false;
        // Todo: realizar el proceso de sacar de cada manejador la cantidad requerida
        
    }

    public boolean ingresarDinero(int dinero, double denominacion) {
        if (manejador.depositar(dinero, denominacion)){
            this.dinero += dinero * denominacion;
            return true;
        }
        return false;
        
        // Todo: Sólo se puede depositar billetes de una sola denominación y agregarse al manejador correspondiente
        
    }

    public void addManejador(Manejador m) {
        if (this.manejador == null) {
            this.manejador = m;
        } 
    }

    public Manejador removeManejador(int i) throws Exception {
        Manejador m =null;
        int indice = 0;
        if(this.manejador==null)
            throw new Exception("No hay manejadores en el cajero");
        if (i==0){
             m = this.manejador ;
             if (this.manejador.getNext()!= null)
                 this.manejador = this.manejador.getNext();
             else   
                this.manejador = null ;
            return m ;
        }
        Manejador recorre = this.manejador  ;
        while(recorre.getNext()!= null){
            //Si el que deseo eliminar se encuentra en la posicion siguiente de mi actual Manejador
            if (indice+1 == i){
                //Pregunto si el siguiente del siguiente es null
                if (recorre.getNext().getNext() == null){
                    m = recorre.getNext();
                    recorre.setNext(null);
                    return m;
                }
                else {
                    m = recorre.getNext();
                    recorre.setNext(recorre.getNext().getNext());
                    return m;
                }
                
            }
            indice+= 1;
            recorre= recorre.getNext();
            
        }
        if (i > indice)
            throw new Exception ("Indice invalido para la cantidad de Manejadores existentes ");
            
            
            
        
        return m;
    }

    //Dentro de las transacciones se debe llamar al ATM para hacer el retiro o deposito de la cuenta correspondiente
    public void transaction(Cuenta cuenta) {
        // here is where most of the work is
        Scanner in = new Scanner(System.in);
        int choice;
        System.out.println("\nPorfavor eliga una opción: ");
        System.out.println("1. Retirar");
        System.out.println("2. Depositar");
        System.out.println("3. Balance");
        System.out.println("4. Balance ATM");
        choice = in.nextInt();
        switch (choice) {
            case 1:
                double amount;
                System.out.println("\nPorfavor ingresar monto a retirar: (ingrese números enteros)");
                amount = in.nextDouble();
                if ((cuenta.Balance() - amount >= 0) && (this.sacarDinero(amount))) {
                    cuenta.Retirar(amount);
                    System.out.println("Se han retirado "+ amount+ " y su balance actual es " + cuenta.Balance());
                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    //Verificar que se puede realizar el retiro del atm
                    if(!(cuenta.Balance() - amount>=0)){
                        System.out.println("Lo sentimos, Used no tiene suficientes fondos\n\n");
                        
                    }
                    else{
                        System.out.println("Lo sentimos, el cajero no tiene la cantidad solicitada \n\n");
                    }
                        anotherTransaction(cuenta);

                    // Todo: actualizar tanto la cuenta como el atm y de los manejadores
                    // cuenta.retirar(amount);
                    // AtmUK.sacarDinero(amount);
                    // Todo: Mostrar resumen de transacción o error
                    // "You have withdrawn "+amount+" and your new balance is "+balance;
                    
                }
                break;
            case 2:
                // option number 2 is depositing 
                float deposit;
                int n;
                System.out.println("\nPor favor ingrese la cantidad de billetes a depositar: ");
                n = in.nextInt();
                System.out.println("\nPor favor ingrese la denomincación a depositar: ");
                deposit = in.nextFloat();
                cuenta.Depositar(n, deposit);
                dinero = dinero -(n*deposit);
                
                // Todo: actualizar tanto la cuenta como el atm
                
                // Todo: Mostrar resumen de transacción o error
                // "You have withdrawn "+amount+" and your new balance is "+balance;
                System.out.println("Se han depositado "+ deposit*n+ " y su balance actual es " + cuenta.Balance());
                anotherTransaction(cuenta);
                break;
            case 3:
                System.out.println("Su Balance es:" + cuenta.Balance());
                // Todo: mostrar el balance de la cuenta
                // "Your balance is "+balance
                anotherTransaction(cuenta);
                break;
            case 4:
                Manejador m = this.manejador;
                if (this.manejador == null) {
                    System.out.println("El cajero no contiene billetes ");
                } else {
                    while (m != null) {
                        System.out.println("El ATM tiene: " + m.balance());
                        m = m.getNext();
                    }

                }
                    // Todo: mostrar el balance del ATM con los billetes en cada manejador*/
                anotherTransaction(cuenta);
                break;
            default:
                System.out.println("Opcion Invalida!!:\n\n");
                anotherTransaction(cuenta);
                break;
        }
    }

    public void anotherTransaction(Cuenta cuenta) {
        System.out.println("\nEliga una opción: \n1.Realizar otra transacción\n2.Salir");
        int transaccion;
        Scanner in = new Scanner(System.in);
        transaccion = in.nextInt();
        switch (transaccion) {
            case 1:
                transaction(cuenta); // call transaction method
                break;
            case 2:
                System.out.println("Gracias por elegirnos,adiós!!");
                break;
            default:
                System.out.println("Opción Invalida\n\n");
                anotherTransaction(cuenta);
                break;
        }
    }
}




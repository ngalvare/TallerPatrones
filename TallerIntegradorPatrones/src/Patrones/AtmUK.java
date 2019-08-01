
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
        System.out.println("Please select an option");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Balance");
        System.out.println("4. Balance ATM");
        choice = in.nextInt();
        switch (choice) {
            case 1:
                float amount;
                System.out.println("Please enter amount to withdraw: ");
                amount = in.nextFloat();
                if (cuenta.Retirar(amount)) {
                    System.out.println("You have insufficient funds\n\n");
                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    //Verificar que se puede realizar el retiro del atm
                    if(this.sacarDinero(amount)){
                        System.out.println("Se han retirado "+ amount+ " y su balance actual es " + cuenta.Balance());
                    }
                    else{
                        System.out.println(" Lo sentimos, el cajero no tiene la cantidad solicitada ");
                    }
                        

                    // Todo: actualizar tanto la cuenta como el atm y de los manejadores
                    // cuenta.retirar(amount);
                    // AtmUK.sacarDinero(amount);
                    // Todo: Mostrar resumen de transacción o error
                    // "You have withdrawn "+amount+" and your new balance is "+balance;
                    anotherTransaction(cuenta);
                }
                break;
            case 2:
                // option number 2 is depositing 
                float deposit;
                System.out.println("Please enter amount you would wish to deposit: ");
                deposit = in.nextFloat();
               
                
                // Todo: actualizar tanto la cuenta como el atm
                
                // Todo: Mostrar resumen de transacción o error
                // "You have withdrawn "+amount+" and your new balance is "+balance;
                System.out.println("Se han depositado "+ deposit+ " y su balance actual es " + cuenta.Balance());
                anotherTransaction(cuenta);
                break;
            case 3:
                System.out.println("Your balance is:" + cuenta.Balance());
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
                        System.out.println("The ATM have " + m.balance());
                        m = m.getNext();
                    }

                }
                    // Todo: mostrar el balance del ATM con los billetes en cada manejador*/
                anotherTransaction(cuenta);
                break;
            default:
                System.out.println("Invalid option:\n\n");
                anotherTransaction(cuenta);
                break;
        }
    }

    public void anotherTransaction(Cuenta cuenta) {
        System.out.println("Do you want another transaction?\n\nPress 1 for another transaction\n2 To exit");
        int transaccion;
        Scanner in = new Scanner(System.in);
        transaccion = in.nextInt();
        switch (transaccion) {
            case 1:
                transaction(cuenta); // call transaction method
                break;
            case 2:
                System.out.println("Thanks for choosing us. Good Bye!");
                break;
            default:
                System.out.println("Invalid choice\n\n");
                anotherTransaction(cuenta);
                break;
        }
    }
}




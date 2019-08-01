
package Patrones;

public class ManejadorDinero implements Manejador {

    protected int monto;
    protected double denominacion;
    private Manejador next;

    public ManejadorDinero(int monto, double denominacion) {
        this.monto = monto; // Total de billetes
        this.denominacion = denominacion; // Valor de cada billete
        this.next = null;
    }


    
    public int getMonto(){ return monto; }
  
    public double getDenominacion(){ return denominacion; }
    public void setMonto(int monto){ this.monto = monto; }
    
  

    @Override
    public boolean retirar(double monto) {
        if (monto == 0) {
            return true;
        }
        if (monto >= denominacion && this.monto > 0) {
            this.monto--;
            return retirar(monto-denominacion);
        } else {
            if (next != null) {
                return next.retirar(monto);
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean depositar(int cantidad, double denominacion) {
        if (denominacion == this.denominacion) {
            this.monto = (int) (this.monto + (cantidad / denominacion));
            return true;
        } else {
            if (next != null) {
                return next.depositar(cantidad, denominacion);
            } else {
                return false;
            }
        }
    }

    @Override
    public void setNext(Manejador manejador) {
        this.next = manejador; //Setea el el viejo manejador por uno nuevo
    }

    @Override
    public Manejador getNext() {
        return this.next ;
    }

    @Override
    public String balance() {
        return  this.monto + " de " + this.denominacion ;
    }

}

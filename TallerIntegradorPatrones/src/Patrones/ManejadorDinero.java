 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

public class ManejadorDinero implements Manejador
{
    protected int monto;
    protected double denominacion;
    private Manejador next;

    public ManejadorDinero(int monto, int denominacion){
        this.monto = monto; // Total de billetes
        this.denominacion = denominacion; // Valor de cada billete
    }

    public int getMonto(){ return monto; }
    public double getDenominacion(){ return denominacion; }
    public void setMonto(int monto){ this.monto = monto; }

    public boolean retirar(int monto){
        if(monto==0) return true;
        if(monto>=denominacion && this.monto>0 ){
           monto-= denominacion;
           this.monto--;
          return retirar(monto);
        }
        
        else{
            if(next!=null){
                return next.retirar(monto);
            }
            else{
                return false;
            }
        }
        }
    
    public boolean depositar(int cantidad, double denominacion){
        if(denominacion==this.denominacion){
            this.monto=(int) (this.monto + (cantidad/denominacion));
            return true;
        }
        else{ 
            if(next!=null){
                return next.depositar(cantidad, denominacion);}
            else {
                return false;
            }
        }
    }

    @Override
    public void setNext(Manejador manejador) {
        this.next=manejador; //Setea el el viejo manejador por uno nuevo
    }
}
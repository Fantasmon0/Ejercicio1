package org.example;

public class Auto extends Vehiculo{
    private int numeroPuertas;
    private boolean aireAcondicionado;

    public Auto(double tarifaBase, String modelo, String marca, String placa, int numeroPuertas, boolean aireAcondicionado) {

        super(tarifaBase, modelo, marca, placa);
        this.numeroPuertas = numeroPuertas;
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

    @Override
    public double calcularTarifaDiaria(){
        return getTarifaBase() + (aireAcondicionado ? 10: 0);
    }
    @Override
    public String toString(){
        return  "[Auto] "+getPlaca()+" "+getMarca()+" "+getModelo()+" ,"+numeroPuertas+" puertas, "+(aireAcondicionado ? "con A/C ,":"sin A/C ,")+" Tarifa diaria: $"+getTarifaBase();
    }
}

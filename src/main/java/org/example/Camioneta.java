package org.example;

public class Camioneta extends Vehiculo{
    private double capacidadCarga;

    public Camioneta(double tarifaBase, String modelo, String marca, String placa, double capacidadCarga) {
        super(tarifaBase, modelo, marca, placa);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }
    @Override
    public double calcularTarifaDiaria(){
        if (capacidadCarga > 1.5){
            return getTarifaBase() * 1.15;
        }
        return getTarifaBase();
    }
    @Override
    public  String toString(){
        return "[Camioneta] "+getPlaca()+" "+getMarca()+" "+getModelo()+" , carga "+capacidadCarga+" t, Tarifa diaria: $"+calcularTarifaDiaria();
    }
}

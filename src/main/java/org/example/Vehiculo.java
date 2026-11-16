package org.example;

public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private double tarifaBase;

    public Vehiculo(double tarifaBase, String modelo, String marca, String placa) {

        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.tarifaBase = tarifaBase;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        if (tarifaBase > 0){
            this.tarifaBase = tarifaBase;
        } else {
            System.out.println("No puede ser menor a 0 la tarifa base.");
        }

    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public double calcularTarifaDiaria(){
        return tarifaBase;
    }
    public double calcularAlquiler(int dias){
        return calcularTarifaDiaria() * dias;
    }
    public double calcularAlquiler(int dias, double porcetajeDescuento){
        double totalSinDescuento = calcularAlquiler(dias);
        return totalSinDescuento - (totalSinDescuento * (porcetajeDescuento)/100.0);
    }
    @Override
    public String toString(){
        return placa+" ,"+marca+" ,"+modelo+" , Tarifa diaria: $"+calcularTarifaDiaria();
    }
}

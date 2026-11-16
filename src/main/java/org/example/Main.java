package org.example;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private static final HashSet<String> placasRegistradas = new HashSet<>();
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ RENTAESFOT ---\n");
            System.out.println("1. Registrar auto");
            System.out.println("2. Registrar camioneta");
            System.out.println("3. Mostrar vehículos");
            System.out.println("4. Cotizar alquiler");
            System.out.println("5. Mostrar estadísticas");
            System.out.println("6. Salir");
            System.out.print("Ingrese opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            if (opcion == 1){
               regitrarAuto();
            } else if (opcion == 2) {
                registrarCamioneta();
            } else if (opcion == 3) {
                mostrarVehiculo();
            } else if (opcion == 4) {
                cotizarAlquiler();
            } else if (opcion == 5) {
                mostrarEstadisticas();
            } else if (opcion == 6) {
                System.out.println("Saliendo del programa....");
            } else {
                System.out.println("Error. Opcion invalida");
                continue;
            }
        } while (opcion != 6);

    }
    private static void regitrarAuto(){
        System.out.print("Placa: ");
        String placa = sc.nextLine().trim().toUpperCase();
        if (placa.isEmpty()){
            System.out.println("Error. la Placa no puede estar vacia.");
            return;
        }
        if (!placasRegistradas.add(placa)){
            System.out.println("La placa "+placa+" ya está registrad.");
            return;
        }
        try {
            System.out.print("Marca: ");
            String marca = sc.nextLine().trim();
            System.out.print("Modelo: ");
            String modelo = sc.nextLine().trim();
            System.out.print("Tarifa diaria base: ");
            double tarifaBase = Double.parseDouble(sc.nextLine().trim());
            if (tarifaBase < 0){
                placasRegistradas.remove(placa);
                System.out.println("Error. La tarifa diaria base no puede ser negativo.");
                return;
            }
            System.out.print("Numero de puertas: ");
            int puertas = Integer.parseInt(sc.nextLine().trim());
            if(puertas <= 0) {
                placasRegistradas.remove(placa);
                System.out.println("Error. El numero de puertas debe ser mayor a 0.");
                return;
            }
            System.out.print("Tiene aire acondicionado (s/n): ");
            String tieneAire = sc.nextLine().trim().toLowerCase();
            boolean tiene = tieneAire.equalsIgnoreCase("s");

            vehiculos.add(new Auto(tarifaBase, marca, modelo, placa, puertas, tiene));
            System.out.println("Auto registrado exitosamente.");
        } catch (NumberFormatException e) {
            placasRegistradas.remove(placa);
            System.out.println("Error. Debe ingresar un valor numerico valido.");
        }
    }
    private static void registrarCamioneta(){
        System.out.print("Placa: ");
        String placa = sc.nextLine().trim().toUpperCase();
        if (placa.isEmpty()){
            System.out.println("Error. la Placa no puede estar vacia.");
            return;
        }
        if (!placasRegistradas.add(placa)){
            System.out.println("La placa "+placa+" ya está registrad.");
            return;
        }
        try {
            System.out.print("Marca: ");
            String marca = sc.nextLine().trim();
            System.out.print("Modelo: ");
            String modelo = sc.nextLine().trim();

            System.out.print("Tarifa diaria base: ");
            double tarifaBase = Double.parseDouble(sc.nextLine().trim());
            if (tarifaBase < 0) {
                placasRegistradas.remove(placa);
                System.out.println("Error: La tarifa diaria base no puede ser negativa.");
                return;
            }

            System.out.print("Capacidad de carga (t): ");
            double carga = Double.parseDouble(sc.nextLine().trim());
            if (carga < 0) {
                placasRegistradas.remove(placa);
                System.out.println("Error: La capacidad de carga no puede ser negativa.");
                return;
            }

            vehiculos.add(new Camioneta(tarifaBase,modelo , marca, placa, carga));
            System.out.println("Camioneta registrada exitosamente.");
        } catch (NumberFormatException e) {
            placasRegistradas.remove(placa);
            System.out.println("Error: Debe ingresar un valor numérico válido.");
        }
    }
    private static void mostrarVehiculo(){
        if (vehiculos.isEmpty()){
            System.out.println("Error. No hay vehículos registrados en el sistema.");
            return;
        }
        for (Vehiculo v : vehiculos){
            System.out.println(v);
        }
    }
    private static void cotizarAlquiler(){
        if (vehiculos.isEmpty()){
            System.out.println("Error. No hay vehículos registrados para cotizar.");
            return;
        }
        System.out.print("Placa: ");
        String placa = sc.nextLine().trim().toUpperCase();

        Vehiculo encontrado = null;
        for (Vehiculo v : vehiculos){
            if (v.getPlaca().equalsIgnoreCase(placa)){
                encontrado = v;
                break;
            }
        }
        if (encontrado == null){
            System.out.println("Error. La placa "+placa+" no existe,");
            return;
        }
        try {
            System.out.print("Días: ");
            String diasSrt = sc.nextLine().trim();
            int dias = Integer.parseInt(diasSrt);
            if (dias <= 0){
                System.out.println("Error. Los días de alquiler deben ser mayores a 0");
                return;
            }
            System.out.print("Aplica descuento (s/n)");
            String aplica = sc.nextLine().trim().toLowerCase();
            if(!aplica.equalsIgnoreCase("s")){
                double total = encontrado.calcularAlquiler(dias);
                System.out.printf("Total a pagar: $%.2f%n", total);
            } else {
                System.out.print("Porcentaje de descuento: ");
                double descuento = Double.parseDouble(sc.nextLine().trim());
                if(descuento < 0 || descuento > 50){
                    System.out.println("Error. El descuento debe estar entre 0 y 50%");
                    return;
                }
                double total = encontrado.calcularAlquiler(dias, descuento);
                System.out.printf("Total a pagar: $%.2f%n", total);
            }
        } catch (NumberFormatException e){
            System.out.println("Error. Debe ingresar el valor númerico válido,");
        }
    }
    private static void mostrarEstadisticas() {
        if (vehiculos.isEmpty()) {
            System.out.println("Error: No hay vehículos registrados para calcular estadísticas.");
            return;
        }

        Vehiculo mayor = vehiculos.get(0);
        double sumaTarifas = 0.0;

        for (Vehiculo v : vehiculos) {
            double tarifaActual = v.calcularTarifaDiaria();
            sumaTarifas += tarifaActual;
            if (tarifaActual > mayor.calcularTarifaDiaria()) {
                mayor = v;
            }
        }

        double promedio = sumaTarifas / vehiculos.size();
        System.out.printf("Vehículo con mayor tarifa diaria: %s %s ($%.2f)%n",
                mayor.getMarca(), mayor.getModelo(), mayor.calcularTarifaDiaria());
        System.out.printf("Promedio de tarifas diarias: $%.2f%n", promedio);
    }
}
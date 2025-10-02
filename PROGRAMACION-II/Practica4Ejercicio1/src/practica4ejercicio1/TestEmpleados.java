package practica4ejercicio1;

import java.util.Scanner;

abstract class Empleado {
    protected String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    public abstract double CalcularSalarioMensual();

    @Override
    public String toString() {
        return "Empleado: " + nombre;
    }
}

class EmpleadoTiempoCompleto extends Empleado {
    private double salarioAnual;

    public EmpleadoTiempoCompleto(String nombre, double salarioAnual) {
        super(nombre);
        this.salarioAnual = salarioAnual;
    }

    @Override
    public double CalcularSalarioMensual() {
        return salarioAnual / 12;
    }

    @Override
    public String toString() {
        return super.toString() + ", Salario Anual: " + salarioAnual 
               + ", Salario Mensual: " + CalcularSalarioMensual();
    }
}

class EmpleadoTiempoHorario extends Empleado {
    private double horasTrabajadas;
    private double tarifaHora;

    public EmpleadoTiempoHorario(String nombre, double horasTrabajadas, double tarifaHora) {
        super(nombre);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaHora = tarifaHora;
    }

    @Override
    public double CalcularSalarioMensual() {
        return horasTrabajadas * tarifaHora;
    }

    @Override
    public String toString() {
        return super.toString() + ", Horas Trabajadas: " + horasTrabajadas 
               + ", Tarifa por Hora: " + tarifaHora 
               + ", Salario Mensual: " + CalcularSalarioMensual();
    }
}

public class TestEmpleados {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Empleado[] empleados = new Empleado[5];

        for (int i = 0; i < 3; i++) {
            System.out.println("Ingrese nombre del empleado tiempo completo #" + (i+1));
            String nombre = sc.nextLine();
            System.out.println("Ingrese salario anual: ");
            double salarioAnual = sc.nextDouble();
            sc.nextLine(); // limpiar buffer
            empleados[i] = new EmpleadoTiempoCompleto(nombre, salarioAnual);
        }

        for (int i = 3; i < 5; i++) {
            System.out.println("Ingrese nombre del empleado tiempo horario #" + (i-2));
            String nombre = sc.nextLine();
            System.out.println("Ingrese horas trabajadas: ");
            double horas = sc.nextDouble();
            System.out.println("Ingrese tarifa por hora: ");
            double tarifa = sc.nextDouble();
            sc.nextLine(); // limpiar buffer
            empleados[i] = new EmpleadoTiempoHorario(nombre, horas, tarifa);
        }

        System.out.println("\n--- Lista de Empleados ---");
        for (Empleado e : empleados) {
            System.out.println(e.nombre + " - Salario Mensual: " + e.CalcularSalarioMensual());
        }

        sc.close();
    }
}

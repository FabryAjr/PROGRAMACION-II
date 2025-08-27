import java.util.Scanner;

public class TestEcuacionLineal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicitar valores al usuario
        System.out.print("Ingrese a, b, c, d, e, f: ");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();
        double d = sc.nextDouble();
        double e = sc.nextDouble();
        double f = sc.nextDouble();

        // Crear objeto
        EcuacionLineal eq = new EcuacionLineal(a, b, c, d, e, f);

        // Mostrar resultados
        if (eq.tieneSolucion()) {
            System.out.println("x = " + eq.getX() + ", y = " + eq.getY());
        } else {
            System.out.println("La ecuación no tiene solución");
        }

        sc.close();
    }
}

package practica4ejercicio2;

import java.util.Random;

interface Coloreado {
    String comoColorear();
}

abstract class Figura {
    protected String color;

    public Figura(String color) {
        this.color = color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract double area();
    public abstract double perimetro();

    @Override
    public String toString() {
        return "Figura de color: " + color;
    }
}

class Cuadrado extends Figura implements Coloreado {
    private double lado;

    public Cuadrado(double lado, String color) {
        super(color);
        this.lado = lado;
    }

    @Override
    public double area() {
        return lado * lado;
    }

    @Override
    public double perimetro() {
        return 4 * lado;
    }

    @Override
    public String comoColorear() {
        return "Colorear los cuatro lados";
    }

    @Override
    public String toString() {
        return "Cuadrado [Lado=" + lado + ", Color=" + color + "]";
    }
}

class Circulo extends Figura {
    private double radio;

    public Circulo(double radio, String color) {
        super(color);
        this.radio = radio;
    }

    @Override
    public double area() {
        return Math.PI * radio * radio;
    }

    @Override
    public double perimetro() {
        return 2 * Math.PI * radio;
    }

    @Override
    public String toString() {
        return "Circulo [Radio=" + radio + ", Color=" + color + "]";
    }
}

public class TestFiguras {
    public static void main(String[] args) {
        Random rand = new Random();

        Figura[] figuras = new Figura[5];
        String[] colores = {"Rojo", "Azul", "Verde", "Amarillo", "Negro"};

        for (int i = 0; i < figuras.length; i++) {
            int tipo = rand.nextInt(2) + 1; 
            String color = colores[rand.nextInt(colores.length)];

            if (tipo == 1) { 
                double lado = rand.nextInt(10) + 1; 
                figuras[i] = new Cuadrado(lado, color);
            } else { 
                double radio = rand.nextInt(10) + 1; 
                figuras[i] = new Circulo(radio, color);
            }
        }

        for (Figura f : figuras) {
            System.out.println(f);
            System.out.println("Area: " + f.area());
            System.out.println("Perimetro: " + f.perimetro());

            if (f instanceof Coloreado) {
                Coloreado c = (Coloreado) f;
                System.out.println("Metodo como Colorear: " + c.comoColorear());
            }

            System.out.println("-------------------------");
        }
    }
}

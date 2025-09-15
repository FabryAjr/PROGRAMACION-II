public class Circulo2D {
    private double x;
    private double y;
    private double radio;

    public Circulo2D() {
        this.x = 0;
        this.y = 0;
        this.radio = 1;
    }

    public Circulo2D(double x, double y, double radio) {
        this.x = x;
        this.y = y;
        this.radio = radio;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadio() {
        return radio;
    }

    public double getArea() {
        return Math.PI * radio * radio;
    }

    public double getPerimetro() {
        return 2 * Math.PI * radio;
    }

    public boolean contiene(double x, double y) {
        double distancia = Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        return distancia <= radio;
    }

    public boolean contiene(Circulo2D c) {
        double distancia = Math.sqrt(Math.pow(c.getX() - this.x, 2) + Math.pow(c.getY() - this.y, 2));
        return distancia + c.getRadio() <= this.radio;
    }

    public boolean sobrepone(Circulo2D c) {
        double distancia = Math.sqrt(Math.pow(c.getX() - this.x, 2) + Math.pow(c.getY() - this.y, 2));
        return distancia < this.radio + c.getRadio() && distancia > Math.abs(this.radio - c.getRadio());
    }

    public static void main(String[] args) {
        Circulo2D c1 = new Circulo2D(2, 0, 1);

        System.out.println("Area de c1: " + c1.getArea());
        System.out.println("Perimetro de c1: " + c1.getPerimetro());

        System.out.println("c1 contiene el punto (2.5, 0)? " + c1.contiene(2.5, 0));
        System.out.println("c1 contiene al circulo (2, 0, 0.5)? " + c1.contiene(new Circulo2D(2, 0, 0.5)));
        System.out.println("c1 se sobrepone con el circulo (0, 0, 2)? " + c1.sobrepone(new Circulo2D(0, 0, 2)));
    }
}

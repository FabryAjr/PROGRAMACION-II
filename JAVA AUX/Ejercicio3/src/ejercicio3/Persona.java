package ejercicio3;

public class Persona {
    private String nombre;
    private String paterno;
    private String materno;
    private int edad;

    public Persona(String nombre, String paterno, String materno, int edad) {
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.edad = edad;
    }

    public Persona(String nombre) {
        this.nombre = nombre;
        this.paterno = "Altamirano";
        this.materno = "Perez";
        this.edad = 19;
    }

    public void mostrar() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido paterno: " + paterno);
        System.out.println("Apellido materno: " + materno);
        System.out.println("Edad: " + edad);
    }

    public void mostrar(boolean nombreCompleto) {
        if (nombreCompleto) {
            System.out.println("Nombre completo: " + nombre + " " + paterno + " " + materno);
        } else {
            mostrar();
        }
    }

    public static void main(String[] args) {
        Persona persona1 = new Persona("Kevin Fabrizio", "Arze", "Cachi", 21);
        Persona persona2 = new Persona("Lucia");

        persona1.mostrar();
        persona2.mostrar();

        persona1.mostrar(true);
        persona2.mostrar(true);
    }
}

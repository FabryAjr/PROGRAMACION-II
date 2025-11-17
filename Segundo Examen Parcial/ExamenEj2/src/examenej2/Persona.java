package examenej2;

public class Persona {

    private String nombre;
    private int edad;
    private float pesoPersona;

    public Persona(String nombre, int edad, float peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.pesoPersona = peso;
    }

    public float getTarifa() {
        if (edad <= 25 || edad >= 60) {
            return 1.5f; 
        }
        return 3f;
    }

    public float getPeso() {
        return pesoPersona;
    }
}

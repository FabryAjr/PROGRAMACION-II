package examenej2;

import java.util.ArrayList;

public class Linea {

    String color;
    ArrayList<Persona> filaPersonas = new ArrayList<>();
    ArrayList<Cabina> cabinas = new ArrayList<>();
    int cantidadCabinas = 0;

    public Linea(String color) {
        this.color = color;
    }

    public void agregarPersona(Persona p) {
        filaPersonas.add(p);
    }

    public void agregarCabina(int nroCab) {
        Cabina c = new Cabina(nroCab);
        cabinas.add(c);
        cantidadCabinas++;
    }

    public boolean subirPersonaACabina(Persona p, int nroCab) {
        for (Cabina c : cabinas) {
            if (c.getNroCabina() == nroCab) {
                return c.agregarPersona(p);
            }
        }
        return false;
    }

    public boolean verificarCabinas() {
        for (Cabina c : cabinas) {
            float peso = 0;
            for (Persona p : c.getPersonas()) {
                peso += p.getPeso();
            }
            if (c.getPersonas().size() > 10 || peso > 850)
                return false;
        }
        return true;
    }

    public float ingresosTotales() {
        float total = 0;
        for (Cabina c : cabinas) {
            total += c.totalIngresos();
        }
        return total;
    }

    public float ingresosRegulares() {
        float total = 0;
        for (Cabina c : cabinas) {
            total += c.ingresosRegulares();
        }
        return total;
    }

    public String getColor() {
        return color;
    }
}

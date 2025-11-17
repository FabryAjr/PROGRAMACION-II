package examenej2;

import java.util.ArrayList;

public class Cabina {

    private int nroCabina;
    ArrayList<Persona> personasAbordo = new ArrayList<>();

    public Cabina(int nroCabina) {
        this.nroCabina = nroCabina;
    }

    public boolean agregarPersona(Persona p) {
        if (personasAbordo.size() >= 10)
            return false;

        float pesoActual = 0;
        for (Persona x : personasAbordo) {
            pesoActual += x.getPeso();
        }

        if (pesoActual + p.getPeso() > 850)
            return false;

        personasAbordo.add(p);
        return true;
    }

    public float totalIngresos() {
        float total = 0;
        for (Persona p : personasAbordo) {
            total += p.getTarifa();
        }
        return total;
    }

    public float ingresosRegulares() {
        float total = 0;
        for (Persona p : personasAbordo) {
            if (p.getTarifa() == 3)
                total += 3;
        }
        return total;
    }

    public int getNroCabina() {
        return nroCabina;
    }

    public ArrayList<Persona> getPersonas() {
        return personasAbordo;
    }
}
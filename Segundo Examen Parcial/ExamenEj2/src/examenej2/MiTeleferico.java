package examenej2;

import java.util.ArrayList;

public class MiTeleferico {

    ArrayList<Linea> lineas = new ArrayList<>();
    float cantidadingresos = 0;

    public void agregarPersonaFila(Persona p, String linea) {
        for (Linea l : lineas) {
            if (l.getColor().equals(linea)) {
                l.agregarPersona(p);
            }
        }
    }

    public void agregarCabina(String linea) {
        for (Linea l : lineas) {
            if (l.getColor().equals(linea)) {
                int nro = l.cantidadCabinas + 1;
                l.agregarCabina(nro);
            }
        }
    }

    public boolean verificarTodo() {
        for (Linea l : lineas) {
            if (!l.verificarCabinas())
                return false;
        }
        return true;
    }

    public float calcularIngresos() {
        float total = 0;
        for (Linea l : lineas) {
            total += l.ingresosTotales();
        }
        this.cantidadingresos = total;
        return total;
    }

    public Linea lineaMayorIngresoRegular() {
        Linea mayor = null;
        float maxIngreso = -1;

        for (Linea l : lineas) {
            float ing = l.ingresosRegulares();
            if (ing > maxIngreso) {
                maxIngreso = ing;
                mayor = l;
            }
        }
        return mayor;
    }
}

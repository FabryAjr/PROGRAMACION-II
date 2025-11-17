package examenej2;

public class Main {

    public static void main(String[] args) {

        MiTeleferico sistema = new MiTeleferico();

        Linea lineaA = new Linea("Amarilla");
        Linea lineaR = new Linea("Roja");
        Linea lineaV = new Linea("Verde");

        sistema.lineas.add(lineaA);
        sistema.lineas.add(lineaR);
        sistema.lineas.add(lineaV);

        for (Linea l : sistema.lineas) {
            sistema.agregarCabina(l.getColor());
            sistema.agregarCabina(l.getColor());
            sistema.agregarCabina(l.getColor());
        }

        Persona p1 = new Persona("Angel", 20, 55);
        Persona p2 = new Persona("Jorge", 30, 80);
        Persona p3 = new Persona("Marco", 65, 70);
        Persona p4 = new Persona("Anabel", 50, 60);
        Persona p5 = new Persona("Ariel", 10, 40);
        Persona p6 = new Persona("Joaquin", 10, 40);

        lineaA.subirPersonaACabina(p1, 1);
        lineaA.subirPersonaACabina(p2, 1);
        lineaA.subirPersonaACabina(p3, 2);
        lineaA.subirPersonaACabina(p5, 2);        

        lineaR.subirPersonaACabina(p3, 1);
        lineaR.subirPersonaACabina(p4, 1);
        lineaR.subirPersonaACabina(p5, 2);
        
        lineaV.subirPersonaACabina(p1, 3);
        lineaV.subirPersonaACabina(p3, 3);
        lineaV.subirPersonaACabina(p6, 2);

        System.out.println("Todas las cabinas cumplen reglas?: " + sistema.verificarTodo());
        System.out.println("Ingreso total del Mi Teleferico: " + sistema.calcularIngresos() + " Bs");

        Linea mayor = sistema.lineaMayorIngresoRegular();
        System.out.println("La linea con mayor ingreso regular es la linea " + mayor.getColor());
    }
}

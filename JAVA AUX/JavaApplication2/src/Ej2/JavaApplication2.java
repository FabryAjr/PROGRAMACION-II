package JavaApplication2
        
public class Videojuego{
    private String nombre;
    private String plataforma;
    private int cantidadJugadores;
    
    public Videojuego(String nombre, String plataforma, int cantidadJugadores){
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.cantidadJugadores = cantidadJugadores;     
    }
    public Videojuego(){
        this.nombre = "Desconocido";
        this.plataforma = "Desconocido";
        this.cantidadJugadores = 0;
    }
    
    public void mostrarInf(){
        System.out.println("nombre: "+ nombre);
        System.out.println("plataforma: "+ plataforma);
        System.out.println("Cantidad de Jugadores: "+ cantidadJugadores);
    }
    
    public void agregarJugadores(){
        cantidadJugadores += 1;
        System.out.println("Jugadores "+ cantidad + "fueron agregados.Ahora tenemos" + cantidadJugadores);
    }
    public static void main(String[] args){
        Videojuego juego1 = new Videojuego("Fifa 26", "Xbox", 4);
        Videojuego juego2 = new Videojuego("Dota 2");
        Videojuego juego3 = new Videojuego( );        
    
        juego1.mostrarInf();
        juego2.mostrarInf();
        juego3.mostrarInf();
        
        juego1.agregarJugadores();
        juego2.agregarJugadores(2);
        }
    }


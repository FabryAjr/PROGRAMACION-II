package practica.n5;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Autor {
    private String nombre;
    private String nacionalidad;

    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public void mostrarInfo() {
        System.out.println("Autor: " + nombre + " (" + nacionalidad + ")");
    }
}

class Estudiante {
    private String codigo;
    private String nombre;

    public Estudiante(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public void mostrarInfo() {
        System.out.println("Estudiante: " + nombre + " [" + codigo + "]");
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
}

class Libro {
    private String titulo;
    private String isbn;
    private List<Pagina> paginas = new ArrayList<>();

    public class Pagina {
        private int numero;
        private String contenido;

        private Pagina(int numero, String contenido) {
            this.numero = numero;
            this.contenido = contenido;
        }

        public void mostrarPagina() {
            System.out.println("Página " + numero + ": " + contenido);
        }

        public int getNumero() { return numero; }
    }

    public Libro(String titulo, String isbn, List<String> paginasContenido) {
        this.titulo = titulo;
        this.isbn = isbn;
        int n = 1;
        for (String texto : paginasContenido) {
            this.paginas.add(this.new Pagina(n++, texto));
        }
    }

    public String getTitulo() { return titulo; }
    public String getIsbn() { return isbn; }

    public void leer() {
        System.out.println("Leyendo libro: " + titulo + " (ISBN: " + isbn + ")");
        for (Pagina p : paginas) {
            p.mostrarPagina();
        }
    }

    public int cantidadPaginas() { return paginas.size(); }
}

class Prestamo {
    private String fechaPrestamo;
    private String fechaDevolucion; 
    private Estudiante estudiante;
    private Libro libro;

    public Prestamo(Estudiante estudiante, Libro libro) {
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        this.fechaDevolucion = null; 
    }

    public void devolver() {
        this.fechaDevolucion = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }

    public void mostrarInfo() {
        System.out.println("Préstamo -> Libro: " + libro.getTitulo() +
            ", Estudiante: " + estudiante.getNombre() +
            ", Fecha prest.: " + fechaPrestamo +
            (fechaDevolucion == null ? " (no devuelto)" :
            ", Fecha dev.: " + fechaDevolucion));
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Libro getLibro() { return libro; }
}

class Biblioteca {
    private String nombre;
    private List<Libro> libros = new ArrayList<>(); 
    private List<Autor> autores = new ArrayList<>(); 
    private List<Prestamo> prestamos = new ArrayList<>();
    private Horario horario; 

    public class Horario {
        private String diasApertura;
        private String horaApertura;
        private String horaCierre;

        private Horario(String dias, String ha, String hc) {
            this.diasApertura = dias;
            this.horaApertura = ha;
            this.horaCierre = hc;
        }

        public void mostrarHorario() {
            System.out.println("Horario: " + diasApertura + " " + horaApertura + " - " + horaCierre);
        }
    }

    public Biblioteca(String nombre, String diasApertura, String horaApertura, String horaCierre) {
        this.nombre = nombre;
        this.horario = this.new Horario(diasApertura, horaApertura, horaCierre); 
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("[Biblioteca] Libro agregado: " + libro.getTitulo());
    }

    public void agregarAutor(Autor autor) {
        autores.add(autor);
        System.out.println("[Biblioteca] Autor registrado.");
    }

    public Prestamo prestarLibro(Estudiante estudiante, Libro libro) {
        Prestamo p = new Prestamo(estudiante, libro);
        prestamos.add(p);
        System.out.println("[Biblioteca] Préstamo creado para " + estudiante.getNombre() + " -> " + libro.getTitulo());
        return p;
    }

    public void mostrarEstado() {
        System.out.println("=== Estado de la Biblioteca: " + nombre + " ===");
        horario.mostrarHorario();
        System.out.println("Libros disponibles (" + libros.size() + "): ");
        for (Libro l : libros) {
            System.out.println(" - " + l.getTitulo() + " (ISBN: " + l.getIsbn() + ") - Páginas: " + l.cantidadPaginas());
        }
        System.out.println("Autores registrados (" + autores.size() + "):");
        for (Autor a : autores) a.mostrarInfo();
        System.out.println("Préstamos activos (" + prestamos.size() + "):");
        for (Prestamo pr : prestamos) pr.mostrarInfo();
        System.out.println("=========================================");
    }

    public void cerrarBiblioteca() {
        System.out.println("Cerrando biblioteca: " + nombre + ". Eliminando todos los préstamos...");
        prestamos.clear(); 
    }
}

public class Main {
    public static void main(String[] args) {
        Autor autor1 = new Autor("Juan Perez", "Bolivia");
        Autor autor2 = new Autor("Maria Gomez", "Perú");

        List<String> contenido1 = Arrays.asList("Introduccion", "Capitulo 1: Fundamentos", "Capitulo 2: Ejemplos");
        Libro libro1 = new Libro("Programación en Java", "ISBN-0001", contenido1);

        List<String> contenido2 = Arrays.asList("Prologo", "Capítulo A: Historia", "Capítulo B: Teoría");
        Libro libro2 = new Libro("Historia de la Ciencia", "ISBN-0002", contenido2);
        
        Biblioteca bib = new Biblioteca("Biblioteca UMSA", "Lun-Vie", "08:00", "18:00");

        bib.agregarAutor(autor1);
        bib.agregarAutor(autor2);
        bib.agregarLibro(libro1);
        bib.agregarLibro(libro2);

        bib.mostrarEstado();

        Estudiante est = new Estudiante("2025001", "Ana Quispe");
        est.mostrarInfo();

        Prestamo p1 = bib.prestarLibro(est, libro1);

        bib.mostrarEstado();

        System.out.println("\nLeyendo libro prestado (composición Libro->Paginas):");
        libro1.leer();

        System.out.println("\nDemostrando agregacion: cerramos la biblioteca pero el libro sigue existiendo");
        bib.cerrarBiblioteca();
        libro1.leer();
        
        System.out.println("Estado final de la biblioteca:");
        bib.mostrarEstado();

        System.out.println("\nPruebas completadas ✅");
    }
}

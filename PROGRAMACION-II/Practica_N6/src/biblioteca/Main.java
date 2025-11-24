package biblioteca;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Autor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String nacionalidad;

    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() { return nombre; }
    public String getNacionalidad() { return nacionalidad; }

    public void mostrarInfo() {
        System.out.println("Autor: " + nombre + " (" + nacionalidad + ")");
    }

    @Override
    public String toString() {
        return nombre + " (" + nacionalidad + ")";
    }
}

class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nombre;

    public Estudiante(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }

    public void mostrarInfo() {
        System.out.println("Estudiante: " + nombre + " [" + codigo + "]");
    }

    @Override
    public String toString() {
        return nombre + " [" + codigo + "]";
    }
}

class Libro implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String isbn;
    private List<Pagina> paginas = new ArrayList<>();

    public static class Pagina implements Serializable {
        private static final long serialVersionUID = 1L;
        private int numero;
        private String contenido;

        public Pagina(int numero, String contenido) {
            this.numero = numero;
            this.contenido = contenido;
        }

        public void mostrarPagina() {
            System.out.println("Página " + numero + ": " + contenido);
        }

        public int getNumero() { return numero; }
        public String getContenido() { return contenido; }

        @Override
        public String toString() {
            return "Página " + numero + ": " + (contenido.length() > 20 ? contenido.substring(0,20) + "..." : contenido);
        }
    }

    public Libro(String titulo, String isbn, List<String> paginasContenido) {
        this.titulo = titulo;
        this.isbn = isbn;
        int n = 1;
        for (String texto : paginasContenido) {
            paginas.add(new Pagina(n++, texto));
        }
    }

    public String getTitulo() { return titulo; }
    public String getIsbn() { return isbn; }
    public int cantidadPaginas() { return paginas.size(); }

    public void leer() {
        System.out.println("Leyendo libro: " + titulo + " (ISBN: " + isbn + ")");
        for (Pagina p : paginas) p.mostrarPagina();
    }

    @Override
    public String toString() {
        return titulo + " (ISBN:" + isbn + ", pgs:" + cantidadPaginas() + ")";
    }
}

class Prestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion; // null si no devuelto
    private Estudiante estudiante;
    private Libro libro;

    public Prestamo(Estudiante estudiante, Libro libro) {
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = null;
    }

    public void devolver() {
        this.fechaDevolucion = LocalDate.now();
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Libro getLibro() { return libro; }

    public String mostrarInfo() {
        String f1 = fechaPrestamo.format(DateTimeFormatter.ISO_DATE);
        String f2 = (fechaDevolucion == null) ? "(no devuelto)" : fechaDevolucion.format(DateTimeFormatter.ISO_DATE);
        return "Préstamo -> " + libro.getTitulo() + " | " + estudiante.getNombre() + " | " + f1 + " -> " + f2;
    }
}

class Biblioteca implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private List<Libro> libros = new ArrayList<>(); // agregación
    private List<Autor> autores = new ArrayList<>(); // agregación
    private List<Estudiante> estudiantes = new ArrayList<>(); // agregación
    private List<Prestamo> prestamos = new ArrayList<>();
    private Horario horario; // composición

    public static class Horario implements Serializable {
        private static final long serialVersionUID = 1L;
        private String diasApertura;
        private String horaApertura;
        private String horaCierre;

        public Horario(String dias, String ha, String hc) {
            this.diasApertura = dias;
            this.horaApertura = ha;
            this.horaCierre = hc;
        }

        public String mostrarHorario() {
            return diasApertura + " " + horaApertura + " - " + horaCierre;
        }
    }

    public Biblioteca(String nombre, String diasApertura, String horaApertura, String horaCierre) {
        this.nombre = nombre;
        this.horario = new Horario(diasApertura, horaApertura, horaCierre);
    }

    public void agregarAutor(Autor a) { autores.add(a); }
    public void agregarLibro(Libro l) { libros.add(l); }
    public void agregarEstudiante(Estudiante e) { estudiantes.add(e); }

    public List<Libro> getLibros() { return libros; }
    public List<Autor> getAutores() { return autores; }
    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public List<Prestamo> getPrestamos() { return prestamos; }

    // crear préstamo (asociación)
    public Prestamo prestarLibro(Estudiante e, Libro l) {
        Prestamo p = new Prestamo(e, l);
        prestamos.add(p);
        return p;
    }

    public String mostrarEstado() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Biblioteca: ").append(nombre).append(" ===\n");
        sb.append("Horario: ").append(horario.mostrarHorario()).append("\n\n");

        sb.append("Autores (").append(autores.size()).append("):\n");
        for (Autor a : autores) sb.append(" - ").append(a.toString()).append("\n");
        sb.append("\n");

        sb.append("Libros (").append(libros.size()).append("):\n");
        for (Libro l : libros) sb.append(" - ").append(l.toString()).append("\n");
        sb.append("\n");

        sb.append("Estudiantes (").append(estudiantes.size()).append("):\n");
        for (Estudiante e : estudiantes) sb.append(" - ").append(e.toString()).append("\n");
        sb.append("\n");

        sb.append("Préstamos activos (").append(prestamos.size()).append("):\n");
        for (Prestamo p : prestamos) sb.append(" - ").append(p.mostrarInfo()).append("\n");

        sb.append("\n=== FIN ===\n");
        return sb.toString();
    }

    public void cerrarBiblioteca() {
        prestamos.clear(); // los préstamos dejan de existir
    }
}


class Persistencia {
    public static void guardar(Biblioteca bib, String ruta) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(bib);
        }
    }

    public static Biblioteca cargar(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            Object obj = ois.readObject();
            if (obj instanceof Biblioteca) return (Biblioteca) obj;
            else throw new ClassNotFoundException("El objeto en el fichero no es una Biblioteca");
        }
    }
}


public class Main {
    private static final String RUTA_PERSIST = "biblioteca.dat";
    private Biblioteca biblioteca;
    private JFrame frame;
    private JTextArea areaEstado;

    public Main() {
        biblioteca = new Biblioteca("Biblioteca UMSA", "Lun-Vie", "08:00", "18:00");
        crearYMostrarGUI();
    }

    private void crearYMostrarGUI() {
        frame = new JFrame("Sistema Biblioteca - UMSA (Persistencia + GUI)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btnAddAutor = new JButton("Agregar Autor");
        JButton btnAddLibro = new JButton("Agregar Libro");
        JButton btnAddEst = new JButton("Agregar Estudiante");
        JButton btnPrestar = new JButton("Prestar Libro");
        JButton btnMostrar = new JButton("Mostrar Estado");
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCargar = new JButton("Cargar");
        JButton btnCerrar = new JButton("Cerrar Biblioteca");

        panelTop.add(btnAddAutor);
        panelTop.add(btnAddLibro);
        panelTop.add(btnAddEst);
        panelTop.add(btnPrestar);
        panelTop.add(btnMostrar);
        panelTop.add(btnGuardar);
        panelTop.add(btnCargar);
        panelTop.add(btnCerrar);

        areaEstado = new JTextArea();
        areaEstado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaEstado);

        btnAddAutor.addActionListener(e -> accionAgregarAutor());
        btnAddLibro.addActionListener(e -> accionAgregarLibro());
        btnAddEst.addActionListener(e -> accionAgregarEstudiante());
        btnPrestar.addActionListener(e -> accionPrestarLibro());
        btnMostrar.addActionListener(e -> mostrarEstadoEnArea());
        btnGuardar.addActionListener(e -> accionGuardar());
        btnCargar.addActionListener(e -> accionCargar());
        btnCerrar.addActionListener(e -> accionCerrarBiblioteca());

        // Layout
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panelTop, BorderLayout.NORTH);
        frame.getContentPane().add(scroll, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        mostrarEstadoEnArea();
    }


    private void accionAgregarAutor() {
        String nombre = JOptionPane.showInputDialog(frame, "Nombre del autor:");
        if (nombre == null || nombre.trim().isEmpty()) return;
        String nac = JOptionPane.showInputDialog(frame, "Nacionalidad:");
        if (nac == null) nac = "";
        Autor a = new Autor(nombre.trim(), nac.trim());
        biblioteca.agregarAutor(a);
        mostrarMensaje("Autor agregado: " + a.toString());
        mostrarEstadoEnArea();
    }

    private void accionAgregarLibro() {
        String titulo = JOptionPane.showInputDialog(frame, "Título del libro:");
        if (titulo == null || titulo.trim().isEmpty()) return;
        String isbn = JOptionPane.showInputDialog(frame, "ISBN:");
        if (isbn == null) isbn = "";
        JTextArea ta = new JTextArea(10, 30);
        ta.setText("Página 1 contenido\nPágina 2 contenido");
        int res = JOptionPane.showConfirmDialog(frame, new JScrollPane(ta), "Escriba cada página en una línea", JOptionPane.OK_CANCEL_OPTION);
        if (res != JOptionPane.OK_OPTION) return;
        String texto = ta.getText();
        String[] lineas = texto.split("\\r?\\n");
        List<String> paginas = new ArrayList<>();
        for (String s : lineas) if (!s.trim().isEmpty()) paginas.add(s.trim());
        if (paginas.isEmpty()) paginas.add("Contenido no especificado");
        Libro l = new Libro(titulo.trim(), isbn.trim(), paginas);
        biblioteca.agregarLibro(l);
        mostrarMensaje("Libro agregado: " + l.getTitulo());
        mostrarEstadoEnArea();
    }

    private void accionAgregarEstudiante() {
        String codigo = JOptionPane.showInputDialog(frame, "Código de estudiante:");
        if (codigo == null || codigo.trim().isEmpty()) return;
        String nombre = JOptionPane.showInputDialog(frame, "Nombre del estudiante:");
        if (nombre == null) nombre = "";
        Estudiante e = new Estudiante(codigo.trim(), nombre.trim());
        biblioteca.agregarEstudiante(e);
        mostrarMensaje("Estudiante agregado: " + e.toString());
        mostrarEstadoEnArea();
    }

    private void accionPrestarLibro() {
        List<Estudiante> estuds = biblioteca.getEstudiantes();
        if (estuds.isEmpty()) { mostrarMensaje("No hay estudiantes registrados."); return; }
        String[] arrEst = new String[estuds.size()];
        for (int i = 0; i < estuds.size(); i++) arrEst[i] = estuds.get(i).toString();
        int idxE = JOptionPane.showOptionDialog(frame, "Seleccione estudiante:", "Estudiantes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, arrEst, arrEst[0]);
        if (idxE < 0) return;
        Estudiante seleccionado = estuds.get(idxE);

        List<Libro> libros = biblioteca.getLibros();
        if (libros.isEmpty()) { mostrarMensaje("No hay libros disponibles."); return; }
        String[] arrLib = new String[libros.size()];
        for (int i = 0; i < libros.size(); i++) arrLib[i] = libros.get(i).toString();
        int idxL = JOptionPane.showOptionDialog(frame, "Seleccione libro:", "Libros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, arrLib, arrLib[0]);
        if (idxL < 0) return;
        Libro libroSeleccionado = libros.get(idxL);

        Prestamo p = biblioteca.prestarLibro(seleccionado, libroSeleccionado);
        mostrarMensaje("Préstamo creado:\n" + p.mostrarInfo());
        mostrarEstadoEnArea();
    }

    private void accionGuardar() {
        try {
            Persistencia.guardar(biblioteca, RUTA_PERSIST);
            mostrarMensaje("Biblioteca guardada en '" + RUTA_PERSIST + "'");
        } catch (IOException ex) {
            mostrarMensaje("Error guardando: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void accionCargar() {
        try {
            Biblioteca cargada = Persistencia.cargar(RUTA_PERSIST);
            if (cargada != null) {
                this.biblioteca = cargada;
                mostrarMensaje("Biblioteca cargada desde '" + RUTA_PERSIST + "'");
                mostrarEstadoEnArea();
            }
        } catch (FileNotFoundException fnf) {
            mostrarMensaje("Archivo de persistencia no encontrado: " + RUTA_PERSIST);
        } catch (IOException | ClassNotFoundException ex) {
            mostrarMensaje("Error cargando: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void accionCerrarBiblioteca() {
        int r = JOptionPane.showConfirmDialog(frame, "¿Cerrar biblioteca y eliminar préstamos actuales?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (r != JOptionPane.YES_OPTION) return;
        biblioteca.cerrarBiblioteca();
        mostrarMensaje("Biblioteca cerrada: préstamos eliminados.");
        mostrarEstadoEnArea();
    }

    private void mostrarEstadoEnArea() {
        areaEstado.setText(biblioteca.mostrarEstado());
    }

    private void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(frame, msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}

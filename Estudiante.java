// Estudiante.java
public class Estudiante {
    int id;
    String nombre;
    String telefono;
    String carrera;
    int semestre;
    boolean gratuidad;

    public Estudiante(int id, String nombre, String telefono, String carrera, int semestre, boolean gratuidad) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.carrera = carrera;
        this.semestre = semestre;
        this.gratuidad = gratuidad;
    }

    public String toString() {
        return "ID: " + id +
               "\nNombre: " + nombre +
               "\nTeléfono: " + telefono +
               "\nCarrera: " + carrera +
               "\nSemestre: " + semestre +
               "\nGratuidad: " + (gratuidad ? "SÍ" : "NO");
    }
}

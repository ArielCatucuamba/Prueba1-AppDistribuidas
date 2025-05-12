import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class server {
    private static final int PORT = 4321;
    private static ArrayList<Estudiante> estudiantes;

    public static void main(String[] args) {
        estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante(1, "Juan Pérez", "123456789", "Ingeniería", 1, true));
        estudiantes.add(new Estudiante(2, "Ana Gómez", "987654321", "Medicina", 2, false));
        estudiantes.add(new Estudiante(3, "Luis Martínez", "456123789", "Derecho", 3, true));
        estudiantes.add(new Estudiante(4, "María López", "789456123", "Arquitectura", 4, false));
        estudiantes.add(new Estudiante(5, "Carlos Sánchez", "321654987", "Psicología", 5, true));
        estudiantes.add(new Estudiante(6, "Laura Torres", "654987321", "Economía", 6, false));
        estudiantes.add(new Estudiante(7, "Pedro Ramírez", "159753486", "Ingeniería", 7, true));
        estudiantes.add(new Estudiante(8, "Sofía Morales", "357159486", "Medicina", 8, false));
        estudiantes.add(new Estudiante(9, "Diego Herrera", "951753486", "Derecho", 9, true));
        estudiantes.add(new Estudiante(10, "Camila Vega", "753951486", "Arquitectura", 10, false));

        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            System.out.println("Servidor UDP escuchando en el puerto " + PORT);

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String received = new String(request.getData(), 0, request.getLength(), StandardCharsets.UTF_8);
                System.out.println("Mensaje recibido: " + received);

                new HiloServer(socket, request).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Estudiante buscarEstudiantePorId(int id) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                return estudiante;
            }
        }
        return null;
    }
}

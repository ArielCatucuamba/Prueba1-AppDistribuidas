// ServidorUDP.java
import java.net.*;
import java.util.*;

public class ServidorUDP {
    static List<Estudiante> estudiantes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5000);
        cargarEstudiantes();

        System.out.println("Servidor UDP en espera...");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            new Thread(new ManejadorCliente(socket, paquete)).start();
        }
    }

    static void cargarEstudiantes() {
        estudiantes.add(new Estudiante(4545, "Juan Luis Perez", "222333", "Desarrollo de Software", 4, false));
        estudiantes.add(new Estudiante(2222, "Ariel David Diaz", "286873", "Desarrollo de Software", 2, true));
        estudiantes.add(new Estudiante(2221, "Isaac Matias Ushiña", "298065", "Fisica", 2, true));
        estudiantes.add(new Estudiante(2223, "Josep Matias Vartolome", "267439", "Desarrollo de Software", 2, true));
        estudiantes.add(new Estudiante(2224, "Paul Josep Turin", "278398", "Desarrollo de Software", 2, true));
        estudiantes.add(new Estudiante(2225, "Ariel Luis Diaz", "278903", "Desarrollo de Software", 2, true));
        estudiantes.add(new Estudiante(2226, "Ariel David Diaz", "289346", "Desarrollo de Software", 2, true));
        estudiantes.add(new Estudiante(2227, "Ariel David Diaz", "217390", "Desarrollo de Software", 2, true));
        estudiantes.add(new Estudiante(2228, "Ariel David Diaz", "210974", "Desarrollo de Software", 2, true));
        estudiantes.add(new Estudiante(2229, "Ariel David Diaz", "249012", "Desarrollo de Software", 2, true));

    }

    static class ManejadorCliente implements Runnable {
        private DatagramSocket socket;
        private DatagramPacket paquete;

        public ManejadorCliente(DatagramSocket socket, DatagramPacket paquete) {
            this.socket = socket;
            this.paquete = paquete;
        }

        public void run() {
            try {
                String mensaje = new String(paquete.getData(), 0, paquete.getLength()).trim();
                int id = Integer.parseInt(mensaje);

                Estudiante e = estudiantes.stream()
                    .filter(est -> est.id == id)
                    .findFirst()
                    .orElse(null);

                String respuesta = (e != null) ? e.toString() : "Estudiante no encontrado";

                byte[] datosRespuesta = respuesta.getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(
                    datosRespuesta,
                    datosRespuesta.length,
                    paquete.getAddress(),
                    paquete.getPort()
                );

                socket.send(paqueteRespuesta);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

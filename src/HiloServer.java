import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class HiloServer extends Thread {
    private DatagramSocket socket;
    private DatagramPacket request;

    public HiloServer(DatagramSocket socket, DatagramPacket request) {
        this.socket = socket;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            String received = new String(request.getData(), 0, request.getLength());
            int id = Integer.parseInt(received.trim());
            String stringGratuidad = "";

            Estudiante estudiante = server.buscarEstudiantePorId(id);
            String responseMessage;

            if (estudiante != null) {
                if (estudiante.isGratuidad()) {
                    stringGratuidad = "SI";
                } else {
                    stringGratuidad = "NO";
                }
                responseMessage = "ID: " + estudiante.getId() + "\n" +
                        "Nombre: " + estudiante.getNombre() + "\n" +
                        "Teléfono: " + estudiante.getTelefono() + "\n" +
                        "Carrera: " + estudiante.getCarrera() + "\n" +
                        "Semestre: " + estudiante.getSemestre() + "\n" +
                        "Gratuidad: " + stringGratuidad + "\n";
            } else {
                responseMessage = "Estudiante no encontrado";
            }

            byte[] responseBuffer = responseMessage.getBytes();
            DatagramPacket response = new DatagramPacket(responseBuffer, responseBuffer.length, request.getAddress(),
                    request.getPort());
            socket.send(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
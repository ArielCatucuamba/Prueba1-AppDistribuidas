// ClienteUDP.java
import javax.swing.*;
import java.awt.event.*;
import java.net.*;

public class ClienteUDP extends JFrame {
    private JTextField campoID;
    private JButton botonBuscar;
    private JTextArea areaResultado;

    public ClienteUDP() {
        setTitle("Aplicación Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel labelID = new JLabel("Ingresar ID del Estudiante:");
        labelID.setBounds(30, 30, 200, 25);
        add(labelID);

        campoID = new JTextField();
        campoID.setBounds(220, 30, 100, 25);
        add(campoID);

        botonBuscar = new JButton("Buscar");
        botonBuscar.setBounds(140, 70, 100, 30);
        add(botonBuscar);

        areaResultado = new JTextArea();
        areaResultado.setBounds(30, 120, 320, 120);
        areaResultado.setEditable(false);
        add(areaResultado);

        botonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarEstudiante();
            }
        });

        setVisible(true);
    }

    private void buscarEstudiante() {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("172.31.115.147");

            String mensaje = campoID.getText();
            byte[] bufferEnvio = mensaje.getBytes();
            DatagramPacket paquete = new DatagramPacket(bufferEnvio, bufferEnvio.length, ip, 4321);
            socket.send(paquete);

            byte[] bufferRecepcion = new byte[1024];
            DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
            socket.receive(paqueteRespuesta);

            String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
            areaResultado.setText(respuesta);

            socket.close();
        } catch (Exception ex) {
            areaResultado.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new ClienteUDP();
    }
}

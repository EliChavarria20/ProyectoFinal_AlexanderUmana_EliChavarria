import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActualizar extends JFrame {

    public MenuActualizar() {
        // Configuración de la ventana
        setTitle("MENÚ ACTUALIZAR");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(44, 62, 80)); // Fondo oscuro
        setLayout(null); // Desactivamos el Layout Manager para posicionar los componentes con coordenadas

        // Crear el título de bienvenida
        JLabel lblTitulo = new JLabel("¿QUÉ DESEA ACTUALIZAR?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE); // Color del texto
        lblTitulo.setBounds(50, 20, 500, 40); // Establecer las coordenadas y tamaño del título
        lblTitulo.setOpaque(false); // Asegúrate de que el JLabel es transparente
        add(lblTitulo);

        // Crear y añadir los botones con el método crearBoton
        JButton btnActualizarVehiculo = crearBoton("Actualizar Vehículo", 80);
        btnActualizarVehiculo.setToolTipText("Haz clic para actualizar los datos de un vehículo");
        btnActualizarVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaActualizarVehiculo();
            }
        });

        JButton btnActualizarPropietario = crearBoton("Actualizar Propietario", 130);
        btnActualizarPropietario.setToolTipText("Haz clic para actualizar los datos de un propietario");
        btnActualizarPropietario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaActualizarPropietario();
            }
        });

        JButton btnActualizarMarca = crearBoton("Actualizar Marca", 180);
        btnActualizarMarca.setToolTipText("Haz clic para actualizar los datos de una marca");
        btnActualizarMarca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaActualizarMarca();
            }
        });

        // Botón para regresar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegresar.setBounds(30, 250, 160, 40); // Ajusta el tamaño del botón
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.RED);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor cuando pasa por encima
        btnRegresar.setToolTipText("Haz clic para regresar al menú principal");
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuPrincipal(); // Regresar al menú inicial
            }
        });

        // Añadir los botones al JFrame
        add(btnActualizarVehiculo);
        add(btnActualizarPropietario);
        add(btnActualizarMarca);
        add(btnRegresar);
    }

    // Método para crear los botones con estilo atractivo
    private JButton crearBoton(String texto, int posicionY) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBounds(150, posicionY, 250, 40); // Tamaño y posición uniforme para los botones
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.BLACK); // Color de texto negro
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor cuando pasa por encima
        boton.setToolTipText("Haz clic para " + texto.toLowerCase());
        return boton;
    }

    // Métodos para abrir las ventanas de actualización
    private void abrirVentanaActualizarPropietario() {
        ActualizarPropietario ventanaPropietario = new ActualizarPropietario();
        ventanaPropietario.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    private void abrirVentanaActualizarVehiculo() {
        ActualizarVehiculo ventanaVehiculo = new ActualizarVehiculo();
        ventanaVehiculo.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    private void abrirVentanaActualizarMarca() {
        ActualizarMarca ventanaMarca = new ActualizarMarca();
        ventanaMarca.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    // Método para regresar al menú inicial
    private void regresarAlMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        this.dispose(); // Cerrar la ventana actual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuActualizar menuActualizar = new MenuActualizar();
            menuActualizar.setVisible(true);
        });
    }
}

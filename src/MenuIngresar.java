import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuIngresar extends JFrame {

    public MenuIngresar() {
        // Configuración de la ventana
        setTitle("MENU INGRESAR");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(44, 62, 80)); // Fondo oscuro
        setLayout(null); // Desactivamos el Layout Manager para posicionar los componentes con coordenadas

        // Crear el título de bienvenida
        JLabel lblBienvenida = new JLabel("BIENVENIDOS AL SISTEMA", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Georgia", Font.BOLD, 24));
        lblBienvenida.setForeground(Color.WHITE); // Color del texto
        lblBienvenida.setBounds(50, 20, 500, 40); // Establecer las coordenadas y tamaño del título
        lblBienvenida.setOpaque(false); // Asegúrate de que el JLabel es transparente
        add(lblBienvenida);

        // Crear y añadir los botones con el método crearBoton
        JButton btnInsertarPropietario = crearBoton("Agregar Propietario", 80);
        btnInsertarPropietario.setToolTipText("INSERTE UN PROPIETARIO"); // Tooltip
        btnInsertarPropietario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarPropietario();
            }
        });

        JButton btnAgregarVehiculo = crearBoton("Agregar Vehiculo", 130);
        btnAgregarVehiculo.setToolTipText("INSERTE UN VEHICULO"); // Tooltip
        btnAgregarVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarVehiculo();
            }
        });

        JButton btnAgregarMarca = crearBoton("Agregar Marca", 180);
        btnAgregarMarca.setToolTipText("INSERTE UNA MARCA"); // Tooltip
        btnAgregarMarca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInsertarMarca();
            }
        });

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegresar.setBounds(30, 250, 160, 40); // Ajusta el tamaño del botón
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.RED);
        btnRegresar.setBorderPainted(false);
        btnRegresar.setFocusPainted(false);
        btnRegresar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor cuando pasa por encima
        btnRegresar.setToolTipText("Haz clic para regresar al menú principal");
        btnRegresar.setToolTipText("REGRESAR"); // Tooltip
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuPrincipal(); // Regresar al menú inicial
            }
        });

        // Añadir los botones al JFrame
        add(btnInsertarPropietario);
        add(btnAgregarVehiculo);
        add(btnAgregarMarca);
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
        boton.setToolTipText("Haz clic para " + texto.toLowerCase() + " un elemento");
        return boton;
    }

    // Método para regresar al menú inicial
    private void regresarAlMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
        this.dispose(); // Cerrar la ventana actual
    }

    private void abrirVentanaInsertarPropietario() {
        InsertarPropietario ventanaPropietario= new InsertarPropietario();
        ventanaPropietario.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    // Método para abrir la ventana Insertar VEHICULO
    private void abrirVentanaInsertarVehiculo() {
        InsertarVehiculo ventanaVehiculo = new InsertarVehiculo();
        ventanaVehiculo.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    // Método para abrir la ventana Insertar Marca
    private void abrirVentanaInsertarMarca() {
        InsertarMarcas ventanaMarca = new InsertarMarcas();
        ventanaMarca.setVisible(true);
        this.setVisible(false); // Ocultar la ventana de menú
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuIngresar menuIngresar = new MenuIngresar();
            menuIngresar.setVisible(true);
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuConsultar extends JFrame {

    public MenuConsultar() {
        // Configuración de la ventana
        setTitle("MENÚ CONSULTAR");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(44, 62, 80)); // Fondo oscuro
        setLayout(null); // Desactivamos el Layout Manager para posicionar los componentes con coordenadas

        // Crear el título de bienvenida
        JLabel lblTitulo = new JLabel("MENÚ DE CONSULTAS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE); // Color del texto
        lblTitulo.setBounds(50, 20, 500, 40); // Establecer las coordenadas y tamaño del título
        lblTitulo.setOpaque(false); // Asegúrate de que el JLabel es transparente
        add(lblTitulo);

        // Crear y añadir los botones con el método crearBoton
        JButton btnVisualizarVehiculo = crearBoton("Vehículos", 80);
        btnVisualizarVehiculo.setToolTipText("Haz clic para visualizar la lista de vehículos");
        btnVisualizarVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarVehiculo().setVisible(true);  // Ventana para visualizar Vehículos
                dispose(); // Cierra el menú consultar
            }
        });

        JButton btnVisualizarPropietarios = crearBoton("Propietarios", 130);
        btnVisualizarPropietarios.setToolTipText("Haz clic para visualizar la lista de propietarios");
        btnVisualizarPropietarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarPropietarios().setVisible(true);  // Ventana para visualizar Propietarios
                dispose(); // Cierra el menú consultar
            }
        });

        JButton btnVisualizarMarcas = crearBoton("Marcas", 180);
        btnVisualizarMarcas.setToolTipText("Haz clic para visualizar la lista de marcas");
        btnVisualizarMarcas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarMarcas().setVisible(true);  // Ventana para visualizar Marcas
                dispose(); // Cierra el menú consultar
            }
        });

        JButton btnVisualizarUsuarios = crearBoton("Usuarios", 230);
        btnVisualizarUsuarios.setToolTipText("Haz clic para visualizar la lista de usuarios");
        btnVisualizarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsultarUsuarios().setVisible(true);  // Ventana para visualizar Usuarios
                dispose(); // Cierra el menú consultar
            }
        });

        // Botón para regresar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegresar.setBounds(30, 300, 160, 40); // Ajusta el tamaño del botón
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
        add(btnVisualizarVehiculo);
        add(btnVisualizarPropietarios);
        add(btnVisualizarMarcas);
        add(btnVisualizarUsuarios);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuConsultar menuConsultar = new MenuConsultar();
            menuConsultar.setVisible(true);
        });
    }
}

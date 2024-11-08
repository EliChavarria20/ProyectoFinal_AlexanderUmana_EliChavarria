import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        // Configuración de la ventana
        setTitle("Inicio");
        setSize(600, 500);
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

        // Crear el botón "Agregar"
        JButton btnIngresar = crearBoton("Agregar", 120);
        btnIngresar.setToolTipText("AGREGAR"); // Tooltip
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuPrincipal(); // Acción para abrir el menú de agregar
            }
        });

        // Crear el botón "Eliminar"
        JButton btnEliminar = crearBoton("Eliminar", 170);
        btnEliminar.setToolTipText("ELIMINAR"); // Tooltip
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuEliminar(); // Acción para abrir el menú de eliminar
            }
        });

        // Crear el botón "Actualizar"
        JButton btnActualizar = crearBoton("Actualizar", 220);
        btnActualizar.setToolTipText("ACTUALIZAR"); // Tooltip
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuActualizar(); // Acción para abrir el menú de actualizar
            }
        });

        // Crear el botón "Visualizar"
        JButton btnVisualizar = crearBoton("Visualizar", 270);
        btnVisualizar.setToolTipText("VISUALIZAR"); // Tooltip
        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMenuVisualizar(); // Acción para abrir el menú de visualizar
            }
        });

        // Crear el botón "Salir"
        JButton btnSalir = crearBoton("Salir", 320);
        btnSalir.setBackground(Color.WHITE); // Color rojo para el botón de salir
        btnSalir.setForeground(Color.RED);
        btnSalir.setToolTipText("SALIR"); // Tooltip
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI(); // Crea una nueva instancia de LoginGUI
                loginGUI.setVisible(true); // Muestra la ventana de inicio de sesión
                dispose(); // Cierra la ventana de MenuPrincipal
            }
        });

        // Añadir todos los botones al JFrame
        add(btnIngresar);
        add(btnEliminar);
        add(btnActualizar);
        add(btnVisualizar);
        add(btnSalir);
    }

    // Método para crear los botones con estilo atractivo
    private JButton crearBoton(String texto, int posicionY) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBounds(220, posicionY, 160, 40);
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.BLACK); // Color de texto blanco
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor cuando pasa por encima
        boton.setToolTipText("Haz clic para " + texto.toLowerCase() + " un elemento"); // Tooltip
        return boton;
    }
    private void abrirMenuPrincipal() {
        MenuIngresar menuPrincipal = new MenuIngresar();
        menuPrincipal.setVisible(true);
        this.dispose(); // Cierra la ventana de inicio
    }

    private void abrirMenuEliminar() {
        MenuEliminar menuEliminar = new MenuEliminar(); // Crear la ventana de eliminar
        menuEliminar.setVisible(true);
        this.dispose(); // Cierra la ventana de inicio
    }

    private void abrirMenuActualizar() {
        MenuActualizar menuActualizar = new MenuActualizar(); // Crear la ventana de actualizar
        menuActualizar.setVisible(true);
        this.dispose(); // Cierra la ventana de inicio
    }

    private void abrirMenuVisualizar() {
        MenuConsultar menuConsultar = new MenuConsultar(); // Crear la ventana de visualizar
        menuConsultar.setVisible(true);
        this.dispose(); // Cierra la ventana de inicio
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal inicio = new MenuPrincipal();
            inicio.setVisible(true);
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginGUI extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtClave;

    public LoginGUI() {
        super("Inicio de Sesión");
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450);
        setResizable(false);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Cargar imagen para el panel de login
        ImageIcon iconoFondo = new ImageIcon(LoginGUI.class.getResource("/Imagenes/sitio-web.png"));
        ImageIcon iconoRedimensionado = new ImageIcon(
                iconoFondo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
        JLabel lblImagen = new JLabel(iconoRedimensionado);

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(44, 62, 80));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblImagen, gbc);

        JLabel lblTitulo = new JLabel("Inicio de Sesión");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK); // Texto en color negro
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        panel.add(lblTitulo, gbc);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.BLACK);
        lblUsuario.setFont(lblUsuario.getFont().deriveFont(Font.BOLD, 16)); // Aumentar el tamaño y hacer negrita
        lblUsuario.setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
        panel.add(lblUsuario, gbc);

        txtUsuario = new JTextField(15);
        txtUsuario.setHorizontalAlignment(JTextField.CENTER); // Centrar el texto en el JTextField
        txtUsuario.setFont(txtUsuario.getFont().deriveFont(Font.PLAIN, 14)); // Reducir el tamaño de la fuente
        panel.add(txtUsuario, gbc);

        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setForeground(Color.BLACK);
        lblClave.setFont(lblClave.getFont().deriveFont(Font.BOLD, 16)); // Aumentar el tamaño y hacer negrita
        lblClave.setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
        panel.add(lblClave, gbc);

        txtClave = new JPasswordField(15);
        txtClave.setHorizontalAlignment(JPasswordField.CENTER); // Centrar el texto en el JPasswordField
        txtClave.setFont(txtClave.getFont().deriveFont(Font.PLAIN, 14)); // Reducir el tamaño de la fuente
        panel.add(txtClave, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setOpaque(false); // Hacer el panel transparente

        // BOTON INICIAR SESION
        ImageIcon iconoIniciarSesion = new ImageIcon(LoginGUI.class.getResource("/Imagenes/AgregarUsuario1.png"));
        ImageIcon iconoIniciarSesionRedimensionado = new ImageIcon(
                iconoIniciarSesion.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        JButton btnIniciarSesion = new JButton("Iniciar Sesión", iconoIniciarSesionRedimensionado);
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 14));
        btnIniciarSesion.setPreferredSize(new Dimension(160, 50));
        btnIniciarSesion.setBackground(Color.WHITE);
        btnIniciarSesion.setForeground(Color.BLACK); // Texto en color negro
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor cuando pasa por encima
        btnIniciarSesion.setHorizontalTextPosition(SwingConstants.CENTER); // Texto centrado horizontalmente
        btnIniciarSesion.setVerticalTextPosition(SwingConstants.BOTTOM); // Texto debajo del icono
        btnIniciarSesion.setToolTipText("Haz clic para iniciar sesión");

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validación del usuario y contraseña
                String usuario = txtUsuario.getText();
                String clave = new String(txtClave.getPassword());

                if (validarUsuario(usuario, clave)) {
                    MenuPrincipal();
                    dispose(); // Cierra la ventana de inicio de sesión
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Usuario o contraseña incorrectos", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelBotones.add(btnIniciarSesion);

        // BOTON SALIR
        ImageIcon iconoSalir = new ImageIcon(LoginGUI.class.getResource("/Imagenes/logout.png"));
        ImageIcon iconoSalirRedimensionado = new ImageIcon(
                iconoSalir.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        JButton btnSalir = new JButton("Salir", iconoSalirRedimensionado);
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setPreferredSize(new Dimension(160, 50));
        btnSalir.setBackground(Color.WHITE);
        btnSalir.setForeground(Color.BLACK); // Texto en color negro
        btnSalir.setBorderPainted(false);
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor cuando pasa por encima
        btnSalir.setHorizontalTextPosition(SwingConstants.CENTER); // Texto centrado horizontalmente
        btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM); // Texto debajo del icono
        btnSalir.setToolTipText("Haz clic para salir de la aplicación");

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Salir de la aplicación
            }
        });
        panelBotones.add(btnSalir);

        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(panelBotones, gbc);

        getContentPane().add(panel);
    }

    private boolean validarUsuario(String usuario, String contrasena) {
        boolean autenticado = false;
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            // Conectar a la base de datos MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root",
                    "Francisco20.");

            // Llamar al procedimiento almacenado para validar el usuario
            String query = "{CALL ValidarUsuario(?, ?)}"; // Llamada al procedimiento almacenado
            callableStatement = connection.prepareCall(query);
            callableStatement.setString(1, usuario);
            callableStatement.setString(2, contrasena);

            // Ejecutar el procedimiento
            resultSet = callableStatement.executeQuery();

            // Si hay un resultado, el usuario es válido
            if (resultSet.next()) {
                autenticado = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en la base de datos: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null)
                    resultSet.close();
                if (callableStatement != null)
                    callableStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return autenticado;
    }

    private void MenuPrincipal() {
        MenuPrincipal MenuPrincipal = new MenuPrincipal();
        MenuPrincipal.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginGUI app = new LoginGUI();
            app.setVisible(true);
        });
    }
}
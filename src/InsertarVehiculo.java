import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;


public class InsertarVehiculo extends JFrame {
    private JTextField txtPlaca, txtCedula, txtID_marca, txtaño;
    private JButton btnInsertar, btnRegresar;

    public InsertarVehiculo() {
        // Configuración de la ventana
        setTitle("Insertar Vehiculo");
        setSize(420, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80)); // Color de fondo
        setLocationRelativeTo(null);
        setLayout(null); // Usar layout nulo para posiciones absolutas

        // Crear el título
        JLabel lblTitulo = new JLabel("Ingrese los Datos del vehiculo:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE); // Color del texto
        lblTitulo.setBounds(50, 5, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame
        
        // Crear y añadir etiquetas y campos de texto con coordenadas personalizadas

        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setBounds(50, 90, 150, 25);
        lblPlaca.setForeground(Color.WHITE);
        add(lblPlaca);
        txtPlaca = new JTextField();
        txtPlaca.setBounds(200, 90, 170, 25);
        txtPlaca.setBackground(Color.WHITE);
        txtPlaca.setForeground(Color.BLACK);
        txtPlaca.setToolTipText("Ingrese la placa del vehiculo.");
        add(txtPlaca);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(50, 50, 150, 25);
        lblCedula.setForeground(Color.WHITE);
        add(lblCedula);
        txtCedula = new JTextField();
        txtCedula.setBounds(200, 50, 170, 25);
        txtCedula.setBackground(Color.WHITE);
        txtCedula.setForeground(Color.BLACK);
        txtCedula.setToolTipText("Ingrese la cédula de la persona.");
        add(txtCedula);

        JLabel lblID_marca = new JLabel("ID_Marca:");
        lblID_marca.setBounds(50, 130, 150, 25);
        lblID_marca.setForeground(Color.WHITE);
        add(lblID_marca);
        txtID_marca = new JTextField();
        txtID_marca.setBounds(200, 130, 170, 25);
        txtID_marca.setBackground(Color.WHITE);
        txtID_marca.setForeground(Color.BLACK);
        txtID_marca.setToolTipText("Ingrese el ID_Marca del vehiculo.");
        add(txtID_marca);

        JLabel lblAño = new JLabel("Año:");
        lblAño.setBounds(50, 170, 150, 25);
        lblAño.setForeground(Color.WHITE);
        add(lblAño);
        txtaño = new JTextField();
        txtaño.setBounds(200, 170, 170, 25);
        txtaño.setBackground(Color.WHITE);
        txtaño.setForeground(Color.BLACK);
        txtaño.setToolTipText("Ingrese el año del vehiculo.");
        add(txtaño);

        // Botón Insertar Vehiculo
        btnInsertar = new JButton("Insertar Vehiculo");
        btnInsertar.setBounds(50, 230, 150, 40); // Ajustar tamaño
        btnInsertar.setBackground(Color.WHITE);
        btnInsertar.setForeground(Color.BLACK);
        btnInsertar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnInsertar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnInsertar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnInsertar.setToolTipText("Haz clic para insertar el vehiculo.");
        btnInsertar.addActionListener(e -> insertarVehiculo());
        add(btnInsertar);

        // Botón Regresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(220, 230, 150, 40); // Ajustar tamaño y ubicación
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.RED);
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnRegresar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnRegresar.setToolTipText("Haz clic para regresar al menú.");
        btnRegresar.addActionListener(e -> regresarAlMenu());
        add(btnRegresar);
    }
    // Método para insertar el cliente llamando al procedimiento almacenado
    private void insertarVehiculo() {
        String placa = txtPlaca.getText(); 
        String cedula = txtCedula.getText();
        String año = txtaño.getText();
        
        int id_marca;
        try {
            // Intenta convertir el ID_Marca a un valor entero
            id_marca = Integer.parseInt(txtID_marca.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID_Marca debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Salir del método si la conversión falla
        }

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal?verifyServerCertificate=false&useSSL=true", "root",
             "Francisco20.");

            // Llamar al procedimiento almacenado
            String query = "{CALL InsertarVehiculo(?, ?, ?, ?)}";
            statement = connection.prepareCall(query);

            // Establecer los parámetros
            statement.setString(1, placa);
            statement.setString(2, cedula);
            statement.setInt(3, id_marca); // Usar id_marca como entero
            statement.setString(4, año);

            // Ejecutar el procedimiento almacenado
            statement.execute();
            JOptionPane.showMessageDialog(this, "Vehiculo insertado exitosamente.");

            // Limpiar los campos después de la inserción
            limpiarCampos();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al insertar el vehiculo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar los recursos
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        txtPlaca.setText("");
        txtCedula.setText("");
        txtID_marca.setText("");
        txtaño.setText("");
    }

    // Método para regresar al menú principal
    private void regresarAlMenu() {
        MenuIngresar menuIngresar = new MenuIngresar();
        menuIngresar.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InsertarVehiculo app = new InsertarVehiculo();
            app.setVisible(true);
        });
    }
}

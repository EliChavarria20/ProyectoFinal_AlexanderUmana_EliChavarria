import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EliminarVehiculo extends JFrame {
    private JTextField txtPlaca;
    private JButton btnEliminar, btnRegresar;

    public EliminarVehiculo() {
        // Configuración de la ventana
        setTitle("Eliminar Vehiculo");
        setSize(410, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80)); // Color de fondo igual que en InsertarVehiculo
        setLocationRelativeTo(null);
        setLayout(null); // Usar layout nulo para posiciones absolutas

        // Crear el título
        JLabel lblTitulo = new JLabel("Eliminar Vehículo", SwingConstants.CENTER);
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
        txtPlaca.setToolTipText("Ingrese la placa del vehículo a eliminar.");
        add(txtPlaca);

        // Botón Eliminar Vehiculo
        btnEliminar = new JButton("Eliminar Vehículo");
        btnEliminar.setBounds(50, 230, 150, 40); // Ajustar tamaño
        btnEliminar.setBackground(Color.WHITE);
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnEliminar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnEliminar.setToolTipText("Haz clic para eliminar el vehículo.");
        btnEliminar.addActionListener(e -> eliminarVehiculo());
        add(btnEliminar);

        // Botón Regresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(220, 230, 150, 40); // Ajustar tamaño y ubicación
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.RED);
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnRegresar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnRegresar.setToolTipText("Haz clic para regresar al menú.");
        btnRegresar.addActionListener(e -> regresarAlMenuEliminar());
        add(btnRegresar);
    }


    // Método para eliminar el cliente llamando al procedimiento almacenado
    private void eliminarVehiculo() {
        String Placa = txtPlaca.getText();

        if (Placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese la placa del vehiculo.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal?verifyServerCertificate=false&useSSL=true", "root", "Aiug01042004*");

            // Llamar al procedimiento almacenado
            String query = "{CALL EliminarVehiculo(?)}";
            statement = connection.prepareCall(query);

            // Establecer el parámetro
            statement.setString(1, Placa);

            // Ejecutar el procedimiento almacenado
            statement.execute();
            JOptionPane.showMessageDialog(this, "Vehiculo eliminado exitosamente.");

            // Limpiar el campo después de la eliminación
            txtPlaca.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el Vehiculo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void regresarAlMenuEliminar() {
        MenuEliminar menuEliminar = new MenuEliminar(); // Crear la instancia del menú Eliminar
        menuEliminar.setVisible(true); // Hacer visible el menú Eliminar
        this.dispose(); // Cerrar la ventana de eliminación
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EliminarVehiculo eliminarVehiculo = new EliminarVehiculo();
            eliminarVehiculo.setVisible(true);
        });
    }
}

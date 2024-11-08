import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EliminarMarca extends JFrame {
    private JTextField txtIdMarca;

    public EliminarMarca() {
        // Configuración de la ventana
        setTitle("Eliminar Marca");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80)); // Color de fondo igual al de InsertarVehiculo
        setLocationRelativeTo(null);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Ingrese el ID de la Marca a eliminar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE); // Color del texto
        lblTitulo.setBounds(20, 5, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Etiqueta y campo de texto para ingresar el ID del cargo
        JLabel lblIdMarca = new JLabel("ID de la Marca:");
        lblIdMarca.setBounds(50, 50, 100, 30);
        lblIdMarca.setForeground(Color.WHITE); // Color del texto
        add(lblIdMarca);

        txtIdMarca= new JTextField();
        txtIdMarca.setBounds(160, 50, 150, 30);
        txtIdMarca.setBackground(new Color(255, 255, 255)); // Fondo blanco como en InsertarVehiculo
        txtIdMarca.setForeground(Color.BLACK); // Color del texto
        txtIdMarca.setToolTipText("Ingrese el ID de la Marca que desea eliminar."); // Tooltip
        add(txtIdMarca);

        // Botón para eliminar la Marca
        JButton btnEliminar = new JButton("Eliminar Marca");
        btnEliminar.setBounds(160, 100, 150, 30);
        btnEliminar.setBackground(Color.WHITE); // Fondo blanco como en InsertarVehiculo
        btnEliminar.setForeground(Color.BLACK); // Color del texto
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnEliminar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnEliminar.setToolTipText("Haz clic para eliminar la Marca con el ID ingresado."); // Tooltip
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarMarca();
            }
        });
        add(btnEliminar);

        // Botón para regresar al menú eliminar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(30, 100, 100, 30);
        btnRegresar.setBackground(Color.WHITE); // Fondo blanco como en InsertarVehiculo
        btnRegresar.setForeground(Color.RED); // Color del texto en rojo
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnRegresar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnRegresar.setToolTipText("Haz clic para regresar al menú de eliminación."); // Tooltip
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuEliminar(); // Regresar al menú eliminar
            }
        });
        add(btnRegresar);
    }


    // Método para eliminar el cargo llamando al procedimiento almacenado
    private void eliminarMarca() {
        String IdMarca = txtIdMarca.getText();

        if (IdMarca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID de la Marca", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal?verifyServerCertificate=false&useSSL=true", "root", "Aiug01042004*");

            // Llamar al procedimiento almacenado
            String query = "{CALL EliminarMarca(?)}";
            statement = connection.prepareCall(query);

            // Establecer el parámetro
            statement.setString(1, IdMarca);

            // Ejecutar el procedimiento almacenado
            statement.execute();
            JOptionPane.showMessageDialog(this, "Marca eliminado exitosamente.");

            // Limpiar el campo después de la eliminación
            txtIdMarca.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la Marca: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    // Método para regresar al menú eliminar
    private void regresarAlMenuEliminar() {
        MenuEliminar menuEliminar = new MenuEliminar(); // Crear la instancia del menú Eliminar
        menuEliminar.setVisible(true); // Hacer visible el menú Eliminar
        this.dispose(); // Cerrar la ventana de eliminación
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EliminarMarca eliminarMarca = new EliminarMarca();
            eliminarMarca.setVisible(true);
        });
    }
}

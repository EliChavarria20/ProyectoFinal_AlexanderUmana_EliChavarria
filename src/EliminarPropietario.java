import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EliminarPropietario extends JFrame {

    // Campo de texto para ingresar la Cedula Propietario
    public JTextField txtCedulaPropietario;

    public EliminarPropietario () {
        // Configuración de la ventana
        setTitle("Eliminar Propietario");
        setSize(370, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80)); 
        setLocationRelativeTo(null);
        setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Cedula del Propietario a eliminar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE); // Color del texto
        lblTitulo.setBounds(30, 5, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Etiqueta y campo de texto para el ID del Propietario
        JLabel lblCedulaPropietario = new JLabel("ID Propietario:");
        lblCedulaPropietario.setBounds(40, 50, 100, 30);
        lblCedulaPropietario.setForeground(Color.WHITE); // Color del texto
        add(lblCedulaPropietario);

        txtCedulaPropietario = new JTextField();
        txtCedulaPropietario.setBounds(160, 50, 160, 30);
        txtCedulaPropietario.setBackground(new Color(255, 255, 255));  
        txtCedulaPropietario.setForeground(Color.BLACK); // Color del texto
        txtCedulaPropietario.setToolTipText("Cedula del Propietario que desea eliminar."); // Tooltip
        add(txtCedulaPropietario);

        // Botón para eliminar el propietario
        JButton btnEliminar = new JButton("Eliminar Propietario");
        btnEliminar.setBounds(160, 100, 160, 30);
        btnEliminar.setBackground(Color.WHITE); // 
        btnEliminar.setForeground(Color.BLACK); // Color del texto
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnEliminar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnEliminar.setToolTipText("Haz clic para eliminar el propietario con la cédula ingresada."); // Tooltip
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPropietario();
            }
        });
        add(btnEliminar);

        // Botón para regresar al menú eliminar
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(30, 100, 100, 30);
        btnRegresar.setBackground(Color.WHITE); // Fondo blanco como en InsertarVehiculo y EliminarMarca
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


    // Método para eliminar el colaborador llamando al procedimiento almacenado
    private void eliminarPropietario() {
        String CedulaPropietario = txtCedulaPropietario.getText();

        if (CedulaPropietario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el ID del colaborador.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection connection = null;
        CallableStatement statement = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basetienda?verifyServerCertificate=false&useSSL=true", "root", "Francisco20.");

            // Llamar al procedimiento almacenado
            String query = "{CALL EliminarPersona(?)}";
            statement = connection.prepareCall(query);

            // Establecer el parámetro del ID del Propietario
            statement.setString(1, CedulaPropietario);

            // Ejecutar el procedimiento almacenado
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Propietario eliminado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un Propietario con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Limpiar el campo de texto
            txtCedulaPropietario.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el Propietario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        MenuEliminar menuEliminar = new MenuEliminar();
        menuEliminar.setVisible(true);
        this.setVisible(false); // Cerrar la ventana actual
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EliminarPropietario  app = new EliminarPropietario ();
            app.setVisible(true);
        });
    }
}

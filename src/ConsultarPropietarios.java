import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarPropietarios extends JFrame {

    public ConsultarPropietarios() {
        // Configuración de la ventana
        setTitle("Lista de Propietarios");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(151, 204, 233));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar los componentes

        // Configurar el modelo de la tabla
        String[] columnNames = {"Cedula", "Nombre 1", "Nombre 2", "Apellido 1", "Apellido 2"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);  // Añadir la tabla a la ventana

        // Crear el botón "Regresar"
        JPanel panelBoton = new JPanel();
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setToolTipText("Haz clic para regresar al menú de consultas.");
        panelBoton.add(btnRegresar);
        add(panelBoton, BorderLayout.SOUTH);

        // Acción del botón "Regresar" para regresar al MenuVisualizar
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuVisualizar(); // Llamar al método que abrirá el MenuVisualizar
            }
        });

        // Cargar los propietarios en la tabla
        cargarPropietarios(tableModel);
    }

    // Método para cargar los colaboradores en la tabla
    private void cargarPropietarios(DefaultTableModel tableModel) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "Aiug01042004*");

            // Llamar al procedimiento almacenado
            String query = "CALL ConsultarPersona();";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            // Añadir los resultados a la tabla
            while (resultSet.next()) {
                Object[] row = new Object[7];
                row[0] = resultSet.getInt("Cedula");
                row[1] = resultSet.getString("Nombre1");
                row[2] = resultSet.getString("Nombre2");
                row[3] = resultSet.getString("Apellido1");
                row[4] = resultSet.getString("Apellido2");
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los propietarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para regresar al menú Visualizar
    private void regresarAlMenuVisualizar() {
        MenuConsultar menuConsultar = new MenuConsultar();
        menuConsultar.setVisible(true);
        this.dispose(); // Cerrar la ventana de Propietarios
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarPropietarios ventana = new ConsultarPropietarios();
            ventana.setVisible(true);
        });
    }
}

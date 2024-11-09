import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarUsuarios extends JFrame {

    private JTextField txtCedula;

    public ConsultarUsuarios() {
        // Configuración de la ventana
        setTitle("Consultar Usuarios");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(151, 204, 233));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar los componentes

        // Crear el campo de texto para la cédula
        JPanel panelEntrada = new JPanel();
        JLabel lblCedula = new JLabel("Cédula:");
        txtCedula = new JTextField(20);
        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setToolTipText("Digite la cedula del usuario a consultar.");
        panelEntrada.add(lblCedula);
        panelEntrada.add(txtCedula);
        panelEntrada.add(btnConsultar);
        add(panelEntrada, BorderLayout.NORTH);

        // Configurar el modelo de la tabla
        String[] columnNames = {"Cedula", "Nombre 1", "Apellido 1", "Usuario", "Contraseña"};
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

        // Acción del botón "Consultar" para cargar los datos del usuario
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText().trim();
                if (!cedula.isEmpty()) {
                    cargarUsuarios(tableModel, cedula);
                } else {
                    JOptionPane.showMessageDialog(ConsultarUsuarios.this, "Por favor ingrese una cédula válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción del botón "Regresar" para regresar al MenuConsultar
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuConsultar(); // Llamar al método que abrirá el MenuConsultar
            }
        });
    }

    // Método para cargar los usuarios en la tabla
    private void cargarUsuarios(DefaultTableModel tableModel, String cedula) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Conectar a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root",
             "Francisco20.");

            // Llamar al procedimiento almacenado
            String query = "CALL ConsultarUsuarios(?);";
            statement = connection.prepareStatement(query);
            statement.setString(1, cedula);
            resultSet = statement.executeQuery();

            // Limpiar la tabla antes de agregar los nuevos datos
            tableModel.setRowCount(0);

            // Añadir los resultados a la tabla
            if (resultSet.next()) {
                Object[] row = new Object[5];
                row[0] = resultSet.getString("Cedula");
                row[1] = resultSet.getString("Nombre1");
                row[2] = resultSet.getString("Apellido1");
                row[3] = resultSet.getString("Usuario");
                row[4] = resultSet.getString("Contraseña");
                tableModel.addRow(row);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron usuarios con la cédula proporcionada.", "Resultado de la búsqueda", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los usuarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    // Método para regresar al menú Consultar
    private void regresarAlMenuConsultar() {
        MenuConsultar menuConsultar = new MenuConsultar();
        menuConsultar.setVisible(true);
        this.dispose(); // Cerrar la ventana de Consultar Usuarios
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarUsuarios ventana = new ConsultarUsuarios();
            ventana.setVisible(true);
        });
    }
}

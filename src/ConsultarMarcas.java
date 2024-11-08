import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarMarcas extends JFrame {

    public ConsultarMarcas() {
        // Configuración de la ventana
        setTitle("Lista de Marcas");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Usamos BorderLayout para organizar los componentes
        getContentPane().setBackground(new Color(151, 204, 233));

        // Configurar el modelo de la tabla
        String[] columnNames = {"Id Marca", "Marca de Vehiculo"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);  // Añadir la tabla a la ventana

        // Crear el panel para el botón "Regresar"
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

        // Cargar las marcas en la tabla
        cargarMarca(tableModel);
    }

    // Método para cargar las marcas en la tabla
    private void cargarMarca(DefaultTableModel tableModel) {

        String query = "CALL ConsultarMarca();";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "Aiug01042004*");
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            // Añadir los resultados a la tabla
            while (resultSet.next()) {
                Object[] row = new Object[3];
                row[0] = resultSet.getInt("Id_Marca");
                row[1] = resultSet.getString("Marca_Vehiculo");
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las Marcas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para regresar al menú Visualizar
    private void regresarAlMenuVisualizar() {
        MenuConsultar menuConsultar = new MenuConsultar();
        menuConsultar.setVisible(true);
        this.dispose(); // Cerrar la ventana de cargos
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarMarcas ventana = new ConsultarMarcas();
            ventana.setVisible(true);
        });
    }
}

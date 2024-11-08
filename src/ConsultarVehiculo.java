import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConsultarVehiculo extends JFrame {

    public ConsultarVehiculo() {
        // Configuración de la ventana
        setTitle("Lista de Vehiculos");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(44, 62, 80)); // Fondo oscuro
        setLayout(new BorderLayout());

        // Configurar el modelo de la tabla
        String[] columnNames = {"Placa", "Cedula", "Año", "ID_Marca"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setBackground(new Color(255, 255, 255)); // Fondo blanco de la tabla
        table.setForeground(Color.BLACK); // Texto negro en la tabla
        table.setFont(new Font("Arial", Font.PLAIN, 12)); // Fuente de la tabla

       

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Crear el panel para el botón "Regresar"
        JPanel panelBoton = new JPanel();
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.RED);
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        btnRegresar.setToolTipText("Haz clic para regresar al menú de consultas.");
        panelBoton.add(btnRegresar);
        add(panelBoton, BorderLayout.SOUTH);

        // Acción del botón "Regresar" para regresar al MenuVisualizar
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAlMenuVisualizar();
            }
        });

        // Conexión y ejecución del procedimiento almacenado
        cargarVehiculos(tableModel);
    }

    // Método para cargar los vehículos en el modelo de la tabla
    private void cargarVehiculos(DefaultTableModel tableModel) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "Aiug01042004*");
             CallableStatement statement = connection.prepareCall("{CALL ConsultarVehiculo()}")) {

            ResultSet resultSet = statement.executeQuery();

            // Añadir cada registro al modelo de la tabla
            while (resultSet.next()) {
                String Placa = resultSet.getString("Placa");
                String Cedula = resultSet.getString("Cedula");
                String Ano = resultSet.getString("Ano");
                String Id_marca = resultSet.getString("Id_marca");

                tableModel.addRow(new Object[]{Placa, Cedula, Ano, Id_marca});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para regresar al MenuVisualizar
    private void regresarAlMenuVisualizar() {
        MenuConsultar menuConsultar = new MenuConsultar();
        menuConsultar.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsultarVehiculo ventana = new ConsultarVehiculo();
            ventana.setVisible(true);
        });
    }
}

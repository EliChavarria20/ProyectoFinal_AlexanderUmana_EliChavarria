import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;

public class ActualizarMarca extends JFrame {

    private JTextField txtIdMarca, txtMarca;
    private JButton btnRegresar;

    public ActualizarMarca() {
        // Configuración de la ventana
        setTitle("Actualizar Marca");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80)); // Fondo color oscuro
        setLocationRelativeTo(null);
        setLayout(null); // Usar layout nulo para posiciones absolutas

        // Crear el título
        JLabel lblTitulo = new JLabel("Datos de la Marca del Vehículo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE); // Color del texto
        lblTitulo.setBounds(50, 5, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Crear y añadir etiquetas y campos de texto con coordenadas personalizadas
        JLabel lblIdMarca = new JLabel("ID de Marca:");
        lblIdMarca.setBounds(50, 50, 150, 25);
        lblIdMarca.setForeground(Color.WHITE); // Color blanco
        add(lblIdMarca);

        txtIdMarca = new JTextField();
        txtIdMarca.setBounds(200, 50, 150, 25);
        txtIdMarca.setBackground(Color.WHITE);
        txtIdMarca.setForeground(Color.BLACK);
        txtIdMarca.setToolTipText("Ingrese el ID de la marca.");
        add(txtIdMarca);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(50, 100, 150, 25);
        lblMarca.setForeground(Color.WHITE); // Color blanco
        add(lblMarca);

        txtMarca = new JTextField();
        txtMarca.setBounds(200, 100, 150, 25);
        txtMarca.setBackground(Color.WHITE);
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setToolTipText("Ingrese el nombre de la marca.");
        add(txtMarca);

        // Botón Actualizar Marca
        JButton btnActualizar = new JButton("Actualizar Marca");
        btnActualizar.setBounds(50, 200, 150, 40); // Ajustar tamaño
        btnActualizar.setBackground(Color.WHITE);
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnActualizar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnActualizar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnActualizar.setToolTipText("Haz clic para actualizar la marca.");
        btnActualizar.addActionListener(e -> actualizarMarca());
        add(btnActualizar);

        // Botón Regresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(220, 200, 150, 40); // Ajustar tamaño y ubicación
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.RED);
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnRegresar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnRegresar.setToolTipText("Haz clic para regresar al menú de actualización.");
        btnRegresar.addActionListener(e -> regresarAlMenu());
        add(btnRegresar);
    }

    // Método para actualizar la marca en la base de datos
    public void actualizarMarca() {
        String idMarca = txtIdMarca.getText();
        String marcaVehiculo = txtMarca.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "Francisco20.")) {
            String query = "{CALL ActualizarMarca(?, ?)}"; // Llamada al procedimiento almacenado
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, idMarca);
            statement.setString(2, marcaVehiculo);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Marca actualizada correctamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la marca de vehículo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        txtIdMarca.setText("");
        txtMarca.setText("");
    }

    // Método para regresar al menú anterior
    private void regresarAlMenu() {
        MenuIngresar menuIngresar = new MenuIngresar();
        menuIngresar.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ActualizarMarca ventana = new ActualizarMarca();
            ventana.setVisible(true);
        });
    }
}

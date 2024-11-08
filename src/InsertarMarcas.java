import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;


public class InsertarMarcas extends JFrame {

    private JTextField txtMarca;
    private JButton btnRegresar;

    public InsertarMarcas() {
        // Configuración de la ventana
        setTitle("Insertar Marca");
        setSize(420, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80)); // Fondo color oscuro
        setLocationRelativeTo(null);
        setLayout(null); // Usar layout nulo para posiciones absolutas

        // Crear el título
        JLabel lblTitulo = new JLabel("Ingrese la Marca del Vehículo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE); // Color del texto
        lblTitulo.setBounds(50, 5, 300, 40); // Establecer las coordenadas y tamaño del título
        add(lblTitulo); // Añadir el JLabel al JFrame

        // Crear y añadir etiquetas y campos de texto con coordenadas personalizadas
        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(50, 50, 150, 25);
        lblMarca.setForeground(Color.WHITE); // Color blanco
        add(lblMarca);
        
        txtMarca = new JTextField();
        txtMarca.setBounds(200, 50, 150, 25);
        txtMarca.setBackground(Color.WHITE);
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setToolTipText("Ingrese la marca del vehículo.");
        add(txtMarca);

        // Botón Insertar Marca
        JButton btnInsertar = new JButton("Insertar Marca");
        btnInsertar.setBounds(50, 200, 150, 40); // Ajustar tamaño
        btnInsertar.setBackground(Color.WHITE);
        btnInsertar.setForeground(Color.BLACK);
        btnInsertar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnInsertar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnInsertar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnInsertar.setToolTipText("Haz clic para insertar la marca.");
        btnInsertar.addActionListener(e -> insertarMarca());
        add(btnInsertar);

        // Botón Regresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(220, 200, 150, 40); // Ajustar tamaño y ubicación
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.RED);
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnRegresar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnRegresar.setToolTipText("Haz clic para regresar al menú de inserción.");
        btnRegresar.addActionListener(e -> regresarAlMenu());
        add(btnRegresar);
    }

    private void insertarMarca() {
        String Marca_Vehiculo = txtMarca.getText();
      

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "Aiug01042004*")) {
            String query = "{CALL InsertarMarca(?)}";
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, Marca_Vehiculo);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Marca insertada correctamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al insertar la marca de vehiculo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtMarca.setText("");
    }

    private void regresarAlMenu() {
        MenuIngresar menuIngresar = new MenuIngresar();
        menuIngresar.setVisible(true);
        this.dispose();
    }
}

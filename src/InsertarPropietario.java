import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.Color;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.*;

public class InsertarPropietario extends JFrame {

    private JTextField txtCedula, txtNombre1, txtNombre2, txtApellido1, txtApellido2;
    private JButton btnRegresar;

    public InsertarPropietario() {
        setTitle("Insertar Propietario");
        setSize(420, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80)); // Beige claro

        setLocationRelativeTo(null);
        setLayout(null);

        // Title
        JLabel lblTitulo = new JLabel("Ingrese Datos del Propietario:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(70, 5, 300, 40);
        add(lblTitulo);

        // Label and text field for Cedula
        JLabel lblCedula = new JLabel("Cedula:");
        lblCedula.setBounds(50, 50, 150, 25);
        lblCedula.setForeground(Color.WHITE);
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(200, 50, 170, 25);
        txtCedula.setBackground(Color.WHITE); // White background
        txtCedula.setForeground(Color.BLACK); // Black text for visibility
        txtCedula.setToolTipText("Ingrese la cedula del propietario.");
        add(txtCedula);

        // Label and text field for Primer Nombre
        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        lblNombre1.setBounds(50, 85, 150, 25);
        lblNombre1.setForeground(Color.WHITE);
        add(lblNombre1);

        txtNombre1 = new JTextField();
        txtNombre1.setBounds(200, 85, 170, 25);
        txtNombre1.setBackground(Color.WHITE);
        txtNombre1.setForeground(Color.BLACK);
        txtNombre1.setToolTipText("Ingrese el primer nombre del propietario.");
        add(txtNombre1);

        // Label and text field for Segundo Nombre
        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        lblNombre2.setBounds(50, 120, 150, 25);
        lblNombre2.setForeground(Color.WHITE);
        add(lblNombre2);

        txtNombre2 = new JTextField();
        txtNombre2.setBounds(200, 120, 170, 25);
        txtNombre2.setBackground(Color.WHITE);
        txtNombre2.setForeground(Color.BLACK);
        txtNombre2.setToolTipText("Ingrese el segundo nombre del propietario.");
        add(txtNombre2);

        // Label and text field for Primer Apellido
        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        lblApellido1.setBounds(50, 155, 150, 25);
        lblApellido1.setForeground(Color.WHITE);
        add(lblApellido1);

        txtApellido1 = new JTextField();
        txtApellido1.setBounds(200, 155, 170, 25);
        txtApellido1.setBackground(Color.WHITE);
        txtApellido1.setForeground(Color.BLACK);
        txtApellido1.setToolTipText("Ingrese el primer apellido del propietario.");
        add(txtApellido1);

        // Label and text field for Segundo Apellido
        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        lblApellido2.setBounds(50, 190, 150, 25);
        lblApellido2.setForeground(Color.WHITE);
        add(lblApellido2);

        txtApellido2 = new JTextField();
        txtApellido2.setBounds(200, 190, 170, 25);
        txtApellido2.setBackground(Color.WHITE);
        txtApellido2.setForeground(Color.BLACK);
        txtApellido2.setToolTipText("Ingrese el segundo apellido del propietario.");
        add(txtApellido2);

        // Botón Insertar Propietario
        JButton btnInsertar = new JButton("Insertar Propietario");
        btnInsertar.setBounds(50, 330, 150, 40); // Ajustar tamaño
        btnInsertar.setBackground(Color.WHITE);
        btnInsertar.setForeground(Color.BLACK);
        btnInsertar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnInsertar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnInsertar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnInsertar.setToolTipText("Haz clic para insertar el propietario.");
        btnInsertar.addActionListener(e -> InsertarPropietario());
        add(btnInsertar);

        // Botón Regresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(220, 330, 150, 40); // Ajustar tamaño y ubicación
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.RED);
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14)); // Estilo de fuente
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Borde azul
        btnRegresar.setFocusPainted(false); // Evitar el borde al hacer foco
        btnRegresar.setToolTipText("Haz clic para regresar al menú de inserción.");
        btnRegresar.addActionListener(e -> regresarAlMenu());
        add(btnRegresar);
    }

    public void InsertarPropietario() {
        String Cedula = txtCedula.getText();
        String nombre1 = txtNombre1.getText();
        String nombre2 = txtNombre2.getText();
        String apellido1 = txtApellido1.getText();
        String apellido2 = txtApellido2.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root",
                "Aiug01042004*")) {
            String query = "{CALL InsertarPersona(?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, Cedula);
            statement.setString(2, nombre1);
            statement.setString(3, nombre2);
            statement.setString(4, apellido1);
            statement.setString(5, apellido2);

            statement.execute();
            JOptionPane.showMessageDialog(this, "Propietario insertado exitosamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al insertar el propietario: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre1.setText("");
        txtNombre2.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
    }

    private void regresarAlMenu() {
        MenuIngresar menuIngresar = new MenuIngresar();
        menuIngresar.setVisible(true);
        this.dispose();
    }
}

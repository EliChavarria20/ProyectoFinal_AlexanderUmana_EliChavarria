import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class ActualizarPropietario extends JFrame {

    private JTextField txtCedula, txtNombre1, txtNombre2, txtApellido1, txtApellido2;
    private JButton btnRegresar;

    public ActualizarPropietario() {
        setTitle("Actualizar Propietario");
        setSize(420, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80)); // Fondo color oscuro
        setLocationRelativeTo(null);
        setLayout(null);

        // Crear el título
        JLabel lblTitulo = new JLabel("Ingrese los datos del propietario", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE); // Color del texto
        lblTitulo.setBounds(50, 5, 300, 40);
        add(lblTitulo);

        // Campos para Cedula, Nombre1, Nombre2, Apellido1, Apellido2
        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(50, 50, 150, 25);
        lblCedula.setForeground(Color.WHITE); // Color blanco
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(200, 50, 150, 25);
        txtCedula.setBackground(Color.WHITE);
        txtCedula.setForeground(Color.BLACK);
        txtCedula.setToolTipText("Ingrese la cédula del propietario.");
        add(txtCedula);

        JLabel lblNombre1 = new JLabel("Primer Nombre:");
        lblNombre1.setBounds(50, 90, 150, 25);
        lblNombre1.setForeground(Color.WHITE); // Color blanco
        add(lblNombre1);

        txtNombre1 = new JTextField();
        txtNombre1.setBounds(200, 90, 150, 25);
        txtNombre1.setBackground(Color.WHITE);
        txtNombre1.setForeground(Color.BLACK);
        txtNombre1.setToolTipText("Ingrese el primer nombre del propietario.");
        add(txtNombre1);

        JLabel lblNombre2 = new JLabel("Segundo Nombre:");
        lblNombre2.setBounds(50, 130, 150, 25);
        lblNombre2.setForeground(Color.WHITE); // Color blanco
        add(lblNombre2);

        txtNombre2 = new JTextField();
        txtNombre2.setBounds(200, 130, 150, 25);
        txtNombre2.setBackground(Color.WHITE);
        txtNombre2.setForeground(Color.BLACK);
        txtNombre2.setToolTipText("Ingrese el segundo nombre del propietario.");
        add(txtNombre2);

        JLabel lblApellido1 = new JLabel("Primer Apellido:");
        lblApellido1.setBounds(50, 170, 150, 25);
        lblApellido1.setForeground(Color.WHITE); // Color blanco
        add(lblApellido1);

        txtApellido1 = new JTextField();
        txtApellido1.setBounds(200, 170, 150, 25);
        txtApellido1.setBackground(Color.WHITE);
        txtApellido1.setForeground(Color.BLACK);
        txtApellido1.setToolTipText("Ingrese el primer apellido del propietario.");
        add(txtApellido1);

        JLabel lblApellido2 = new JLabel("Segundo Apellido:");
        lblApellido2.setBounds(50, 210, 150, 25);
        lblApellido2.setForeground(Color.WHITE); // Color blanco
        add(lblApellido2);

        txtApellido2 = new JTextField();
        txtApellido2.setBounds(200, 210, 150, 25);
        txtApellido2.setBackground(Color.WHITE);
        txtApellido2.setForeground(Color.BLACK);
        txtApellido2.setToolTipText("Ingrese el segundo apellido del propietario.");
        add(txtApellido2);

        // Botón para actualizar el propietario
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(50, 250, 150, 40);
        btnActualizar.setBackground(Color.WHITE);
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnActualizar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setToolTipText("Haz clic para actualizar los datos del propietario.");
        btnActualizar.addActionListener(e -> actualizarPropietario());
        add(btnActualizar);

        // Botón para regresar al menú anterior
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(220, 250, 150, 40);
        btnRegresar.setBackground(Color.WHITE);
        btnRegresar.setForeground(Color.RED);
        btnRegresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegresar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        btnRegresar.setFocusPainted(false);
        btnRegresar.setToolTipText("Haz clic para regresar al menú de actualización.");
        btnRegresar.addActionListener(e -> regresarAlMenu());
        add(btnRegresar);
    }


    // Método para actualizar el propietario en la base de datos
    public void actualizarPropietario() {
        String cedula = txtCedula.getText();
        String nombre1 = txtNombre1.getText();
        String nombre2 = txtNombre2.getText();
        String apellido1 = txtApellido1.getText();
        String apellido2 = txtApellido2.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "Aiug01042004*")) {
            String query = "{CALL ActualizarPersona(?, ?, ?, ?, ?)}"; // Llamada al procedimiento almacenado
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, cedula);
            statement.setString(2, nombre1);
            statement.setString(3, nombre2);
            statement.setString(4, apellido1);
            statement.setString(5, apellido2);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Propietario actualizado correctamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el propietario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre1.setText("");
        txtNombre2.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
    }

    // Método para regresar al menú anterior
    private void regresarAlMenu() {
        MenuPrincipal menuPrincipal = new MenuPrincipal(); // Asegúrate de tener esta clase creada
        menuPrincipal.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ActualizarPropietario ventana = new ActualizarPropietario();
            ventana.setVisible(true);
        });
    }
}

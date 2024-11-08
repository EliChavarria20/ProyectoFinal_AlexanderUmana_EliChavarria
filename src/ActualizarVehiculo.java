import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class ActualizarVehiculo extends JFrame {

    private JTextField txtPlaca, txtCedula, txtIdMarca, txtAno;
    private JButton btnRegresar;

    public ActualizarVehiculo() {
        setTitle("Actualizar Vehículo");
        setSize(420, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80)); // Fondo color oscuro
        setLocationRelativeTo(null);
        setLayout(null);

        // Crear el título
        JLabel lblTitulo = new JLabel("Ingrese los datos del vehículo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 14));
        lblTitulo.setForeground(Color.WHITE); // Color del texto
        lblTitulo.setBounds(50, 5, 300, 40);
        add(lblTitulo);

        // Campos para Placa, Cedula, Id_marca, Ano
        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setBounds(50, 50, 150, 25);
        lblPlaca.setForeground(Color.WHITE); // Color blanco
        add(lblPlaca);

        txtPlaca = new JTextField();
        txtPlaca.setBounds(200, 50, 150, 25);
        txtPlaca.setBackground(Color.WHITE);
        txtPlaca.setForeground(Color.BLACK);
        txtPlaca.setToolTipText("Ingrese la placa del vehículo.");
        add(txtPlaca);

        JLabel lblCedula = new JLabel("Cédula del Propietario:");
        lblCedula.setBounds(50, 90, 150, 25);
        lblCedula.setForeground(Color.WHITE); // Color blanco
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(200, 90, 150, 25);
        txtCedula.setBackground(Color.WHITE);
        txtCedula.setForeground(Color.BLACK);
        txtCedula.setToolTipText("Ingrese la cédula del propietario.");
        add(txtCedula);

        JLabel lblIdMarca = new JLabel("ID de Marca:");
        lblIdMarca.setBounds(50, 130, 150, 25);
        lblIdMarca.setForeground(Color.WHITE); // Color blanco
        add(lblIdMarca);

        txtIdMarca = new JTextField();
        txtIdMarca.setBounds(200, 130, 150, 25);
        txtIdMarca.setBackground(Color.WHITE);
        txtIdMarca.setForeground(Color.BLACK);
        txtIdMarca.setToolTipText("Ingrese el ID de la marca del vehículo.");
        add(txtIdMarca);

        JLabel lblAno = new JLabel("Año:");
        lblAno.setBounds(50, 170, 150, 25);
        lblAno.setForeground(Color.WHITE); // Color blanco
        add(lblAno);

        txtAno = new JTextField();
        txtAno.setBounds(200, 170, 150, 25);
        txtAno.setBackground(Color.WHITE);
        txtAno.setForeground(Color.BLACK);
        txtAno.setToolTipText("Ingrese el año del vehículo.");
        add(txtAno);

        // Botón para actualizar el vehículo
        JButton btnActualizar = new JButton("Actualizar Vehículo");
        btnActualizar.setBounds(50, 250, 150, 40);
        btnActualizar.setBackground(Color.WHITE);
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnActualizar.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setToolTipText("Haz clic para actualizar el vehículo.");
        btnActualizar.addActionListener(e -> actualizarVehiculo());
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


    // Método para actualizar el vehículo en la base de datos
    public void actualizarVehiculo() {
        String placa = txtPlaca.getText();
        String cedula = txtCedula.getText();
        String idMarca = txtIdMarca.getText();
        String ano = txtAno.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectofinal", "root", "Francisco20.")) {
            String query = "{CALL ActualizarVehiculo(?, ?, ?, ?)}"; // Llamada al procedimiento almacenado
            CallableStatement statement = connection.prepareCall(query);
            statement.setString(1, placa);
            statement.setString(2, cedula);
            statement.setString(3, idMarca);
            statement.setString(4, ano);
            statement.execute();
            JOptionPane.showMessageDialog(this, "Vehículo actualizado correctamente.");
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el vehículo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        txtPlaca.setText("");
        txtCedula.setText("");
        txtIdMarca.setText("");
        txtAno.setText("");
    }

    // Método para regresar al menú anterior
    private void regresarAlMenu() {
        MenuPrincipal menuPrincipal = new MenuPrincipal(); // Asegúrate de tener esta clase creada
        menuPrincipal.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ActualizarVehiculo ventana = new ActualizarVehiculo();
            ventana.setVisible(true);
        });
    }
}

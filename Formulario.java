import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Formulario extends JFrame {
    public Formulario() {
        setTitle("Formulario de Registro");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(12, 2, 5, 5));

        // Labels y campos de texto
        add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Correo Electrónico:"));
        JTextField correoField = new JTextField();
        add(correoField);

        add(new JLabel("Celular:"));
        JTextField celularField = new JTextField();
        add(celularField);

        add(new JLabel("Código Postal:"));
        JTextField cpField = new JTextField();
        add(cpField);

        add(new JLabel("Dirección:"));
        JTextField direccionField = new JTextField();
        add(direccionField);

        add(new JLabel("Edad:"));
        JTextField edadField = new JTextField();
        add(edadField);

        add(new JLabel("Género:"));
        String[] generos = {"Masculino", "Femenino", "Otro"};
        JComboBox<String> generoBox = new JComboBox<>(generos);
        add(generoBox);

        add(new JLabel("Tipo de Sangre:"));
        String[] tiposSangre = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        JComboBox<String> sangreBox = new JComboBox<>(tiposSangre);
        add(sangreBox);

        add(new JLabel("Usuario:"));
        JTextField usuarioField = new JTextField();
        add(usuarioField);

        add(new JLabel("Contraseña:"));
        JPasswordField passwordField = new JPasswordField();
        add(passwordField);

        JCheckBox verPasswordCheck = new JCheckBox("Mostrar Contraseña");
        add(verPasswordCheck);

        verPasswordCheck.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (verPasswordCheck.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });

        // Botón de enviar
        JButton enviarButton = new JButton("Enviar");
        add(enviarButton);

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText().trim();
                String correo = correoField.getText().trim();
                String celular = celularField.getText().trim();
                String cp = cpField.getText().trim();
                String direccion = direccionField.getText().trim();
                String edadStr = edadField.getText().trim();
                String usuario = usuarioField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (nombre.isEmpty() || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,50}")) {
                    JOptionPane.showMessageDialog(null, "Nombre inválido.");
                    return;
                }

                if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    JOptionPane.showMessageDialog(null, "Correo inválido.");
                    return;
                }

                if (!celular.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "Celular inválido. Debe tener 10 dígitos.");
                    return;
                }

                if (!cp.matches("\\d{5}")) {
                    JOptionPane.showMessageDialog(null, "Código Postal inválido. Debe tener 5 dígitos.");
                    return;
                }

                if (direccion.isEmpty() || direccion.length() < 5) {
                    JOptionPane.showMessageDialog(null, "Dirección inválida.");
                    return;
                }

                if (!edadStr.matches("\\d+") || Integer.parseInt(edadStr) < 0 || Integer.parseInt(edadStr) > 99) {
                    JOptionPane.showMessageDialog(null, "Edad inválida. Debe estar entre 0 y 99 años.");
                    return;
                }

                if (!usuario.matches("[A-Za-z0-9_]{5,20}")) {
                    JOptionPane.showMessageDialog(null, "Usuario inválido. Debe tener entre 5 y 20 caracteres.");
                    return;
                }

                if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!?¡¿*]).{8,}$")) {
                    JOptionPane.showMessageDialog(null, "Contraseña inválida. Debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial.");
                    return;
                }

                // Limpiar los campos
                nombreField.setText("");
                correoField.setText("");
                celularField.setText("");
                cpField.setText("");
                direccionField.setText("");
                edadField.setText("");
                usuarioField.setText("");
                passwordField.setText("");
                generoBox.setSelectedIndex(0);
                sangreBox.setSelectedIndex(0);

                // Mostrar los datos ingresados en una nueva ventana
                JOptionPane.showMessageDialog(null, "Datos ingresados:\n" +
                        "Nombre: " + nombre + "\n" +
                        "Correo: " + correo + "\n" +
                        "Celular: " + celular + "\n" +
                        "Código Postal: " + cp + "\n" +
                        "Dirección: " + direccion + "\n" +
                        "Edad: " + edadStr + "\n" +
                        "Género: " + generoBox.getSelectedItem() + "\n" +
                        "Tipo de Sangre: " + sangreBox.getSelectedItem() + "\n" +
                        "Usuario: " + usuario);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Formulario();
    }
}

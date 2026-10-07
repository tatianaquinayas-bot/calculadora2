package vista;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class VistaCalculadora extends JFrame {

    private JTextField pantalla;
    private JPanel panelNumeros;
    private List<JButton> botones = new ArrayList<>();

    public VistaCalculadora() {
        setTitle("Calculadora");
        setSize(320, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        pantalla = new JTextField("0");
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setFont(new Font("Arial", Font.BOLD, 28));
        add(pantalla, BorderLayout.NORTH);

        panelNumeros = new JPanel(new GridLayout(4, 3, 5, 5));
        String[] teclas = {"7","8","9","4","5","6","1","2","3","0",".","="};
        for (String t : teclas) {
            panelNumeros.add(crearBoton(t, t));
        }
        add(panelNumeros, BorderLayout.CENTER);
                JPanel panelOperaciones = new JPanel(new GridLayout(4, 1, 5, 5));
        panelOperaciones.add(crearBoton("÷", "/"));
        panelOperaciones.add(crearBoton("×", "*"));
        panelOperaciones.add(crearBoton("−", "-"));
        panelOperaciones.add(crearBoton("+", "+"));
        add(panelOperaciones, BorderLayout.EAST);
        
                JPanel panelEspeciales = new JPanel(new GridLayout(1, 4, 5, 5));
        panelEspeciales.add(crearBoton("C", "C"));
        panelEspeciales.add(crearBoton("√", "sqrt"));
        panelEspeciales.add(crearBoton("3√", "cbrt"));
        panelEspeciales.add(crearBoton("ln", "ln"));
        add(panelEspeciales, BorderLayout.SOUTH);
        
                pantalla.setPreferredSize(new Dimension(300, 70));
        pantalla.setBackground(Color.BLACK);
        pantalla.setForeground(Color.PINK);
        for (JButton b : botones) {
            b.setFocusPainted(false);
            b.setBackground(new Color(230, 230, 230));
        }
    }

    private JButton crearBoton(String texto, String comando) {
        JButton b = new JButton(texto);
        b.setActionCommand(comando);
        b.setFont(new Font("Arial", Font.BOLD, 18));
        botones.add(b);
        return b;
    }
        public void agregarListener(ActionListener l) {
        for (JButton b : botones) {
            b.addActionListener(l);
        }
    }

    public String getTextoPantalla() {
        return pantalla.getText();
    }

    public void setTextoPantalla(String texto) {
        pantalla.setText(texto);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaCalculadora().setVisible(true));
    }
}
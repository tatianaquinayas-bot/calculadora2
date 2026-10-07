/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ModeloCalculadora;
import vista.VistaCalculadora;

public class ControladorCalculadora implements ActionListener {
    
    private final VistaCalculadora vista;
    private final ModeloCalculadora modelo;
    
    private double primerNumero = 0;
    private String operacion = "";
    private boolean nuevoNumero = true;

public ControladorCalculadora(VistaCalculadora vista, ModeloCalculadora modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.agregarListener(this);
    }
 
       @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
                try {
            if (comando.matches("[0-9]") || comando.equals(".")) {
                manejarNumero(comando);
            } else if (comando.matches("[+\\-*/]")) {
                seleccionarOperacion(comando);
            } else if (comando.equals("=")) {
                calcularResultado();
            } else if (comando.equals("C")) {
                limpiar();
            }
        } catch (ArithmeticException | NumberFormatException ex) {
            vista.setPantalla("Error");
            reiniciarEstado();
        }
    }
        private void manejarNumero(String digito) {
        String actual = vista.getPantalla();
        if (nuevoNumero || actual.equals("0") || actual.equals("Error")) {
            vista.setPantalla(digito.equals(".") ? "0." : digito);
            nuevoNumero = false;
        } else if (!(digito.equals(".") && actual.contains("."))) {
            vista.setPantalla(actual + digito);
        }
    }

    private void reiniciarEstado() {
        primerNumero = 0;
        operacion = "";
        nuevoNumero = true;
    }
        private void seleccionarOperacion(String op) {
        if (!operacion.isEmpty() && !nuevoNumero) {
            calcularResultado();
        }
        primerNumero = Double.parseDouble(vista.getPantalla());
        operacion = op;
        nuevoNumero = true;
    }

    private void calcularResultado() {
        if (operacion.isEmpty()) {
            return;
        }
        double segundoNumero = Double.parseDouble(vista.getPantalla());
        double resultado = modelo.calcular(operacion, primerNumero, segundoNumero);
        mostrar(resultado);
        operacion = "";
        nuevoNumero = true;
    }

    private void limpiar() {
        vista.setPantalla("0");
        reiniciarEstado();
    }

    private void mostrar(double resultado) {
        if (Double.isNaN(resultado) || Double.isInfinite(resultado)) {
            vista.setPantalla("Error");
            reiniciarEstado();
        } else if (resultado == Math.rint(resultado) && Math.abs(resultado) < 1e15) {
            vista.setPantalla(String.valueOf((long) resultado));
        } else {
            vista.setPantalla(String.valueOf(resultado));
        }
    }
}
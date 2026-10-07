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

            } else if (comando.equals("sqrt")
                    || comando.equals("cbrt")
                    || comando.equals("ln")) {

                operacionUnaria(comando);
            }

        } catch (ArithmeticException | NumberFormatException ex) {

            vista.setTextoPantalla("Error");

            reiniciarEstado();
        }
    }

    private void manejarNumero(String digito) {

        String actual = vista.getTextoPantalla();

        if (nuevoNumero
                || actual.equals("0")
                || actual.equals("Error")) {

            if (digito.equals(".")) {
                vista.setTextoPantalla("0.");
            } else {
                vista.setTextoPantalla(digito);
            }

            nuevoNumero = false;

        } else if (!(digito.equals(".") && actual.contains("."))) {

            vista.setTextoPantalla(actual + digito);
        }
    }

    private void seleccionarOperacion(String op) {

        if (!operacion.isEmpty() && !nuevoNumero) {
            calcularResultado();
        }

        primerNumero =
                Double.parseDouble(vista.getTextoPantalla());

        operacion = op;

        nuevoNumero = true;
    }

    private void calcularResultado() {

        if (operacion.isEmpty()) {
            return;
        }

        double segundoNumero =
                Double.parseDouble(vista.getTextoPantalla());

        double resultado =
                modelo.calcular(
                        operacion,
                        primerNumero,
                        segundoNumero
                );

        mostrar(resultado);

        operacion = "";
        nuevoNumero = true;
    }

    private void operacionUnaria(String op) {

        double valor =
                Double.parseDouble(vista.getTextoPantalla());

        double resultado =
                modelo.calcular(op, valor, 0);

        mostrar(resultado);

        nuevoNumero = true;
    }

    private void limpiar() {

        vista.setTextoPantalla("0");

        reiniciarEstado();
    }

    private void mostrar(double resultado) {

        if (Double.isNaN(resultado)
                || Double.isInfinite(resultado)) {

            vista.setTextoPantalla("Error");

            reiniciarEstado();

        } else if (resultado == Math.rint(resultado)
                && Math.abs(resultado) < 1e15) {

            vista.setTextoPantalla(
                    String.valueOf((long) resultado)
            );

        } else {

            vista.setTextoPantalla(
                    String.valueOf(resultado)
            );
        }
    }

    private void reiniciarEstado() {

        primerNumero = 0;
        operacion = "";
        nuevoNumero = true;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

import controlador.ControladorCalculadora;
import modelo.ModeloCalculadora;
import vista.VistaCalculadora;

public class Calculadora {

    public static void main(String[] args) {

        ModeloCalculadora modelo = new ModeloCalculadora();

        VistaCalculadora vista = new VistaCalculadora();

        ControladorCalculadora controlador
                = new ControladorCalculadora(vista, modelo);

        vista.setVisible(true);
    }
}
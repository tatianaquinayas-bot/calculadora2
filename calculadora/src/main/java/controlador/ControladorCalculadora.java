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
    }
}
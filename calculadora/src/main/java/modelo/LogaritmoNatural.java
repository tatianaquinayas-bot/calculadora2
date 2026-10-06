/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author 57323
 */
public class LogaritmoNatural extends Operacion {

    @Override
    public double calcular(double numero1, double numero2) {
        return Math.log(numero1);
    }
}
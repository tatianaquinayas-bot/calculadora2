/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author 57323
 */
public class ModeloCalculadora {

    public double calcular(String tipoOperacion,
                           double numero1,
                           double numero2) {

        Operacion operacion;

        switch (tipoOperacion) {

            case "+":
                operacion = new Suma();
                break;

            case "-":
                operacion = new Resta();
                break;

            case "*":
                operacion = new Multiplicacion();
                break;

            case "/":
                operacion = new Division();
                break;

            case "sqrt":
                operacion = new RaizCuadrada();
                break;

            case "cbrt":
                operacion = new RaizCubica();
                break;

            case "ln":
                operacion = new LogaritmoNatural();
                break;

            default:
                return 0;
        }

        return operacion.calcular(numero1, numero2);
    }
}
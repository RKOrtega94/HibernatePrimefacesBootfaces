/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Usuario
 */
public class SumaFactura {
    
    public Double suma(Double valor, int cantidad){
        Double result = 0.0;
        result = valor * cantidad;
        return result;
    }
}

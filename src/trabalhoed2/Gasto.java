/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed2;

/**
 *
 * @author ice
 */
public class Gasto {
    
    private String data;
    private String hora;
    private float totalGasto;

    public Gasto() {
    }

    public Gasto(String data, float totalGasto) {
        this.data = data;
        this.totalGasto = totalGasto;
    }

    public float getGasto() {
        return totalGasto;
    }

    public void setGasto(float totalGasto) {
        this.totalGasto = totalGasto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
    
}

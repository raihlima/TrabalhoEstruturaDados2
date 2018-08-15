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
    private float gasto;

    public Gasto() {
    }

    public Gasto(String data, float gasto) {
        this.data = data;
        this.gasto = gasto;
    }

    public float getGasto() {
        return gasto;
    }

    public void setGasto(float gasto) {
        this.gasto = gasto;
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

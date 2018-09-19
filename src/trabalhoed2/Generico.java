/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed2;

/**
 *
 * @author carcara
 */
public class Generico {
    private String nome;
    private float totalGasto;

    public Generico() {
    }

    public Generico(String nome, float totalGasto) {
        this.nome = nome;
        this.totalGasto = totalGasto;
    }

    public float getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(float totalGasto) {
        this.totalGasto = totalGasto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}

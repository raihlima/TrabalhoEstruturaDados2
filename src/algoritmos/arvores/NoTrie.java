/*
 * To change this license header, choose License Headers in Project Propediries.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.arvores;

/**
 *
 * 
 */
public class NoTrie {

    private NoTrie[] vetorLetras;
    private boolean isFinal;
    private double totalGasto;
    private String palavra;

    public NoTrie() {
        //26 para letras
        //1 para espaço
        //1 para virgula
        this.totalGasto =0;
        this.vetorLetras = new NoTrie[28];
        this.palavra = "";
    }

    public NoTrie[] getVetorLetras() {
        return vetorLetras;
    }

    public void setVetorLetras(NoTrie[] arr) {
        this.vetorLetras = arr;
    }

    public boolean isIsFinal() {
        return isFinal;
    }

    public void setIsFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }
    
    public void incementarTotalGasto(double gasto){
        this.totalGasto = this.totalGasto + gasto;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }
    
    
}

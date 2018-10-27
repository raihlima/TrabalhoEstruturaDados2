/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.arvores;

/**
 *
 * @author ice
 */
class No {
    private int id;
    private int altura;
    private int balanceamento;
    private No dir;
    private No esq;
    private No pai;
    private Chave chave;

    public No() {
        this.altura = 0;
        this.balanceamento = 0;
        this.dir = null;
        this.esq = null;
        this.pai = null;
    }

    public No(int id,Chave chave) {
        this.altura = 0;
        this.balanceamento = 0;
        this.id = id;
        this.dir = null;
        this.esq = null;
        this.chave = chave;
        this.pai = null;
    }
    
    public No(int id,Chave chave, No pai) {
        this.altura = 0;
        this.balanceamento = 0;
        this.id = id;
        this.dir = null;
        this.esq = null;
        this.chave = chave;
        this.pai = pai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public No getDir() {
        return dir;
    }

    public void setDir(No dir) {
        this.dir = dir;
    }

    public No getEsq() {
        return esq;
    }

    public void setEsq(No esq) {
        this.esq = esq;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getBalanceamento() {
        return balanceamento;
    }

    public void setBalanceamento(int balanceamento) {
        this.balanceamento = balanceamento;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }
    
    

}

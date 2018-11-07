/*
 * To change this license header, choose License Headers in Project Propediries.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.arvores;

/**
 *
 * @author rodri
 */
class NoRubroNegro {

    private int id;
    private int cor;
    private NoRubroNegro esq;
    private NoRubroNegro dir = null;
    private NoRubroNegro pai = null;
    private Chave chave;

     NoRubroNegro(int id) {
        this.id = id;
        this.cor = -1;
        this.dir = null;
        this.esq = null;
    }
    
    public NoRubroNegro(int id, Chave chave) {
        this.chave = chave;
        this.id = id;
        this.cor = -1;
        this.dir = null;
        this.esq = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public NoRubroNegro getEsq() {
        return esq;
    }

    public void setEsq(NoRubroNegro esq) {
        this.esq = esq;
    }

    public NoRubroNegro getDir() {
        return dir;
    }

    public void setDir(NoRubroNegro dir) {
        this.dir = dir;
    }

    public NoRubroNegro getPai() {
        return pai;
    }

    public void setPai(NoRubroNegro pai) {
        this.pai = pai;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }
    
}

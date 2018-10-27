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
public class NoRubroNegro {

    private NoRubroNegro esq;
    private NoRubroNegro dir;
    private int id;
    private int cor;
    private Chave chave;

    /* Constructor */
    public NoRubroNegro(int id) {
        this(id, null, null);
    }

    /* Constructor */
    public NoRubroNegro(int id, NoRubroNegro esq, NoRubroNegro dir) {
        this.esq = esq;
        this.dir = dir;
        this.id = id;
        this.cor = 1;
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

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

}


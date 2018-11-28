package algoritmos.arvores;

/**
 *
 * 
 */
class NoVermelhoPreto {

    private int id;
    private int cor;
    private NoVermelhoPreto esq;
    private NoVermelhoPreto dir = null;
    private NoVermelhoPreto pai = null;
    private Chave chave;

     NoVermelhoPreto(int id) {
        this.id = id;
        this.cor = -1;
        this.dir = null;
        this.esq = null;
    }
    
    public NoVermelhoPreto(int id, Chave chave) {
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

    public NoVermelhoPreto getEsq() {
        return esq;
    }

    public void setEsq(NoVermelhoPreto esq) {
        this.esq = esq;
    }

    public NoVermelhoPreto getDir() {
        return dir;
    }

    public void setDir(NoVermelhoPreto dir) {
        this.dir = dir;
    }

    public NoVermelhoPreto getPai() {
        return pai;
    }

    public void setPai(NoVermelhoPreto pai) {
        this.pai = pai;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }
    
}

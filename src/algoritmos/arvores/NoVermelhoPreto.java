package algoritmos.arvores;

/**
 * Implementa o NoVermelhoPreto 
 */
class NoVermelhoPreto {

    private int id;
    private int cor;
    private NoVermelhoPreto esq;
    private NoVermelhoPreto dir = null;
    private NoVermelhoPreto pai = null;
    private Chave chave;

    /**
     * Contrutor da Classe
     * @param id 
     */
     NoVermelhoPreto(int id) {
        this.id = id;
        this.cor = -1;
        this.dir = null;
        this.esq = null;
    }
    
     /**
      * Construtor da Classe
      * @param id
      * @param chave 
      */
    public NoVermelhoPreto(int id, Chave chave) {
        this.chave = chave;
        this.id = id;
        this.cor = -1;
        this.dir = null;
        this.esq = null;
    }

    /**
     * Retorna o identificador do no
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Altera o identificador do no
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna a cor do no
     * @return cor
     */
    public int getCor() {
        return cor;
    }

    /**
     * Altera a cor do no
     * @param cor 
     */
    public void setCor(int cor) {
        this.cor = cor;
    }

    /**
     * Retorna o no da esquerda
     * @return noEsq
     */
    public NoVermelhoPreto getEsq() {
        return esq;
    }

    /**
     * Altera o no da esquerda
     * @param esq 
     */
    public void setEsq(NoVermelhoPreto esq) {
        this.esq = esq;
    }

    /**
     * Retorna o no da Direita
     * @return noDIr
     */
    public NoVermelhoPreto getDir() {
        return dir;
    }

    /**
     * Altera o no da direita
     * @param dir 
     */
    public void setDir(NoVermelhoPreto dir) {
        this.dir = dir;
    }

    /**
     * Retorna o pai do no
     * @return noPai
     */
    public NoVermelhoPreto getPai() {
        return pai;
    }

    /**
     * Altera o pai do no
     * @param pai 
     */
    public void setPai(NoVermelhoPreto pai) {
        this.pai = pai;
    }

    /**
     * rRtorna a chave do no
     * @return chave
     */
    public Chave getChave() {
        return chave;
    }

    /**
     * Altera a chave do no
     * @param chave 
     */
    public void setChave(Chave chave) {
        this.chave = chave;
    }
    
}

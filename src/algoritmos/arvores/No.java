
package algoritmos.arvores;

/**
 * Implementacao do no de algumas arvores
 */
class No {
    private int id;
    private int altura;
    private int balanceamento;
    private No dir;
    private No esq;
    private No pai;
    private Chave chave;

    /**
     * Contrutor do No
     */
    public No() {
        this.altura = 0;
        this.balanceamento = 0;
        this.dir = null;
        this.esq = null;
        this.pai = null;
    }

    /**
     * Construtor do No
     * @param id identificado do No
     * @param chave 
     */
    public No(int id,Chave chave) {
        this.altura = 0;
        this.balanceamento = 0;
        this.id = id;
        this.dir = null;
        this.esq = null;
        this.chave = chave;
        this.pai = null;
    }
    
    /**
     * Construtor do No
     * @param id identificador do No
     * @param chave
     * @param pai 
     */
    public No(int id,Chave chave, No pai) {
        this.altura = 0;
        this.balanceamento = 0;
        this.id = id;
        this.dir = null;
        this.esq = null;
        this.chave = chave;
        this.pai = pai;
    }

    /**
     * Retorna o Id do No
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Altera o id do no
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o no da direira
     * @return noDireita
     */
    public No getDir() {
        return dir;
    }

    /**
     * Altera o no da direita
     * @param dir no
     */
    public void setDir(No dir) {
        this.dir = dir;
    }

    /**
     * Retorna o no da esquerda
     * @return noEsquerda
     */
    public No getEsq() {
        return esq;
    }

    /**
     * ALtera o no da esquerda
     * @param esq No
     */
    public void setEsq(No esq) {
        this.esq = esq;
    }

    /**
     * Retorna a altura
     * @return altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * Altera a altura
     * @param altura 
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * Retorna o valor de balanceamento
     * @return balanceamento
     */
    public int getBalanceamento() {
        return balanceamento;
    }

    /**
     * Altera o valor de balanceamento
     * @param balanceamento 
     */
    public void setBalanceamento(int balanceamento) {
        this.balanceamento = balanceamento;
    }

    /**
     * Retorna a chave armazenada
     * @return chave
     */
    public Chave getChave() {
        return chave;
    }

    /**
     * Altera a chave armazenada
     * @param chave 
     */
    public void setChave(Chave chave) {
        this.chave = chave;
    }

    /**
     * Retorna o valor do no pai
     * @return noPai
     */
    public No getPai() {
        return pai;
    }

    /**
     * Altera o no Pai
     * @param pai 
     */
    public void setPai(No pai) {
        this.pai = pai;
    }    

}

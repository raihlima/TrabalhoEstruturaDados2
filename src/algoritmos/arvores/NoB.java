package algoritmos.arvores;

/**
 * Implementacao do No B
 */
public class NoB {

    private int ordem; 
    private Chave[] chave; 
    private NoB[] filho;
    private boolean folha;
    private int larguraFilho;
    final int DIFERENCA_ALTURA = 30;
    final int DIFERENCA_IRMAOS = 5;

    /**
     * Construtor da classe
     * @param ordem Valor da Ordem
     */
    public NoB(int ordem) {
        this.chave = new Chave[(ordem - 1)];
        for (int i = 0; i < ordem - 1; i++) {
            this.chave[i]=null;
        }
        this.filho = new NoB[(ordem)];
        for (int i = 0; i < ordem; i++) {
            this.filho[i]=null;
        }
        this.folha = true;
        this.ordem = 0;
    }

    /**
     * Retorna o vetor de chaves
     * @return vetorChaves
     */
    public Chave[] getChave() {
        return chave;
    }

    /**
     * Altera o vetor de chaves
     * @param chave 
     */
    public void setChave(Chave[] chave) {
        this.chave = chave;
    }

    /**
     * Retorna os filho
     * @return vetorFilho
     */
    public NoB[] getFilho() {
        return filho;
    }

    /**
     * ALtera o vetor de FIlho
     * @param filho 
     */
    public void setFilho(NoB[] filho) {
        this.filho = filho;
    }

    /**
     * Retorna se o no é folha
     * @return verdadeiro ou falso
     */
    public boolean isFolha() {
        return folha;
    }

    /**
     * Altera se o no e folha
     * @param folha 
     */
    public void setFolha(boolean folha) {
        this.folha = folha;
    }

    /**
     * Retorna o valor de Ordem
     * @return ordem
     */
    public int getOrdem() {
        return ordem;
    }

    /**
     * Altera o valor de Ordem do no
     * @param ordem 
     */
    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

}

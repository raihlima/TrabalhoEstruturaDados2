package algoritmos.arvores;

/**
 * Implementa o no da Arvore Trie
 */
public class NoTrie {

    private NoTrie[] vetorLetras;
    private boolean isFinal;
    private double totalGasto;
    private String palavra;

    /**
     * Construtor da Classe
     */
    public NoTrie() {
        //26 para letras
        //1 para espaço
        //1 para virgula
        this.totalGasto =0;
        this.vetorLetras = new NoTrie[28];
        this.palavra = "";
    }

    /**
     * Retorna o vetor de letras
     * @return vetorLetras
     */
    public NoTrie[] getVetorLetras() {
        return vetorLetras;
    }

    /**
     * Altera o vetor de letras
     * @param vetor 
     */
    public void setVetorLetras(NoTrie[] vetor) {
        this.vetorLetras = vetor;
    }

    /**
     * Retorna se é final de palavra
     * @return isFInal
     */
    public boolean isIsFinal() {
        return isFinal;
    }

    /**
     * Altera se é final de palavra
     * @param isFinal 
     */
    public void setIsFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    /**
     * Retorna o total gasto
     * @return totalGasto
     */
    public double getTotalGasto() {
        return totalGasto;
    }

    /**
     * Altera o valor do gasto
     * @param totalGasto 
     */
    public void setTotalGasto(double totalGasto) {
        this.totalGasto = totalGasto;
    }
    
    /**
     * Soma o gasto atual com o novo gasto
     * @param gasto 
     */
    public void incementarTotalGasto(double gasto){
        this.totalGasto = this.totalGasto + gasto;
    }

    /**
     * Retorna a palavra salva no no
     * @return palavra
     */
    public String getPalavra() {
        return palavra;
    }

    /**
     * Altera a palavra salva no no
     * @param palavra 
     */
    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }
    
    
}

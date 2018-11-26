/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.arvores;

/**
 *
 * @author rodri
 */
public class NoB {

    private int ordem; //Atributo que guarda a quantidade de chaves no nÃ³
    private Chave[] chave; //vetor das chaves
    private NoB[] filho;//vetor dos filhos
    private boolean folha;//Atributo que indica se a nÃ³ eh folha ou nao
    //private int X;//Atributo que guarda a posicao X onde o NÃ³ deve aparecer na tela
    //private int Y;//Atributo que guarda a posicao Y onde o NÃ³ deve aparecer na tela
    private int larguraFilho;
    final int DIFERENCA_ALTURA = 30;
    final int DIFERENCA_IRMAOS = 5;

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

    public Chave[] getChave() {
        return chave;
    }

    public void setChave(Chave[] chave) {
        this.chave = chave;
    }

    public NoB[] getFilho() {
        return filho;
    }

    public void setFilho(NoB[] filho) {
        this.filho = filho;
    }

    public boolean isFolha() {
        return folha;
    }

    public void setFolha(boolean folha) {
        this.folha = folha;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int computeSize() {
        return ordem * 28 + 12;
    }

    public int UpdateLFilho() {
        larguraFilho = 0;
        if (!folha) {
            for (int i = 0; i < ordem + 1; i++) {
                larguraFilho += filho[i].UpdateLFilho();
            }
        } else {
            larguraFilho = computeSize() + DIFERENCA_IRMAOS;
        }
        return larguraFilho;
    }
}

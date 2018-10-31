package algoritmos.arvores;

public class NoB {

    private int n; //Atributo que guarda a quantidade de chaves no nó
    //private Vector<Integer> chave; //vetor das chaves
    private Chave[] chave;
    //private Vector<NoB> filho;//vetor dos filhos
    private NoB[] filho;
    private boolean folha;//Atributo que indica se a nó eh folha ou nao
    private int larguraFilho;
    final int DIFERENCA_ALTURA = 30;
    final int DIFERENCA_IRMAOS = 5;

    public NoB(int n) {
        this.chave = new Chave[n - 1];
        for (int i = 0; i < n - 1; i++) {
            this.chave[i] = (null);
        }
        this.filho = new NoB[(n)];
        for (int i = 0; i < n; i++) {
            this.filho[i] = (null);
        }
        this.folha = true;
        this.n = 0;
    }

    public Chave[] getChave() {
        return chave;
    }

    public void setChave(Chave[] chave) {
        this.chave = chave;
    }

    public Chave getChave(int index) {
        if (index >= chave.length || index < 0) {
            throw new IndexOutOfBoundsException("Index da Chave do Nó B errado!");
        } else {
            return chave[index];
        }
    }

    public void setChave(int index, Chave chave) {
        if (index >= this.chave.length || index < 0) {
            throw new IndexOutOfBoundsException("Index da Chave do Nó B errado!");
        } else {
            this.chave[index] = chave;
        }
    }

    public NoB[] getFilho() {
        return filho;
    }

    public void setFilho(NoB[] filho) {
        this.filho = filho;
    }

    public NoB getFilho(int index) {
        if (index >= chave.length || index < 0) {
            throw new IndexOutOfBoundsException("Index da Chave do Filho do nó B errado!");
        } else {
            return filho[index];
        }
    }

    public void setFilho(int index, NoB noB) {
        if (index >= this.chave.length || index < 0) {
            throw new IndexOutOfBoundsException("Index da Chave do Filho do nó B errado!");
        } else {
            this.filho[index] = noB;
        }
    }

    public boolean isFolha() {
        return folha;
    }

    public void setFolha(boolean folha) {
        this.folha = folha;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int computeSize() {
        return n * 28 + 12;
    }

    public int UpdateLFilho() {
        larguraFilho = 0;
        if (!folha) {
            for (int i = 0; i < n + 1; i++) {
                larguraFilho += filho[i].UpdateLFilho();
            }
        } else {
            larguraFilho = computeSize() + DIFERENCA_IRMAOS;
        }
        return larguraFilho;
    }
}

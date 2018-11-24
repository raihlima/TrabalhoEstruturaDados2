package algoritmos.arvores;

public class ArvoreTrie {

    private NoTrie raiz;

    public ArvoreTrie() {
        raiz = new NoTrie();
    }

    //funcao para tratar os caracteres
    private int retornaIndice(char c) {
        int aux = c - 'a';
        if (aux < 0) {
            aux = aux + 32;
        }
        if (aux < 0) {
            aux = -1;
        }
        return aux;
    }

    // Insere a palavra na trie.
    public void inserir(String palavra, double gasto) {
        NoTrie no = raiz;
        for (int i = 0; i < palavra.length(); i++) {
            char c = palavra.charAt(i);
            int indice = retornaIndice(c);
            if (indice >= 0) {
                if (no.getVetorLetras()[indice] == null) {
                    NoTrie temp = new NoTrie();
                    no.getVetorLetras()[indice] = temp;
                    no = temp;
                } else {
                    no = no.getVetorLetras()[indice];
                }
            }
        }
        no.setIsFinal(true);
        no.incementarTotalGasto(gasto);
        no.setPalavra(palavra);
    }

    // Retorna se a palavra está na trie
    public boolean procurar(String palavra) {
        NoTrie no = procurarNo(palavra);
        if (no == null) {
            return false;
        } else {
            if (no.isIsFinal()) {
                return true;
            }
        }

        return false;
    }

    // Retorna se existe alguam palavra na trie que comeca com o prefixo dado
    public boolean comecaCom(String prefixo) {
        NoTrie no = procurarNo(prefixo);
        if (no == null) {
            return false;
        } else {
            return true;
        }
    }

    public NoTrie procurarNo(String s) {
        NoTrie no = raiz;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int indice = retornaIndice(c);
            if (indice >= 0) {
                if (no.getVetorLetras()[indice] != null) {
                    no = no.getVetorLetras()[indice];
                } else {
                    return null;
                }
            }
        }

        if (no == raiz) {
            return null;
        }

        return no;
    }

}

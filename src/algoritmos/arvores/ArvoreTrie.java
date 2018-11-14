package algoritmos.arvores;

public class ArvoreTrie {

    private NoTrie raiz;

    public ArvoreTrie() {
        raiz = new NoTrie();
    }

    // Insere a palavra na trie.
    public void inserir(String palavra) {
        NoTrie p = raiz;
        for (int i = 0; i < palavra.length(); i++) {
            char c = palavra.charAt(i);
            int indice = c - 'a';
            if (p.arr[indice] == null) {
                NoTrie temp = new NoTrie();
                p.arr[indice] = temp;
                p = temp;
            } else {
                p = p.arr[indice];
            }
        }
        p.ehFinal = true;
    }

    // Retorna se a palavra está na trie
    public boolean procurar(String palavra) {
        NoTrie p = procurarNo(palavra);
        if (p == null) {
            return false;
        } else {
            if (p.ehFinal) {
                return true;
            }
        }

        return false;
    }

    // Retorna se existe alguam palavra na trie que comeca com o prefixo dado
    public boolean comecaCom(String prefixo) {
        NoTrie p = procurarNo(prefixo);
        if (p == null) {
            return false;
        } else {
            return true;
        }
    }

    public NoTrie procurarNo(String s) {
        NoTrie p = raiz;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int indice = c - 'a';
            if (p.arr[indice] != null) {
                p = p.arr[indice];
            } else {
                return null;
            }
        }

        if (p == raiz) {
            return null;
        }

        return p;
    }
}

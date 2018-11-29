package algoritmos.arvores;

/**
 * Implementacao da Arvore Trie
 */
import java.util.ArrayList;

public class ArvoreTrie {

    private NoTrie raiz;

    /**
     * Contrutor da arvore
     */
    public ArvoreTrie() {
        raiz = new NoTrie();
    }

    /**
     * Metodo para retornar o indice para preeencher a arvore
     * @param letra letra a ser buscada e inserida na arvore
     * @return index
     */
    private int retornaIndice(char letra) {
        int aux = letra - 'a';
        if (aux < 0) {
            aux = aux + 32;
        }
        if (aux < 0) {
            aux = -1;
        }
        return aux;
    }
    
    /**
     * Metodo para inserir na arvore
     * @param palavra Palavra a ser inserida
     * @param gasto gasto a ser adicionado
     */
    public void inserir(String palavra, double gasto) {
        NoTrie no = raiz;
        for (int i = 0; i < palavra.length(); i++) {
            char letra = palavra.charAt(i);
            int indice = retornaIndice(letra);
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

    /**
     * Metodo para procurar uma determinada palavra na arvore
     * @param palavra Palavra a ser buscada
     * @return verdadeiro ou falso
     */
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

    /**
     * Metodo para retornar o no de uma determinada palavra
     * @param palavra Palavra a ser buscada
     * @return No encontrado
     */
    public NoTrie procurarNo(String palavra) {
        NoTrie no = raiz;
        for (int i = 0; i < palavra.length(); i++) {
            char letra = palavra.charAt(i);
            int indice = retornaIndice(letra);
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

    /**
     * Metodo para imprimir a arvore
     */
    public void imprimeArvore() {
        imprimeArvore(this.raiz);
    }

    /**
     * Metodo auxiliar para imprimir a arvore
     * @param no No de pesquisa
     */
    private void imprimeArvore(NoTrie no) {
        if (no != null) {
            if (no.isIsFinal()) {
                System.out.println(no.getPalavra());
            }
            for (int i = 0; i < no.getVetorLetras().length; i++) {
                imprimeArvore(no.getVetorLetras()[i]);
            }
        }
    }

/**
 * Metodo para retornar uma lista de palavras a partir da palavra de busca
 * @param palavra inicio da busca
 * @return lista de palavras
 */
    public ArrayList<String> retornaListaPalavras(String palavra) {
        ArrayList<String> aux = new ArrayList<>();
        String palavraCortada = "";
        if (palavra == null) {
            retornaListaPalavras(this.raiz, aux);
        } else {
            for (int i = palavra.length(); i >= 0; i--) {
                palavraCortada = palavra.substring(0, i);
                if (procurar(palavra)) {
                    break;
                }
            }
            if (palavraCortada.length() == 0) {
                retornaListaPalavras(this.raiz, aux);
            } else {
                NoTrie no = procurarNo(palavraCortada);
                retornaListaPalavras(no, aux);
            }          
        }
        return aux;
    }

    /**
     * Metodo auxiliar para retornar uma lista de palavras a partir da palavra de busca
     * @param no No de inicio da busca
     * @param lista lista a ser preenchida
     * @return lista de palavras
     */
    private ArrayList<String> retornaListaPalavras(NoTrie no, ArrayList<String> lista) {
        if (no != null) {
            if (no.isIsFinal()) {
                lista.add(no.getPalavra());
            }
            for (int i = 0; i < no.getVetorLetras().length; i++) {
                retornaListaPalavras(no.getVetorLetras()[i], lista);
            }
        }
        return lista;
    }
}

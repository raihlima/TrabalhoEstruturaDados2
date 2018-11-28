/*
 * To change this license header, choose License Headers in Project Propediries.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.arvores;

/**
 *
 * 
 */
import java.util.ArrayList;

public class ArvoreTrie {

    private NoTrie raiz;

    public ArvoreTrie() {
        raiz = new NoTrie();
    }

    //funcao para tratar os caracteres
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

    public boolean comecaCom(String prefixo) {
        NoTrie no = procurarNo(prefixo);
        if (no == null) {
            return false;
        } else {
            return true;
        }
    }

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

    public void imprimeArvore() {
        imprimeArvore(this.raiz);
    }

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

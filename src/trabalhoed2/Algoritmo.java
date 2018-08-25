/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed2;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 *
 * @author ice
 */
public class Algoritmo {

    public static void bubbleSortDeputados(ArrayList<Deputado> deputados) {
        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        //auxiliar.compare(string, string1)

        for (int i = 0; i < deputados.size(); i++) {
            for (int j = 1; j < deputados.size() - i; j++) {
                if (collator.compare(deputados.get(j - 1).getNome(), deputados.get(j).getNome()) > 0) {
                    //if (deputados.get(j - 1).getNome().compareToIgnoreCase(deputados.get(j).getNome()) > 0) {
                    Collections.swap(deputados, j, j - 1);
                }
            }
        }

    }

    public static void bubbleSortRecibos(Deputado deputado) {
        for (int i = 0; i < deputado.getRecibos().size(); i++) {
            for (int j = 1; j < deputado.getRecibos().size() - i; j++) {
                if (deputado.getRecibos().get(j).getGasto() < deputado.getRecibos().get(j - 1).getGasto()) {
                    Collections.swap(deputado.getRecibos(), j - 1, j);
                }
            }
        }
    }

    public static int particionaQuickSort(ArrayList<Deputado> deputados, int min, int max) {
        //Cria o leitor de letras ascii
        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        //Define o pivo como o maior da lista
        Deputado pivo = deputados.get(max);
        int i = (min - 1); // indice do menor elemento
        for (int j = min; j < max; j++) {
            //Compara as strings
            if (collator.compare(deputados.get(j).getNome(), pivo.getNome()) < 0) {
                i++;//avanca o menor
                Collections.swap(deputados, i, j);
            }
        }
        Deputado temp = deputados.get(i + 1);
        Collections.swap(deputados, (i + 1), max);

        return i + 1;
    }

    void quickSort(ArrayList<Deputado> deputados, int min, int max) {
        if (min < max) {
            //define o indice da particao (ip)
            int ip = particionaQuickSort(deputados, min, max);
            // Recusividade para ordenar os elementos antes e depois da particao
            quickSort(deputados, min, ip - 1);
            quickSort(deputados, ip + 1, max);
        }
    }

    public void heapSort(ArrayList<Deputado> deputados) {
        int n = deputados.size();
        // Constroi a Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(deputados, n, i);
        }
        // Extrai um elemento por vez da heap
        for (int i = n - 1; i >= 0; i--) {
            // Move a raiz para o fim
            Collections.swap(deputados, 0, i);
            // heapify a heap reduzida
            heapify(deputados, i, 0);
        }
    }

    private void heapify(ArrayList<Deputado> deputados, int n, int i) {
        int maior = i;  // Inicializa o maior como raiz
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        // Se filho da esq é maior que a raiz
        if (esq < n && (collator.compare(deputados.get(esq).getNome(), deputados.get(maior).getNome()) > 0)) {
            maior = esq;
        }
        // Se filho da dir é maior que a raiz
        if (dir < n && (collator.compare(deputados.get(dir).getNome(), deputados.get(maior).getNome()) > 0)) {
            maior = dir;
        }
        // Se maior nao e raiz
        if (maior != i) {
            Collections.swap(deputados, i, maior);
            // Heapify recursivamente a sub arvore afetada
            heapify(deputados, n, maior);
        }
    }

    void insertionSort(ArrayList<Deputado> deputados) {
        int n = deputados.size();
        //Cria o leitor de letras ascii
        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        for (int i = 1; i < n; ++i) {
            Deputado key = deputados.get(i);
            int j = i - 1;

            /* Move elements of deputados[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            //while (j >= 0 && deputados[j] > key) {
            while (collator.compare(deputados.get(j).getNome(), key.getNome()) > 0){
                deputados.set(j+1, deputados.get(j));
                j = j - 1;
            }
            deputados.set((j + 1), key);
        }
    }
}

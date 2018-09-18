/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import trabalhoed2.Deputado;

/**
 *
 * @author ice
 */
public class Algoritmo {

    /**
     * Esta função faz o bubbleSort
     *
     * @param deputados
     */
    public static void bubbleSortDeputados(ListaEncadeada<Deputado> deputados) {
        //Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        //auxiliar.compare(string, string1)

        for (int i = 0; i < deputados.getTamanho(); i++) {
            for (int j = 1; j < deputados.getTamanho() - i; j++) {
                if (deputados.retornaInfo(j - 1).getTotalGasto() > deputados.retornaInfo(j).getTotalGasto()) {
                    //if (deputados.get(j - 1).getNome().compareToIgnoreCase(deputados.get(j).getNome()) > 0) {
                    deputados.troca(j, j - 1);
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

    public static void bubbleSortArrayListInteiro(List<Integer> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 1; j < lista.size() - i; j++) {
                if (lista.get(j) < lista.get(j - 1)) {
                    int aux = lista.get(j);
                    lista.set(j, lista.get(j - 1));
                    lista.set(j - 1, aux);
                }
            }
        }
    }

    private static int particionaQuickSortRec(ListaEncadeada<Deputado> deputados, int min, int max) {

        //Define o pivo como o maior da lista
        Deputado pivo = deputados.retornaInfo(max);
        int i = (min - 1); // indice do menor elemento
        for (int j = min; j < max; j++) {
            //Compara as strings
            if (deputados.retornaInfo(j).getTotalGasto() < pivo.getTotalGasto()) {
                i++;//avanca o menor
                deputados.troca(i, j);
            }
        }
        Deputado temp = deputados.retornaInfo(i + 1);
        deputados.troca(i + 1, max);

        return i + 1;
    }

    public static void quickSortRec(ListaEncadeada<Deputado> deputados) {
        quickSortRec(deputados, 0, deputados.getTamanho() - 1);
    }

    private static void quickSortRec(ListaEncadeada<Deputado> deputados, int min, int max) {
        if (min < max) {
            //define o indice da particao (ip)
            int ip = particionaQuickSortRec(deputados, min, max);
            // Recusividade para ordenar os elementos antes e depois da particao
            quickSortRec(deputados, min, ip - 1);
            quickSortRec(deputados, ip + 1, max);
        }
    }

    public static void quicksortMedianaDeTres(Deputado[] deputados) {
        quicksortMedianaDeTres(deputados, 0, deputados.length - 1);
    }

    private static void quicksortMedianaDeTres(Deputado[] deputados, int inicio, int fim) {
        if (inicio < fim) {
            //realiza a partição
            int q = partition(deputados, inicio, fim);
            //ordena a partição esquerda
            quicksortMedianaDeTres(deputados, inicio, q - 1);
            //ordena a partição direita
            quicksortMedianaDeTres(deputados, q + 1, fim);
        }
    }

    //Método de partição
    private static int partition(Deputado[] deputados, int inicio, int fim) {
        //procura a mediana entre inicio, meio e fim
        int meio = (inicio + fim) / 2;
        float a = deputados[inicio].getTotalGasto();
        float b = deputados[meio].getTotalGasto();
        float c = deputados[fim].getTotalGasto();
        int medianaIndice; //índice da mediana
        //A sequência de if...else a seguir verifica qual é a mediana
        if (a < b) {
            if (b < c) {
                //a < b && b < c
                medianaIndice = meio;
            } else {
                if (a < c) {
                    //a < c && c <= b
                    medianaIndice = fim;
                } else {
                    //c <= a && a < b
                    medianaIndice = inicio;
                }
            }
        } else {
            if (c < b) {
                //c < b && b <= a
                medianaIndice = meio;
            } else {
                if (c < a) {
                    //b <= c && c < a
                    medianaIndice = fim;
                } else {
                    //b <= a && a <= c
                    medianaIndice = inicio;
                }
            }
        }
        //coloca o elemento da mediana no fim para poder usar o Quicksort de Cormen
        trocaDeputados(deputados, medianaIndice, fim);

        //*******************ALGORITMO DE PARTIÇÃO DE CORMEN*********************
        //o pivo é o elemento final
        Deputado pivo = deputados[fim];
        int i = inicio - 1;
        /*
         * Este laço irá varrer os vetores da esquerda para direira
         * procurando os elementos que são menores ou iguais ao pivô.
         * Esses elementos são colocados na partição esquerda.         
         */
        for (int j = inicio; j <= fim - 1; j++) {
            if (deputados[j].getTotalGasto() <= pivo.getTotalGasto()) {
                i = i + 1;
                trocaDeputados(deputados, i, j);
            }
        }
        //coloca o pivô na posição de ordenação
        trocaDeputados(deputados, i + 1, fim);
        return i + 1; //retorna a posição do pivô
    }

    private static void trocaDeputados(Deputado[] deputado, int i, int j) {
        Deputado aux = deputado[i];
        deputado[i] = deputado[j];
        deputado[j] = aux;
    }

    public static void heapSort(ListaEncadeada<Deputado> deputados) {
        int n = deputados.getTamanho();
        // Constroi a Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(deputados, n, i);
        }
        // Extrai um elemento por vez da heap
        for (int i = n - 1; i >= 0; i--) {
            // Move a raiz para o fim
            deputados.troca(0, i);
            // heapify a heap reduzida
            heapify(deputados, i, 0);
        }
    }

    private static void heapify(ListaEncadeada<Deputado> deputados, int n, int i) {
        int maior = i;  // Inicializa o maior como raiz
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        // Se filho da esq é maior que a raiz
        if (esq < n && ((deputados.retornaInfo(esq).getTotalGasto() > deputados.retornaInfo(maior).getTotalGasto()))) {
            maior = esq;
        }
        // Se filho da dir é maior que a raiz
        if (dir < n && ((deputados.retornaInfo(dir).getTotalGasto() > deputados.retornaInfo(maior).getTotalGasto()))) {
            maior = dir;
        }
        // Se maior nao e raiz
        if (maior != i) {
            deputados.troca(i, maior);
            // Heapify recursivamente a sub arvore afetada
            heapify(deputados, n, maior);
        }
    }

    public static void insertionSort(ListaEncadeada<Deputado> deputados) {

        for (int i = 1; i < deputados.getTamanho(); i++) {
            Deputado chave = deputados.retornaInfo(i);
            int j = i - 1;

            while (j >= 0 && deputados.retornaInfo(j).getTotalGasto() > chave.getTotalGasto()) {
                deputados.altera(j + 1, deputados.retornaInfo(j));
                j = j - 1;
            }
            deputados.altera((j + 1), chave);
        }
    }

    private static void merge(ListaEncadeada<Deputado> deputados, int esq, int meio, int dir) {

        // Encontra os tamanhos dos dois sub arrays para serem mesclados
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        // Cria os arrays temporarios
        ListaEncadeada<Deputado> esqArray = new ListaEncadeada();
        ListaEncadeada<Deputado> dirArray = new ListaEncadeada();
        // Copia os dados para o novo array
        for (int i = 0; i < n1; ++i) {
            esqArray.insereFinal(deputados.retornaInfo(esq + i));
        }
        for (int j = 0; j < n2; ++j) {
            dirArray.insereFinal(deputados.retornaInfo(meio + 1 + j));
        }
        // Junta os arrays temporarios
        // Indice inicial dos sub arrays
        int i = 0, j = 0;
        // Indice inicial dos sub arrays
        int k = esq;
        while (i < n1 && j < n2) {
            if (esqArray.retornaInfo(i).getTotalGasto() <= dirArray.retornaInfo(j).getTotalGasto()) {
                deputados.altera(k, esqArray.retornaInfo(i));
                i++;
            } else {
                deputados.altera(k, dirArray.retornaInfo(j));
                j++;
            }
            k++;
        }
        // Copia os elementos do vetor esq se houver
        while (i < n1) {
            deputados.altera(k, esqArray.retornaInfo(i));
            //deputados[k] = esqVet[i];
            i++;
            k++;
        }
        // Copia os elementos do vetor dir se houver
        while (j < n2) {
            deputados.altera(k, dirArray.retornaInfo(j));
            j++;
            k++;
        }
    }

    public static void mergeSort(ListaEncadeada<Deputado> deputados) {
        mergeSort(deputados, 0, deputados.getTamanho() - 1);
    }

    private static void mergeSort(ListaEncadeada<Deputado> deputados, int esq, int dir) {
        if (esq < dir) {
            // Encontra o termo do meio
            int meio = (esq + dir) / 2;
            // Ordena primeira e segunda metade
            mergeSort(deputados, esq, meio);
            mergeSort(deputados, meio + 1, dir);
            // Junta as metades
            merge(deputados, esq, meio, dir);
        }
    }

    public static void shellSort(ListaEncadeada<Deputado> deputados) {
        int h = 1;
        int n = deputados.getTamanho();
        // Define o h para o while
        while (h < n) {
            h = h * 3 + 1;
        }
        //
        h = h / 3;
        Deputado c;
        int j;

        while (h > 0) {
            for (int i = h; i < n; i++) {
                c = deputados.retornaInfo(i);
                j = i;
                while (j >= h && (deputados.retornaInfo(j - h).getTotalGasto() >= c.getTotalGasto())) {
                    deputados.altera(j, deputados.retornaInfo(j - h));
                    j = j - h;
                }
                deputados.altera(j, c);
            }
            h = h / 2;
        }
    }

    public Deputado[] tabela(int tam) {
        Deputado[] tab = new Deputado[tam];
        for (int i = 0; i < tam; i++) {
            tab[i] = null;
        }
        return tab;
    }

    public int hash(int k, int m) {
        return k % m;
    }

    public int primo(int k) {
        for (int i = k; k > 0; i--) {
            if (ehPrimo(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean ehPrimo(int k) {
        for (int i = 2; i < k; i++) {
            if (k % i == 0) {
                return false;
            }
        }
        return true;
    }

    public Deputado[] sondagemLinear(ArrayList<Deputado> deputados, int tam) {
        Deputado[] tabela = tabela(tam);
        int h = primo(tam);
        int pos;
        for (int i = 0; i < tam; i++) {
            pos = (hash(deputados.get(i).getId(), h));
            if (tabela[pos] == null) {
                tabela[(hash(deputados.get(i).getId(), h))] = deputados.get(i);
            } else {
                int j = i + 1;
                while ((tabela[j] != null) && j < tam) {
                    j++;
                }
                if (tabela[j] != null) {
                    tabela[j] = deputados.get(i);
                } else {
                    j = 0;
                    while (tabela[j] != null) {
                        j++;
                    }
                    tabela[j] = deputados.get(i);
                }
            }
        }
        return tabela;
    }

    public Deputado[] sondagemQuadratica(ArrayList<Deputado> deputados, int tam) {
        Deputado[] tabela = tabela(tam);
        int h = primo(tam);
        int pos;

        return tabela;
    }

}

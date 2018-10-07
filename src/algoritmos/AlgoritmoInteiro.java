/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//TODO terminar os comentarios das funcoes
package algoritmos;

import dados.Relatorio;
import java.util.ArrayList;
import java.util.List;
import trabalhoed2.Generico;
import trabalhoed2.Partido;

/**
 *
 * @author ice
 */
public class AlgoritmoInteiro {

    /*
    *   Algoritmos de ordenação dos gastos dos Deputados
     */
    /**
     * Esta função contém o código para execução do algoritmo de Bubble Sort
     *
     * @param listaInteiro
     */
    public static void bubbleSort(ListaEncadeada<Integer> listaInteiro, Relatorio relatorio) {
        int contIteracao = 0;

        for (int i = 0; i < listaInteiro.getTamanho(); i++) {
            contIteracao++;
            for (int j = 1; j < listaInteiro.getTamanho() - i; j++) {
                contIteracao++;
                if (listaInteiro.retornaInfo(j - 1) > listaInteiro.retornaInfo(j)) {
                    listaInteiro.troca(j, j - 1);
                    contIteracao++;
                }
                contIteracao++;
            }
            contIteracao++;
        }
        contIteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contIteracao));
    }

    /**
     * Esta função contém o código para execução do algoritmo de Insertion Sort
     *
     * @param listaInteiro
     */
    public static void insertionSort(ListaEncadeada<Integer> listaInteiro, Relatorio relatorio) {
        insertionSort(listaInteiro, 0, listaInteiro.getTamanho(), relatorio);
    }

    private static void insertionSort(ListaEncadeada<Integer> listaInteiro, int min, int max, Relatorio relatorio) {
        int contIteracao = 1;

        for (int i = min + 1; i < max; i++) {
            contIteracao++;
            Integer chave = listaInteiro.retornaInfo(i);
            int j = i - 1;

            while (j >= 0 && listaInteiro.retornaInfo(j) > chave) {
                contIteracao++;
                listaInteiro.altera(j + 1, listaInteiro.retornaInfo(j));
                j = j - 1;
            }
            contIteracao++;
            listaInteiro.altera((j + 1), chave);
        }
        contIteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contIteracao));
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Merge Sort
     *
     * @param listaInteiro
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao
    public static void mergeSort(ListaEncadeada<Integer> listaInteiro, Relatorio relatorio) {
        mergeSort(listaInteiro, 0, listaInteiro.getTamanho() - 1, relatorio);
    }

    //Funcao de chamada recursiva do algoritmo Merge Sort
    private static void mergeSort(ListaEncadeada<Integer> listaInteiro, int esq, int dir, Relatorio relatorio) {
        int contInteracao = 1;
        if (esq < dir) {
            // Encontra o termo do meio
            int meio = (esq + dir) / 2;
            // Ordena primeira e segunda metade
            mergeSort(listaInteiro, esq, meio, relatorio);
            mergeSort(listaInteiro, meio + 1, dir, relatorio);
            // Junta as metades
            merge(listaInteiro, esq, meio, dir, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    //Funcao principal do algoritmo Merge Sort
    private static void merge(ListaEncadeada<Integer> listaInteiro, int esq, int meio, int dir, Relatorio relatorio) {
        // Encontra os tamanhos dos dois sub arrays para serem mesclados
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        int contInteracao = 1;

        // Cria os arrays temporarios
        ListaEncadeada<Integer> esqArray = new ListaEncadeada();
        ListaEncadeada<Integer> dirArray = new ListaEncadeada();
        // Copia os dados para o novo array
        for (int i = 0; i < n1; ++i) {
            contInteracao++;
            esqArray.insereFinal(listaInteiro.retornaInfo(esq + i));
        }
        contInteracao++;
        for (int j = 0; j < n2; ++j) {
            contInteracao++;
            dirArray.insereFinal(listaInteiro.retornaInfo(meio + 1 + j));
        }
        contInteracao++;
        // Junta os arrays temporarios
        // Indice inicial dos sub arrays
        int i = 0, j = 0;
        // Indice inicial dos sub arrays
        int k = esq;
        while (i < n1 && j < n2) {
            contInteracao++;
            if (esqArray.retornaInfo(i) <= dirArray.retornaInfo(j)) {
                listaInteiro.altera(k, esqArray.retornaInfo(i));
                contInteracao++;
                i++;
            } else {
                listaInteiro.altera(k, dirArray.retornaInfo(j));
                contInteracao++;
                j++;
            }
            contInteracao++;
            k++;
        }
        contInteracao++;
        // Copia os elementos do vetor esq se houver
        while (i < n1) {
            contInteracao++;
            listaInteiro.altera(k, esqArray.retornaInfo(i));
            contInteracao++;
            //listaInteiro[k] = esqVet[i];
            i++;
            k++;
        }
        contInteracao++;
        // Copia os elementos do vetor dir se houver
        while (j < n2) {
            contInteracao++;
            listaInteiro.altera(k, dirArray.retornaInfo(j));
            contInteracao++;
            j++;
            k++;
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Quick Sort
     *
     * @param listaInteiro
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao da recurcao
    public static void quickSortRec(ListaEncadeada<Integer> listaInteiro, Relatorio relatorio) {
        quickSortRec(listaInteiro, 0, listaInteiro.getTamanho() - 1, relatorio);
    }

    //Executa a funcao recursivamente em todos os vetores apos as particoes
    private static void quickSortRec(ListaEncadeada<Integer> listaInteiro, int min, int max, Relatorio relatorio) {
        int contInteracao = 1;
        if (min < max) {
            contInteracao++;
            //define o indice da particao (ip)
            int ip = particionaQuickSortRec(listaInteiro, min, max, relatorio);
            // Recusividade para ordenar os elementos antes e depois da particao
            quickSortRec(listaInteiro, min, ip - 1, relatorio);
            quickSortRec(listaInteiro, ip + 1, max, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    //Cria as particoes para o algoritmo
    private static int particionaQuickSortRec(ListaEncadeada<Integer> listaInteiro, int min, int max, Relatorio relatorio) {
        int contInteracao = 1;
        //Define o pivo como o maior da lista
        Integer pivo = listaInteiro.retornaInfo(max);
        int i = (min - 1); // indice do menor elemento
        for (int j = min; j < max; j++) {
            contInteracao++;
            //Compara as strings
            if (listaInteiro.retornaInfo(j) < pivo) {
                i++;//avanca o menor
                listaInteiro.troca(i, j);
            }
            contInteracao++;
        }
        contInteracao++;
        Integer temp = listaInteiro.retornaInfo(i + 1);
        listaInteiro.troca(i + 1, max);
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
        return i + 1;
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Quick Sort
     * Mediana de 3
     *
     * @param listaInteiro
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao
    public static void quicksortMedianaK(ListaEncadeada<Integer> listaInteiro, int k, Relatorio relatorio) {
        quicksortMedianaK(listaInteiro, 0, listaInteiro.getTamanho() - 1, k, relatorio);
    }

    //Método de partição do Quick Sort Mediana de 3
    private static int particionaMedianaK(ListaEncadeada<Integer> listaInteiro, int inicio, int fim, int k, Relatorio relatorio) {
        //procura a mediana entre inicio, meio e fim
        float div = ((float) (fim - inicio)) / (float) (k - 1);
        float aux = 0;
        int[] indice = new int[k];
        int contInteracao = 1;
        for (int i = 0; i < k; i++) {
            contInteracao++;
            indice[i] = (int) aux;
            aux = aux + div;
        }
        contInteracao++;

        int n = indice.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            contInteracao++;
            for (int j = 1; j < (n - i); j++) {
                contInteracao++;
                if (listaInteiro.retornaInfo(indice[j - 1]) > listaInteiro.retornaInfo(indice[j])) {
                    //swap elements  
                    temp = indice[j - 1];
                    indice[j - 1] = indice[j];
                    indice[j] = temp;
                }
                contInteracao++;
            }
            contInteracao++;
        }
        contInteracao++;
        int medianaIndice = (inicio + fim) / 2;
        trocaDeputados(listaInteiro, medianaIndice, fim, relatorio);

        //o pivo é o elemento final
        Integer pivo = listaInteiro.retornaInfo(fim);
        int i = inicio - 1;

        for (int j = inicio; j <= fim - 1; j++) {
            contInteracao++;
            if (listaInteiro.retornaInfo(j) <= pivo) {
                i = i + 1;
                trocaDeputados(listaInteiro, i, j, relatorio);
            }
            contInteracao++;
        }
        contInteracao++;
        //coloca o pivô na posição de ordenação
        trocaDeputados(listaInteiro, i + 1, fim, relatorio);
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
        return i + 1; //retorna a posição do pivô
    }

    //Funcao principal do algoritmo, executa a recursao
    private static void quicksortMedianaK(ListaEncadeada<Integer> listaInteiro, int inicio, int fim, int k, Relatorio relatorio) {
        int contInteracao = 1;
        if (inicio < fim) {
            //realiza a partição
            int q = particionaMedianaK(listaInteiro, inicio, fim, k, relatorio);
            //ordena a partição esquerda
            quicksortMedianaK(listaInteiro, inicio, q - 1, k, relatorio);
            //ordena a partição direita
            quicksortMedianaK(listaInteiro, q + 1, fim, k, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));

    }

    /**
     * Esta função contém o código para execução da troca (swap) de objetos no
     * vetor
     *
     * @param listaInteiro
     */
    private static void trocaDeputados(ListaEncadeada<Integer> listaInteiro, int i, int j, Relatorio relatorio) {
        Integer aux = listaInteiro.retornaInfo(i);
        listaInteiro.altera(i, listaInteiro.retornaInfo(j));
        listaInteiro.altera(j, aux);
        int contInteracao = 2;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    public static void quickSortHibrido(ListaEncadeada<Integer> listaInteiro, int k, Relatorio relatorio) {
        quickSortHibrido(listaInteiro, 0, listaInteiro.getTamanho() - 1, k, relatorio);
    }

    private static void quickSortHibrido(ListaEncadeada<Integer> listaInteiro, int min, int max, int k, Relatorio relatorio) {
        int size = (max + 1) - min;
        int contInteracao = 1;
        if (size <= k) { // inserion sort se o tamanho for menor que 10
            insertionSort(listaInteiro, min, size,relatorio);
        } else // quicksort se for maior
        {
            int pivo = particionaQuickSortRec(listaInteiro, min, max,relatorio);
            quickSortRec(listaInteiro, min, pivo - 1,relatorio);
            quickSortRec(listaInteiro, pivo + 1, max,relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao()+contInteracao));
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Heap Sort
     *
     * @param listaInteiro
     */
    //Chamada da funcao principal
    public static void heapSort(ListaEncadeada<Integer> listaInteiro, Relatorio relatorio) {
        int n = listaInteiro.getTamanho();
        // Constroi a Heap
        int contInteracao = 0;
        for (int i = n / 2 - 1; i >= 0; i--) {
            contInteracao++;
            heapify(listaInteiro, n, i,relatorio);
        }
        contInteracao++;
        // Extrai um elemento por vez da heap
        for (int i = n - 1; i >= 0; i--) {
            contInteracao++;
            // Move a raiz para o fim
            listaInteiro.troca(0, i);
            // heapify a heap reduzida
            heapify(listaInteiro, i, 0, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao()+contInteracao));
    }

    //Funcao principal do algoritmo de Heap Sort
    private static void heapify(ListaEncadeada<Integer> listaInteiro, int n, int i, Relatorio relatorio) {
        int maior = i;  // Inicializa o maior como raiz
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
        int contInteracao = 0;

        // Se filho da esq é maior que a raiz
        if (esq < n && ((listaInteiro.retornaInfo(esq) > listaInteiro.retornaInfo(maior)))) {
            maior = esq;
        }
        contInteracao++;
        // Se filho da dir é maior que a raiz
        if (dir < n && ((listaInteiro.retornaInfo(dir) > listaInteiro.retornaInfo(maior)))) {
            maior = dir;
        }
        contInteracao++;
        // Se maior nao e raiz
        if (maior != i) {
            listaInteiro.troca(i, maior);
            // Heapify recursivamente a sub arvore afetada
            heapify(listaInteiro, n, maior,relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao()+contInteracao));
    }

    /**
     * Esta função contém o código para execução do algoritmo de Shell Sort
     *
     * @param listaInteiro
     */
    //Funcao principal do algoritmo de Shell Sort
    public static void shellSort(ListaEncadeada<Integer> listaInteiro, Relatorio relatorio) {
        int h = 1;
        int n = listaInteiro.getTamanho();
        int contInteracao = 0;
        // Define o h para o while
        while (h < n) {
            contInteracao++;
            h = h * 3 + 1;
        }
        contInteracao++;

        h = h / 3;
        Integer c;
        int j;

        while (h > 0) {
            contInteracao++;
            for (int i = h; i < n; i++) {
                contInteracao++;
                c = listaInteiro.retornaInfo(i);
                j = i;
                while (j >= h && (listaInteiro.retornaInfo(j - h) >= c)) {
                    contInteracao++;
                    listaInteiro.altera(j, listaInteiro.retornaInfo(j - h));
                    contInteracao++;
                    j = j - h;
                }
                contInteracao++;
                listaInteiro.altera(j, c);
                contInteracao++;
            }
            contInteracao++;
            h = h / 2;
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao()+contInteracao));
    }
    /*
    *   AlgoritmoDeputado para ordenar lista inteira
     */
    /**
     * Esta função contém o código para execução do algoritmo de Bubble Sort
     *
     * @param lista
     */
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

    /**
     * Esta função contém o código para execução do algoritmo de Merge Sort
     *
     * @param lista
     * @param esq
     * @param meio
     * @param dir
     */
    private static void mergeInteiro(List<Integer> lista, int esq, int meio, int dir) {

        // Encontra os tamanhos dos dois sub arrays para serem mesclados
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        // Cria os arrays temporarios
        List<Integer> esqArray = new ArrayList<>();
        List<Integer> dirArray = new ArrayList<>();
        // Copia os dados para o novo array
        for (int i = 0; i < n1; ++i) {
            esqArray.add(lista.get(esq + i));
        }
        for (int j = 0; j < n2; ++j) {
            dirArray.add(lista.get(meio + 1 + j));
        }
        // Junta os arrays temporarios
        // Indice inicial dos sub arrays
        int i = 0, j = 0;
        // Indice inicial dos sub arrays
        int k = esq;
        while (i < n1 && j < n2) {
            if (esqArray.get(i) <= dirArray.get(j)) {
                lista.set(k, esqArray.get(i));
                i++;
            } else {
                lista.set(k, dirArray.get(j));
                j++;
            }
            k++;
        }
        // Copia os elementos do vetor esq se houver
        while (i < n1) {
            lista.set(k, esqArray.get(i));
            //listaInteiros[k] = esqVet[i];
            i++;
            k++;
        }
        // Copia os elementos do vetor dir se houver
        while (j < n2) {
            lista.set(k, dirArray.get(j));
            j++;
            k++;
        }
    }

    /**
     * Esta função contém o código para execução do algoritmo de Merge Sort
     *
     * @param lista
     * @param esq
     * @param dir
     */
    public static void mergeSortInteiro(List<Integer> lista, int esq, int dir) {
        if (esq < dir) {
            // Encontra o termo do meio
            int meio = (esq + dir) / 2;
            // Ordena primeira e segunda metade
            mergeSortInteiro(lista, esq, meio);
            mergeSortInteiro(lista, meio + 1, dir);
            // Junta as metades
            mergeInteiro(lista, esq, meio, dir);
        }
    }

    //TODO Java Doc a partir daqui
    /*
    *   Aqui começam os algoritmos de Hashing
     */
    private static Integer[] tabela(int tam) {
        Integer[] tab = new Integer[tam];
        for (int i = 0; i < tam; i++) {
            tab[i] = null;
        }
        return tab;
    }

    private static ListaEncadeada<Integer>[] tabelaEncadeada(int tam) {
        ListaEncadeada[] tab = new ListaEncadeada[tam];//falta tipo inteiro na lista?
        for (int i = 0; i < tam; i++) {
            tab[i] = new ListaEncadeada();
        }
        return tab;
    }

    private static Integer[][] tabelaCoalescida(int tam) {
        Integer[][] tab = new Integer[tam][2];
        for (int i = 0; i < tam; i++) {
            tab[i][0] = null;
            tab[i][1] = null;
        }
        return tab;
    }

    private static int hash(int k, int m) {
        return k % m;
    }

    private static int hash2(int k, int j) {
        int aux = (k * k * j * j) + (j) + 11;
        return aux;
    }

    private static int primo(int k) {
        for (int i = k; k > 0; i--) {
            if (ehPrimo(i)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean ehPrimo(int k) {
        for (int i = 2; i < k; i++) {
            if (k % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Funcoes de hashing
     */
    public static Integer[] sondagemLinear(ListaEncadeada<Integer> listaInteiros) {
        int pos;
        int h = primo(listaInteiros.getTamanho());
        Integer[] tabela = tabela(listaInteiros.getTamanho());
        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            pos = (hash(listaInteiros.retornaInfo(i), h));
            while (tabela[pos] != null) {
                pos = hash(pos + 1, listaInteiros.getTamanho());
            }
            tabela[pos] = listaInteiros.retornaInfo(i);
        }
        return tabela;
    }

    public static Integer[] sondagemQuadratica(ListaEncadeada<Integer> listaInteiros) {
        int pos;
        int h = primo(listaInteiros.getTamanho());
        Integer[] tabela = tabela(listaInteiros.getTamanho());
        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            pos = (hash(listaInteiros.retornaInfo(i), h));
            int j = 0;
            while (tabela[pos] != null) {
                j++;
                pos = hash(pos + j * j, listaInteiros.getTamanho());
            }
            tabela[pos] = listaInteiros.retornaInfo(i);
        }
        return tabela;
    }

    public static Integer[] duploHashing(ListaEncadeada<Integer> listaInteiros) {
        int pos;
        int h = primo(listaInteiros.getTamanho());
        Integer[] tabela = tabela(listaInteiros.getTamanho());
        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            pos = (hash(listaInteiros.retornaInfo(i), h));
            int j = 0;
            while (tabela[pos] != null) {
                j++;
                pos = hash(hash(listaInteiros.retornaInfo(i), h) + j * hash2(listaInteiros.retornaInfo(i), j), listaInteiros.getTamanho());
            }
            tabela[pos] = listaInteiros.retornaInfo(i);
        }
        return tabela;
    }

    public static ListaEncadeada[] encadeamentoSeparado(ListaEncadeada<Integer> listaInteiros) {
        int pos;
        ListaEncadeada<Integer>[] tabela = tabelaEncadeada(50);
        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            pos = (hash(listaInteiros.retornaInfo(i), 50));
            tabela[pos].insereFinal(listaInteiros.retornaInfo(i));
        }
        return tabela;
    }

    public static Integer[][] encadeamentoCoalescido(ListaEncadeada<Integer> listaInteiros) {
        int pos;
        int h = primo(listaInteiros.getTamanho());
        Integer[][] tabela = tabelaCoalescida(listaInteiros.getTamanho());
        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            pos = (hash(listaInteiros.retornaInfo(i), h));
            if (tabela[pos][0] != null) {
                pos = listaInteiros.getTamanho() - 1;
                while (tabela[pos][0] != null) {
                    pos = pos - 1;
                }
                tabela[pos][0] = listaInteiros.retornaInfo(i);
                //Cria um dep aux que usa o id como posicao para o proximo
                //TODO Expandir o comentario acima
                int aux;
                aux=pos;
                tabela[hash(listaInteiros.retornaInfo(i), h)][1] = aux;
            } else {
                tabela[pos][0] = listaInteiros.retornaInfo(i);
            }
        }
        return tabela;
    }

    /**
     * Funcoes de busca de hashing
     */
    public static Integer buscaSondagemLinear(Integer inteiro, Integer[] tabela) {
        int h = primo(tabela.length);
        int pos = (hash(inteiro, h));
        if (tabela[pos] == inteiro) {
            return tabela[pos];//Encontrou o Integer requerido na primeira iteração(não há colisões)
        } else {
            int cont = 0; //Contador para verificar se o dep nao foi encontrado
            while (tabela[pos] != inteiro && cont < tabela.length) {
                pos = hash(pos + 1, tabela.length);
                cont++;
            }
            if (cont < tabela.length) {
                return tabela[pos];//Encontrou o Integer Requerido(há colisões)
            } else {
                return null;//Não Encontrou o Integer Requerido
            }
        }
    }

    public static Integer buscaSondagemQuadratica(Integer inteiro, Integer[] tabela) {
        int h = primo(tabela.length);
        int pos = (hash(inteiro, h));
        if (tabela[pos] == inteiro) {
            return tabela[pos];
        } else {
            int cont = 0; //contador para verificar se o dep nao foi encontrado
            int j = 0;
            while (tabela[pos] != inteiro && cont < tabela.length) { //TODO Revisar a condicao de parada (cont)
                j++;
                pos = hash(pos + j * j, tabela.length);
                cont++;
            }
            if (cont < tabela.length) {
                return tabela[pos];
            } else {
                return null;
            }
        }
    }

    public static Integer buscaDuploHashing(Integer inteiro, Integer[] tabela) {
        int h = primo(tabela.length);
        int pos = (hash(inteiro, h));
        if (tabela[pos] == inteiro) {
            return tabela[pos];
        } else {
            int cont = 0; //contador para verificar se o dep nao foi encontrado
            int j = 0;
            while (tabela[pos] != inteiro && cont < tabela.length) { //TODO Revisar a condicao de parada (cont)
                j++;
                cont++;
                pos = hash(hash(inteiro, h) + j * hash2(inteiro, j), tabela.length);
            }
            if (cont < tabela.length) {
                return tabela[pos];
            } else {
                return null;
            }
        }
    }

    public static Integer buscaEncadeamentoSeparado(Integer inteiro, ListaEncadeada<Integer>[] tabela) {
        int pos = (hash(inteiro, tabela.length));
        for (int j = 0; j < tabela[pos].getTamanho(); j++) {
            if (tabela[pos].retornaInfo(j) == inteiro) {
                return tabela[pos].retornaInfo(j);
            }
        }
        return null;
    }

    public static Integer buscaEncadeamentoCoalescido(Integer inteiro, Integer[][] tabela) {
        int h = primo(tabela.length);
        int pos = (hash(inteiro, h));
        while (tabela[pos][0] != inteiro) {
            if (tabela[pos][1] != null) {
                pos = tabela[pos][1];
            } else {
                return null;
            }
        }
        return tabela[pos][0];
    }
}

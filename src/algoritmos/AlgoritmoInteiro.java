/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//TODO terminar os comentarios das funcoes
package algoritmos;

import java.util.ArrayList;
import java.util.List;

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
     * @param listaInteiros
     */
    public static void bubbleSort(ListaEncadeada<Integer> listaInteiros) {
        int contIteracao = 0;

        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            contIteracao++;
            for (int j = 1; j < listaInteiros.getTamanho() - i; j++) {
                contIteracao++;
                if (listaInteiros.retornaInfo(j - 1) > listaInteiros.retornaInfo(j)) {
                    listaInteiros.troca(j, j - 1);
                    contIteracao++;
                }
                contIteracao++;
            }
            contIteracao++;
        }
        contIteracao++;
    }

    /**
     * Esta função contém o código para execução do algoritmo de Insertion Sort
     *
     * @param listaInteiros
     */
    public static void insertionSort(ListaEncadeada<Integer> listaInteiros) {
        insertionSort(listaInteiros, 0, listaInteiros.getTamanho());
    }

    private static void insertionSort(ListaEncadeada<Integer> listaInteiros, int min, int max) {
        int contIteracao = 0;

        for (int i = min + 1; i < max; i++) {
            contIteracao++;
            Integer chave = listaInteiros.retornaInfo(i);
            int j = i - 1;

            while (j >= 0 && listaInteiros.retornaInfo(j) > chave) {
                contIteracao++;
                listaInteiros.altera(j + 1, listaInteiros.retornaInfo(j));
                j = j - 1;
            }
            contIteracao++;
            listaInteiros.altera((j + 1), chave);
        }
        contIteracao++;
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Merge Sort
     *
     * @param listaInteiros
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao
    public static void mergeSort(ListaEncadeada<Integer> listaInteiros) {
        mergeSort(listaInteiros, 0, listaInteiros.getTamanho() - 1);
    }

    //Funcao de chamada recursiva do algoritmo Merge Sort
    private static void mergeSort(ListaEncadeada<Integer> listaInteiros, int esq, int dir) {
        if (esq < dir) {
            // Encontra o termo do meio
            int meio = (esq + dir) / 2;
            // Ordena primeira e segunda metade
            mergeSort(listaInteiros, esq, meio);
            mergeSort(listaInteiros, meio + 1, dir);
            // Junta as metades
            merge(listaInteiros, esq, meio, dir);
        }
    }

    //Funcao principal do algoritmo Merge Sort
    private static void merge(ListaEncadeada<Integer> listaInteiros, int esq, int meio, int dir) {
        // Encontra os tamanhos dos dois sub arrays para serem mesclados
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        int contInteracao = 0;

        // Cria os arrays temporarios
        ListaEncadeada<Integer> esqArray = new ListaEncadeada();
        ListaEncadeada<Integer> dirArray = new ListaEncadeada();
        // Copia os dados para o novo array
        for (int i = 0; i < n1; ++i) {
            contInteracao++;
            esqArray.insereFinal(listaInteiros.retornaInfo(esq + i));
        }
        contInteracao++;
        for (int j = 0; j < n2; ++j) {
            contInteracao++;
            dirArray.insereFinal(listaInteiros.retornaInfo(meio + 1 + j));
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
                listaInteiros.altera(k, esqArray.retornaInfo(i));
                contInteracao++;
                i++;
            } else {
                listaInteiros.altera(k, dirArray.retornaInfo(j));
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
            listaInteiros.altera(k, esqArray.retornaInfo(i));
            contInteracao++;
            //listaInteiros[k] = esqVet[i];
            i++;
            k++;
        }
        contInteracao++;
        // Copia os elementos do vetor dir se houver
        while (j < n2) {
            contInteracao++;
            listaInteiros.altera(k, dirArray.retornaInfo(j));
            contInteracao++;
            j++;
            k++;
        }
        contInteracao++;
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Quick Sort
     *
     * @param listaInteiros
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao da recurcao
    public static void quickSortRec(ListaEncadeada<Integer> listaInteiros) {
        quickSortRec(listaInteiros, 0, listaInteiros.getTamanho() - 1);
    }

    //Executa a funcao recursivamente em todos os vetores apos as particoes
    private static void quickSortRec(ListaEncadeada<Integer> listaInteiros, int min, int max) {
        if (min < max) {
            //define o indice da particao (ip)
            int ip = particionaQuickSortRec(listaInteiros, min, max);
            // Recusividade para ordenar os elementos antes e depois da particao
            quickSortRec(listaInteiros, min, ip - 1);
            quickSortRec(listaInteiros, ip + 1, max);
        }
    }

    //Cria as particoes para o algoritmo
    private static int particionaQuickSortRec(ListaEncadeada<Integer> listaInteiros, int min, int max) {
        int contInteracao = 0;
        //Define o pivo como o maior da lista
        Integer pivo = listaInteiros.retornaInfo(max);
        int i = (min - 1); // indice do menor elemento
        for (int j = min; j < max; j++) {
            contInteracao++;
            //Compara as strings
            if (listaInteiros.retornaInfo(j) < pivo) {
                i++;//avanca o menor
                listaInteiros.troca(i, j);
            }
            contInteracao++;
        }
        contInteracao++;
        Integer temp = listaInteiros.retornaInfo(i + 1);
        listaInteiros.troca(i + 1, max);

        return i + 1;
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Quick Sort
     * Mediana de 3
     *
     * @param listaInteiros
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao
    public static void quicksortMedianaK(ListaEncadeada<Integer> listaInteiros, int k) {
        quicksortMedianaK(listaInteiros, 0, listaInteiros.getTamanho() - 1, k);
    }

    //Método de partição do Quick Sort Mediana de 3
    private static int particionaMedianaK(ListaEncadeada<Integer> listaInteiros, int inicio, int fim, int k) {
        //procura a mediana entre inicio, meio e fim
        float div = ((float) (fim - inicio)) / (float) (k - 1);
        float aux = 0;
        int[] indice = new int[k];
        int contInteracao = 0;
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
                if (listaInteiros.retornaInfo(indice[j - 1]) > listaInteiros.retornaInfo(indice[j])) {
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
        //coloca o elemento da mediana no fim para poder usar o Quicksort de Cormen
        trocaDeputados(listaInteiros, medianaIndice, fim);

        //*******************ALGORITMO DE PARTIÇÃO DE CORMEN*********************
        //o pivo é o elemento final
        Integer pivo = listaInteiros.retornaInfo(fim);
        int i = inicio - 1;
        /*
         * Este laço irá varrer os vetores da esquerda para direira
         * procurando os elementos que são menores ou iguais ao pivô.
         * Esses elementos são colocados na partição esquerda.         
         */
        for (int j = inicio; j <= fim - 1; j++) {
            contInteracao++;
            if (listaInteiros.retornaInfo(j) <= pivo) {
                i = i + 1;
                trocaDeputados(listaInteiros, i, j);
            }
            contInteracao++;
        }
        contInteracao++;
        //coloca o pivô na posição de ordenação
        trocaDeputados(listaInteiros, i + 1, fim);
        return i + 1; //retorna a posição do pivô
    }

    //Funcao principal do algoritmo, executa a recursao
    private static void quicksortMedianaK(ListaEncadeada<Integer> listaInteiros, int inicio, int fim, int k) {
        int contInteracao = 0;
        if (inicio < fim) {
            //realiza a partição
            int q = particionaMedianaK(listaInteiros, inicio, fim, k);
            //ordena a partição esquerda
            quicksortMedianaK(listaInteiros, inicio, q - 1, k);
            //ordena a partição direita
            quicksortMedianaK(listaInteiros, q + 1, fim, k);
        }
        contInteracao++;

    }

    /**
     * Esta função contém o código para execução da troca (swap) de objetos no
     * vetor
     *
     * @param listaInteiros
     */
    private static void trocaDeputados(ListaEncadeada<Integer> listaInteiros, int i, int j) {
        Integer aux = listaInteiros.retornaInfo(i);
        listaInteiros.altera(i, listaInteiros.retornaInfo(j));
        listaInteiros.altera(j, aux);
    }

    public static void quickSortHibrido(ListaEncadeada<Integer> listaInteiros, int k) {
        quickSortHibrido(listaInteiros, 0, listaInteiros.getTamanho() - 1, k);
    }

    private static void quickSortHibrido(ListaEncadeada<Integer> listaInteiros, int min, int max, int k) {
        int size = (max + 1) - min;
        int contInteracao = 0;
        if (size <= k) { // inserion sort se o tamanho for menor que 10
            insertionSort(listaInteiros, min, size);
        } else // quicksort se for maior
        {
            int pivo = particionaQuickSortRec(listaInteiros, min, max);
            quickSortRec(listaInteiros, min, pivo - 1);
            quickSortRec(listaInteiros, pivo + 1, max);
        }
        contInteracao++;
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Heap Sort
     *
     * @param listaInteiros
     */
    //Chamada da funcao principal
    public static void heapSort(ListaEncadeada<Integer> listaInteiros) {
        int n = listaInteiros.getTamanho();
        // Constroi a Heap
        int contInteracao = 0;
        for (int i = n / 2 - 1; i >= 0; i--) {
            contInteracao++;
            heapify(listaInteiros, n, i);
        }
        contInteracao++;
        // Extrai um elemento por vez da heap
        for (int i = n - 1; i >= 0; i--) {
            contInteracao++;
            // Move a raiz para o fim
            listaInteiros.troca(0, i);
            // heapify a heap reduzida
            heapify(listaInteiros, i, 0);
        }
        contInteracao++;
    }

    //Funcao principal do algoritmo de Heap Sort
    private static void heapify(ListaEncadeada<Integer> listaInteiros, int n, int i) {
        int maior = i;  // Inicializa o maior como raiz
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
        int contInteracao = 0;

        // Se filho da esq é maior que a raiz
        if (esq < n && ((listaInteiros.retornaInfo(esq) > listaInteiros.retornaInfo(maior)))) {
            maior = esq;
        }
        contInteracao++;
        // Se filho da dir é maior que a raiz
        if (dir < n && ((listaInteiros.retornaInfo(dir) > listaInteiros.retornaInfo(maior)))) {
            maior = dir;
        }
        contInteracao++;
        // Se maior nao e raiz
        if (maior != i) {
            listaInteiros.troca(i, maior);
            // Heapify recursivamente a sub arvore afetada
            heapify(listaInteiros, n, maior);
        }
        contInteracao++;
    }

    /**
     * Esta função contém o código para execução do algoritmo de Shell Sort
     *
     * @param listaInteiros
     */
    //Funcao principal do algoritmo de Shell Sort
    public static void shellSort(ListaEncadeada<Integer> listaInteiros) {
        int h = 1;
        int n = listaInteiros.getTamanho();
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
                c = listaInteiros.retornaInfo(i);
                j = i;
                while (j >= h && (listaInteiros.retornaInfo(j - h) >= c)) {
                    contInteracao++;
                    listaInteiros.altera(j, listaInteiros.retornaInfo(j - h));
                    contInteracao++;
                    j = j - h;
                }
                contInteracao++;
                listaInteiros.altera(j, c);
                contInteracao++;
            }
            contInteracao++;
            h = h / 2;
        }
        contInteracao++;
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

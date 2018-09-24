/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//TODO terminar os comentarios das funcoes
package algoritmos;

import java.util.ArrayList;
import java.util.List;
import trabalhoed2.Deputado;
import trabalhoed2.Generico;
import trabalhoed2.Partido;

/**
 *
 * @author ice
 */
public class Algoritmo {

    /*
    *   Algoritmos de ordenação dos gastos dos Deputados
     */
    /**
     * Esta função contém o código para execução do algoritmo de Bubble Sort
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

    /**
     * Esta função contém o código para execução do algoritmo de Insertion Sort
     *
     * @param deputados
     */
    public static void insertionSort(ListaEncadeada<Deputado> deputados) {
        insertionSort(deputados, 0, deputados.getTamanho());
    }

    private static void insertionSort(ListaEncadeada<Deputado> deputados, int min, int max) {

        for (int i = min + 1; i < max; i++) {
            Deputado chave = deputados.retornaInfo(i);
            int j = i - 1;

            while (j >= 0 && deputados.retornaInfo(j).getTotalGasto() > chave.getTotalGasto()) {
                deputados.altera(j + 1, deputados.retornaInfo(j));
                j = j - 1;
            }
            deputados.altera((j + 1), chave);
        }
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Merge Sort
     *
     * @param deputados
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao
    public static void mergeSort(ListaEncadeada<Deputado> deputados) {
        mergeSort(deputados, 0, deputados.getTamanho() - 1);
    }

    //Funcao de chamada recursiva do algoritmo Merge Sort
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

    //Funcao principal do algoritmo Merge Sort
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

    /**
     * Estas funções contém o código para execução do algoritmo de Quick Sort
     *
     * @param deputados
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao da recurcao
    public static void quickSortRec(ListaEncadeada<Deputado> deputados) {
        quickSortRec(deputados, 0, deputados.getTamanho() - 1);
    }

    //Executa a funcao recursivamente em todos os vetores apos as particoes
    private static void quickSortRec(ListaEncadeada<Deputado> deputados, int min, int max) {
        if (min < max) {
            //define o indice da particao (ip)
            int ip = particionaQuickSortRec(deputados, min, max);
            // Recusividade para ordenar os elementos antes e depois da particao
            quickSortRec(deputados, min, ip - 1);
            quickSortRec(deputados, ip + 1, max);
        }
    }

    //Cria as particoes para o algoritmo
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

    /**
     * Estas funções contém o código para execução do algoritmo de Quick Sort
     * Mediana de 3
     *
     * @param deputados
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao
    public static void quicksortMedianaDeTres(ListaEncadeada<Deputado> deputados) {
        quicksortMedianaDeTres(deputados, 0, deputados.getTamanho() - 1);
    }

    //Método de partição do Quick Sort Mediana de 3
    private static int particionaMediana(ListaEncadeada<Deputado> deputados, int inicio, int fim) {
        //procura a mediana entre inicio, meio e fim
        int meio = (inicio + fim) / 2;
        float a = deputados.retornaInfo(inicio).getTotalGasto();
        float b = deputados.retornaInfo(meio).getTotalGasto();
        float c = deputados.retornaInfo(fim).getTotalGasto();
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
        Deputado pivo = deputados.retornaInfo(fim);
        int i = inicio - 1;
        /*
         * Este laço irá varrer os vetores da esquerda para direira
         * procurando os elementos que são menores ou iguais ao pivô.
         * Esses elementos são colocados na partição esquerda.         
         */
        for (int j = inicio; j <= fim - 1; j++) {
            if (deputados.retornaInfo(j).getTotalGasto() <= pivo.getTotalGasto()) {
                i = i + 1;
                trocaDeputados(deputados, i, j);
            }
        }
        //coloca o pivô na posição de ordenação
        trocaDeputados(deputados, i + 1, fim);
        return i + 1; //retorna a posição do pivô
    }

    //Funcao principal do algoritmo, executa a recursao
    private static void quicksortMedianaDeTres(ListaEncadeada<Deputado> deputados, int inicio, int fim) {
        if (inicio < fim) {
            //realiza a partição
            int q = particionaMediana(deputados, inicio, fim);
            //ordena a partição esquerda
            quicksortMedianaDeTres(deputados, inicio, q - 1);
            //ordena a partição direita
            quicksortMedianaDeTres(deputados, q + 1, fim);
        }
    }

    /**
     * Esta função contém o código para execução da troca (swap) de objetos no
     * vetor
     *
     * @param deputados
     */
    private static void trocaDeputados(ListaEncadeada<Deputado> deputados, int i, int j) {
        Deputado aux = deputados.retornaInfo(i);
        deputados.altera(i, deputados.retornaInfo(j));
        deputados.altera(j, aux);
    }

    public static void quickSortHibrido(ListaEncadeada<Deputado> deputados) {
        quickSortHibrido(deputados, 0, deputados.getTamanho() - 1);
    }

    private static void quickSortHibrido(ListaEncadeada<Deputado> deputados, int min, int max) {
        int size = (max + 1) - min;
        if (size <= 10) { // inserion sort se o tamanho for menor que 10
            insertionSort(deputados, min, size);
        } else // quicksort se for maior
        {
            int pivo = particionaQuickSortRec(deputados, min, max);
            quickSortRec(deputados, min, pivo - 1);
            quickSortRec(deputados, pivo + 1, max);
        }
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Heap Sort
     *
     * @param deputados
     */
    //Chamada da funcao principal
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

    //Funcao principal do algoritmo de Heap Sort
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

    /**
     * Esta função contém o código para execução do algoritmo de Shell Sort
     *
     * @param deputados
     */
    //Funcao principal do algoritmo de Shell Sort
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

    /*
    *   Algoritmo para ordenar lista inteira
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
            //deputados[k] = esqVet[i];
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
    private static Deputado[] tabela(int tam) {
        Deputado[] tab = new Deputado[tam];
        for (int i = 0; i < tam; i++) {
            tab[i] = null;
        }
        return tab;
    }

    private static ListaEncadeada<Deputado>[] tabelaEncadeada(int tam) {
        ListaEncadeada[] tab = new ListaEncadeada[tam];//falta tipo deputado na lista?
        for (int i = 0; i < tam; i++) {
            tab[i] = new ListaEncadeada();
        }
        return tab;
    }

    private static Deputado[][] tabelaCoalescida(int tam) {
        Deputado[][] tab = new Deputado[tam][1];
        for (int i = 0; i < tam; i++) {
            tab[i][0] = null;
            tab[i][1] = null;
        }
        return tab;
    }

    private static int hash(int k, int m) {
        return k % m;
    }

    private static int hash2(int k) {
        return k * 2;
    }

    private static int hashDuplo(int k, int m, int colisao) {
        return (hash(k, m) + colisao * hash2(k));
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

    public static Deputado[] sondagemLinear(ListaEncadeada<Deputado> deputados) {
        int pos;
        int h = primo(deputados.getTamanho());
        Deputado[] tabela = tabela(deputados.getTamanho());
        //for (int i = 0; i < tam; i++) {
        for (int i = 0; i < deputados.getTamanho(); i++) {
            pos = (hash(deputados.retornaInfo(i).getId(), h));
            while (tabela[pos] != null) {
                pos = hash(pos + 1, deputados.getTamanho());
            }
            tabela[pos] = deputados.retornaInfo(i);
        }
        return tabela;
    }

    public static Deputado[] sondagemQuadratica(ListaEncadeada<Deputado> deputados) {
        int pos;
        int h = primo(deputados.getTamanho());
        Deputado[] tabela = tabela(deputados.getTamanho());
        for (int i = 0; i < deputados.getTamanho(); i++) {
            pos = (hash(deputados.retornaInfo(i).getId(), h));
            int j = 0;
            while (tabela[pos] != null) {
                j++;
                pos = hash(pos + j * j, deputados.getTamanho());
            }
            tabela[pos] = deputados.retornaInfo(i);
        }
        return tabela;
    }

    public static Deputado[] duploHashing(ListaEncadeada<Deputado> deputados) {
        int pos;
        int h = primo(deputados.getTamanho());
        Deputado[] tabela = tabela(deputados.getTamanho());
        for (int i = 0; i < deputados.getTamanho(); i++) {
            pos = (hash(deputados.retornaInfo(i).getId(), h));
            int j = 0;
            while (tabela[pos] != null) {
                j++;
                pos = hash(hashDuplo(deputados.retornaInfo(i).getId(), h, j), deputados.getTamanho());
                //pos = hash(deputados.retornaInfo(i).getId(), h);
            }
             tabela[pos] = deputados.retornaInfo(i);
        }
        return tabela;
    }

    public static ListaEncadeada[] encadeamentoSeparado(ListaEncadeada<Deputado> deputados) {
        int pos;
        ListaEncadeada<Deputado>[] tabela = tabelaEncadeada(50);
        for (int i = 0; i < deputados.getTamanho(); i++) {
            System.out.println(i);
            pos = (hash(deputados.retornaInfo(i).getId(), 50));
            tabela[pos].insereFinal(deputados.retornaInfo(i));
        }
        return tabela;
    }

    public static Deputado[][] encadeamentoCoalescido(ListaEncadeada<Deputado> deputados) {
        int pos;
        int h = primo(deputados.getTamanho());
        Deputado[][] tabela = tabelaCoalescida(deputados.getTamanho());
        for (int i = 0; i < deputados.getTamanho(); i++) {
            pos = (hash(deputados.retornaInfo(i).getId(), h));
            if (tabela[pos][0] != null) {
                pos = deputados.getTamanho() - 1;
                while (tabela[pos][0] != null) {
                    pos = pos - 1;
                }
                tabela[pos][0] = deputados.retornaInfo(i);
                //Cria um dep aux que usa o id como posicao para o proximo
                //TODO Expandir o comentario acima
                Deputado aux = new Deputado();
                aux.setId(pos);
                tabela[hash(deputados.retornaInfo(i).getId(), h)][1] = aux;
            } else {
                tabela[pos][0] = deputados.retornaInfo(i);
            }
        }
        System.out.println("FUNCIONA PORRA!!!");
        return tabela;
    }

    /**
    *Funcoes de busca de hashing
    */
    
    public static Deputado buscaSondagemLinear(Deputado deputado, Deputado[] tabela) {
        int pos;
        int h = primo(tabela.length);

        pos = (hash(deputado.getId(), h));
        if (tabela[pos].getId() == deputado.getId()) {
            return tabela[pos];
        } else if (tabela[pos].getId() != deputado.getId()) {
            int cont = 0; //contador para verificar se o dep nao foi encontrado
            while (tabela[pos].getId() != deputado.getId() && cont < tabela.length) {
                pos = hash(pos + 1, tabela.length);
                cont++;
            }
            if (cont < tabela.length) {
                return tabela[pos];
            } else {
                return null;
            }
        }
        return null; //retorno para "comprir tabela"
    }

    public static Deputado buscaSondagemQuadratica(Deputado deputado, Deputado[] tabela) {
        int pos;
        int h = primo(tabela.length);

        pos = (hash(deputado.getId(), h));
        if (tabela[pos].getId() == deputado.getId()) {
            return tabela[pos];
        } else if (tabela[pos].getId() != deputado.getId()) {
            int cont = 0; //contador para verificar se o dep nao foi encontrado
            int j = 0;
            while (tabela[pos].getId() != deputado.getId() && cont < tabela.length) { //TODO Revisar a condicao de parada (cont)
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
        return null; //retorno para "comprir tabela"
    }

    public static Deputado buscaDuploHashing(Deputado deputado, Deputado[] tabela) {
        int pos;
        int h = primo(tabela.length);

        pos = (hash(deputado.getId(), h));
        if (tabela[pos].getId() == deputado.getId()) {
            return tabela[pos];
        } else if (tabela[pos].getId() != deputado.getId()) {
            int cont = 0; //contador para verificar se o dep nao foi encontrado
            int j = 0;
            while (tabela[pos].getId() != deputado.getId() && cont < tabela.length) { //TODO Revisar a condicao de parada (cont)
                j++;
                cont++;
                pos = hash(hashDuplo(deputado.getId(), h, j), tabela.length);
            }
            if (cont < tabela.length) {
                return tabela[pos];
            } else {
                return null;
            }
        }
        return null; //retorno para "comprir tabela"
    }

    public static Deputado buscaEncadeamentoSeparado(Deputado deputado, ListaEncadeada[] tabela) {
        int pos;
        pos = (hash(deputado.getId(), tabela.length));
        No<Deputado> aux = tabela[pos].getInicio();
        while (aux.getProximo() != null) {
            if (aux.getObjeto().getId() == deputado.getId()) {
                return aux.getObjeto();
            }
        }
        return null;
    }

    public static Deputado buscaEncadeamentoCoalescido(Deputado deputado, Deputado[][] tabela) {
        int pos;
        int h = primo(tabela[0].length);
        pos = (hash(deputado.getId(), h));
        while (tabela[pos][0].getId() != deputado.getId() && tabela[pos][1] != null) {
            pos = tabela[pos][1].getId();
        }
        if (tabela[pos][1] == null) {
            return null;
        } else {
            return tabela[pos][0];
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//TODO terminar os comentarios das funcoes
package algoritmos;

import dados.Relatorio;

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
     * @param relatorio
     */
    public static void bubbleSort(ListaEncadeada<Integer> listaInteiros, Relatorio relatorio) {
        int contIteracao = 0;

        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            contIteracao++;
            for (int j = 1; j < listaInteiros.getTamanho() - i; j++) {
                contIteracao++;
                if (listaInteiros.retornaInfo(j - 1) > listaInteiros.retornaInfo(j)) {
                    listaInteiros.troca(j, j - 1, relatorio);

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
     * @param listaInteiros
     * @param relatorio
     */
    public static void insertionSort(ListaEncadeada<Integer> listaInteiros, Relatorio relatorio) {
        insertionSort(listaInteiros, 0, listaInteiros.getTamanho(), relatorio);
    }

    private static void insertionSort(ListaEncadeada<Integer> listaInteiros, int min, int max, Relatorio relatorio) {
        int contIteracao = 1;

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
            relatorio.incrementaTrocaColisao();
        }
        contIteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contIteracao));
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Merge Sort
     *
     * @param listaInteiros
     * @param relatorio
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao
    public static void mergeSort(ListaEncadeada<Integer> listaInteiros, Relatorio relatorio) {
        mergeSort(listaInteiros, 0, listaInteiros.getTamanho() - 1, relatorio);
    }

    //Funcao de chamada recursiva do algoritmo Merge Sort
    private static void mergeSort(ListaEncadeada<Integer> listaInteiros, int esq, int dir, Relatorio relatorio) {
        int contInteracao = 1;
        if (esq < dir) {
            // Encontra o termo do meio
            int meio = (esq + dir) / 2;
            // Ordena primeira e segunda metade
            mergeSort(listaInteiros, esq, meio, relatorio);
            mergeSort(listaInteiros, meio + 1, dir, relatorio);
            // Junta as metades
            merge(listaInteiros, esq, meio, dir, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    //Funcao principal do algoritmo Merge Sort
    private static void merge(ListaEncadeada<Integer> listaInteiros, int esq, int meio, int dir, Relatorio relatorio) {
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
            relatorio.incrementaTrocaColisao();
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
        relatorio.incrementaTrocaColisao();
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Quick Sort
     *
     * @param listaInteiros
     * @param relatorio
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao da recurcao
    public static void quickSortRec(ListaEncadeada<Integer> listaInteiros, Relatorio relatorio) {
        quickSortRec(listaInteiros, 0, listaInteiros.getTamanho() - 1, relatorio);
    }

    //Executa a funcao recursivamente em todos os vetores apos as particoes
    private static void quickSortRec(ListaEncadeada<Integer> listaInteiros, int min, int max, Relatorio relatorio) {
        int contInteracao = 1;
        if (min < max) {
            contInteracao++;
            //define o indice da particao (ip)
            int ip = particionaQuickSortRec(listaInteiros, min, max, relatorio);
            // Recusividade para ordenar os elementos antes e depois da particao
            quickSortRec(listaInteiros, min, ip - 1, relatorio);
            quickSortRec(listaInteiros, ip + 1, max, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    //Cria as particoes para o algoritmo
    private static int particionaQuickSortRec(ListaEncadeada<Integer> listaInteiros, int min, int max, Relatorio relatorio) {
        int contInteracao = 1;
        //Define o pivo como o maior da lista
        Integer pivo = listaInteiros.retornaInfo(max);
        int i = (min - 1); // indice do menor elemento
        for (int j = min; j < max; j++) {
            contInteracao++;
            //Compara as strings
            if (listaInteiros.retornaInfo(j) < pivo) {
                i++;//avanca o menor
                listaInteiros.troca(i, j, relatorio);
            }
            contInteracao++;
        }
        contInteracao++;
        Integer temp = listaInteiros.retornaInfo(i + 1);
        listaInteiros.troca(i + 1, max, relatorio);
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
        return i + 1;
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Quick Sort
     * Mediana de 3
     *
     * @param listaInteiros
     * @param relatorio
     */
    //Chamada da funcao, calcula os parametros necessarios para execucao
    public static void quicksortMedianaK(ListaEncadeada<Integer> listaInteiros, int k, Relatorio relatorio) {
        quicksortMedianaK(listaInteiros, 0, listaInteiros.getTamanho() - 1, k, relatorio);
    }

    //Método de partição do Quick Sort Mediana de 3
    private static int particionaMedianaK(ListaEncadeada<Integer> listaInteiros, int inicio, int fim, int k, Relatorio relatorio) {
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
        listaInteiros.troca(medianaIndice, fim, relatorio);

        //o pivo é o elemento final
        Integer pivo = listaInteiros.retornaInfo(fim);
        int i = inicio - 1;

        for (int j = inicio; j <= fim - 1; j++) {
            contInteracao++;
            if (listaInteiros.retornaInfo(j) <= pivo) {
                i = i + 1;
                listaInteiros.troca(i, j, relatorio);
                contInteracao++;

            }
            contInteracao++;
        }
        contInteracao++;
        //coloca o pivô na posição de ordenação
        listaInteiros.troca(i + 1, fim, relatorio);
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
        return i + 1; //retorna a posição do pivô
    }

    //Funcao principal do algoritmo, executa a recursao
    private static void quicksortMedianaK(ListaEncadeada<Integer> listaInteiros, int inicio, int fim, int k, Relatorio relatorio) {
        int contInteracao = 1;
        if (inicio < fim) {
            //realiza a partição
            int q = particionaMedianaK(listaInteiros, inicio, fim, k, relatorio);
            //ordena a partição esquerda
            quicksortMedianaK(listaInteiros, inicio, q - 1, k, relatorio);
            //ordena a partição direita
            quicksortMedianaK(listaInteiros, q + 1, fim, k, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));

    }

    public static void quickSortHibrido(ListaEncadeada<Integer> listaInteiros, int k, Relatorio relatorio) {
        quickSortHibrido(listaInteiros, 0, listaInteiros.getTamanho() - 1, k, relatorio);
    }

    private static void quickSortHibrido(ListaEncadeada<Integer> listaInteiros, int min, int max, int k, Relatorio relatorio) {
        int size = (max + 1) - min;
        int contInteracao = 1;
        if (size <= k) { // inserion sort se o tamanho for menor que 10
            insertionSort(listaInteiros, min, size, relatorio);
        } else // quicksort se for maior
        {
            int pivo = particionaQuickSortRec(listaInteiros, min, max, relatorio);
            quickSortRec(listaInteiros, min, pivo - 1, relatorio);
            quickSortRec(listaInteiros, pivo + 1, max, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    /**
     * Estas funções contém o código para execução do algoritmo de Heap Sort
     *
     * @param listaInteiros
     * @param relatorio
     */
    //Chamada da funcao principal
    public static void heapSort(ListaEncadeada<Integer> listaInteiros, Relatorio relatorio) {
        int n = listaInteiros.getTamanho();
        // Constroi a Heap
        int contInteracao = 0;
        for (int i = n / 2 - 1; i >= 0; i--) {
            contInteracao++;
            heapify(listaInteiros, n, i, relatorio);
        }
        contInteracao++;
        // Extrai um elemento por vez da heap
        for (int i = n - 1; i >= 0; i--) {
            contInteracao++;
            // Move a raiz para o fim
            listaInteiros.troca(0, i, relatorio);
            // heapify a heap reduzida
            heapify(listaInteiros, i, 0, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    //Funcao principal do algoritmo de Heap Sort
    private static void heapify(ListaEncadeada<Integer> listaInteiros, int n, int i, Relatorio relatorio) {
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
            listaInteiros.troca(i, maior, relatorio);
            // Heapify recursivamente a sub arvore afetada
            heapify(listaInteiros, n, maior, relatorio);
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
    }

    /**
     * Esta função contém o código para execução do algoritmo de Shell Sort
     *
     * @param listaInteiros
     * @param relatorio
     */
    //Funcao principal do algoritmo de Shell Sort
    public static void shellSort(ListaEncadeada<Integer> listaInteiros, Relatorio relatorio) {
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
                relatorio.incrementaTrocaColisao();
            }
            contInteracao++;
            h = h / 2;
        }
        contInteracao++;
        relatorio.setInteracao((relatorio.getInteracao() + contInteracao));
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
        int aux =k*j+1;
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
     *
     */
    public static Integer[] sondagemLinear(ListaEncadeada<Integer> listaInteiros, Relatorio relatorio) {
        int pos;
        int h = primo(listaInteiros.getTamanho());
        Integer[] tabela = tabela(listaInteiros.getTamanho());
        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            relatorio.incrementaInteracao();
            pos = (hash(listaInteiros.retornaInfo(i), h));
            //Colisões
            while (tabela[pos] != null) {
                relatorio.incrementaInteracao();
                pos = hash(pos + 1, listaInteiros.getTamanho());
                relatorio.incrementaTrocaColisao();
            }
            relatorio.incrementaInteracao();
            tabela[pos] = listaInteiros.retornaInfo(i);
        }
        relatorio.incrementaInteracao();
        return tabela;
    }

    public static Integer[] sondagemQuadratica(ListaEncadeada<Integer> listaInteiros, Relatorio relatorio) {
        int pos;
        int h = primo(listaInteiros.getTamanho());
        Integer[] tabela = tabela(listaInteiros.getTamanho());
        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            relatorio.incrementaInteracao();
            pos = (hash(listaInteiros.retornaInfo(i), h));
            int j = 0;
            if (pos < 0) {
                pos += tabela.length;
            }
            while (tabela[pos] != null) {
                relatorio.incrementaInteracao();
                j++;
                pos = hash(pos + (j * j), listaInteiros.getTamanho());
                relatorio.incrementaTrocaColisao();
                if (pos < 0) {
                    pos += tabela.length;
                }
            }

            if (pos < 0) {
                pos += tabela.length;
            }
            relatorio.incrementaInteracao();
            tabela[pos] = listaInteiros.retornaInfo(i);
        }

        relatorio.incrementaInteracao();
        return tabela;
    }

    public static Integer[] duploHashing(ListaEncadeada<Integer> listaInteiros, Relatorio relatorio) {
       int pos = 0;
        int h = primo(listaInteiros.getTamanho());
        Integer [] tabela = tabela(listaInteiros.getTamanho());
        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            relatorio.incrementaInteracao();
            pos = (hash(listaInteiros.retornaInfo(i), h));
            if(pos<0){
                pos=pos+tabela.length;
            }
            int j = 0;
            while (tabela[pos] != null && pos < 0) {
                relatorio.incrementaInteracao();
                j++;
                pos = hash(pos + hash2(pos, j), listaInteiros.getTamanho());
                //pos=hash(pos+1,tabela.length);
                relatorio.incrementaTrocaColisao();
                            if(pos<0){
                pos=pos+tabela.length;
            }
            }
                        if(pos<0){
                pos=pos+tabela.length;
            }
            relatorio.incrementaInteracao();
            tabela[pos] = listaInteiros.retornaInfo(i);
        }
        relatorio.incrementaInteracao();
        return null;
    }

    public static ListaEncadeada[] encadeamentoSeparado(ListaEncadeada<Integer> listaInteiros, Relatorio relatorio) {
        int pos;
        ListaEncadeada<Integer>[] tabela = tabelaEncadeada(50);
        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            relatorio.incrementaInteracao();
            pos = (hash(listaInteiros.retornaInfo(i), 50));
            tabela[pos].insereFinal(listaInteiros.retornaInfo(i));
            if (tabela[pos].retornaFim() != null) {
                relatorio.incrementaTrocaColisao();
            }
            relatorio.incrementaInteracao();
        }
        relatorio.incrementaInteracao();
        return tabela;
    }

    public static Integer[][] encadeamentoCoalescido(ListaEncadeada<Integer> listaInteiros, Relatorio relatorio) {
        int pos;
        int h = primo(listaInteiros.getTamanho());
        Integer[][] tabela = tabelaCoalescida(listaInteiros.getTamanho());
        for (int i = 0; i < listaInteiros.getTamanho(); i++) {
            relatorio.incrementaInteracao();
            pos = (hash(listaInteiros.retornaInfo(i), h));
            if (tabela[pos][0] != null) {
                pos = listaInteiros.getTamanho() - 1;
                while (tabela[pos][0] != null) {
                    relatorio.incrementaInteracao();
                    pos = pos - 1;
                    relatorio.incrementaTrocaColisao();
                }
                relatorio.incrementaInteracao();
                tabela[pos][0] = listaInteiros.retornaInfo(i);
                //Cria um dep aux que usa o id como posicao para o proximo
                //TODO Expandir o comentario acima
                int aux = pos;
                tabela[hash(listaInteiros.retornaInfo(i), h)][1] = aux;
            } else {
                tabela[pos][0] = listaInteiros.retornaInfo(i);
            }
            relatorio.incrementaInteracao();
        }
        relatorio.incrementaInteracao();
        return tabela;
    }

    /**
     * Funcoes de busca de hashing
     */
    private static Integer buscaSondagemLinear(Integer valor, Integer[] tabela, Relatorio relatorio) {
        int h = primo(tabela.length);
        int pos = (hash(valor, h));
        if (tabela[pos] == valor) {
            relatorio.incrementaInteracao();
            return tabela[pos];//Encontrou o Integer requerido na primeira iteração(não há colisões)
        } else {
            relatorio.incrementaInteracao();
            int cont = 0; //Contador para verificar se o dep nao foi encontrado
            while (tabela[pos] != valor && cont < tabela.length) {
                relatorio.incrementaInteracao();
                pos = hash(pos + 1, tabela.length);
                cont++;
            }
            relatorio.incrementaInteracao();
            if (cont < tabela.length) {
                relatorio.incrementaInteracao();
                return tabela[pos];//Encontrou o Integer Requerido(há colisões)

            } else {
                relatorio.incrementaInteracao();
                return null;//Não Encontrou o Integer Requerido
            }
        }
    }

    private static Integer buscaSondagemQuadratica(Integer valor, Integer[] tabela, Relatorio relatorio) {
        int h = primo(tabela.length);
        int pos = (hash(valor, h));
        if (tabela[pos] == valor) {
            relatorio.incrementaInteracao();
            return tabela[pos];
        } else {
            relatorio.incrementaInteracao();
            int cont = 0; //contador para verificar se o dep nao foi encontrado
            int j = 0;
            while (tabela[pos] != valor && cont < tabela.length) { //TODO Revisar a condicao de parada (cont)
                relatorio.incrementaInteracao();
                j++;
                pos = hash(pos + j * j, tabela.length);
                cont++;
            }
            relatorio.incrementaInteracao();
            if (cont < tabela.length) {
                relatorio.incrementaInteracao();
                return tabela[pos];
            } else {
                relatorio.incrementaInteracao();
                return null;
            }
        }
    }

    private static Integer buscaDuploHashing(Integer valor, Integer[] tabela, Relatorio relatorio) {
        int h = primo(tabela.length);
        int pos = (hash(valor, h));
        if (tabela[pos] == valor) {
            relatorio.incrementaInteracao();
            return tabela[pos];
        } else {
            relatorio.incrementaInteracao();
            int cont = 0; //contador para verificar se o dep nao foi encontrado
            int j = 0;
            while (tabela[pos] != valor && cont < tabela.length) { //TODO Revisar a condicao de parada (cont)

                relatorio.incrementaInteracao();
                j++;
                cont++;
                pos = hash(hash(valor, h) + j * hash2(valor, j), tabela.length);
            }
            relatorio.incrementaInteracao();
            if (cont < tabela.length) {
                relatorio.incrementaInteracao();
                return tabela[pos];
            } else {
                relatorio.incrementaInteracao();
                return null;
            }
        }
    }

    private static Integer buscaEncadeamentoSeparado(Integer valor, ListaEncadeada<Integer>[] tabela, Relatorio relatorio) {
        int pos = (hash(valor, tabela.length));
        for (int j = 0; j < tabela[pos].getTamanho(); j++) {
            relatorio.incrementaInteracao();
            if (tabela[pos].retornaInfo(j) == valor) {
                relatorio.incrementaInteracao();
                return tabela[pos].retornaInfo(j);
            }
        }
        relatorio.incrementaInteracao();
        return null;
    }

    private static Integer buscaEncadeamentoCoalescido(Integer valor, Integer[][] tabela, Relatorio relatorio) {
        int h = primo(tabela.length);
        int pos = (hash(valor, h));
        while (tabela[pos][0] != valor) {
            relatorio.incrementaInteracao();
            if (tabela[pos][1] != null) {
                relatorio.incrementaInteracao();
                pos = tabela[pos][1];
            } else {
                relatorio.incrementaInteracao();
                return null;
            }
        }
        relatorio.incrementaInteracao();
        return tabela[pos][0];
    }
}

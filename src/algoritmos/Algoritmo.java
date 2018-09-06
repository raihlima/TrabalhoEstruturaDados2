/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import trabalhoed2.Deputado;

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

    public static int particionaQuickSortRec(ArrayList<Deputado> deputados, int min, int max) {
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

    public void quickSortRec(ArrayList<Deputado> deputados, int min, int max) {
        if (min < max) {
            //define o indice da particao (ip)
            int ip = particionaQuickSortRec(deputados, min, max);
            // Recusividade para ordenar os elementos antes e depois da particao
            quickSortRec(deputados, min, ip - 1);
            quickSortRec(deputados, ip + 1, max);
        }
    }

    public static int particionaQuickSortMed(ArrayList<Deputado> deputados, int min, int max, int k) {
        //Cria o leitor de letras ascii
        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        //Define o pivo como o maior da lista
        //Deputado pivo = deputados.get(max);
        //Cria um num randomico para ler no indice do vetor original de deputados
        Random rand = new Random();

        ArrayList<Deputado> deputadoMed = new ArrayList();
        //Popula o vetor de novoa deputados para calcular a mediana
        for (int i = 0; i < k; i++) {
            deputadoMed.add(deputados.get(rand.nextInt(deputados.size())));
        }
        ///TO DO Implementar o sort aqui
        Algoritmo.bubbleSortDeputados(deputadoMed);
        //Define o pivo do algoritmo
        Deputado pivo = deputadoMed.get(k / 2);

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

    public void quickSortMed(ArrayList<Deputado> deputados, int min, int max, int k) {
        if (min < max) {
            //define o indice da particao (ip)
            int ip = particionaQuickSortMed(deputados, min, max, k);
            // Recusividade para ordenar os elementos antes e depois da particao
            quickSortMed(deputados, min, ip - 1, k);
            quickSortMed(deputados, ip + 1, max, k);
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

    public void insertionSort(ArrayList<Deputado> deputados) {
        int n = deputados.size();
        //Cria o leitor de letras ascii
        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        for (int i = 1; i < n; ++i) {
            Deputado chave = deputados.get(i);
            int j = i - 1;

            while (collator.compare(deputados.get(j).getNome(), chave.getNome()) > 0) {
                deputados.set(j + 1, deputados.get(j));
                j = j - 1;
            }
            deputados.set((j + 1), chave);
        }
    }

    public void insertionSortRecursivo(ArrayList<Deputado> deputados, int n) {
        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        //Caso base
        if (n <= 1) {
            return;
        }
        // Sort first n-1 elements
        insertionSortRecursivo(deputados, n - 1);

        // Insert ultimo element at its correct position
        // in sorted array.
        Deputado ultimo = deputados.get(n - 1);
        int j = n - 2;

        /* Move elements of deputados[0..i-1], that are
          greater than key, to one position ahead
          of their current position */
        //while (j >= 0 && deputados.get(j) > ultimo)
        while (j > 0 && collator.compare(deputados.get(j).getNome(), ultimo.getNome()) >= 0) {
            deputados.set(j + 1, deputados.get(j));
            j--;
        }
        deputados.set(j + 1, ultimo);
    }

    private void merge(ArrayList<Deputado> deputados, int esq, int meio, int dir) {
        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        // Encontra os tamanhos dos dois sub arrays para serem mesclados
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        // Cria os arrays temporarios
        ArrayList<Deputado> esqArray = new ArrayList();
        ArrayList<Deputado> dirArray = new ArrayList();
        // Copia os dados para o novo array
        for (int i = 0; i < n1; ++i) {
            esqArray.add(deputados.get(esq + i));
        }
        for (int j = 0; j < n2; ++j) {
            dirArray.add(deputados.get(meio + 1 + j));
        }
        // Junta os arrays temporarios
        // Indice inicial dos sub arrays
        int i = 0, j = 0;
        // Indice inicial dos sub arrays
        int k = esq;
        while (i < n1 && j < n2) {
            if (collator.compare(esqArray.get(i).getNome(), dirArray.get(j).getNome()) <= 0) {
                deputados.set(k, esqArray.get(i));
                i++;
            } else {
                deputados.set(k, dirArray.get(j));
                j++;
            }
            k++;
        }
        // Copia os elementos do vetor esq se houver
        while (i < n1) {
            deputados.set(k, esqArray.get(i));
            //deputados[k] = esqVet[i];
            i++;
            k++;
        }
        // Copia os elementos do vetor dir se houver
        while (j < n2) {
            deputados.set(k, dirArray.get(j));
            j++;
            k++;
        }
    }

    public void mergeSort(ArrayList<Deputado> deputados, int esq, int dir) {
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

    public static void shellSort(ArrayList<Deputado> deputados) {
        int h = 1;
        int n = deputados.size();
        Collator collator = Collator.getInstance(new Locale("pt", "BR"));
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
                c = deputados.get(i);
                j = i;
                while (j >= h && collator.compare(deputados.get(j - h).getNome(), c.getNome()) >= 0) {
                    deputados.set(j, deputados.get(j - h));
                    j = j - h;
                }
                deputados.set(j, c);
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

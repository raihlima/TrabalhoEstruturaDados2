package algoritmos;

import trabalhoed2.Partido;

/**
 * Implementacao dos algoritmos de ordenacao usados nos Partidos 
 */
public class AlgoritmoPartido {
        //Chamada da funcao, calcula os parametros necessarios para execucao
    
    public static void mergeSort(ListaEncadeada<Partido> partidos) {
        mergeSort(partidos, 0, partidos.getTamanho() - 1);
    }

    //Funcao de chamada recursiva do algoritmo Merge Sort
    private static void mergeSort(ListaEncadeada<Partido> partidos, int esq, int dir) {
        if (esq < dir) {
            // Encontra o termo do meio
            int meio = (esq + dir) / 2;
            // Ordena primeira e segunda metade
            mergeSort(partidos, esq, meio);
            mergeSort(partidos, meio + 1, dir);
            // Junta as metades
            merge(partidos, esq, meio, dir);
        }
        

    }

    //Funcao principal do algoritmo Merge Sort
    private static void merge(ListaEncadeada<Partido> partidos, int esq, int meio, int dir) {
        // Encontra os tamanhos dos dois sub arrays para serem mesclados
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        int contInteracao = 1;

        // Cria os arrays temporarios
        ListaEncadeada<Partido> esqArray = new ListaEncadeada();
        ListaEncadeada<Partido> dirArray = new ListaEncadeada();
        // Copia os dados para o novo array
        for (int i = 0; i < n1; ++i) {
            
            esqArray.insereFinal(partidos.retornaInfo(esq + i));
        }
        
        for (int j = 0; j < n2; ++j) {
            
            dirArray.insereFinal(partidos.retornaInfo(meio + 1 + j));
        }
        
        // Junta os arrays temporarios
        // Indice inicial dos sub arrays
        int i = 0, j = 0;
        // Indice inicial dos sub arrays
        int k = esq;
        while (i < n1 && j < n2) {
            
            if (esqArray.retornaInfo(i).getTotalGasto() <= dirArray.retornaInfo(j).getTotalGasto()) {
                partidos.altera(k, esqArray.retornaInfo(i));
                
                i++;
            } else {
                partidos.altera(k, dirArray.retornaInfo(j));
                
                j++;
            }     
            k++;
        }
        
        // Copia os elementos do vetor esq se houver
        while (i < n1) {
            
            partidos.altera(k, esqArray.retornaInfo(i));
            
            //partidos[k] = esqVet[i];
            i++;
            k++;
        }
        
        // Copia os elementos do vetor dir se houver
        while (j < n2) {
            
            partidos.altera(k, dirArray.retornaInfo(j));
            
            j++;
            k++;
        }
    }

}

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
         Collator collator = Collator.getInstance (new Locale ("pt", "BR"));
        //auxiliar.compare(string, string1)
        
        
        for (int i = 0; i < deputados.size(); i++) {
            for (int j = 1; j < deputados.size() - i; j++) {
                if(collator.compare(deputados.get(j - 1).getNome(),deputados.get(j).getNome()) > 0){
                //if (deputados.get(j - 1).getNome().compareToIgnoreCase(deputados.get(j).getNome()) > 0) {
                    Collections.swap(deputados,j,j-1);
                }
            }
        }

    }
    
    public static void bubbleSortRecibos(Deputado deputado){
        for (int i = 0; i<deputado.getRecibos().size(); i++){
            for(int j=1; j<deputado.getRecibos().size() -i; j++){
                if(deputado.getRecibos().get(j).getGasto() < deputado.getRecibos().get(j-1).getGasto()){
                    Collections.swap(deputado.getRecibos(), j-1, j);
                }
            }
        }
    }
}

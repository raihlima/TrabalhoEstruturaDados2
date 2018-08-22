/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author ice
 */
public class TrabalhoED2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Deputado> deputados;
        deputados = new ArrayList<>();
        leDados(deputados);

        imprimeDeputados(deputados);
        System.out.println("Número de deputados: " + deputados.size());

    }

    public static void leDados(ArrayList<Deputado> deputados) {
        //File arquivo = new File("texto.txt");
        File arquivo = new File("deputies_dataset.csv");
        double cont = 0;
        
        try (FileInputStream fi = new FileInputStream(arquivo)) {
            BufferedInputStream bis = new BufferedInputStream(fi);
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
            String linha = linha = reader.readLine();

            //String
            String[] partes;
            String aux;
            
            /*
            for (int i = 0; i < 100000; i++) {

                linha = reader.readLine();
                aux = linha;
                //System.out.println(linha);
                
                partes = aux.split(",");
                //System.out.println(partes[]);
                preencheDeputados(deputados, partes);

                //deputados.add(new Deputado(partes[5], partes[3], partes[4], 12));

            }
            */
            while(linha !=null){
                cont++;
                //System.out.println(linha);
                linha = reader.readLine();
                partes = linha.split(",");
                preencheDeputados(deputados, partes);
                //System.out.println(cont);
            }//*/
            
            //System.out.println("Terminou");
            
        } catch (Exception e) {
            System.out.println(cont);
            System.err.println("Error");

        }
    }

    private static void imprimeDeputados(ArrayList<Deputado> deputados) {
        for (int i = 0; i < deputados.size(); i++) {
            deputados.get(i).imprimeDeputado();
        }
    }

    private static void preencheDeputados(ArrayList<Deputado> deputados, String[] partes) {
        int padrao = 10;

        if(partes.length == padrao){
        Deputado deputado = new Deputado(partes[5], partes[3], partes[4], Integer.parseInt(partes[2]));
        Gasto gasto = new Gasto();
        Empresa empresa = new Empresa(partes[8], partes[6], partes[7]);
        
        if (partes[0] == "0") {
            String[] div = partes[1].split(" ");
            gasto.setData(div[0]);
            gasto.setHora(div[1]);
        } else if (partes[0] == "1") {
            gasto.setData(partes[1] + "-01-01");
            gasto.setHora("00:00:00");
        }
        gasto.setGasto(Float.parseFloat(partes[9]));

        //variaveis auxiliares
        int indexDeputado = verificaDeputadoLista(deputado, deputados);
        int indexEmpresa = verificaEmpresa(indexDeputado, deputados, empresa);

        //Se o deputado não estiver na lista, adiciona
        if (indexDeputado == -1) {
            deputados.add(deputado);
        }

        //Se a empresa não estiver na lista, adiciona
        if (indexEmpresa == -1) {
            empresa.addGastos(gasto);

            if (indexDeputado == -1) {
                deputados.get(deputados.size() - 1).addEmpresa(empresa);
            } else {
                deputados.get(indexDeputado).addEmpresa(empresa);
            }
        } else {
            if(indexDeputado == -1){
                deputados.get(deputados.size()-1).addGastoEmpresa(indexEmpresa, gasto);
            } else {
                deputados.get(indexDeputado).addGastoEmpresa(indexEmpresa, gasto);
            }
        }
        } else {
            
        }
        

    }

    private static int verificaDeputadoLista(Deputado deputado, ArrayList<Deputado> deputados) {
        for (int i = 0; i < deputados.size(); i++) {
            if (deputado.getNome().equals(deputados.get(i).getNome())) {
                return i;
            }
        }
        return -1;
    }

    private static int verificaEmpresa(int indexDeputado, ArrayList<Deputado> deputados, Empresa empresa) {
        if (indexDeputado == -1) {
            return -1;
        } else {
                for (int j = 0; j < deputados.get(indexDeputado).getEmpresas().size(); j++) {
                    if (empresa.getNome().equals(deputados.get(indexDeputado).getEmpresas().get(j).getNome())) {
                        return j;
                    }
                }
            }
        return -1;
    }

}

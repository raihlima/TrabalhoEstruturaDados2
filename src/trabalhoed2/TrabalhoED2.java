/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed2;

import algoritmos.Algoritmo;
import algoritmos.ListaEncadeada;
import java.io.*;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ice
 */
public class TrabalhoED2 {

    //static int conta = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Algoritmo algoritmo = new Algoritmo();
        ListaEncadeada<Deputado> deputados;
        deputados = new ListaEncadeada<>();
        clearConsole();
        leDados(deputados);


        //Relatorio relatorio = new Relatorio();
        //Scanner scanner = new Scanner(System.in);
        //scanner.nextLine();
        //relatorio.setRelatorioFinal();
        //relatorio.retornaTempoExecucao();
        //imprimeDeputados(deputados);    
        //algoritmo.bubbleSortDeputados(deputados);
        imprimeDeputados(deputados);
        //algoritmo.bubbleSortRecibos(deputados.get(0));
        //deputados.get(0).imprimeRecibos();
        //System.out.println("Número de deputados: " + deputados.size());
        clearConsole();

    }

    public static void leDados(ListaEncadeada<Deputado> deputados) {
        //File arquivo = new File("texto.txt");
        File arquivo = new File("deputies_dataset_tratado.csv");
        int cont = 0;

        long start = System.currentTimeMillis();
        // faz o trabalho a ser medido

        try (FileInputStream fi = new FileInputStream(arquivo)) {
            System.out.println("Tentando ler o arquivo");

            BufferedInputStream bis = new BufferedInputStream(fi);
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
            String linha = linha = reader.readLine();

            //Saber a quantidade de linhas          
            LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivo));
            linhaLeitura.skip(arquivo.length());
            int qtdLinha = linhaLeitura.getLineNumber();
            System.out.println(qtdLinha);
            //String
            String[] partes;
            String aux;

            //linha = reader.readLine();
            for (int i = 0; i < 500000; i++) {

                linha = reader.readLine();
                aux = linha;
                //System.out.println(linha);
                if (i % 100000 == 0) {
                    System.out.println("Marca de " + i);
                }
                partes = aux.split(";");
                //System.out.println(partes[]);
                preencheDeputados(deputados, partes);

                //deputados.add(new Deputado(partes[5], partes[3], partes[4], 12));
            }

            /*
            while (linha != null) {
                cont++;
                if (cont == 1) {
                    System.out.println("Arquivo sendo executado");
                }
                //System.out.println(linha);
                linha = reader.readLine();
                partes = linha.split(";");
                if (partes[0].equals("0") || partes[0].equals("1")) {
                    preencheDeputados(deputados, partes);
                }
            

                //System.out.println(cont);
            }//*/
            //System.out.println("Terminou");
        } catch (Exception e) {
            //System.out.println(cont);
            if (cont == 0) {
                System.err.println("Erro ao ler o arquivo.");
            }
            if (cont < 3014904) {
                System.err.println("Erro ao ler o arquivo na linha " + cont);
            } else {
                long elapsed = System.currentTimeMillis() - start;
                System.out.println("Arquivo lido com sucesso!");
                System.out.println(elapsed);
            }

        }
    }

    private static void imprimeDeputados(ListaEncadeada<Deputado> deputados) {
        for (int i = 0; i < deputados.getTamanho(); i++) {
            deputados.retornaInfo(i).imprimeDeputado();
        }
    }

    private static void preencheDeputados(ListaEncadeada<Deputado> deputados, String[] partes) {
        Deputado deputado = new Deputado(partes[5], partes[3], partes[4], Integer.parseInt(partes[2]));
        Recibo recibo = new Recibo(partes[8], partes[6], partes[7], Float.parseFloat(partes[9]));

        //Preenchendo a hora no recibo
        if (partes[0].equals("0")) {
            String[] div = partes[1].split(" ");
            recibo.setData(div[0]);
            recibo.setHora(div[1]);

        } else if (partes[0].equals("1")) {
            recibo.setData(partes[1] + "-01-01");
            recibo.setHora("00:00:00");
        }

        //variaveis auxiliares
        int indexDeputado = verificaDeputadoLista(deputado, deputados);

        //Se o deputado não estiver na lista, adiciona
        if (indexDeputado == -1) {
            deputado.addRecibo(recibo);
            deputados.insereFinal(deputado);
        } else {
            deputados.retornaInfo(indexDeputado).addRecibo(recibo);
        }

    }

    private static int verificaDeputadoLista(Deputado deputado, ListaEncadeada<Deputado> deputados) {
        for (int i = 0; i < deputados.getTamanho(); i++) {
            if (deputado.getNome().equals(deputados.retornaInfo(i).getNome())) {
                return i;
            }
        }
        return -1;
    }

    public final static void clearConsole() {

        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");

            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Tratar Exceptions
        }
    }

}

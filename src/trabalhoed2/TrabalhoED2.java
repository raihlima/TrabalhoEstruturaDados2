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
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

/**
 *
 * @author ice
 */
public class TrabalhoED2 {
    static int conta =0;
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
        clearConsole();

    }

    public static void leDados(ArrayList<Deputado> deputados) {
        //File arquivo = new File("texto.txt");
        File arquivo = new File("deputies_dataset.csv");
        double cont = 0;

        try (FileInputStream fi = new FileInputStream(arquivo)) {
            System.out.println("Tentando ler o arquivo");

            BufferedInputStream bis = new BufferedInputStream(fi);
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
            String linha = linha = reader.readLine();

            //Saber a quantidade de linhas          
            //LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivo));
            //linhaLeitura.skip(arquivo.length());
            //int qtdLinha = linhaLeitura.getLineNumber();
            //System.out.println(qtdLinha);

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
            while (linha != null) {
                cont++;
                if (cont == 1) {
                    System.out.println("Arquivo sendo executado");
                }
                //System.out.println(linha);
                linha = reader.readLine();
                partes = linha.split(",");
                preencheDeputados(deputados, partes);
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
                System.out.println("Arquivo lido com sucesso!");
            }

        }
    }

    private static void imprimeDeputados(ArrayList<Deputado> deputados) {
        for (int i = 0; i < deputados.size(); i++) {
            deputados.get(i).imprimeDeputado();
        }
    }

    private static void preencheDeputados(ArrayList<Deputado> deputados, String[] partes) {
        int padrao = 10;
        conta++;
        if (partes.length == padrao) {
            Deputado deputado = new Deputado(partes[5], partes[3], partes[4], Integer.parseInt(partes[2]));
            Recibo recibo = new Recibo(partes[8], partes[6], partes[7], Float.parseFloat(partes[9]));
            //Não utilizado
            /*
        Gasto gasto = new Gasto();
        Empresa empresa = new Empresa(partes[8], partes[6], partes[7]);
             */

            //Preenchendo a hora no recibo
            if (partes[0].equals("0")) {
               //String[] div = partes[1].split(" ");
               //recibo.setData(div[0]);
               //recibo.setHora(div[1]);
                //System.out.println(conta);
                //System.out.println(div[0]+div[1]);
            } else if (partes[0].equals("1")) {
                recibo.setData(partes[1] + "-01-01");
                recibo.setHora("00:00:00");
            }

            //Não utilizado
            /*
        gasto.setGasto(Float.parseFloat(partes[9]));
             */
            //variaveis auxiliares
            int indexDeputado = verificaDeputadoLista(deputado, deputados);

            //Não utilizado
            //int indexEmpresa = verificaEmpresa(indexDeputado, deputados, empresa);
            //Se o deputado não estiver na lista, adiciona
            if (indexDeputado == -1) {
                deputado.addRecibo(recibo);
                deputados.add(deputado);
            } else {
                deputados.get(indexDeputado).addRecibo(recibo);
            }

            //Não utilizado
            /*
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
             */
            
            //Criar maior que o padrão
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

    //Não utilizado
    /*
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
     */
    
    public final static void clearConsole(){

        try{
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")){
                Runtime.getRuntime().exec("cls");

            }else{
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e){
        //  Tratar Exceptions
        }
    }
}

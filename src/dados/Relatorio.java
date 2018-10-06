/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.omg.SendingContext.RunTime;

/**
 *
 * @author carcara
 */
public class Relatorio implements Serializable {

    private Calendar dataInicio;
    private Calendar dataFim;
    private String descricao;
    private long usoMemoria;
    private int quantidadeLinhas;
    private int semente = 1;
    private String tipoAlgoritmo; //Ordenacao ou Busca
    private String tipoOrganizacao; //Deputado ou Partido
    private String tipoExecucao; //Customizada ou Sementes
    private String tipoLeitura; //Linear ou Aleatória
    private String tempoExecucao;
    private String sistemaOperacional;
    private long tempoIni;
    private long tempoFim;
    private long interacao;

    public Relatorio() {
        this.dataInicio = Calendar.getInstance();
        this.sistemaOperacional = System.getProperty("os.name");
        this.tempoIni = System.nanoTime();
    }

    public Relatorio(int quantidadeLinhas, String tipoExecucao, String tipoLeitura, String tipoOrganizacao) {
        this.dataInicio = Calendar.getInstance();
        this.sistemaOperacional = System.getProperty("os.name");
        this.quantidadeLinhas = quantidadeLinhas;
        this.tipoExecucao = tipoExecucao;
        this.tipoLeitura = tipoLeitura;
        this.tipoOrganizacao = tipoOrganizacao;
        this.tempoIni = System.nanoTime();
    }

    public Relatorio(String descricao) {
        this.dataInicio = Calendar.getInstance();
        this.descricao = descricao;
        this.sistemaOperacional = System.getProperty("os.name");
        this.tempoIni = System.nanoTime();
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getUsoMemoria() {
        return usoMemoria;
    }

    public String getTempoExecucao() {
        return tempoExecucao;
    }

    public void setRelatorioFinal(String algoritmo) throws IOException {
        this.tempoFim = System.nanoTime();

        this.dataFim = Calendar.getInstance();
        this.tipoAlgoritmo = algoritmo;
        /*
        long hora = ((((dataFim.getTimeInMillis() - dataInicio.getTimeInMillis()) / 1000) / 60) / 60) % 60;
        long min = (((dataFim.getTimeInMillis() - dataInicio.getTimeInMillis()) / 1000) / 60) % 60;
        long seg = ((dataFim.getTimeInMillis() - dataInicio.getTimeInMillis()) / 1000) % 60;
        long miliseg = (dataFim.getTimeInMillis() - dataInicio.getTimeInMillis()) % 1000;
        this.tempoExecucao = Long.toString(hora) + " hora(s) " + Long.toString(min) + " min " + Long.toString(seg) + " seg " + Long.toString(miliseg) + " ms";
         */
        this.tempoExecucao = Long.toString((this.tempoFim-this.tempoIni));
        geraTexto();
    }

    public void setRelatorioFinal(String algoritmo, String descricao, int semente) throws IOException {
        this.semente = semente;
        this.descricao = descricao;
        this.dataFim = Calendar.getInstance();
        this.tipoAlgoritmo = algoritmo;
        /*
        long hora = ((((dataFim.getTimeInMillis() - dataInicio.getTimeInMillis()) / 1000) / 60) / 60) % 60;
        long min = (((dataFim.getTimeInMillis() - dataInicio.getTimeInMillis()) / 1000) / 60) % 60;
        long seg = ((dataFim.getTimeInMillis() - dataInicio.getTimeInMillis()) / 1000) % 60;
        long miliseg = (dataFim.getTimeInMillis() - dataInicio.getTimeInMillis()) % 1000;
        */
        this.tempoExecucao = Long.toString((this.tempoFim-this.tempoIni));
       // this.tempoExecucao = Long.toString(hora) + " hora(s) " + Long.toString(min) + " min " + Long.toString(seg) + " seg " + Long.toString(miliseg) + " ms";
        geraTexto();
    }

    public void retornaTempoExecucao() {
        System.out.println("Data inicio: " + dataInicio.getTime());
        System.out.println("Data inicio: " + dataInicio.getTimeInMillis());
        System.out.println("Data fim: " + dataFim.getTime());
        System.out.println("Data fim: " + dataFim.getTimeInMillis());
        System.out.println("Tempo execução: " + tempoExecucao);
        System.out.println(sistemaOperacional);
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public int getQuantidadeLinhas() {
        return quantidadeLinhas;
    }

    public void setQuantidadeLinhas(int quantidadeLinhas) {
        this.quantidadeLinhas = quantidadeLinhas;
    }

    public String getTipoExecucao() {
        return tipoExecucao;
    }

    public void setTipoExecucao(String tipoExecucao) {
        this.tipoExecucao = tipoExecucao;
    }

    public String getTipoLeitura() {
        return tipoLeitura;
    }

    public void setTipoLeitura(String tipoLeitura) {
        this.tipoLeitura = tipoLeitura;
    }

    public String getTipoOrganizacao() {
        return tipoOrganizacao;
    }

    public void setTipoOrganizacao(String tipoOrganizacao) {
        this.tipoOrganizacao = tipoOrganizacao;
    }

    public void geraTexto() throws IOException {
        new File("Relatorios/" + tipoOrganizacao).mkdirs();
        FileWriter arq = new FileWriter("Relatorios/" + tipoOrganizacao + "/" + tipoAlgoritmo + tipoExecucao + ".txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy;HH:mm:ss");
        gravarArq.print(this.getTipoLeitura() + ";");
        gravarArq.print(sdf.format(this.getDataInicio().getTime()) + ";");
        gravarArq.print(sdf.format(this.getDataFim().getTime()) + ";");
        gravarArq.print(this.getSistemaOperacional() + ";");
        gravarArq.print(this.getTempoExecucao() + "ns;");
        Runtime rt = Runtime.getRuntime();  
        this.usoMemoria = rt.maxMemory() -rt.freeMemory();
        gravarArq.print(this.getUsoMemoria() + "bytes;");
        gravarArq.print(this.getQuantidadeLinhas() + ";");
        gravarArq.print(this.getDescricao());
        gravarArq.println();
        arq.close();
    }

    public int getSemente() {
        return semente;
    }

    public void setSemente(int semente) {
        this.semente = semente;
    }

    public long getInteracao() {
        return interacao;
    }

    public void setInteracao(long interacao) {
        this.interacao = interacao;
    }
    
    

}

package dados;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;
import javax.swing.JOptionPane;
//import org.omg.SendingContext.RunTime;

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
    private int trocaColisaoCopia; //Troca para ordenação e Colisão para Busca(Hash)
    private String tempoExecucao;
    private String sistemaOperacional;
    private long tempoIni;
    private long tempoFim;
    private long interacao;

    public Relatorio() {
        this.dataInicio = Calendar.getInstance();
        this.sistemaOperacional = System.getProperty("os.name");
        this.tempoIni = System.nanoTime();
        this.interacao = 0;
        this.trocaColisaoCopia = 0;
    }

    public Relatorio(int quantidadeLinhas, String tipoExecucao, String tipoLeitura, String tipoOrganizacao) {
        this.dataInicio = Calendar.getInstance();
        this.sistemaOperacional = System.getProperty("os.name");
        this.quantidadeLinhas = quantidadeLinhas;
        this.tipoExecucao = tipoExecucao;
        this.tipoLeitura = tipoLeitura;
        this.tipoOrganizacao = tipoOrganizacao;
        this.tempoIni = System.nanoTime();
        this.interacao = 0;
        this.trocaColisaoCopia = 0;
    }

    public Relatorio(String descricao) {
        this.dataInicio = Calendar.getInstance();
        this.descricao = descricao;
        this.sistemaOperacional = System.getProperty("os.name");
        this.tempoIni = System.nanoTime();
        this.interacao = 0;
        this.trocaColisaoCopia = 0;
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
        this.tempoExecucao = Long.toString((this.tempoFim - this.tempoIni));
        geraTexto();
    }

    public void setRelatorioFinal(String algoritmo, String descricao, int semente) throws IOException {
        this.tempoFim = System.nanoTime();
        this.semente = semente;
        this.descricao = descricao;
        this.dataFim = Calendar.getInstance();
        this.tipoAlgoritmo = algoritmo;
        this.tempoExecucao = Long.toString((this.tempoFim - this.tempoIni));

        // this.tempoExecucao = Long.toString(hora) + " hora(s) " + Long.toString(min) + " min " + Long.toString(seg) + " seg " + Long.toString(miliseg) + " ms";
        geraTexto();
    }
    //Imprime o tempo de execucao calculado
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

    public void geraTexto() {
        try {
            //Parametros da criacao do arquivo texto
            new File("Relatorios/" + tipoOrganizacao).mkdirs();
            File arquivo = new File("Relatorios/" + this.tipoOrganizacao + "/" + this.tipoAlgoritmo + this.tipoExecucao + ".txt");
            FileWriter arq = new FileWriter(arquivo, true);
            PrintWriter gravarArq = new PrintWriter(arq);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy;HH:mm:ss");
            
            //Calvula a quantidade de linhas
            LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(arquivo));
            linhaLeitura.skip(arquivo.length());
            int totalLinhas = linhaLeitura.getLineNumber();
            
            if(totalLinhas==0){//Cria o cabecalho do texto
                gravarArq.println("Tipo;Data_Inicio;Hora_Inicio;Data_Termino;Hora_Termino;Sistema_Operacional;Tempo(ns);Tempo(ms);Gasto_Memoria(bytes);Linhas_Lidas;Algoritmo;Interacoes;Troca_ou_Colisao_ou_Copia");
            }
            //parametros da gravacao do texto
            if (this.tipoExecucao.equalsIgnoreCase("Sementes")) {
                gravarArq.print("Semente " + this.semente + ";");
            } else {
                gravarArq.print(this.getTipoLeitura() + ";");
            }
            gravarArq.print(sdf.format(this.getDataInicio().getTime()) + ";");
            gravarArq.print(sdf.format(this.getDataFim().getTime()) + ";");
            gravarArq.print(this.getSistemaOperacional() + ";");
            gravarArq.print(this.getTempoExecucao() + ";" + (this.dataFim.getTimeInMillis() - this.dataInicio.getTimeInMillis()) + ";");
            Runtime rt = Runtime.getRuntime();
            this.usoMemoria = rt.maxMemory() - rt.freeMemory();
            gravarArq.print(this.getUsoMemoria() + ";");
            gravarArq.print(this.getQuantidadeLinhas() + ";");
            gravarArq.print(this.getDescricao() + ";");
            gravarArq.print(getInteracao() + ";");
            gravarArq.print(getTrocaColisaoCopia());
            gravarArq.println();
            arq.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o relatório", "Erro", JOptionPane.ERROR_MESSAGE);
        }
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

    public void incrementaInteracao() {
        this.interacao = this.interacao + 1;
    }

    public void setInteracao(long interacao) {
        this.interacao = interacao;
    }

    public int getTrocaColisaoCopia() {
        return trocaColisaoCopia;
    }

    public void setTrocaColisao(int trocaColisaoCopia) {
        this.trocaColisaoCopia = trocaColisaoCopia;
    }

    public void incrementaTrocaColisaoCopia() {
        this.trocaColisaoCopia = this.trocaColisaoCopia + 1;
    }

}

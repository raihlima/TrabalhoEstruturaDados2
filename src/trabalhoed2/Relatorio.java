/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author carcara
 */
public class Relatorio {
    private Calendar dataInicio;
    private Calendar dataFim;
    private String descricao;
    private long usoMemoria;
    private long tempoExecucao;
    private String sistemaOperacional;

    public Relatorio() {
        this.dataInicio = Calendar.getInstance();
        this.sistemaOperacional = System.getProperty("os.name");
        
    }

    public Relatorio(String descricao) {
        this.dataInicio = Calendar.getInstance();
        this.descricao = descricao;
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

    public long getUsoMemoria() {
        return usoMemoria;
    }

    public long getTempoExecucao() {
        return tempoExecucao;
    }
    
    
      public void setRelatorioFinal(){
        this.dataFim = Calendar.getInstance();
        this.tempoExecucao = (dataFim.getTimeInMillis()-dataInicio.getTimeInMillis())/1000;
    }
    
    public void retornaTempoExecucao(){
        System.out.println("Data inicio: " + dataInicio.getTime());
        System.out.println("Data inicio: " + dataInicio.getTimeInMillis());
        System.out.println("Data fim: " + dataFim.getTime());
        System.out.println("Data fim: " + dataFim.getTimeInMillis());
        System.out.println("Tempo execução: " + tempoExecucao);
        System.out.println(sistemaOperacional);
    }
    
    
}

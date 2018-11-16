package trabalhoed2;
//TO DO testar se funciona sem esse arquivo
//import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;

/**
 * /////////////////////////////////////
 */
public class Relatorio {
    private Calendar dataInicio;
    private Calendar dataFim;
    private String descricao;
    private long usoMemoria;
    private String tempoExecucao;
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

    public String getTempoExecucao() {
        return tempoExecucao;
    }
    
    
      public void setRelatorioFinal(){
        this.dataFim = Calendar.getInstance();
        long hora = ((((dataFim.getTimeInMillis()-dataInicio.getTimeInMillis())/1000)/60)/60)%60;
        long min = (((dataFim.getTimeInMillis()-dataInicio.getTimeInMillis())/1000)/60)%60;
        long seg = ((dataFim.getTimeInMillis()-dataInicio.getTimeInMillis())/1000)%60;
        long miliseg = (dataFim.getTimeInMillis()-dataInicio.getTimeInMillis())%1000;
        this.tempoExecucao = Long.toString(hora) + " hora(s) " + Long.toString(min) + " min " + Long.toString(seg) + " seg " + Long.toString(miliseg) + " ms" ;
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

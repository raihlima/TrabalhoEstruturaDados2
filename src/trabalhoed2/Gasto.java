package trabalhoed2;

/**
 * Armazena os dados dos gastos lidos do arquivo de entrada
 */
public class Gasto {
    
    private String data;
    private String hora;
    private float totalGasto;

    public Gasto() {
    }
    /**
     * Construtor do tipo de dado Gasto
     * @param data Data de registro do gasto
     * @param totalGasto Valor total gasto
     */
    public Gasto(String data, float totalGasto) {
        this.data = data;
        this.totalGasto = totalGasto;
    }

    public float getGasto() {
        return totalGasto;
    }

    public void setGasto(float totalGasto) {
        this.totalGasto = totalGasto;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

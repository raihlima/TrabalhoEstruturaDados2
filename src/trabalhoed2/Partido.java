package trabalhoed2;

/**
 * Armazena os dados dos Partidos lidos do arquivo de entrada
 */
public class Partido {

    private String nome;
    private float totalGasto;

    public Partido() {
    }

    /**
     * Construtor do tipo de dado Partido
     *
     * @param nome Nome do partido
     * @param totalGasto Valor total gasto
     */
    public Partido(String nome, float totalGasto) {
        this.nome = nome;
        this.totalGasto = totalGasto;
    }

    public float getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(float totalGasto) {
        this.totalGasto = totalGasto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

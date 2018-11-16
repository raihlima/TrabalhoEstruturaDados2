package trabalhoed2;

import java.util.ArrayList;
//import java.util.Collections;

/**
 * Classe que contem as funcoes do objeto Deputado
 */
public class Deputado {

    private String nome;
    private String partido;
    private String estado;
    private int id;
    private ArrayList<Recibo> recibos;
    private float totalGasto;

    /**
     * Construtores do obejtos que representam os deputados
     */
    public Deputado() {
        this.recibos = new ArrayList<>();
        this.totalGasto = 0;
    }

    public Deputado(String nome, String partido, String estado, int id) {
        this.nome = nome;
        this.totalGasto = 0;
        this.partido = partido;
        this.estado = estado;
        this.id = id;
        this.recibos = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Essa funcao imprime os dados basicos do Deputado
     */
    public void imprimeDeputado() {
        System.out.println("Nome: " + getNome() + " Partido: " + this.partido + " " + this.estado + " ID: " + this.id);
    }

    public ArrayList<Recibo> getRecibos() {
        return recibos;
    }

    public void setRecibos(ArrayList<Recibo> recibos) {
        this.recibos = recibos;
    }

    /**
     * Adiciona um novo Recibo ao Deputado em questao
     *
     * @param recibo Recibo do gasto realizado pelo deputado
     */
    public void addRecibo(Recibo recibo) {
        this.recibos.add(recibo);
    }

    /**
     * Imprime todos os Recibos de um determinado Deputado
     */
    public void imprimeRecibos() {
        for (int i = 0; i < recibos.size(); i++) {
            System.out.println(recibos.get(i).getGasto());
        }
    }

    public float getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(float totalGasto) {
        this.totalGasto = totalGasto;
    }

}

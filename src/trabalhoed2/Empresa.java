package trabalhoed2;

import java.util.ArrayList;

/**
 * Classe que representa os dados das Empresas do programa
 */
public class Empresa {

    private String nome;
    private String cnpj;
    private String tipo;
    private ArrayList<Gasto> gastos;

    public Empresa() {
    }

    /**
     * Construtor de Empresa
     *
     * @param nome Nome da Empresa
     * @param cnpj Numero CNPJ
     * @param tipo Tipo da empresa
     */
    public Empresa(String nome, String cnpj, String tipo) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.tipo = tipo;
        gastos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(ArrayList<Gasto> gastos) {
        this.gastos = gastos;
    }

    /**
     * Adiciona um novo gasto na Empresa
     *
     * @param gasto Dados do gasto inseridos a partir do arquivo de entrada
     */
    public void addGastos(Gasto gasto) {
        this.gastos.add(gasto);
    }
}

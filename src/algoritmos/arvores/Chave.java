/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.arvores;

/**
 *
 * @author ice
 */
public class Chave {

    private String data;
    private String hora;
    private int idDeputado;
    private String partido;
    private String estado;
    private String deputado;
    private String cnpj;
    private String descricao;
    private String empresa;
    private float gasto;
    private int id;

    public Chave() {
    }

    public Chave(String data, String hora, int idDeputado, String partido, String estado, String deputado, String cnpj, String descricao, String empresa, float gasto) {
        this.data = data;
        this.hora = hora;
        this.idDeputado = idDeputado;
        this.partido = partido;
        this.estado = estado;
        this.deputado = deputado;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.empresa = empresa;
        this.gasto = gasto;
        this.id = -1;
    }

    public Chave(String data, String hora, int idDeputado, String partido, String estado, String deputado, String cnpj, String descricao, String empresa, float gasto, int id) {
        this.data = data;
        this.hora = hora;
        this.idDeputado = idDeputado;
        this.partido = partido;
        this.estado = estado;
        this.deputado = deputado;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.empresa = empresa;
        this.gasto = gasto;
        this.id = id;
    }

    public float getGasto() {
        return gasto;
    }

    public void setGasto(float gasto) {
        this.gasto = gasto;
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

    public int getIdDeputado() {
        return idDeputado;
    }

    public void setIdDeputado(int idDeputado) {
        this.idDeputado = idDeputado;
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

    public String getDeputado() {
        return deputado;
    }

    public void setDeputado(String deputado) {
        this.deputado = deputado;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

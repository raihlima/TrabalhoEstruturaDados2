/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed2;

/**
 *
 * @author carcara
 */
public class Recibo {
    private String nomeEmpresa;
    private String cnpj;
    private String tipoGasto;
    private String data;
    private String hora;
    private float gasto;

    public Recibo() {
    }

    public Recibo(String nomeEmpresa, String cnpj, String tipoGasto, float gasto) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.tipoGasto = tipoGasto;
        this.gasto = gasto;
    }

    public Recibo(String nomeEmpresa, String cnpj, String tipoGasto, String data, String hora, float gasto) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
        this.tipoGasto = tipoGasto;
        this.data = data;
        this.hora = hora;
        this.gasto = gasto;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
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

    public float getGasto() {
        return gasto;
    }

    public void setGasto(float gasto) {
        this.gasto = gasto;
    }
      
    
}

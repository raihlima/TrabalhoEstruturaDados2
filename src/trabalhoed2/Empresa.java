/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed2;

import java.util.ArrayList;

/**
 *
 * @author ice
 */
public class Empresa {
    private String nome;
    private String cnpj;
    private String tipo;
    private ArrayList <Gasto> gastos;

    public Empresa() {
    } 

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

    public ArrayList <Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(ArrayList <Gasto> gastos) {
        this.gastos = gastos;
    }
    
    public void addGastos(Gasto gasto){
        this.gastos.add(gasto);
    }
    
    
    
    
}

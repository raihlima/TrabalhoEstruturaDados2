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
public class Deputado {
    private String nome;
    private String partido;
    private String estado;
    private int id;
    private ArrayList<Empresa>empresas;

    public Deputado() {
        this.empresas = new ArrayList<>();
    }
    
    

    public Deputado(String nome, String partido, String estado, int id) {
        this.nome = nome;
        this.partido = partido;
        this.estado = estado;
        this.id = id;
        this.empresas = new ArrayList<>();
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
    
    public void imprimeDeputado(){
        System.out.println("Nome: " + this.nome + " Partido: " + this.partido + " " + this.estado + " ID: " + this.id);
    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }
    
    public void addEmpresa(Empresa empresa){
        this.empresas.add(empresa);
    }
    
    public void addGastoEmpresa (int indexEmpresa, Gasto gasto){
        this.empresas.get(indexEmpresa).addGastos(gasto);
    }
    
    
    
}

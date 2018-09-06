/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

/**
 *
 * @author carcara
 */
class No<Class> {

    private Class objeto;
    private No proximo;
    private No anterior;

    No() {
    }
    
    No (Class objeto){
        this.objeto = objeto;
    }
    
    No(Class objeto, No anterior) {
        this.objeto = objeto;
        this.proximo = null;
    }

    No(Class objeto, No proximo, No anterior) {
        this.objeto = objeto;
        this.proximo = proximo;
        this.anterior = anterior;
    }
    
    

    public Class getObjeto() {
        return this.objeto;
    }

    public void setObjeto(Class objeto) {
        this.objeto = objeto;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    public No getAnterior() {
        return anterior;
    }

    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }
    
    
}

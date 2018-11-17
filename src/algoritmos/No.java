package algoritmos;

import java.io.Serializable;

/**
 * Implementacao da classe de No
 */
public class No<Class> implements Serializable {
    private Class objeto;
    private No proximo;
    private No anterior;

    // Construtores //
    protected No() {
    }
    
    protected No (Class objeto){
        this.objeto = objeto;
    }
    
    protected No(Class objeto, No anterior) {
        this.objeto = objeto;
        this.proximo = null;
    }

    protected No(Class objeto, No proximo, No anterior) {
        this.objeto = objeto;
        this.proximo = proximo;
        this.anterior = anterior;
    }
    // Gets e Sets //
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

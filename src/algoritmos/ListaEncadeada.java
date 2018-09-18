/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

/**
 *
 * @author carcara
 * @param <Class>
 */
public class ListaEncadeada<Class> {

    private No inicio;
    private No fim;
    private int tamanho;

    public ListaEncadeada() {
        this.tamanho = 0;
    }

    public No getInicio() {
        return inicio;
    }

    public void setInicio(No inicio) {
        this.inicio = inicio;
    }

    public No getFim() {
        return fim;
    }

    public void setFim(No fim) {
        this.fim = fim;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    //Métodos
    public void insereInicio(Class objeto) {
        No no = new No(objeto, this.inicio, null);

        if (this.tamanho == 0) {
            this.fim = no;
        } else {
            this.inicio.setAnterior(no);
        }
        this.inicio = no;
        this.tamanho += 1;
    }

    public void removeInicio() {
        No no;
        if (this.inicio != null) {
            no = this.inicio;
            this.inicio = no.getProximo();
            no = null;
            this.tamanho -= 1;
            if (this.tamanho == 0) {
                this.fim = null;
            } else {
                this.inicio.setAnterior(null);
            }
        } else {

        }

    }

    public void insereFinal(Class objeto) {
        No no = new No(objeto, null, this.fim);

        if (this.tamanho == 0) {
            this.inicio = no;
        } else {
            this.fim.setProximo(no);
        }
        this.fim = no;
        this.tamanho += 1;

    }

    public void removeFinal() {
        No no;
        if (this.fim != null) {
            no = this.fim;
            this.fim = no.getAnterior();
            no = null;
            this.tamanho -= 1;
            if (this.tamanho == 0) {
                this.inicio = null;
                this.fim = null;
            } else {
                this.fim.setProximo(null);
            }
        } else {

        }
    }
    
    public Class retornaFim(){
        return (Class) fim.getObjeto();
    }
    /**
     * Esta função retorna o objeto guardado na Lista Encadeada
     *
     * @param index (inteiro para selecionar a posição da lista)
     * @return objeto
     */
    public Class retornaInfo(int index) {
        if (index == 0) {
            return (Class) this.inicio.getObjeto();
        } else if (index == this.tamanho - 1) {
            return (Class) this.fim.getObjeto();
        } else if (index < 0 || index >= this.tamanho) {
            return null;

        } else {
            No aux;
            if (index <= this.tamanho / 2) {
                aux = this.inicio;
                for (int i = 0; i < index; i++) {
                    aux = aux.getProximo();
                }
            } else {
                aux = this.fim;
                for (int i = this.tamanho - 1; i > index; i--) {
                    aux = aux.getAnterior();
                }
            }

            return (Class) aux.getObjeto();
        }
    }

    public void insere(int index, Class objeto) throws NullPointerException {
        if (index == 0) {
            insereInicio(objeto);
        } else if (index == tamanho) {
            insereFinal(objeto);
        } else if (index > 0 && index < tamanho) {
            No no = new No(objeto);
            No aux = this.inicio;
            No aux2;
            for (int i = 1; i < index; i++) {
                aux = aux.getProximo();
            }
            aux2 = aux.getProximo();
            no.setAnterior(aux);
            no.setProximo(aux2);
            aux.setProximo(no);
            aux2.setAnterior(no);
            this.tamanho++;
        } else {
            NullPointerException e = new NullPointerException("Index fora do escopo");
            throw e;
        }

    }

    public void altera(int index, Class objeto) throws NullPointerException {
        if (index < 0 || index >= this.tamanho) {
            throw new NullPointerException("Index inválido");
        } else {

            if (index == 0) {
                this.inicio.setObjeto(objeto);
            } else if (index == tamanho - 1) {
                this.fim.setObjeto(objeto);
            } else if (index <= tamanho / 2) {
                No aux = this.inicio;
                for (int i = 0; i < index; i++) {
                    aux = aux.getProximo();
                }
                aux.setObjeto(objeto);
            } else {
                No aux = this.fim;
                for (int i = this.getTamanho()-1; i > index; i--) {
                    aux = aux.getAnterior();
                }
                aux.setObjeto(objeto);
            }

        }
    }

    public void remove(int index) {
        if (index == 0) {
            removeInicio();
        } else if (index == tamanho - 1) {
            removeFinal();
        } else if (index > 0 && index < tamanho) {
            No aux;
            No auxAnt;
            No auxProx;
            aux = this.inicio;
            for (int i = 0; i < index; i++) {
                aux = aux.getProximo();
            }
            auxAnt = aux.getAnterior();
            auxProx = aux.getProximo();

            auxAnt.setProximo(auxProx);
            auxProx.setAnterior(auxAnt);
            aux = null;

            this.tamanho--;
        }
    }

    public void troca(int index1, int index2) {
        Class aux;
        No no1;
        No no2;

        if (index1 == 0) {
            aux = (Class) inicio.getObjeto();
            no1 = this.inicio;
        } else if (index1 == tamanho - 1) {
            aux = (Class) fim.getObjeto();
            no1 = this.fim;
        } else {
            no1 = this.inicio;
            for (int i = 0; i < index1; i++) {
                no1 = no1.getProximo();
            }
            aux = (Class) no1.getObjeto();
        }

        if (index2 == 0) {
            no2 = this.inicio;
        } else if (index2 == tamanho - 1) {
            no2 = this.fim;
        } else {
            no2 = this.inicio;
            for (int i = 0; i < index2; i++) {
                no2 = no2.getProximo();
            }
        }

        no1.setObjeto((Class) no2.getObjeto());
        no2.setObjeto(aux);

    }

}

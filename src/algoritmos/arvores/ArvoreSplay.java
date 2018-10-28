/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.arvores;

/**
 *
 * @author carcara
 */
public class ArvoreSplay {

    private No raiz;
    private int cont = 0;

    /**
     * Constructor *
     */
    public ArvoreSplay() {
        raiz = null;
    }

    public boolean isVazio() {
        return raiz == null;
    }

    public void limparArvore() {
        raiz = null;
        cont = 0;
    }


    public void inserir(int id, Chave chave) {
        No z = raiz;
        No p = null;
        while (z != null) {
            p = z;
            if (id > p.getId()) {
                z = z.getDir();
            } else {
                z = z.getEsq();
            }
        }
        z = new No(id,chave);       
        z.setPai(p);

        if (p == null) {
            this.raiz = z;
        } else if (id > p.getId()) {
            p.setDir(z);
        } else {
            p.setEsq(z);
        }
        Splay(z);
        cont++;
    }

    /**
     * rotate makeLeftChildParent*
     */
    public void rotacaoDireita(No c, No p) {

        if ((c == null) || (p == null) || (p.getEsq() != c) || (c.getPai() != p)) {
            throw new RuntimeException("WRONG");
        }

        if (p.getPai() != null) {

            if (p == p.getPai().getEsq()) {
                p.getPai().setEsq(c);
            } else {
                p.getPai().setDir(c);
            }

        }

        if (c.getDir() != null) {
            c.getDir().setPai(p);
        }

        c.setPai(p.getPai());

        p.setPai(c);

        p.setEsq(c.getDir());

        c.setDir(p);

    }

    /**
     * rotate makeRightChildParent *
     */
    public void rotacaoEsquerda(No c, No p) {

        if ((c == null) || (p == null) || (p.getDir() != c) || (c.getPai() != p)) {
            throw new RuntimeException("WRONG");
        }

        if (p.getPai() != null) {

            if (p == p.getPai().getEsq()) {
                p.getPai().setEsq(c);
            } else {
                p.getPai().setDir(c);
            }

        }

        if (c.getEsq() != null) {
            c.getEsq().setPai(p);
        }

        c.setPai(p.getPai());

        p.setPai(c);

        p.setDir(c.getEsq());

        c.setEsq(p);

    }

    /**
     * function splay *
     */
    private void Splay(No x) {

        while (x.getPai() != null) {

            No pai = x.getPai();

            No avo = pai.getPai();

            if (avo == null) {

                if (x == pai.getEsq()) {
                    rotacaoDireita(x, pai);
                } else {
                    rotacaoEsquerda(x, pai);
                }

            } else {

                if (x == pai.getEsq()) {

                    if (pai == avo.getEsq()) {

                        rotacaoDireita(pai, avo);

                        rotacaoDireita(x, pai);

                    } else {

                        rotacaoDireita(x, x.getPai());

                        rotacaoEsquerda(x, x.getPai());

                    }

                } else {

                    if (pai == avo.getEsq()) {

                        rotacaoEsquerda(x, x.getPai());

                        rotacaoDireita(x, x.getPai());

                    } else {

                        rotacaoEsquerda(pai, avo);

                        rotacaoEsquerda(x, pai);

                    }

                }

            }

        }

        raiz = x;

    }

    /**
     * function to remove getId() *
     */
    public void remove(int id) {

        No no = encontarNo(id);

        remove(no);

    }

    /**
     * function to remove no *
     */
    private void remove(No no) {

        if (no == null) {
            return;
        }

        Splay(no);

        if ((no.getEsq() != null) && (no.getDir() != null)) {

            No min = no.getEsq();

            while (min.getDir() != null) {
                min = min.getDir();
            }

            min.setDir(no.getDir());

            no.getDir().setPai(min);

            no.getEsq().setPai(null);

            raiz = no.getEsq();

        } else if (no.getDir() != null) {

            no.getDir().setPai(null);

            raiz = no.getDir();

        } else if (no.getEsq() != null) {

            no.getEsq().setPai(null);

            raiz = no.getEsq();

        } else {

            raiz = null;

        }

        no.setPai(null);

        no.setEsq(null);

        no.setDir(null);

        no = null;

        cont--;

    }

    /**
     * Functions to cont number of nodes *
     */
    public int quantidadeNos() {

        return cont;

    }

    /**
     * Functions to buscarNo for an getId() *
     */
    public boolean buscarNo(int id) {

        return encontarNo(id) != null;

    }

    private No encontarNo(int id) {

        No noAnterior = null;

        No z = raiz;

        while (z != null) {

            noAnterior = z;

            if (id > z.getId()) {
                z = z.getDir();
            } else if (id < z.getId()) {
                z = z.getEsq();
            } else if (id == z.getId()) {

                Splay(z);

                return z;

            }

        }

        if (noAnterior != null) {

            Splay(noAnterior);

            return null;

        }

        return null;

    }

    /**
     * Function for impressaoEmOrdem traversal *
     */
    public void impressaoEmOrdem() {

        impressaoEmOrdem(raiz);

    }

    private void impressaoEmOrdem(No r) {

        if (r != null) {

            impressaoEmOrdem(r.getEsq());

            System.out.print(r.getId() + " ");

            impressaoEmOrdem(r.getDir());

        }

    }

    /**
     * Function for impressaoEmPreOrdem traversal *
     */
    public void impressaoEmPreOrdem() {

        impressaoEmPreOrdem(raiz);

    }

    private void impressaoEmPreOrdem(No r) {

        if (r != null) {

            System.out.print(r.getId() + " ");

            impressaoEmPreOrdem(r.getEsq());

            impressaoEmPreOrdem(r.getDir());

        }

    }

    /**
     * Function for impressaoEmPosOrdem traversal *
     */
    public void impressaoEmPosOrdem() {

        impressaoEmPosOrdem(raiz);

    }

    private void impressaoEmPosOrdem(No r) {

        if (r != null) {

            impressaoEmPosOrdem(r.getEsq());

            impressaoEmPosOrdem(r.getDir());

            System.out.print(r.getId() + " ");

        }

    }
}

package algoritmos.arvores;

import dados.Relatorio;

public class ArvoreSplay {

    private No raiz;
    private int cont = 0;

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

    public void inserir(int id, Chave chave, Relatorio relatorio) {
        No z = raiz;
        No p = null;
        while (z != null) {
            relatorio.incrementaInteracao();
            p = z;
            if (id > p.getId()) {
                relatorio.incrementaInteracao();
                z = z.getDir();
            } else {
                relatorio.incrementaInteracao();
                z = z.getEsq();
            }
        }
        z = new No(id, chave);
        z.setPai(p);

        if (p == null) {
            relatorio.incrementaInteracao();
            this.raiz = z;
        } else if (id > p.getId()) {
            relatorio.incrementaInteracao();
            p.setDir(z);
        } else {
            relatorio.incrementaInteracao();
            p.setEsq(z);
        }
        Splay(z, relatorio);
        cont++;
    }

    /**
     * rotate makeLeftChildParent*
     */
    public void rotacaoDireita(No c, No p, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        if ((c == null) || (p == null) || (p.getEsq() != c) || (c.getPai() != p)) {
            relatorio.incrementaInteracao();
            throw new RuntimeException("WRONG");
        }

        if (p.getPai() != null) {
            relatorio.incrementaInteracao();
            if (p == p.getPai().getEsq()) {
                relatorio.incrementaInteracao();
                p.getPai().setEsq(c);
            } else {
                relatorio.incrementaInteracao();
                p.getPai().setDir(c);
            }

        }

        if (c.getDir() != null) {
            relatorio.incrementaInteracao();
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
    public void rotacaoEsquerda(No c, No p, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        if ((c == null) || (p == null) || (p.getDir() != c) || (c.getPai() != p)) {
            relatorio.incrementaInteracao();
            throw new RuntimeException("WRONG");
        }

        if (p.getPai() != null) {
            relatorio.incrementaInteracao();
            if (p == p.getPai().getEsq()) {
                relatorio.incrementaInteracao();
                p.getPai().setEsq(c);
            } else {
                relatorio.incrementaInteracao();
                p.getPai().setDir(c);
            }

        }

        if (c.getEsq() != null) {
            relatorio.incrementaInteracao();
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
    private void Splay(No x, Relatorio relatorio) {

        while (x.getPai() != null) {
            relatorio.incrementaInteracao();
            No pai = x.getPai();

            No avo = pai.getPai();

            if (avo == null) {
                relatorio.incrementaInteracao();
                if (x == pai.getEsq()) {
                    relatorio.incrementaInteracao();
                    rotacaoDireita(x, pai, relatorio);
                } else {
                    relatorio.incrementaInteracao();
                    rotacaoEsquerda(x, pai, relatorio);
                }

            } else {
                relatorio.incrementaInteracao();
                if (x == pai.getEsq()) {
                    relatorio.incrementaInteracao();
                    if (pai == avo.getEsq()) {
                        relatorio.incrementaInteracao();
                        rotacaoDireita(pai, avo, relatorio);

                        rotacaoDireita(x, pai, relatorio);

                    } else {
                        relatorio.incrementaInteracao();
                        rotacaoDireita(x, x.getPai(), relatorio);

                        rotacaoEsquerda(x, x.getPai(), relatorio);

                    }

                } else {
                    relatorio.incrementaInteracao();
                    if (pai == avo.getEsq()) {
                        relatorio.incrementaInteracao();
                        rotacaoEsquerda(x, x.getPai(), relatorio);

                        rotacaoDireita(x, x.getPai(), relatorio);

                    } else {
                        relatorio.incrementaInteracao();
                        rotacaoEsquerda(pai, avo, relatorio);

                        rotacaoEsquerda(x, pai, relatorio);

                    }

                }

            }

        }

        raiz = x;

    }

    /**
     * function to remove getId() *
     */
    public void remover(int id, Relatorio relatorio) {

        No no = encontarNo(id, relatorio);

        remover(no, relatorio);

    }

    /**
     * function to remove no *
     */
    private void remover(No no, Relatorio relatorio) {

        if (no == null) {
            relatorio.incrementaInteracao();
            return;
        }

        Splay(no, relatorio);

        if ((no.getEsq() != null) && (no.getDir() != null)) {
            relatorio.incrementaInteracao();

            No min = no.getEsq();

            while (min.getDir() != null) {
                relatorio.incrementaInteracao();
                min = min.getDir();
            }

            min.setDir(no.getDir());

            no.getDir().setPai(min);

            no.getEsq().setPai(null);

            raiz = no.getEsq();

        } else if (no.getDir() != null) {
            relatorio.incrementaInteracao();

            no.getDir().setPai(null);

            raiz = no.getDir();

        } else if (no.getEsq() != null) {
            relatorio.incrementaInteracao();

            no.getEsq().setPai(null);

            raiz = no.getEsq();

        } else {
            relatorio.incrementaInteracao();
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
    public int quantidadeNos(Relatorio relatorio) {

        return cont;

    }

    /**
     * Functions to buscarNo for an getId() *
     */
    public boolean busca(int id, Relatorio relatorio) {

        return encontarNo(id, relatorio) != null;

    }

    private No encontarNo(int id, Relatorio relatorio) {

        No noAnterior = null;

        No z = raiz;

        while (z != null) {
            relatorio.incrementaInteracao();

            noAnterior = z;

            if (id > z.getId()) {
                relatorio.incrementaInteracao();
                z = z.getDir();
            } else if (id < z.getId()) {
                relatorio.incrementaInteracao();
                z = z.getEsq();
            } else if (id == z.getId()) {
                relatorio.incrementaInteracao();

                Splay(z, relatorio);

                return z;

            }

        }

        if (noAnterior != null) {

            Splay(noAnterior, relatorio);

            return null;

        }

        return null;

    }

    /**
     * Function for impressaoEmOrdem traversal *
     */
    public void impressaoEmOrdem(Relatorio relatorio) {

        impressaoEmOrdem(raiz, relatorio);

    }

    private void impressaoEmOrdem(No r, Relatorio relatorio) {

        if (r != null) {
            relatorio.incrementaInteracao();
            impressaoEmOrdem(r.getEsq(), relatorio);

            System.out.print(r.getId() + " ");

            impressaoEmOrdem(r.getDir(), relatorio);

        }

    }

    /**
     * Function for impressaoEmPreOrdem traversal *
     */
    public void impressaoEmPreOrdem(Relatorio relatorio) {

        impressaoEmPreOrdem(raiz, relatorio);

    }

    private void impressaoEmPreOrdem(No r, Relatorio relatorio) {

        if (r != null) {
            relatorio.incrementaInteracao();
            System.out.print(r.getId() + " ");

            impressaoEmPreOrdem(r.getEsq(), relatorio);

            impressaoEmPreOrdem(r.getDir(), relatorio);

        }

    }

    /**
     * Function for impressaoEmPosOrdem traversal *
     */
    public void impressaoEmPosOrdem(Relatorio relatorio) {

        impressaoEmPosOrdem(raiz, relatorio);

    }

    private void impressaoEmPosOrdem(No r, Relatorio relatorio) {

        if (r != null) {
            relatorio.incrementaInteracao();
            impressaoEmPosOrdem(r.getEsq(), relatorio);

            impressaoEmPosOrdem(r.getDir(), relatorio);

            System.out.print(r.getId() + " ");

        }

    }
}

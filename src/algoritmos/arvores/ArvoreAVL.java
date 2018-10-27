package algoritmos.arvores;

import java.util.ArrayList;

public class ArvoreAVL {

    private No raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    public void inserir(int id, Chave chave) {
        if (raiz == null) {
            this.raiz = new No(id, chave);
        } else {
            No n = new No(id, chave);
            inserirAVL(this.raiz, n);
        }
    }

    private void inserirAVL(No aComparar, No aInserir) {

        if (aComparar == null) {
            this.raiz = aInserir;

        } else {

            if (aInserir.getId() < aComparar.getId()) {

                if (aComparar.getEsq() == null) {
                    aComparar.setEsq(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento(aComparar);

                } else {
                    inserirAVL(aComparar.getEsq(), aInserir);
                }

            } else if (aInserir.getId() > aComparar.getId()) {

                if (aComparar.getDir() == null) {
                    aComparar.setDir(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento(aComparar);

                } else {
                    inserirAVL(aComparar.getDir(), aInserir);
                }

            } else {
                // O nó já existe
            }
        }
    }

    public void verificarBalanceamento(No atual) {
        setBalanceamento(atual);
        int balanceamento = atual.getBalanceamento();

        if (balanceamento == -2) {

            if (altura(atual.getEsq().getEsq()) >= altura(atual.getEsq().getDir())) {
                atual = rotacaoDireita(atual);

            } else {
                atual = duplaRotacaoEsquerdaDireita(atual);
            }

        } else if (balanceamento == 2) {

            if (altura(atual.getDir().getDir()) >= altura(atual.getDir().getEsq())) {
                atual = rotacaoEsquerda(atual);

            } else {
                atual = duplaRotacaoDireitaEsquerda(atual);
            }
        }

        if (atual.getPai() != null) {
            verificarBalanceamento(atual.getPai());
        } else {
            this.raiz = atual;
        }
    }

    public void remover(int k) {
        removerAVL(this.raiz, k);
    }

    public void removerAVL(No atual, int k) {
        if (atual == null) {
            return;

        } else {

            if (atual.getId() > k) {
                removerAVL(atual.getEsq(), k);

            } else if (atual.getId() < k) {
                removerAVL(atual.getDir(), k);

            } else if (atual.getId() == k) {
                removerNoEncontrado(atual);
            }
        }
    }

    public void removerNoEncontrado(No aRemover) {
        No r;

        if (aRemover.getEsq() == null || aRemover.getDir() == null) {

            if (aRemover.getPai() == null) {
                this.raiz = null;
                aRemover = null;
                return;
            }
            r = aRemover;

        } else {
            r = sucessor(aRemover);
            aRemover.setId(r.getId());
        }

        No p;
        if (r.getEsq() != null) {
            p = r.getEsq();
        } else {
            p = r.getDir();
        }

        if (p != null) {
            p.setPai(r.getPai());
        }

        if (r.getPai() == null) {
            this.raiz = p;
        } else {
            if (r == r.getPai().getEsq()) {
                r.getPai().setEsq(p);
            } else {
                r.getPai().setDir(p);
            }
            verificarBalanceamento(r.getPai());
        }
        r = null;
    }

    public No rotacaoEsquerda(No inicial) {

        No direita = inicial.getDir();
        direita.setPai(inicial.getPai());

        inicial.setDir(direita.getEsq());

        if (inicial.getDir() != null) {
            inicial.getDir().setPai(inicial);
        }

        direita.setEsq(inicial);
        inicial.setPai(direita);

        if (direita.getPai() != null) {

            if (direita.getPai().getDir() == inicial) {
                direita.getPai().setDir(direita);

            } else if (direita.getPai().getEsq() == inicial) {
                direita.getPai().setEsq(direita);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(direita);

        return direita;
    }

    public No rotacaoDireita(No inicial) {

        No esquerda = inicial.getEsq();
        esquerda.setPai(inicial.getPai());

        inicial.setEsq(esquerda.getDir());

        if (inicial.getEsq() != null) {
            inicial.getEsq().setPai(inicial);
        }

        esquerda.setDir(inicial);
        inicial.setPai(esquerda);

        if (esquerda.getPai() != null) {

            if (esquerda.getPai().getDir() == inicial) {
                esquerda.getPai().setDir(esquerda);

            } else if (esquerda.getPai().getEsq() == inicial) {
                esquerda.getPai().setEsq(esquerda);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(esquerda);

        return esquerda;
    }

    public No duplaRotacaoEsquerdaDireita(No inicial) {
        inicial.setEsq(rotacaoEsquerda(inicial.getEsq()));
        return rotacaoDireita(inicial);
    }

    public No duplaRotacaoDireitaEsquerda(No inicial) {
        inicial.setDir(rotacaoDireita(inicial.getDir()));
        return rotacaoEsquerda(inicial);
    }

    public No sucessor(No q) {
        if (q.getDir() != null) {
            No r = q.getDir();
            while (r.getEsq() != null) {
                r = r.getEsq();
            }
            return r;
        } else {
            No p = q.getPai();
            while (p != null && q == p.getDir()) {
                q = p;
                p = q.getPai();
            }
            return p;
        }
    }

    private int altura(No atual) {
        if (atual == null) {
            return -1;
        }

        if (atual.getEsq() == null && atual.getDir() == null) {
            return 0;

        } else if (atual.getEsq() == null) {
            return 1 + altura(atual.getDir());

        } else if (atual.getDir() == null) {
            return 1 + altura(atual.getEsq());

        } else {
            return 1 + Math.max(altura(atual.getEsq()), altura(atual.getDir()));
        }
    }

    private void setBalanceamento(No no) {
        no.setBalanceamento(altura(no.getDir()) - altura(no.getEsq()));
    }

    final protected ArrayList<No> inorder() {
        ArrayList<No> ret = new ArrayList<No>();
        inorder(raiz, ret);
        return ret;
    }

    final protected void inorder(No no, ArrayList<No> lista) {
        if (no == null) {
            return;
        }
        inorder(no.getEsq(), lista);
        lista.add(no);
        inorder(no.getDir(), lista);
    }
}

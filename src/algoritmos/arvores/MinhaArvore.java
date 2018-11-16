package algoritmos.arvores;

import dados.Relatorio;
import java.util.ArrayList;

public class MinhaArvore {

    private No raiz;

    public MinhaArvore() {
        this.raiz = null;
    }

    public void inserir(int id, Chave chave, Relatorio relatorio) {
        if (raiz == null) {
            this.raiz = new No(id, chave);
            relatorio.incrementaInteracao();
        } else {
            No n = new No(id, chave);
            inserirAVL(this.raiz, n, relatorio);
            relatorio.incrementaInteracao();
        }
    }

    private void inserirAVL(No aComparar, No aInserir, Relatorio relatorio) {

        if (aComparar == null) {
            relatorio.incrementaInteracao();
            this.raiz = aInserir;

        } else {
            relatorio.incrementaInteracao();
            if (aInserir.getId() < aComparar.getId()) {
                relatorio.incrementaInteracao();
                if (aComparar.getEsq() == null) {
                    relatorio.incrementaInteracao();
                    aComparar.setEsq(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento(aComparar, relatorio);
                } else {
                    relatorio.incrementaInteracao();
                    inserirAVL(aComparar.getEsq(), aInserir, relatorio);
                }

            } else if (aInserir.getId() > aComparar.getId()) {
                relatorio.incrementaInteracao();
                if (aComparar.getDir() == null) {
                    aComparar.setDir(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento(aComparar, relatorio);
                    relatorio.incrementaInteracao();
                } else {
                    inserirAVL(aComparar.getDir(), aInserir, relatorio);
                    relatorio.incrementaInteracao();
                }

            } else {
                relatorio.incrementaInteracao();
                // O nó já existe
            }
        }
    }

    public void verificarBalanceamento(No atual, Relatorio relatorio) {
        setBalanceamento(atual, relatorio);
        int balanceamento = atual.getBalanceamento();

        if (balanceamento == -2) {
            relatorio.incrementaInteracao();
            if (altura(atual.getEsq().getEsq(), relatorio) >= altura(atual.getEsq().getDir(), relatorio)) {
                atual = rotacaoDireita(atual, relatorio);
                relatorio.incrementaInteracao();

            } else {
                atual = duplaRotacaoEsquerdaDireita(atual, relatorio);
                relatorio.incrementaInteracao();
            }

        } else if (balanceamento == 2) {
            relatorio.incrementaInteracao();
            if (altura(atual.getDir().getDir(), relatorio) >= altura(atual.getDir().getEsq(), relatorio)) {
                atual = rotacaoEsquerda(atual, relatorio);
                relatorio.incrementaInteracao();
            } else {
                atual = duplaRotacaoDireitaEsquerda(atual, relatorio);
                relatorio.incrementaInteracao();
            }
        }

        if (atual.getPai() != null) {
            relatorio.incrementaInteracao();
            verificarBalanceamento(atual.getPai(), relatorio);
        } else {
            relatorio.incrementaInteracao();
            this.raiz = atual;
        }
    }

    public void remover(int k, Relatorio relatorio) {
        removerAVL(this.raiz, k, relatorio);
    }

    public void removerAVL(No atual, int k, Relatorio relatorio) {
        if (atual == null) {
            relatorio.incrementaInteracao();
            return;

        } else {
            relatorio.incrementaInteracao();
            if (atual.getId() > k) {
                relatorio.incrementaInteracao();
                removerAVL(atual.getEsq(), k, relatorio);

            } else if (atual.getId() < k) {
                relatorio.incrementaInteracao();
                removerAVL(atual.getDir(), k, relatorio);

            } else if (atual.getId() == k) {
                relatorio.incrementaInteracao();
                removerNoEncontrado(atual, relatorio);
            }
        }
    }

    public void removerNoEncontrado(No aRemover, Relatorio relatorio) {
        No r;

        if (aRemover.getEsq() == null || aRemover.getDir() == null) {
            relatorio.incrementaInteracao();
            if (aRemover.getPai() == null) {
                relatorio.incrementaInteracao();
                this.raiz = null;
                aRemover = null;
                return;
            }
            r = aRemover;

        } else {
            relatorio.incrementaInteracao();
            r = sucessor(aRemover, relatorio);
            aRemover.setId(r.getId());
        }

        No p;
        if (r.getEsq() != null) {
            relatorio.incrementaInteracao();
            p = r.getEsq();
        } else {
            relatorio.incrementaInteracao();
            p = r.getDir();
        }

        if (p != null) {
            relatorio.incrementaInteracao();
            p.setPai(r.getPai());
        }

        if (r.getPai() == null) {
            relatorio.incrementaInteracao();
            this.raiz = p;
        } else {
            relatorio.incrementaInteracao();
            if (r == r.getPai().getEsq()) {
                relatorio.incrementaInteracao();
                r.getPai().setEsq(p);
            } else {
                relatorio.incrementaInteracao();
                r.getPai().setDir(p);
            }
            verificarBalanceamento(r.getPai(), relatorio);
        }
        r = null;
    }

    public No rotacaoEsquerda(No inicial, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        No direita = inicial.getDir();
        direita.setPai(inicial.getPai());

        inicial.setDir(direita.getEsq());

        if (inicial.getDir() != null) {
            inicial.getDir().setPai(inicial);
            relatorio.incrementaInteracao();
        }

        direita.setEsq(inicial);
        inicial.setPai(direita);

        if (direita.getPai() != null) {
            relatorio.incrementaInteracao();
            if (direita.getPai().getDir() == inicial) {
                direita.getPai().setDir(direita);
                relatorio.incrementaInteracao();
            } else if (direita.getPai().getEsq() == inicial) {
                direita.getPai().setEsq(direita);
                relatorio.incrementaInteracao();
            }
        }

        setBalanceamento(inicial, relatorio);
        setBalanceamento(direita, relatorio);

        return direita;
    }

    public No rotacaoDireita(No inicial, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        No esquerda = inicial.getEsq();
        esquerda.setPai(inicial.getPai());

        inicial.setEsq(esquerda.getDir());

        if (inicial.getEsq() != null) {
            inicial.getEsq().setPai(inicial);
            relatorio.incrementaInteracao();
        }

        esquerda.setDir(inicial);
        inicial.setPai(esquerda);

        if (esquerda.getPai() != null) {
            relatorio.incrementaInteracao();
            if (esquerda.getPai().getDir() == inicial) {
                esquerda.getPai().setDir(esquerda);
                relatorio.incrementaInteracao();
            } else if (esquerda.getPai().getEsq() == inicial) {
                esquerda.getPai().setEsq(esquerda);
                relatorio.incrementaInteracao();
            }
        }

        setBalanceamento(inicial, relatorio);
        setBalanceamento(esquerda, relatorio);

        return esquerda;
    }

    public No duplaRotacaoEsquerdaDireita(No inicial, Relatorio relatorio) {
        inicial.setEsq(rotacaoEsquerda(inicial.getEsq(), relatorio));
        return rotacaoDireita(inicial, relatorio);
    }

    public No duplaRotacaoDireitaEsquerda(No inicial, Relatorio relatorio) {
        inicial.setDir(rotacaoDireita(inicial.getDir(), relatorio));
        return rotacaoEsquerda(inicial, relatorio);
    }

    public No sucessor(No q, Relatorio relatorio) {
        if (q.getDir() != null) {
            relatorio.incrementaInteracao();
            No r = q.getDir();
            relatorio.incrementaTrocaColisaoCopia();
            while (r.getEsq() != null) {
                r = r.getEsq();
                relatorio.incrementaInteracao();
                relatorio.incrementaTrocaColisaoCopia();
            }
            return r;
        } else {
            relatorio.incrementaInteracao();
            No p = q.getPai();
            relatorio.incrementaTrocaColisaoCopia();
            while (p != null && q == p.getDir()) {
                q = p;
                p = q.getPai();
                relatorio.incrementaTrocaColisaoCopia();
                relatorio.incrementaTrocaColisaoCopia();
                relatorio.incrementaInteracao();
            }
            return p;
        }
    }

    private int altura(No atual, Relatorio relatorio) {
        if (atual == null) {
            relatorio.incrementaInteracao();
            return -1;
        }

        if (atual.getEsq() == null && atual.getDir() == null) {
            relatorio.incrementaInteracao();
            return 0;

        } else if (atual.getEsq() == null) {
            relatorio.incrementaInteracao();
            return 1 + altura(atual.getDir(), relatorio);

        } else if (atual.getDir() == null) {
            relatorio.incrementaInteracao();
            return 1 + altura(atual.getEsq(), relatorio);

        } else {
            relatorio.incrementaInteracao();
            return 1 + Math.max(altura(atual.getEsq(), relatorio), altura(atual.getDir(), relatorio));
        }
    }

    private void setBalanceamento(No no, Relatorio relatorio) {
        no.setBalanceamento(altura(no.getDir(), relatorio) - altura(no.getEsq(), relatorio));
    }

    final protected ArrayList<No> emOrdem(Relatorio relatorio) {
        ArrayList<No> ret = new ArrayList<No>();
        emOrdem(raiz, ret, relatorio);
        return ret;
    }

    final protected void emOrdem(No no, ArrayList<No> lista, Relatorio relatorio) {
        if (no == null) {
            relatorio.incrementaInteracao();
            return;

        }
        emOrdem(no.getEsq(), lista, relatorio);
        lista.add(no);
        emOrdem(no.getDir(), lista, relatorio);
    }
    
    public boolean busca(int k,Relatorio relatorio){
        return buscaAVL(this.raiz,k,relatorio);
    }
    
    public boolean buscaAVL(No atual,int k,Relatorio relatorio){
        if (atual == null) {
            relatorio.incrementaInteracao();
            return false;

        } else {
            relatorio.incrementaInteracao();
            if (atual.getId() > k) {
                relatorio.incrementaInteracao();
                buscaAVL(atual.getEsq(), k, relatorio);

            } else if (atual.getId() < k) {
                relatorio.incrementaInteracao();
                buscaAVL(atual.getDir(), k, relatorio);

            } else if (atual.getId() == k) {
                relatorio.incrementaInteracao();
                return true;
            }
        }
        return false;
    }
}

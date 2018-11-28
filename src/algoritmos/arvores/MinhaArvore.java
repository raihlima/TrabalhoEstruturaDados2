/*
 * To change this license header, choose License Headers in Project Propediries.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.arvores;

/**
 *
 * 
 */
import dados.Relatorio;
import java.util.ArrayList;

public class MinhaArvore {

    private No raiz;

    /**
     * Funcao que contem o construtor
     */
    public MinhaArvore() {
        this.raiz = null;
    }

    /**
     * Esta funcao cria um novo No e tenta inserir usando a funcao insereAVL
     *
     * @param id Numero da linha do arquivo de entrada onde se encontra o dado
     * @param chave O dado que sera guardado no no
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    public void inserir(int id, Chave chave, Relatorio relatorio) {
        if (raiz == null) {
            this.raiz = new No(id, chave);
            relatorio.incrementaInteracao();
        } else {
            No no = new No(id, chave);
            inserirAVL(this.raiz, no, relatorio);
            relatorio.incrementaInteracao();
        }
    }

    /**
     * Esta funcao insere um novo No na arvore
     *
     * @param aComparar No a ser comparado para analisar onde ocorrera a
     * insercao
     * @param aInserir No a ser inserido
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    private void inserirAVL(No aComparar, No aInserir, Relatorio relatorio) {

        if (aComparar == null) {
            relatorio.incrementaInteracao();
            this.raiz = aInserir;
            relatorio.incrementaTrocaColisaoCopia();

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

    /**
     * Esta funcao verifica se e necessario fazer balanceamento e chama as
     * funcoes de balanceamento caso seja necessario
     *
     * @param atual No onde a busca parou anteriormente
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    public void verificarBalanceamento(No atual, Relatorio relatorio) {
        setBalanceamento(atual, relatorio);
        int balanceamento = atual.getBalanceamento();

        if (balanceamento < -4) {
            relatorio.incrementaInteracao();
            if (altura(atual.getEsq().getEsq(), relatorio) >= altura(atual.getEsq().getDir(), relatorio)) {
                atual = rotacaoDireita(atual, relatorio);
                relatorio.incrementaTrocaColisaoCopia();
                relatorio.incrementaInteracao();

            } else {
                atual = duplaRotacaoEsquerdaDireita(atual, relatorio);
                relatorio.incrementaTrocaColisaoCopia();
                relatorio.incrementaInteracao();
            }

        } else if (balanceamento > 4) {
            relatorio.incrementaInteracao();
            if (altura(atual.getDir().getDir(), relatorio) >= altura(atual.getDir().getEsq(), relatorio)) {
                atual = rotacaoEsquerda(atual, relatorio);
                relatorio.incrementaTrocaColisaoCopia();
                relatorio.incrementaInteracao();
            } else {
                atual = duplaRotacaoDireitaEsquerda(atual, relatorio);
                relatorio.incrementaTrocaColisaoCopia();
                relatorio.incrementaInteracao();
            }
        }

        if (atual.getPai() != null) {
            relatorio.incrementaInteracao();
            verificarBalanceamento(atual.getPai(), relatorio);
        } else {
            relatorio.incrementaInteracao();
            this.raiz = atual;
            relatorio.incrementaTrocaColisaoCopia();
        }
    }

    /**
     * Essa funcao chama a removerAVL para remocao de uma chave da arvore
     *
     * @param id A chave a ser removida da arvore
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    public void remover(int id, Relatorio relatorio) {
        removerAVL(this.raiz, id, relatorio);
    }

    /**
     * Essa funcao auxilia na remocao de um no da arvore
     *
     * @param atual No onde a busca parou anteriormente
     * @param id A chave a ser removida da arvore
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    public void removerAVL(No atual, int id, Relatorio relatorio) {
        if (atual == null) {
            relatorio.incrementaInteracao();
            return;

        } else {
            relatorio.incrementaInteracao();
            if (atual.getId() > id) {
                relatorio.incrementaInteracao();
                removerAVL(atual.getEsq(), id, relatorio);

            } else if (atual.getId() < id) {
                relatorio.incrementaInteracao();
                removerAVL(atual.getDir(), id, relatorio);

            } else if (atual.getId() == id) {
                relatorio.incrementaInteracao();
                removerNoEncontrado(atual, relatorio);
            }
        }
    }

    /**
     * Essa funcao remove um no da arvore em questao
     *
     * @param aRemover No a remover da arvore
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    public void removerNoEncontrado(No aRemover, Relatorio relatorio) {
        No no;

        if (aRemover.getEsq() == null || aRemover.getDir() == null) {
            relatorio.incrementaInteracao();
            if (aRemover.getPai() == null) {
                relatorio.incrementaInteracao();
                this.raiz = null;
                aRemover = null;
                return;
            }
            no = aRemover;
            relatorio.incrementaTrocaColisaoCopia();

        } else {
            relatorio.incrementaInteracao();
            no = sucessor(aRemover, relatorio);
            relatorio.incrementaTrocaColisaoCopia();
            aRemover.setId(no.getId());
        }

        No aux;
        if (no.getEsq() != null) {
            relatorio.incrementaInteracao();
            aux = no.getEsq();
            relatorio.incrementaTrocaColisaoCopia();
        } else {
            relatorio.incrementaInteracao();
            aux = no.getDir();
            relatorio.incrementaTrocaColisaoCopia();
        }

        if (aux != null) {
            relatorio.incrementaInteracao();
            aux.setPai(no.getPai());
        }

        if (no.getPai() == null) {
            relatorio.incrementaInteracao();
            this.raiz = aux;
            relatorio.incrementaTrocaColisaoCopia();
        } else {
            relatorio.incrementaInteracao();
            if (no == no.getPai().getEsq()) {
                relatorio.incrementaInteracao();
                no.getPai().setEsq(aux);
            } else {
                relatorio.incrementaInteracao();
                no.getPai().setDir(aux);
            }
            verificarBalanceamento(no.getPai(), relatorio);
        }
        no = null;
    }

    /**
     * Esta funcao faz uma rotacao pela esquerda
     *
     * @param inicial No onde a rotacao sera iniciada
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     * @return Novo No da direita
     */
    public No rotacaoEsquerda(No inicial, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        No direita = inicial.getDir();
        relatorio.incrementaTrocaColisaoCopia();
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

    /**
     * Esta funcao faz uma rotacao pela direita
     *
     * @param inicial No onde a rotacao sera iniciada
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     * @return Novo No da esquerda
     */
    public No rotacaoDireita(No inicial, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        No esquerda = inicial.getEsq();
        relatorio.incrementaTrocaColisaoCopia();
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

    /**
     * Esta funcao auxilia na dupla rotacao da esquerda para direita
     *
     * @param inicial No onde a rotacao sera iniciada
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     * @return Chama a funcao de rotacao pela direita
     */
    public No duplaRotacaoEsquerdaDireita(No inicial, Relatorio relatorio) {
        inicial.setEsq(rotacaoEsquerda(inicial.getEsq(), relatorio));
        return rotacaoDireita(inicial, relatorio);
    }

    /**
     * Esta funcao auxilia na dupla rotacao da direita para esquerda
     *
     * @param inicial o onde a rotacao sera iniciada
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     * @return
     */
    public No duplaRotacaoDireitaEsquerda(No inicial, Relatorio relatorio) {
        inicial.setDir(rotacaoDireita(inicial.getDir(), relatorio));
        return rotacaoEsquerda(inicial, relatorio);
    }

    /**
     * Esta funcao retorna o no sucessor de um dado no
     *
     * @param atual No atual
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     * @return No a direita ou esquerda ou pai
     */
    public No sucessor(No atual, Relatorio relatorio) {
        if (atual.getDir() != null) {
            relatorio.incrementaInteracao();
            No no = atual.getDir();
            relatorio.incrementaTrocaColisaoCopia();
            while (no.getEsq() != null) {
                no = no.getEsq();
                relatorio.incrementaInteracao();
                relatorio.incrementaTrocaColisaoCopia();
            }
            return no;
        } else {
            relatorio.incrementaInteracao();
            No aux = atual.getPai();
            relatorio.incrementaTrocaColisaoCopia();
            while (aux != null && atual == aux.getDir()) {
                atual = aux;
                relatorio.incrementaTrocaColisaoCopia();
                aux = atual.getPai();
                relatorio.incrementaTrocaColisaoCopia();
                relatorio.incrementaInteracao();
            }
            return aux;
        }
    }

    /**
     * Esta funcao calcula a altura da arvore abaixo de um No passado por
     * parametro
     *
     * @param atual No onde iniciou ou parou a busca
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     * @return A altura da arvore
     */
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

    /**
     * Essa funcao ajusta o parametro limite para o balanceamento
     *
     * @param no No onde o balanceamento comeca / continua
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    private void setBalanceamento(No no, Relatorio relatorio) {
        no.setBalanceamento(altura(no.getDir(), relatorio) - altura(no.getEsq(), relatorio));
    }

    /**
     * Essa e uma funcao auxiliar da emOrdem
     *
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     * @return A lista depois de ordenada
     */
    final protected ArrayList<No> emOrdem(Relatorio relatorio) { // em ordem
        ArrayList<No> ret = new ArrayList<No>();
        emOrdem(raiz, ret, relatorio);
        return ret;
    }

    /**
     * Essa funcao monta uma lista ordenada dos gastos
     *
     * @param no
     * @param lista
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    final protected void emOrdem(No no, ArrayList<No> lista, Relatorio relatorio) {
        if (no == null) {
            relatorio.incrementaInteracao();
            return;

        }
        emOrdem(no.getEsq(), lista, relatorio);
        lista.add(no);
        emOrdem(no.getDir(), lista, relatorio);
    }

    public boolean busca(int id, Relatorio relatorio) {
        return buscaAVL(this.raiz, id, relatorio);
    }

    /**
     * Essa funcao faz uma busca por uma chave dentro da arvore
     *
     * @param atual No onde comeca / continua a busca
     * @param id Chave a ser encontrada
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     * @return Se encontrou a chave ou nao
     */
    public boolean buscaAVL(No atual, int id, Relatorio relatorio) {
        if (atual == null) {
            relatorio.incrementaInteracao();
            return false;

        } else {
            relatorio.incrementaInteracao();
            if (atual.getId() > id) {
                relatorio.incrementaInteracao();
                buscaAVL(atual.getEsq(), id, relatorio);

            } else if (atual.getId() < id) {
                relatorio.incrementaInteracao();
                buscaAVL(atual.getDir(), id, relatorio);

            } else if (atual.getId() == id) {
                relatorio.incrementaInteracao();
                return true;
            }
        }
        return false;
    }
}

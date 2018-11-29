package algoritmos.arvores;

import dados.Relatorio;

/**
 * Implementacao da Arvore de Espalhamento
 */
public class ArvoreSplay {

    private No raiz;
    private int qtdNos = 0;

    /**
     * Metodo que contem o construtor
     */
    public ArvoreSplay() {
        raiz = null;
    }

    /**
     * Metodo que verifica se a arvore está vazia
     *
     * @return Verdadeiro ou falso
     */
    public boolean isVazio() {
        return raiz == null;
    }

    /**
     * Metodo que deleta a arvore
     */
    public void limparArvore() {
        raiz = null;
        qtdNos = 0;
    }

    /**
     * Metodo que implementa a insercao de uma chave
     *
     * @param id Identificador do No
     * @param chave Valor contido no No
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    public void inserir(int id, Chave chave, Relatorio relatorio) {
        No no = raiz;
        relatorio.incrementaTrocaColisaoCopia();
        No aux = null;
        while (no != null) {
            relatorio.incrementaInteracao();
            aux = no;
            relatorio.incrementaTrocaColisaoCopia();
            if (id > aux.getId()) {
                relatorio.incrementaInteracao();
                no = no.getDir();
                relatorio.incrementaTrocaColisaoCopia();
            } else {
                relatorio.incrementaInteracao();
                no = no.getEsq();
                relatorio.incrementaTrocaColisaoCopia();
            }
        }
        no = new No(id, chave);
        no.setPai(aux);

        if (aux == null) {
            relatorio.incrementaInteracao();
            this.raiz = no;
            relatorio.incrementaTrocaColisaoCopia();
        } else if (id > aux.getId()) {
            relatorio.incrementaInteracao();
            aux.setDir(no);
        } else {
            relatorio.incrementaInteracao();
            aux.setEsq(no);
        }
        Splay(no, relatorio);
        qtdNos++;
    }

    /**
     * Metodo que rotaciona a arvore para direita
     *
     * @param no
     * @param aux
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    private void rotacaoDireita(No no, No aux, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        if ((no == null) || (aux == null) || (aux.getEsq() != no) || (no.getPai() != aux)) {
            relatorio.incrementaInteracao();
        }
        if (aux.getPai() != null) {
            relatorio.incrementaInteracao();
            if (aux == aux.getPai().getEsq()) {
                relatorio.incrementaInteracao();
                aux.getPai().setEsq(no);
            } else {
                relatorio.incrementaInteracao();
                aux.getPai().setDir(no);
            }
        }
        if (no.getDir() != null) {
            relatorio.incrementaInteracao();
            no.getDir().setPai(aux);
        }
        no.setPai(aux.getPai());
        aux.setPai(no);
        aux.setEsq(no.getDir());
        no.setDir(aux);
    }

    /**
     * Metodo que rotaciona a arvore para esquerda
     *
     * @param no
     * @param aux
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    public void rotacaoEsquerda(No no, No aux, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        if ((no == null) || (aux == null) || (aux.getDir() != no) || (no.getPai() != aux)) {
            relatorio.incrementaInteracao();
        }
        if (aux.getPai() != null) {
            relatorio.incrementaInteracao();
            if (aux == aux.getPai().getEsq()) {
                relatorio.incrementaInteracao();
                aux.getPai().setEsq(no);
            } else {
                relatorio.incrementaInteracao();
                aux.getPai().setDir(no);
            }
        }
        if (no.getEsq() != null) {
            relatorio.incrementaInteracao();
            no.getEsq().setPai(aux);
        }
        no.setPai(aux.getPai());
        aux.setPai(no);
        aux.setDir(no.getEsq());
        no.setEsq(aux);
    }

    /**
     * Metodo que ajusta a arvore
     *
     * @param no
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    private void Splay(No no, Relatorio relatorio) {
        while (no.getPai() != null) {
            relatorio.incrementaInteracao();
            No pai = no.getPai();
            relatorio.incrementaTrocaColisaoCopia();
            No avo = pai.getPai();
            relatorio.incrementaTrocaColisaoCopia();
            if (avo == null) {
                relatorio.incrementaInteracao();
                if (no == pai.getEsq()) {
                    relatorio.incrementaInteracao();
                    rotacaoDireita(no, pai, relatorio);
                } else {
                    relatorio.incrementaInteracao();
                    rotacaoEsquerda(no, pai, relatorio);
                }
            } else {
                relatorio.incrementaInteracao();
                if (no == pai.getEsq()) {
                    relatorio.incrementaInteracao();
                    if (pai == avo.getEsq()) {
                        relatorio.incrementaInteracao();
                        rotacaoDireita(pai, avo, relatorio);
                        rotacaoDireita(no, pai, relatorio);
                    } else {
                        relatorio.incrementaInteracao();
                        rotacaoDireita(no, no.getPai(), relatorio);
                        rotacaoEsquerda(no, no.getPai(), relatorio);
                    }
                } else {
                    relatorio.incrementaInteracao();
                    if (pai == avo.getEsq()) {
                        relatorio.incrementaInteracao();
                        rotacaoEsquerda(no, no.getPai(), relatorio);
                        rotacaoDireita(no, no.getPai(), relatorio);
                    } else {
                        relatorio.incrementaInteracao();
                        rotacaoEsquerda(pai, avo, relatorio);
                        rotacaoEsquerda(no, pai, relatorio);
                    }
                }
            }
        }
        raiz = no;
        relatorio.incrementaTrocaColisaoCopia();
    }

    /**
     *Metodo que remove um no da arvore
     * @param id Identificador do No
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    public void remover(int id, Relatorio relatorio) {
        No no = encontarNo(id, relatorio);
        relatorio.incrementaTrocaColisaoCopia();
        remover(no, relatorio);
    }

    /**
     * Metodo auxiliar que remove um no
     * @param no
     * @param relatorio Acesso ao relatorio para gravar os dados de analise  
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
            relatorio.incrementaTrocaColisaoCopia();
            while (min.getDir() != null) {
                relatorio.incrementaInteracao();
                min = min.getDir();
                relatorio.incrementaTrocaColisaoCopia();
            }
            min.setDir(no.getDir());
            no.getDir().setPai(min);
            no.getEsq().setPai(null);
            raiz = no.getEsq();
            relatorio.incrementaTrocaColisaoCopia();

        } else if (no.getDir() != null) {
            relatorio.incrementaInteracao();
            no.getDir().setPai(null);
            raiz = no.getDir();
            relatorio.incrementaTrocaColisaoCopia();

        } else if (no.getEsq() != null) {
            relatorio.incrementaInteracao();
            no.getEsq().setPai(null);
            raiz = no.getEsq();
            relatorio.incrementaTrocaColisaoCopia();

        } else {
            relatorio.incrementaInteracao();
            raiz = null;
        }
        no.setPai(null);
        no.setEsq(null);
        no.setDir(null);
        no = null;
        qtdNos--;
    }

    /**
     * Metodo que retorna a quantidade de nos da arvore
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     * @return quantidade de nos da arvore
     */
    public int quantidadeNos(Relatorio relatorio) {
        return qtdNos;
    }

    /**
     * Metodo que busca um determinado valor na arvore
     * @param id Identificador do No
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     * @return No encontrado
     */
    public boolean busca(int id, Relatorio relatorio) {
        return encontarNo(id, relatorio) != null;
    }

    /**
     * Metodo auxiliar que busca um determinado valor na arvore
     * @param id Identificador do No
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     * @return No encontrado
     */
    private No encontarNo(int id, Relatorio relatorio) {
        No noAnterior = null;
        No no = raiz;
        relatorio.incrementaTrocaColisaoCopia();

        while (no != null) {
            relatorio.incrementaInteracao();
            noAnterior = no;
            relatorio.incrementaTrocaColisaoCopia();
            if (id > no.getId()) {
                relatorio.incrementaInteracao();
                no = no.getDir();
                relatorio.incrementaTrocaColisaoCopia();

            } else if (id < no.getId()) {
                relatorio.incrementaInteracao();
                no = no.getEsq();
                relatorio.incrementaTrocaColisaoCopia();

            } else if (id == no.getId()) {
                relatorio.incrementaInteracao();
                Splay(no, relatorio);
                return no;
            }
        }
        if (noAnterior != null) {
            Splay(noAnterior, relatorio);
            return null;
        }
        return null;
    }

}

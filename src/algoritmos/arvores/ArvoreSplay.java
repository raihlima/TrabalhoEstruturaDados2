/*
 * To change this license header, choose License Headers in Project Propediries.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.arvores;

/**
 *
 * @author rodri
 */
import dados.Relatorio;

public class ArvoreSplay {

    private No raiz;
    private int qtdNos = 0;

    public ArvoreSplay() {
        raiz = null;
    }

    public boolean isVazio() {
        return raiz == null;
    }

    public void limparArvore() {
        raiz = null;
        qtdNos = 0;
    }

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

    public void rotacaoDireita(No no, No aux, Relatorio relatorio) {
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

    public void remover(int id, Relatorio relatorio) {
        No no = encontarNo(id, relatorio);
        relatorio.incrementaTrocaColisaoCopia();
        remover(no, relatorio);
    }

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

    public int quantidadeNos(Relatorio relatorio) {
        return qtdNos;
    }

    public boolean busca(int id, Relatorio relatorio) {
        return encontarNo(id, relatorio) != null;
    }

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

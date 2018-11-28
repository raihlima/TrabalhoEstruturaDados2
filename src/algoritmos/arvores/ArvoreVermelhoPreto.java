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

public class ArvoreVermelhoPreto {

    private final int VERMELHO = 0;
    private final int PRETO = 1;

    private final NoVermelhoPreto nulo = new NoVermelhoPreto(-1);
    private NoVermelhoPreto raiz = nulo;

    public NoVermelhoPreto busca(int id, Relatorio relatorio) {
        NoVermelhoPreto procurar = new NoVermelhoPreto(id);
        return procurar(procurar, raiz, relatorio);
    }

    private NoVermelhoPreto procurar(NoVermelhoPreto procurar, NoVermelhoPreto No, Relatorio relatorio) {
        if (raiz == nulo) {
            relatorio.incrementaInteracao();
            return null;
        }

        if (procurar.getId() < No.getId()) {
            relatorio.incrementaInteracao();
            if (No.getEsq() != nulo) {
                relatorio.incrementaInteracao();
                return procurar(procurar, No.getEsq(), relatorio);
            }

        } else if (procurar.getId() > No.getId()) {
            relatorio.incrementaInteracao();
            if (No.getDir() != nulo) {
                relatorio.incrementaInteracao();
                return procurar(procurar, No.getDir(), relatorio);
            }

        } else if (procurar.getId() == No.getId()) {
            relatorio.incrementaInteracao();
            return No;
        }

        return null;
    }

    public void inserir(int id, Chave chave, Relatorio relatorio) {
        NoVermelhoPreto no = new NoVermelhoPreto(id, chave);
        NoVermelhoPreto temp = raiz;
        relatorio.incrementaTrocaColisaoCopia();
        if (raiz == nulo) {
            relatorio.incrementaInteracao();
            raiz = no;
            relatorio.incrementaTrocaColisaoCopia();
            no.setCor(PRETO);
            no.setPai(nulo);
            raiz.setEsq(nulo);
            raiz.setDir(nulo);
        } else {
            relatorio.incrementaInteracao();
            no.setCor(VERMELHO);
            while (true) {
                relatorio.incrementaInteracao();
                if (no.getId() < temp.getId()) {
                    relatorio.incrementaInteracao();
                    if (temp.getEsq() == nulo) {
                        relatorio.incrementaInteracao();
                        temp.setEsq(no);
                        no.setPai(temp);
                        no.setEsq(nulo);  //nulo a esq do nó folha
                        no.setDir(nulo);  //nulo a dir do nó folha
                        break;
                    } else {
                        relatorio.incrementaInteracao();
                        temp = temp.getEsq();
                        relatorio.incrementaTrocaColisaoCopia();
                    }
                } else if (no.getId() >= temp.getId()) {
                    relatorio.incrementaInteracao();
                    if (temp.getDir() == nulo) {
                        relatorio.incrementaInteracao();
                        temp.setDir(no);
                        no.setPai(temp);
                        no.setEsq(nulo);  //nulo a esq do nó
                        no.setDir(nulo);  //nulo a dir do nó
                        break;
                    } else {
                        relatorio.incrementaInteracao();
                        temp = temp.getDir();
                        relatorio.incrementaTrocaColisaoCopia();
                    }
                }
            }
            balancear(no, relatorio);
        }
    }

    private void balancear(NoVermelhoPreto No, Relatorio relatorio) {
        while (No.getPai().getCor() == VERMELHO) {
            relatorio.incrementaInteracao();
            NoVermelhoPreto tio = nulo;
            if (No.getPai() == No.getPai().getPai().getEsq()) {
                relatorio.incrementaInteracao();
                tio = No.getPai().getPai().getDir();
                relatorio.incrementaTrocaColisaoCopia();

                if (tio != nulo && tio.getCor() == VERMELHO) {
                    relatorio.incrementaInteracao();
                    No.getPai().setCor(PRETO);
                    tio.setCor(PRETO);
                    No.getPai().getPai().setCor(VERMELHO);
                    No = No.getPai().getPai();
                    relatorio.incrementaTrocaColisaoCopia();
                    continue;
                }
                if (No == No.getPai().getDir()) {
                    relatorio.incrementaInteracao();
                    No = No.getPai();
                    relatorio.incrementaTrocaColisaoCopia();
                    rotacionarEsquerda(No, relatorio);
                }
                No.getPai().setCor(PRETO);
                No.getPai().getPai().setCor(VERMELHO);
                rotacionarDireita(No.getPai().getPai(), relatorio);
            } else {
                relatorio.incrementaInteracao();
                tio = No.getPai().getPai().getEsq();
                if (tio != nulo && tio.getCor() == VERMELHO) {
                    relatorio.incrementaInteracao();
                    No.getPai().setCor(PRETO);
                    tio.setCor(PRETO);
                    No.getPai().getPai().setCor(VERMELHO);
                    No = No.getPai().getPai();
                    relatorio.incrementaTrocaColisaoCopia();
                    continue;
                }
                if (No == No.getPai().getEsq()) {
                    relatorio.incrementaInteracao();
                    No = No.getPai();
                    relatorio.incrementaTrocaColisaoCopia();
                    rotacionarDireita(No, relatorio);
                }
                No.getPai().setCor(PRETO);
                No.getPai().getPai().setCor(VERMELHO);
                rotacionarEsquerda(No.getPai().getPai(), relatorio);
            }
        }
        raiz.setCor(PRETO);
    }

    private void rotacionarEsquerda(NoVermelhoPreto No, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        if (No.getPai() != nulo) {
            relatorio.incrementaInteracao();
            if (No == No.getPai().getEsq()) {
                relatorio.incrementaInteracao();
                No.getPai().setEsq(No.getDir());
            } else {
                relatorio.incrementaInteracao();
                No.getPai().setDir(No.getDir());
            }
            No.getDir().setPai(No.getPai());
            No.setPai(No.getDir());
            if (No.getDir().getEsq() != nulo) {
                relatorio.incrementaInteracao();
                No.getDir().getEsq().setPai(No);
            }
            No.setDir(No.getDir().getEsq());
            No.getPai().setEsq(No);
        } else {
            relatorio.incrementaInteracao();
            NoVermelhoPreto direita = raiz.getDir();
            relatorio.incrementaTrocaColisaoCopia();
            raiz.setDir(direita.getEsq());
            direita.getEsq().setPai(raiz);
            raiz.setPai(direita);
            direita.setEsq(raiz);
            direita.setPai(nulo);
            raiz = direita;
            relatorio.incrementaTrocaColisaoCopia();
        }
    }

    private void rotacionarDireita(NoVermelhoPreto No, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        if (No.getPai() != nulo) {
            relatorio.incrementaInteracao();
            if (No == No.getPai().getEsq()) {
                relatorio.incrementaInteracao();
                No.getPai().setEsq(No.getEsq());
            } else {
                relatorio.incrementaInteracao();
                No.getPai().setDir(No.getEsq());
            }
            No.getEsq().setPai(No.getPai());
            No.setPai(No.getEsq());
            if (No.getEsq().getDir() != nulo) {
                relatorio.incrementaInteracao();
                No.getEsq().getDir().setPai(No);
            }
            No.setEsq(No.getEsq().getDir());
            No.getPai().setDir(No);
            
        } else {
            relatorio.incrementaInteracao();
            NoVermelhoPreto esquerda = raiz.getEsq();
            relatorio.incrementaTrocaColisaoCopia();
            raiz.setEsq(raiz.getEsq().getDir());
            esquerda.getDir().setPai(raiz);
            raiz.setPai(esquerda);
            esquerda.setDir(raiz);
            esquerda.setPai(nulo);
            raiz = esquerda;
            relatorio.incrementaTrocaColisaoCopia();
        }
    }

    public void removerArvore() {
        raiz = nulo;
    }

    private void transplantar(NoVermelhoPreto alvo, NoVermelhoPreto no, Relatorio relatorio) {
        if (alvo.getPai() == nulo) {
            relatorio.incrementaInteracao();
            raiz = no;
            relatorio.incrementaTrocaColisaoCopia();
            
        } else if (alvo == alvo.getPai().getEsq()) {
            relatorio.incrementaInteracao();
            alvo.getPai().setEsq(no);
            
        } else {
            relatorio.incrementaInteracao();
            alvo.getPai().setDir(no);
        }
        no.setPai(alvo.getPai());
    }

    public boolean remover(int id, Relatorio relatorio) {
        NoVermelhoPreto no = new NoVermelhoPreto(id);
        return remover(no, relatorio);
    }

   private boolean remover(NoVermelhoPreto no, Relatorio relatorio) {
        no = procurar(no, raiz, relatorio);
        relatorio.incrementaTrocaColisaoCopia();
        if (no == null) {
            relatorio.incrementaInteracao();
            return false;
        }
        NoVermelhoPreto aux;
        NoVermelhoPreto temp = no; 
        relatorio.incrementaTrocaColisaoCopia();
        int corTemp = temp.getCor();

        if (no.getEsq() == nulo) {
            relatorio.incrementaInteracao();
            aux = no.getDir();
            relatorio.incrementaTrocaColisaoCopia();
            transplantar(no, no.getDir(), relatorio);
        } else if (no.getDir() == nulo) {
            relatorio.incrementaInteracao();
            aux = no.getEsq();
            relatorio.incrementaTrocaColisaoCopia();
            transplantar(no, no.getEsq(), relatorio);
        } else {
            relatorio.incrementaInteracao();
            temp = arvoreMinima(no.getDir(), relatorio);
            relatorio.incrementaTrocaColisaoCopia();
            corTemp = temp.getCor();
            aux = temp.getDir();
            relatorio.incrementaTrocaColisaoCopia();
            if (temp.getPai() == no) {
                relatorio.incrementaInteracao();
                aux.setPai(temp);
            } else {
                relatorio.incrementaInteracao();
                transplantar(temp, temp.getDir(), relatorio);
                temp.setDir(no.getDir());
                temp.getDir().setPai(temp);
            }
            transplantar(no, temp, relatorio);
            temp.setEsq(no.getEsq());
            temp.getEsq().setPai(temp);
            temp.setCor(no.getCor());
        }
        if (corTemp == PRETO) {
            relatorio.incrementaInteracao();
            arrumarNoRemovido(aux, relatorio);
        }
        return true;
    }

    private void arrumarNoRemovido(NoVermelhoPreto no, Relatorio relatorio) {//deleteFixup
        while (no != raiz && no.getCor() == PRETO) {
            relatorio.incrementaInteracao();
            if (no == no.getPai().getEsq()) {
                relatorio.incrementaInteracao();
                NoVermelhoPreto aux = no.getPai().getDir();
                relatorio.incrementaTrocaColisaoCopia();
                if (aux.getCor() == VERMELHO) {
                    relatorio.incrementaInteracao();
                    aux.setCor(PRETO);
                    no.getPai().setCor(VERMELHO);
                    rotacionarEsquerda(no.getPai(), relatorio);
                    aux = no.getPai().getDir();
                    relatorio.incrementaTrocaColisaoCopia();
                }
                if (aux.getEsq().getCor() == PRETO && aux.getDir().getCor() == PRETO) {
                    relatorio.incrementaInteracao();
                    aux.setCor(VERMELHO);
                    no = no.getPai();
                    relatorio.incrementaTrocaColisaoCopia();
                    continue;
                } else if (aux.getDir().getCor() == PRETO) {
                    relatorio.incrementaInteracao();
                    aux.getEsq().setCor(PRETO);
                    aux.setCor(VERMELHO);
                    rotacionarDireita(aux, relatorio);
                    aux = no.getPai().getDir();
                    relatorio.incrementaTrocaColisaoCopia();
                }
                if (aux.getDir().getCor() == VERMELHO) {
                    relatorio.incrementaInteracao();
                    aux.setCor(no.getPai().getCor());
                    no.getPai().setCor(PRETO);
                    aux.getDir().setCor(PRETO);
                    rotacionarEsquerda(no.getPai(), relatorio);
                    no = raiz;
                    relatorio.incrementaTrocaColisaoCopia();
                }
            } else {
                relatorio.incrementaInteracao();
                NoVermelhoPreto aux = no.getPai().getEsq();
                relatorio.incrementaTrocaColisaoCopia();
                if (aux.getCor() == VERMELHO) {
                    relatorio.incrementaInteracao();
                    aux.setCor(PRETO);
                    no.getPai().setCor(VERMELHO);
                    rotacionarDireita(no.getPai(), relatorio);
                    aux = no.getPai().getEsq();
                    relatorio.incrementaTrocaColisaoCopia();
                }
                if (aux.getDir().getCor() == PRETO && aux.getEsq().getCor() == PRETO) {
                    relatorio.incrementaInteracao();
                    aux.setCor(VERMELHO);
                    no = no.getPai();
                    relatorio.incrementaTrocaColisaoCopia();
                    continue;
                } else if (aux.getEsq().getCor() == PRETO) {
                    relatorio.incrementaInteracao();
                    aux.getDir().setCor(PRETO);
                    aux.setCor(VERMELHO);
                    rotacionarEsquerda(aux, relatorio);
                    aux = no.getPai().getEsq();
                    relatorio.incrementaTrocaColisaoCopia();
                }
                if (aux.getEsq().getCor() == VERMELHO) {
                    relatorio.incrementaInteracao();
                    aux.setCor(no.getPai().getCor());
                    no.getPai().setCor(PRETO);
                    aux.getEsq().setCor(PRETO);
                    rotacionarDireita(no.getPai(), relatorio);
                    no = raiz;
                    relatorio.incrementaTrocaColisaoCopia();
                }
            }
        }
        no.setCor(PRETO);
    }

    private NoVermelhoPreto arvoreMinima(NoVermelhoPreto subArvore, Relatorio relatorio) {
        while (subArvore.getEsq() != nulo) {
            relatorio.incrementaInteracao();
            subArvore = subArvore.getEsq();
            relatorio.incrementaTrocaColisaoCopia();
        }
        return subArvore;
    }

}

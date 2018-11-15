package algoritmos.arvores;

import dados.Relatorio;

public class RubroNegro {

    private final int VERMELHO = 0;
    private final int PRETO = 1;

    private final NoRubroNegro nulo = new NoRubroNegro(-1);
    private NoRubroNegro raiz = nulo;

    public void imprimirArvore(NoRubroNegro No, Relatorio relatorio) {
        if (No == nulo) {
            relatorio.incrementaInteracao();
            return;
        }
        if (No.getEsq() == null || No.getDir() == null) { //Caso nó procurado não esteja na arvore
            relatorio.incrementaInteracao();
            System.out.println("No nao encontrado.");
            return;
        }
        imprimirArvore(No.getEsq(), relatorio);
        System.out.print(((No.getCor() == VERMELHO) ? "Cor: Vermelho " : "Cor: Preto ") + "Key: " + No.getId() + " Pai: " + No.getPai().getId() + "\n");
        imprimirArvore(No.getDir(), relatorio);
    }

    public NoRubroNegro procurar(NoRubroNegro procurar, NoRubroNegro No, Relatorio relatorio) {
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
        NoRubroNegro no = new NoRubroNegro(id, chave);
        NoRubroNegro temp = raiz;
        if (raiz == nulo) {
            relatorio.incrementaInteracao();
            raiz = no;
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
                    }
                }
            }
            balancear(no, relatorio);
        }
    }

    //Takes as argument the newly inserted No
    public void balancear(NoRubroNegro No, Relatorio relatorio) {
        while (No.getPai().getCor() == VERMELHO) {
            relatorio.incrementaInteracao();
            NoRubroNegro tio = nulo;
            if (No.getPai() == No.getPai().getPai().getEsq()) {
                relatorio.incrementaInteracao();
                tio = No.getPai().getPai().getDir();

                if (tio != nulo && tio.getCor() == VERMELHO) {
                    relatorio.incrementaInteracao();
                    No.getPai().setCor(PRETO);
                    tio.setCor(PRETO);
                    No.getPai().getPai().setCor(VERMELHO);
                    No = No.getPai().getPai();
                    continue;
                }
                if (No == No.getPai().getDir()) {
                    relatorio.incrementaInteracao();
                    //Double rotation needed
                    No = No.getPai();
                    rotacionarEsquerda(No, relatorio);
                }
                No.getPai().setCor(PRETO);
                No.getPai().getPai().setCor(VERMELHO);
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation 
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
                    continue;
                }
                if (No == No.getPai().getEsq()) {
                    relatorio.incrementaInteracao();
                    //Double rotation needed
                    No = No.getPai();
                    rotacionarDireita(No, relatorio);
                }
                No.getPai().setCor(PRETO);
                No.getPai().getPai().setCor(VERMELHO);
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                rotacionarEsquerda(No.getPai().getPai(), relatorio);
            }
        }
        raiz.setCor(PRETO);
    }

    void rotacionarEsquerda(NoRubroNegro No, Relatorio relatorio) {
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
        } else {//Need to rotate raiz
            relatorio.incrementaInteracao();
            NoRubroNegro Direita = raiz.getDir();
            raiz.setDir(Direita.getEsq());
            Direita.getEsq().setPai(raiz);
            raiz.setPai(Direita);
            Direita.setEsq(raiz);
            Direita.setPai(nulo);
            raiz = Direita;
        }
    }

    void rotacionarDireita(NoRubroNegro No, Relatorio relatorio) {
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
        } else {//Need to rotate raiz
            relatorio.incrementaInteracao();
            NoRubroNegro Esquerda = raiz.getEsq();
            raiz.setEsq(raiz.getEsq().getDir());
            Esquerda.getDir().setPai(raiz);
            raiz.setPai(Esquerda);
            Esquerda.setDir(raiz);
            Esquerda.setPai(nulo);
            raiz = Esquerda;
        }
    }

    //Deletes whole tree
    void removerArvore() {
        raiz = nulo;
    }

    //Deletion Code .
    //This operation doesn't care about the new NoRubroNegro's connections
    //no previous No's getEsq() and getDir(). The caller has to take care
    //of that.
    void transplantar(NoRubroNegro alvo, NoRubroNegro no, Relatorio relatorio) {
        if (alvo.getPai() == nulo) {
            relatorio.incrementaInteracao();
            raiz = no;
        } else if (alvo == alvo.getPai().getEsq()) {
            relatorio.incrementaInteracao();
            alvo.getPai().setEsq(no);
        } else {
            relatorio.incrementaInteracao();
            alvo.getPai().setDir(no);
        }
        no.setPai(alvo.getPai());
    }

    boolean remover(NoRubroNegro z, Relatorio relatorio) {
        if ((z = procurar(z, raiz, relatorio)) == null) {
            relatorio.incrementaInteracao();
            return false;
        }
        NoRubroNegro x;
        NoRubroNegro y = z; // temporary reference y
        int corY = y.getCor();

        if (z.getEsq() == nulo) {
            relatorio.incrementaInteracao();
            x = z.getDir();
            transplantar(z, z.getDir(), relatorio);
        } else if (z.getDir() == nulo) {
            relatorio.incrementaInteracao();
            x = z.getEsq();
            transplantar(z, z.getEsq(), relatorio);
        } else {
            relatorio.incrementaInteracao();
            y = arvoreMinima(z.getDir(), relatorio);
            corY = y.getCor();
            x = y.getDir();
            if (y.getPai() == z) {
                relatorio.incrementaInteracao();
                x.setPai(y);
            } else {
                relatorio.incrementaInteracao();
                transplantar(y, y.getDir(), relatorio);
                y.setDir(z.getDir());
                y.getDir().setPai(y);
            }
            transplantar(z, y, relatorio);
            y.setEsq(z.getEsq());
            y.getEsq().setPai(y);
            y.setCor(z.getCor());
        }
        if (corY == PRETO) {
            relatorio.incrementaInteracao();
            arrumarNoRemovido(x, relatorio);
        }
        return true;
    }

    void arrumarNoRemovido(NoRubroNegro x, Relatorio relatorio) {//deleteFixup
        while (x != raiz && x.getCor() == PRETO) {
            relatorio.incrementaInteracao();
            if (x == x.getPai().getEsq()) {
                relatorio.incrementaInteracao();
                NoRubroNegro w = x.getPai().getDir();
                if (w.getCor() == VERMELHO) {
                    relatorio.incrementaInteracao();
                    w.setCor(PRETO);
                    x.getPai().setCor(VERMELHO);
                    rotacionarEsquerda(x.getPai(), relatorio);
                    w = x.getPai().getDir();
                }
                if (w.getEsq().getCor() == PRETO && w.getDir().getCor() == PRETO) {
                    relatorio.incrementaInteracao();
                    w.setCor(VERMELHO);
                    x = x.getPai();
                    continue;
                } else if (w.getDir().getCor() == PRETO) {
                    relatorio.incrementaInteracao();
                    w.getEsq().setCor(PRETO);
                    w.setCor(VERMELHO);
                    rotacionarDireita(w, relatorio);
                    w = x.getPai().getDir();
                }
                if (w.getDir().getCor() == VERMELHO) {
                    relatorio.incrementaInteracao();
                    w.setCor(x.getPai().getCor());
                    x.getPai().setCor(PRETO);
                    w.getDir().setCor(PRETO);
                    rotacionarEsquerda(x.getPai(), relatorio);
                    x = raiz;
                }
            } else {
                relatorio.incrementaInteracao();
                NoRubroNegro w = x.getPai().getEsq();
                if (w.getCor() == VERMELHO) {
                    relatorio.incrementaInteracao();
                    w.setCor(PRETO);
                    x.getPai().setCor(VERMELHO);
                    rotacionarDireita(x.getPai(), relatorio);
                    w = x.getPai().getEsq();
                }
                if (w.getDir().getCor() == PRETO && w.getEsq().getCor() == PRETO) {
                    relatorio.incrementaInteracao();
                    w.setCor(VERMELHO);
                    x = x.getPai();
                    continue;
                } else if (w.getEsq().getCor() == PRETO) {
                    relatorio.incrementaInteracao();
                    w.getDir().setCor(PRETO);
                    w.setCor(VERMELHO);
                    rotacionarEsquerda(w, relatorio);
                    w = x.getPai().getEsq();
                }
                if (w.getEsq().getCor() == VERMELHO) {
                    relatorio.incrementaInteracao();
                    w.setCor(x.getPai().getCor());
                    x.getPai().setCor(PRETO);
                    w.getEsq().setCor(PRETO);
                    rotacionarDireita(x.getPai(), relatorio);
                    x = raiz;
                }
            }
        }
        x.setCor(PRETO);
    }

    NoRubroNegro arvoreMinima(NoRubroNegro subArvore, Relatorio relatorio) {
        while (subArvore.getEsq() != nulo) {
            relatorio.incrementaInteracao();
            subArvore = subArvore.getEsq();
        }
        return subArvore;
    }

}

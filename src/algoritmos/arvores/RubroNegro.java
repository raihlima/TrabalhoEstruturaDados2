package algoritmos.arvores;

public class RubroNegro {
     
private final int VERMELHO = 0;
    private final int PRETO = 1;

    private final NoRubroNegro nulo = new NoRubroNegro(-1);
    private NoRubroNegro raiz = nulo;

    public void imprimirArvore(NoRubroNegro No) {
        if (No == nulo) {
            return;
        }
        if(No.getEsq() == null || No.getDir() == null){ //Caso nó procurado não esteja na arvore
            System.out.println("No nao encontrado.");
            return;
        }
        imprimirArvore(No.getEsq());        
        System.out.print(((No.getCor() == VERMELHO) ? "Cor: Vermelho " : "Cor: Preto ") + "Key: " + No.getId() + " Pai: " + No.getPai().getId() + "\n");
        imprimirArvore(No.getDir());
    }

    public NoRubroNegro procurar(NoRubroNegro procurar, NoRubroNegro No) {
        if (raiz == nulo) {
            return null;
        }

        if (procurar.getId() < No.getId()) {
            if (No.getEsq() != nulo) {
                return procurar(procurar, No.getEsq());
            }
        } else if (procurar.getId() > No.getId()) {
            if (No.getDir() != nulo) {
                return procurar(procurar, No.getDir());
            }
        } else if (procurar.getId() == No.getId()) {
            return No;
        }
        return null;
    }

    public void inserir(int id, Chave chave) {
        NoRubroNegro no = new NoRubroNegro(id, chave);
        NoRubroNegro temp = raiz;
        if (raiz == nulo) {
            raiz = no;
            no.setCor(PRETO);
            no.setPai(nulo);
            raiz.setEsq(nulo);
            raiz.setDir(nulo);            
        } else {
            no.setCor(VERMELHO);
            while (true) {
                if (no.getId() < temp.getId()) {
                    if (temp.getEsq() == nulo) {
                        temp.setEsq(no);
                        no.setPai(temp);
                        no.setEsq(nulo);  //nulo a esq do nó folha
                        no.setDir(nulo);  //nulo a dir do nó folha
                        break;
                    } else {
                        temp = temp.getEsq();
                    }
                } else if (no.getId() >= temp.getId()) {
                    if (temp.getDir() == nulo) {
                        temp.setDir(no);
                        no.setPai(temp);
                        no.setEsq(nulo);  //nulo a esq do nó
                        no.setDir(nulo);  //nulo a dir do nó
                        break;
                    } else {
                        temp = temp.getDir();
                    }
                }
            }
            balancear(no);
        }
    }

    //Takes as argument the newly inserted No
    public void balancear(NoRubroNegro No) {
        while (No.getPai().getCor() == VERMELHO) {
            NoRubroNegro tio = nulo;
            if (No.getPai() == No.getPai().getPai().getEsq()) {
                tio = No.getPai().getPai().getDir();

                if (tio != nulo && tio.getCor() == VERMELHO) {
                    No.getPai().setCor(PRETO);
                    tio.setCor(PRETO);
                    No.getPai().getPai().setCor(VERMELHO);
                    No = No.getPai().getPai();
                    continue;
                }
                if (No == No.getPai().getDir()) {
                    //Double rotation needed
                    No = No.getPai();
                    rotacionarEsquerda(No);
                }
                No.getPai().setCor(PRETO);
                No.getPai().getPai().setCor(VERMELHO);
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation 
                rotacionarDireita(No.getPai().getPai());
            } else {
                tio = No.getPai().getPai().getEsq();
                if (tio != nulo && tio.getCor() == VERMELHO) {
                    No.getPai().setCor(PRETO);
                    tio.setCor(PRETO);
                    No.getPai().getPai().setCor(VERMELHO);
                    No = No.getPai().getPai();
                    continue;
                }
                if (No == No.getPai().getEsq()) {
                    //Double rotation needed
                    No = No.getPai();
                    rotacionarDireita(No);
                }
                No.getPai().setCor(PRETO);
                No.getPai().getPai().setCor(VERMELHO);
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                rotacionarEsquerda(No.getPai().getPai());
            }
        }
        raiz.setCor(PRETO);
    }

    void rotacionarEsquerda(NoRubroNegro No) {
        if (No.getPai() != nulo) {
            if (No == No.getPai().getEsq()) {
                No.getPai().setEsq(No.getDir());
            } else {
                No.getPai().setDir(No.getDir());
            }
            No.getDir().setPai(No.getPai());
            No.setPai(No.getDir());
            if (No.getDir().getEsq() != nulo) {
                No.getDir().getEsq().setPai(No);
            }
            No.setDir(No.getDir().getEsq());
            No.getPai().setEsq(No);
        } else {//Need to rotate raiz
            NoRubroNegro Direita = raiz.getDir();
            raiz.setDir(Direita.getEsq());
            Direita.getEsq().setPai(raiz);
            raiz.setPai(Direita);
            Direita.setEsq(raiz);
            Direita.setPai(nulo);
            raiz = Direita;
        }
    }

    void rotacionarDireita(NoRubroNegro No) {
        if (No.getPai() != nulo) {
            if (No == No.getPai().getEsq()) {
                No.getPai().setEsq(No.getEsq());
            } else {
                No.getPai().setDir(No.getEsq());
            }

            No.getEsq().setPai(No.getPai());
            No.setPai(No.getEsq());
            if (No.getEsq().getDir() != nulo) {
                No.getEsq().getDir().setPai(No);
            }
            No.setEsq(No.getEsq().getDir());
            No.getPai().setDir(No);
        } else {//Need to rotate raiz
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
    void deletarArvore() {
        raiz = nulo;
    }

    //Deletion Code .
    //This operation doesn't care about the new NoRubroNegro's connections
    //no previous No's getEsq() and getDir(). The caller has to take care
    //of that.
    void transplantar(NoRubroNegro alvo, NoRubroNegro no) {
        if (alvo.getPai() == nulo) {
            raiz = no;
        } else if (alvo == alvo.getPai().getEsq()) {
            alvo.getPai().setEsq(no);
        } else {
            alvo.getPai().setDir(no);
        }
        no.setPai(alvo.getPai());
    }

    boolean deletar(NoRubroNegro z) {
        if ((z = procurar(z, raiz)) == null) {
            return false;
        }
        NoRubroNegro x;
        NoRubroNegro y = z; // temporary reference y
        int corY = y.getCor();

        if (z.getEsq() == nulo) {
            x = z.getDir();
            transplantar(z, z.getDir());
        } else if (z.getDir() == nulo) {
            x = z.getEsq();
            transplantar(z, z.getEsq());
        } else {
            y = arvoreMinima(z.getDir());
            corY = y.getCor();
            x = y.getDir();
            if (y.getPai() == z) {
                x.setPai(y);
            } else {
                transplantar(y, y.getDir());
                y.setDir(z.getDir());
                y.getDir().setPai(y);
            }
            transplantar(z, y);
            y.setEsq(z.getEsq());
            y.getEsq().setPai(y);
            y.setCor(z.getCor());
        }
        if (corY == PRETO) {
            arrumarNoDeletado(x);
        }
        return true;
    }

    void arrumarNoDeletado(NoRubroNegro x) {//deleteFixup
        while (x != raiz && x.getCor() == PRETO) {
            if (x == x.getPai().getEsq()) {
                NoRubroNegro w = x.getPai().getDir();
                if (w.getCor() == VERMELHO) {
                    w.setCor(PRETO);
                    x.getPai().setCor(VERMELHO);
                    rotacionarEsquerda(x.getPai());
                    w = x.getPai().getDir();
                }
                if (w.getEsq().getCor() == PRETO && w.getDir().getCor() == PRETO) {
                    w.setCor(VERMELHO);
                    x = x.getPai();
                    continue;
                } else if (w.getDir().getCor() == PRETO) {
                    w.getEsq().setCor(PRETO);
                    w.setCor(VERMELHO);
                    rotacionarDireita(w);
                    w = x.getPai().getDir();
                }
                if (w.getDir().getCor() == VERMELHO) {
                    w.setCor(x.getPai().getCor());
                    x.getPai().setCor(PRETO);
                    w.getDir().setCor(PRETO);
                    rotacionarEsquerda(x.getPai());
                    x = raiz;
                }
            } else {
                NoRubroNegro w = x.getPai().getEsq();
                if (w.getCor() == VERMELHO) {
                    w.setCor(PRETO);
                    x.getPai().setCor(VERMELHO);
                    rotacionarDireita(x.getPai());
                    w = x.getPai().getEsq();
                }
                if (w.getDir().getCor() == PRETO && w.getEsq().getCor() == PRETO) {
                    w.setCor(VERMELHO);
                    x = x.getPai();
                    continue;
                } else if (w.getEsq().getCor() == PRETO) {
                    w.getDir().setCor(PRETO);
                    w.setCor(VERMELHO);
                    rotacionarEsquerda(w);
                    w = x.getPai().getEsq();
                }
                if (w.getEsq().getCor() == VERMELHO) {
                    w.setCor(x.getPai().getCor());
                    x.getPai().setCor(PRETO);
                    w.getEsq().setCor(PRETO);
                    rotacionarDireita(x.getPai());
                    x = raiz;
                }
            }
        }
        x.setCor(PRETO);
    }

    NoRubroNegro arvoreMinima(NoRubroNegro subArvore) {
        while (subArvore.getEsq() != nulo) {
            subArvore = subArvore.getEsq();
        }
        return subArvore;
    }
    
 
}

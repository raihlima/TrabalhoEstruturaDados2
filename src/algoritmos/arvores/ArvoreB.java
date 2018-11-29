package algoritmos.arvores;

import dados.Relatorio;
/**
 * Implementacao da arvoreB
 */
public class ArvoreB {
    private NoB raiz;
    private int ordem;
    private int nElementos;
    /**
     * Metodo que contem o construtor
     * @param ordem Tamanho do vetor 
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     */
    public ArvoreB(int ordem, Relatorio relatorio) {
        this.raiz = new NoB(ordem);
        this.ordem = ordem;
        nElementos = 0;
    }

    public int getnElementos(Relatorio relatorio) {
        return nElementos;
    }

    public void setOrdem(int ordem, Relatorio relatorio) {
        this.ordem = ordem;
    }

    public int getOrdem(Relatorio relatorio) {
        return ordem;
    }

    public NoB getRaiz(Relatorio relatorio) {
        return raiz;
    }
    /**
     * Metodo que implementa a insercao de uma chave
     * @param id Identificador do No
     * @param chave Valor contido no No
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    public void inserir(int id, Chave chave, Relatorio relatorio) {
        chave.setId(id);
        if (buscaChave(raiz, chave, relatorio) == null) { 
            relatorio.incrementaInteracao();
            if (raiz.getOrdem() == 0) {
                relatorio.incrementaInteracao();
                raiz.getChave()[0] = chave;
                relatorio.incrementaTrocaColisaoCopia();
                raiz.setOrdem(raiz.getOrdem() + 1);
            } else { 
                relatorio.incrementaInteracao();
                NoB aux = raiz;
                relatorio.incrementaTrocaColisaoCopia();
 
                if (aux.getOrdem() == ordem - 1) {
                    relatorio.incrementaInteracao();
                    NoB temp = new NoB(ordem);
                    raiz = temp;
                    relatorio.incrementaTrocaColisaoCopia();
                    temp.setFolha(false);
                    temp.setOrdem(0);
                    temp.getFilho()[0] = aux;
                    relatorio.incrementaTrocaColisaoCopia();
                    divideNo(temp, 0, aux, relatorio);
                    insereNoNaoCheio(temp, chave, relatorio);
                } else {
                    relatorio.incrementaInteracao();
                    insereNoNaoCheio(aux, chave, relatorio);
                }
            }
            nElementos++;
        }
    }

    private void divideNo(NoB noPai, int i, NoB noFilho, Relatorio relatorio) {
        int min = (int) Math.floor((ordem - 1) / 2);
        NoB aux = new NoB(ordem);
        aux.setFolha(noFilho.isFolha());
        aux.setOrdem(min);

        for (int j = 0; j < min; j++) {
            relatorio.incrementaInteracao();
            if ((ordem - 1) % 2 == 0) {
                relatorio.incrementaInteracao();              
                aux.getChave()[j] = noFilho.getChave()[j + min];
                relatorio.incrementaTrocaColisaoCopia();
            } else {
                relatorio.incrementaInteracao();
                aux.getChave()[j] = noFilho.getChave()[j + min + 1];
                relatorio.incrementaTrocaColisaoCopia();
            }
            noFilho.setOrdem(noFilho.getOrdem() - 1);
        }

        if (!noFilho.isFolha()) {
            relatorio.incrementaInteracao();
            for (int j = 0; j < min + 1; j++) {
                relatorio.incrementaInteracao();
                if ((ordem - 1) % 2 == 0) {
                    relatorio.incrementaInteracao();
                    aux.getFilho()[j] = noFilho.getFilho()[j + min];
                    relatorio.incrementaTrocaColisaoCopia();
                } else {
                    relatorio.incrementaInteracao();
                    aux.getFilho()[j] = noFilho.getFilho()[j + min + 1];
                    relatorio.incrementaTrocaColisaoCopia();
                }

            }
        }

        noFilho.setOrdem(min);
        for (int j = noPai.getOrdem(); j > i; j--) {
            relatorio.incrementaInteracao();
            noPai.getFilho()[j + 1] = noPai.getFilho()[j];
            relatorio.incrementaTrocaColisaoCopia();
        }
        noPai.getFilho()[i + 1] = aux;
        relatorio.incrementaTrocaColisaoCopia();

        for (int j = noPai.getOrdem(); j > i; j--) {
            relatorio.incrementaInteracao();
            noPai.getChave()[j] = noPai.getChave()[j - 1];
            relatorio.incrementaTrocaColisaoCopia();
        }

        if ((ordem - 1) % 2 == 0) {
            relatorio.incrementaInteracao();
            noPai.getChave()[i] = noFilho.getChave()[min - 1];
            relatorio.incrementaTrocaColisaoCopia();
            noFilho.setOrdem(noFilho.getOrdem() - 1);

        } else {
            relatorio.incrementaInteracao();
            noPai.getChave()[i] = noFilho.getChave()[min];
            relatorio.incrementaTrocaColisaoCopia();
        }
        noPai.setOrdem(noPai.getOrdem() + 1);

    }
    /**
     * Metodo que implementa a insercao de um No vazio
     * @param noInserir Objeto No criado anteriormente para ser inserido
     * @param chave Valor do No
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    private void insereNoNaoCheio(NoB noInserir, Chave chave, Relatorio relatorio) {
        int i = noInserir.getOrdem() - 1;
        if (noInserir.isFolha()) {
            relatorio.incrementaInteracao();
            while (i >= 0 && chave.getId() < noInserir.getChave()[i].getId()) {
                relatorio.incrementaInteracao();
                noInserir.getChave()[i + 1] = noInserir.getChave()[i];
                relatorio.incrementaTrocaColisaoCopia();
                i--;
            }
            i++;
            noInserir.getChave()[i] = chave;
            relatorio.incrementaTrocaColisaoCopia();
            noInserir.setOrdem(noInserir.getOrdem() + 1);

        } else {
            relatorio.incrementaInteracao();
            while ((i >= 0 && chave.getId() < noInserir.getChave()[i].getId())) {
                relatorio.incrementaInteracao();
                i--;
            }
            i++;
  
            if ((noInserir.getFilho()[i]).getOrdem() == ordem - 1) {
                relatorio.incrementaInteracao();
                divideNo(noInserir, i, noInserir.getFilho()[i], relatorio);
                if (chave.getId() > noInserir.getChave()[i].getId()) {
                    relatorio.incrementaInteracao();
                    i++;
                }
            }
            insereNoNaoCheio(noInserir.getFilho()[i], chave, relatorio);
        }

    }
    /**
     * Metodo que implementa a busca por identificador
     * @param id Identificador do No
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     * @return Chave do No buscado, caso seja encontrado
     */
    public NoB busca(int id, Relatorio relatorio) {
        Chave chave = new Chave();
        chave.setId(id);
        return buscaChave(raiz, chave, relatorio);
    }
    /**
     * Metodo auxiliar que implementa a busca por identificador
     * @param no Objeto que instancia No
     * @param chave Valor a ser buscado na arvore
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     * @return No onde a chave esta armazenada
     */
    private NoB buscaChave(NoB no, Chave chave, Relatorio relatorio) {
        int i = 1;
        while ((i <= no.getOrdem()) && (chave.getId() > no.getChave()[i - 1].getId())) {
            relatorio.incrementaInteracao();
            i++;
        }
        if ((i <= no.getOrdem()) && (chave.getId() == no.getChave()[i - 1].getId())) {
            relatorio.incrementaInteracao();
            return no;
        }
        if (no.isFolha()) {
            relatorio.incrementaInteracao();
            return null;
        } else {
            relatorio.incrementaInteracao();
            return (buscaChave(no.getFilho()[i - 1], chave, relatorio));
        }
    }
    /**
     * Metodo que implementa a remocao de um No da arvore
     * @param id Identificador do No
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    public void remover(int id, Relatorio relatorio) {
        Chave chave = new Chave();
        chave.setId(id);
        remover(chave, relatorio);
    }
    /**
     * Metodo auxiliar que implementa a remocao de um No da arvore
     * @param chave Valor armazenado a ser removido do No
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    private void remover(Chave chave, Relatorio relatorio) {
        if (buscaChave(this.raiz, chave, relatorio) != null) {
            relatorio.incrementaInteracao();
            NoB aux = buscaChave(this.raiz, chave, relatorio);
            relatorio.incrementaTrocaColisaoCopia();
            int i = 1;

            while (aux.getChave()[i - 1].getId() < chave.getId()) {
                relatorio.incrementaInteracao();
                i++;
            }

            if (aux.isFolha()) {
                relatorio.incrementaInteracao();
                for (int j = i + 1; j <= aux.getOrdem(); j++) {
                    relatorio.incrementaInteracao();
                    aux.getChave()[j - 2] = aux.getChave()[j - 1];
                    relatorio.incrementaTrocaColisaoCopia();
                }
                aux.setOrdem(aux.getOrdem() - 1);
                if (aux != this.raiz) {
                    relatorio.incrementaInteracao();
                    balanceiaFolha(aux, relatorio);
                }
            } else {
                relatorio.incrementaInteracao();
                NoB temp = antecessor(this.raiz, chave, relatorio);
                relatorio.incrementaTrocaColisaoCopia();
                Chave y = temp.getChave()[temp.getOrdem() - 1];
                temp.setOrdem(temp.getOrdem() - 1);
                aux.getChave()[i - 1] = y;
                balanceiaFolha(temp, relatorio);
            }
            nElementos--;
        }
    }
    /**
     * Metodo para balancear uma Folha
     * @param noFolha Objeto que implementa o No Folha a ser balanceado
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    private void balanceiaFolha(NoB noFolha, Relatorio relatorio) {
        if (noFolha.getOrdem() < Math.floor((ordem - 1) / 2)) {
            relatorio.incrementaInteracao();
            NoB noPai = getPai(raiz, noFolha, relatorio);
            relatorio.incrementaTrocaColisaoCopia();
            int j = 1;

            while (noPai.getFilho()[(j - 1)] != noFolha) {
                relatorio.incrementaInteracao();
                j++;
            }

            if (j == 1 || (noPai.getFilho()[(j - 2)]).getOrdem() == Math.floor((ordem - 1) / 2)) {
                relatorio.incrementaInteracao();
                if (j == noPai.getOrdem() + 1 || (noPai.getFilho()[j].getOrdem() == Math.floor((ordem - 1) / 2))) {
                    relatorio.incrementaInteracao();
                    diminuiAltura(noFolha, relatorio);
                } else {
                    relatorio.incrementaInteracao();
                    balanceiaDirEsq(noPai, j - 1, noPai.getFilho()[j], noFolha, relatorio);
                }
            } else {
                relatorio.incrementaInteracao();
                balanceiaEsqDir(noPai, j - 2, noPai.getFilho()[j - 2], noFolha, relatorio);
            }
        }
    }

    /**
     * Metodo que reduz a alrura da arvore
     * @param no Objeto do tipo No
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    private void diminuiAltura(NoB no, Relatorio relatorio) {
        int j;
        NoB aux = new NoB(ordem);
        if (no == this.raiz) {
            relatorio.incrementaInteracao();
            if (no.getOrdem() == 0) {
                relatorio.incrementaInteracao();
                this.raiz = no.getFilho()[0];
                relatorio.incrementaTrocaColisaoCopia();
                no.getFilho()[0] = null;
            }
        } else {
            relatorio.incrementaInteracao();
            int min = (int) Math.floor((ordem - 1) / 2);
            
            if (no.getOrdem() < min) {
                relatorio.incrementaInteracao();
                aux = getPai(raiz, no, relatorio);
                relatorio.incrementaTrocaColisaoCopia();
                j = 1;

                while (aux.getFilho()[j - 1] != no) {
                    relatorio.incrementaInteracao();
                    j++;
                }

                if (j > 1) {
                    relatorio.incrementaInteracao();
                    juncaoNo(getPai(raiz, no, relatorio), j - 1, relatorio);
                } else {
                    relatorio.incrementaInteracao();
                    juncaoNo(getPai(raiz, no, relatorio), j, relatorio);
                }
                diminuiAltura(getPai(raiz, no, relatorio), relatorio);
            }
        }
    }
    /**
     * Metodo que balanceia esquerda para direita
     * @param noPai Objeto que instancia o No Pai
     * @param e Valor da esquerda que indica o n-esimo filho do pai
     * @param Esq Objeto que instancia o No da esquerda
     * @param Dir Objeto que instancia o No da direita
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    private void balanceiaEsqDir(NoB noPai, int e, NoB Esq, NoB Dir, Relatorio relatorio) {
        try{
        relatorio.incrementaTrocaColisaoCopia();
        for (int i = 0; i < Dir.getOrdem(); i++) {
            relatorio.incrementaInteracao();
            Dir.getChave()[i + 1] = Dir.getChave()[i];
            relatorio.incrementaTrocaColisaoCopia();
        }

        if (!Dir.isFolha()) {
            relatorio.incrementaInteracao();
            for (int i = 0; i > Dir.getOrdem(); i++) {
                relatorio.incrementaInteracao();
                Dir.getFilho()[i + 1] = Dir.getFilho()[i];
                relatorio.incrementaTrocaColisaoCopia();
            }
        }
        Dir.setOrdem(Dir.getOrdem() + 1);
        Dir.getChave()[0] = noPai.getChave()[e];
        relatorio.incrementaTrocaColisaoCopia();
        noPai.getChave()[e] = Esq.getChave()[Esq.getOrdem() - 1];
        relatorio.incrementaTrocaColisaoCopia();
        Dir.getFilho()[0] = Esq.getFilho()[Esq.getOrdem()];
        relatorio.incrementaTrocaColisaoCopia();
        Esq.setOrdem(Esq.getOrdem() - 1);
        } catch (Exception ex){
            
        }

    }
    /**
     * Metodo que balanceia da direita para esquerda
     * @param noPai Objeto que instancia o No Pai
     * @param e Valor da esquerda que indica o n-esimo filho do pai
     * @param Esq Objeto que instancia o No da esquerda
     * @param Dir Objeto que instancia o No da direita
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    private void balanceiaDirEsq(NoB noPai, int e, NoB Dir, NoB Esq, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        Esq.setOrdem(Esq.getOrdem() + 1);
        Esq.getChave()[Esq.getOrdem() - 1] = noPai.getChave()[e];
        relatorio.incrementaTrocaColisaoCopia();
        noPai.getChave()[e] = Dir.getChave()[0];
        relatorio.incrementaTrocaColisaoCopia();
        Esq.getFilho()[Esq.getOrdem()] = Dir.getFilho()[0];
        relatorio.incrementaTrocaColisaoCopia();

        for (int j = 1; j < Dir.getOrdem(); j++) {
            relatorio.incrementaInteracao();
            Dir.getChave()[j - 1] = Dir.getChave()[j];
            relatorio.incrementaTrocaColisaoCopia();
        }

        if (!Dir.isFolha()) {
            relatorio.incrementaInteracao();
            for (int i = 1; i < Dir.getOrdem() + 1; i++) {
                relatorio.incrementaInteracao();
                Dir.getFilho()[i - 1] = Dir.getFilho()[i];
                relatorio.incrementaTrocaColisaoCopia();
            }
        }
        Dir.setOrdem(Dir.getOrdem() - 1);

    }
    /** 
     * Metodo que implementa a uniao de NOs
     * @param noPai Objeto que instancia o No da esquerda
     * @param i Posicao do filho em ocorrera a juncao
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    private void juncaoNo(NoB noPai, int i, Relatorio relatorio) {
        NoB aux = noPai.getFilho()[i - 1]; 
        relatorio.incrementaTrocaColisaoCopia();
        NoB temp = noPai.getFilho()[i];
        relatorio.incrementaTrocaColisaoCopia();
        int ordem = aux.getOrdem();
        aux.getChave()[ordem] = noPai.getChave()[i - 1];
        relatorio.incrementaTrocaColisaoCopia();

        for (int j = 1; j <= temp.getOrdem(); j++) {
            relatorio.incrementaInteracao();
            aux.getChave()[j + ordem] = temp.getChave()[j - 1];
            relatorio.incrementaTrocaColisaoCopia();
        }

        if (!temp.isFolha()) {
            relatorio.incrementaInteracao();
            for (int j = 1; j <= temp.getOrdem(); j++) {
                relatorio.incrementaInteracao();
                aux.getFilho()[j + ordem] = temp.getFilho()[j - 1];
                relatorio.incrementaTrocaColisaoCopia();
            }
        }
        aux.setOrdem(aux.getOrdem() + temp.getOrdem() + 1);
        noPai.getFilho()[i] = null;
        
        for (int j = i; j <= noPai.getOrdem() - 1; j++) {
            relatorio.incrementaInteracao();
            noPai.getChave()[j - 1] = noPai.getChave()[j];
            relatorio.incrementaTrocaColisaoCopia();
            noPai.getFilho()[j] = noPai.getFilho()[j + 1];
            relatorio.incrementaTrocaColisaoCopia();
        }

        noPai.setOrdem(noPai.getOrdem() - 1);
    }
    /**
     * 
     * @param no Objeto que instancia o No antecessor
     * @param chave Valor
     * @param relatorio Acesso ao relatorio para gravar os dados de analise
     * @return O antecessor do No parametro
     */
    private NoB antecessor(NoB no, Chave chave, Relatorio relatorio) {
        int i = 1;
        while (i <= no.getOrdem() && no.getChave()[i - 1].getId() < chave.getId()) {
            relatorio.incrementaInteracao();
            i++;
        }
        if (no.isFolha()) {
            relatorio.incrementaInteracao();
            return no;
        } else {
            relatorio.incrementaInteracao();
            return antecessor(no.getFilho()[i - 1], chave, relatorio);
        }
    }
    /**
     * Metodo que retorna o pai de um dado No
     * @param noIni No onde comeca a busca
     * @param no Objeto que instacia o No
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     * @return 
     */
    private NoB getPai(NoB noIni, NoB no, Relatorio relatorio) {
        if (this.raiz == no) {
            relatorio.incrementaInteracao();
            return null;
        }
        for (int j = 0; j <= noIni.getOrdem(); j++) {
            relatorio.incrementaInteracao();
            if (noIni.getFilho()[j] == no) {
                relatorio.incrementaInteracao();
                return noIni;
            }
            if (!noIni.getFilho()[j].isFolha()) {
                relatorio.incrementaInteracao();
                NoB aux = getPai(noIni.getFilho()[j], no, relatorio);
                relatorio.incrementaTrocaColisaoCopia();
                if (aux != null) {
                    relatorio.incrementaInteracao();
                    return aux;
                }
            }
        }
        return null;
    }

    /**
     * Apaga completamente a arvores B
     * @param no Objeto que instancia o No
     * @param ordem Novo tamando do vetor
     * @param relatorio Acesso ao relatorio para gravar os dados de analise 
     */
    public void deletarArvore(NoB no, int ordem, Relatorio relatorio) {
        this.raiz = new NoB(ordem);
        this.ordem = ordem;
        nElementos = 0;
    }
}

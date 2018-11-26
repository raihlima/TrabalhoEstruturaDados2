package algoritmos.arvores;

import dados.Relatorio;

public class ArvoreB {

    //Atributos da Classe ArvoreB
    private NoB raiz; //Atributo do NÃ³ raiz;
    private int ordem; //Ordem da Arvore-ArvoreB;
    private int nElementos; //Contador para a quantidade de elementos na arvore ArvoreB;

    //Construtor da Classe ArvoreB
    //Cria um novo nÃ³ para a raiz, seta a ordem passada como paÃ¢metro e inicializa
    //o atributo contador de numeros de elementos
    public ArvoreB(int ordem, Relatorio relatorio) {
        this.raiz = new NoB(ordem);
        this.ordem = ordem;
        nElementos = 0;
    }

    //Getters e Setters dos atributos nElementos,ordem e raiz
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

    //Metodo de InserÃ§Ã£o na ArvoreB
    //parametros: chave - chave a ser inserida
    public void inserir(int id, Chave chave, Relatorio relatorio) {
        chave.setId(id);
        //Verifica se a chave a ser inserida existe
        if (buscaChave(raiz, chave, relatorio) == null) { //sÃ³ insere se nÃ£o houver, para evitar duplicaÃ§Ã£o de chaves
            //verifica se a chave estÃ¡ vazia
            relatorio.incrementaInteracao();
            if (raiz.getOrdem() == 0) {
                relatorio.incrementaInteracao();
                raiz.getChave()[0] = chave;//seta a chave na primeira posiÃ§Ã£o da raiz
                relatorio.incrementaTrocaColisaoCopia();
                raiz.setOrdem(raiz.getOrdem() + 1);
            } else { //caso nao estaja vazia
                relatorio.incrementaInteracao();
                NoB aux = raiz;
                relatorio.incrementaTrocaColisaoCopia();
                //verifica se a raiz estÃ¡ cheia
                if (aux.getOrdem() == ordem - 1) {//hÃ¡ necessidade de dividir a raiz
                    relatorio.incrementaInteracao();
                    NoB temp = new NoB(ordem);
                    raiz = temp;
                    relatorio.incrementaTrocaColisaoCopia();
                    temp.setFolha(false);
                    temp.setOrdem(0);
                    temp.getFilho()[0] = aux;
                    relatorio.incrementaTrocaColisaoCopia();
                    divideNo(temp, 0, aux, relatorio);//divide nÃ³
                    insereNoNaoCheio(temp, chave, relatorio);//depois de dividir a raiz comeÃ§a inserindo apartir da raiz

                } else {//caso contrario comeÃ§a inserindo apartir da raiz
                    relatorio.incrementaInteracao();
                    insereNoNaoCheio(aux, chave, relatorio);
                }
            }
            nElementos++;//incrementa o numero de elemantos na arvore
        }
    }

    //MÃ©todo de divisÃ£o de nÃ³
    //ParÃ¢metros: noPai - nÃ³ Pai, noFilho - nÃ³ Filho e i - Ã­ndice i que indica que noFilho Ã© o i-Ã©simo filho de noPai.
    public void divideNo(NoB noPai, int i, NoB noFilho, Relatorio relatorio) {
        int min = (int) Math.floor((ordem - 1) / 2);
        //cria nÃ³ aux
        NoB aux = new NoB(ordem);
        aux.setFolha(noFilho.isFolha());
        aux.setOrdem(min);

        //passa as min ultimas chaves de noFilho para aux
        for (int j = 0; j < min; j++) {
            relatorio.incrementaInteracao();
            if ((ordem - 1) % 2 == 0) {
                relatorio.incrementaInteracao();
                //aux.getChave().set(j, noFilho.getChave().get(j + min));                
                aux.getChave()[j] = noFilho.getChave()[j + min];
                relatorio.incrementaTrocaColisaoCopia();
            } else {
                relatorio.incrementaInteracao();
                //aux.getChave().set(j, noFilho.getChave().get(j + min + 1));
                aux.getChave()[j] = noFilho.getChave()[j + min + 1];
                relatorio.incrementaTrocaColisaoCopia();
            }
            noFilho.setOrdem(noFilho.getOrdem() - 1);
        }

        //se noFilho nao for folha, pasa os min+1 Ãºltimos flhos de noFilho para aux
        if (!noFilho.isFolha()) {
            relatorio.incrementaInteracao();
            for (int j = 0; j < min + 1; j++) {
                relatorio.incrementaInteracao();
                if ((ordem - 1) % 2 == 0) {
                    relatorio.incrementaInteracao();
                    //aux.getFilho().set(j, noFilho.getFilho().get(j + min));
                    aux.getFilho()[j] = noFilho.getFilho()[j + min];
                    relatorio.incrementaTrocaColisaoCopia();
                } else {
                    relatorio.incrementaInteracao();
                    //aux.getFilho().set(j, noFilho.getFilho().get(j + min + 1));
                    aux.getFilho()[j] = noFilho.getFilho()[j + min + 1];
                    relatorio.incrementaTrocaColisaoCopia();
                }

            }
        }

        noFilho.setOrdem(min);//seta a nova quantidade de chaves de noFilho

        //descola os filhos de noPai uma posiÃ§Ã£o para a direita
        for (int j = noPai.getOrdem(); j > i; j--) {
            relatorio.incrementaInteracao();
            //noPai.getFilho().set(j + 1, noPai.getFilho().get(j));
            noPai.getFilho()[j + 1] = noPai.getFilho()[j];
            relatorio.incrementaTrocaColisaoCopia();
        }

        //noPai.getFilho().set(i + 1, aux);//seta aux como filho de noPai na posiÃ§Ã£o i+1
        noPai.getFilho()[i + 1] = aux;
        relatorio.incrementaTrocaColisaoCopia();

        //desloca as chaves de noPai uma posiÃ§Ã£o para a direita, para podermos subir uma chave de noFilho
        for (int j = noPai.getOrdem(); j > i; j--) {
            relatorio.incrementaInteracao();
            //noPai.getChave().set(j, noPai.getChave().get(j - 1));
            noPai.getChave()[j] = noPai.getChave()[j - 1];
            relatorio.incrementaTrocaColisaoCopia();
        }

        //"sobe" uma chave de noFilho para aux
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

        //incrementa o numero de chaves de noPai
        noPai.setOrdem(noPai.getOrdem() + 1);

    }

    //MÃ©todo para inserir uma chave em um nÃ³ nÃ£o cheio
    //PaÃ¢metros: no - nÃ³ a ser inserido, chave - chave a ser inserida NoB nÃ³ no
    public void insereNoNaoCheio(NoB noInserir, Chave chave, Relatorio relatorio) {
        int i = noInserir.getOrdem() - 1;
        //verifica se noInserir Ã© um nÃ³ folha
        if (noInserir.isFolha()) {
            relatorio.incrementaInteracao();
            //adquire a posiÃ§Ã£o correta para ser inserido a chave
            while (i >= 0 && chave.getId() < noInserir.getChave()[i].getId()) {
                relatorio.incrementaInteracao();
                noInserir.getChave()[i + 1] = noInserir.getChave()[i];
                relatorio.incrementaTrocaColisaoCopia();
                i--;
            }
            i++;
            noInserir.getChave()[i] = chave;//insere a chave na posiÃ§Ã£o i
            relatorio.incrementaTrocaColisaoCopia();
            noInserir.setOrdem(noInserir.getOrdem() + 1);

        } else {//caso noInserir nÃ£o for folha
            //adquire a posiÃ§Ã£o correta para ser inserido a chave
            relatorio.incrementaInteracao();
            while ((i >= 0 && chave.getId() < noInserir.getChave()[i].getId())) {
                relatorio.incrementaInteracao();
                i--;
            }
            i++;
            //se o filho i de noInserir estiver cheio, divide o mesmo
            if ((noInserir.getFilho()[i]).getOrdem() == ordem - 1) {
                relatorio.incrementaInteracao();
                divideNo(noInserir, i, noInserir.getFilho()[i], relatorio);
                if (chave.getId() > noInserir.getChave()[i].getId()) {
                    relatorio.incrementaInteracao();
                    i++;
                }
            }
            //insere a chave NoB filho i de noInserir
            insereNoNaoCheio(noInserir.getFilho()[i], chave, relatorio);
        }

    }

    //MÃ©todo de busca de uma chave, retorna um nÃ³ onde a chave buscada se encontra
    //ParÃ¢metros: no - nÃ³ por onde comeÃ§ar a busca, chave - chave a ser buscada
    public NoB busca(int id, Relatorio relatorio) {
        Chave chave = new Chave();
        chave.setId(id);
        return buscaChave(raiz, chave, relatorio);
    }

    public NoB buscaChave(NoB no, Chave chave, Relatorio relatorio) {
        int i = 1;
        //procura ate nao estourar o tamanho e ate o valor nao ser maior q o procurado
        while ((i <= no.getOrdem()) && (chave.getId() > no.getChave()[i - 1].getId())) {
            relatorio.incrementaInteracao();
            i++;
        }
        //se o tamanho nao tiver excedido e for o valor procurado..
        if ((i <= no.getOrdem()) && (chave.getId() == no.getChave()[i - 1].getId())) {
            relatorio.incrementaInteracao();
            return no;
        }
        //se nao foi Ã© igual, entao foi o tamanho q excedeu. ai procura NoB filho se nao for folha
        if (no.isFolha()) { //se o NoB no for folha
            relatorio.incrementaInteracao();
            return null;
        } else {
            relatorio.incrementaInteracao();
            return (buscaChave(no.getFilho()[i - 1], chave, relatorio));
        }
    }

    //MÃ©todo de RemoÃ§Ã£o de uma determinada chave da arvoreB
    public void remover(int id, Relatorio relatorio) {
        Chave chave = new Chave();
        chave.setId(id);
        remover(chave, relatorio);
    }

    private void remover(Chave chave, Relatorio relatorio) {
        //verifica se a chave a ser removida existe
        if (buscaChave(this.raiz, chave, relatorio) != null) {
            relatorio.incrementaInteracao();
            //aux Ã© o nÃ³ onde se encontra chave
            NoB aux = buscaChave(this.raiz, chave, relatorio);
            relatorio.incrementaTrocaColisaoCopia();
            int i = 1;

            //adquire a posiÃ§Ã£o correta da chave em aux
            while (aux.getChave()[i - 1].getId() < chave.getId()) {
                relatorio.incrementaInteracao();
                i++;
            }

            //se aux for folha, remove ela e deve se balancear aux
            if (aux.isFolha()) {
                relatorio.incrementaInteracao();
                for (int j = i + 1; j <= aux.getOrdem(); j++) {
                    relatorio.incrementaInteracao();
                    aux.getChave()[j - 2] = aux.getChave()[j - 1];//desloca chaves quando tem mais de uma
                    relatorio.incrementaTrocaColisaoCopia();
                }
                aux.setOrdem(aux.getOrdem() - 1);
                if (aux != this.raiz) {
                    relatorio.incrementaInteracao();
                    Balanceia_Folha(aux, relatorio);//Balanceia aux
                }
            } else {//caso contrÃ¡rio(aux nao Ã© folha), substitui a chave por seu antecessor e balanceia a folha onde se encontrava o ancecessor
                relatorio.incrementaInteracao();
                NoB temp = Antecessor(this.raiz, chave, relatorio);//temp eh onde se encontra o antecessor de chave
                relatorio.incrementaTrocaColisaoCopia();
                //int y = temp.getChave().get(temp.getOrdem() - 1);//y Ã© o antecessor de chave
                Chave y = temp.getChave()[temp.getOrdem() - 1];
                temp.setOrdem(temp.getOrdem() - 1);
                aux.getChave()[i - 1] = y;//substitui a chave por y
                Balanceia_Folha(temp, relatorio);//balanceia temp
            }
            nElementos--;//decrementa o numero de elementos na arvoreB
        }
    }

    //MÃ©tode de Balancear um nÃ³ folha
    //ParÃ¢metros: noFolha - nÃ³ Folha a ser balanceada
    private void Balanceia_Folha(NoB noFolha, Relatorio relatorio) {
        //verifica se noFolha estÃ¡ cheio
        if (noFolha.getOrdem() < Math.floor((ordem - 1) / 2)) {
            relatorio.incrementaInteracao();
            NoB noPai = getPai(raiz, noFolha, relatorio);//noPai Ã© o pai de noFolha
            relatorio.incrementaTrocaColisaoCopia();
            int j = 1;

            //adquire a posiÃ§Ã£o de noFolha em noPai
            while (noPai.getFilho()[(j - 1)] != noFolha) {
                relatorio.incrementaInteracao();
                j++;
            }

            //verifica se o irmÃ£o Ã  esquerda de noFolha nÃ£o tem chaves para emprestar
            if (j == 1 || (noPai.getFilho()[(j - 2)]).getOrdem() == Math.floor((ordem - 1) / 2)) {
                relatorio.incrementaInteracao();
                //verifica se o irmÃ£o Ã  direita de noFolha nÃ£o tem chaves para emprestar
                if (j == noPai.getOrdem() + 1 || (noPai.getFilho()[j].getOrdem() == Math.floor((ordem - 1) / 2))) {
                    relatorio.incrementaInteracao();
                    Diminui_Altura(noFolha, relatorio); //nenhum irmÃ£o tem chaves para emprestar eh necessario diminuir a altura
                } else {//caso contrario (tem chaves para emprestar) executa Balanceia_Dir_Esq
                    relatorio.incrementaInteracao();
                    Balanceia_Dir_Esq(noPai, j - 1, noPai.getFilho()[j], noFolha, relatorio);
                }
            } else {//caso contrario (tem chaves para emprestar) executa Balanceia_Esq_Dir
                relatorio.incrementaInteracao();
                Balanceia_Esq_Dir(noPai, j - 2, noPai.getFilho()[j - 2], noFolha, relatorio);
            }
        }
    }

    //MÃ©todo para diminuir a altura
    //ParÃ¢metros: no - nÃ³ onde vai ser diminuido a altura
    private void Diminui_Altura(NoB no, Relatorio relatorio) {
        int j;
        NoB aux = new NoB(ordem);

        //verifica se no Ã© a raiz
        if (no == this.raiz) {
            relatorio.incrementaInteracao();
            //verifica se no estÃ¡ vazio
            if (no.getOrdem() == 0) {
                relatorio.incrementaInteracao();
                this.raiz = no.getFilho()[0];//o filho o de no passa a ser raiz
                relatorio.incrementaTrocaColisaoCopia();
                no.getFilho()[0] = null; // desaloca o filho de no
            }
        } else {//caso contrario(no nao Ã© raiz)
            relatorio.incrementaInteracao();
            int min = (int) Math.floor((ordem - 1) / 2);
            //verifica se o numero de chaves de no Ã© menor que o permitido
            
            if (no.getOrdem() < min) {
                relatorio.incrementaInteracao();
                aux = getPai(raiz, no, relatorio);//aux Ã© pai de no
                relatorio.incrementaTrocaColisaoCopia();
                j = 1;

                //adquire a posicao correta do filho no em aux
                while (aux.getFilho()[j - 1] != no) {
                    relatorio.incrementaInteracao();
                    j++;
                }

                //junta os nÃ³s
                if (j > 1) {
                    relatorio.incrementaInteracao();
                    Juncao_No(getPai(raiz, no, relatorio), j - 1, relatorio);
                } else {
                    relatorio.incrementaInteracao();
                    Juncao_No(getPai(raiz, no, relatorio), j, relatorio);
                }
                Diminui_Altura(getPai(raiz, no, relatorio), relatorio);
            }
        }
    }

    //MÃ³todo de Balancear da esquerda para a direita
    //ParÃ¢metros: noPai - NÃ³ pai, e - indica que Esq Ã© o e-Ã©simo filho de noPai, Esq - NÃ³ da esquerda, Dir - NÃ³ da direita
    private void Balanceia_Esq_Dir(NoB noPai, int e, NoB Esq, NoB Dir, Relatorio relatorio) {
        try{
        relatorio.incrementaTrocaColisaoCopia();
        //Desloca as chave de Dir uma posicao para a direita
        for (int i = 0; i < Dir.getOrdem(); i++) {
            relatorio.incrementaInteracao();
            Dir.getChave()[i + 1] = Dir.getChave()[i];
            relatorio.incrementaTrocaColisaoCopia();
        }

        //Se Dir nao for folha descola seu filhos uma posicao para a direita
        if (!Dir.isFolha()) {//nao foi testado, mas teoricamente funciona
            relatorio.incrementaInteracao();
            for (int i = 0; i > Dir.getOrdem(); i++) {
                relatorio.incrementaInteracao();
                Dir.getFilho()[i + 1] = Dir.getFilho()[i];
                relatorio.incrementaTrocaColisaoCopia();
            }
        }
        Dir.setOrdem(Dir.getOrdem() + 1);//Incrementa a quantidades de chaves em Dir
        Dir.getChave()[0] = noPai.getChave()[e];//"desce" uma chave de noPai para Dir
        relatorio.incrementaTrocaColisaoCopia();
        noPai.getChave()[e] = Esq.getChave()[Esq.getOrdem() - 1];//"sobe" uma chave de Esq para noPai
        relatorio.incrementaTrocaColisaoCopia();
        Dir.getFilho()[0] = Esq.getFilho()[Esq.getOrdem()];//Seta o ultimo filho de Esq como primeiro filho de Dir
        relatorio.incrementaTrocaColisaoCopia();
        Esq.setOrdem(Esq.getOrdem() - 1);//Decrementa a quantidade de chaves em Esq
        } catch (Exception ex){
            
        }

    }

    //MÃ©todo de Balancear da direita para a esquerda
    //ParÃ¢metros: noPai - NÃ³ pai, e - indica que Dir Ã© o e-Ã©simo filho de noPai, Dir - NÃ³ da direita, Esq - NÃ³ da esquerda
    private void Balanceia_Dir_Esq(NoB noPai, int e, NoB Dir, NoB Esq, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        Esq.setOrdem(Esq.getOrdem() + 1);//Incrementa a quantidade de chaves em Esq
        Esq.getChave()[Esq.getOrdem() - 1] = noPai.getChave()[e];//"desce" uma chave de noPai para Esq
        relatorio.incrementaTrocaColisaoCopia();
        noPai.getChave()[e] = Dir.getChave()[0];//"sobe" uma chave de Dir para noPai
        relatorio.incrementaTrocaColisaoCopia();
        Esq.getFilho()[Esq.getOrdem()] = Dir.getFilho()[0];//Seta o primeiro filho de Dir como Ãºltimo filho de Esq
        relatorio.incrementaTrocaColisaoCopia();

        //descola as chaves de Dir uma posiÃ§Ã£o para a esquerda
        for (int j = 1; j < Dir.getOrdem(); j++) {
            relatorio.incrementaInteracao();
            Dir.getChave()[j - 1] = Dir.getChave()[j];
            relatorio.incrementaTrocaColisaoCopia();
        }

        //se Dir nao for folha, desloca todos os filhos de Dir uma posicao a esquerda
        if (!Dir.isFolha()) {//nao foi testado, mas teoricamente funciona
            relatorio.incrementaInteracao();
            for (int i = 1; i < Dir.getOrdem() + 1; i++) {
                relatorio.incrementaInteracao();
                Dir.getFilho()[i - 1] = Dir.getFilho()[i];
                relatorio.incrementaTrocaColisaoCopia();
            }
        }

        //descrementa a quantidade de chaves de Dir
        Dir.setOrdem(Dir.getOrdem() - 1);

    }

    //MÃ©todo para junÃ§Ã£o do nÃ³
    //ParÃ¢metros: noPai - NoB pai, i - posicao do filho de noPai onde vai ser juntado
    private void Juncao_No(NoB noPai, int i, Relatorio relatorio) {
        NoB aux = noPai.getFilho()[i - 1]; //cria aux
        relatorio.incrementaTrocaColisaoCopia();
        NoB temp = noPai.getFilho()[i];//cria temp        
        relatorio.incrementaTrocaColisaoCopia();
        int ordem = aux.getOrdem();
        aux.getChave()[ordem] = noPai.getChave()[i - 1];//"desce" uma chave de noPai para noPai
        relatorio.incrementaTrocaColisaoCopia();

        //descola as de chaves de temp para aux
        for (int j = 1; j <= temp.getOrdem(); j++) {
            relatorio.incrementaInteracao();
            aux.getChave()[j + ordem] = temp.getChave()[j - 1];
            relatorio.incrementaTrocaColisaoCopia();
        }

        //se temp nao for folha, descola seus filhos tbm
        if (!temp.isFolha()) {
            relatorio.incrementaInteracao();
            for (int j = 1; j <= temp.getOrdem(); j++) {
                relatorio.incrementaInteracao();
                aux.getFilho()[j + ordem] = temp.getFilho()[j - 1];
                relatorio.incrementaTrocaColisaoCopia();
            }
        }

        //seta a quantidades de chaves em aux
        aux.setOrdem(aux.getOrdem() + temp.getOrdem() + 1);

        noPai.getFilho()[i] = null;//desaloca o temp setando o filho de noPai que apontava pra temp pra null

        //descola os filhos e as chaves de noPai uma para a esquera, para "fechar o buraco"
        for (int j = i; j <= noPai.getOrdem() - 1; j++) {//ainda nao
            relatorio.incrementaInteracao();
            noPai.getChave()[j - 1] = noPai.getChave()[j];
            relatorio.incrementaTrocaColisaoCopia();
            noPai.getFilho()[j] = noPai.getFilho()[j + 1];
            relatorio.incrementaTrocaColisaoCopia();
        }

        //decrementa a quantidade de chaves em noPai
        noPai.setOrdem(noPai.getOrdem() - 1);
    }

    //Metodo que retorna o nÃ³ onde a chave antecessora de K se encontra
    //ParÃ¢metros: no - NÃ³ onde comeÃ§a a busca, chave - chave a ser buscada
    private NoB Antecessor(NoB no, Chave chave, Relatorio relatorio) {
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
            return Antecessor(no.getFilho()[i - 1], chave, relatorio);
        }
    }

    //Metodo que retorna o nÃ³ pai de no
    //ParÃ¢metros: noIni - NÃ³ onde comeÃ§a a busca, no - nÃ³ que deve se buscar o pai
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

    //MÃ©todo para Limpar a arvoreB.
    //ParÃ¢metros: no - NÃ³ onde se deve iniciar a varredura, ordem - Nova ordem da arvoreB
    public void LimparArvore(NoB no, int ordem, Relatorio relatorio) {

        for (int i = 0; i < no.getOrdem() + 1; i++) {
            relatorio.incrementaInteracao();
            if (!no.isFolha()) {
                relatorio.incrementaInteracao();
                LimparArvore(no.getFilho()[i], ordem, relatorio);
            }
            no.getFilho()[i] = null;
            no.setOrdem(0);
        }

        if (no == this.raiz) {
            relatorio.incrementaInteracao();
            this.raiz = new NoB(ordem);
        }
        nElementos = 0;
    }
}

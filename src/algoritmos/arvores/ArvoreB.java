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
    //parametros: k - chave a ser inserida
    public void inserir(int id, Chave k, Relatorio relatorio) {
        k.setId(id);
        //Verifica se a chave a ser inserida existe
        if (buscaChave(raiz, k, relatorio) == null) { //sÃ³ insere se nÃ£o houver, para evitar duplicaÃ§Ã£o de chaves
            //verifica se a chave estÃ¡ vazia
            relatorio.incrementaInteracao();
            if (raiz.getOrdem() == 0) {
                relatorio.incrementaInteracao();
                raiz.getChave()[0] = k;//seta a chave na primeira posiÃ§Ã£o da raiz
                relatorio.incrementaTrocaColisaoCopia();
                raiz.setOrdem(raiz.getOrdem() + 1);
            } else { //caso nao estaja vazia
                relatorio.incrementaInteracao();
                NoB r = raiz;
                relatorio.incrementaTrocaColisaoCopia();
                //verifica se a raiz estÃ¡ cheia
                if (r.getOrdem() == ordem - 1) {//hÃ¡ necessidade de dividir a raiz
                    relatorio.incrementaInteracao();
                    NoB s = new NoB(ordem);
                    raiz = s;
                    relatorio.incrementaTrocaColisaoCopia();
                    s.setFolha(false);
                    s.setOrdem(0);
                    s.getFilho()[0] = r;
                    relatorio.incrementaTrocaColisaoCopia();
                    divideNo(s, 0, r, relatorio);//divide nÃ³
                    insereNoNaoCheio(s, k, relatorio);//depois de dividir a raiz comeÃ§a inserindo apartir da raiz

                } else {//caso contrario comeÃ§a inserindo apartir da raiz
                    relatorio.incrementaInteracao();
                    insereNoNaoCheio(r, k, relatorio);
                }
            }
            nElementos++;//incrementa o numero de elemantos na arvore
        }
    }

    //MÃ©todo de divisÃ£o de nÃ³
    //ParÃ¢metros: x - nÃ³ Pai, y - nÃ³ Filho e i - Ã­ndice i que indica que y Ã© o i-Ã©simo filho de x.
    public void divideNo(NoB x, int i, NoB y, Relatorio relatorio) {
        int t = (int) Math.floor((ordem - 1) / 2);
        //cria nÃ³ z
        NoB z = new NoB(ordem);
        z.setFolha(y.isFolha());
        z.setOrdem(t);

        //passa as t ultimas chaves de y para z
        for (int j = 0; j < t; j++) {
            relatorio.incrementaInteracao();
            if ((ordem - 1) % 2 == 0) {
                relatorio.incrementaInteracao();
                //z.getChave().set(j, y.getChave().get(j + t));                
                z.getChave()[j] = y.getChave()[j + t];
                relatorio.incrementaTrocaColisaoCopia();
            } else {
                relatorio.incrementaInteracao();
                //z.getChave().set(j, y.getChave().get(j + t + 1));
                z.getChave()[j] = y.getChave()[j + t + 1];
                relatorio.incrementaTrocaColisaoCopia();
            }
            y.setOrdem(y.getOrdem() - 1);
        }

        //se y nao for folha, pasa os t+1 Ãºltimos flhos de y para z
        if (!y.isFolha()) {
            relatorio.incrementaInteracao();
            for (int j = 0; j < t + 1; j++) {
                relatorio.incrementaInteracao();
                if ((ordem - 1) % 2 == 0) {
                    relatorio.incrementaInteracao();
                    //z.getFilho().set(j, y.getFilho().get(j + t));
                    z.getFilho()[j] = y.getFilho()[j + t];
                    relatorio.incrementaTrocaColisaoCopia();
                } else {
                    relatorio.incrementaInteracao();
                    //z.getFilho().set(j, y.getFilho().get(j + t + 1));
                    z.getFilho()[j] = y.getFilho()[j + t + 1];
                    relatorio.incrementaTrocaColisaoCopia();
                }

            }
        }

        y.setOrdem(t);//seta a nova quantidade de chaves de y

        //descola os filhos de x uma posiÃ§Ã£o para a direita
        for (int j = x.getOrdem(); j > i; j--) {
            relatorio.incrementaInteracao();
            //x.getFilho().set(j + 1, x.getFilho().get(j));
            x.getFilho()[j + 1] = x.getFilho()[j];
            relatorio.incrementaTrocaColisaoCopia();
        }

        //x.getFilho().set(i + 1, z);//seta z como filho de x na posiÃ§Ã£o i+1
        x.getFilho()[i + 1] = z;
        relatorio.incrementaTrocaColisaoCopia();

        //desloca as chaves de x uma posiÃ§Ã£o para a direita, para podermos subir uma chave de y
        for (int j = x.getOrdem(); j > i; j--) {
            relatorio.incrementaInteracao();
            //x.getChave().set(j, x.getChave().get(j - 1));
            x.getChave()[j] = x.getChave()[j - 1];
            relatorio.incrementaTrocaColisaoCopia();
        }

        //"sobe" uma chave de y para z
        if ((ordem - 1) % 2 == 0) {
            relatorio.incrementaInteracao();
            x.getChave()[i] = y.getChave()[t - 1];
            relatorio.incrementaTrocaColisaoCopia();
            y.setOrdem(y.getOrdem() - 1);

        } else {
            relatorio.incrementaInteracao();
            x.getChave()[i] = y.getChave()[t];
            relatorio.incrementaTrocaColisaoCopia();
        }

        //incrementa o numero de chaves de x
        x.setOrdem(x.getOrdem() + 1);

    }

    //MÃ©todo para inserir uma chave em um nÃ³ nÃ£o cheio
    //PaÃ¢metros: x - nÃ³ a ser inserido, k - chave a ser inserida NoB nÃ³ x
    public void insereNoNaoCheio(NoB x, Chave k, Relatorio relatorio) {
        int i = x.getOrdem() - 1;
        //verifica se x Ã© um nÃ³ folha
        if (x.isFolha()) {
            relatorio.incrementaInteracao();
            //adquire a posiÃ§Ã£o correta para ser inserido a chave
            while (i >= 0 && k.getId() < x.getChave()[i].getId()) {
                relatorio.incrementaInteracao();
                x.getChave()[i + 1] = x.getChave()[i];
                relatorio.incrementaTrocaColisaoCopia();
                i--;
            }
            i++;
            x.getChave()[i] = k;//insere a chave na posiÃ§Ã£o i
            relatorio.incrementaTrocaColisaoCopia();
            x.setOrdem(x.getOrdem() + 1);

        } else {//caso x nÃ£o for folha
            //adquire a posiÃ§Ã£o correta para ser inserido a chave
            relatorio.incrementaInteracao();
            while ((i >= 0 && k.getId() < x.getChave()[i].getId())) {
                relatorio.incrementaInteracao();
                i--;
            }
            i++;
            //se o filho i de x estiver cheio, divide o mesmo
            if ((x.getFilho()[i]).getOrdem() == ordem - 1) {
                relatorio.incrementaInteracao();
                divideNo(x, i, x.getFilho()[i], relatorio);
                if (k.getId() > x.getChave()[i].getId()) {
                    relatorio.incrementaInteracao();
                    i++;
                }
            }
            //insere a chave NoB filho i de x
            insereNoNaoCheio(x.getFilho()[i], k, relatorio);
        }

    }

    //MÃ©todo de busca de uma chave, retorna um nÃ³ onde a chave buscada se encontra
    //ParÃ¢metros: X - nÃ³ por onde comeÃ§ar a busca, k - chave a ser buscada
    public NoB busca(int id, Relatorio relatorio) {
        Chave chave = new Chave();
        chave.setId(id);
        return buscaChave(raiz, chave, relatorio);
    }

    public NoB buscaChave(NoB X, Chave k, Relatorio relatorio) {
        int i = 1;
        //procura ate nao estourar o tamanho e ate o valor nao ser maior q o procurado
        while ((i <= X.getOrdem()) && (k.getId() > X.getChave()[i - 1].getId())) {
            relatorio.incrementaInteracao();
            i++;
        }
        //se o tamanho nao tiver excedido e for o valor procurado..
        if ((i <= X.getOrdem()) && (k == X.getChave()[i - 1])) {
            relatorio.incrementaInteracao();
            return X;
        }
        //se nao foi Ã© igual, entao foi o tamanho q excedeu. ai procura NoB filho se nao for folha
        if (X.isFolha()) { //se o NoB X for folha
            relatorio.incrementaInteracao();
            return null;
        } else {
            relatorio.incrementaInteracao();
            return (buscaChave(X.getFilho()[i - 1], k, relatorio));
        }
    }

    //MÃ©todo de RemoÃ§Ã£o de uma determinada chave da arvoreB
    public void remover(int id, Relatorio relatorio) {
        Chave chave = new Chave();
        chave.setId(id);
        remover(chave, relatorio);
    }

    private void remover(Chave k, Relatorio relatorio) {
        //verifica se a chave a ser removida existe
        if (buscaChave(this.raiz, k, relatorio) != null) {
            relatorio.incrementaInteracao();
            //N Ã© o nÃ³ onde se encontra k
            NoB N = buscaChave(this.raiz, k, relatorio);
            relatorio.incrementaTrocaColisaoCopia();
            int i = 1;

            //adquire a posiÃ§Ã£o correta da chave em N
            while (N.getChave()[i - 1].getId() < k.getId()) {
                relatorio.incrementaInteracao();
                i++;
            }

            //se N for folha, remove ela e deve se balancear N
            if (N.isFolha()) {
                relatorio.incrementaInteracao();
                for (int j = i + 1; j <= N.getOrdem(); j++) {
                    relatorio.incrementaInteracao();
                    N.getChave()[j - 2] = N.getChave()[j - 1];//desloca chaves quando tem mais de uma
                    relatorio.incrementaTrocaColisaoCopia();
                }
                N.setOrdem(N.getOrdem() - 1);
                if (N != this.raiz) {
                    relatorio.incrementaInteracao();
                    Balanceia_Folha(N, relatorio);//Balanceia N
                }
            } else {//caso contrÃ¡rio(N nao Ã© folha), substitui a chave por seu antecessor e balanceia a folha onde se encontrava o ancecessor
                relatorio.incrementaInteracao();
                NoB S = Antecessor(this.raiz, k, relatorio);//S eh onde se encontra o antecessor de k
                relatorio.incrementaTrocaColisaoCopia();
                //int y = S.getChave().get(S.getOrdem() - 1);//y Ã© o antecessor de k
                Chave y = S.getChave()[S.getOrdem() - 1];
                S.setOrdem(S.getOrdem() - 1);
                N.getChave()[i - 1] = y;//substitui a chave por y
                Balanceia_Folha(S, relatorio);//balanceia S
            }
            nElementos--;//decrementa o numero de elementos na arvoreB
        }
    }

    //MÃ©tode de Balancear um nÃ³ folha
    //ParÃ¢metros: F - nÃ³ Folha a ser balanceada
    private void Balanceia_Folha(NoB F, Relatorio relatorio) {
        //verifica se F estÃ¡ cheio
        if (F.getOrdem() < Math.floor((ordem - 1) / 2)) {
            relatorio.incrementaInteracao();
            NoB P = getPai(raiz, F, relatorio);//P Ã© o pai de F
            relatorio.incrementaTrocaColisaoCopia();
            int j = 1;

            //adquire a posiÃ§Ã£o de F em P
            while (P.getFilho()[(j - 1)] != F) {
                relatorio.incrementaInteracao();
                j++;
            }

            //verifica se o irmÃ£o Ã  esquerda de F nÃ£o tem chaves para emprestar
            if (j == 1 || (P.getFilho()[(j - 2)]).getOrdem() == Math.floor((ordem - 1) / 2)) {
                relatorio.incrementaInteracao();
                //verifica se o irmÃ£o Ã  direita de F nÃ£o tem chaves para emprestar
                if (j == P.getOrdem() + 1 || (P.getFilho()[j].getOrdem() == Math.floor((ordem - 1) / 2))) {
                    relatorio.incrementaInteracao();
                    Diminui_Altura(F, relatorio); //nenhum irmÃ£o tem chaves para emprestar eh necessario diminuir a altura
                } else {//caso contrario (tem chaves para emprestar) executa Balanceia_Dir_Esq
                    relatorio.incrementaInteracao();
                    Balanceia_Dir_Esq(P, j - 1, P.getFilho()[j], F, relatorio);
                }
            } else {//caso contrario (tem chaves para emprestar) executa Balanceia_Esq_Dir
                relatorio.incrementaInteracao();
                Balanceia_Esq_Dir(P, j - 2, P.getFilho()[j - 2], F, relatorio);
            }
        }
    }

    //MÃ©todo para diminuir a altura
    //ParÃ¢metros: X - nÃ³ onde vai ser diminuido a altura
    private void Diminui_Altura(NoB X, Relatorio relatorio) {
        int j;
        NoB P = new NoB(ordem);

        //verifica se X Ã© a raiz
        if (X == this.raiz) {
            relatorio.incrementaInteracao();
            //verifica se X estÃ¡ vazio
            if (X.getOrdem() == 0) {
                relatorio.incrementaInteracao();
                this.raiz = X.getFilho()[0];//o filho o de x passa a ser raiz
                relatorio.incrementaTrocaColisaoCopia();
                X.getFilho()[0] = null; // desaloca o filho de x
            }
        } else {//caso contrario(X nao Ã© raiz)
            relatorio.incrementaInteracao();
            int t = (int) Math.floor((ordem - 1) / 2);
            //verifica se o numero de chaves de X Ã© menor que o permitido
            if (X.getOrdem() < t) {
                relatorio.incrementaInteracao();
                P = getPai(raiz, X, relatorio);//P Ã© pai de X
                relatorio.incrementaTrocaColisaoCopia();
                j = 1;

                //adquire a posicao correta do filho X em P
                while (P.getFilho()[j - 1] != X) {
                    relatorio.incrementaInteracao();
                    j++;
                }

                //junta os nÃ³s
                if (j > 1) {
                    relatorio.incrementaInteracao();
                    Juncao_No(getPai(raiz, X, relatorio), j - 1, relatorio);
                } else {
                    relatorio.incrementaInteracao();
                    Juncao_No(getPai(raiz, X, relatorio), j, relatorio);
                }
                Diminui_Altura(getPai(raiz, X, relatorio), relatorio);
            }
        }
    }

    //MÃ³todo de Balancear da esquerda para a direita
    //ParÃ¢metros: P - NÃ³ pai, e - indica que Esq Ã© o e-Ã©simo filho de P, Esq - NÃ³ da esquerda, Dir - NÃ³ da direita
    private void Balanceia_Esq_Dir(NoB P, int e, NoB Esq, NoB Dir, Relatorio relatorio) {
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
        Dir.getChave()[0] = P.getChave()[e];//"desce" uma chave de P para Dir
        relatorio.incrementaTrocaColisaoCopia();
        P.getChave()[e] = Esq.getChave()[Esq.getOrdem() - 1];//"sobe" uma chave de Esq para P
        relatorio.incrementaTrocaColisaoCopia();
        Dir.getFilho()[0] = Esq.getFilho()[Esq.getOrdem()];//Seta o ultimo filho de Esq como primeiro filho de Dir
        relatorio.incrementaTrocaColisaoCopia();
        Esq.setOrdem(Esq.getOrdem() - 1);//Decrementa a quantidade de chaves em Esq

    }

    //MÃ©todo de Balancear da direita para a esquerda
    //ParÃ¢metros: P - NÃ³ pai, e - indica que Dir Ã© o e-Ã©simo filho de P, Dir - NÃ³ da direita, Esq - NÃ³ da esquerda
    private void Balanceia_Dir_Esq(NoB P, int e, NoB Dir, NoB Esq, Relatorio relatorio) {
        relatorio.incrementaTrocaColisaoCopia();
        Esq.setOrdem(Esq.getOrdem() + 1);//Incrementa a quantidade de chaves em Esq
        Esq.getChave()[Esq.getOrdem() - 1] = P.getChave()[e];//"desce" uma chave de P para Esq
        relatorio.incrementaTrocaColisaoCopia();
        P.getChave()[e] = Dir.getChave()[0];//"sobe" uma chave de Dir para P
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
    //ParÃ¢metros: X - NoB pai, i - posicao do filho de X onde vai ser juntado
    private void Juncao_No(NoB X, int i, Relatorio relatorio) {
        NoB Y = X.getFilho()[i - 1]; //cria Y
        relatorio.incrementaTrocaColisaoCopia();
        NoB Z = X.getFilho()[i];//cria Z        
        relatorio.incrementaTrocaColisaoCopia();
        int k = Y.getOrdem();
        Y.getChave()[k] = X.getChave()[i - 1];//"desce" uma chave de X para X
        relatorio.incrementaTrocaColisaoCopia();

        //descola as de chaves de Z para Y
        for (int j = 1; j <= Z.getOrdem(); j++) {
            relatorio.incrementaInteracao();
            Y.getChave()[j + k] = Z.getChave()[j - 1];
            relatorio.incrementaTrocaColisaoCopia();
        }

        //se Z nao for folha, descola seus filhos tbm
        if (!Z.isFolha()) {
            relatorio.incrementaInteracao();
            for (int j = 1; j <= Z.getOrdem(); j++) {
                relatorio.incrementaInteracao();
                Y.getFilho()[j + k] = Z.getFilho()[j - 1];
                relatorio.incrementaTrocaColisaoCopia();
            }
        }

        //seta a quantidades de chaves em Y
        Y.setOrdem(Y.getOrdem() + Z.getOrdem() + 1);

        X.getFilho()[i] = null;//desaloca o Z setando o filho de X que apontava pra Z pra null

        //descola os filhos e as chaves de X uma para a esquera, para "fechar o buraco"
        for (int j = i; j <= X.getOrdem() - 1; j++) {//ainda nao
            relatorio.incrementaInteracao();
            X.getChave()[j - 1] = X.getChave()[j];
            relatorio.incrementaTrocaColisaoCopia();
            X.getFilho()[j] = X.getFilho()[j + 1];
            relatorio.incrementaTrocaColisaoCopia();
        }

        //decrementa a quantidade de chaves em X
        X.setOrdem(X.getOrdem() - 1);
    }

    //Metodo que retorna o nÃ³ onde a chave antecessora de K se encontra
    //ParÃ¢metros: N - NÃ³ onde comeÃ§a a busca, k - chave a ser buscada
    private NoB Antecessor(NoB N, Chave k, Relatorio relatorio) {
        int i = 1;
        while (i <= N.getOrdem() && N.getChave()[i - 1].getId() < k.getId()) {
            relatorio.incrementaInteracao();
            i++;
        }
        if (N.isFolha()) {
            relatorio.incrementaInteracao();
            return N;
        } else {
            relatorio.incrementaInteracao();
            return Antecessor(N.getFilho()[i - 1], k, relatorio);
        }
    }

    //Metodo que retorna o nÃ³ pai de N
    //ParÃ¢metros: T - NÃ³ onde comeÃ§a a busca, N - nÃ³ que deve se buscar o pai
    private NoB getPai(NoB T, NoB N, Relatorio relatorio) {
        if (this.raiz == N) {
            relatorio.incrementaInteracao();
            return null;
        }
        for (int j = 0; j <= T.getOrdem(); j++) {
            relatorio.incrementaInteracao();
            if (T.getFilho()[j] == N) {
                relatorio.incrementaInteracao();
                return T;
            }
            if (!T.getFilho()[j].isFolha()) {
                relatorio.incrementaInteracao();
                NoB X = getPai(T.getFilho()[j], N, relatorio);
                relatorio.incrementaTrocaColisaoCopia();
                if (X != null) {
                    relatorio.incrementaInteracao();
                    return X;
                }
            }
        }
        return null;
    }

    //MÃ©todo para Limpar a arvoreB.
    //ParÃ¢metros: N - NÃ³ onde se deve iniciar a varredura, ordem - Nova ordem da arvoreB
    public void LimparArvore(NoB N, int ordem, Relatorio relatorio) {

        for (int i = 0; i < N.getOrdem() + 1; i++) {
            relatorio.incrementaInteracao();
            if (!N.isFolha()) {
                relatorio.incrementaInteracao();
                LimparArvore(N.getFilho()[i], ordem, relatorio);
            }
            N.getFilho()[i] = null;
            N.setOrdem(0);
        }

        if (N == this.raiz) {
            relatorio.incrementaInteracao();
            this.raiz = new NoB(ordem);
        }
        nElementos = 0;
    }
}

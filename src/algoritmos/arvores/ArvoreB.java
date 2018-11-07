package algoritmos.arvores;

public class ArvoreB {

    //Atributos da Classe ArvoreB
    private NoB raiz; //Atributo do NÃ³ raiz;
    private int ordem; //Ordem da Arvore-ArvoreB;
    private int nElementos; //Contador para a quantidade de elementos na arvore ArvoreB;

    //Construtor da Classe ArvoreB
    //Cria um novo nÃ³ para a raiz, seta a ordem passada como paÃ¢metro e inicializa
    //o atributo contador de numeros de elementos
    public ArvoreB(int ordem) {
        this.raiz = new NoB(ordem);
        this.ordem = ordem;
        nElementos = 0;
    }

    //Getters e Setters dos atributos nElementos,ordem e raiz
    public int getnElementos() {
        return nElementos;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getOrdem() {
        return ordem;
    }

    public NoB getRaiz() {
        return raiz;
    }

    //Metodo de InserÃ§Ã£o na ArvoreB
    //parametros: k - chave a ser inserida
    public void inserir(int id, Chave k) {
        k.setId(id);
        //Verifica se a chave a ser inserida existe
        if (buscaChave(raiz, k) == null) { //sÃ³ insere se nÃ£o houver, para evitar duplicaÃ§Ã£o de chaves
            //verifica se a chave estÃ¡ vazia
            if (raiz.getOrdem() == 0) {
                raiz.getChave()[0] = k;//seta a chave na primeira posiÃ§Ã£o da raiz
                raiz.setOrdem(raiz.getOrdem() + 1);
            } else { //caso nao estaja vazia
                NoB r = raiz;
                //verifica se a raiz estÃ¡ cheia
                if (r.getOrdem() == ordem - 1) {//hÃ¡ necessidade de dividir a raiz
                    NoB s = new NoB(ordem);
                    raiz = s;
                    s.setFolha(false);
                    s.setOrdem(0);
                    s.getFilho()[0] = r;
                    divideNo(s, 0, r);//divide nÃ³
                    insereNoNaoCheio(s, k);//depois de dividir a raiz comeÃ§a inserindo apartir da raiz

                } else {//caso contrario comeÃ§a inserindo apartir da raiz
                    insereNoNaoCheio(r, k);
                }
            }
            nElementos++;//incrementa o numero de elemantos na arvore
        }
    }

    //MÃ©todo de divisÃ£o de nÃ³
    //ParÃ¢metros: x - nÃ³ Pai, y - nÃ³ Filho e i - Ã­ndice i que indica que y Ã© o i-Ã©simo filho de x.
    public void divideNo(NoB x, int i, NoB y) {
        int t = (int) Math.floor((ordem - 1) / 2);
        //cria nÃ³ z
        NoB z = new NoB(ordem);
        z.setFolha(y.isFolha());
        z.setOrdem(t);

        //passa as t ultimas chaves de y para z
        for (int j = 0; j < t; j++) {
            if ((ordem - 1) % 2 == 0) {
                //z.getChave().set(j, y.getChave().get(j + t));
                z.getChave()[j] = y.getChave()[j + t];
            } else {
                //z.getChave().set(j, y.getChave().get(j + t + 1));
                z.getChave()[j] = y.getChave()[j + t + 1];
            }
            y.setOrdem(y.getOrdem() - 1);
        }

        //se y nao for folha, pasa os t+1 Ãºltimos flhos de y para z
        if (!y.isFolha()) {
            for (int j = 0; j < t + 1; j++) {
                if ((ordem - 1) % 2 == 0) {
                    //z.getFilho().set(j, y.getFilho().get(j + t));
                    z.getFilho()[j] = y.getFilho()[j + t];
                } else {
                    //z.getFilho().set(j, y.getFilho().get(j + t + 1));
                    z.getFilho()[j] = y.getFilho()[j + t + 1];
                }

            }
        }

        y.setOrdem(t);//seta a nova quantidade de chaves de y

        //descola os filhos de x uma posiÃ§Ã£o para a direita
        for (int j = x.getOrdem(); j > i; j--) {
            //x.getFilho().set(j + 1, x.getFilho().get(j));
            x.getFilho()[j + 1] = x.getFilho()[j];
        }

        //x.getFilho().set(i + 1, z);//seta z como filho de x na posiÃ§Ã£o i+1
        x.getFilho()[i + 1] = z;

        //desloca as chaves de x uma posiÃ§Ã£o para a direita, para podermos subir uma chave de y
        for (int j = x.getOrdem(); j > i; j--) {
            //x.getChave().set(j, x.getChave().get(j - 1));
            x.getChave()[j] = x.getChave()[j - 1];
        }

        //"sobe" uma chave de y para z
        if ((ordem - 1) % 2 == 0) {
            x.getChave()[i] = y.getChave()[t - 1];
            y.setOrdem(y.getOrdem() - 1);

        } else {
            x.getChave()[i] = y.getChave()[t];
        }

        //incrementa o numero de chaves de x
        x.setOrdem(x.getOrdem() + 1);

    }

    //MÃ©todo para inserir uma chave em um nÃ³ nÃ£o cheio
    //PaÃ¢metros: x - nÃ³ a ser inserido, k - chave a ser inserida NoB nÃ³ x
    public void insereNoNaoCheio(NoB x, Chave k) {
        int i = x.getOrdem() - 1;
        //verifica se x Ã© um nÃ³ folha
        if (x.isFolha()) {
            //adquire a posiÃ§Ã£o correta para ser inserido a chave
            while (i >= 0 && k.getId() < x.getChave()[i].getId()) {
                x.getChave()[i + 1] = x.getChave()[i];
                i--;
            }
            i++;
            x.getChave()[i] = k;//insere a chave na posiÃ§Ã£o i
            x.setOrdem(x.getOrdem() + 1);

        } else {//caso x nÃ£o for folha
            //adquire a posiÃ§Ã£o correta para ser inserido a chave
            while ((i >= 0 && k.getId() < x.getChave()[i].getId())) {
                i--;
            }
            i++;
            //se o filho i de x estiver cheio, divide o mesmo
            if ((x.getFilho()[i]).getOrdem() == ordem - 1) {
                divideNo(x, i, x.getFilho()[i]);
                if (k.getId() > x.getChave()[i].getId()) {
                    i++;
                }
            }
            //insere a chave NoB filho i de x
            insereNoNaoCheio(x.getFilho()[i], k);
        }

    }

    //MÃ©todo de busca de uma chave, retorna um nÃ³ onde a chave buscada se encontra
    //ParÃ¢metros: X - nÃ³ por onde comeÃ§ar a busca, k - chave a ser buscada
    public NoB buscaChave(NoB X, Chave k) {
        int i = 1;
        //procura ate nao estourar o tamanho e ate o valor nao ser maior q o procurado
        while ((i <= X.getOrdem()) && (k.getId() > X.getChave()[i - 1].getId())) {
            i++;
        }
        //se o tamanho nao tiver excedido e for o valor procurado..
        if ((i <= X.getOrdem()) && (k == X.getChave()[i - 1])) {
            return X;
        }
        //se nao foi Ã© igual, entao foi o tamanho q excedeu. ai procura NoB filho se nao for folha
        if (X.isFolha()) { //se o NoB X for folha
            return null;
        } else {
            return (buscaChave(X.getFilho()[i - 1], k));
        }
    }

    //MÃ©todo de RemoÃ§Ã£o de uma determinada chave da arvoreB
    public void Remove(Chave k) {
        //verifica se a chave a ser removida existe
        if (buscaChave(this.raiz, k) != null) {
            //N Ã© o nÃ³ onde se encontra k
            NoB N = buscaChave(this.raiz, k);
            int i = 1;

            //adquire a posiÃ§Ã£o correta da chave em N
            while (N.getChave()[i - 1].getId() < k.getId()) {
                i++;
            }

            //se N for folha, remove ela e deve se balancear N
            if (N.isFolha()) {
                for (int j = i + 1; j <= N.getOrdem(); j++) {
                    N.getChave()[j - 2] = N.getChave()[j - 1];//desloca chaves quando tem mais de uma
                }
                N.setOrdem(N.getOrdem() - 1);
                if (N != this.raiz) {
                    Balanceia_Folha(N);//Balanceia N
                }
            } else {//caso contrÃ¡rio(N nao Ã© folha), substitui a chave por seu antecessor e balanceia a folha onde se encontrava o ancecessor
                NoB S = Antecessor(this.raiz, k);//S eh onde se encontra o antecessor de k
                //int y = S.getChave().get(S.getOrdem() - 1);//y Ã© o antecessor de k
                Chave y = S.getChave()[S.getOrdem() - 1];
                S.setOrdem(S.getOrdem() - 1);
                N.getChave()[i - 1] = y;//substitui a chave por y
                Balanceia_Folha(S);//balanceia S
            }
            nElementos--;//decrementa o numero de elementos na arvoreB
        }
    }

    //MÃ©tode de Balancear um nÃ³ folha
    //ParÃ¢metros: F - nÃ³ Folha a ser balanceada
    private void Balanceia_Folha(NoB F) {
        //verifica se F estÃ¡ cheio
        if (F.getOrdem() < Math.floor((ordem - 1) / 2)) {
            NoB P = getPai(raiz, F);//P Ã© o pai de F
            int j = 1;

            //adquire a posiÃ§Ã£o de F em P
            while (P.getFilho()[(j - 1)] != F) {
                j++;
            }

            //verifica se o irmÃ£o Ã  esquerda de F nÃ£o tem chaves para emprestar
            if (j == 1 || (P.getFilho()[(j - 2)]).getOrdem() == Math.floor((ordem - 1) / 2)) {
                //verifica se o irmÃ£o Ã  direita de F nÃ£o tem chaves para emprestar
                if (j == P.getOrdem() + 1 || (P.getFilho()[j].getOrdem() == Math.floor((ordem - 1) / 2))) {
                    Diminui_Altura(F); //nenhum irmÃ£o tem chaves para emprestar eh necessario diminuir a altura
                } else {//caso contrario (tem chaves para emprestar) executa Balanceia_Dir_Esq
                    Balanceia_Dir_Esq(P, j - 1, P.getFilho()[j], F);
                }
            } else {//caso contrario (tem chaves para emprestar) executa Balanceia_Esq_Dir
                Balanceia_Esq_Dir(P, j - 2, P.getFilho()[j - 2], F);
            }
        }
    }

    //MÃ©todo para diminuir a altura
    //ParÃ¢metros: X - nÃ³ onde vai ser diminuido a altura
    private void Diminui_Altura(NoB X) {
        int j;
        NoB P = new NoB(ordem);

        //verifica se X Ã© a raiz
        if (X == this.raiz) {
            //verifica se X estÃ¡ vazio
            if (X.getOrdem() == 0) {
                this.raiz = X.getFilho()[0];//o filho o de x passa a ser raiz
                X.getFilho()[0] = null; // desaloca o filho de x
            }
        } else {//caso contrario(X nao Ã© raiz)
            int t = (int) Math.floor((ordem - 1) / 2);
            //verifica se o numero de chaves de X Ã© menor que o permitido
            if (X.getOrdem() < t) {
                P = getPai(raiz, X);//P Ã© pai de X
                j = 1;

                //adquire a posicao correta do filho X em P
                while (P.getFilho()[j - 1] != X) {
                    j++;
                }

                //junta os nÃ³s
                if (j > 1) {
                    Juncao_No(getPai(raiz, X), j - 1);
                } else {
                    Juncao_No(getPai(raiz, X), j);
                }
                Diminui_Altura(getPai(raiz, X));
            }
        }
    }

    //MÃ³todo de Balancear da esquerda para a direita
    //ParÃ¢metros: P - NÃ³ pai, e - indica que Esq Ã© o e-Ã©simo filho de P, Esq - NÃ³ da esquerda, Dir - NÃ³ da direita
    private void Balanceia_Esq_Dir(NoB P, int e, NoB Esq, NoB Dir) {
        //Desloca as chave de Dir uma posicao para a direita
        for (int i = 0; i < Dir.getOrdem(); i++) {
            Dir.getChave()[i + 1] = Dir.getChave()[i];
        }

        //Se Dir nao for folha descola seu filhos uma posicao para a direita
        if (!Dir.isFolha()) {//nao foi testado, mas teoricamente funciona
            for (int i = 0; i > Dir.getOrdem(); i++) {
                Dir.getFilho()[i + 1] = Dir.getFilho()[i];
            }
        }
        Dir.setOrdem(Dir.getOrdem() + 1);//Incrementa a quantidades de chaves em Dir
        Dir.getChave()[0] = P.getChave()[e];//"desce" uma chave de P para Dir
        P.getChave()[e] = Esq.getChave()[Esq.getOrdem() - 1];//"sobe" uma chave de Esq para P
        Dir.getFilho()[0] = Esq.getFilho()[Esq.getOrdem()];//Seta o ultimo filho de Esq como primeiro filho de Dir
        Esq.setOrdem(Esq.getOrdem() - 1);//Decrementa a quantidade de chaves em Esq

    }

    //MÃ©todo de Balancear da direita para a esquerda
    //ParÃ¢metros: P - NÃ³ pai, e - indica que Dir Ã© o e-Ã©simo filho de P, Dir - NÃ³ da direita, Esq - NÃ³ da esquerda
    private void Balanceia_Dir_Esq(NoB P, int e, NoB Dir, NoB Esq) {

        Esq.setOrdem(Esq.getOrdem() + 1);//Incrementa a quantidade de chaves em Esq
        Esq.getChave()[Esq.getOrdem() - 1] = P.getChave()[e];//"desce" uma chave de P para Esq
        P.getChave()[e] = Dir.getChave()[0];//"sobe" uma chave de Dir para P
        Esq.getFilho()[Esq.getOrdem()] = Dir.getFilho()[0];//Seta o primeiro filho de Dir como Ãºltimo filho de Esq

        //descola as chaves de Dir uma posiÃ§Ã£o para a esquerda
        for (int j = 1; j < Dir.getOrdem(); j++) {
            Dir.getChave()[j - 1] = Dir.getChave()[j];
        }

        //se Dir nao for folha, desloca todos os filhos de Dir uma posicao a esquerda
        if (!Dir.isFolha()) {//nao foi testado, mas teoricamente funciona
            for (int i = 1; i < Dir.getOrdem() + 1; i++) {
                Dir.getFilho()[i - 1] = Dir.getFilho()[i];
            }
        }

        //descrementa a quantidade de chaves de Dir
        Dir.setOrdem(Dir.getOrdem() - 1);

    }

    //MÃ©todo para junÃ§Ã£o do nÃ³
    //ParÃ¢metros: X - NoB pai, i - posicao do filho de X onde vai ser juntado
    private void Juncao_No(NoB X, int i) {
        NoB Y = X.getFilho()[i - 1]; //cria Y
        NoB Z = X.getFilho()[i];//cria Z

        int k = Y.getOrdem();
        Y.getChave()[k] = X.getChave()[i - 1];//"desce" uma chave de X para X

        //descola as de chaves de Z para Y
        for (int j = 1; j <= Z.getOrdem(); j++) {
            Y.getChave()[j + k] = Z.getChave()[j - 1];
        }

        //se Z nao for folha, descola seus filhos tbm
        if (!Z.isFolha()) {
            for (int j = 1; j <= Z.getOrdem(); j++) {
                Y.getFilho()[j + k] = Z.getFilho()[j - 1];
            }
        }

        //seta a quantidades de chaves em Y
        Y.setOrdem(Y.getOrdem() + Z.getOrdem() + 1);

        X.getFilho()[i] = null;//desaloca o Z setando o filho de X que apontava pra Z pra null

        //descola os filhos e as chaves de X uma para a esquera, para "fechar o buraco"
        for (int j = i; j <= X.getOrdem() - 1; j++) {//ainda nao
            X.getChave()[j - 1] = X.getChave()[j];
            X.getFilho()[j] = X.getFilho()[j + 1];
        }

        //decrementa a quantidade de chaves em X
        X.setOrdem(X.getOrdem() - 1);
    }

    //Metodo que retorna o nÃ³ onde a chave antecessora de K se encontra
    //ParÃ¢metros: N - NÃ³ onde comeÃ§a a busca, k - chave a ser buscada
    private NoB Antecessor(NoB N, Chave k) {
        int i = 1;
        while (i <= N.getOrdem() && N.getChave()[i - 1].getId() < k.getId()) {
            i++;
        }
        if (N.isFolha()) {
            return N;
        } else {
            return Antecessor(N.getFilho()[i - 1], k);
        }
    }

    //Metodo que retorna o nÃ³ pai de N
    //ParÃ¢metros: T - NÃ³ onde comeÃ§a a busca, N - nÃ³ que deve se buscar o pai
    private NoB getPai(NoB T, NoB N) {
        if (this.raiz == N) {
            return null;
        }
        for (int j = 0; j <= T.getOrdem(); j++) {
            if (T.getFilho()[j] == N) {
                return T;
            }
            if (!T.getFilho()[j].isFolha()) {
                NoB X = getPai(T.getFilho()[j], N);
                if (X != null) {
                    return X;
                }
            }
        }
        return null;
    }

    //MÃ©todo para Limpar a arvoreB.
    //ParÃ¢metros: N - NÃ³ onde se deve iniciar a varredura, ordem - Nova ordem da arvoreB
    public void LimparArvore(NoB N, int ordem) {

        for (int i = 0; i < N.getOrdem() + 1; i++) {
            if (!N.isFolha()) {
                LimparArvore(N.getFilho()[i], ordem);
            }
            N.getFilho()[i] = null;
            N.setOrdem(0);
        }

        if (N == this.raiz) {
            this.raiz = new NoB(ordem);
        }
        nElementos = 0;
    }
}

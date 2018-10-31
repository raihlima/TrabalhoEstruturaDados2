package algoritmos.arvores;

public class ArvoreB {

    //Atributos da Classe ArvoreB
    private NoB raiz; //Atributo do Nó raiz;
    private int ordem; //Ordem da Arvore-B;
    private int nElementos; //Contador para a quantidade de elementos na arvore B;

    //Construtor da Classe ArvoreB
    //Cria um novo nó para a raiz, seta a ordem passada como paâmetro e inicializa
    //o atributo contador de numeros de elementos
    public ArvoreB(int n) {
        this.raiz = new NoB(n);
        this.ordem = n;
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

    //Metodo de Inserção na ArvoreB
    //parametros: id - chave a ser inserida
    public void insere(Chave chave) {
        //Verifica se a chave a ser inserida existe
        if (BuscaChave(raiz, chave) == null) { //só insere se não houver, para evitar duplicação de chaves
            //verifica se a chave está vazia
            if (raiz.getN() == 0) {
                raiz.setChave(0, chave);//seta a chave na primeira posição da raiz

                raiz.setN(raiz.getN() + 1);
            } else { //caso nao estaja vazia
                NoB r = raiz;
                //verifica se a raiz está cheia
                if (r.getN() == ordem - 1) {//há necessidade de dividir a raiz
                    NoB s = new NoB(ordem);
                    raiz = s;
                    s.setFolha(false);
                    s.setN(0);
                    s.setFilho(0, r);
                    divideNo(s, 0, r);//divide nó
                    insereNoNaoCheio(s, chave);//depois de dividir a raiz começa inserindo apartir da raiz

                } else {//caso contrario começa inserindo apartir da raiz
                    insereNoNaoCheio(r, chave);
                }
            }
            nElementos++;//incrementa o numero de elemantos na arvore
        }
    }

    //Método de divisão de nó
    //Parâmetros: x - nó Pai, y - nó Filho e i - índice i que indica que y é o i-ésimo filho de x.
    public void divideNo(NoB x, int i, NoB y) {
        int t = (int) Math.floor((ordem - 1) / 2);
        //cria nó z
        NoB z = new NoB(ordem);
        z.setFolha(y.isFolha());
        z.setN(t);

        //passa as t ultimas chaves de y para z
        for (int j = 0; j < t; j++) {
            if ((ordem - 1) % 2 == 0) {
                //z.getChave().set(j, y.getChave().get(j + t));
                z.setChave(j, y.getChave(j + t));
            } else {
                // z.getChave().set(j, y.getChave().get(j + t + 1));
                z.setChave(j, y.getChave(j + t + 1));
            }
            y.setN(y.getN() - 1);
        }

        //se y nao for folha, pasa os t+1 últimos flhos de y para z
        if (!y.isFolha()) {
            for (int j = 0; j < t + 1; j++) {
                if ((ordem - 1) % 2 == 0) {
                    //z.getFilho().set(j, y.getFilho().get(j + t));
                    z.setFilho(j, y.getFilho(j + t));
                } else {
                    //z.getFilho().set(j, y.getFilho().get(j + t + 1));
                    z.setFilho(j, y.getFilho(j + t + 1));
                }

            }
        }

        y.setN(t);//seta a nova quantidade de chaves de y

        //descola os filhos de x uma posição para a direita
        for (int j = x.getN(); j > i; j--) {
            // x.getFilho().set(j + 1, x.getFilho().get(j));
            x.setFilho(j + 1, x.getFilho(j));
        }
        x.setFilho(i + 1, z);
        //x.getFilho().set(i + 1, z);//seta z como filho de x na posição i+1

        //desloca as chaves de x uma posição para a direita, para podermos subir uma chave de y
        for (int j = x.getN(); j > i; j--) {
            x.setChave(j, x.getChave(j - 1));
            //x.getChave().set(j, x.getChave().get(j - 1));
        }

        //"sobe" uma chave de y para z
        if ((ordem - 1) % 2 == 0) {
            x.setChave(i, y.getChave(t - 1));
            //x.getChave().set(i, y.getChave().get(t - 1));
            y.setN(y.getN() - 1);

        } else {
            x.setChave(i, y.getChave(t));
            //x.getChave().set(i, y.getChave().get(t));
        }

        //incrementa o numero de chaves de x
        x.setN(x.getN() + 1);

    }

    //Método para inserir uma chave em um nó não cheio
    //Paâmetros: x - nó a ser inserido, id - chave a ser inserida no nó x
    public void insereNoNaoCheio(NoB x, Chave chave) {
        int i = x.getN() - 1;
        //verifica se x é um nó folha
        if (x.isFolha()) {
            //adquire a posição correta para ser inserido a chave
            while (i >= 0 && chave.getId() < x.getChave(i).getId()) {
                x.setChave(i + 1, x.getChave(i));
                //x.getChave().set(i + 1, x.getChave().get(i));
                i--;
            }
            i++;
            x.setChave(i, chave);
            //x.getChave().set(i, id);//insere a chave na posição i
            x.setN(x.getN() + 1);

        } else {//caso x não for folha
            //adquire a posição correta para ser inserido a chave
            while ((i >= 0 && chave.getId() < x.getChave(i).getId())) {
                i--;
            }
            i++;
            //se o filho i de x estiver cheio, divide o mesmo
            if (x.getFilho(i).getN() == ordem - 1) {
                divideNo(x, i, x.getFilho(i));
                if (chave.getId() > x.getChave(i).getId()) {
                    i++;
                }
            }
            //insere a chave no filho i de x
            insereNoNaoCheio(x.getFilho(i), chave);
        }

    }

    //Método de busca de uma chave, retorna um nó onde a chave buscada se encontra
    //Parâmetros: X - nó por onde começar a busca, id - chave a ser buscada
    public NoB BuscaChave(NoB X, Chave chave) {
        int i = 1;
        //procura ate nao estourar o tamanho e ate o valor nao ser maior q o procurado
        while ((i <= X.getN()) && (chave.getId() > X.getChave(i - 1).getId())) {
            i++;
        }
        //se o tamanho nao tiver excedido e for o valor procurado..
        if ((i <= X.getN()) && (chave.getId() == X.getChave(i - 1).getId())) {
            return X;
        }
        //se nao foi é igual, entao foi o tamanho q excedeu. ai procura no filho se nao for folha
        if (X.isFolha()) { //se o no X for folha
            return null;
        } else {
            return (BuscaChave(X.getFilho(i - 1), chave));
        }
    }

    //Método de Remoção de uma determinada chave da arvoreB
    public void Remove(Chave chave) {
        //verifica se a chave a ser removida existe
        if (BuscaChave(this.raiz, chave) != null) {
            //N é o nó onde se encontra id
            NoB N = BuscaChave(this.raiz, chave);
            int i = 1;

            //adquire a posição correta da chave em N
            while (N.getChave(i - 1).getId() < chave.getId()) {
                i++;
            }

            //se N for folha, remove ela e deve se balancear N
            if (N.isFolha()) {
                for (int j = i + 1; j <= N.getN(); j++) {
                    N.setChave(j - 2, N.getChave(j - 1));
                    //N.getChave().set(j - 2, N.getChave().get(j - 1));//desloca chaves quando tem mais de uma

                }
                N.setN(N.getN() - 1);
                if (N != this.raiz) {
                    Balanceia_Folha(N);//Balanceia N
                }
            } else {//caso contrário(N nao é folha), substitui a chave por seu antecessor e balanceia a folha onde se encontrava o ancecessor
                NoB S = Antecessor(this.raiz, chave);//S eh onde se encontra o antecessor de id

                Chave y = new Chave();
                y = S.getChave(S.getN() - 1);
                //int y = S.getChave().get(S.getN() - 1);//y é o antecessor de id
                S.setN(S.getN() - 1);
                N.setChave(i - 1, y);
                //N.getChave().set(i - 1, y);//substitui a chave por y
                Balanceia_Folha(S);//balanceia S
            }
            nElementos--;//decrementa o numero de elementos na arvoreB
        }
    }

    //Métode de Balancear um nó folha
    //Parâmetros: F - nó Folha a ser balanceada
    private void Balanceia_Folha(NoB F) {
        //verifica se F está cheio
        if (F.getN() < Math.floor((ordem - 1) / 2)) {
            NoB P = getPai(raiz, F);//P é o pai de F
            int j = 1;

            //adquire a posição de F em P
            while (P.getFilho(j - 1) != F) {
                j++;
            }

            //verifica se o irmão à esquerda de F não tem chaves para emprestar
            if (j == 1 || (P.getFilho(j - 2)).getN() == Math.floor((ordem - 1) / 2)) {
                //verifica se o irmão à direita de F não tem chaves para emprestar
                if (j == P.getN() + 1 || (P.getFilho(j).getN() == Math.floor((ordem - 1) / 2))) {
                    Diminui_Altura(F); //nenhum irmão tem chaves para emprestar eh necessario diminuir a altura
                } else {//caso contrario (tem chaves para emprestar) executa Balanceia_Dir_Esq
                    Balanceia_Dir_Esq(P, j - 1, P.getFilho(j), F);
                }
            } else {//caso contrario (tem chaves para emprestar) executa Balanceia_Esq_Dir
                Balanceia_Esq_Dir(P, j - 2, P.getFilho(j - 2), F);
            }
        }
    }

    //Método para diminuir a altura
    //Parâmetros: X - nó onde vai ser diminuido a altura
    private void Diminui_Altura(NoB X) {
        int j;
        NoB P = new NoB(ordem);

        //verifica se X é a raiz
        if (X == this.raiz) {
            //verifica se X está vazio
            if (X.getN() == 0) {

                this.raiz = X.getFilho(0);//o filho o de x passa a ser raiz
                X.setFilho(0, null);
                //X.getFilho().set(0, null); // desaloca o filho de x
            }
        } else {//caso contrario(X nao é raiz)
            int t = (int) Math.floor((ordem - 1) / 2);
            //verifica se o numero de chaves de X é menor que o permitido
            if (X.getN() < t) {
                P = getPai(raiz, X);//P é pai de X
                j = 1;

                //adquire a posicao correta do filho X em P
                while (P.getFilho(j - 1) != X) {
                    j++;
                }

                //junta os nós
                if (j > 1) {
                    Juncao_No(getPai(raiz, X), j - 1);
                } else {
                    Juncao_No(getPai(raiz, X), j);
                }
                Diminui_Altura(getPai(raiz, X));
            }
        }
    }

    //Mótodo de Balancear da esquerda para a direita
    //Parâmetros: P - Nó pai, e - indica que Esq é o e-ésimo filho de P, Esq - Nó da esquerda, Dir - Nó da direita
    private void Balanceia_Esq_Dir(NoB P, int e, NoB Esq, NoB Dir) {
        //Desloca as chave de Dir uma posicao para a direita
        for (int i = 0; i < Dir.getN(); i++) {
            Dir.setChave(i + 1, Dir.getChave(i));
            //Dir.getChave().set(i + 1, Dir.getChave().get(i));
        }

        //Se Dir nao for folha descola seu filhos uma posicao para a direita
        if (!Dir.isFolha()) {//nao foi testado, mas teoricamente funciona
            for (int i = 0; i > Dir.getN(); i++) {
                Dir.setFilho(i + 1, Dir.getFilho(i));
                //Dir.getFilho().set(i + 1, Dir.getFilho().get(i));
            }
        }
        Dir.setN(Dir.getN() + 1);//Incrementa a quantidades de chaves em Dir
        Dir.setChave(0, Dir.getChave(e));
        //Dir.getChave().set(0, P.getChave().get(e));//"desce" uma chave de P para Dir
        P.setChave(e, Esq.getChave(Esq.getN() - 1));
        //P.getChave().set(e, Esq.getChave().get(Esq.getN() - 1));//"sobe" uma chave de Esq para P
        Dir.setFilho(0, Esq.getFilho(Esq.getN()));
        //Dir.getFilho().set(0, Esq.getFilho().get(Esq.getN()));//Seta o ultimo filho de Esq como primeiro filho de Dir
        Esq.setN(Esq.getN() - 1);//Decrementa a quantidade de chaves em Esq

    }

    //Método de Balancear da direita para a esquerda
    //Parâmetros: P - Nó pai, e - indica que Dir é o e-ésimo filho de P, Dir - Nó da direita, Esq - Nó da esquerda
    private void Balanceia_Dir_Esq(NoB P, int e, NoB Dir, NoB Esq) {

        Esq.setN(Esq.getN() + 1);//Incrementa a quantidade de chaves em Esq
        Esq.setChave(Esq.getN() - 1, P.getChave(e));
        //Esq.getChave().set(Esq.getN() - 1, P.getChave().get(e));//"desce" uma chave de P para Esq
        P.setChave(e, Dir.getChave(0));
        //P.getChave().set(e, Dir.getChave().get(0));//"sobe" uma chave de Dir para P
        Esq.setFilho(Esq.getN(), Dir.getFilho(0));
        //Esq.getFilho().set(Esq.getN(), Dir.getFilho().get(0));//Seta o primeiro filho de Dir como último filho de Esq

        //descola as chaves de Dir uma posição para a esquerda
        for (int j = 1; j < Dir.getN(); j++) {
            Dir.setChave(j - 1, Dir.getChave(j));
            //Dir.getChave().set(j - 1, Dir.getChave().get(j));
        }

        //se Dir nao for folha, desloca todos os filhos de Dir uma posicao a esquerda
        if (!Dir.isFolha()) {//nao foi testado, mas teoricamente funciona
            for (int i = 1; i < Dir.getN() + 1; i++) {
                Dir.setFilho(i - 1, Dir.getFilho(i));
                //Dir.getFilho().set(i - 1, Dir.getFilho().get(i));
            }
        }

        //descrementa a quantidade de chaves de Dir
        Dir.setN(Dir.getN() - 1);

    }

    //Método para junção do nó
    //Parâmetros: X - NoB pai, i - posicao do filho de X onde vai ser juntado
    private void Juncao_No(NoB X, int i) {
        NoB Y = X.getFilho(i - 1); //cria Y
        NoB Z = X.getFilho(i);//cria Z

        int id = Y.getN();
        Y.setChave(id, X.getChave(i - 1));
        //Y.getChave().set(id, X.getChave().get(i - 1));//"desce" uma chave de X para X

        //descola as de chaves de Z para Y
        for (int j = 1; j <= Z.getN(); j++) {
            Y.setChave(j + id, Z.getChave(j - 1));
            //Y.getChave().set(j + id, Z.getChave().get(j - 1));
        }

        //se Z nao for folha, descola seus filhos tbm
        if (!Z.isFolha()) {
            for (int j = 1; j <= Z.getN(); j++) {
                Y.setFilho(j + id, Z.getFilho(j - 1));
                //Y.getFilho().set(j + id, Z.getFilho().get(j - 1));
            }
        }

        //seta a quantidades de chaves em Y
        Y.setN(Y.getN() + Z.getN() + 1);

        X.setFilho(i, null);
        //X.getFilho().set(i, null);//desaloca o Z setando o filho de X que apontava pra Z pra null

        //descola os filhos e as chaves de X uma para a esquera, para "fechar o buraco"
        for (int j = i; j <= X.getN() - 1; j++) {//ainda nao
            X.setChave(j - 1, X.getChave(j));
            //X.getChave().set(j - 1, X.getChave().get(j));
            Y.setFilho(j, X.getFilho(j + 1));
            //X.getFilho().set(j, X.getFilho().get(j + 1));
        }

        //decrementa a quantidade de chaves em X
        X.setN(X.getN() - 1);
    }

    //Metodo que retorna o nó onde a chave antecessora de K se encontra
    //Parâmetros: N - Nó onde começa a busca, id - chave a ser buscada
    private NoB Antecessor(NoB N, Chave chave) {
        int i = 1;
        while (i <= N.getN() && N.getChave(i - 1).getId() < chave.getId()) {
            i++;
        }
        if (N.isFolha()) {
            return N;
        } else {
            return Antecessor(N.getFilho(i - 1), chave);
        }
    }

    //Metodo que retorna o nó pai de N
    //Parâmetros: T - Nó onde começa a busca, N - nó que deve se buscar o pai
    private NoB getPai(NoB T, NoB N) {
        if (this.raiz == N) {
            return null;
        }
        for (int j = 0; j <= T.getN(); j++) {
            if (T.getFilho(j) == N) {
                return T;
            }
            if (!T.getFilho(j).isFolha()) {
                NoB X = getPai(T.getFilho(j), N);
                if (X != null) {
                    return X;
                }
            }
        }
        return null;
    }

    //Método para Limpar a arvoreB.
    //Parâmetros: N - Nó onde se deve iniciar a varredura, ordem - Nova ordem da arvoreB
    public void LimparArvore(NoB N, int ordem) {

        for (int i = 0; i < N.getN() + 1; i++) {
            if (!N.isFolha()) {
                LimparArvore(N.getFilho(i), ordem);
            }
            N.setFilho(i, null);
            //N.getFilho().set(i, null);
            N.setN(0);
        }

        if (N == this.raiz) {
            this.raiz = new NoB(ordem);
        }
        nElementos = 0;
    }
}

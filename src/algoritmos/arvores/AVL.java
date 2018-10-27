package algoritmos.arvores;

public class AVL {

    No raiz;

    // Calcula a altura da Arvore 
    int altura(No N) {
        if (N == null) {
            return 0;
        }

        return N.getAltura();
    }

    // Máximo entre 2 inteiros 
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    No RotacaoDireita(No y) {
        No x = y.getEsq();
        No T2 = x.getDir();

        // Rotação 
        x.setDir(y);
        y.setEsq(T2);

        // Atualiza Alturas 
        y.setAltura(max(altura(y.getEsq()), altura(y.getDir())) + 1);
        x.setAltura(max(altura(x.getEsq()), altura(x.getDir())) + 1);

        // Retorna nova Raiz 
        return x;
    }

    No RotacaoEsquerda(No x) {
        No y = x.getDir();
        No T2 = y.getEsq();

        // Rotação
        y.setEsq(x);
        x.setDir(T2);

        //  Atualiza Alturas
        x.setAltura(max(altura(x.getEsq()), altura(x.getDir())) + 1);
        y.setAltura(max(altura(y.getEsq()), altura(y.getDir())) + 1);

        // Retorna nova Raiz 
        return y;
    }

    //  Retorna a diferenca de altura entre as subÁrvores
    int Balanceamento(No N) {
        if (N == null) {
            return 0;
        }
        return altura(N.getEsq()) - altura(N.getDir());
    }

    No inserir(No no, int id, Chave chave) {

        /* 1.  inserção normal*/
        if (no == null) {
            return (new No(id, chave));
        }

        if (id < no.getId()) {
            no.setEsq(inserir(no.getEsq(), id, chave));
        } else if (id > no.getId()) {
            no.setDir(inserir(no.getDir(), id, chave));
        } else // Não permite id's duplicados
        {
            return no;
        }

        /* 2. Atualiza altura do nó anterior */
        no.setAltura(1 + max(altura(no.getEsq()), altura(no.getDir())));

        /* 3. Retorna o fator de balanceamento das subÁrvores*/
        int balancear = Balanceamento(no);

        //Rotação a ESQUERDA
        if (balancear > 1 && id < no.getEsq().getId()) {
            return RotacaoDireita(no);
        }

        // Rotação a DIREITA 
        if (balancear < -1 && id > no.getDir().getId()) {
            return RotacaoEsquerda(no);
        }

        // Rotação ESQUERDA-DIREITA 
        if (balancear > 1 && id > no.getEsq().getId()) {
            no.setEsq(RotacaoEsquerda(no.getEsq()));
            return RotacaoDireita(no);
        }

        // Rotação DIREITA-ESQUERDA 
        if (balancear < -1 && id < no.getDir().getId()) {
            no.setDir(RotacaoDireita(no.getDir()));
            return RotacaoEsquerda(no);
        }

        /* Retorna o ponteiro pro nó */
        return no;
    }
/*
    COMO USAR:
    AVL arvore = new AVL();
    arvore.raiz=arvore.inserir(arvore.raiz,chave);
      
    */
}

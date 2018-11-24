package algoritmos.arvores;

import dados.Relatorio;

/**
 *
 * @author ice
 */
public class ArvoreB {

// here are variables available to tree
    int ordem; // ordem of tree

    NoB raiz;  //every tree has at least a raiz node

// ---------------------------------------------------------
// here is the constructor for tree                        |
// ---------------------------------------------------------
    public ArvoreB(int ordem, Relatorio relatorio) {
        this.ordem = ordem;

        raiz = new NoB(ordem, null);

    }

// --------------------------------------------------------
// this will be method to procurar for a given node where   |
// we want to inserir a chave value. this method is called   |
// from SearchnPrintNode.  It returns a node with chave     |
// value in it                                            |
// --------------------------------------------------------
    public NoB busca(int id, Relatorio relatorio) {
        return busca(raiz, id, relatorio);
    }

    private NoB busca(NoB raiz, int id, Relatorio relatorio) {
        int i = 0;//we always want to start searching the 0th index of node.

        while (i < raiz.contar && id > raiz.vet_chave[i].getId())//keep incrementing
        //in node while chave >
        //current value.
        {
            i++;
            relatorio.incrementaInteracao();
        }
        relatorio.incrementaInteracao();
        if (i <= raiz.contar && id == raiz.vet_chave[i].getId())//obviously if chave is in node
        //we went to return node.
        {
            relatorio.incrementaInteracao();

            return raiz;
        }
        relatorio.incrementaInteracao();
        if (raiz.folha)//since we've already checked raiz
        //if it is folha we don't have anything else to check
        {
            relatorio.incrementaInteracao();
            return null;

        } else//else if it is not leave recurse down through ith filho
        {
            relatorio.incrementaInteracao();
            return busca(raiz.getFilho(i), id, relatorio);

        }
    }
//  --------------------------------------------------------
//  this will be the dividir method.  It will dividir node we  |
//  want to inserir into if it is full.                     |
//  --------------------------------------------------------

    public void dividir(NoB x, int i, NoB y, Relatorio relatorio) {
        NoB z = new NoB(ordem, null);//gotta have extra node if we are
        //to dividir.

        z.folha = y.folha;//set boolean to same as y
        relatorio.incrementaTrocaColisaoCopia();

        z.contar = ordem - 1;//this is updated size
        relatorio.incrementaTrocaColisaoCopia();

        for (int j = 0; j < ordem - 1; j++) {
            z.vet_chave[j] = y.vet_chave[j + ordem]; //copy end of y into front of z
            relatorio.incrementaTrocaColisaoCopia();
            relatorio.incrementaInteracao();
        }
        relatorio.incrementaInteracao();
        if (!y.folha)//if not folha we have to reassign filho nodes.
        {
            relatorio.incrementaInteracao();
            for (int k = 0; k < ordem; k++) {
                relatorio.incrementaInteracao();
                z.vet_filho[k] = y.vet_filho[k + ordem]; //reassing filho of y
                relatorio.incrementaTrocaColisaoCopia();
            }
            relatorio.incrementaInteracao();
        }
        relatorio.incrementaInteracao();

        y.contar = ordem - 1; //new size of y
        relatorio.incrementaTrocaColisaoCopia();

        for (int j = x.contar; j > i; j--)//if we push chave into x we have
        {				 //to rearrange filho nodes

            x.vet_filho[j + 1] = x.vet_filho[j]; //shift children of x
            relatorio.incrementaTrocaColisaoCopia();
            relatorio.incrementaInteracao();
        }
        relatorio.incrementaInteracao();
        x.vet_filho[i + 1] = z; //reassign i+1 filho of x
        relatorio.incrementaTrocaColisaoCopia();

        for (int j = x.contar; j > i; j--) {
            x.vet_chave[j + 1] = x.vet_chave[j]; // shift keys
            relatorio.incrementaTrocaColisaoCopia();
            relatorio.incrementaInteracao();
        }
        relatorio.incrementaInteracao();
        x.vet_chave[i] = y.vet_chave[ordem - 1];//finally push value up into raiz.
        relatorio.incrementaTrocaColisaoCopia();

        y.vet_chave[ordem - 1] = null; //erase value where we pushed from
        relatorio.incrementaTrocaColisaoCopia();

        for (int j = 0; j < ordem - 1; j++) {
            y.vet_chave[j + ordem] = null; //'delete' old values
            relatorio.incrementaTrocaColisaoCopia();
            relatorio.incrementaInteracao();
        }
        relatorio.incrementaInteracao();

        x.contar++;  //increase contar of keys in x
    }

// ----------------------------------------------------------
// this will be inserir method when node is not full.        |
// ----------------------------------------------------------
    public void inserirNoNaoCheio(NoB x, Chave chave, Relatorio relatorio) {
        int i = x.contar; //i is number of keys in node x

        if (x.folha) {
            relatorio.incrementaInteracao();
            while (i >= 1 && chave.getId() < x.vet_chave[i - 1].getId())//here find spot to put chave.
            {
                relatorio.incrementaInteracao();
                x.vet_chave[i] = x.vet_chave[i - 1];//shift values to make room
                relatorio.incrementaTrocaColisaoCopia();

                i--;//decrement
            }
            relatorio.incrementaInteracao();
            x.vet_chave[i] = chave;//finally assign value to node
            relatorio.incrementaTrocaColisaoCopia();
            x.contar++; //increment contar of keys in this node now.

        } else {
            relatorio.incrementaInteracao();
            int j = 0;
            while (j < x.contar && chave.getId() > x.vet_chave[j].getId())//find spot to recurse
            {			             //on correct filho  		
                j++;
                relatorio.incrementaInteracao();
            }
            relatorio.incrementaInteracao();
            //	i++;
            if (x.vet_filho[j].contar == ordem * 2 - 1) {
                relatorio.incrementaInteracao();
                dividir(x, j, x.vet_filho[j], relatorio);//call dividir on node x's ith filho

                if (chave.getId() > x.vet_chave[j].getId()) {
                    relatorio.incrementaInteracao();
                    j++;
                }
                relatorio.incrementaInteracao();
            }
            relatorio.incrementaInteracao();

            inserirNoNaoCheio(x.vet_filho[j], chave, relatorio);//recurse
        }
    }
//--------------------------------------------------------------
//this will be the method to inserir in general, it will call    |
//inserir non full if needed.                                    |
//--------------------------------------------------------------

    public void inserir(ArvoreB t, int id, Chave chave, Relatorio relatorio) {
        chave.setId(id);
        NoB r = t.raiz;//this method finds the node to be inserted as 
        //it goes through this starting at raiz node.
        if (r.contar == 2 * ordem - 1)//if is full
        {
            relatorio.incrementaInteracao();
            NoB s = new NoB(ordem, null);//new node

            t.raiz = s;    //\
            relatorio.incrementaTrocaColisaoCopia();
            // \	
            s.folha = false;//  \
            //   > this is to initialize node.
            s.contar = 0;   //  /
            // /	
            s.vet_filho[0] = r;///
            relatorio.incrementaTrocaColisaoCopia();

            dividir(s, 0, r, relatorio);//dividir raiz

            inserirNoNaoCheio(s, chave, relatorio); //call inserir method
        } else {
            relatorio.incrementaInteracao();
            inserirNoNaoCheio(r, chave, relatorio);//if its not full just inserir it
        }
    }

// ---------------------------------------------------------------------------------
// this will be method to print out a node, or recurses when raiz node is not folha |
// ---------------------------------------------------------------------------------
    public void imprimir(NoB n) {
        for (int i = 0; i < n.contar; i++) {

            System.out.print(n.getChave(i) + " ");//this part prints raiz node
        }

        if (!n.folha)//this is called when raiz is not folha;
        {

            for (int j = 0; j <= n.contar; j++)//in this loop we recurse
            {				  //to print out tree in
                if (n.getFilho(j) != null) //preorder fashion.
                {			  //going from left most
                    System.out.println();	  //filho to right most
                    imprimir(n.getFilho(j));     //filho.
                }
            }
        }
    }

// ------------------------------------------------------------
// this will be method to print out a node                    |
// ------------------------------------------------------------
    public void procurarNoImpressao(ArvoreB T, int x, Relatorio relatorio) {
        NoB temp = new NoB(ordem, null);

        temp = busca(T.raiz, x, relatorio);

        if (temp == null) {

            System.out.println("The Key does not exist in this tree");
        } else {

            imprimir(temp);
        }

    }

//--------------------------------------------------------------
//this method will delete a chave value from the folha node it is |
//in.  We will use the procurar method to traverse through the   |
//tree to find the node where the chave is in.  We will then     |
//iterated through chave[] array until we get to node and will   |
//assign k[i] = k[i+1] overwriting chave we want to delete and   |
//keeping blank spots out as well.  Note that this is the most |
//simple case of delete that there is and we will not have time|
//to implement all cases properly.                             |
//--------------------------------------------------------------
    public void remover(ArvoreB t, int id, Relatorio relatorio) {

        NoB temp = new NoB(ordem, null);//temp Bnode

        temp = busca(t.raiz, id, relatorio);//call of procurar method on tree for chave

        if (temp.folha && temp.contar > ordem - 1) {
            relatorio.incrementaInteracao();
            int i = 0, j = 0;

            while (id > temp.getChave(i).getId()) {
                relatorio.incrementaInteracao();
                i++;
            }
            relatorio.incrementaInteracao();
            for (j = i; j < 2 * ordem - 2; j++) {
                relatorio.incrementaInteracao();
                temp.vet_chave[j] = temp.getChave(j + 1);
                relatorio.incrementaTrocaColisaoCopia();
            }
            relatorio.incrementaInteracao();
            temp.contar--;

        } else {
            relatorio.incrementaInteracao();
            System.out.println("This node is either not a folha or has less than ordem - 1 keys.");
        }
    }

}

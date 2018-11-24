package algoritmos.arvores;

public class NoB {

        static int ordem;  //variable to determine order of tree

        int contar; // number of keys in node

        Chave vet_chave[];  // array of vet_chave values

        NoB vet_filho[]; //array of references

        boolean folha; //is node a folha or not

        NoB pai;  //pai of current node.

// ----------------------------------------------------
// this will be default constructor for new node      |
// ----------------------------------------------------
        public NoB() {
        }
// ----------------------------------------------------
// initial value constructor for new node             |
// will be called from BTree.java                     |
// ----------------------------------------------------

        public NoB(int ordem, NoB pai) {
            this.ordem = ordem;  //assign size

            this.pai = pai; //assign pai

            vet_chave = new Chave[2 * ordem - 1];  // array of proper size

            vet_filho = new NoB[2 * ordem]; // array of refs proper size

            folha = true; // everynode is folha at first;

            contar = 0; //until we add keys later.
        }

// -----------------------------------------------------
// this is method to return vet_chave value at indice position|
// -----------------------------------------------------
        public Chave getChave(int indice) {
            return vet_chave[indice];
        }

// ----------------------------------------------------
// this is method to get ith vet_filho of node            |
// ----------------------------------------------------
        public NoB getFilho(int indice) {
            return vet_filho[indice];
        }
        
    }
    

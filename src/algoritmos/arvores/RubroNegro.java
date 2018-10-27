/*
 * To change this license raiz, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.arvores;

/**
 *
 * @author rodri
 */
public class RubroNegro {
     
     private NoRubroNegro atual;
     private NoRubroNegro pai;
     private NoRubroNegro avo;
     private NoRubroNegro maior;
     private NoRubroNegro raiz;    
     private static NoRubroNegro nullNode;
     /* static initializer for nullNode */
     static 
     {
         nullNode = new NoRubroNegro(0);
         nullNode.setEsq(nullNode); 
         nullNode.setDir(nullNode);
     }
     /* Black - 1  VERMELHO - 0 */
     static final int PRETO = 1;    
     static final int VERMELHO   = 0;
 
     /* Constructor */
     public RubroNegro(int negInf)
     {
         raiz = new NoRubroNegro(negInf);
         raiz.setEsq(nullNode);
         raiz.setDir(nullNode);
     }
     /* Function to check if tree is empty */
     public boolean vazio()
     {
         return raiz.getDir() == nullNode;
     }
     /* Make the tree logically empty */
     public void esvaziar()
     {
         raiz.setDir(nullNode);
     }
     /* Function to inserir id */
     public void inserir(int id )
     {
         atual = pai = avo = raiz;
         nullNode.setId(id);
         while (atual.getId() != id)
         {            
             maior = avo; 
             avo = pai; 
             pai = atual;
             atual = id < atual.getId() ? atual.getEsq() : atual.getDir();
             // Check if two red children and fix if so            
             if (atual.getEsq().getCor() == VERMELHO && atual.getDir().getCor() == VERMELHO)
                 reorientar( id );
         }
         // Insertion fails if already present
         if (atual != nullNode)
             return;
         atual = new NoRubroNegro(id, nullNode, nullNode);
         // Attach to pai
         if (id < pai.getId())
             pai.setEsq(atual);
         else
             pai.setDir(atual);        
         reorientar( id );
     }
     private void reorientar(int id)
     {
         // Do the cor flip
         atual.setCor(VERMELHO);
         atual.getEsq().setCor(PRETO);
         atual.getDir().setCor(PRETO);
 
         if (pai.getCor() == VERMELHO)   
         {
             // Have to rotacionar
             avo.setCor(VERMELHO);
             if (id < avo.getId() != id < pai.getId())
                 pai = rotacionar( id, avo );  // Start dbl rotacionar
             atual = rotacionar(id, maior );
             atual.setCor(PRETO);
         }
         // Make root black
         raiz.getDir().setCor(PRETO); 
     }      
     private NoRubroNegro rotacionar(int id, NoRubroNegro pai)
     {
         
         if(id < pai.getId())
             return null;//pai.setEsq(id < pai.getEsq().getId() ? rotacaoEsquerda(pai.getEsq()) : rotacaoDireita(pai.getEsq()) );  
         else
             return null//pai.setDir(id < pai.getDir().getId() ? rotacaoEsquerda(pai.getDir()) : rotacaoDireita(pai.getDir()));  
     ;
         }
     /* Rotate binary tree node with esq child */
     private NoRubroNegro rotacaoEsquerda(NoRubroNegro k2)
     {
         NoRubroNegro k1 = k2.getEsq();
         k2.setEsq(k1.getDir());
         k1.setDir(k2);
         return k1;
     }
     /* Rotate binary tree node with dir child */
     private NoRubroNegro rotacaoDireita(NoRubroNegro k1)
     {
         NoRubroNegro k2 = k1.getDir();
         k1.setDir(k2.getEsq());
         k2.setEsq(k1);
         return k2;
     }
     /* Functions to count number of nodes */
     public int contarNos()
     {
         return contarNos(raiz.getDir());
     }
     private int contarNos(NoRubroNegro r)
     {
         if (r == nullNode)
             return 0;
         else
         {
             int l = 1;
             l += contarNos(r.getEsq());
             l += contarNos(r.getDir());
             return l;
         }
     }
     /* Functions to procurar for an id */
     public boolean procurar(int id)
     {
         return procurar(raiz.getDir(), id);
     }
     private boolean procurar(NoRubroNegro r, int id)
     {
         boolean encontrou = false;
         while ((r != nullNode) && !encontrou)
         {
             int rval = r.getId();
             if (id < rval)
                 r = r.getEsq();
             else if (id > rval)
                 r = r.getDir();
             else
             {
                 encontrou = true;
                 break;
             }
             encontrou = procurar(r, id);
         }
         return encontrou;
     }
     /* Function for emOrdem traversal */ 
     public void emOrdem()
     {
         emOrdem(raiz.getDir());
     }
     private void emOrdem(NoRubroNegro r)
     {
         if (r != nullNode)
         {
             emOrdem(r.getEsq());
             char c = 'P';
             if (r.getCor() == 0)
                 c = 'V';
             System.out.print(r.getId() +""+c+" ");
             emOrdem(r.getDir());
         }
     }
     /* Function for preOrdem traversal */
     public void preOrdem()
     {
         preOrdem(raiz.getDir());
     }
     private void preOrdem(NoRubroNegro r)
     {
         if (r != nullNode)
         {
             char c = 'P';
             if (r.getCor() == 0)
                 c = 'V';
             System.out.print(r.getId() +""+c+" ");
             preOrdem(r.getEsq());             
             preOrdem(r.getDir());
         }
     }
     /* Function for posOrdem traversal */
     public void posOrdem()
     {
         posOrdem(raiz.getDir());
     }
     private void posOrdem(NoRubroNegro r)
     {
         if (r != nullNode)
         {
             posOrdem(r.getEsq());             
             posOrdem(r.getDir());
             char c = 'P';
             if (r.getCor() == 0)
                 c = 'V';
             System.out.print(r.getId() +""+c+" ");
         }
     }     
 
}

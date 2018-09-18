/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import algoritmos.ListaEncadeada;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author carcara
 */
public class RelatorioDados implements Serializable{
    ListaEncadeada <Relatorio> relatorios = new ListaEncadeada<>();

    public RelatorioDados() {
    }
    
    
    public void gravaRelatorio() {
        try {
            FileOutputStream arq = new FileOutputStream("Relatorio.txt");
            ObjectOutputStream obj = new ObjectOutputStream(arq);
            obj.writeObject(relatorios);
            obj.flush();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar o arquivo.", "Erro", 0);
            Logger.getLogger(RelatorioDados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leRelatorio() {
        try {
            FileInputStream arq = new FileInputStream("Relatorio.ser");
            ObjectInputStream obj = new ObjectInputStream(arq);
            this.relatorios = (ListaEncadeada<Relatorio>) obj.readObject();

        } catch (Exception erro) {
            //JOptionPane.showMessageDialog(null, "Não há cadastro de relatorioss.", "Info", 1);
        }
    }

    public ListaEncadeada<Relatorio> retornaDados() {
        leRelatorio();
        return relatorios;
    }

    public void adicionaDados(Relatorio m) {
        leRelatorio();
        this.relatorios.insereFinal(m);
        gravaRelatorio();
    }

    public void deletaDados(int m) {
        leRelatorio();
        relatorios.remove(m);
        gravaRelatorio();
    }

    public void salvaDados(Relatorio a, int m) {
        leRelatorio();
        relatorios.insere(m, a);
        gravaRelatorio();
    }

    public Relatorio retornaRelatorio(int i) {
        leRelatorio();
        return relatorios.retornaInfo(i);
    }
}



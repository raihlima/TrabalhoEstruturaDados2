/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoed2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 *
 * @author ice
 */
public class TrabalhoED2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        leDados();
        
        
    }
    
    public static void leDados(){
        File arquivo = new File("texto.txt");
        //File arquivo = new File("deputies_dataset.csv");
        try(FileInputStream fi = new FileInputStream(arquivo)){
            BufferedInputStream bis = new BufferedInputStream(fi);
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
            String linha = linha = reader.readLine();
            
            /*
            for(int i=0;i<5;i++){
                
                linha = reader.readLine();
                System.out.println(linha);
            }
            */
            while(linha !=null){
                System.out.println(linha);
                linha = reader.readLine();
            }//*/
            
        } catch (Exception e){
            
        }
    }
        
    
}

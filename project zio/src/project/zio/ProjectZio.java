/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.zio;

import java.io.*;
import java.lang.*;
import java.util.*;

/**
 *
 * @author fedus
 */
public class ProjectZio {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
  throws IOException {
        
    FileReader f=null;
    
    String percorso="C:\\Users\\fedus\\Documents\\NetBeansProjects\\Project-zio\\project zio\\src\\project\\zio\lista.txt";
    
    Scanner in = new Scanner(System.in);  //variabile di input, che rappresenta la tastiera
    
    System.out.println("Inserisci il percorso del file 8Es.: c:/federico/lista.txt");
    
    percorso=in.nextLine(); //memorizzo il percorso file
    
    try { //Verifico se il file esista...
            f=new FileReader(percorso);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(ProjectZio.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("File non trovato: assicurati che si chiami lista.txt");
            System.out.println(f);
        }

    BufferedReader b;
    b=new BufferedReader(f);

    String s;

    while (true){
        b.readLine();
    }
    
    System.out.println(b);
    
  }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectziotextonly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author fedus
 */
public class ProjectZioTextOnly {
 
    /*attributi*/
    
    private FileReader f;
    
    private String percorso;
    
    private Scanner in;  //variabile di input, che rappresenta la tastiera
    
    private BufferedReader b;
    
    private String s; //stringa di buffer
    private int numSoci=0;
    
    public String temp="";
    
    public String[] codiciSocio;
    public String[] codiciContatori;
    public String[] volumeDopo;
    public String[] volumePrima;
    public String[] date;
    
        public ProjectZioTextOnly(){
            setIn(new Scanner(System.in));
            setS("");
        
        }
    
    
    public void getData() throws IOException{
    
        getB().readLine(); //salto la prima riga del file
        
        //ciclo per la lettura di tutti i codici socio
        for(int y=0; y<getNumSoci(); y++){
            
            //CODICI CONTATORE
            for(int i = 0; i<8; i++){
                
                setS(getS() + ((char) getB().read()));
                    
                //System.out.println(getS());
            }
            
            codiciContatori[y]=getS();
            
            setS("");
            
            //CODICI SOCIO
            getB().skip(49); //salto 62 caratteri sino ad arrivare al codice socio
            
            for(int i = 0; i<=4; i++){
                
                setS(getS() + ((char) getB().read()));
                    
                //System.out.println(getS());
            }
            
            codiciSocio[y]=getS();
            setS("");
            
            
            
            //VOLUME DOPO
            
            getB().skip(206);
            
            for(int i = 0; i<8; i++){
                
                setS(getS() + ((char) getB().read()));
                    
                //System.out.println(getS());
            }
          
            volumeDopo[y]=getS();
            
            setS("");
            
            //VOLUME PRIMA
            
            getB().skip(61);
            
            for(int i = 0; i<8; i++){
                
                setS(getS() + ((char) getB().read()));
                    
                //System.out.println(getS());
            }
          
            volumePrima[y]=getS();
            
            System.out.println(codiciContatori[y]+ "" + codiciSocio[y] + "" + volumeDopo[y] + ""+ volumePrima[y]);
            setS("");
            
            
            

            getB().readLine(); //passo alla riga successiva
            
            
        }
    
        
    }
    
    
    public void VerificaFile(){
        Boolean flag=true;
        do{
            System.out.println("Inserisci il nome del file, compresa l'estensione (Es.: lista.txt)");
            setPercorso(in.nextLine());            
            try { //Verifico se il file esista...
                setF(new FileReader(percorso)); //da decommentare nel jar finale
                flag=false;
                setB(new BufferedReader(getF()));
                //setF(new FileReader(getPercorso())); //DEBUG

            } catch (FileNotFoundException ex) {
                //Logger.getLogger(ProjectZio.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("File non trovato, reinserisci il nome");
                //System.out.println(percorso);
                flag=true;

            }
        }while(flag);
        
        
        
    }

public void GeneraOutput() throws FileNotFoundException, IOException{
        setIn(new Scanner(System.in));
        System.out.println("Inserisci il nome del file di output, SENZA estensione (Quella di default è .txt)");
        setPercorso(in.nextLine()); 
        try (FileOutputStream fileOut = new FileOutputStream(percorso+".txt")) {
            PrintStream scrivi = new PrintStream(fileOut);
            
            scrivi.println(codiciSocio);
        }
    
    }

public static void main(String args[]) throws IOException{     
    
    /*System.out.println("Inserisci il percorso del file..Es.: c:/federico/lista.txt");
    
    percorso=in.nextLine(); //memorizzo il percorso file*/
    ProjectZioTextOnly ogg= new ProjectZioTextOnly();
    
    ogg.VerificaFile(); //verifico la presenza del file lista 
    
    System.out.println("Inserisci il numero massimo dei soci: ");
    
    ogg.setNumSoci(ogg.getIn().nextInt());
    
    ogg.getData(); //leggo i dati dal file
        
    ogg.GeneraOutput(); //genero l'ouput
    
    System.out.println("superato3");
    
  }

/**
     * @return the f
     */
    public FileReader getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(FileReader f) {
        this.f = f;
    }

    /**
     * @return the percorso
     */
    public String getPercorso() {
        return percorso;
    }

    /**
     * @param percorso the percorso to set
     */
    public void setPercorso(String percorso) {
        this.percorso = percorso;
    }

    /**
     * @return the in
     */
    public Scanner getIn() {
        return in;
    }

    /**
     * @param in the in to set
     */
    public void setIn(Scanner in) {
        this.in = in;
    }

    /**
     * @return the b
     */
    public BufferedReader getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(BufferedReader b) {
        this.b = b;
    }

    /**
     * @return the s
     */
    public String getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(String s) {
        this.s = s;
    }

    /**
     * @return the numSoci
     */
    public int getNumSoci() {
        return numSoci;
    }

    /**
     * @param numSoci the numSoci to set
     */
    public void setNumSoci(int numSoci) {
        codiciSocio=new String[numSoci];
        codiciContatori=new String[numSoci];
        volumeDopo=new String[numSoci];
        volumePrima=new String[numSoci];
        date=new String[numSoci];
        this.numSoci = numSoci;
    }

    /**
     * @return the wb
     */


}



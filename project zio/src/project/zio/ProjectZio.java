/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project.zio;

import java.io.*;
import java.lang.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author fedus
 */
public class ProjectZio {

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
        this.numSoci = numSoci;
    }

    /**
     * @return the wb
     */
    public Workbook getWb() {
        return wb;
    }

    /**
     * @param wb the wb to set
     */
    public void setWb(Workbook wb) {
        this.wb = wb;
    }

    /**
     * @return the sheet1
     */
    public Sheet getSheet1() {
        return sheet1;
    }

    /**
     * @param sheet1 the sheet1 to set
     */
    public void setSheet1(Sheet sheet1) {
        this.sheet1 = sheet1;
    }

    /**
     * @return the createHelper
     */
    public CreationHelper getCreateHelper() {
        return createHelper;
    }

    /**
     * @param createHelper the createHelper to set
     */
    public void setCreateHelper(CreationHelper createHelper) {
        this.createHelper = createHelper;
    }
    
    /**
     * @param args the command line arguments
     */
    
    /*attributi*/
    
    private FileReader f;
    
    private String percorso;
    
    private Scanner in;  //variabile di input, che rappresenta la tastiera
    
    private BufferedReader b;
    
    private String s; //stringa di buffer
    private int numSoci=0;
    
    private Workbook wb;
    
    private Sheet sheet1;
    
    private CreationHelper createHelper;
    
    /*Costruttori*/
    
    public ProjectZio(){
        setPercorso("C:/Users/fedus/Desktop/lista.txt");
        setIn(new Scanner(System.in));
        setS("");
        
        
        
        setWb(new HSSFWorkbook());  //creo file .xls mediante l'interfaccia Workbook, che è compatibile anche con gli .xlsx
        setSheet1(wb.createSheet("Nuovo Foglio")); //Creo un nuovo foglio excel
        setCreateHelper(wb.getCreationHelper()); //Otteniamo una istanza di CreationHelper del nostro Workbook
    }
    
    /*metodi*/
    
    public void VerificaFile(){
        try { //Verifico se il file esista...
            //setF(new FileReader("./lista.txt")); //da decommentare nel jar finale
            setF(new FileReader(getPercorso())); //DEBUG
            
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(ProjectZio.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("File non trovato");
            System.out.println(getPercorso());
        }
        
        setB(new BufferedReader(getF()));
    }
    
    public void CodiciSocio() throws IOException{
        
        getB().readLine(); //Salto la prima riga
        
        //ciclo per la lettura di tutti i codici socio
        for(int y=1; y<getNumSoci()+1; y++){

            //Setto l'intestazione "Numero Socio"
            Row rowMain = getSheet1().createRow((short)0); //creo una riga in posizione 0
            Cell cellMain = rowMain.createCell (0);
            Cell cellMain1=rowMain.createCell(1);
            Cell cellMain2=rowMain.createCell(2);
            cellMain.setCellValue(getCreateHelper().createRichTextString("Numero Socio"));
            cellMain1.setCellValue(getCreateHelper().createRichTextString("Volume Attuale"));
            cellMain2.setCellValue(getCreateHelper().createRichTextString("Data"));

            getB().skip(57); //Salto i dati che non mi servono

            //memorizzo il codice in una stringa di buffer
            for(int i=0; i<=4; i++){
                setS(getS() + ((char) getB().read()));
            }

            Row row = getSheet1().createRow((short)y); //creo una riga in posizione y-esima
            Cell cell = row.createCell (0); //creo una cella
            cell.setCellValue(getCreateHelper().createRichTextString(getS()));
            setS("");

            getB().readLine(); //passo alla riga successiva
        }
    }
    
    public void AltriDati() throws IOException{
        getB().readLine(); //salto una riga
    
        //ciclo per la lettura di VolumeAttuale e Data associata
        for(int y=1; y<getNumSoci()+1; y++){
            getB().skip(33); //salto i caratteri inutili, sino ad arrivare ai volumi

            //memorizzo i volumi nella stringa s
            for(int i=0; i<=11; i++){
                setS(getS() + ((char) getB().read()));
            }

            //Inserisco i volumi nella cella 1 di ogni riga
            Row a = getSheet1().getRow(y); //estraggo la riga y-esima dal foglio creato prima


            Cell cell1 = a.createCell (1); //creo una cella
            cell1.setCellValue(getCreateHelper().createRichTextString(getS())); //inserisco il valore di s nella cella
            setS("");

            getB().skip(25); //salto i caratteri inutili, sino ad arrivare alle date

            //memorizzo le date in una stringa s
            for(int i=0; i<=9; i++){
                setS(getS() + ((char) getB().read()));
            }

            //Inserisco le date nella cella 2 di ogni riga
            Cell cell2 = a.createCell (2); //creo una cella
            cell2.setCellValue(getCreateHelper().createRichTextString(getS())); //Inserisco il valore di s nella cella
            setS("");

            getB().readLine(); //passo alla riga successiva
        }
    
    }
    
    public void GeneraOutput() throws FileNotFoundException, IOException{
        //Genero l'output
        //System.out.println("Inserisci il percorso di salvataggio del file .xls (Es.: C:/Deskop/prova.xls");
        //percorso=in.nextLine();
        FileOutputStream fileOut = new FileOutputStream("prova.xls");
        getWb().write(fileOut);
        fileOut.close();
    
    }
    
    public static void main(String args[]) throws IOException{     
    
    /*System.out.println("Inserisci il percorso del file..Es.: c:/federico/lista.txt");
    
    percorso=in.nextLine(); //memorizzo il percorso file*/
    ProjectZio ogg= new ProjectZio();
    
    ogg.VerificaFile(); //verifico la presenza del file lista 
    
    System.out.println("Inserisci il numero massimo dei soci: ");
    ogg.setNumSoci(ogg.getIn().nextInt());
    
    ogg.CodiciSocio(); //leggo i codici socio
    
    ogg.AltriDati(); //leggo i volumi attuali e la data
        
    ogg.GeneraOutput(); //genero l'ouput*/
    
  }
    
}

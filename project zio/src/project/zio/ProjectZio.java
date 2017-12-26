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
     * @param args the command line arguments
     */
    public static void main(String args[])
  throws IOException {
        
    FileReader f=null;
    
    String percorso="C:/Users/fedus/Desktop/lista.txt";
    
    Scanner in = new Scanner(System.in);  //variabile di input, che rappresenta la tastiera
    
    System.out.println("Inserisci il percorso del file..Es.: c:/federico/lista.txt");
    
    percorso=in.nextLine(); //memorizzo il percorso file
    
    try { //Verifico se il file esista...
            f=new FileReader(percorso);
            
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(ProjectZio.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("File non trovato");
            System.out.println(percorso+"lista.txt");
        }

    BufferedReader b;
    b=new BufferedReader(f);
    
    String s=""; //stringa di buffer
    int numSoci=0;
    
    System.out.println("Inserisci il numero massimo dei soci: ");
    numSoci=in.nextInt();

    Workbook wb = new HSSFWorkbook();  //creo file .xls mediante l'interfaccia Workbook, che è compatibile anche con gli .xlsx

    Sheet sheet1 = wb.createSheet("Nuovo Foglio"); //Creo un nuovo foglio excel

    CreationHelper createHelper = wb.getCreationHelper(); //Otteniamo una istanza di CreationHelper del nostro Workbook

    b.readLine(); //Salto la prima riga   
    
    //ciclo per la lettura di tutti i codici socio
    for(int y=1; y<numSoci+1; y++){
        
        //Setto l'intestazione "Numero Socio"
        Row rowMain = sheet1.createRow((short)0); //creo una riga in posizione 0
        Cell cellMain = rowMain.createCell (0);
        Cell cellMain1=rowMain.createCell(1);
        Cell cellMain2=rowMain.createCell(2);
        cellMain.setCellValue(createHelper.createRichTextString("Numero Socio"));
        cellMain1.setCellValue(createHelper.createRichTextString("Volume Attuale"));
        cellMain2.setCellValue(createHelper.createRichTextString("Data"));
        
        b.skip(57); //Salto i dati che non mi servono

        //memorizzo il codice in una stringa di buffer
        for(int i=0; i<=4; i++){
            s=s+((char)b.read());
        }
        
        Row row = sheet1.createRow((short)y); //creo una riga in posizione y-esima
        Cell cell = row.createCell (0); //creo una cella
        cell.setCellValue(createHelper.createRichTextString(s));
        s="";

        b.readLine(); //passo alla riga successiva
    }
    
    b.readLine(); //salto una riga
    
    //ciclo per la lettura di VolumeAttuale e Data associata
    for(int y=1; y<numSoci+1; y++){
        b.skip(33); //salto i caratteri inutili, sino ad arrivare ai volumi
        
        //memorizzo i volumi nella stringa s
        for(int i=0; i<=11; i++){
            s=s+((char)b.read());
        }
        
        //Inserisco i volumi nella cella 1 di ogni riga
        Row a = sheet1.getRow(y); //estraggo la riga y-esima dal foglio creato prima

        
        Cell cell1 = a.createCell (1); //creo una cella
        cell1.setCellValue(createHelper.createRichTextString(s)); //inserisco il valore di s nella cella
        s="";
        
        b.skip(25); //salto i caratteri inutili, sino ad arrivare alle date
        
        //memorizzo le date in una stringa s
        for(int i=0; i<=9; i++){
            s=s+((char)b.read());
        }
        
        //Inserisco le date nella cella 2 di ogni riga
        Cell cell2 = a.createCell (2); //creo una cella
        cell2.setCellValue(createHelper.createRichTextString(s)); //Inserisco il valore di s nella cella
        s="";
        
        b.readLine(); //passo alla riga successiva
    }
        
    
    
    
    //Genero l'output
    //System.out.println("Inserisci il percorso di salvataggio del file .xls (Es.: C:/Deskop/prova.xls");
    //percorso=in.nextLine();
    FileOutputStream fileOut = new FileOutputStream("prova.xls");
    wb.write(fileOut);
    fileOut.close();

    //System.out.println(b);
    
  }
    
}

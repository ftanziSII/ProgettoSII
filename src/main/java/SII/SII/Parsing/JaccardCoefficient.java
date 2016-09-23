package SII.SII.Parsing;

import info.debatty.java.stringsimilarity.Jaccard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class JaccardCoefficient {
	Jaccard ja=new Jaccard(2);
	File dict = new File("/home/fabrizio/Documenti/SII/words.txt");
		
	public String getLine(String linea){
		String lineaCorretta=null;
		try{
			FileReader fw1 = new FileReader(dict);
			BufferedReader bufRead = new BufferedReader(fw1);
			String dic = null;
			double valore = 0.0;
			String[] word=linea.split(" ");
			lineaCorretta=word[0];
			for(int i=1;i<word.length;i++){
				boolean trovato=false;
				String parola=null;
				while (((dic = bufRead.readLine())!=null)||(!trovato)){
					if((ja.similarity(dic, word[i]))>valore){
						parola=linea;
						valore=ja.similarity(dic, word[i]);
					}else if(ja.similarity(dic, word[i])==1.0){
						parola=word[i];
						trovato=true;
					}
				}
				lineaCorretta=lineaCorretta.concat(parola);
			}
			bufRead.close();
		 }catch (IOException ex) {
			ex.printStackTrace();
		}
		return lineaCorretta;
	}
	
	public static void main(String[] args) {
		JaccardCoefficient jc = new JaccardCoefficient();
		String line = jc.getLine("123 This is a boook");
		System.out.println(line);
	}
	
}
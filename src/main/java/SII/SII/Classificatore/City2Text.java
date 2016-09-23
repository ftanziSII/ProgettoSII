package SII.SII.Classificatore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class City2Text {
		
	//public void city2text()throws IOException{
	public static void main(String[] args) throws IOException{
	
		FileReader input1 = new FileReader("C:/Users/PcFabrizio/Documents/SII/city2user.txt");
		BufferedReader city = new BufferedReader(input1);
		
		File out1 = new File("C:/Users/PcFabrizio/Documents/SII/city2text.txt");
		FileWriter fw1 = new FileWriter(out1,true);
		BufferedWriter bw1 = new BufferedWriter(fw1);
		
		String lineaCity=null;
		String lineaTweet=null;
		String[] cityUtenti=null;
		String[] utentiTesto=null;
		
		while ((lineaCity=city.readLine())!=null){ 
			cityUtenti = lineaCity.split("  |\\, ");
			bw1.write(cityUtenti[0]+"  ");
			for(int i=1; i<cityUtenti.length; i++){
				FileReader input2 = new FileReader("C:/Users/PcFabrizio/Documents/SII/tweet_parsati.txt");
				BufferedReader text = new BufferedReader(input2);
				while((lineaTweet=text.readLine())!=null){
					utentiTesto = lineaTweet.split(" ");
					if(cityUtenti[i].equals(utentiTesto[0])){
						for(int y=1; y<utentiTesto.length; y++){
							bw1.write(utentiTesto[y]+" ");
						}
						bw1.write(", ");					
					}
					
				}
				text.close();				
			}
			bw1.write("\n");
			bw1.flush();
		}
		bw1.close();
		city.close();
	}

}


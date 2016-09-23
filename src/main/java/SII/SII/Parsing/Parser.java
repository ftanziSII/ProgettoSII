package SII.SII.Parsing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import weka.core.Stopwords;

public class Parser {
	public static void main( String[] args ) throws Exception {
		
		FileReader input = new FileReader("/home/fabrizio/Documenti/SII/training_set_tweets.txt");
		BufferedReader bufRead = new BufferedReader(input);
		
		File out1 = new File("/home/fabrizio/Documenti/SII/tweet_parsati.txt");
		FileWriter fw1 = new FileWriter(out1,true);
		BufferedWriter bw1 = new BufferedWriter(fw1);
		
		String tweetID= "	\\d{2,10}	";
		String data="\\d{4}-\\d{2}-\\d{2} ";
		String ora="\\d{2}:\\d{2}:\\d{2}";
		String email ="\\S{1,}@\\S{1,}\\.(\\w{2,4})";
		String url ="((mailto\\:|(news|(ht|f)tp(s?))\\://){1}\\S+)|(http(s)?://)?([\\w-]+\\.)+[\\w-]+[\\w-]+[\\.]+[\\.com]+([./?%&=]*)?";
		String at="@\\S{1,} " ;
		String punteggiatura = "[^a-zA-Z0-9'\\s]"; //rimuove tutto quello che non è alfanumerico, spazio bianco e '
		String notAscii = "[^\\x1F-\\x7F]+";
		String regex = tweetID+"|"+data+"|"+ora+"|"+email+"|"+url+"|"+notAscii+"|"+at+"|"+punteggiatura;
		
	
		JazzySpellChecker jazzySpellChecker = new JazzySpellChecker();
		Stopwords stop = new Stopwords();
		
		String linea = null;
		String[] word = null;
		
		while ((linea = bufRead.readLine())!=null){
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(linea);
			String lineaFinale=null;
			linea = linea.replaceAll(regex, " ");
			linea = jazzySpellChecker.getCorrectedLine(linea);
			word = linea.split(" ");
			if(word.length>1){
				lineaFinale = word[0]+" ";
				for (int i=1;i<=word.length-1;i++){
					if (stop.is(word[i]))word[i]="";
					lineaFinale=lineaFinale.concat(word[i]+" ");
				}
				//System.out.println(lineaFinale.replaceAll("\\s+", " "));
				bw1.write(lineaFinale.replaceAll("\\s+", " "));
				bw1.write("\n");
				bw1.flush();
			}			
		}
		bw1.close();
		bufRead.close();	
	}
}

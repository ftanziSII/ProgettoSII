package SII.SII.Classificatore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CittaUtenti {
	
	public void  city2user() throws IOException {
		
		FileReader input = new FileReader("/home/fabrizio/Documenti/SII/training_set_users.txt");
		BufferedReader bufRead = new BufferedReader(input);
		
		File out1 = new File("/home/fabrizio/Documenti/SII/city2user.txt");
		FileWriter fw1 = new FileWriter(out1,true);
		BufferedWriter bw1 = new BufferedWriter(fw1);
		
		String linea=null;
		String[] campo=null;
		
		List<String> tmp;
		Map<String, List<String>> mappa;

		mappa = new HashMap<String, List<String>>();

		while ((linea = bufRead.readLine())!=null){
			campo=linea.split("	|\\,"); 
			tmp = mappa.get(campo[1]);
			if (tmp==null) tmp = new ArrayList<String>();
			tmp.add(campo[0]);
			mappa.put(campo[1], tmp);
		}
		
		Set<String> key=mappa.keySet();
		for(String chiave : key){
			bw1.write(chiave+"  ");
			List<String>user=mappa.get(chiave);
			bw1.write(user.get(0));
			user.remove(0);
			for(String utente : user){
				bw1.write(", " +utente);
			}
			bw1.write("\n");
			bw1.flush();
		}
		bw1.close();
		bufRead.close();
	}

		

}

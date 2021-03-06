package it.polito.tdp.spellchecker.model;

import java.io.*;

public class EnglishDictionary extends Dictionary {

	public String toString(){
		return "English";
	}
	
	@Override
	public void loadDictionary() {

		try {
			FileReader fr = new FileReader("rsc/English.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
			// Aggiungere word alla struttura dati
				
				this.addParola(word.toLowerCase().trim());
			
			}
			br.close();
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
		
	}

}

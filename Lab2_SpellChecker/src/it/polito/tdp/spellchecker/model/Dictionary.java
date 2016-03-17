package it.polito.tdp.spellchecker.model;

import java.util.*;

public abstract class Dictionary {

	public List<String> dizionario= new ArrayList<String>();
	
	public void addParola(String s){
		dizionario.add(s);
	}

	public abstract void loadDictionary();
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		List<RichWord> ris=new ArrayList<RichWord>();
		
		for(String s:inputTextList){
			//controllare se le stringhe s hanno dei caratteri di punteggiatura
			
			//aggiungo al risultato
			if(dizionario.contains(s.trim())){
				ris.add(new RichWord(s.trim(),true));
			}else{
				ris.add(new RichWord(s.trim(),false));
			}
		}
		
		return ris;	
	}
}

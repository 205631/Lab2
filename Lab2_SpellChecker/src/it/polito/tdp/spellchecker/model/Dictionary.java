package it.polito.tdp.spellchecker.model;

import java.util.*;

public abstract class Dictionary {

	List<RichWord> dizionario= new ArrayList<RichWord>();
	
	public abstract String loadDictionary();
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		List<RichWord> ris=new ArrayList<RichWord>();
		
		for(String s:inputTextList){
			RichWord r=new RichWord(s,true);
			
			if(dizionario.contains(r)){
				ris.add(r);
			}else{
				r.setCorretta(false);
				ris.add(r);
			}
		}
		
		return ris;	
	}
}

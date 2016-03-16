package it.polito.tdp.spellchecker.model;

import java.util.*;

public class TestModel {

	public static void main(String[] args) {
		
		Dictionary d=new ItalianDictionary();
		d.loadDictionary();
		
		//for(String s:d.dizionario){
		//System.out.println(s);
		//}
		List<String> temp= new LinkedList<String>();
		temp.add("ciao ");
		temp.add("come ");
		temp.add("stai");
		System.out.println(d.spellCheckText(temp));
		
	}

}

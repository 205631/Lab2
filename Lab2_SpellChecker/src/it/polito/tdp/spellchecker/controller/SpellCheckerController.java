package it.polito.tdp.spellchecker.controller;

import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;



public class SpellCheckerController {
	
	ItalianDictionary id;
	EnglishDictionary ed;
	
	public void setModel(ItalianDictionary id,EnglishDictionary ed){
		this.id=id;
		this.ed=ed;
		
		cmbLang.getItems().add(id);
        cmbLang.getItems().add(ed);
        cmbLang.setValue(id);
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Dictionary> cmbLang;

    @FXML
    private TextArea txtIn;

    @FXML
    private Button btmSpellCheck;

    @FXML
    private TextArea txtOut;
    
    @FXML
    private TextFlow txtOutFlow;

    @FXML
    private Label lblErr;

    @FXML
    private Button btmClearText;

    @FXML
    private Label lblTime;

    @FXML
    void doClearText(ActionEvent event) {
    	//txtOut.setText("");
    	txtOutFlow.getChildren().clear();
    	txtIn.setText("");
    	
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	txtOutFlow.getChildren().clear();
    	
    	Dictionary temp=cmbLang.getValue();
    	boolean pFalse=false;
    	//prendo il testo e lo divido in stringhe separate dal carattere 
    	//di spaziatura e poi le crica in una lista
    	
    	String s=txtIn.getText().replaceAll("\\p{Punct}", "").toLowerCase();
    	String[]p=s.split(" ");
    	
    	List<String> parole=new ArrayList<String>();
    	
    	for(int i=0;i<p.length;i++){
    		parole.add(p[i]);
    	}
    	//richiamo il controllo ortografico e stampo il risultato
    	
    	long t0=System.nanoTime();
    	
    	if(temp instanceof ItalianDictionary){
        	temp.loadDictionary();
        	List<RichWord> l=temp.spellCheckText(parole);
        	/*s="";
        	for(RichWord r:l){
        		if(r.isCorretta()==false && pFalse==false)
        			pFalse=true;
        		s+=r.getParola()+" ";
        	}*/
        	//txtOut.setText(s);
        	
        	for(RichWord r:l){
        		if(r.isCorretta()==false && pFalse==false)
        			pFalse=true;
        		if(r.isCorretta()==false){
        			Text t=new Text(r.getParola()+" ");
        			t.setFill(javafx.scene.paint.Color.RED);
        			txtOutFlow.getChildren().add(t);
        		}else{
        			Text t=new Text(r.getParola());
        			txtOutFlow.getChildren().add(t);
        		}
        	}

    	}
    	
    	if(temp instanceof EnglishDictionary){
        	temp.loadDictionary();
        	List<RichWord> l=temp.spellCheckText(parole);
        	/*s="";
        	for(RichWord r:l){
        		if(r.isCorretta()==false && pFalse==false)
        			pFalse=true;
        		s+=r.getParola()+" ";
        	}
        	txtOut.setText(s);*/
        	
        	for(RichWord r:l){
        		if(r.isCorretta()==false && pFalse==false)
        			pFalse=true;
        		if(r.isCorretta()==false){
        			Text t=new Text(r.getParola()+" ");
        			t.setFill(javafx.scene.paint.Color.RED);
        			txtOutFlow.getChildren().add(t);
        		}else{
        			Text t=new Text(r.getParola());
        			txtOutFlow.getChildren().add(t);
        		}
        	}
        	
        }
    	long t1=System.nanoTime();
    	if(pFalse==true){
    		lblErr.setText("Il testo contiene errori");
    		lblTime.setText(String.format("Spell check completed in %fs seconds", (t1-t0)/1e9));
    	}else{
    		lblErr.setText("Il testo è corretto");
    		lblTime.setText(String.format("Spell check completed in %fs seconds", (t1-t0)/1e9));
    	}
    	
    }

    @FXML
    void initialize() {
        assert cmbLang != null : "fx:id=\"cmbLang\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtIn != null : "fx:id=\"txtIn\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btmSpellCheck != null : "fx:id=\"btmSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtOut != null : "fx:id=\"txtOut\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblErr != null : "fx:id=\"lblErr\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btmClearText != null : "fx:id=\"btmClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        
        
    }
}

package it.polito.tdp.spellchecker.controller;

import java.util.*;

import javax.swing.JComboBox;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.beans.binding.Bindings;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SpellCheckerController {

    @FXML
    private TextArea txtScrittura;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtLettura;

    @FXML
    private TextField txtConteggioErrori;
    

    @FXML
    private TextField tempo;

    
    @FXML
    private ComboBox<?> lingua;

    @FXML
    private Button btnClear;

	private Dictionary model;
	
	   @FXML
	    void doSeleziona(ActionEvent event) {
		/*   lingua.getSelectionModel().
		   JComboBox cb = (JComboBox)event.getSource();
	        String selezione = (String)cb.getSelectedItem();
	        System.out.println(selezione); */

	    }

    @FXML
    void doClearText(ActionEvent event) {
    	this.txtLettura.clear();
    	this.txtConteggioErrori.clear();
    	this.txtScrittura.clear();

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	this.txtLettura.clear();
    	String testo=this.txtScrittura.getText();
    	String[] temp=testo.split(" ");
    	LinkedList<String> parole=new LinkedList<String>();
    	String linguascelta= (String) lingua.getValue();
    	model.loadDictionary(linguascelta);
    	for(int i=0;i<temp.length;i++) {
    		parole.add(temp[i]);
    	}
    	LinkedList<RichWord> correzioni=this.model.SpellCheckText(parole);
    	for(RichWord r: correzioni) {
    		if(!r.isCorretto())
    			this.txtLettura.appendText(r.getParola().toLowerCase()+"\n");
    	}

    }

	public void setModel(Dictionary model) {
		this.model=model;
		this.txtConteggioErrori.textProperty().bind(model.getSbagliFattiProperty());
		this.tempo.textProperty().bind(Bindings.convert(model.getTempoImpiegato()));
	}

}

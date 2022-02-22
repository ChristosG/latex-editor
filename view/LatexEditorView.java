package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JEditorPane;

import controller.LatexEditorController;
import model.Document;
import model.VersionsManager;
import model.TexReader;

public class LatexEditorView {
	private LatexEditorController controller;
	private Document currentDocument;
	private String type;
	private String text;
	private String filename;
	private String strategy;
	private VersionsManager versionsManager;
	private String latexCommand;
	private JEditorPane editorPane ;
	private String encryption;
	public VersionsManager getVersionsManager() {
		return versionsManager;
	}

	public void setVersionsManager(VersionsManager versionsManager) {
		this.versionsManager = versionsManager;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
	public void setEncryption(String enc) {
		this.encryption = enc;
	}
	public String getEncryption() {
		return this.encryption;
	}
	
	public void setEditorPane(JEditorPane ep) {
		this.editorPane = ep;
	}
	public JEditorPane getEditorPane() {
		return this.editorPane;
	}
	
	public void setLatexCommand(String lc) {
		this.latexCommand = lc;
	}
	public String getLatexCommand() {
		return latexCommand;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LatexEditorController getController() {
		return controller;
	}

	public void setController(LatexEditorController controller) {
		this.controller = controller;
	}

	public Document getCurrentDocument() {
		return currentDocument;
	}

	public void setCurrentDocument(Document currentDocument) {
		this.currentDocument = currentDocument;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}


}

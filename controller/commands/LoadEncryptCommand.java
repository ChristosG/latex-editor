package controller.commands;

import model.Document;
import model.TexReader;
import model.VersionsManager;
import view.LatexEditorView;

public class LoadEncryptCommand implements Command {

	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView;
	public LoadEncryptCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	public void execute() {
		this.latexEditorView = this.versionsManager.getView();
		String filename = this.latexEditorView.getFilename();
		TexReader texReader = new TexReader();
		String fileContents = texReader.texRead(filename);
		Document currentDocument = new Document();
		this.latexEditorView.setCurrentDocument(currentDocument);

		if (this.latexEditorView.getEncryption().equals("atbash")) {
			String ciphertext = fileContents;
			String plaintext = "";
			String out = "";
			for(char c : ciphertext.toLowerCase().toCharArray())
	        {
	            if(Character.isLetterOrDigit(c))
	            {
	                out += c;
	            }else {out+=c;}
	        }
			ciphertext = out;
			
	        for(char c : ciphertext.toCharArray())
	        {
	            if(Character.isLetter(c))
	            {
	                plaintext += (char) ('z' + ('a' - c));
	            }
	            else
	            {
	                plaintext += c;
	            }
	        }
	        currentDocument.setContents(plaintext);
	        fileContents = plaintext.trim();
		}
		
		if (this.latexEditorView.getEncryption().equals("rot13")) {
			String s = fileContents;
			String rot = "";
	        for (int i = 0; i < s.length(); i++) {
	            char c = s.charAt(i);
	            if       (c >= 'a' && c <= 'm') c += 13;
	            else if  (c >= 'A' && c <= 'M') c += 13;
	            else if  (c >= 'n' && c <= 'z') c -= 13;
	            else if  (c >= 'N' && c <= 'Z') c -= 13;
	            rot += c;
	        }  
	        currentDocument.setContents(rot);
	        fileContents = rot.trim();
		}
		
		String	type = "emptyTemplate";
		if (fileContents.startsWith("\\documentclass[11pt,twocolumn,a4paper]{article}")) {
			type = "articleTemplate";
		} else if (fileContents.startsWith("\\documentclass[11pt,a4paper]{book}")) {
			type = "bookTemplate";
		} else if (fileContents.startsWith("\\documentclass[11pt,a4paper]{report}")) {
			type = "reportTemplate";
		} else if (fileContents.startsWith("\\documentclass{letter}")) {
			type = "letterTemplate";
		}
		this.latexEditorView.setType(type);	
	}
}

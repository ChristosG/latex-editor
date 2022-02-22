package controller.commands;

import model.Document;
import model.VersionsManager;
import view.LatexEditorView;

public class SaveEncryptCommand implements Command {
	
	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView;
	public SaveEncryptCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	public void execute() {
		latexEditorView = this.versionsManager.getView();
		Document currentDocument = this.latexEditorView.getCurrentDocument();
		String filename = this.latexEditorView.getFilename();

		if(this.latexEditorView.getEncryption().equals("rot13")) {
			String s = currentDocument.getContents();
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
		}
		
		if(this.latexEditorView.getEncryption().equals("atbash")) {
			
			String ciphertext = currentDocument.getContents();
			
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
		}
		currentDocument.save(filename);
	}
}

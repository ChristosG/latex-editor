package controller.commands;

import model.VersionsManager;
import view.*;
import model.Document;
public class SaveCommand implements Command {
	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView;

	public SaveCommand(VersionsManager versionsManager) {
		// TODO Auto-generated constructor stub
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {

	    latexEditorView = this.versionsManager.getView();
		Document currentDocument = this.latexEditorView.getCurrentDocument();
		String filename = this.latexEditorView.getFilename();
		currentDocument.save(filename);
	}
}

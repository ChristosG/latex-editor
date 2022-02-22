package controller.commands;

import model.Document;
import model.TexReader;
import model.VersionsManager;
import view.*;
import model.*;
public class LoadCommand implements Command {
	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView;
	public LoadCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	public VersionsManager getVersionsManager() {
		return versionsManager;
	}

	public void setVersionsManager(VersionsManager versionsManager) {
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {
	
		this.latexEditorView = this.versionsManager.getView();
		String filename = this.latexEditorView.getFilename();
		TexReader texReader = new TexReader();
		String fileContents = texReader.texRead(filename);
		
		Document currentDocument = new Document();
		this.latexEditorView.setCurrentDocument(currentDocument);
		currentDocument.setContents(fileContents);

		String	type = "emptyTemplate";

		fileContents = fileContents.trim();
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

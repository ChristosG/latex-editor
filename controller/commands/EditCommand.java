package controller.commands;
import model.Document;
import model.VersionsManager;
import view.*;
public class EditCommand implements Command {
	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView;
	public EditCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {

		this.latexEditorView = this.versionsManager.getView();
		Document currentDocument = this.latexEditorView.getCurrentDocument();
		
		if (versionsManager.isEnabled()) {
			versionsManager.putVersion(currentDocument);
			currentDocument.changeVersion();
		}
		
		currentDocument.setContents(this.latexEditorView.getText());
	}	
}

package controller.commands;

import javax.swing.JOptionPane;

import model.Document;
import model.VersionsManager;
import model.strategies.VersionsStrategy;
import view.LatexEditorView;
public class RollbackToPreviousVersionCommand implements Command {
	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView;
	public RollbackToPreviousVersionCommand(VersionsManager versionsManager) {
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {

		this.latexEditorView = this.versionsManager.getView();
		VersionsStrategy strategy = this.versionsManager.getStrategy();
		
		if (this.versionsManager.isEnabled() == false) {
			JOptionPane.showMessageDialog(null, "Strategy is not enabled", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Document doc = strategy.getVersion();
			if (doc == null) {
				JOptionPane.showMessageDialog(null, "No version available", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			} else {
				strategy.removeVersion();
				latexEditorView.setCurrentDocument(doc);
			}
		}
		
	}

}

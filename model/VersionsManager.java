package model;

import javax.swing.JOptionPane;

import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;
import view.LatexEditorView;

public class VersionsManager {
	private boolean enabled;
	private VersionsStrategy strategy;
	private LatexEditorView latexEditorView;

	public VersionsManager(VersionsStrategy versionsStrategy, LatexEditorView latexEditorView) {
		this.strategy = versionsStrategy;
		this.latexEditorView = latexEditorView;
	}
	
	
	public LatexEditorView getView() {
		return this.latexEditorView;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}

	public void setStrategy(VersionsStrategy strategy) {
		this.strategy = strategy;
	}

	public void setCurrentVersion(Document document) {
		latexEditorView.setCurrentDocument(document);
	}

	public void putVersion(Document document) {
		// TODO Auto-generated method stub
		strategy.putVersion(document);
	}

	public VersionsStrategy getStrategy() {
		// TODO Auto-generated method stub
		return strategy;
	}
}

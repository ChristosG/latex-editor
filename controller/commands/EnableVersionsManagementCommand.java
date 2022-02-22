package controller.commands;

import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;
import view.LatexEditorView;
public class EnableVersionsManagementCommand implements Command {
	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView;
	public EnableVersionsManagementCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	
		this.latexEditorView = this.versionsManager.getView();
		VersionsStrategy strategy = this.versionsManager.getStrategy();
		String strategyType = latexEditorView.getStrategy();
		if (strategyType.equals("volatile") && strategy instanceof VolatileVersionsStrategy) {
			this.versionsManager.enable();
		} else if (strategyType.equals("stable") && strategy instanceof VolatileVersionsStrategy) {
			VersionsStrategy newStrategy = new StableVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			this.versionsManager.enable();
		} else if (strategyType.equals("volatile") && strategy instanceof StableVersionsStrategy) {
			VersionsStrategy newStrategy = new VolatileVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			this.versionsManager.enable();
		} else if (strategyType.equals("stable") && strategy instanceof StableVersionsStrategy) {
			this.versionsManager.enable();
		}
	}
	
	

	
}

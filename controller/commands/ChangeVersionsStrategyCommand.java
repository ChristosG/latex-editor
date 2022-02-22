package controller.commands;

import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;
import view.LatexEditorView;
public class ChangeVersionsStrategyCommand implements Command {
	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView;
	public ChangeVersionsStrategyCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {

		this.latexEditorView = this.versionsManager.getView();
		VersionsStrategy strategy = this.versionsManager.getStrategy();
		
		String strategyType = latexEditorView.getStrategy();
		if (strategyType.equals("stable") && strategy instanceof VolatileVersionsStrategy) {
			VersionsStrategy newStrategy = new StableVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			this.versionsManager.enable();
			this.versionsManager.setStrategy(strategy);

		} else if (strategyType.equals("volatile") && strategy instanceof StableVersionsStrategy) {
			VersionsStrategy newStrategy = new VolatileVersionsStrategy();
			newStrategy.setEntireHistory(strategy.getEntireHistory());
			strategy = newStrategy;
			this.versionsManager.enable();
			this.versionsManager.setStrategy(strategy);
		}
		
	}
	
}

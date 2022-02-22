package controller;

import java.util.HashMap;

import controller.commands.AddLatexCommand;
import controller.commands.ChangeVersionsStrategyCommand;
import controller.commands.Command;
import controller.commands.CommandFactory;
import controller.commands.CreateCommand;
import controller.commands.DisableVersionsManagementCommand;
import controller.commands.EditCommand;
import controller.commands.EnableVersionsManagementCommand;
import controller.commands.LoadCommand;
import controller.commands.RollbackToPreviousVersionCommand;
import controller.commands.SaveCommand;
import model.VersionsManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LatexEditorController {
	private HashMap<String, Command> commands;
	ArrayList<String> commandNames = new ArrayList<String>();
	List<String> names = Arrays.asList("addLatex","changeVersionsStrategy", "create","disableVersionsManagement","edit","enableVersionsManagement","load","rollbackToPreviousVersion","save","LoadEncrypt","SaveEncrypt");
	
	public LatexEditorController(VersionsManager versionsManager) {
		commandNames.addAll(names);
		CommandFactory commandFactory = new CommandFactory(versionsManager);
		commands = new HashMap<String, Command>();
		for(String s : commandNames) {
			commands.put(s, commandFactory.createCommand(s));
		}

	}

	public void enact(String command) {
		commands.get(command).execute();
	}
}

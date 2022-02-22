package controller.commands;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JEditorPane;
import model.VersionsManager;
import view.LatexEditorView;
public class AddLatexCommand implements Command {
	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView;
	public AddLatexCommand(VersionsManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.latexEditorView = this.versionsManager.getView();
		JEditorPane editorPane = this.latexEditorView.getEditorPane();
		String contents = latexEditorView.getText();
		String before = contents.substring(0, editorPane.getCaretPosition());
		String after = contents.substring(editorPane.getCaretPosition());
		String type = this.latexEditorView.getLatexCommand();
		
		ArrayList<String> latexCommands = new ArrayList<String>();
		List<String> commandType = Arrays.asList("\n\\chapter{...}" ,"\n\\section{...}", "\n\\subsection{...}","\n\\subsubsection{...}",
				"\\begin{enumerate}\n" + "\\item ...\n" + "\\item ...\n" + "\\end{enumerate}\n",
				"\\begin{itemize}\n" + "\\item ...\n" + "\\item ...\n" + "\\end{itemize}\n" ,
				"\\begin{table}\n" + "\\caption{....}\\label{...}\n" + "\\begin{tabular}{|c|c|c|}\n"
						+ "\\hline\n" + "... &...&...\\\\\n" + "... &...&...\\\\\n" + "... &...&...\\\\\n" + "\\hline\n"
						+ "\\end{tabular}\n" + "\\end{table}\n" ,
						"\\begin{figure}\n" + "\\includegraphics[width=...,height=...]{...}\n"
								+ "\\caption{....}\\label{...}\n" + "\\end{figure}\n");
		
		ArrayList<String> typeList = new ArrayList<String>();
		List<String> typeName = Arrays.asList("chapter","section","subsection","subsubsection","enumerate","itemize","table","figure");
		latexCommands.addAll(commandType);
		typeList.addAll(typeName);
		int i = 0;
		for(String t: typeList) {
			if(type.equals(t)) {
				contents = before + latexCommands.get(i) +"\n" + after;
			}
			i++;
		}
		
		
		latexEditorView.setText(contents);
		editorPane.setText(contents);
		

	}

}

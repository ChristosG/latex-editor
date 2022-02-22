package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

class LoadEncryptCommandTest {
	private LatexEditorView latexEditorView = new LatexEditorView();
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager(null, latexEditorView);
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private LoadEncryptCommand encryptCommand = new LoadEncryptCommand(versionsManager);

	@Test
	void test() {
		latexEditorView.setEncryption("rot13");
		latexEditorView.setType("bookTemplate");
		createCommand.execute();
		latexEditorView.setFilename("./Resources/aaaaaaa.texR");
		
		encryptCommand.execute();
		String contentsBook = "\\documentclass[11pt,a4paper]{book}\n" + 
				"\n" + 
				"\\begin{document}\n" + 
				"\\title{Book: How to Structure a LaTeX Document}\n" + 
				"\\author{Author1 \\and Author2 \\and ...}\n" + 
				"\\date{\\today}\n" + 
				"\n" + 
				"\\maketitle\n" + 
				"\n" + 
				"\\frontmatter\n" + 
				"\n" + 
				"\\chapter{Preface}\n" + 
				"% ...\n" + 
				"\n" + 
				"\\mainmatter\n" + 
				"\\chapter{First chapter}\n" + 
				"\\section{Section Title 1}\n" + 
				"\\section{Section Title 2}\n" + 
				"\n" + 
				"\\section{Section Title.....}\n" + 
				"\n" + 
				"\\chapter{....}\n" + 
				"\n" + 
				"\\chapter{Conclusion}\n" + 
				"\n" + 
				"\\chapter*{References}\n" + 
				"\n" + 
				"\n" + 
				"\\backmatter\n" + 
				"\\chapter{Last note}\n" + 
				"\n" + 
				"\\end{document}\n"+
				"\n";
		
		String actualContents = latexEditorView.getCurrentDocument().getContents();
		assertEquals(contentsBook, actualContents);
	}

}

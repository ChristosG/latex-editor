package controller.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

class SaveEncryptCommandTest {
	private LatexEditorView latexEditorView = new LatexEditorView();
	private DocumentManager documentManager = new DocumentManager();
	private VersionsManager versionsManager = new VersionsManager(null, latexEditorView);
	private CreateCommand createCommand = new CreateCommand(documentManager, versionsManager);
	private SaveEncryptCommand encryptCommand = new SaveEncryptCommand(versionsManager);

	@Test
	void test() {
		latexEditorView.setEncryption("rot13");
		latexEditorView.setType("bookTemplate");
		createCommand.execute();
		latexEditorView.setFilename("./Resources/testRot13.texR");
		encryptCommand.execute();
		
		String contentsBook = "\\qbphzragpynff[11cg,n4cncre]{obbx}\n" + 
				"\n" + 
				"\\ortva{qbphzrag}\n" + 
				"\\gvgyr{Obbx: Ubj gb Fgehpgher n YnGrK Qbphzrag}\n" + 
				"\\nhgube{Nhgube1 \\naq Nhgube2 \\naq ...}\n" + 
				"\\qngr{\\gbqnl}\n" + 
				"\n" + 
				"\\znxrgvgyr\n" + 
				"\n" + 
				"\\sebagznggre\n" + 
				"\n" + 
				"\\puncgre{Cersnpr}\n" + 
				"% ...\n" + 
				"\n" + 
				"\\znvaznggre\n" + 
				"\\puncgre{Svefg puncgre}\n" + 
				"\\frpgvba{Frpgvba Gvgyr 1}\n" + 
				"\\frpgvba{Frpgvba Gvgyr 2}\n" + 
				"\n" + 
				"\\frpgvba{Frpgvba Gvgyr.....}\n" + 
				"\n" + 
				"\\puncgre{....}\n" + 
				"\n" + 
				"\\puncgre{Pbapyhfvba}\n" + 
				"\n" + 
				"\\puncgre*{Ersreraprf}\n" + 
				"\n" + 
				"\n" + 
				"\\onpxznggre\n" + 
				"\\puncgre{Ynfg abgr}\n" + 
				"\n" + 
				"\\raq{qbphzrag}\n" + 
				"\n";
		
		String actualContents = latexEditorView.getCurrentDocument().getContents();
		assertEquals(contentsBook, actualContents);
	}

}

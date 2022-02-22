package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.LatexEditorController;
import model.VersionsManager;
import model.strategies.VersionsStrategy;
import model.strategies.VolatileVersionsStrategy;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OpeningWindow {

	private JFrame frame;
	private LatexEditorView latexEditorView;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpeningWindow window = new OpeningWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpeningWindow() {
		VersionsStrategy versionsStrategy = new VolatileVersionsStrategy();
		latexEditorView = new LatexEditorView();
		VersionsManager versionsManager = new VersionsManager(versionsStrategy, latexEditorView);
		LatexEditorController controller = new LatexEditorController(versionsManager);
		latexEditorView.setController(controller);
		latexEditorView.setVersionsManager(versionsManager);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnCreateNewDocument = new JButton("Create New Document");
		btnCreateNewDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseTemplate chooseTemplate = new ChooseTemplate(latexEditorView, "opening");
				frame.dispose();
			}
		});
		btnCreateNewDocument.setBounds(89, 26, 278, 36);
		frame.getContentPane().add(btnCreateNewDocument);

		JButton btnOpenExistingDocument = new JButton("Open Existing Document");
		btnOpenExistingDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				 chooser.setCurrentDirectory(new java.io.File("./Resources")); 
				int option = chooser.showOpenDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					String filename = chooser.getSelectedFile().toString();
					
					latexEditorView.setFilename(filename);
					latexEditorView.getController().enact("load");
					MainWindow mainWindow = new MainWindow(latexEditorView);
					//mainWindow.setEditorPane(latexEditorView.getCurrentDocument().getContents());
				}
			}
		});
		btnOpenExistingDocument.setBounds(89, 92, 278, 36);
		frame.getContentPane().add(btnOpenExistingDocument);
		
		JButton mntmEncryptedLoad = new JButton("Load Encryption");
		mntmEncryptedLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setCurrentDirectory(new java.io.File("./Resources")); 
				int option = filechooser.showOpenDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					String filename = filechooser.getSelectedFile().toString();
					
					if(filename.endsWith(".texR") != true && filename.endsWith(".texA") != true) {
						JOptionPane.showMessageDialog(null, "This file is not encrypted!", "Try Again!", JOptionPane.INFORMATION_MESSAGE);
					}else {
						if (filename.endsWith(".texR") == true) {
							latexEditorView.setEncryption("rot13");
						}
						if (filename.endsWith(".texA") == true) {
							latexEditorView.setEncryption("atbash");
						}
						latexEditorView.setFilename(filename);
						latexEditorView.getController().enact("LoadEncrypt");
						MainWindow mainWindow = new MainWindow(latexEditorView);
					}
				}
			}
		});
		mntmEncryptedLoad.setBounds(89, 158, 278, 36);
		frame.getContentPane().add(mntmEncryptedLoad);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(110, 210, 240, 25);
		frame.getContentPane().add(btnExit);
	}
}

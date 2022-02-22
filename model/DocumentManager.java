package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DocumentManager {
	private HashMap<String, Document> templates;
	
	private ArrayList<String> templateNames = new ArrayList<String>();
	private List<String> names = Arrays.asList("reportTemplate","bookTemplate", "articleTemplate","letterTemplate","emptyTemplate");
	
	private String contents;
	
	public DocumentManager() {
		templates = new HashMap<String, Document>();
		templateNames.addAll(names); 
		
		for(String s : templateNames) {
			Document document = new Document();
			document.setContents(getContents(s));
			templates.put(s, document);
		}
		
		
	}

	public Document createDocument(String type) {
		return templates.get(type).clone();
	}

	public String getContents(String type) {
		TexReader texFile = new TexReader();
		return texFile.texRead("./Resources/"+type+".tex");
	}
}

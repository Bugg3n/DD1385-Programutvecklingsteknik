import javax.swing.*;
import javax.swing.tree.*;

import java.awt.Color;
import java.io.*;

class LifeTree extends TreeFrame {

	static String directory = "./Liv.xml";

	// Overrides method in TreeFrame
	@Override
	void initTree() {
		try {
			buildTree();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	// New method
	private void buildTree() throws IOException {
		XMLReader reader = new XMLReader(new PushbackReader(new FileReader(new File(directory))));
		String rootStartTag = reader.readRootStartTag();
		String rootText = reader.readNextNonEmptyObject();

		if (XMLReader.getType(rootStartTag) == XMLReader.START_TAG) {
			System.out.println("Root: " + rootStartTag);
			System.out.println("Root Text: " + rootText);
			root = new LifeMutableTreeNode(XMLReader.getTagAttribute(rootStartTag, "namn"), 
										   rootText,
										   XMLReader.getTagName(rootStartTag));
			treeModel = new DefaultTreeModel(root);
			tree = new JTree(treeModel);
			tree.setBackground(Color.GREEN);
			buildTree(reader, root);
		} else {
			throw new IOException("Error: expected start tag, got " + rootStartTag);
		}
	}

	// New method
	private void buildTree(XMLReader reader, DefaultMutableTreeNode parent) throws IOException {
		String nextTag = reader.readNextNonEmptyObject();
		while (XMLReader.getType(nextTag) == XMLReader.START_TAG) {
			String nextText = reader.readNextNonEmptyObject();
			LifeMutableTreeNode childNode = new LifeMutableTreeNode(XMLReader.getTagAttribute(nextTag, "namn"), 
																	nextText,
																	XMLReader.getTagName(nextTag));
			parent.add(childNode);
			buildTree(reader, childNode);
			nextTag = reader.readNextNonEmptyObject();
		}
	}

	// Overrides method in TreeFrame
	@Override
	void showDetails(TreePath p) {
		if (p == null)
			return;
		LifeMutableTreeNode node = (LifeMutableTreeNode) p.getLastPathComponent();
		String extraMessage = "Men allt som är " + node.getName();
		while (node.getParent() != null) {
			node = (LifeMutableTreeNode) node.getParent();
			extraMessage += " är " + node.getName();
		}
		JOptionPane.showMessageDialog(this, node.getType() + ": " + 
											node.getName() + " " + 
											node.getText() + "\n" + 
											extraMessage);
	}

	public static void main(String[] args) {
		if (args.length > 0)
			directory = args[0];
		new LifeTree();
	}
}

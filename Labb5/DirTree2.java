import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;

class DirTree2 extends TreeFrame {

	static String directory = ".";

	// Overrides method in TreeFrame
	void initTree() {
		root = new DefaultMutableTreeNode(directory);
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		buildTree();
	}

	// New method
	private void buildTree() {
		File f = new File(directory);
		String[] list = f.list();
		for (int i = 0; i < list.length; i++)
			buildTree(new File(f, list[i]), root);
	}

	// New method
	private void buildTree(File f, DefaultMutableTreeNode parent) {
		DefaultMutableTreeNode child = new DefaultMutableTreeNode(f.toString());
		parent.add(child);
		if (f.isDirectory()) {
			String list[] = f.list();
			for (int i = 0; i < list.length; i++)
				buildTree(new File(f, list[i]), child);
		}
	}

	// Overrides method in TreeFrame
	void showDetails(TreePath p) {
		if (p == null)
			return;
		File f = new File(p.getLastPathComponent().toString());
		JOptionPane.showMessageDialog(this, f.getPath() +
				"\n   " +
				getAttributes(f));
	}

	// New method
	private String getAttributes(File f) {
		String t = "";
		if (f.isDirectory())
			t += "Directory";
		else
			t += "Nondirectory file";
		t += "\n   ";
		if (!f.canRead())
			t += "not ";
		t += "Readable\n   ";
		if (!f.canWrite())
			t += "not ";
		t += "Writeable\n  ";
		if (!f.isDirectory())
			t += "Size in bytes: " + f.length() + "\n   ";
		else {
			t += "Contains files: \n     ";
			String[] contents = f.list();
			for (int i = 0; i < contents.length; i++)
				t += contents[i] + ", ";
			t += "\n";
		}
		return t;
	}

	public static void main(String[] args) {
		if (args.length > 0)
			directory = args[0];
		new DirTree2();
	}

}

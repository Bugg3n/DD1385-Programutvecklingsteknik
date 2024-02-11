
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class XMLReader {
    
    public static final int START_TAG = 0;
    public static final int END_TAG = 1;
    public static final int TEXT = 2;
    public static final int INSTRUCTION = 3;

    private PushbackReader pr;
    private Deque<String> stack;

    public XMLReader(PushbackReader pr) {
        this.pr = pr;
        this.stack = new ArrayDeque<>();
    }

    public void unread(String s) throws IOException {
        for (int i = s.length() - 1; i >= 0; i--) {
            pr.unread(s.charAt(i));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        XMLReader xmlReader = new XMLReader(new PushbackReader(new FileReader(new File("./Liv.xml"))));
        try {
            String nextObject = xmlReader.readNextNonEmptyObject();
            while (nextObject != null) {
                nextObject = xmlReader.readNextNonEmptyObject();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readNextNonEmptyObject() throws IOException {
        String nextObject = readNextObject();
        while (nextObject != null && nextObject.trim().length() == 0) {
            nextObject = readNextObject();
        }
        if (nextObject == null) {
            if (stack.isEmpty())
                return null;
            else
                throw new IOException("Error: expected end tag for " + stack.peek() + ", got end of file");
        }
        if (getType(nextObject) == START_TAG) {
            stack.push(getTagName(nextObject));
        }
        if (getType(nextObject) == END_TAG) {
            if (getTagName(nextObject).equals(stack.peek()))
                stack.pop();
            else
                throw new IOException("Error: expected end tag for " + stack.peek() + ", got " + nextObject);
        }
        return nextObject;
    }

    public String readNextObject() throws IOException {
        // returns the next object which is either a tag or a text
        int nextInt = pr.read();
        if (nextInt == -1) {
            return null;
        }
        char nextChar = (char) nextInt;
        if (nextChar == '<') {
            try {
                StringBuilder sb = readUntilIncl('>');
                sb.insert(0, nextChar);
                return sb.toString();
            }
            catch (IOException e) {
                throw new IOException("End of file while searching \'>\'.");
            }
        }
        else {
            try {
                StringBuilder sb = readUntilExcl('<');
                sb.insert(0, nextChar);
                return sb.toString().trim();
            }
            catch (IOException e) {
                throw new IOException("End of file while searching \'<\'.");
            }
        }
    }

    private StringBuilder readUntilExcl(char c) throws IOException {
        StringBuilder xmlObject = new StringBuilder();
        int nextInt = pr.read();
        while (nextInt != -1 && (char) nextInt != c) {
            xmlObject.append((char) nextInt);
            nextInt = pr.read();
        }
        if (nextInt != -1) {
            pr.unread((char) nextInt);
        } else {
            throw new IOException("End of file");
        }
        return xmlObject;
    }

    private StringBuilder readUntilIncl(char c) throws IOException {
        StringBuilder xmlObject = readUntilExcl(c);
        pr.read();
        xmlObject.append(c);
        return xmlObject;
    }


    public String readRootStartTag() throws IOException {
        String rootStartTag = readNextNonEmptyObject();
		while (XMLReader.getType(rootStartTag) != XMLReader.START_TAG) {
			rootStartTag = readNextNonEmptyObject();
		}
        return rootStartTag;
    }

    public static int getType(String xmlObject) {
        // returns the type of xmlObject
        if (xmlObject.charAt(1) == '?') {
            return INSTRUCTION;
        }
        if (xmlObject.charAt(1) == '/') {
            return END_TAG;
        }
        if (xmlObject.charAt(0) == '<') {
            return START_TAG;
        }
        else {
            return TEXT;
        }
    }

    public static String getTagAttribute(String xmlTag, String attributeName) {
        int index = xmlTag.indexOf(attributeName);
        if (index == -1) {
            return null;
        }
        int startIndex = xmlTag.indexOf('=', index);
        if (startIndex == -1) {
            return null;
        }
        startIndex++;
        while (xmlTag.charAt(startIndex) == ' ') {
            startIndex++;
        }
        int endIndex = startIndex;
        while (xmlTag.charAt(endIndex) != ' ' && xmlTag.charAt(endIndex) != '>') {
            endIndex++;
        }
        return xmlTag.substring(startIndex, endIndex).replace("\"", "");
    }

    public static String getTagName(String xmlTag) {
        int startIndex = 1;
        if (xmlTag.charAt(1) == '/') {
            startIndex++;
        }
        int endIndex = startIndex;
        while (xmlTag.charAt(endIndex) != ' ' && xmlTag.charAt(endIndex) != '>') {
            endIndex++;
        }
        return xmlTag.substring(startIndex, endIndex);
    }
}

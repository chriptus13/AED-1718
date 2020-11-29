package series.serie2.parte2;

public class DocEntry {
    final String fileName;
    int count = 1, docID;

    public DocEntry(String n, int id) {
        fileName = n;
        docID = id;
    }

    public void addWord() {
        count++;
    }

    @Override
    public String toString() {
        return fileName + " : " + count;
    }
}

package series.serie2.parte2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class RankingBySimilarities {
    private static int numberOfDocs;
    private static HashMap<String, Node<DocEntry>> docsWords;
    private static HashSet<String> stopWords;
    private static HashSet<WordCounter> query;

    public static void main(String[] args) {
        docsWords = new HashMap<>();
        stopWords = new HashSet<>();
        numberOfDocs = args.length - 1;
        float[] dimDocs = new float[numberOfDocs];

        loadStopWords(args[0]);

        for (int i = 1; i < args.length; i++) insertWords(args[i], i);

        calcDimDocs(dimDocs);

        Scanner input = new Scanner(System.in);
        while (true) {
            String command = input.next();
            switch (command) {
                case "ranking":
                    initQuery(input.next());
                    float[] ranking = calcRank(dimDocs);
                    printRank(ranking);
                    break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("Command not found");
            }
            input.nextLine();
        }
    }

    private static void insertWords(String fileName, int n) {
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            while (bf.ready()) {
                String[] wds = bf.readLine().split(" ");
                for (String s : wds) {
                    if (!stopWords.contains(s)) {
                        if (!docsWords.containsKey(s)) docsWords.put(s, new Node<>(new DocEntry(fileName, n)));
                        else {
                            Node<DocEntry> curr = docsWords.get(s);
                            boolean found = false;
                            for (Node<DocEntry> it = curr; !found && it != null; curr = it, it = it.next) {
                                DocEntry e = it.value;
                                if (e.fileName.equals(fileName)) {
                                    e.addWord();
                                    found = true;
                                }
                            }
                            if (!found) curr.next = new Node<>(new DocEntry(fileName, n));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadStopWords(String fileName) {
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            while (bf.ready()) {
                String[] aux = bf.readLine().split(" ");
                for (String s : aux) stopWords.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printRank(float[] ranking) {
        String aux = "Ranking:\n";

        for (int i = 0; i < ranking.length; i++) {
            float f = -1;
            int k = 0;
            for (int j = 0; j < ranking.length; j++) {
                if (ranking[j] > f) {
                    f = ranking[j];
                    k = j;
                }
            }
            if (k < ranking.length) {
                aux += "Documento " + (k + 1) + "->" + ranking[k] + '\n';
                ranking[k] = -1;
            }
        }
        System.out.println(aux);
    }

    private static float[] calcRank(float[] dimDocs) {
        float[] ranking = new float[numberOfDocs];
        Iterator<WordCounter> it = query.iterator();
        float dimQuery = 0;
        while (it.hasNext()) {
            WordCounter wc = it.next();
            int count = wc.getCount();
            dimQuery += count * count;
            if (docsWords.containsKey(wc.getWord())) {
                Node<DocEntry> nd = docsWords.get(wc.getWord());
                while (nd != null) {
                    ranking[nd.value.docID - 1] += nd.value.count;
                    nd = nd.next;
                }
            }
        }
        dimQuery = (float) Math.sqrt(dimQuery);
        for (int i = 0; i < ranking.length; i++) ranking[i] = ranking[i] / (dimDocs[i] * dimQuery);
        return ranking;
    }

    private static void calcDimDocs(float[] arr) {
        for (Map.Entry<String, Node<DocEntry>> i : docsWords.entrySet()) {
            Node<DocEntry> x = i.getValue();
            while (x != null) {
                int idx = x.value.docID - 1;
                int count = x.value.count;
                arr[idx] += count * count;
                x = x.next;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) Math.sqrt(arr[i]);
        }
    }

    private static void initQuery(String queryName) {
        try (BufferedReader in = new BufferedReader(new FileReader(queryName))) {
            if (query == null) query = new HashSet<>();
            else query.clear();
            while (in.ready()) {
                String[] aux = in.readLine().split(" ");
                for (String s : aux) {
                    WordCounter wc = new WordCounter(s);
                    if (!query.add(wc)) query.get(wc).incCount();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class WordCounter {
        private String word;
        private int counter;

        public WordCounter(String str) {
            word = str;
            counter = 1;
        }

        public void incCount() {
            counter++;
        }

        public int getCount() {
            return counter;
        }

        public String getWord() {
            return word;
        }

        @Override
        public String toString() {
            return word + "->" + counter;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof WordCounter && ((WordCounter) obj).getWord().equals(word);
        }

        @Override
        public int hashCode() {
            return word.hashCode();
        }
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }
}
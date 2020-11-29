package series.serie1;

import java.io.*;

public class MaisProximas {
    private static final int argK = 0;
    private static final int argP = 1;
    private static final int argOutputFile = 2;
    private static final int argInputFiles = 3;

    private static class Element {
        private final String name;
        private final int count;

        public Element(String name, int count) {
            this.count = count;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + " " + count;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }
    }

    private static class FileEntry {
        private String str;
        private BufferedReader bf;

        public String getStr() {
            return str;
        }

        public BufferedReader getBuff() {
            return bf;
        }

        public FileEntry(BufferedReader b) {
            bf = b;
        }

        public String next() {
            try {
                return (str = bf.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("ERRO: Faltam argumentos!");
            System.exit(-1);
        }

        FileEntry[] files = new FileEntry[args.length - argInputFiles];
        Element[] finalRes = new Element[Integer.parseInt(args[argK])];
        String p = args[argP];
        String outPut = args[argOutputFile];
        int n = files.length;

        try {
            for (int i = 0; i < files.length; i++) {
                FileEntry v = new FileEntry(new BufferedReader(new FileReader(args[i + argInputFiles])));
                for (v.next(); v.getBuff().ready() && v.getStr().charAt(0) < p.charAt(0); v.next()) ;
                insert(v, files);
            }

            while (atLeastOneFileHaveAString(files)) {
                for (int i = 0, j = 1; i < files.length - 1 && j < files.length; ) {
                    if (files[i].getBuff().ready() && files[j].getBuff().ready() && files[i].getStr().equals(files[j].getStr())) {
                        files[j++].next();
                    } else {
                        i++;
                        j = i + 1;
                    }
                }

                if (files[0].getStr().charAt(0) > p.charAt(0)) break;
                insert(new Element(files[0].getStr(), commonPrefix(files[0].getStr(), p)), finalRes);

                for (String next = files[0].getStr(); next.equals(next = files[0].next()); ) ;

                if (files[0].getStr() == null && n > 1) exchange(files, 0, --n);
                minHeapify(files, n, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        printFinal(outPut, finalRes);
    }

    private static int commonPrefix(String a, String b) {
        int counter = 0;
        for (int i = 0; i < a.length() && i < b.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) break;
            counter++;
        }
        return counter;
    }

    private static void printFinal(String output, Element[] elms) {
        try (PrintWriter pw = new PrintWriter(output)) {
            for (Element e : elms) pw.println(e.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void insert(Element e, Element[] arr) {
        if (arr[arr.length - 1] == null) {
            int i = 0;
            for (; i < arr.length && arr[i] != null; i++) ;
            arr[i] = e;
            while (i > 0 && e.getCount() < arr[parent(i)].getCount()) {
                exchange(arr, i, parent(i));
                i = parent(i);
            }
        } else if (arr[0].getCount() < e.getCount()) {
            arr[0] = e;
            minHeapify(arr, arr.length, 0);
        }
    }

    private static void insert(FileEntry str, FileEntry[] arr) {
        if (arr[arr.length - 1] == null) {
            int i = 0;
            for (; i < arr.length && arr[i] != null; i++) ;
            arr[i] = str;
            while (i > 0 && str.getStr().compareTo(arr[parent(i)].getStr()) < 0) {
                exchange(arr, i, parent(i));
                i = parent(i);
            }
        } else if (str.getStr().compareTo(arr[0].getStr()) < 0) {
            arr[0] = str;
            minHeapify(arr, arr.length, 0);
        }
    }

    private static boolean atLeastOneFileHaveAString(FileEntry[] fileEntries) throws IOException {
        for (int i = 0; i < fileEntries.length; i++)
            if (fileEntries[i].getStr() != null) return true;
        return false;
    }

    private static void minHeapify(Element[] arr, int n, int pos) {
        int min = pos, l = left(min), r = right(min);
        if (l < n && arr[min].getCount() > arr[l].getCount()) min = l;
        if (r < n && arr[min].getCount() > arr[r].getCount()) min = r;
        if (pos != min) {
            exchange(arr, min, pos);
            minHeapify(arr, n, min);
        }
    }

    private static void minHeapify(FileEntry[] arr, int n, int pos) {
        int min = pos, l = left(min), r = right(min);
        if (l < n && arr[min].getStr().compareTo(arr[l].getStr()) > 0) min = l;
        if (r < n && arr[min].getStr().compareTo(arr[r].getStr()) > 0) min = r;
        if (pos != min) {
            exchange(arr, min, pos);
            minHeapify(arr, n, min);
        }
    }

    private static void exchange(Element[] arr, int pos, int pos2) {
        Element aux = arr[pos];
        arr[pos] = arr[pos2];
        arr[pos2] = aux;
    }

    private static void exchange(FileEntry[] arr, int pos, int pos2) {
        FileEntry aux = arr[pos];
        arr[pos] = arr[pos2];
        arr[pos2] = aux;
    }

    private static int left(int i) {
        return i * 2 + 1;
    }

    private static int right(int i) {
        return i * 2 + 2;
    }

    private static int parent(int i) {
        return (i - 1) / 2;
    }
}
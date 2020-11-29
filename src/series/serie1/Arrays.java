package series.serie1;

public class Arrays {
    public static int countEquals(int[] v1, int l1, int r1, int[] v2, int l2, int r2) {
        int count = 0;
        while (l1 <= r1 && l2 <= r2) {
            if (v1[l1] > v2[l2]) l2++;
            else if (v1[l1] < v2[l2]) l1++;
            else {
                count++;
                l1++;
                l2++;
            }
        }
        return count;
    }

    public static int printEachThreeElementsThatSumTo(int[] v, int l, int r, int s) {
        return printEachThreeElementsThatSumTo1(v, l, r, s);
    }

    public static int printEachThreeElementsThatSumTo1(int[] v, int l, int r, int s) { //O(n^3)
        int count = 0;
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                if (j == i) continue;
                for (int k = j + 1; k <= r; k++) {
                    if (k == j || k == i) continue;
                    if (v[i] + v[j] + v[k] == s) {
                        System.out.println(v[i] + "+" + v[j] + "+" + v[k] + "=" + s);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int printEachThreeElementsThatSumTo2(int[] v, int l, int r, int s) { //O(n^2.log n)
        int[] arrAux = v.clone();
        int counter = 0;
        for (int i = l; i < r; i++) {
            int min = i;
            for (int j = i + 1; j <= r; j++) {
                if (arrAux[j] < arrAux[min])
                    min = j;
            }
            int aux = arrAux[i];
            arrAux[i] = arrAux[min];
            arrAux[min] = aux;
        }

        for (int i = l; i < r - 2; i++) {
            for (int j = i + 1; j < r - 1; j++) {
                int elem = s - arrAux[j] - arrAux[i];
                int left = j, right = r;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (elem < arrAux[mid])
                        right = mid - 1;
                    if (elem > arrAux[mid])
                        left = mid + 1;
                    if (elem == arrAux[mid]) {
                        if (mid != j && mid != i) {
                            System.out.println(arrAux[i] + "+" + arrAux[j] + "+" + elem);
                            counter++;
                        }
                        break;
                    }
                }
            }
        }
        return counter;
    }

    public static int printEachThreeElementsThatSumTo3(int[] v, int l, int r, int s) { //O(n^2)
        int[] arrAux = v.clone();
        int counter = 0;
        for (int i = l; i < r; i++) {
            int min = i;
            for (int j = i + 1; j <= r; j++) {
                if (arrAux[j] < arrAux[min])
                    min = j;
            }
            int aux = arrAux[i];
            arrAux[i] = arrAux[min];
            arrAux[min] = aux;
        }

        for (int i = l; i <= r; i++) {
            int j = i + 1, k = r;
            while (j < k) {
                if (s - arrAux[i] - arrAux[j] - arrAux[k] == 0) {
                    counter++;
                    System.out.println(v[i] + "+" + v[j] + "+" + v[k] + "=" + s);
                    k--;
                } else if (s - arrAux[i] - arrAux[j] - arrAux[k] > 0) j++;
                else k--;
            }
        }
        return counter;
    }

    public static int removeIndexes(int v[], int l, int r, int[] vi, int li, int ri) { //O(n)
        if (r < l) return 0;
        if (ri < li) return r - l + 1;

        boolean copy = false;
        int i = l, count = 0, k = 0;
        for (int j = li; i <= r && j <= ri; ) {
            if (vi[j] < i) j++;
            else if (vi[j] == i) {
                if (!copy) {
                    k = i;
                    copy = true;
                }
                j++;
                i++;
                count++;
            } else {
                if (copy) v[k++] = v[i];
                i++;
            }
        }
        if (count == 0) return r - l + 1;
        for (; k < r && i <= r; k++, i++) {
            v[k] = v[i];
        }
        return r - l - count + 1;
    }


    public static String greaterCommonPrefix(String[] v, int l, int r, String word) { //O(log n)
        if (r - l + 1 == 0) return null;
        int i = 0, mid = 0, maxCounter = 0, max = r, counter = 0;
        while (l <= r) {
            if (counter == 0) mid = l + (r - l) / 2;
            if (word.charAt(i) > v[mid].charAt(i)) {
                if (counter > maxCounter || counter == maxCounter && mid > max) {
                    max = mid;
                    maxCounter = counter;
                }
                l = mid + 1;
                i = 0;
                counter = 0;
            } else if (word.charAt(i) < v[mid].charAt(i)) {
                if (counter > maxCounter || counter == maxCounter && mid > max) {
                    max = mid;
                    maxCounter = counter;
                }
                r = mid - 1;
                i = 0;
                counter = 0;
            } else {
                i++;
                if (i >= word.length() || i >= v[mid].length()) {
                    if (counter > maxCounter || counter == maxCounter && mid > max) max = mid;
                    break;
                }
                counter++;
            }
        }
        return v[max];
    }


    public static int sumGivenN(int n) { //O(n)
        int sum = 0, counter = 1, min = 1;
        for (int i = 1; min <= n / 2; ) {
            if (sum == n) {
                for (int j = min; j < i; j++) System.out.print(j + " ");
                System.out.println();
                sum -= min++;
                counter++;
            } else if (sum > n) sum -= min++;
            else sum += i++;
        }
        System.out.println(n);
        return counter;
    }

    public static int deleteMin(int[] maxHeap, int sizeHeap) { //O(n)
        if (sizeHeap < 1) return 0;
        int menor = maxHeap[0], k = 0;
        for (int i = 0; i < sizeHeap; i++)
            if (maxHeap[i] < menor) {
                menor = maxHeap[i];
                k = i;
            }
        maxHeap[k] = maxHeap[sizeHeap - 1];
        buildHeap(maxHeap, sizeHeap - 1);
        return sizeHeap - 1;
    }

    private static void buildHeap(int[] arr, int n) {
        int p = parent(n - 1);
        for (int i = p; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    private static void heapify(int[] arr, int n, int pos) {
        int max = pos, l = left(max), r = right(max);
        if (l < n && arr[max] < arr[l]) max = l;
        if (r < n && arr[max] < arr[r]) max = r;
        if (pos != max) {
            exchange(arr, max, pos);
            heapify(arr, n, max);
        }
    }

    private static void exchange(int[] arr, int pos, int pos2) {
        int aux = arr[pos];
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

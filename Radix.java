public class Radix {
    public static int length(int n) {
        int count = 0;
        while (n != 0) {
            n /= 10;
            count++;
        }
        return count;
    }

    public static int nth(int n, int col) throws IndexOutOfBoundsException {
        if (col < 0) {
            throw new IndexOutOfBoundsException(col + " index is out of bounds");
        }
        if (col >= length(n)) {
            return 0;
        }
        if (n < 0) {
            n = -n;
        }
        int temp = n / (int)(Math.pow(10, col));
        return (temp % 10);
    }

    public static void merge(SortableLinkedList original, SortableLinkedList[] buckets) {
        for (SortableLinkedList i : buckets) {
            original.extend(i);
        }
    }

    public static void radixSortSimple(SortableLinkedList data) {
        if (data.size() < 2) {
            return;
        }
        SortableLinkedList[] buckets = new SortableLinkedList[10];
        SortableLinkedList temp = new SortableLinkedList();
        int max = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) > max) {
                max = data.get(i);
            }
        }

        for (int i = 0; i < length(max); i++) {
            for (int j = 0; j < data.size(); j++) {
                buckets[nth(data.get(i), i)].add(data.get(i));
            }
            while (data.size() > 0) {
                data.remove(0);
            }
            merge(data, buckets);
        }
    }
}
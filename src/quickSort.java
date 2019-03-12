/**
 * Applies quick sort algorithm to an array of elements of type int and String
 */
public class quickSort {
    /**
     *Applies quick sort algorithm on an array of integers
     *
     * @param a the array to be sorted
     * @param start the start of the array
     * @param end the end of the array
     * @return
     */
    public int[] sortInt(int[] a, int start, int end) {

        int pivotIndex = start + (end - start) / 2;//set the location of the middle element as pivot
        int pivotValue = a[pivotIndex];

        int i = start, j = end;

        while(i <= j) {
            //find an element greater than the pivot from the left side
            while(a[i] < pivotValue) {
                i++;
            }
            //find an element lesser than the pivot from the right side
            while(a[j] > pivotValue) {
                j--;
            }
            //swap elements at positions i and j since they are misplaced
            if(i <= j) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
                j--;
            }
            //partition one side
            if(start < i) {
                //sort again
                sortInt(a, start, j);
            }
            //partition the other side
            if(end > i) {
                //sort again
                sortInt(a, i, end);
            }
        }
        return a;
    }

    /**
     * Applies quick sort algorithm on an array of Strings
     *
     * @param a
     * @param start
     * @param end
     */
    public void sortStr(String[] a, int start, int end) {

        int pivotIndex = start + (end - start) / 2;
        String pivotValue = a[pivotIndex];

        int i = start, j = end;

        while(i <= j) {

            while(a[i].compareTo(pivotValue) < 0) {
                i++;
            }

            while(a[j].compareTo(pivotValue) > 0) {
                j--;
            }

            if(i <= j) {
                String tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
                j--;
            }

            if(start < i) {
                sortStr(a, start, j);
            }

            if(end > i) {
                sortStr(a, i, end);
            }
        }
    }
}



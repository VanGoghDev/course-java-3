    package ru.firsov.kirill;

    import java.util.Arrays;

    /**
     * Hello world!
     *
     */
    public class App
    {
        public static void main( String[] args )
        {
            int[] arr = {1, 2, 4, 5, 3};
            System.out.println(Arrays.toString(task1(arr)));
        }

        static int[] task1(int[] arr) throws RuntimeException{
            boolean hasValue;
            int[] result = new int[0];
            hasValue = checkForValue(arr);

            if (!hasValue) throw new RuntimeException();

            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] != 4) {
                    result = addElement(result, arr[i]);
                } else {
                    break;
                }
            }

            reverse(result);

            return result;
        }

        private static boolean checkForValue(int[] arr) {
            for (int anArr : arr) {
                if (anArr == 4) {
                    return true;
                }
            }
            return false;
        }

        private static int[] addElement(int[] a, int e) {
            a = Arrays.copyOf(a, a.length + 1);
            a[a.length - 1] = e;
            return a;
        }

        private static void reverse(int[] arr) {
            for (int i = 0; i < arr.length>>1; i++) {
                // Меняет два элемента местами:
                arr[arr.length-1-i] ^= arr[i] ^= arr[arr.length-1-i];
                arr[i] ^= arr[arr.length-1-i];
            }
        }
    }

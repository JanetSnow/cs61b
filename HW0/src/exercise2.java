public class exercise2 {
    /**
     * Returns the maximum value from m.
     */
    public static int max(int[] m) {
        int max = 0;
        int temp = 0;
        // int i = 0;
        // while (i < m.length) {
        //     if (m[i] > max) {
        //         max = m[i];
        //         temp = max;
        //     }else {
        //         max = temp;
        //     }
        //     i++;
        // }
        for(int i = 0; i < m.length; i++){
            if(m[i] > max){
                max = m[i];
                temp = max;
            }else {
                max = temp;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        int maxNum = max(numbers);
        System.out.println(maxNum);
    }
}

public class exercise1b {
    public static void main(String[] args) {
        drawTriangle(10);
    }
    public static void drawTriangle(int N){
        int i = 1;
        while(i <= N){
            int j = 0;
            while (j<i){
                System.out.print("*");
                j++;
            }
            System.out.println();
            i++;
        }
    }
}

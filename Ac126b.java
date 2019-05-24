import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ac126b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("4文字の数字列を入力してください");
        String str = br.readLine();

        if (str.length() != 4) {
            System.out.println("4文字で入力してください");
            System.exit(0);
        }

        // 前2文字と後ろに文字を分けて、String型からint型へ変換
        int front = 0;
        int back = 0;
        try {
            front = Integer.parseInt(str.substring(0, 2));
            back = Integer.parseInt(str.substring(2, 4));
        } catch (NumberFormatException e) {
            System.out.println("数値が正しく入力されていません");
            System.exit(0);
        }

        boolean my = (front >= 1 && front <= 12);
        boolean ym = (back >= 1 && back <= 12);

        // myがtrue→MMYY、ymがtrue→YYMM、両方true→AMBIGUOS,両方false→NA、を表示
        if (my) {
            if (ym) {
                System.out.println("AMBIGUOS");
            } else {
                System.out.println("MMYY");
            }
        } else if (ym) {
            System.out.println("YYMM");
        } else {
            System.out.println("NA");
        }
    }
}
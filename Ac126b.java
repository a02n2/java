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

        // 前2文字と後ろ2文字を分けて、String型からint型へ変換
        int front = convert(str.substring(0, 2));
        int back = convert(str.substring(2, 4));

        // myがtrue→MMYY、ymがtrue→YYMM、両方true→AMBIGUOS,両方false→NA、を表示
        boolean my = isMonth(front);
        boolean ym = isMonth(back);
        if (my && ym) {
            System.out.println("AMBIGUOS");
        } else if (my) {
            System.out.println("MMYY");
        } else if (ym) {
            System.out.println("YYMM");
        } else {
            System.out.println("NA");
        }
    }

    /**
     * 汎用性を重視する為にStringからint型に変換 （変換失敗時はexit）
     * 
     * @param str 入力された数字列
     * @return 変換した数値
     */
    public static int convert(String str) {

        int num = 0;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("正しく数字列を入力してください");
            System.exit(0);
        }
        return num;
    }

    /**
     * 数値が1〜12(month)か調べる
     *　1〜12ならtrueを返し、そうでなければfalseを返す
     * 
     * @param num 前2文字か後2文字
     * @return　true,false
     */
    public static boolean isMonth(int num){
        if (num >= 1 && num <= 12){
            return true;
        } else {
            return false;
        }
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ac125a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("A=ビスケットが作られる時間,B=A秒間に作られるビスケットの枚数,T=起動時間");
        System.out.println("ABTを空白で区切って入力してください(1 <= A,B,T <= 20)");
        String str = br.readLine();
        String[] timePieces = str.split(" ", 3);

        if (timePieces.length != 3) {
            System.out.println("数値を正しく区切って入力してください");
            System.exit(0);
        }

        // 3分割したものをString型からint型に変換し代入
        int seconds = stringToInt(timePieces[0]);
        int pieces = stringToInt(timePieces[1]);
        int time = stringToInt(timePieces[2]);

        // 制約チェック
        rangeCheck(seconds, "A");
        rangeCheck(pieces, "B");
        rangeCheck(time, "T");

        // 何回作られるのか計算し、作られるビスケットの枚数を計算
        int sumPieces = pieces * (time / seconds);
        System.out.println(sumPieces);
    }

    /**
     * 汎用性を重視する為にStringからint型に変換 （変換失敗時はexit）
     * 
     * @param timePieces ビスケットが作られる時間,A秒間に作られるビスケットの枚数,起動時間
     * @return 変換した値
     */
    public static int stringToInt(String timePieces) {
        int timePieces_int = 0;
        try {
            timePieces_int = Integer.parseInt(timePieces);
        } catch (NumberFormatException e) {
            System.out.println("数値を正しく入力してください");
            System.exit(0);
        }
        return timePieces_int;
    }

    /**
     * 制約のチェックを行う
     * 1 <= ビスケットが作られる時間,A秒間に作られるビスケットの枚数,起動時間 <=20
     * 
     * @param num ビスケットが作られる時間,A秒間に作られるビスケットの枚数,起動時間
     * @param text チェック対象のタイトル
     */
    public static void rangeCheck(int num, String text) {
        if (num < 1 || num > 20) {
            System.out.println(text + "が範囲外です");
            System.exit(0);
        }
    }
}
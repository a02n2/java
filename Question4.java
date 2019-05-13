import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Question4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("整数(-10000～10000)の加減か減算を空白で区切って入力してください");
        System.out.println("演算記号は「+(和)」「-(差)」です");
        System.out.println("※複数回の演算も行えます");
        String input = br.readLine();
        String[] param = input.split(" ");
        int sum = loopCalc(param, 0, param.length);
        System.out.println(sum);
    }

    /**
     * 変数paramに格納されている計算式を変数startからカウンターが変数endに達するまでループ
     * 変数paramから演算記号、数値を取得 ()が存在すれば再帰呼び出しを行い、先に()内の処理をする
     * 加算減算の処理はcalcを呼び出す
     * 
     * @param param 入力された式
     * @param start 式の始まり
     * @param end   式の終わり
     * @return 合計値
     */
    public static int loopCalc(String[] param, int start, int end) {

        String mark = ""; // 演算記号
        int x = 0; // 入力値
        int sum = 0; // 合計値 

        for (int i = start; i < end; i++) {

            if (param[i].equals("+") || param[i].equals("-")) {
                mark = param[i];
            } else if (param[i].equals("(")) {
                int closeParen = 0;

                try {
                    closeParen = searchCloseParen(param, i); // ")"を見つける
                } catch (Exception e) { // ()が対になっていなければエラー
                    System.out.println("()を正しく入力してください");
                    System.exit(0);
                }

                // "()"内の合計値
                try {
                    sum = calc(mark, sum, loopCalc(param, i + 1, closeParen));
                } catch (Exception e) { // -10000～10000を超えるとエラー
                    System.out.println("※-10000～10000の範囲外になりました");
                    System.exit(0);
                }

                i = closeParen; // ")"の次からループ再開    
            } else {
                try {
                    x = Integer.parseInt(param[i]);
                } catch (NumberFormatException e) { // 入力された式がおかしければエラー
                    System.out.println("※式を正しく入力してください");
                    System.exit(0);
                }

                // 合計値
                try {
                    sum = calc(mark, sum, x);
                } catch (Exception e) { // -10000～10000を超えるとエラー
                    System.out.println("※-10000～10000の範囲外になりました");
                    System.exit(0);
                }
            }
        }
        return sum;
    }

    /**
     * 計算途中の合計値に数値を加算または減算
     * 
     * @param mark           入力された演算記号
     * @param sum            計算途中の合計値
     * @param x　入力された数値 @return　現在の合計値に数値を加算または減算した結果
     * @throws Exception -10000から10000を超えた時
     */
    public static int calc(String mark, int sum, int x) throws Exception {

        // 演算記号によって計算しsumに代入
        switch (mark) {
        case "":
            sum = x;
            break;
        case "+":
            sum += x;
            break;
        case "-":
            sum -= x;
            break;
        }

        if (sum < -10000 || sum > 10000) { // -10000～10000を越えたら例外発生
            throw new Exception();
        }

        return sum;
    }

    /**
     * 開始括弧に対応する終了括弧を探す
     * 
     * @param param     入力された式
     * @param openParen 開始括弧
     * @return 終了括弧
     * @throws Exception ()が対になっていない時
     */
    public static int searchCloseParen(String[] param, int openParen) throws Exception {

        int i = openParen;
        int parenCount = 0; // "()"の数をカウント

        // "("から式の最後までループし、対応する")"を見つける(countが0になる)とreturn
        for (; i < param.length; i++) {
            if (param[i].equals("(")) {
                parenCount++;
            } else if (param[i].equals(")")) {
                parenCount--;
            }

            if (parenCount == 0) {
                return i;
            }
        }

        throw new Exception(); // countが0でなければ例外発生
    }
}
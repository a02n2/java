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
        int sum = roop(param, 0, param.length);
        System.out.println(sum);
    }

    /**
     * 入力された式を始めから終わりまでループします。
     * 計算を行う別メソッドを呼び出し、最終の合計値を返します。
     * @param param 入力された式
     * @param start 式の始まり
     * @param end 式の終わり
     * @return 最終の合計値
     */
    public static int roop(String[] param, int start, int end) {

        String mark = "";   // 演算記号
        int x = 0;          // 入力値
        int sum = 0;        // 合計値 

        for (int i = start; i < end; i++) {

            if (param[i].equals("+") || param[i].equals("-")) { 
                mark = param[i];
            } else if (param[i].equals("(")) { 
                int closeParen = 0; 

                try {
                    closeParen = searchCloseParen(param, i);    // ")"を見つける
                } catch (Exception e) {     // "()"の数がおかしければエラー
                    System.out.println("()を正しく入力してください");
                    System.exit(0);
                }

                // "()"内の合計値
                sum = calc(mark, sum, roop(param, i + 1, closeParen));

                i = closeParen;    // ")"の次からループ再開    
            } else { 
                try {
                    x = Integer.parseInt(param[i]);
                } catch (NumberFormatException e) {     // 入力された式がおかしければエラー
                    System.out.println("※式を正しく入力してください");
                    System.exit(0);
                }

                // 合計値
                sum = calc(mark, sum, x);

            }
        }
        return sum;
    }

    /**
     * 計算途中の合計値に数値を加算または減算します。
     * @param mark 入力された演算記号
     * @param sum 計算途中の合計値
     * @param x　入力された数値
     * @return　現在の合計値に数値を加算または減算した結果
     */
    public static int calc(String mark, int sum, int x) {

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

        if (sum < -10000 || sum > 10000) {      // -10000～10000を越えたらエラー
            System.out.println("※-10000～10000の範囲外になりました");
            System.exit(0);
        }

        return sum;
    }

    /**
     * 開始括弧に対応する終了括弧を探します。
     * @param param 入力された式
     * @param openParen 開始括弧
     * @return 終了括弧
     * @throws Exception ()の数が合わない時
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
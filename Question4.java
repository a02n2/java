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
        int sum = calc(param, 0, param.length);
        System.out.println(sum);
    }

    // 入力された式の計算
    public static int calc(String[] param, int start, int end) {

        String mark = ""; // 演算記号
        int x = 0; // 入力値
        int sum = 0; // 合計値 

        for (int i = start; i < end; i++) {

            if (param[i].equals("+") || param[i].equals("-")) { // 入力値が"+"か"-"の場合
                mark = param[i];
            } else if (param[i].equals("(")) { // 入力値が"("の場合
                int rightParen = 0; // ")"

                try {
                    rightParen = searchRightParen(param, i); // ")"を見つける
                } catch (Exception e) { // "()"の数がおかしければエラー
                    System.out.println("()を正しく入力してください");
                    System.exit(0);
                }

                // "()"内を計算
                sum = calc1(mark, sum, calc(param, i + 1, rightParen));

                i = rightParen; // ")"の次からループ再開    
            } else { // 入力値が"+"、"-"、"("以外の場合
                try {
                    x = Integer.parseInt(param[i]);
                } catch (NumberFormatException e) { // 入力された式がおかしければエラー
                    System.out.println("※式を正しく入力してください");
                    System.exit(0);
                }

                if (x < -10000 || x > 10000) { // 入力値が-10000~10000でなければエラー
                    System.out.println("※-10000～10000で入力してください");
                    System.exit(0);
                }

                // 合計値の計算
                sum = calc1(mark, sum, x);

            }
        }
        return sum;
    }

    // 値の加算、減算
    public static int calc1(String mark, int sum, int x) {

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

        if (sum < -10000 || sum > 10000) { // 計算途中で-10000～10000を越えたらエラー
            System.out.println("※計算途中で-10000～10000の範囲外になりました");
            System.exit(0);
        }

        return sum;
    }

    // ")"をさがす
    public static int searchRightParen(String[] param, int leftparan) throws Exception {

        int i = leftparan;
        int parencount = 0; // "()"の数をカウント

        // "("から式の最後までループし")"を探す
        for (; i < param.length; i++) {
            if (param[i].equals("(")) { // "("がでてきたらcount+1
                parencount++;
            } else if (param[i].equals(")")) { // ")"がでてきたらcount-1
                parencount--;
            }

            if (parencount == 0) { // countが0(=対応する")"をみつける)とreturn
                return i;
            }
        }

        throw new Exception(); // countが0でなければ例外発生
    }
}
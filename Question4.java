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

    public static int calc(String[] param, int start, int end) {

        String mark = ""; // 演算記号
        Short x = 0; // 入力値
        int sum = 0; // 合計値
        for (int i = start; i < end; i++) {

            if ("+".equals(param[i]) || "-".equals(param[i])) { // 入力値が"+"か"-"の場合
                mark = param[i];
            } else if (param[i].equals("(")) { // 入力値が"("の場合
                int endi = endmark(param, i); // ")"を見つける

                switch (mark) { // "("の前の記号によって計算し代入
                case "+":
                    sum += calc(param, i + 1, endi);
                    break;
                case "-":
                    sum -= calc(param, i + 1, endi);
                    break;
                case "":
                    sum = calc(param, i + 1, endi);
                    break;
                }
                i = endi; // ")"の次へ
            } else { // 入力値が"+"、"-"、"("以外の場合
                try {
                    x = Short.parseShort(param[i]);
                } catch (NumberFormatException e) {
                    System.out.println("※式を正しく入力してください");
                    System.exit(0);
                }

                if (x < -10000 || x > 10000) { // 入力値が-10000~10000でなければエラー
                    System.out.println("※-10000～10000で入力してください");
                    System.exit(0);
                }

                switch (mark) { // 記号によって計算し代入
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
            }
        }
        return sum;
    }

    // ")"をさがす
    public static int endmark(String[] param, int start) {
        int i = 0;
        int count = 0;

        for (i = start; i < param.length; i++) {
            if (param[i].equals("(")) {
                count++;
            }
            if (param[i].equals(")")) {
                count--;
            }
            if (count == 0) {
                break;
            }
        }
        return i;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Question3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("整数(-10000～10000)の加減か減算を空白で区切って入力してください");
        System.out.println("演算記号は「+(和)」「-(差)」です");
        System.out.println("※複数回の演算も行えます");
        String input = br.readLine();
        String[] param = input.split(" ");

        String mark = "";   // 演算記号
        Short x = 0;        // 入力値
        int sum = 0;        // 合計値

        for (int i = 0; i < param.length; i++) {
            if ("+".equals(param[i]) || "-".equals(param[i])) {
                mark = param[i];                    // 入力値が"+"か"-"ならmarkに代入
            } else {
                try {
                    x = Short.parseShort(param[i]); // 入力値が"+"か"-"以外なら変換してxに代入
                } catch (NumberFormatException e) {
                    System.out.println("式を正しく入力してください");
                    return;
                }

                if (x < -10000 || x > 10000) {          // 入力値が-10000~10000でなければエラー
                    System.out.println("-10000～10000で入力してください");
                    return;
                }

                switch (mark) {         // 記号によって計算
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

                if (sum < -10000 || sum > 10000) {      // 計算途中で-10000～10000を越えたらエラー
                    System.out.println("※計算途中で-10000～10000の範囲外になりました");
                    return;
                }
            }
        }
        System.out.println(sum);
    }
}
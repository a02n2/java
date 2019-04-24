import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Question2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("整数(-10000〜10000)の四則演算を空白で区切って入力してください");
        System.out.println("演算記号は「+(和)」「-(差)」「*(積)」「/(商)」です");
        String input = br.readLine();
        String[] param = input.split(" ");

        short x = 0;
        short y = 0;

        try {
            x = Short.parseShort(param[0]);
            y = Short.parseShort(param[2]);
        } catch (NumberFormatException n) {
            System.out.println("-10000〜10000で入力してください");
            return;
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("正しい式を入力してください");
            return;
        }

        if (x > 10000 || x < -10000 || y > 10000 || y < -10000) {
            System.out.println("-10000〜10000で入力してください");
            return;
        }

        switch (param[1]) {
        case "+":
            System.out.println(x + y);
            break;
        case "-":
            System.out.println(x - y);
            break;
        case "*":
            System.out.println(x * y);
            break;
        case "/":
            try {
                System.out.println(x / y + "..." + x % y);
            } catch (ArithmeticException e) {
                System.out.println("０では割れません");
                return;
            }
            break;
        default:
            System.out.println("正しい式を入力してください");
            return;
        }
    }
}
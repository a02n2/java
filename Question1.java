import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.NumberFormatException;

class Question1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("整数2つを空白を挟んで-10000から10000で入力してください。");
        String input = br.readLine();
        String[] param = input.split(" ");

        short x = 0;
        short y = 0;

        try {
            x = Short.parseShort(param[0]);
            y = Short.parseShort(param[1]);
        } catch (NumberFormatException e) {
            System.out.println("-10000から10000で入力してください。");
            return;
        }

        if (x <= 10000 && x >= -10000 && y <= 10000 && y >= -10000) {
            System.out.println(x + y);
        } else {
            System.out.println("-10000から10000で入力してください。");
        }
    }
}
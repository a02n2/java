import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ac126a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("一行目に文字列の長さ、小文字にする位置を空白で区切って入力して下さい");
        System.out.println("二行目にABCからできた文字列を入力してください");
        String str = br.readLine();
        String s = br.readLine();
        String[] nk = str.split(" ", 2);

        try {
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);

            if (!(n >= 1 && n <= 50)) {
                System.out.println("文字列の長さが範囲外です");
                System.exit(0);
            }

            if (!(k >= 1 && k <= n)) {
                System.out.println("小文字に変換する位置が範囲外です");
                System.exit(0);
            }

            if (!(n == s.length())) {
                System.out.println("指定した文字列の長さと、ABCからできた文字列の長さが違います");
                System.exit(0);
            }

            if (!s.matches("[A-C]*")) {
                System.out.println("文字列はABCだけで作ってください");
                System.exit(0);
            }

            // 文字列の指定の位置を小文字に変換し、出力
            s = s.substring(0, k - 1) + s.substring(k - 1, k).toLowerCase() + s.substring(k);
            System.out.println(s);

        } catch (NumberFormatException e) {
            System.out.println("正しく入力してください");
        }
    }
}
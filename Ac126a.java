import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ac126a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("一行目に文字列の長さ、小文字にする位置を空白で区切って入力して下さい");
        System.out.println("二行目にABCからできた文字列を入力してください");
        String str = br.readLine();
        String abcStr = br.readLine();
        String[] lenLow = str.split(" ", 2);

        // 文字列の長さと小文字にする位置をint型に変換
        int length = convert(lenLow[0], "文字列の長さ");
        int lowerCase = convert(lenLow[1], "小文字にする位置");

        // 　制約チェック
        check(length, lowerCase, abcStr);

        // 文字列の指定の位置を小文字に変換し、出力
        abcStr = abcStr.substring(0, lowerCase - 1) + abcStr.substring(lowerCase - 1, lowerCase).toLowerCase()
                + abcStr.substring(lowerCase);
        System.out.println(abcStr);
    }

    /**
     * 汎用性を重視する為にStringからint型に変換 （変換失敗時はexit）
     * 
     * @param strLenLow　文字列の長さ、小文字にする位置
     * @param text　"文字列の長さ"、"小文字にする位置"の文章
     * @return　変換した値
     */
    public static int convert(String strLenLow, String text) {
        int lenLow = 0;
        try {
            lenLow = Integer.parseInt(strLenLow);
        } catch (NumberFormatException e) {
            System.out.println(text + "が正しく指定されていません");
            System.exit(0);
        }
        return lenLow;
    }

    /**
     * 制約のチェックを行う 
     * ・1<=文字列の長さ<=50 
     * ・1<=小文字にする位置<=文字列の長さ
     * ・文字列はABCからなる
     * エラーがあるとflagがtrueになり最後に強制終了
     * 
     * @param length    文字列の長さ
     * @param lowerCase 小文字にする位置
     * @param abcStr    文字列
     */
    public static void check(int length, int lowerCase, String abcStr) {

        boolean err_flg = false;

        if (length < 1 || length > 50) {
            System.out.println("文字列の長さが範囲外です");
            err_flg = true;
        }

        if (lowerCase < 1 || lowerCase > length) {
            System.out.println("小文字に変換する位置が範囲外です");
            err_flg = true;
        }

        if (length != abcStr.length()) {
            System.out.println("指定した文字列の長さと、ABCからできた文字列の長さが違います");
            err_flg = true;
        }

        if (!abcStr.matches("[A-C]+")) {
            System.out.println("文字列はABCだけで作ってください");
            err_flg = true;
        }

        if(err_flg == true){
            System.exit(0);
        }
    }
}
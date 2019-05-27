import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ac126b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("4•¶š‚Ì”š—ñ‚ğ“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
        String str = br.readLine();

        if (str.length() != 4) {
            System.out.println("4•¶š‚Å“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
            System.exit(0);
        }

        // ‘O2•¶š‚ÆŒã‚ë2•¶š‚ğ•ª‚¯‚ÄAStringŒ^‚©‚çintŒ^‚Ö•ÏŠ·
        int front = convert(str.substring(0, 2));
        int back = convert(str.substring(2, 4));

        // my‚ªtrue¨MMYYAym‚ªtrue¨YYMMA—¼•ûtrue¨AMBIGUOS,—¼•ûfalse¨NAA‚ğ•\¦
        boolean my = isMonth(front);
        boolean ym = isMonth(back);
        if (my && ym) {
            System.out.println("AMBIGUOS");
        } else if (my) {
            System.out.println("MMYY");
        } else if (ym) {
            System.out.println("YYMM");
        } else {
            System.out.println("NA");
        }
    }

    /**
     * ”Ä—p«‚ğd‹‚·‚éˆ×‚ÉString‚©‚çintŒ^‚É•ÏŠ· i•ÏŠ·¸”s‚Íexitj
     * 
     * @param str “ü—Í‚³‚ê‚½”š—ñ
     * @return •ÏŠ·‚µ‚½”’l
     */
    public static int convert(String str) {

        int num = 0;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("³‚µ‚­”š—ñ‚ğ“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
            System.exit(0);
        }
        return num;
    }

    /**
     * ”’l‚ª1`12(month)‚©’²‚×‚é
     *@1`12‚È‚çtrue‚ğ•Ô‚µA‚»‚¤‚Å‚È‚¯‚ê‚Îfalse‚ğ•Ô‚·
     * 
     * @param num ‘O2•¶š‚©Œã2•¶š
     * @return@true,false
     */
    public static boolean isMonth(int num){
        if (num >= 1 && num <= 12){
            return true;
        } else {
            return false;
        }
    }
}
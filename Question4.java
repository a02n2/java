import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Question4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("®”(-10000`10000)‚Ì‰ÁŒ¸‚©Œ¸Z‚ğ‹ó”’‚Å‹æØ‚Á‚Ä“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
        System.out.println("‰‰Z‹L†‚Íu+(˜a)vu-(·)v‚Å‚·");
        System.out.println("¦•¡”‰ñ‚Ì‰‰Z‚às‚¦‚Ü‚·");
        String input = br.readLine();
        String[] param = input.split(" ");
        int sum = calc(param, 0, param.length);
        System.out.println(sum);
    }

    public static int calc(String[] param, int start, int end) {

        String mark = "";       // ‰‰Z‹L†
        Short x = 0;            // “ü—Í’l
        int sum = 0;            // ‡Œv’l
        for (int i = start; i < end; i++) {
            System.out.println("i:" + i);

            System.out.println("param:" + param[i]);
            
            if ("+".equals(param[i]) || "-".equals(param[i])) {       // “ü—Í’l‚ª"+"‚©"-"‚Ìê‡
                mark = param[i];
            } else if (param[i].equals("(")) {                        // “ü—Í’l‚ª"("‚Ìê‡
                int endi = endmark(param, i);       // ")"‚ª“ü‚Á‚Ä‚¢‚é—v‘f‚Ì“Yš
                System.out.println("endi:" + endi);


                switch (mark) {     //mark‚É‚æ‚Á‚ÄŒvZ
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
                i = endi;           // ")"‚ÌŸ‚Ì“Yš‚ğ‘ã“ü
            } else if(param[i].equals(")")){        // “ü—Í’l‚É")"‚ª—ˆ‚½‚çŸ‚Ö
                continue ;
            } else {                    // “ü—Í’l‚ª"+"‚©"-"ˆÈŠO
                try {
                    x = Short.parseShort(param[i]);
                } catch (NumberFormatException e) {
                    System.out.println("®‚ğ³‚µ‚­“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
                    System.exit(0);
                }

                if (x < -10000 || x > 10000) {      // “ü—Í’l‚ª-10000~10000‚Å‚È‚¯‚ê‚ÎƒGƒ‰[
                    System.out.println("-10000`10000‚Å“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
                    System.exit(0);
                }
                switch (mark) {         // ‹L†‚É‚æ‚Á‚ÄŒvZ
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

                if (sum < -10000 || sum > 10000) {      // ŒvZ“r’†‚Å-10000`10000‚ğ‰z‚¦‚½‚çƒGƒ‰[
                    System.out.println("¦ŒvZ“r’†‚Å-10000`10000‚Ì”ÍˆÍŠO‚É‚È‚è‚Ü‚µ‚½");
                    System.exit(0);
                }
            }
            System.out.println("sum:" + sum);
        }
        return sum;
    }

    //")"‚ğ‚³‚ª‚·
    public static int endmark(String[] param, int start) {
        int i = 0;

        for (i = start; i < param.length; i++) {

            if (")".equals(param[i])) {
                break;
            }
        }
        return i;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Question4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("����(-10000�`10000)�̉��������Z���󔒂ŋ�؂��ē��͂��Ă�������");
        System.out.println("���Z�L���́u+(�a)�v�u-(��)�v�ł�");
        System.out.println("��������̉��Z���s���܂�");
        String input = br.readLine();
        String[] param = input.split(" ");
        int sum = calc(param, 0, param.length);
        System.out.println(sum);
    }

    public static int calc(String[] param, int start, int end) {

        String mark = "";       // ���Z�L��
        Short x = 0;            // ���͒l
        int sum = 0;            // ���v�l
        for (int i = start; i < end; i++) {
            System.out.println("i:" + i);

            System.out.println("param:" + param[i]);
            
            if ("+".equals(param[i]) || "-".equals(param[i])) {       // ���͒l��"+"��"-"�̏ꍇ
                mark = param[i];
            } else if (param[i].equals("(")) {                        // ���͒l��"("�̏ꍇ
                int endi = endmark(param, i);       // ")"�������Ă���v�f�̓Y��
                System.out.println("endi:" + endi);


                switch (mark) {     //mark�ɂ���Čv�Z
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
                i = endi;           // ")"�̎��̓Y������
            } else if(param[i].equals(")")){        // ���͒l��")"�������玟��
                continue ;
            } else {                    // ���͒l��"+"��"-"�ȊO
                try {
                    x = Short.parseShort(param[i]);
                } catch (NumberFormatException e) {
                    System.out.println("���𐳂������͂��Ă�������");
                    System.exit(0);
                }

                if (x < -10000 || x > 10000) {      // ���͒l��-10000~10000�łȂ���΃G���[
                    System.out.println("-10000�`10000�œ��͂��Ă�������");
                    System.exit(0);
                }
                switch (mark) {         // �L���ɂ���Čv�Z
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

                if (sum < -10000 || sum > 10000) {      // �v�Z�r����-10000�`10000���z������G���[
                    System.out.println("���v�Z�r����-10000�`10000�͈̔͊O�ɂȂ�܂���");
                    System.exit(0);
                }
            }
            System.out.println("sum:" + sum);
        }
        return sum;
    }

    //")"��������
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
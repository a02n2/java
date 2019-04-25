import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Question3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("����(-10000�`10000)�̉��������Z���󔒂ŋ�؂��ē��͂��Ă�������");
        System.out.println("���Z�L���́u+(�a)�v�u-(��)�v�ł�");
        System.out.println("��������̉��Z���s���܂�");
        String input = br.readLine();
        String[] param = input.split(" ");

        String mark = "";   // ���Z�L��
        Short x = 0;        // ���͒l
        int sum = 0;        // ���v�l

        for (int i = 0; i < param.length; i++) {
            if ("+".equals(param[i]) || "-".equals(param[i])) {
                mark = param[i];                    // ���͒l��"+"��"-"�Ȃ�mark�ɑ��
            } else {
                try {
                    x = Short.parseShort(param[i]); // ���͒l��"+"��"-"�ȊO�Ȃ�ϊ�����x�ɑ��
                } catch (NumberFormatException e) {
                    System.out.println("���𐳂������͂��Ă�������");
                    return;
                }

                if (x < -10000 || x > 10000) {          // ���͒l��-10000~10000�łȂ���΃G���[
                    System.out.println("-10000�`10000�œ��͂��Ă�������");
                    return;
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
                    return;
                }
            }
        }
        System.out.println(sum);
    }
}
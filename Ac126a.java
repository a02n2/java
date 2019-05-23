import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ac126a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("��s�ڂɕ�����̒����A�������ɂ���ʒu���󔒂ŋ�؂��ē��͂��ĉ�����");
        System.out.println("��s�ڂ�ABC����ł������������͂��Ă�������");
        String str = br.readLine();
        String s = br.readLine();
        String[] nk = str.split(" ", 2);

        try {
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);

            if (!(n >= 1 && n <= 50)) {
                System.out.println("������̒������͈͊O�ł�");
                System.exit(0);
            }

            if (!(k >= 1 && k <= n)) {
                System.out.println("�������ɕϊ�����ʒu���͈͊O�ł�");
                System.exit(0);
            }

            if (!(n == s.length())) {
                System.out.println("�w�肵��������̒����ƁAABC����ł���������̒������Ⴂ�܂�");
                System.exit(0);
            }

            if (!s.matches("[A-C]*")) {
                System.out.println("�������ABC�����ō���Ă�������");
                System.exit(0);
            }

            // ������̎w��̈ʒu���������ɕϊ����A�o��
            s = s.substring(0, k - 1) + s.substring(k - 1, k).toLowerCase() + s.substring(k);
            System.out.println(s);

        } catch (NumberFormatException e) {
            System.out.println("���������͂��Ă�������");
        }
    }
}
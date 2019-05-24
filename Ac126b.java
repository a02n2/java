import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ac126b {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("4�����̐��������͂��Ă�������");
        String str = br.readLine();

        if (str.length() != 4) {
            System.out.println("4�����œ��͂��Ă�������");
            System.exit(0);
        }

        // �O2�����ƌ��ɕ����𕪂��āAString�^����int�^�֕ϊ�
        int front = 0;
        int back = 0;
        try {
            front = Integer.parseInt(str.substring(0, 2));
            back = Integer.parseInt(str.substring(2, 4));
        } catch (NumberFormatException e) {
            System.out.println("���l�����������͂���Ă��܂���");
            System.exit(0);
        }

        boolean my = (front >= 1 && front <= 12);
        boolean ym = (back >= 1 && back <= 12);

        // my��true��MMYY�Aym��true��YYMM�A����true��AMBIGUOS,����false��NA�A��\��
        if (my) {
            if (ym) {
                System.out.println("AMBIGUOS");
            } else {
                System.out.println("MMYY");
            }
        } else if (ym) {
            System.out.println("YYMM");
        } else {
            System.out.println("NA");
        }
    }
}
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

        // �O2�����ƌ��2�����𕪂��āAString�^����int�^�֕ϊ�
        int front = convert(str.substring(0, 2));
        int back = convert(str.substring(2, 4));

        // my��true��MMYY�Aym��true��YYMM�A����true��AMBIGUOS,����false��NA�A��\��
        boolean my = (front >= 1 && front <= 12);
        boolean ym = (back >= 1 && back <= 12);
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

    /**
     * �ėp�����d������ׂ�String����int�^�ɕϊ� �i�ϊ����s����exit�j
     * 
     * @param str ���͂��ꂽ������
     * @return �ϊ��������l
     */
    public static int convert(String str) {

        int num = 0;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("���l�����������͂���Ă��܂���");
            System.exit(0);
        }
        return num;
    }
}
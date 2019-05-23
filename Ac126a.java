import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ac126a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("��s�ڂɕ�����̒����A�������ɂ���ʒu���󔒂ŋ�؂��ē��͂��ĉ�����");
        System.out.println("��s�ڂ�ABC����ł������������͂��Ă�������");
        String str = br.readLine();
        String abcStr = br.readLine();
        String[] lenLow = str.split(" ", 2);

        // ������̒����Ə������ɂ���ʒu��int�^�ɕϊ�
        int length = convert(lenLow[0], "������̒���");
        int lowerCase = convert(lenLow[1], "�������ɂ���ʒu");

        // �@����`�F�b�N
        check(length, lowerCase, abcStr);

        // ������̎w��̈ʒu���������ɕϊ����A�o��
        abcStr = abcStr.substring(0, lowerCase - 1) + abcStr.substring(lowerCase - 1, lowerCase).toLowerCase()
                + abcStr.substring(lowerCase);
        System.out.println(abcStr);
    }

    /**
     * String�^�œ��͂��ꂽ������̒����Ə������ɂ���ʒu��int�^�ɕϊ�
     * 
     * @param strLenLow�@������̒����A�������ɂ���ʒu
     * @param text�@"������̒���"�A"�������ɂ���ʒu"�̕���
     * @return�@�ϊ������l
     */
    public static int convert(String strLenLow, String text) {
        int lenLow = 0;
        try {
            lenLow = Integer.parseInt(strLenLow);
        } catch (NumberFormatException e) {
            System.out.println(text + "���������w�肳��Ă��܂���");
            System.exit(0);
        }
        return lenLow;
    }

    /**
     * ����̃`�F�b�N���s�� 
     * �E1<=������̒���<=50 
     * �E1<=�������ɂ���ʒu<=������̒���
     * �E�������ABC����Ȃ�
     * 
     * @param length    ������̒���
     * @param lowerCase �������ɂ���ʒu
     * @param abcStr    ������
     */
    public static void check(int length, int lowerCase, String abcStr) {

        if (length < 1 || length > 50) {
            System.out.println("������̒������͈͊O�ł�");
            System.exit(0);
        }

        if (lowerCase < 1 || lowerCase > length) {
            System.out.println("�������ɕϊ�����ʒu���͈͊O�ł�");
            System.exit(0);
        }

        if (length != abcStr.length()) {
            System.out.println("�w�肵��������̒����ƁAABC����ł���������̒������Ⴂ�܂�");
            System.exit(0);
        }

        if (!abcStr.matches("[A-C]+")) {
            System.out.println("�������ABC�����ō���Ă�������");
            System.exit(0);
        }
    }
}
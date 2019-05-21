import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Knowledge3 {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("�t�@�C���͈�w�肵�Ă��������B");
            System.exit(0);
        }

        try {
            File fr = new File(args[0]);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fr), "SJIS"));

            List<String> list = new ArrayList<String>();

            // �t�@�C���̒��g����s���ǂݍ��݁A���X�g�֊i�[
            String str;
            while ((str = br.readLine()) != null) {
                list.add(str);
            }

            br.close();

            // �@���Ԃɏo��
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }

            // �@�t���ɏo��
            for (int i = (list.size() - 1); i >= 0; i--) {
                System.out.println(list.get(i));
            }

            // �@"����������"���i�[����Ă���s�ԍ����o��
            if (list.contains("����������")){  
                System.out.println((list.indexOf("����������") + 1) + "�s��");  
            } else {
                System.out.println("�u�����������v�̓t�@�C���ɑ��݂��܂���");
            }

        } catch (FileNotFoundException e) {
            System.out.println("�t�@�C����������܂���B");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
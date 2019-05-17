import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Knowledge1 {
    public static void main(String[] args) {

        try {
            // �t�@�C���ǂݍ���
            File fr = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(fr));
            
            // �t�@�C����������
            File fw = new File(args[1]);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fw),"UTF-8"));

            // �t�@�C����ǂݍ��݁A���e��\�����A�قȂ�t�@�C���֏�������
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
                bw.write(str);
                bw.write("\r\n");
            }
            br.close();
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("�����Ƃ��ăt�@�C�����w�肵�Ă�������");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
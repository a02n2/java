import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class Knowledge2 {
    public static void main(String[] args) {
        try {
            // �t�@�C���ǂݍ���
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(args[0]));
            // �t�@�C����������
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(args[1]));

            // �t�@�C����ǂݍ��݁A�ʃt�@�C���֏�������
            int data;
            while ((data = bis.read()) != -1) {
                bos.write(data);
            }
            
            bis.close();
            bos.close();
        } catch (FileNotFoundException e) {
            System.out.println("�ǂݍ��ރt�@�C����������܂���");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("�t�@�C�����w�肵�Ă��������B");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
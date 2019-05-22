import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;

public class Knowledge4 {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("�t�@�C���͈�w�肵�Ă�������");
            System.exit(0);
        }

        try {
            File fr = new File(args[0]);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fr), "SJIS"));

            Map<String, String> map = new LinkedHashMap<String, String>();

            // �t�@�C���̒��g����s���ǂݍ��݁A�󔒂ŕ����A�}�b�v�֊i�[
            String str;
            while ((str = br.readLine()) != null) {
                String[] keyValue = str.split(" ", 2);

                map.put(keyValue[0], keyValue[1]);
            }
            
            br.close();

            // �}�b�v�Ɋi�[����Ă��邷�ׂẴL�[�ƒl���o��
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }

            // �@�L�[�uTOKYO�v�̒l���o��
            if (map.containsKey("TOKYO")) {
                System.out.println("TOKYO=>" + map.get("TOKYO"));
            } else {
                System.out.println("�uTOKYO�v�͑��݂��܂���");
            }

        } catch (FileNotFoundException e) {
            System.out.println("�t�@�C����������܂���");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
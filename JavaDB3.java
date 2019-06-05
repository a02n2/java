import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class JavaDB3 {
    private static Connection conn = null;

    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        String portnumber = "5432";
        String dbname = "test";
        String username = "postgres";
        String password = "user01";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":" + portnumber + "/" + dbname,
                    username, password);

            // �e�[�u���ɓo�^����Ă��邷�ׂĂ̍s��\��
            selectAll();

            while (true) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("�X�V����ID����͂��Ă�������");
                    String id_str = br.readLine();

                    // String�^����int�^�ɕϊ�
                    int id = Integer.parseInt(id_str);

                    // ���R�[�h�̑��݃`�F�b�N
                    recordExists(id);

                    // �R�����g�ƃ^�C�g����ǂݍ��݁A����
                    System.out.println("�X�V�������^�C�g���ƃR�����g���R���}�ŋ�؂��ē��͂��Ă�������");
                    String[] titleComment = splitComma(br.readLine());

                    // �^�C�g���ƃR�����g�̒����`�F�b�N
                    rangeCheck(titleComment[0], titleComment[1]);

                    // ���R�[�h���X�V
                    updateRecord(id, titleComment[0], titleComment[1]);
                    System.out.println("���R�[�h���X�V���܂���");

                    // �X�V���I�����邩
                    System.out.println("�X�V���I�����܂���(0�ŏI��)");
                    String str = br.readLine();
                    if (continueFlg(str)) {
                        break;
                    }
                } catch (SQLException e) {
                    throw e;
                } catch (NumberFormatException e) {
                    System.out.println("��ID�𐳂������͂��Ă�������");
                    continue;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }
            // �e�[�u���ɓo�^����Ă��邷�ׂĂ̍s��\��
            selectAll();
        } catch (SQLException e) {
            System.out.println(e);
        } catch(Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * �e�[�u���ɓo�^����Ă���S�Ă̍s��\��
     * 
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     */
    public static void selectAll() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from tasks;");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.print(rs.getInt("id"));
            System.out.print("/");
            System.out.print(rs.getString("title"));
            System.out.print("/");
            System.out.print(rs.getString("comment"));
            System.out.print("\n");
        }
    }

    /**
     * ���R�[�h�����݂��邩�`�F�b�N 
     * 
     * @param id �X�V���郌�R�[�h��ID
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     * @throws Exception    �w�肳�ꂽ���R�[�h�����݂��Ȃ���
     */
    public static void recordExists(int id) throws SQLException, Exception {
        PreparedStatement ps = conn.prepareStatement("select * from tasks where id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            throw new Exception("���Y������ID�����݂��܂���");
        }
    }

    /**
     * �ǂݎ������������R���}�Ń^�C�g���ƃR�����g�ɕ��� 
     * 
     * @param str �ǂݎ����������
     * @return �^�C�g���A�R�����g
     * @throws Exception ������ɃR���}���Ȃ���
     */
    public static String[] splitComma(String str) throws Exception {
        if (!str.contains(",")) {
            throw new Exception("���^�C�g���ƃR�����g�𐳂������͂��Ă�������");
        }
        String[] strings = str.split(",", 2);
        return strings;
    }

    /**
     * �^�C�g���ƃR�����g�̒������`�F�b�N   
     * 0 < �^�C�g�� < 32    
     * �R�����g < 256 
     * 
     * @param title�@�^�C�g��
     * @param comment�@�R�����g
     * @throws Exception �^�C�g�����R�����g�̒������͈͊O�̎�
     */
    public static void rangeCheck(String title, String comment) throws Exception {
        boolean checkFlg = false;
        String titleErr = "";
        String commentErr = "";
        if (title.length() < 1 || title.length() > 32) {
            checkFlg = true;
            titleErr = "���^�C�g�����͈͊O�ł�";
        }
        if (comment.length() > 256) {
            checkFlg = true;
            commentErr = "���R�����g���������܂�";
        }
        if(checkFlg){
            throw new Exception(titleErr + commentErr);
        }
    }

    /**
     * �w�肳�ꂽID�̃��R�[�h���X�V
     * 
     * @param id �X�V���郌�R�[�h��ID
     * @param title    �X�V�������^�C�g��
     * @param comment  �X�V�������R�����g
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     */
    public static void updateRecord(int id, String title, String comment) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("update tasks set (title, comment) = (?, ?) where id = ?;");
        ps.setString(1, title);
        ps.setString(2, comment);
        ps.setInt(3, id);
        ps.executeUpdate();
    }

    /**
     * �폜���I������̂�     
     * 0�ŏI��(true)�A0�ȊO���s(false)
     * 
     * @param str 0������ȊO�̕���
     * @return true,false
     */
    public static boolean continueFlg(String str) {
        if (str.equals("0")) {
            System.out.println("�I�����܂�");
            return true;
        }
        System.out.println("���s���܂�");
        return false;
    }
}
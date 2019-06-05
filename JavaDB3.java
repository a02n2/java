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
        String dbname = "test";
        String username = "postgres";
        String password = "user01";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":5432/" + dbname, username, password);

            // �e�[�u���ɓo�^����Ă��邷�ׂĂ̍s��\��
            selectAll();

            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("�X�V����ID����͂��Ă�������");
                String id = br.readLine();

                // String�^����int�^�ɕϊ�
                int updateID = 0;
                try {
                    updateID = getUpdateID(id);
                } catch (NumberFormatException e) {
                    System.out.println("��ID�𐳂������͂��Ă�������");
                    continue;
                }
                // ���R�[�h�̑��݃`�F�b�N
                if (!getRecordExists(updateID)) {
                    continue;
                }

                System.out.println("�X�V�������^�C�g���ƃR�����g���R���}�ŋ�؂��ē��͂��Ă�������");
                String titleComment[] = {};
                try {
                    // �R�����g�ƃ^�C�g����ǂݍ��݁A����
                    titleComment = splitTC(br.readLine());
                } catch (Exception e) {
                    System.out.println("�^�C�g���ƃR�����g�𐳂������͂��Ă�������");
                    continue;
                }
                // �^�C�g���ƃR�����g�̒����`�F�b�N
                if (!rangeCheck(titleComment[0], 32, "�^�C�g��") || !rangeCheck(titleComment[1], 256, "�R�����g")){   
                    continue;
                }
                // ���R�[�h���X�V
                updateRecord(updateID, titleComment[0], titleComment[1]);

                // �X�V���I�����邩
                System.out.println("�X�V���I�����܂���(0�ŏI��)");
                String str = br.readLine();
                if (continueFlg(str)) {
                    continue;
                }
                break;
            }
            // �e�[�u���ɓo�^����Ă��邷�ׂĂ̍s��\��
            selectAll();

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
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
     * �ėp�����d������ׂ�String����int�^�ɕϊ�
     * 
     * @param id �X�V���郌�R�[�h��ID�̐�����
     * @return �X�V���郌�R�[�h��ID
     * @throws NumberFormatException �����ȊO�����͂��ꂽ�Ƃ��̃G���[
     */
    public static int getUpdateID(String id) throws NumberFormatException {
        return Integer.parseInt(id);
    }

    /**
     * ���R�[�h�����݂��邩�`�F�b�N   
     * ���݂��Ȃ�(false),���݂���(true)
     * 
     * @param updateID �X�V���郌�R�[�h��ID
     * @return true,false
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     */
    public static boolean getRecordExists(int updateID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from tasks where id = ?");
        ps.setInt(1, updateID);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            System.out.println("���Y������ID�͓o�^����Ă��܂���");
            return false;
        }
        return true;
    }

    /**
     * �ǂݎ������������R���}�Ń^�C�g���ƃR�����g�ɕ���
     * ������ɃR���}���Ȃ��Ƃ��͗�O����
     * 
     * @param tc �ǂݎ����������
     * @return �^�C�g���A�R�����g
     * @throws Exception ������ɃR���}���Ȃ���
     */
    public static String[] splitTC(String tc) throws Exception {
        if(!tc.contains(",")){
            throw new Exception();
        }
        String[] titleComment = tc.split(",", 2);
        return titleComment;
    }

    /**
     * �^�C�g���ƃR�����g�̒������`�F�b�N   
     * 0 < �^�C�g�� < 32   
     * 0 < �R�����g�@< 256
     * 
     * @param titleComment�@�^�C�g���A�R�����g
     * @param length�@�`�F�b�N�Ώۂ̍ő�̒���
     * @param text�@�`�F�b�N�Ώۂ̃^�C�g��
     */
    public static boolean rangeCheck(String titleComment, int length, String txt) {
        if (titleComment.length() < 1 || titleComment.length() > length) {
            System.out.println("��" + txt + "���͈͊O�ł�");
            return false;
        }
        return true;
    }

    /**
     * �w�肳�ꂽID�̃��R�[�h���X�V
     * 
     * @param updateID �X�V���郌�R�[�h��ID
     * @param title    �X�V�������^�C�g��
     * @param comment  �X�V�������R�����g
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     */
    public static void updateRecord(int updateID, String title, String comment) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("update tasks set title = ?,comment = ? where id = ?;");
        ps.setString(1, title);
        ps.setString(2, comment);
        ps.setInt(3, updateID);
        ps.executeUpdate();
    }

    /**
     * �폜���I������̂�    
     * 0�ŏI��(false)�A0�ȊO���s(true)
     * 
     * @param str 0������ȊO�̕���
     * @return true,false
     */
    public static boolean continueFlg(String str) {
        if (str.equals("0")) {
            System.out.println("�I�����܂�");
            return false;
        }
        System.out.println("���s���܂�");
        return true;
    }
}
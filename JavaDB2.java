import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class JavaDB2 {

    private static Connection conn = null;

    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        String dbname = "test";
        String username = "postgres";
        String password = "user01";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":5432/" + dbname, username, password);

            // ���ׂĂ̍s��\��
            selectAll();

            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("�폜���郌�R�[�h��ID����͂��Ă�������");
                String id = br.readLine();

                int deleteID = 0;
                // String�^����int�^�ɕϊ�
                try {
                    deleteID = getDeleteID(id);
                } catch (NumberFormatException e) {
                    System.out.println("���폜���郌�R�[�h��ID�𐳂������͂��Ă�������");
                    continue;
                }
                // ���R�[�h�̑��݃`�F�b�N
                if (!getRecordExists(deleteID)) {
                    continue;
                }
                // �w�肳�ꂽ���R�[�h���폜
                deleteRecord(deleteID);
                
                // �ē��͂��邩�ǂ���
                System.out.println("���͂��I�����܂����H(0�ŏI��)");
                String str = br.readLine();
                if (continueFlg(str)) {
                    continue;
                }
                break;
            }
            // �폜��̂��ׂĂ̍s��\��
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
     * �e�[�u���ɓo�^����Ă��邷�ׂĂ̍s��\��
     * 
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     */
    public static void selectAll() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM tasks;");
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
     * @param id �폜���郌�R�[�h��ID�̐�����
     * @return �폜���郌�R�[�h��ID
     * @throws SQLException          �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     * @throws NumberFormatException �����ȊO�����͂��ꂽ�Ƃ��̃G���[
     */
    public static int getDeleteID(String id) throws SQLException, NumberFormatException {
        int deleteID = Integer.parseInt(id);

        return deleteID;
    }

    /**
     * ���R�[�h�����݂��邩�`�F�b�N 
     * ���݂��Ȃ�(false),���݂���(true)
     * 
     * @param deleteID �폜���郌�R�[�h��ID
     * @return true,false
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     */
    public static boolean getRecordExists(int deleteID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from tasks where id = ?;");
        ps.setInt(1, deleteID);
        ResultSet rs = ps.executeQuery();

        boolean isExists = rs.next();
        if (!isExists) {
            System.out.println("���Y������ID�͓o�^����Ă��܂���");
            return false;
        }
        return true;
    }

    /**
     * �w�肳�ꂽID�̃��R�[�h���폜
     * 
     * @param deleteID �폜���郌�R�[�h��ID
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     */
    public static void deleteRecord(int deleteID) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM tasks where id = ?;");
        ps.setInt(1, deleteID);
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
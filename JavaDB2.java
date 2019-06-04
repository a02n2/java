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
            // �w�肳�ꂽ���R�[�h���폜
            deleteRecord();
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
            System.out.println("\n");
        }
    }

    /**
     * �w�肳�ꂽID�̃��R�[�h���폜
     * 
     * @throws IOException ���o�̓G���[
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     */
    public static void deleteRecord() throws IOException, SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM tasks where id = ?;");

        while (true) {

            // �폜���郌�R�[�h��ID���擾
            int deleteID = getDeleteID();

            // ���͂��ꂽID�̃��R�[�h���폜
            ps.setInt(1, deleteID);
            ps.executeUpdate();

            // �폜���I�����邩���Ȃ���
            if (deleteEnd()) {
                break;
            }
        }
    }

    /**
     * �폜���郌�R�[�h��ID���擾
     * 
     * @return �폜���郌�R�[�h��ID
     * @throws IOException ���o�̓G���[
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     */
    public static int getDeleteID() throws IOException, SQLException {
        int deleteID = 0;
        PreparedStatement ps = conn.prepareStatement("select * from tasks where id = ?;");

        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("�폜���郌�R�[�h��ID����͂��Ă�������");
            String str = br.readLine();

            try {
                // �ėp�����d������ׂ�String����int�^�ɕϊ�
                deleteID = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("���폜���郌�R�[�h��ID�𐳂������͂��Ă�������");
                continue;
            }

            // ���͂��ꂽID�����݂��邩�`�F�b�N
            ps.setInt(1, deleteID);
            ResultSet rs = ps.executeQuery();

            boolean isExists = rs.next();
            if (!isExists) {
                System.out.println("���Y������ID�͓o�^����Ă��܂���");
                continue;
            }

            break;
            
        }
        return deleteID;
    }

    /**
     * �폜���I������̂�
     * 0�ŏI��(true)�A0�ȊO���s(false)
     * 
     * @return true,false
     * @throws IOException ���o�̓G���[
     */
    public static boolean deleteEnd() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("���͂��I�����܂����H(0�ŏI��)");
        String str = br.readLine();

        if (str.equals("0")) {
            System.out.println("�I�����܂�");
            return true;
        } else {
            System.out.println("���s���܂�");
            return false;
        }
    }
}
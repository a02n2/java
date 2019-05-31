import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class JavaDB1 {

    private static Connection conn = null;

    public static void main(String[] args) throws IOException {
        String hostname = "localhost";
        String dbname = "test";
        String username = "postgres";
        String password = "user01";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":5432/" + dbname, username, password);

            // ���͂��ꂽ���e��}��
            insert();

            // �o�^����Ă��邷�ׂĂ̍s��\��
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
     * �W�����͂��ꂽ�^�C�g���ƃR�����g���e�[�u���ɒǉ�
     * 
     * @throws IOException  ���炩�̓��o�̓G���[
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     */
    public static void insert() throws IOException, SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into tasks(title,comment) values(?,?)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("�^�C�g���ƃR�����g��','�ŋ�؂��ē��͂��Ă�������");
            String str = br.readLine();

            if (!str.contains(",")) {
                System.out.println("��','�����͂���Ă��܂���");
                System.exit(0);
            }

            // ,�Ń^�C�g���ƃR�����g�ɕ���
            String[] titleComment = str.split(",", 2);

            // �^�C�g���ƃR�����g�͈̔̓`�F�b�N
            rangeCheck(titleComment[0], 32, "�^�C�g��");
            rangeCheck(titleComment[1], 256, "�R�����g");

            // �v���[�X�z���_�ɃZ�b�g
            ps.setString(1, titleComment[0]);
            ps.setString(2, titleComment[1]);

            ps.executeUpdate();

            // ���͂��I������̂�
            System.out.println("���͂𑱂��܂����H(0�ŏI��)");
            String num = br.readLine();
            if (num.equals("0")) {
                break;
            }
        }
    }

    /**
     * �e�[�u���ɓo�^����Ă��邷�ׂĂ̍s��\��
     * 
     * @throws SQLException �f�[�^�x�[�X�E�A�N�Z�X�E�G���[�܂��͂��̑��̃G���[
     */
    public static void selectAll() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from tasks");
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
     * �^�C�g���ƃR�����g�̒������`�F�b�N 
     * 0 < �^�C�g�� < 32 
     * 0 < �R�����g�@< 256
     * 
     * @param titleComment�@�^�C�g���A�R�����g
     * @param length�@�`�F�b�N�Ώۂ̍ő�̒���
     * @param text�@�`�F�b�N�Ώۂ̃^�C�g��
     */
    public static void rangeCheck(String titleComment, int length, String text) {
        if (titleComment.length() < 1 || titleComment.length() >= length) {
            System.out.println("��" + text + "�̒������͈͊O�ł�");
            System.exit(0);
        }
    }
}
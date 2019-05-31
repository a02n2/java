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

            // 入力された内容を挿入
            insert();

            // 登録されているすべての行を表示
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
     * 標準入力されたタイトルとコメントをテーブルに追加
     * 
     * @throws IOException  何らかの入出力エラー
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
     */
    public static void insert() throws IOException, SQLException {
        PreparedStatement ps = conn.prepareStatement("insert into tasks(title,comment) values(?,?)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("タイトルとコメントを','で区切って入力してください");
            String str = br.readLine();

            if (!str.contains(",")) {
                System.out.println("※','が入力されていません");
                System.exit(0);
            }

            // ,でタイトルとコメントに分割
            String[] titleComment = str.split(",", 2);

            // タイトルとコメントの範囲チェック
            rangeCheck(titleComment[0], 32, "タイトル");
            rangeCheck(titleComment[1], 256, "コメント");

            // プレースホルダにセット
            ps.setString(1, titleComment[0]);
            ps.setString(2, titleComment[1]);

            ps.executeUpdate();

            // 入力を終了するのか
            System.out.println("入力を続けますか？(0で終了)");
            String num = br.readLine();
            if (num.equals("0")) {
                break;
            }
        }
    }

    /**
     * テーブルに登録されているすべての行を表示
     * 
     * @throws SQLException データベース・アクセス・エラーまたはその他のエラー
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
     * タイトルとコメントの長さをチェック 
     * 0 < タイトル < 32 
     * 0 < コメント　< 256
     * 
     * @param titleComment　タイトル、コメント
     * @param length　チェック対象の最大の長さ
     * @param text　チェック対象のタイトル
     */
    public static void rangeCheck(String titleComment, int length, String text) {
        if (titleComment.length() < 1 || titleComment.length() >= length) {
            System.out.println("※" + text + "の長さが範囲外です");
            System.exit(0);
        }
    }
}
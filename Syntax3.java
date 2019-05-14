import java.util.List;
import java.util.ArrayList;

public class Syntax3 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < args.length; i++) {
            list.add(args[i]);
            System.out.println(list.get(i));
        }
    }
}
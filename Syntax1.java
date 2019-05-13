public class Syntax1 {
    public static void main(String[] args) {
        System.out.println("引数は" + args.length + "個指定されています。");

        for (int i = 0; i < args.length; i++) {
            System.out.println(i+1 + "番目の引数は「" + args[i] + "」です。");
        }
    }
}
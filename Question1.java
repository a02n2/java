import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Question1{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String input =br.readLine();
        String[] param=input.split(" ");

        int x=Integer.parseInt(param[0]); 
        int y=Integer.parseInt(param[1]);

        System.out.println(x+y);
    }
}
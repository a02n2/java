import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Question1{
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        System.out.println("����2���󔒂������-10000����10000�œ��͂��Ă��������B");
        String input =br.readLine();
        String[] param=input.split(" ");

        short x=Short.parseShort(param[0]); 
        short y=Short.parseShort(param[1]);

        if(x<=10000 && x>=-10000){
            if(y<=10000 && y>=-10000){
                System.out.println(x+y);
            }
        }else{
            System.out.println("-10000����10000�œ��͂��Ă��������B");
        }
    }
}
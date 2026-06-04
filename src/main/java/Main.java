import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("$ ");
            if(!sc.hasNextLine()) break;
            String input = sc.nextLine().trim();
            System.out.println(input+": command not found");
        }
    }
}

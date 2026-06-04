import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("$ ");
            if(!sc.hasNextLine()) break;
            String input = sc.nextLine().trim();
            if(input.startsWith("exit")) System.exit(0);
            System.out.println(input+": command not found");
        }
    }
}

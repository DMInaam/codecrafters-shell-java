import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("$ ");
            if(!sc.hasNextLine())
                break;
            String input = sc.nextLine().trim();
            if(input.startsWith("exit"))
                System.exit(0);
            else if(input.startsWith("echo")){
                input = input.substring(5).trim().replaceAll("\\n","\n");
                System.out.println(input);
            }
            else if(input.startsWith("type")){
                input = input.substring(5).trim();
                switch(input){
                    case "exit":System.out.println("exit is a shell builtin");break;
                    case "echo":System.out.println("echo is a shell builtin");break;
                    case "type":System.out.println("type is a shell builtin");break;
                    default:
                        String execPath = findExec(input);
                        if(execpath != null)
                            System.out.println(input+" is "+execPath);
                        else
                            System.out.println(input+": not found");
                        break;
                }
            }
            else
                System.out.println(input+": command not found");
        }
    }
    private static String findExec(String command){
        String pathEnv = System.getenv("PATH");
        String[] dirs = pathEnv.split(File.pathSeparator);
        for(String dir: dirs){
            File file = new File(dir,command);
            if(file.exists() && file.isFile() && file.canExecute())
                return file.getAbsolutePath();
        }
        return null;
    }
}

import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("$ ");
            if(!sc.hasNextLine())
                break;
            String[] input = sc.nextLine().trim().split(" ");;
            String command = input[0];
            if(command.startsWith("exit"))
                System.exit(0);
            else if(command.startsWith("echo")){
                for(int i=1;i<input.length;i++){
                    System.out.print(input[i]);
                    if(i<input.length-1)
                        System.out.print(" ");
                }
                System.out.println();
            }
            else if(command.startsWith("type")){
                String msg = input[1];
                switch(msg){
                    case "exit":System.out.println("exit is a shell builtin");break;
                    case "echo":System.out.println("echo is a shell builtin");break;
                    case "type":System.out.println("type is a shell builtin");break;
                    default:
                        String execPath = findExec(msg);
                        if(execPath != null)
                            System.out.println(msg+" is "+execPath);
                        else
                            System.out.println(msg+": not found");
                        break;
                }
            }
            else if(findExec(command) != null){
                ProcessBuilder pb = new ProcessBuilder(input);
                try{
                    Process process = pb.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while((line = reader.readLine()) != null){
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.out.println("Error executing command: "+e.getMessage());
                }
            }
            else
                System.out.println(command+": command not found");
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

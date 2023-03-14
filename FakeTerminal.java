import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
// import java.io.InputStreamReader;
// import java.net.URI;
// import java.net.URISyntaxException;
import java.util.Arrays;
// import java.util.stream.Stream;
import java.util.Scanner;

class ExecHelpers {

  /**
    Takes an input stream, reads the full stream, and returns the result as a
    string.

    In Java 9 and later, new String(out.readAllBytes()) would be a better
    option, but using Java 8 for compatibility with ieng6.
  */
  public static String streamToString(InputStream out) throws IOException {
    String result = "";
    while(true) {
      int c = out.read();
      if(c == -1) { break; }
      result += (char)c;
    }
    return result;
  }
  public static String streamReadLine(InputStream out) throws IOException {
    String result = "";
    int readCount = 0;
    System.out.println("Reading stream");
    while(true) {
      int c = out.read();
      System.out.print((char)c);
      if(c == -1) { break; }
      if((char)c == '\n'){ break;}
      result += (char)c;
      readCount += 1;
      if(readCount > 10 ){
        break;
      }
    }
    return result;
  }
  public static void steramWriteString(OutputStream in) throws IOException {

  }

  /**
    Takes a command, represented as an array of strings as it would by typed at
    the command line, runs it, and returns its combined stdout and stderr as a
    string.
  */
  static String exec(String[] cmd) throws IOException {
    Process p = new ProcessBuilder()
                    .command(Arrays.asList(cmd))
                    .redirectErrorStream(true)
                    .start();
    InputStream out = p.getInputStream();
    return String.format("%s\n", streamToString(out));
  }

}

class FakeTerminal{
    // static String userDevice = "martinlu@Martins-MacBook-Pro-2";
    public static String makeTerminalHeader() throws UnknownHostException{
        String[] systemNameList= InetAddress.getLocalHost().getHostName().split("\\.");
        String systemName = systemNameList[0];
        String username = System.getProperty("user.name");
        String[] workingDirPath = System.getProperty("user.dir").split("/");
        String workingDir = workingDirPath[workingDirPath.length - 1];
        return username + "@"+ systemName + " " + workingDir + " % ";
    }
    public static void main(String[] args) throws IOException, InterruptedException{
        String header = makeTerminalHeader();
        String filename = args[0];
        String[] commands = ExecHelpers.exec(new String[]{"cat",filename}).split(System.lineSeparator());

        
//         String result = ExecHelpers.exec(new String[]{"exec",command});
//         Exception in thread "main" java.io.IOException: Cannot run program "eval": error=2, No such file or directory
//         at java.lang.ProcessBuilder.start(ProcessBuilder.java:1048)
//         at ExecHelpers.exec(FakeTerminal.java:40)
//         at FakeTerminal.main(FakeTerminal.java:63)
// Caused by: java.io.IOException: error=2, No such file or directory
//         at java.lang.UNIXProcess.forkAndExec(Native Method)
//         at java.lang.UNIXProcess.<init>(UNIXProcess.java:247)
//         at java.lang.ProcessImpl.start(ProcessImpl.java:134)
//         at java.lang.ProcessBuilder.

        // for(String command : commands){
        //     System.out.println(header + command);
        //     // String result = ExecHelpers.exec(new String[]{"exec",command});
        //     String result = ExecHelpers.exec(new String[]{"bash","execscript.bash",command});
        //     System.out.print(result); 
        // }

        Process p = new ProcessBuilder()
                        .command(Arrays.asList(new String[]{"bash","-s"}))
                        .redirectErrorStream(true)
                        .start();

        //https://stackoverflow.com/questions/18903549/writing-to-inputstream-of-a-java-process
        OutputStream stdin = p.getOutputStream();
        InputStream stdout = p.getInputStream();

        // BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin)); 
        Scanner scanner = new Scanner(stdout);

        String DONE_STRING = "lkdfjnghreiugyerpifuahlriungulraeg";

        for(String command : commands){
            if(command.charAt(0) == '#') continue;
            System.out.println(header+command);
            writer.write(command + "\n");
            writer.write("echo "+DONE_STRING+"\n");
            writer.flush();
            
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.equals(DONE_STRING)){break;}
                System.out.println(line);
            }
        }



        // scanner.hasNextLine();

        writer.flush();
        scanner.close();
        p.destroy();

    }
}
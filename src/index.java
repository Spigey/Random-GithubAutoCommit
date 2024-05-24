import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
class Colors{
    static final String RS = "\u001B[0m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String BLUE = "\u001B[34m";
    static final String ORANGE = "\u001B[38;5;208m";
}
public class index {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ques("Amount of commits");
        int commitCount = scanner.nextInt();
        scanner.nextLine();
        ques("Automatically push? (y/n)");
        String autoPush = scanner.nextLine().toLowerCase();
        ques("Delay before commiting again");
        int delay = scanner.nextInt();
        System.out.println("Committing " + commitCount + " times...");

        for (int i = 0; i < commitCount; i++) {
            try {
                Runtime.getRuntime().exec(new String[]{"git", "commit", "--allow-empty", "-m", String.format("Commit %d of %d", i + 1, commitCount)});
                System.out.printf(Colors.GREEN + "Commit %d of %d Done!%n" + Colors.RS, i + 1, commitCount);
                Thread.sleep(delay);
            } catch (IOException | InterruptedException e) {
                System.err.println("Error during commit: " + e.getMessage());
                e.printStackTrace();
            }
        }

        println(Colors.GREEN + "Commited " + commitCount + " times! Pushing..." + Colors.RS);

        if (autoPush.equals("y")) {
            try {
                Runtime.getRuntime().exec(new String[]{"git", "push"});
                System.out.println(Colors.BLUE + "Pushed all commits to GitHub!" + Colors.RS);
            } catch (IOException e) {
                System.err.println("Error during push: " + e.getMessage());
                e.printStackTrace();
            }
        }
        scanner.close();
    }
    static void print(String s) {
        System.out.println(s);
    }
    static void println(String s) {
        System.out.println(s);
    }
    static void ques(String s){
        System.out.print(Colors.ORANGE + s  + ": " + Colors.RS);
    }
}
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
        boolean autoPush = scanner.nextLine().equalsIgnoreCase("y");
        ques("Delay before commiting again");
        int delay = scanner.nextInt();
        scanner.nextLine();
        ques("Enable super fast mode? (y/n)");
        boolean superFast = scanner.nextLine().equalsIgnoreCase("y");

        int days = (int) TimeUnit.MILLISECONDS.toDays((long) delay * commitCount);
        long remaining = ((long) delay * commitCount) - TimeUnit.DAYS.toMillis(days);
        int hours = (int) (remaining / TimeUnit.MILLISECONDS.toHours(1));
        remaining %= TimeUnit.HOURS.toMillis(1);
        int minutes = (int) (remaining / TimeUnit.MINUTES.toMillis(1));
        int seconds = (int) (remaining / TimeUnit.SECONDS.toMillis(1));
        System.out.printf("Committing " + commitCount + " times...\nThis will take approximately %d days, %d hours, %d minutes and %d seconds.\n\n\n\n", days, hours, minutes, seconds);

        for (int i = 0; i < commitCount; i++) {
            try {
                String commitMessage = String.format("Commit %d of %d", i + 1, commitCount);
                Runtime.getRuntime().exec(new String[]{"git", "commit", "--allow-empty", "-m", commitMessage});
                if(!superFast) {
                    final int barLength = 50;
                    // if ((i + 1) % Math.max(commitCount / barLength, 1) == 0 || i == commitCount - 1) {
                    // Thread.sleep(10);
                    double percent = (double) (i + 1) / commitCount * 100;
                    int progress = (int) (percent / (100.0 / barLength));

                    System.out.print("\033[A\033[2K\r\u001B[A\u001B[2K\n\u001B[A\u001B[2K\n[");
                    for (int j = 0; j < progress; j++) {
                        System.out.print("▓"); // Filled block
                    }
                    for (int j = progress; j < barLength; j++) {
                        System.out.print("▒"); // Empty block
                    }
                    System.out.printf("] %.1f%% Commit %d of %d\n", percent, i + 1, commitCount);
                    // }
                    // System.out.printf(Colors.GREEN + "Commit %d of %d Done!%n" + Colors.RS, i + 1, commitCount);
                }
                Thread.sleep(delay);
            } catch (IOException | InterruptedException e) {
                System.err.println("Error during commit: " + e.getMessage());
                e.printStackTrace();
            }
        }

        println(Colors.GREEN + "Commited " + commitCount + " times! " + Colors.RS);
        if(autoPush) println(Colors.BLUE + "Pushing..." + Colors.RS);

        if (autoPush) {
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
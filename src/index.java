import java.io.IOException;
import java.util.Scanner;

public class index {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Amount of commits: ");
        int commitCount = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Auto git push when committed? (y/n): ");
        String autoPush = scanner.nextLine().toLowerCase();
        System.out.print("Delay before commiting again (ms): ");
        int delay = scanner.nextInt();
        System.out.println("Committing " + commitCount + " times...");

        for (int i = 0; i < commitCount; i++) {
            try {
                Runtime.getRuntime().exec(new String[]{"git", "commit", "--allow-empty", "-m", String.format("Commit %d of %d", i + 1, commitCount)});
                System.out.printf("Commit %d of %d Done!%n", i + 1, commitCount);
                Thread.sleep(delay);
            } catch (IOException | InterruptedException e) {
                System.err.println("Error during commit: " + e.getMessage());
                e.printStackTrace();
            }
        }

        println("Commited " + commitCount + " times! Pushing...");

        if (autoPush.equals("y")) {
            try {
                Runtime.getRuntime().exec(new String[]{"git", "push"});
                System.out.println("Pushed all commits to GitHub!");
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
}
import java.io.IOException;
import java.util.Scanner;

public class index {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many times do you want to commit? \n");
        int commitCount = scanner.nextInt();

        System.out.print("Auto git push when committed? (y/n) \n");
        String autoPush = scanner.nextLine().toLowerCase();

        for (int i = 0; i < commitCount; i++) {
            String commitMessage = String.format("Commit %d of %d", i + 1, commitCount);
            try {
                Runtime.getRuntime().exec(new String[]{"git", "commit", "--allow-empty", "-m", commitMessage});
            } catch (IOException e) {
                System.err.println("Error during commit: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("Commited " + commitCount + " times");

        if (autoPush.equals("y")) {
            try {
                Runtime.getRuntime().exec(new String[]{"git", "push"});
            } catch (IOException e) {
                System.err.println("Error during push: " + e.getMessage());
                e.printStackTrace();
            }
        }

        scanner.close();
    }
}

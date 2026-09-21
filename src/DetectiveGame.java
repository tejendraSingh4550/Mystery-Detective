
import java.util.ArrayList;
import java.util.Scanner;

public class DetectiveGame {

    private Scanner sc = new Scanner(System.in);

    private ArrayList<Clue> clues = new ArrayList<>();

    private Suspect[] suspects = {
        new Suspect(
        "Alex",
        "I was at the cafe all evening.",
        false
        ),
        new Suspect(
        "Ryan",
        "I never entered the office.",
        true
        ),
        new Suspect(
        "Sam",
        "I left before 8 PM.",
        false
        )
    };

    private int score = 0;
    private int investigations = 5;

    public void start() {

        System.out.println("======================================");
        System.out.println("       🕵️ MYSTERY DETECTIVE");
        System.out.println("======================================");

        System.out.println(
                "\nCase: The Missing Company Laptop"
        );

        System.out.println(
                "A laptop disappeared from the office."
        );

        System.out.println(
                "You have 5 investigation attempts."
        );

        while (investigations > 0) {

            showMenu();

            int choice = readNumber();

            switch (choice) {

                case 1:
                    investigateOffice();
                    break;

                case 2:
                    questionSuspects();
                    break;

                case 3:
                    showClues();
                    break;

                case 4:
                    solveCase();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        System.out.println("\n❌ Investigation time over!");
        System.out.println("The case remains unsolved.");
    }

    private void showMenu() {

        System.out.println("\n--------------------------------------");
        System.out.println("Investigation Attempts: " + investigations);
        System.out.println("--------------------------------------");

        System.out.println("1. Investigate Office");
        System.out.println("2. Question Suspects");
        System.out.println("3. View Evidence");
        System.out.println("4. Solve Case");

        System.out.print("\nChoose: ");
    }

    private void investigateOffice() {

        investigations--;

        System.out.println("\n🔎 Searching the office...");

        if (!hasClue("Broken Window")) {

            clues.add(
                    new Clue(
                            "Broken Window",
                            "The window was broken from inside."
                    )
            );

            score += 20;

            System.out.println(
                    "Found clue: Broken Window"
            );

        } else {

            System.out.println(
                    "You already investigated this area."
            );
        }

        if (!hasClue("Security Card")) {

            clues.add(
                    new Clue(
                            "Security Card",
                            "A security card was found near the desk."
                    )
            );

            score += 20;

            System.out.println(
                    "Found clue: Security Card"
            );
        }
    }

    private void questionSuspects() {

        investigations--;

        System.out.println("\n👤 SUSPECTS");

        for (int i = 0; i < suspects.length; i++) {

            System.out.println(
                    (i + 1) + ". "
                    + suspects[i].getName()
            );
        }

        System.out.print("\nQuestion suspect: ");

        int choice = readNumber();

        if (choice < 1 || choice > suspects.length) {

            System.out.println("Invalid suspect.");
            return;
        }

        Suspect suspect = suspects[choice - 1];

        System.out.println(
                "\n" + suspect.getName()
                + ": \"" + suspect.getStatement() + "\""
        );

        if (suspect.isGuilty()) {

            clues.add(
                    new Clue(
                            "Suspicious Statement",
                            suspect.getName()
                            + " gave a suspicious statement."
                    )
            );

            score += 30;
        }
    }

    private void showClues() {

        System.out.println("\n======================================");
        System.out.println("             EVIDENCE");
        System.out.println("======================================");

        if (clues.isEmpty()) {

            System.out.println("No evidence collected yet.");
            return;
        }

        for (Clue clue : clues) {

            System.out.println(
                    "\n🔎 " + clue.getName()
            );

            System.out.println(
                    clue.getDescription()
            );
        }
    }

    private void solveCase() {

        System.out.println("\n======================================");
        System.out.println("             SOLVE CASE");
        System.out.println("======================================");

        System.out.println("\nWho stole the laptop?");

        for (int i = 0; i < suspects.length; i++) {

            System.out.println(
                    (i + 1) + ". "
                    + suspects[i].getName()
            );
        }

        System.out.print("\nYour answer: ");

        int choice = readNumber();

        if (choice < 1 || choice > suspects.length) {

            System.out.println("Invalid choice.");
            return;
        }

        Suspect selected = suspects[choice - 1];

        if (selected.isGuilty()) {

            score += 50;

            System.out.println(
                    "\n🎉 CASE SOLVED!"
            );

            System.out.println(
                    "The culprit was "
                    + selected.getName() + "."
            );

            System.out.println(
                    "Your Score: " + score
            );

            if (score >= 100) {
                System.out.println(
                        "🏆 Rank: Master Detective"
                );
            } else {
                System.out.println(
                        "🥈 Rank: Junior Detective"
                );
            }

        } else {

            System.out.println(
                    "\n❌ Wrong suspect!"
            );

            System.out.println(
                    "The real culprit was Ryan."
            );

            System.out.println(
                    "Final Score: " + score
            );
        }
    }

    private boolean hasClue(String name) {

        for (Clue clue : clues) {

            if (clue.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    private int readNumber() {

        try {

            return Integer.parseInt(
                    sc.nextLine()
            );

        } catch (Exception e) {

            return -1;
        }
    }
}

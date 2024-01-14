import java.util.Random;
import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 5;
        int totalRounds = 3;
        int totalScore = 0;

        System.out.println("Welcome to the Enhanced Number Guessing Game!");
        System.out.println("Guess the number between " + lowerBound + " and " + upperBound);

        for (int round = 1; round <= totalRounds; round++) {
            int generatedNumber = generateRandomNumber(lowerBound, upperBound);
            int roundScore = playOneRound(scanner, generatedNumber, maxAttempts);
            totalScore += roundScore;

            System.out.println("Round " + round + " over. Your Score: " + roundScore);
            System.out.println("Total Score: " + totalScore);

            if (round < totalRounds) {
                System.out.println("Next round starting...");
            }
        }

        System.out.println("Game Over. Thank you for playing!");
        scanner.close();
    }

    private static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    private static int playOneRound(Scanner scanner, int generatedNumber, int maxAttempts) {
        int attemptsLeft = maxAttempts;
        int roundScore = 0;

        while (attemptsLeft > 0) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            if (userGuess == generatedNumber) {
                System.out.println("Congratulations! You guessed the correct number: " + generatedNumber);
                roundScore = calculateScore(attemptsLeft, maxAttempts);
                break;
            } else if (userGuess < generatedNumber) {
                System.out.println("Try again! The number is higher.");
            } else {
                System.out.println("Try again! The number is lower.");
            }

            attemptsLeft--;
            System.out.println("Attempts left: " + attemptsLeft);
        }

        return roundScore;
    }

    private static int calculateScore(int attemptsLeft, int maxAttempts) {
        // A more complex scoring mechanism: more points for fewer attempts, with a bonus for early correct guesses
        int baseScore = (maxAttempts - attemptsLeft + 1) * 10;
        int bonusScore = (attemptsLeft == maxAttempts) ? 0 : 20;
        return baseScore + bonusScore;
    }
}

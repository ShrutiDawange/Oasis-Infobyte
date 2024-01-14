import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    String username;
    String password;
    String profile;

    User(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }
}

class Question {
    String question;
    String[] options;
    int correctOption;

    Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class OnlineExaminationSystem {
    private static Map<String, User> users = new HashMap<>();
    private static Map<String, Question> questions = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeUsers();
        initializeQuestions();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User currentUser = login(username, password);

        if (currentUser != null) {
            System.out.println("Login successful. Welcome, " + currentUser.username + "!");
            updateProfile(currentUser);
            selectAnswers();
            timerAndAutoSubmit();
            closeSessionAndLogout();
        } else {
            System.out.println("Login failed. Exiting...");
        }
    }

    private static void initializeUsers() {
        users.put("user1", new User("user1", "password1", "Student"));
        users.put("user2", new User("user2", "password2", "Teacher"));
        // Add more user profiles as needed
    }

    private static void initializeQuestions() {
        questions.put("q1", new Question("What is the capital of France?",
                new String[]{"Berlin", "Paris", "Madrid", "London"}, 1));

        questions.put("q2", new Question("Which programming language is Java?",
                new String[]{"C++", "Java", "Python", "Ruby"}, 2));
        // Add more questions as needed
    }

    private static User login(String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void updateProfile(User user) {
        System.out.println("Current Profile: " + user.profile);
        System.out.print("Enter new profile: ");
        String newProfile = scanner.nextLine();
        user.profile = newProfile;

        System.out.println("Profile updated successfully. New profile: " + user.profile);
    }

    private static void selectAnswers() {
        System.out.println("Selecting answers for MCQs...");
        for (Map.Entry<String, Question> entry : questions.entrySet()) {
            Question question = entry.getValue();
            System.out.println(question.question);
            for (int i = 0; i < question.options.length; i++) {
                System.out.println((i + 1) + ". " + question.options[i]);
            }

            System.out.print("Enter your choice (1-" + question.options.length + "): ");
            int userChoice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            if (userChoice == question.correctOption) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is: " + question.options[question.correctOption - 1]);
            }
        }
    }

    private static void timerAndAutoSubmit() {
        System.out.println("Timer started. You have 10 minutes for the examination.");

        // Simulating time delay for 10 minutes (600,000 milliseconds)
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Time's up! Auto-submitting the examination.");
    }

    private static void closeSessionAndLogout() {
        System.out.println("Closing session and logging out...");
        scanner.close();
        System.exit(0);
    }
}

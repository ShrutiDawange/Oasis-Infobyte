import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Reservation {
    String passengerName;
    String trainNumber;
    String classType;
    String dateOfJourney;
    String from;
    String to;
    String pnr;

    Reservation(String passengerName, String trainNumber, String classType, String dateOfJourney, String from, String to, String pnr) {
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
        this.pnr = pnr;
    }
}

public class OnlineReservationSystem {
    private static List<Reservation> reservations = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Reservation");
            System.out.println("2. Cancellation");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    doReservation();
                    break;
                case 2:
                    doCancellation();
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void doReservation() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter source station: ");
        String from = scanner.nextLine();
        System.out.print("Enter destination station: ");
        String to = scanner.nextLine();

        String pnr = generatePNR();

        Reservation reservation = new Reservation(name, trainNumber, classType, dateOfJourney, from, to, pnr);
        reservations.add(reservation);

        System.out.println("Reservation successful!");
        System.out.println("Your PNR is: " + pnr);
    }

    private static void doCancellation() {
        System.out.print("Enter your PNR number: ");
        String pnr = scanner.nextLine();

        for (Reservation reservation : reservations) {
            if (reservation.pnr.equals(pnr)) {
                System.out.println("Passenger Name: " + reservation.passengerName);
                System.out.println("Train Number: " + reservation.trainNumber);
                System.out.println("Class Type: " + reservation.classType);
                System.out.println("Date of Journey: " + reservation.dateOfJourney);
                System.out.println("From: " + reservation.from);
                System.out.println("To: " + reservation.to);

                System.out.print("Do you want to cancel this reservation? (Enter 'OK' to confirm): ");
                String confirmation = scanner.nextLine();
                if (confirmation.equalsIgnoreCase("OK")) {
                    reservations.remove(reservation);
                    System.out.println("Cancellation successful!");
                } else {
                    System.out.println("Cancellation aborted.");
                }
                return;
            }
        }

        System.out.println("No reservation found with the provided PNR.");
    }

    private static String generatePNR() {
        // Generate a simple PNR (for demonstration purposes)
        return "PNR" + System.currentTimeMillis();
    }
}

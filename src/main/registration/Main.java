package registration;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RegistrationManager manager = new RegistrationManager();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1:
                    addRegistration();
                    break;
                case 2:
                    manager.displayAll();
                    break;
                case 3:
                    findRegistration();
                    break;
                case 4:
                    deleteRegistration();
                    break;
                case 5:
                    showByEvent();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                case Integer.MIN_VALUE:
                    break;
                default:
                    System.out.println("Unknown option. Please choose 0-5.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("1. Add registration");
        System.out.println("2. Show all registrations");
        System.out.println("3. Find registration by ID");
        System.out.println("4. Delete registration by ID");
        System.out.println("5. Show registrations for an event");
        System.out.println("0. Exit");
    }

        private static int readInt(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            return 0;
        }
        String line = scanner.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number, not \"" + line + "\".");
            return Integer.MIN_VALUE;
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.hasNextLine() ? scanner.nextLine() : "";
    }

    private static void printEvents() {
        for (int i = 0; i < RegistrationManager.EVENTS.length; i++) {
            System.out.println((i + 1) + ". " + RegistrationManager.EVENTS[i]);
        }
    }

    private static void addRegistration() {
        String name;
        while (true) {
            name = readLine("Full name: ");
            String error = RegistrationManager.validateName(name);
            if (error == null) break;
            System.out.println(error);
            if (!askRetry()) return;
        }

        String email;
        while (true) {
            email = readLine("Email: ");
            String error = RegistrationManager.validateEmail(email);
            if (error == null) break;
            System.out.println(error);
            if (!askRetry()) return;
        }

        String group;
        while (true) {
            group = readLine("Student group: ");
            String error = RegistrationManager.validateGroup(group);
            if (error == null) break;
            System.out.println(error);
            if (!askRetry()) return;
        }

        int eventNumber;
        while (true) {
            printEvents();
            eventNumber = readInt("Select event: ");
            if (eventNumber == Integer.MIN_VALUE) {
                if (!askRetry()) return;
                continue;
            }
            String error = RegistrationManager.validateEventNumber(eventNumber);
            if (error == null) break;
            System.out.println(error);
            if (!askRetry()) return;
        }

        int id = manager.addRegistration(name, email, group, eventNumber);
        if (id > 0) {
            System.out.println("Registration created. ID: " + id);
        } else {
            System.out.println("Registration was not created.");
        }
    }

    private static boolean askRetry() {
        String answer = readLine("Try again? (y = retry, anything else = back to menu): ");
        return answer.trim().equalsIgnoreCase("y");
    }

    private static void findRegistration() {
        int id = readInt("Enter ID: ");
        if (id == Integer.MIN_VALUE) return;
        Registration r = manager.findById(id);
        if (r == null) {
            System.out.println("Registration not found.");
        } else {
            System.out.println(r);
        }
    }

    private static void deleteRegistration() {
        int id = readInt("Enter ID: ");
        if (id == Integer.MIN_VALUE) return;
        if (manager.deleteById(id)) {
            System.out.println("Registration " + id + " deleted.");
        } else {
            System.out.println("Registration not found. Nothing deleted.");
        }
    }

    private static void showByEvent() {
        printEvents();
        int n = readInt("Select event: ");
        if (n == Integer.MIN_VALUE) return;
        String error = RegistrationManager.validateEventNumber(n);
        if (error != null) {
            System.out.println(error);
            return;
        }
        manager.displayByEvent(n);
    }
}

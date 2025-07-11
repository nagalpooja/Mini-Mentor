import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Admin - Add Subject");
            System.out.println("4. Admin - Add Topic");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String input;
            if (sc.hasNextLine()) {
                input = sc.nextLine().trim();
            } else {
                System.out.println("No input found. Exiting.");
                break;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 1) {
                Student.register(sc);
                sc = new Scanner(System.in);

            } else if (choice == 2) {
                int studentId = Student.login(sc);
                if (studentId != -1) {
                    while (true) {
                        System.out.println("\n1. View Subjects");
                        System.out.println("2. View Topics");
                        System.out.println("3. Mark Topic Completed");
                        System.out.println("4. View Completed Topics");
                        System.out.println("5. Logout");
                        System.out.print("Enter your choice: ");

                        String subInput;
                        if (sc.hasNextLine()) {
                            subInput = sc.nextLine().trim();
                        } else {
                            System.out.println("No input found. Logging out...");
                            break;
                        }

                        int subChoice;
                        try {
                            subChoice = Integer.parseInt(subInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                            continue;
                        }

                        if (subChoice == 1) Subject.listSubjects(sc);
                        else if (subChoice == 2) Topic.listTopicsBySubject(sc);
                        else if (subChoice == 3) Progress.markTopicCompleted(studentId);
                        else if (subChoice == 4) Progress.viewCompletedTopics(studentId);
                        else if (subChoice == 5) break;
                        else System.out.println("Invalid option.");
                    }
                }

            } else if (choice == 3) {
                Admin.addSubject(sc);
            } else if (choice == 4) {
                Admin.addTopic(sc);
            } else if (choice == 5) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close(); 
    }
}

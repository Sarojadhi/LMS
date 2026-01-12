import java.io.*;
import java.util.Scanner;

public class Main {

    static final String FILE_NAME = "student.txt";
    static Scanner sc = new Scanner(System.in);
    static final String DELIMITER = "=====>>";

    public static void main(String[] args) {

        File file = new File(FILE_NAME);

        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        while (true) {

            System.out.println("\n==== Student Record System ====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student");
            System.out.println("5. Save And Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    deleteStudent();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ADD STUDENT
    static void addStudent() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            System.out.print("Enter Student ID: ");
            String id = sc.nextLine();

            String name;
            while (true) {

                System.out.print("Enter Student Name (Alphabet only): ");
                name = sc.nextLine();

                if (name.matches("[a-zA-Z ]+")) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter letters only.");
                }
            }

            System.out.print("Enter Student Age: ");

            int age;
            while (true) {

                age = sc.nextInt();
                sc.nextLine();

                if (age >= 15 && age <= 120) {
                    break;
                } else {
                    System.out.println("Enter between 15 to 120");
                }
            }

            bw.write(id + DELIMITER + name + DELIMITER + age);
            bw.newLine();

            System.out.println("Student added successfully!");

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    // VIEW STUDENTS
    static void viewStudents() {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            System.out.println("\n--- Student Records ---");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(DELIMITER);

                if (data.length < 3) {
                    System.out.println("Invalid record skipped: " + line);
                    continue;
                }

                System.out.println(
                        "ID=====>> " + data[0] +
                        " | Name=====>> " + data[1] +
                        " | Age=====>> " + data[2]
                );
            }

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    // SEARCH STUDENT
    static void searchStudent() {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            System.out.print("Enter Student ID to search: ");
            String searchId = sc.nextLine();

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(DELIMITER);

                if (data.length < 3) {
                    continue;
                }

                if (data[0].equals(searchId)) {

                    System.out.println(
                            "ID: " + data[0] +
                            " | Name: " + data[1] +
                            " | Age: " + data[2]
                    );

                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student not found.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    // DELETE STUDENT
    static void deleteStudent() {

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        try (
                BufferedReader br = new BufferedReader(new FileReader(inputFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))
        ) {

            System.out.print("Enter Student ID to delete: ");
            String deleteId = sc.nextLine();

            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(DELIMITER);

                if (data.length < 3) {
                    bw.write(line);
                    bw.newLine();
                    continue;
                }

                if (data[0].equals(deleteId)) {
                    found = true;
                    continue;
                }

                bw.write(line);
                bw.newLine();
            }

            if (found) {
                inputFile.delete();
                tempFile.renameTo(inputFile);
                System.out.println("Student deleted successfully.");
            } else {
                tempFile.delete();
                System.out.println("Student not found.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}

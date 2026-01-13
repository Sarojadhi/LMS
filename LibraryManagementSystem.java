import java.io.*;
import java.util.Scanner;

class Student {
    String id;
    String name;
    int age;

    Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return id + "=====>>" + name + "=====>>" + age;
    }
}

public class Main {

    static final String FILE_NAME = "student.txt";
    static final String DELIMITER = "=====>>";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        createFile();

        while (true) {
            System.out.println("\n==== Student Record System ====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = getIntInput();

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
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // CREATE FILE
    static void createFile() {
        try {
            File file = new File(FILE_NAME);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("File error");
        }
    }

    // ADD STUDENT
    static void addStudent() {

        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        if (id.isEmpty() || idExists(id)) {
            System.out.println("Invalid or duplicate ID");
            return;
        }

        String name;
        while (true) {
            System.out.print("Enter Name: ");
            name = sc.nextLine();
            if (name.matches("[a-zA-Z ]+")) break;
            System.out.println("Letters only!");
        }

        System.out.print("Enter Age: ");
        int age = getIntRange(15, 120);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(new Student(id, name, age).toString());
            bw.newLine();
            System.out.println("Student added");
        } catch (IOException e) {
            System.out.println("Write error");
        }
    }

    // VIEW STUDENTS
    static void viewStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Records ---");
            while ((line = br.readLine()) != null) {
                String[] d = line.split(DELIMITER);
                if (d.length < 3) continue;
                System.out.println("ID: " + d[0] + " | Name: " + d[1] + " | Age: " + d[2]);
            }
        } catch (IOException e) {
            System.out.println("Read error");
        }
    }

    // SEARCH
    static void searchStudent() {
        System.out.print("Enter ID: ");
        String id = sc.nextLine();
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(DELIMITER);
                if (d.length < 3) continue;
                if (d[0].equals(id)) {
                    System.out.println("Found → " + d[1] + ", Age: " + d[2]);
                    found = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Search error");
        }

        if (!found) System.out.println("Student not found");
    }

    // UPDATE
    static void updateStudent() {

        File temp = new File("temp.txt");
        boolean updated = false;

        System.out.print("Enter ID to update: ");
        String id = sc.nextLine();

        try (
                BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(DELIMITER);
                if (d.length < 3) continue;

                if (d[0].equals(id)) {
                    String name;
                    while (true) {
                        System.out.print("New Name: ");
                        name = sc.nextLine();
                        if (name.matches("[a-zA-Z ]+")) break;
                        System.out.println("Letters only!");
                    }

                    System.out.print("New Age: ");
                    int age = getIntRange(15, 120);
                    bw.write(new Student(id, name, age).toString());
                    updated = true;
                } else {
                    bw.write(line);
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Update error");
        }

        replaceFile(temp, updated, "Updated", "ID not found");
    }

    // DELETE
    static void deleteStudent() {

        File temp = new File("temp.txt");
        boolean deleted = false;

        System.out.print("Enter ID to delete: ");
        String id = sc.nextLine();

        try (
                BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(DELIMITER);
                if (d.length < 3) continue;

                if (d[0].equals(id)) {
                    deleted = true;
                    continue;
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Delete error");
        }

        replaceFile(temp, deleted, "Deleted", "ID not found");
    }

    // HELPERS
    static boolean idExists(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(id + DELIMITER)) return true;
            }
        } catch (IOException e) {}
        return false;
    }

    static int getIntInput() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Enter number: ");
        }
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    static int getIntRange(int min, int max) {
        int n;
        while (true) {
            n = getIntInput();
            if (n >= min && n <= max) return n;
            System.out.print("Enter between " + min + " - " + max + ": ");
        }
    }

    static void replaceFile(File temp, boolean success, String ok, String fail) {
        File original = new File(FILE_NAME);
        if (success && original.delete()) {
            temp.renameTo(original);
            System.out.println(ok);
        } else {
            temp.delete();
            System.out.println(fail);
        }
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager manager = new ContactManager();
        String filePath = "contacts.csv";

        while (true) {
            System.out.println("---- CONTACT MANAGEMENT PROGRAM ----");
            System.out.println("1. View all contacts");
            System.out.println("2. Add new contact");
            System.out.println("3. Update contact");
            System.out.println("4. Delete contact");
            System.out.println("5. Search contacts");
            System.out.println("6. Read from file");
            System.out.println("7. Save to file");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    manager.viewAll();
                    break;
                case 2:
                    System.out.println("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Enter group: ");
                    String group = scanner.nextLine();
                    System.out.println("Enter full name: ");
                    String fullName = scanner.nextLine();
                    System.out.println("Enter gender: ");
                    String gender = scanner.nextLine();
                    System.out.println("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.println("Enter birth date: ");
                    String birthDate = scanner.nextLine();
                    System.out.println("Enter email: ");
                    String email = scanner.nextLine();

                    Contact newContact = new Contact(phoneNumber, group, fullName, gender, address, birthDate, email);
                    manager.addContact(newContact);
                    manager.writeToFile(filePath);
                    System.out.println("New contact added.");
                    break;
                case 3:
                    System.out.println("Enter phone number of contact to update: ");
                    String phoneToUpdate = scanner.nextLine();

                    System.out.println("Enter new information:");
                    System.out.println("Enter group: ");
                    String newGroup = scanner.nextLine();
                    System.out.println("Enter full name: ");
                    String newFullName = scanner.nextLine();
                    System.out.println("Enter gender: ");
                    String newGender = scanner.nextLine();
                    System.out.println("Enter address: ");
                    String newAddress = scanner.nextLine();
                    System.out.println("Enter birth date: ");
                    String newBirthDate = scanner.nextLine();
                    System.out.println("Enter emai2l: ");
                    String newEmail = scanner.nextLine();

                    Contact updatedContact = new Contact(phoneToUpdate, newGroup, newFullName, newGender, newAddress, newBirthDate, newEmail);
                    if (manager.updateContact(phoneToUpdate, updatedContact)) {
                        System.out.println("Contact updated successfully.");
                    } else {
                        System.out.println("Phone number not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter phone number of contact to delete: ");
                    String phoneToDelete = scanner.nextLine();
                    if (manager.deleteContact(phoneToDelete)) {
                        System.out.println("Contact deleted successfully.");
                    } else {
                        System.out.println("Phone number not found.");
                    }
                    break;
                case 5:
                    System.out.println("Enter keyword to search (phone number or full name): ");
                    String keyword = scanner.nextLine();
                    var results = manager.searchContacts(keyword);
                    if (results.isEmpty()) {
                        System.out.println("No results found.");
                    } else {
                        results.forEach(System.out::println);
                    }
                    break;
                case 6:
                    manager.readFromFile(filePath);
                    break;
                case 7:
                    manager.writeToFile(filePath);
                    break;
                case 8:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option selected.");
            }
        }
    }
}

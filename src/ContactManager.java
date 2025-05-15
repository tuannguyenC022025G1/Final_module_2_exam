import java.io.*;
import java.util.*;

public class ContactManager {
    private List<Contact> contactList = new ArrayList<>();

    // View all contacts
    public void viewAll() {
        if (contactList.isEmpty()) {
            System.out.println("Contact list is empty.");
            return;
        }
        for (Contact c : contactList) {
            System.out.println(c);
        }
    }

    //check data
    public boolean isValidContact(Contact contact) {

        if (!contact.getPhoneNumber().matches("\\d{10}")) {
            System.out.println("Invalid phone number. It must be 10 digits.");
            return false;
        }


        if (!contact.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            System.out.println("Invalid email address.");
            return false;
        }


        if (contact.getFullName() == null || contact.getFullName().trim().isEmpty()) {
            System.out.println("Name cannot be empty.");
            return false;
        }

        if (!contact.getBirthDate().matches("^([0-2][0-9]|3[0-1])/([0][1-9]|1[0-2])/\\d{4}$")) {
            System.out.println("Invalid birth date. It must be in the format dd/mm/yyyy.");
            return false;
        }
        if (!contact.getGender().equalsIgnoreCase("male") && !contact.getGender().equalsIgnoreCase("female")) {
            System.out.println("Invalid gender. Please enter 'male' or 'female'.");
            return false;
        }
        if (contact.getAddress() == null || contact.getAddress().trim().isEmpty()) {
            System.out.println("Address cannot be empty.");
            return false;
        }
        if (contact.getGroup() == null || contact.getGroup().trim().isEmpty()) {
            System.out.println("Group cannot be empty.");
            return false;
        }


        return true;
    }


    // add new contact
    public void addContact(Contact contact) {
        if (isValidContact(contact)) {
            contactList.add(contact);
            writeToFile("contacts.csv");
            System.out.println("Contact added successfully.");
        } else {
            System.out.println("Failed to add contact due to invalid data.");
        }
    }

    // Update contact by phone number
    public boolean updateContact(String phoneNumber, Contact newContact) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getPhoneNumber().equals(phoneNumber)) {
                contactList.set(i, newContact);
                return true;
            }
        }
        return false;
    }

    // Delete contact by phone number
    public boolean deleteContact(String phoneNumber) {
        return contactList.removeIf(c -> c.getPhoneNumber().equals(phoneNumber));
    }

    // Search contact by phone number or full name
    public List<Contact> searchContacts(String keyword) {
        List<Contact> results = new ArrayList<>();
        for (Contact c : contactList) {
            if (c.getPhoneNumber().contains(keyword) || c.getFullName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(c);
            }
        }
        return results;
    }

    // Read from CSV file
    public void readFromFile(String filePath) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Loading from file will replace current contact list. Do you want to continue? (Y/N): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (!confirmation.equals("y") && !confirmation.equals("yes")) {
            System.out.println("Action cancelled. Contact list not modified.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            contactList.clear();

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    Contact c = new Contact(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
                    contactList.add(c);
                }
            }
            System.out.println("Contact list loaded from file." + contactList);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    // Write to CSV file
    public void writeToFile(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Contact c : contactList) {
                bw.write(c.toString());
                bw.newLine();
            }
            System.out.println("Contact list saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int attemptLimit = 3;
    private static int currentID = 1000; // Starting ID

    public static int generateID(AVLTree<AccountNode> accountRoot) {
        AccountNode temp = accountRoot.getRoot().getRight();

        if(temp == null) {
            System.out.println("ID can not be generated, no accounts exist.");
            return 0;
        }
        while(temp.getRight() != null)
        {
            temp = temp.getRight();
            System.out.println("Current ID: " + temp.getId());
        } 
        return temp.getId() + 1; // Increment the last ID found
    }


    public static void main(String[] args) {
        AccountNode accountNode = new AccountNode();
        UserNode userNode = new UserNode();
        AVLTree<UserNode> adminRoot = new AVLTree<>();
        AVLTree<UserNode> staffRoot = new AVLTree<>();
        AVLTree<AccountNode> accountRoot = new AVLTree<>();

        int choice = 1, choice1 = 1, id = 0, pin = 0;
        String username, password, accountType = "",type = "";

        // Load data (you need to implement file reading in your AVLTree class)
        accountRoot = DataLoader.loadAccountData("accounts.txt");
        adminRoot = DataLoader.loadUserData("admin.txt");
        staffRoot = DataLoader.loadUserData("staff.txt");
        System.out.println("**********************************WELCOME TO BANK MANAGEMENT SYSTEM*********************************");

        while (choice != 0) {
            System.out.println("Please Select Your Role");
            System.out.println("1. Admin");
            System.out.println("2. Staff");
            System.out.println("3. ATM Services");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (authenticate(adminRoot)) {
                        while (choice1 != 0) {
                            System.out.println("Select an operation:");
                            System.out.println("1. Create new accountNode");
                            System.out.println("2. Delete accountNode");
                            System.out.println("3. Search accountNode");
                            System.out.println("4. View all accounts");
                            System.out.println("5. Update accountNode");
                            System.out.println("6. View logs");
                            System.out.println("0. Logout");
                            System.out.print("Enter your choice: ");
                            choice1 = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (choice1) {
                                case 1:
                                    System.out.println("Enter the name (CNIC Name): ");
                                    username = scanner.nextLine();
                                    int newID = generateID(accountRoot);
                                    System.out.println("Generated ID: " + newID);

                                    System.out.print("Enter password: ");
                                    password = scanner.nextLine();
                                    System.out.print("Enter PIN: ");
                                    pin = scanner.nextInt();

                                    System.out.println("Select Account Type:\n1. Savings\n2. Current\n3. Default");
                                    int typeChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (typeChoice) {
                                        case 1 -> accountType = "Savings";
                                        case 2 -> accountType = "Current";
                                        default -> accountType = "Default";
                                        }
                                        if (newID == 0) {
                                            newID = 1001;
                                        }
                                    accountNode=new AccountNode(newID, username, password, "active", accountType, 0.0f, pin);
                                   System.out.println("Account created successfully.");
                                    break;
                                case 2:
                                    System.out.print("Enter accountNode ID to delete: ");
                                    id = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    if(accountRoot.search(id) == null)
                                    {
                                        System.out.println("Account with ID " + id + " not found.");
                                    }
                                    else
                                    {
                                        accountRoot.deleteNode(id);
                                        System.out.println("Account with ID " + id + " deleted successfully.");
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter account ID to search: ");
                                    id = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    accountNode = accountRoot.search(id);
                                    if(accountNode == null)
                                    {
                                        System.out.println("Account with ID " + id + " not found.");

                                    }//end of if 
                                    else
                                    {
                                        System.out.println("Account found: ID: " + accountNode.getId() + ", Username: " + accountNode.getUsername() +
                                                ", Type: " + accountNode.getType() + ", Balance: " + accountNode.getBalance() + ", Status: " + accountNode.getStatus());
                                    }//End of else
                                    break;
                                case 4:
                                    accountRoot.inorderDisplay();
                                    break;
                                case 5:
                                    System.out.print("Enter account ID to update: ");
                                    id = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    accountNode = accountRoot.search(id);
                                    if (accountNode == null) {
                                        System.out.println("Account with ID " + id + " not found.");
                                        break;
                                    }
                                    System.out.print("Select the attribute you want to change:");
                                    System.out.println("1. Username");
                                    System.out.println("2. Password");
                                    System.out.println("3. PIN");
                                    System.out.println("4. Account Type");
                                    System.out.println("5.Status");
                                    System.out.print("Enter your choice: ");
                                    int updateChoice = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    switch (updateChoice) {
                                        case 1:
                                            System.out.println("Enter new username: ");
                                            username = scanner.nextLine();
                                            break;
                                            case 2:
                                            System.out.println("Enter new password: ");
                                            password = scanner.nextLine();
                                            break;
                                        case 3:
                                            System.out.println("Enter new PIN: ");
                                            pin = scanner.nextInt();
                                            scanner.nextLine(); // Consume newline
                                            break;
                                        case 4:
                                            System.out.println("Select new accountNode type:\n1. Savings\n2. Current\n3. Default");
                                            int newTypeChoice = scanner.nextInt();
                                            scanner.nextLine(); // Consume newline
                                            switch (newTypeChoice) {
                                                case 1 -> accountType = "Savings";
                                                case 2 -> accountType = "Current";
                                                default -> accountType = "Default";
                                            }
                                            break;
                                        case 5:
                                            System.out.println("Enter new status : ");
                                            System.out.println("1. Active");
                                            System.out.println("2. Inactive");
                                            System.out.println("3.Blocked");
                                            int statusChoice = scanner.nextInt();
                                            scanner.nextLine(); // Consume newline
                                            switch (statusChoice) {
                                                case 1 ->accountNode.setStatus("active"); 
                                                case 2 -> accountNode.setStatus("inactive");
                                                case 3 -> accountNode.setStatus("blocked");
                                                default->System.out.println("Invalid status choice."); 
                                            }
                                            System.out.println("Account status updated to: " + accountNode.getStatus());
                                    }
                                    break;
                                case 6:
                                    // Implement logs
                                    break;
                                case 0:
                                    System.out.println("Logging out...");
                                    break;
                            }
                        }
                    }
                    break;

                case 2:
                    if (authenticate(staffRoot)) {
                        while (choice1 != 0) {
                            System.out.println("Staff Menu:");
                            System.out.println("1. Check Account");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Deposit");
                            System.out.println("4. View Logs");
                            System.out.println("0. Logout");
                            System.out.print("Enter choice: ");
                            choice1 = scanner.nextInt();
                            scanner.nextLine();

                            switch (choice1) {
                                case 1:
                                    System.out.print("Enter accountNode ID to check: ");
                                    id = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    AccountNode accountToCheck = accountRoot.search(id);
                                    if(accountToCheck == null){
                                        System.out.println("Account with ID " + id + " not found.");
                                    } else {
                                        System.out.println("Account found: ID: " + accountToCheck.getId() + ", Username: " + accountToCheck.getUsername() +
                                                 ", Balance: " + accountToCheck.getBalance() + ", Status: " + accountToCheck.getStatus());
                                    }
                                    break;
                                case 2:
                                    System.out.print("Enter account ID for transaction: ");
                                    id = scanner.nextInt();
                                    scanner.nextLine(); 
                                    accountNode = accountRoot.search(id);
                                    if(accountNode == null){
                                        System.out.println("Account with ID " + id + " not found.");
                                    } else {
                                        System.out.print("Enter amount to withdraw: ");
                                        float amount = scanner.nextFloat();
                                        scanner.nextLine(); // Consume newline
                                        System.out.print("Enter PIN: ");
                                        pin = scanner.nextInt();
                                        scanner.nextLine(); // Consume newline
                                        if(pin != accountNode.getPin()) {
                                            System.out.println("Incorrect PIN. Transaction aborted.");
                                            break;
                                        }// End of if 
                                        else{
                                        accountNode.addTransaction("withdraw", amount); 
                                        }//End of else    
                                    }//End of switch
                                    break;
                                case 3:
                                    
                                    System.out.print("Enter account ID for transaction: ");
                                    id = scanner.nextInt();
                                    scanner.nextLine(); 
                                    accountNode = accountRoot.search(id);
                                    if(accountNode == null){
                                        System.out.println("Account with ID " + id + " not found.");
                                    } else {
                                        System.out.print("Enter amount to withdraw: ");
                                        float amount = scanner.nextFloat();
                                        scanner.nextLine(); // Consume newline
                                        System.out.print("Enter PIN: ");
                                        pin = scanner.nextInt();
                                        scanner.nextLine(); // Consume newline
                                        if(pin != accountNode.getPin()) {
                                            System.out.println("Incorrect PIN. Transaction aborted.");
                                            break;
                                        }// End of if 
                                        else{
                                        accountNode.addTransaction("deposit", amount); 
                                        }//End of else    
                                    }//End of switch
                                case 4:
                                    // Implement staff functionality
                                    break;
                                case 0:
                                    System.out.println("Logging out...");
                                    break;
                            }
                        }
                    }
                    break;

                case 3:
                    while (choice1 != 0) {
                        System.out.println("ATM Menu:");
                        System.out.println("1. Withdraw Cash");
                        System.out.println("2. Exit");
                        System.out.print("Enter choice: ");
                        choice1 = scanner.nextInt();
                        scanner.nextLine();

                        if (choice1 == 1) {
                            // Implement ATM withdrawal
                        } else if (choice1 == 2) {
                            System.out.println("Exiting ATM services...");
                            break;
                        }
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

            choice1 = 1; // reset submenu
        }
    }

    private static boolean authenticate(AVLTree<UserNode> root) {
        int attempts = attemptLimit;
        while (attempts-- > 0) {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            UserNode user = root.search(id);
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("Login Successful.");
                return true;
            } else {
                System.out.println("Invalid credentials. Attempts left: " + attempts);
            }
        }
        System.out.println("Too many failed attempts. Exiting...");
        return false;
    }
}

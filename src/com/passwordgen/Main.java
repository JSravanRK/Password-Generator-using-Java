package com.passwordgen;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PasswordGenerator generator = new PasswordGenerator();
        PasswordStrengthChecker checker = new PasswordStrengthChecker();
        VaultManager vault = new VaultManager();

        while (true) {
            System.out.println("\n==============================");
            System.out.println("     JAVA PASSWORD TOOL");
            System.out.println("==============================");
            System.out.println("1. Generate Password");
            System.out.println("2. Check Password Strength");
            System.out.println("3. Save Password Securely (AES Vault)");
            System.out.println("4. View Saved Vault (Decrypt)");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Include Uppercase? (Y/N): ");
                    boolean upper = sc.nextLine().trim().equalsIgnoreCase("Y");

                    System.out.print("Include Lowercase? (Y/N): ");
                    boolean lower = sc.nextLine().trim().equalsIgnoreCase("Y");

                    System.out.print("Include Numbers? (Y/N): ");
                    boolean digits = sc.nextLine().trim().equalsIgnoreCase("Y");

                    System.out.print("Include Symbols? (Y/N): ");
                    boolean symbols = sc.nextLine().trim().equalsIgnoreCase("Y");

                    System.out.print("Enter password length: ");
                    int len;
                    try {
                        len = Integer.parseInt(sc.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("❌ Invalid length.");
                        break;
                    }

                    try {
                        String pass = generator.generate(len, upper, lower, digits, symbols);
                        System.out.println("\n✅ Generated Password: " + pass);

                        String strength = checker.checkStrength(pass);
                        System.out.println("🔍 Strength: " + strength);

                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                }

                case "2" -> {
                    System.out.print("Enter password to check: ");
                    String pwd = sc.nextLine();

                    String strength = checker.checkStrength(pwd);
                    System.out.println("🔍 Strength: " + strength);
                }

                case "3" -> {
                    System.out.print("Enter app/website name: ");
                    String site = sc.nextLine();

                    System.out.print("Enter username/email: ");
                    String user = sc.nextLine();

                    System.out.print("Enter password: ");
                    String pass = sc.nextLine();

                    System.out.print("Set master key (remember it): ");
                    String masterKey = sc.nextLine();

                    try {
                        vault.saveCredential(site, user, pass, masterKey);
                        System.out.println("✅ Saved securely to vault.enc");
                    } catch (Exception e) {
                        System.out.println("❌ Error saving: " + e.getMessage());
                    }
                }

                case "4" -> {
                    System.out.print("Enter master key to decrypt vault: ");
                    String masterKey = sc.nextLine();

                    try {
                        System.out.println("\n--- SAVED VAULT CONTENT ---");
                        System.out.println(vault.readVault(masterKey));
                    } catch (Exception e) {
                        System.out.println("❌ Error reading vault: " + e.getMessage());
                    }
                }

                case "5" -> {
                    System.out.println("✅ Exiting...");
                    sc.close();
                    return;
                }

                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}


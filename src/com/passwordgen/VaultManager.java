package com.passwordgen;

import java.io.*;

public class VaultManager {

    private static final String VAULT_FILE = "vault.enc";
    private final CryptoManager crypto = new CryptoManager();

    public void saveCredential(String site, String username, String password, String masterKey) throws Exception {
        String record = "SITE: " + site + "\nUSER: " + username + "\nPASS: " + password + "\n---\n";
        String encrypted = crypto.encrypt(record, masterKey);

        try (FileWriter fw = new FileWriter(VAULT_FILE, true)) {
            fw.write(encrypted + "\n");
        }
    }

    public String readVault(String masterKey) throws Exception {
        File file = new File(VAULT_FILE);
        if (!file.exists()) return "Vault is empty (vault.enc not found).";

        StringBuilder out = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                try {
                    out.append(crypto.decrypt(line, masterKey)).append("\n");
                } catch (Exception e) {
                    out.append("❌ Could not decrypt one entry (wrong key?)\n");
                }
            }
        }
        return out.toString();
    }
}


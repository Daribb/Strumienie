import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Вызначаем начальный случайный хеш
        String initialHash = generateRandomHash();
        System.out.println("Initial Hash: " + initialHash);

        // Список транзакций
        List<Transaction> transactions = new ArrayList<>();
        String lastHash = initialHash;

        // Генерация 5 объектов Transaction
        for (int i = 0; i < 5; i++) {
            double randomAmount = generateRandomAmount();
            Transaction transaction = findValidTransaction(randomAmount, lastHash);
            transactions.add(transaction);
            lastHash = hash(transaction.toHashString());
            System.out.println("Transaction " + (i + 1) + ": " + transaction);
            System.out.println("Hash: " + lastHash);
        }
    }

    // Класс Transaction
    static class Transaction {
        private double amount;
        private String lastTransaction;
        private long nonce;

        public Transaction(double amount, String lastTransaction, long nonce) {
            this.amount = amount;
            this.lastTransaction = lastTransaction;
            this.nonce = nonce;
        }

        @Override
        public String toString() {
            return "{" +
                    "amount: " + amount +
                    ", lastTransaction: '" + lastTransaction + '\'' +
                    ", nonce: " + nonce +
                    '}';
        }

        public String toHashString() {
            return amount + lastTransaction + nonce;
        }
    }

    // Поиск корректного nonce
    private static Transaction findValidTransaction(double amount, String lastTransaction) throws NoSuchAlgorithmException {
        long nonce = 0;
        String hash;
        do {
            Transaction transaction = new Transaction(amount, lastTransaction, nonce);
            hash = hash(transaction.toHashString());
            nonce++;
        } while (!hash.endsWith("00000"));
        return new Transaction(amount, lastTransaction, nonce - 1);
    }

    // Генерация хеша
    private static String hash(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Генерация случайного начального хеша
    private static String generateRandomHash() throws NoSuchAlgorithmException {
        Random random = new Random();
        String randomString = Long.toString(random.nextLong());
        return hash(randomString);
    }

    // Генерация случайного значения amount
    private static double generateRandomAmount() {
        Random random = new Random();
        return 10 + (1000 - 10) * random.nextDouble();
    }
}
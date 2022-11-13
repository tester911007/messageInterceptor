
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public final class Computer {
    private static final String[] ALGORITHMS = {"HmacSHA1", "HmacSHA256", "HmacSHA384", "HmacSHA512"};
    private static String algorithm = null;
    private static SecretKey key = null;
    private static KeyGenerator kg = null;
    private final Mac mac;

    public Computer() {
        try {
            if (Computer.algorithm == null) {
                Computer.algorithm = getAlgorithm();
                System.out.println("Для шифровки был выбран алгоритм: " + Computer.algorithm);
                Computer.kg = KeyGenerator.getInstance(Computer.algorithm);
                Computer.kg.init(new Random().nextInt(16) + 40);
                Computer.key = Computer.kg.generateKey();
            }
            this.mac = Mac.getInstance(Computer.algorithm);
            this.mac.init(Computer.key);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Ты вот зачем в код полез, вот и получай теперь");
        }
    }

    public SendBox sendBox(String message) {
        mac.update(message.getBytes(StandardCharsets.UTF_8));
        return new SendBox(message, Base64.getEncoder().encodeToString(mac.doFinal()));
    }

    public void getBox(SendBox box) {
        mac.update(box.getMessage().getBytes(StandardCharsets.UTF_8));
        System.out.println("Получено сообщение  \"" + box.getMessage() + "\"");
        if (box.getHash().equals(Base64.getEncoder().encodeToString(mac.doFinal()))) {
            System.out.println("Вмешательство хакера не обнаружено ");
        } else {
            System.out.println("Обнаружен взлом!!!");
        }
    }

    private String getAlgorithm() {
        return Computer.ALGORITHMS[new Random().nextInt(4)];
    }

}

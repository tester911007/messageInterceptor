import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Main {
    private static final String[] ALGORITHMS = {"HmacSHA1", "HmacSHA256", "HmacSHA384", "HmacSHA512"};

    public static void main(String[] args) {
        Computer firstComp = new Computer();
        Computer secondComputer = new Computer();
        SendBox box = firstComp.sendBox("message for you");

        box = hack(box, "change message not for you");

        secondComputer.getBox(box);
    }

    private static SendBox hack(SendBox box, String message) {
        /*TODO: метод для взлома и замены SendBox на ваш new SendBox
         *
         * ABOUT: задумка в том, чтобы получить сообщение из box методом getMessage
         * и Mac-шифр c помощью getHash();
         *
         * Далее подберите такой ключ и алгоритм, при которых сможете получить из отправленного сообщения отправленный шифр
         * Ключ является байтовым массивом размерностью 4-6 значений типа int от -130 до 130
         * Пример [120, 34, -90, 70, 10]
         *
         * Используйте метод convertByteToSecretKey для получения экземпляра класса SecretKey
         *
         * Для перевода шифров в string (и записи нового hash в новый box) используйте кострукцию Base64.getEncoder().encodeToString(mac.doFinal()),
         * где mac - ВАШ экземпляр класса Mac
         * */

        return new SendBox(box.getMessage(), box.getHash());
        //return new SendBox("заведомо ошибочное", "сообщение");
    }

    public static SecretKey convertByteToSecretKey(byte[] key, String algorithm) {
        return new SecretKeySpec(key, 0, key.length, algorithm);
    }

}

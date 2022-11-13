public class SendBox {
    private final String message;
    private final String hash;

    public SendBox(String message, String hash) {
        this.message = message;
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SendBox box = (SendBox) o;
        return (message + hash).equals(box.message + box.hash);
    }
}

package chapter4.task2;

public enum Design {
    NATURE("Nature"),
    TECH("Tech"),
    BUSINESS("Business"),
    MUSIC("Music"),
    NAUGHTY("Naughty");

    private String text;

    Design(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Design fromString(String text) {
        for (Design b : Design.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
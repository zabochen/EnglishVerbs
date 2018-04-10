package ua.ck.zabochen.englishverbs.builder;

public class ContactBuilder {

    private String name;
    private String phone;

    public ContactBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public ContactBuilder phone(final String phone) {
        this.phone = phone;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}

package ua.ck.zabochen.englishverbs.model.realm;

import io.realm.RealmObject;

public class VerbExample extends RealmObject {

    private String example;

    public VerbExample() {
    }

    public VerbExample(String example) {
        this.example = example;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

}

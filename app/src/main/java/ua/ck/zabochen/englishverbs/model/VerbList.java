package ua.ck.zabochen.englishverbs.model;

import java.util.ArrayList;

import ua.ck.zabochen.englishverbs.model.realm.Verb;

public class VerbList {

    private ArrayList<Verb> verbList = new ArrayList<>();

    public ArrayList<Verb> getVerbList() {
        return verbList;
    }

    public void setVerb(Verb verb) {
        verbList.add(verb);
    }

    public void setVerbList(ArrayList<Verb> verbList) {
        this.verbList.addAll(verbList);
    }

    public void clearVerbList() {
        verbList.clear();
    }

}

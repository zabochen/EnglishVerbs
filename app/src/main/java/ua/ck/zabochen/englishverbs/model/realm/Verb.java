package ua.ck.zabochen.englishverbs.model.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Verb extends RealmObject {

    @PrimaryKey
    private String verb;
    private String verbTranscription;

    private String verbPastSimple;
    private String verbPastSimpleTranscription;

    private String verbPastParticiple;
    private String verbPastParticipleTranscription;

    private String verbTranslation;

    private String verbImage;

    private RealmList<VerbExample> verbExamples;

    public Verb() {
    }

    public Verb(String verb, String verbTranscription, String verbPastSimple, String verbPastSimpleTranscription, String verbPastParticiple, String verbPastParticipleTranscription, String verbTranslation, String verbImage, RealmList<VerbExample> verbExamples) {
        this.verb = verb;
        this.verbTranscription = verbTranscription;
        this.verbPastSimple = verbPastSimple;
        this.verbPastSimpleTranscription = verbPastSimpleTranscription;
        this.verbPastParticiple = verbPastParticiple;
        this.verbPastParticipleTranscription = verbPastParticipleTranscription;
        this.verbTranslation = verbTranslation;
        this.verbImage = verbImage;
        this.verbExamples = verbExamples;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getVerbTranscription() {
        return verbTranscription;
    }

    public void setVerbTranscription(String verbTranscription) {
        this.verbTranscription = verbTranscription;
    }

    public String getVerbPastSimple() {
        return verbPastSimple;
    }

    public void setVerbPastSimple(String verbPastSimple) {
        this.verbPastSimple = verbPastSimple;
    }

    public String getVerbPastSimpleTranscription() {
        return verbPastSimpleTranscription;
    }

    public void setVerbPastSimpleTranscription(String verbPastSimpleTranscription) {
        this.verbPastSimpleTranscription = verbPastSimpleTranscription;
    }

    public String getVerbPastParticiple() {
        return verbPastParticiple;
    }

    public void setVerbPastParticiple(String verbPastParticiple) {
        this.verbPastParticiple = verbPastParticiple;
    }

    public String getVerbPastParticipleTranscription() {
        return verbPastParticipleTranscription;
    }

    public void setVerbPastParticipleTranscription(String verbPastParticipleTranscription) {
        this.verbPastParticipleTranscription = verbPastParticipleTranscription;
    }

    public String getVerbTranslation() {
        return verbTranslation;
    }

    public void setVerbTranslation(String verbTranslation) {
        this.verbTranslation = verbTranslation;
    }

    public String getVerbImage() {
        return verbImage;
    }

    public void setVerbImage(String verbImage) {
        this.verbImage = verbImage;
    }

    public RealmList<VerbExample> getVerbExamples() {
        return verbExamples;
    }

    public void setVerbExamples(RealmList<VerbExample> verbExamples) {
        this.verbExamples = verbExamples;
    }

}

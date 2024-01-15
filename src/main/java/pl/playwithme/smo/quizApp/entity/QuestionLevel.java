package pl.playwithme.smo.quizApp.entity;

public enum QuestionLevel {
    zero(0),
    first(12),
    second(24),
    third(36),
    fourth(48);

    private final int value;
    QuestionLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}

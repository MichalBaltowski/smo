package pl.playwithme.smo.dto;

public enum KnowledgeLevel {
    zero(0),
    first(12),
    second(24),
    third(36),
    fourth(48);

    private final int value;
    KnowledgeLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}

package com.example._50zo.model;

public enum CardEnum {
    HEART_1("as-corazon.png"),
    HEART_2("2-corazon.png"),
    HEART_3("3-corazon.png"),
    HEART_4("4-corazon.png"),
    HEART_5("5-corazon.png"),
    HEART_6("6-corazon.png"),
    HEART_7("7-corazon.png"),
    HEART_8("8-corazon.png"),
    HEART_9("9-corazon.png"),
    HEART_10("10-corazon.png"),
    HEART_J("j-corazon.png"),
    HEART_Q("q-corazon.png"),
    HEART_K("k-corazon.png"),
    SPADE_A("as-picas.png"),
    SPADE_2("2-picas.png"),
    SPADE_3("3-picas.png"),
    SPADE_4("4-picas.png"),
    SPADE_5("5-picas.png"),
    SPADE_6("6-picas.png"),
    SPADE_7("7-picas.png"),
    SPADE_8("8-picas.png"),
    SPADE_9("9-picas.png"),
    SPADE_10("10-picas.png"),
    SPADE_J("j-picas.png"),
    SPADE_Q("q-picas.png"),
    SPADE_K("k-picas.png"),
    DIAMOND_1("as-diamante.png"),
    DIAMOND_2("2-diamante.png"),
    DIAMOND_3("3-diamante.png"),
    DIAMOND_4("4-diamante.png"),
    DIAMOND_5("5-diamante.png"),
    DIAMOND_6("6-diamante.png"),
    DIAMOND_7("7-diamante.png"),
    DIAMOND_8("8-diamante.png"),
    DIAMOND_9("9-diamante.png"),
    DIAMOND_10("10-diamante.png"),
    DIAMOND_J("j-diamante.png"),
    DIAMOND_Q("q-diamante.png"),
    DIAMOND_K("k-diamante.png"),
    CLUB_1("as-treboles.png"),
    CLUB_2("2-trebol.png"),
    CLUB_3("3-trebol.png"),
    CLUB_4("4-trebol.png"),
    CLUB_5("5-trebol.png"),
    CLUB_6("6-trebol.png"),
    CLUB_7("7-trebol.png"),
    CLUB_8("8-trebol.png"),
    CLUB_9("9-trebol.png"),
    CLUB_10("10-trebol.png"),
    CLUB_J("j-trebol.png"),
    CLUB_Q("q-trebol.png"),
    CLUB_K("k-trebol.png"),
    CARD_FACE_DOWN("face_downCard.jpg");


    private final String filePath;
    private static final String PATH = "/com/example/_50zo/";

    /**
     * Constructor for the EISCUnoEnum enum.
     *
     * @param filePath the file path of the image relative to the base directory
     */
    CardEnum(String filePath) {
        this.filePath = PATH + filePath;
    }

    /**
     * Gets the full file path of the image.
     *
     * @return the full file path of the image
     */
    public String getFilePath() {
        return filePath;
    }
}

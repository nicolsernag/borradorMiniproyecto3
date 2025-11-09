package com.example._50zo.model;

public enum CardEnum {
    HEART_1("unoCora.jpg"),
    HEART_2("dosCorazon.jpg"),
    HEART_3("tresCorazon.jpg"),
    HEART_4("cuatroCora.jpg"),
    HEART_5("cincoCorazon.jpg"),
    HEART_6("6_cora.jpg"),
    HEART_7("7_cora.jpg"),
    HEART_8("8_cora.jpg"),
    HEART_9("9_cora.jpg"),
    HEART_10("10_cora.jpg"),
    HEART_J("J_cora.jpg"),
    HEART_Q("Q_cora.jpg"),
    HEART_K("K_cora.jpg"),
    SPADE_A("1_pica.jpg"),
    SPADE_2("2_pica.jpg"),
    SPADE_3("3_pica.jpg"),
    SPADE_4("4_pica.jpg"),
    SPADE_5("5_pica.jpg"),
    SPADE_6("6_pica.jpg"),
    SPADE_7("7_pica.jpg"),
    SPADE_8("8_pica.jpg"),
    SPADE_9("9_pica.jpg"),
    SPADE_10("10_pica.jpg"),
    SPADE_J("J_pica.jpg"),
    SPADE_Q("Q_pica.jpg"),
    SPADE_K("K_pica.jpg"),
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

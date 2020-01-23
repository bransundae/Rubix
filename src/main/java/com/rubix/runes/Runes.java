package com.rubix.runes;

public class Runes {

    //NODES AFFECTED BY ROTATIONS
    public static final String ROTATION_NODES_F[] = {
            "111",
            "211",
            "311",
            "312",
            "313",
            "213",
            "113",
            "112"
    };
    public static final String ROTATION_NODES_B[] = {
            "131",
            "132",
            "133",
            "233",
            "333",
            "332",
            "331",
            "231"
    };
    public static final String ROTATION_NODES_U[] = {
            "311",
            "321",
            "331",
            "332",
            "333",
            "323",
            "313",
            "312"
    };
    public static final String ROTATION_NODES_D[] = {
            "111",
            "112",
            "113",
            "123",
            "133",
            "132",
            "131",
            "121"
    };
    public static final String ROTATION_NODES_L[] = {
            "111",
            "121",
            "131",
            "231",
            "331",
            "321",
            "311",
            "211"
    };
    public static final String ROTATION_NODES_R[] = {
            "113",
            "213",
            "313",
            "323",
            "333",
            "233",
            "133",
            "123"
    };

    //FACES AFFECTED BY ROTATIONS
    public static final String ROTATION_FACES_F[] = {
            "U",
            "R",
            "D",
            "L",
            "U"
    };
    public static final String ROTATION_FACES_B[] = {
            "U",
            "L",
            "D",
            "R",
            "U"
    };
    public static final String ROTATION_FACES_U[] = {
            "F",
            "L",
            "B",
            "R",
            "F"
    };
    public static final String ROTATION_FACES_D[] = {
            "F",
            "R",
            "B",
            "L",
            "F"
    };
    public static final String ROTATION_FACES_L[] = {
            "U",
            "F",
            "D",
            "B",
            "U"
    };
    public static final String ROTATION_FACES_R[] = {
            "U",
            "B",
            "D",
            "F",
            "U"
    };

    public static String getMirror(String rotate) {
        switch (rotate){
            case "F":
                return "B";
            case "B":
                return "F";
            case "U":
                return "D";
            case "D":
                return "U";
            case "L":
                return "R";
            case "R":
                return "L";
        }
        return null;
    }
}

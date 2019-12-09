package parser;

import java.util.*;
import parser.Plane;

public class Cube{

    private  Plane Front;
    private  Plane Back;
    private  Plane Up;
    private  Plane Down;
    private  Plane Left;
    private  Plane Right;
    private  int dimensions;
    private HashMap <String, String[]> sides;

    public Cube(final int dimension) {
        dimensions = dimension;
        Front = new Plane('F', dimensions);
        Back = new Plane('B', dimensions);
        Up = new Plane('U', dimensions);
        Down = new Plane('D', dimensions);
        Left = new Plane('L', dimensions);
        Right = new Plane('R', dimensions);
        sides = new HashMap<String, String[]>();
        sides.put("U", new String[]{"F", "L", "B", "R"});
        sides.put("D", new String[]{"F", "L", "B", "R"});
        sides.put("L", new String[]{"U", "F", "D", "B"});
        sides.put("R", new String[]{"U", "F", "D", "B"});
        sides.put("B", new String[]{"U", "L", "D", "R"});
        sides.put("F", new String[]{"U", "L", "D", "R"});
    }
    

    public int readInstructions(final String instructions) {
        char[] tem = new char[dimensions];
        for (String temp : instructions.split(" ")) {
            
        }
        return (0);
    }

    public void rotation(String face, int direction, int rot){
        char [][] temp = new char [][] {getFaceRotation(sides.get(face)[0], rot), 
            getFaceRotation(sides.get(face)[1], rot), getFaceRotation(sides.get(face)[2], rot), 
            getFaceRotation(sides.get(face)[3], rot)};
        if (direction == 1){
            for (int i = 0; i < sides.get(face).length; i++){
                faceParser(face, rot, temp[i]);
            }
        }else if (direction == -1){
            for (int i = sides.get(face).length - 1; i >= 0 ; i--){
                faceParser(face, rot, temp[i]);
            }
        }
    }

    public void faceParser(String face, int rotation, char [] row){
        switch(face){
            case "F":
                Front.setRotate(rotation, row);
                break;
            case "B":
                Back.setRotate(rotation, row);
                break;
            case "L":
                Left.setRotate(rotation, row);
                break;
            case "R":
                Right.setRotate(rotation, row);
                break;
            case "U":
                Up.setRotate(rotation, row);
                break;
            case "D":
                Down.setRotate(rotation, row);
                break;
        }
    }

    public char[] getFaceRotation(String face, int rotation){
        switch(face){
            case "F":
                return Front.getRotate(rotation);
            case "B":
                return Back.getRotate(rotation);
            case "L":
                return Left.getRotate(rotation);
            case "R":
                return Right.getRotate(rotation);
            case "U":
                return Up.getRotate(rotation);
            case "D":
                return Down.getRotate(rotation);
            default:
                return null;
        }
    }
}
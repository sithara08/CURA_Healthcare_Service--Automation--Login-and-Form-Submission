package utils;

public class PathUtils {
    public static String getRelativePath(String fileName, String timestamp) {
        return "..\\screenshots\\" + fileName + " " + timestamp + ".png";
    }
}

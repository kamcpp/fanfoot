package ir.fanfoot.util.graphics;

import java.io.IOException;

public interface ImageResizer {

    void resize(String filePath, String resultFilePath, int width, int height) throws IOException;

    void resizeWithWidthFixed(String filePath, String resultFilePath, int width) throws IOException;

    void resizeWithHeightFixed(String filePath, String resultFilePath, int height) throws IOException;
}

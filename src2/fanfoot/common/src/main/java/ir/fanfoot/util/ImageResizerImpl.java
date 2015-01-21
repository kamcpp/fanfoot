package ir.fanfoot.util;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageResizerImpl implements ImageResizer {

    @Override
    public void resizeWithWidthFixed(String filePath, String resultFilePath, int width) throws IOException {
        BufferedImage original = ImageIO.read(new FileInputStream(filePath));
        if (original != null) {
            double ratio = (double) original.getWidth() / original.getHeight();
            int height = (int) (width / ratio) + 1;
            internalResize(original, filePath, resultFilePath, width, height);
        }
    }

    @Override
    public void resizeWithHeightFixed(String filePath, String resultFilePath, int height) throws IOException {
        BufferedImage original = ImageIO.read(new FileInputStream(filePath));
        if (original != null) {
            double ratio = (double) original.getWidth() / original.getHeight();
            int width = (int) (ratio * height) + 1;
            internalResize(original, filePath, resultFilePath, width, height);
        }
    }

    @Override
    public void resize(String filePath, String resultFilePath, int width, int height) throws IOException {
        BufferedImage original = ImageIO.read(new FileInputStream(filePath));
        if (original != null) {
            internalResize(original, filePath, resultFilePath, width, height);
        }
    }

    private void internalResize(BufferedImage original, String filePath, String resultFilePath, int width, int height) throws IOException {
        BufferedImage result = new BufferedImage(width, height, original.getType());
        AffineTransform affineTransform =
                AffineTransform.getScaleInstance((double) width / original.getWidth(), (double) height / original.getHeight());
        Graphics2D graphics = result.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR
        );
        graphics.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawRenderedImage(original, affineTransform);
        ImageIO.write(result, FilenameUtils.getExtension(filePath), new FileOutputStream(resultFilePath));
        graphics.dispose();
    }
}

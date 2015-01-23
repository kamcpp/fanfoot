package ir.fanfoot.util.crypto;

import java.io.File;

public interface HashProvider {
    String hashAsString(String text);

    String hashAsString(File file);
}

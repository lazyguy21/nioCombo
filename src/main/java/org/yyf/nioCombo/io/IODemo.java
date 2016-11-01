package org.yyf.nioCombo.io;

import com.google.common.io.Files;
import com.google.common.io.Resources;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by tobi on 16-10-27.
 */
public class IODemo {
    @Test
    public void file() throws IOException {
        URL resource = Resources.getResource("");
        String path = resource.getPath();
        String s = path + "haha.txt";
        System.out.println(s);
        File file = new File(s);
        file.createNewFile();
        file.mkdir();
    }

    @Test
    public void name() throws Exception {
        String separator = File.separator;
        String pathSeparator = File.pathSeparator;
        System.out.println(separator);
        System.out.println(pathSeparator);

    }
}

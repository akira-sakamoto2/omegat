/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool 
          with fuzzy matching, translation memory, keyword search, 
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2008 Alex Buloichik
               2009 Didier Briel
               2012 Alex Buloichik, Didier Briel
               Home page: http://www.omegat.org/
               Support center: http://groups.yahoo.com/group/OmegaT/

 This file is part of OmegaT.

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **************************************************************************/

package org.omegat.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.omegat.gui.help.HelpFrame;

/**
 * Files processing utilities.
 * 
 * @author Alex Buloichik (alex73mail@gmail.com)
 * @author Didier Briel
 */
public class FileUtil {
    private static final int MAX_BACKUPS = 11;
    public static String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * Removes old backups so that only 10 last are there.
     */
    public static void removeOldBackups(final File originalFile) {
        try {
            File[] bakFiles = originalFile.getParentFile().listFiles(new FileFilter() {
                public boolean accept(File f) {
                    return !f.isDirectory() && f.getName().startsWith(originalFile.getName())
                            && f.getName().endsWith(".bak");
                }
            });

            if (bakFiles != null && bakFiles.length > MAX_BACKUPS) {
                Arrays.sort(bakFiles, new Comparator<File>() {
                    public int compare(File f1, File f2) {
                        return f2.getName().compareTo(f1.getName());
                    }
                });
                for (int i = MAX_BACKUPS; i < bakFiles.length; i++) {
                    bakFiles[i].delete();
                }
            }
        } catch (Exception e) {
            // we don't care
        }
    }

    /**
     * Create file backup with datetime suffix.
     */
    public static void backupFile(File f) throws IOException {
        long fileMillis = f.lastModified();
        String str = new SimpleDateFormat("yyyyMMddHHmm").format(new Date(fileMillis));
        LFileCopy.copy(f, new File(f.getPath() + "." + str + ".bak"));
    }

    /**
     * Writes a text into a UTF-8 text file in the script directory.
     * 
     * @param textToWrite
     *            The text to write in the file
     * @param fileName
     *            The file name without path
     */
    public static File writeScriptFile(String textToWrite, String fileName) {

        File outFile = new File(StaticUtils.getScriptDir(), fileName);
        File outFileTemp = new File(StaticUtils.getScriptDir(), fileName + ".temp");
        outFile.delete();
        BufferedWriter bw = null;
        try {
            textToWrite = textToWrite.replaceAll("\n", System.getProperty("line.separator"));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFileTemp), OConsts.UTF8));
            bw.write(textToWrite);
        } catch (Exception ex) {
            // Eat exception silently
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        outFileTemp.renameTo(outFile);
        return outFile;
    }

    public static String readScriptFile(File file) {
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream(file), OConsts.UTF8));

            try {
                StringWriter out = new StringWriter();
                LFileCopy.copy(rd, out);
                return out.toString().replace(System.getProperty("line.separator"), "\n");
            } finally {
                rd.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Read file as UTF-8 text.
     */
    public static String readTextFile(File file) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream(file), OConsts.UTF8));

        try {
            StringWriter out = new StringWriter();
            LFileCopy.copy(rd, out);
            return out.toString();
        } finally {
            rd.close();
        }
    }

    /**
     * Write text in file using UTF-8.
     */
    public static void writeTextFile(File file, String text) throws IOException {
        Writer wr = new OutputStreamWriter(new FileOutputStream(file), OConsts.UTF8);
        try {
            wr.write(text);
        } finally {
            wr.close();
        }
    }

    /**
     * Find files in subdirectories.
     * 
     * @param dir
     *            directory to start find
     * @param filter
     *            filter for found files
     * @return list of filtered found files
     */
    public static List<File> findFiles(final File dir, final FileFilter filter) {
        final List<File> result = new ArrayList<File>();
        findFiles(dir, filter, result);
        return result;
    }

    /**
     * Internal find method, which calls himself recursively.
     * 
     * @param dir
     *            directory to start find
     * @param filter
     *            filter for found files
     * @param result
     *            list of filtered found files
     */
    private static void findFiles(final File dir, final FileFilter filter, final List<File> result) {
        File[] list = dir.listFiles();
        if (list != null) {
            for (File f : list) {
                if (f.isDirectory()) {
                    findFiles(f, filter, result);
                } else {
                    if (filter.accept(f)) {
                        result.add(f);
                    }
                }
            }
        }
    }

    /**
     * Compute relative path of file.
     * 
     * @param rootDir
     *            root directory
     * @param filePath
     *            file path
     * @return
     */
    public static String computeRelativePath(File rootDir, File file) throws IOException {
        String rootAbs = rootDir.getAbsolutePath().replace('\\', '/') + '/';
        String fileAbs = file.getAbsolutePath().replace('\\', '/');

        switch (Platform.getOsType()) {
        case WIN32:
        case WIN64:
            if (!fileAbs.toUpperCase().startsWith(rootAbs.toUpperCase())) {
                throw new IOException("File '" + file + "' is not under dir '" + rootDir + "'");
            }
            break;
        default:
            if (!fileAbs.startsWith(rootAbs)) {
                throw new IOException("File '" + file + "' is not under dir '" + rootDir + "'");
            }
            break;
        }
        return fileAbs.substring(rootAbs.length());
    }
    
    /**
     * Load a text file from the root of help.
     * @param The name of the text file
     * @return The content of the text file
     */
    public static String loadTextFileFromDoc(String textFile) {

        // Get the license
        URL url = HelpFrame.getHelpFileURL(null, textFile);
        if (url == null) {
            return HelpFrame.errorHaiku();
        }

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(url.openStream(), OConsts.UTF8));
            try {
                StringWriter out = new StringWriter();
                LFileCopy.copy(rd, out);
                return out.toString();
            } finally {
                rd.close();
            }
        } catch (IOException ex) {
            return HelpFrame.errorHaiku();
        }

    }        
}

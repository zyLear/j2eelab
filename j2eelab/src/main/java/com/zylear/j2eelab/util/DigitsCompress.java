package com.zylear.j2eelab.util;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezongyu on 2019/4/28.
 */
public class DigitsCompress {

    public static void main(String[] args) throws Exception {
        List<String> strings = FileUtils.readLines(new File("D:\\home\\1w_hash.txt"), CharEncoding.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string).append(",");
        }
        String string = stringBuilder.toString();
        System.out.println(string);
        FileUtils.writeStringToFile(new File("D:\\home\\1w_hash_line.txt"), string, CharEncoding.UTF_8);
        FileUtils.writeStringToFile(new File("D:\\home\\1w_hash_md5_base64_line.txt"), new Base64().encodeAsString(string.getBytes()), CharEncoding.UTF_8);
        compressFiles2Zip(new File[]{new File("D:\\home\\1w_hash_line.txt")}, "D:\\home\\1w_hash_zip.zip");

    }


    public static void compressFiles2Zip(File[] files, String zipFilePath) {
        if (files != null && files.length > 0) {
            ZipArchiveOutputStream zaos = null;
            File f = new File(zipFilePath);
            if (f.isDirectory()) {
                System.out.println("isDirectory");
                return;
            }
            if (f.exists()) {
                f.delete();
            }
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
//                File zipFile = new File(zipFilePath);
                byteArrayOutputStream = new ByteArrayOutputStream();
//                zaos = new ZipArchiveOutputStream(zipFile);
                zaos = new ZipArchiveOutputStream(byteArrayOutputStream);
                zaos.setUseZip64(Zip64Mode.AsNeeded);
                int index = 0;
                for (File file : files) {
                    if (file != null) {
                        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(
                                file, new File(file.getParent()).getName() + File.separator + file.getName());
                        zaos.putArchiveEntry(zipArchiveEntry);
                        InputStream is = null;
                        try {
                            is = new BufferedInputStream(new FileInputStream(
                                    file));
                            byte[] buffer = new byte[1024 * 5];
                            int len = -1;
                            while ((len = is.read(buffer)) != -1) {
                                // 把缓冲区的字节写入到ZipArchiveEntry
                                zaos.write(buffer, 0, len);
                            }
                            // Writes all necessary data for this entry.
                            zaos.closeArchiveEntry();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        } finally {
                            if (is != null)
                                is.close();
                        }

                    }

                }

                zaos.finish();

            } catch (Exception e) {

                throw new RuntimeException(e);

            } finally {


                try {
                    if (byteArrayOutputStream != null) {
                        String string = new Base64().encodeAsString(byteArrayOutputStream.toByteArray());
                        FileUtils.writeStringToFile(new File("D:\\home\\1w_hash_zip_string.txt"), string, CharEncoding.UTF_8);
                        byteArrayOutputStream.close();
                    }
                    if (zaos != null) {

                        zaos.close();

                    }

                } catch (IOException e) {

                    throw new RuntimeException(e);

                }

            }

        }

    }


    public static int BUFFER_SIZE = 2048;
    public static List<String> unZip(File zipfile, String destDir)
            throws Exception {
        if (StringUtils.isBlank(destDir)) {
            destDir = zipfile.getParent();
        }
        destDir = destDir.endsWith(File.separator) ? destDir : destDir
                + File.separator;
        ZipArchiveInputStream is = null;
        List<String> fileNames = new ArrayList<>();

        try {
            is = new ZipArchiveInputStream(new BufferedInputStream(
                    new FileInputStream(zipfile), BUFFER_SIZE));
            ZipArchiveEntry entry = null;
            while ((entry = is.getNextZipEntry()) != null) {
                fileNames.add(entry.getName());
                if (entry.isDirectory()) {
                    File directory = new File(destDir, entry.getName());
                    directory.mkdirs();
                } else {
                    OutputStream os = null;
                    try {
                        os = new BufferedOutputStream(new FileOutputStream(
                                new File(destDir, entry.getName())),
                                BUFFER_SIZE);
                        IOUtils.copy(is, os);
                    } finally {
                        IOUtils.closeQuietly(os);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            IOUtils.closeQuietly(is);
        }

        return fileNames;
    }

    public static List<String> unZip(String zipfile, String destDir)
            throws Exception {
        File zipFile = new File(zipfile);
        return unZip(zipFile, destDir);
    }

}

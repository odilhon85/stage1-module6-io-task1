package com.epam.mjc.io;

import java.io.*;

public class FileReader {

    private String name;
    private Integer age;
    private String email;
    private Long phone;


    public Profile getDataFromFile(File file) {
        char[] dataBuffer = readFile(file);
        findValues(dataBuffer);
        return new Profile(name, age, email, phone);
    }

    public char[] readFile(File file){
        char[] dataBuffer = null;
        try (InputStream input = new FileInputStream(file);
             InputStreamReader inputReader = new InputStreamReader(input);) {
            int fileSize = input.available();
            dataBuffer = new char[fileSize];
            inputReader.read(dataBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataBuffer;
    }

    public void findValues(char[] dataBuffer){
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < dataBuffer.length; i++) {
            key.append(dataBuffer[i]);
            if (dataBuffer[i] == '\n' || dataBuffer[i] == dataBuffer.length - 1) {
                int spaceIndex = key.indexOf(" ");
                String keyName = key.substring(0, spaceIndex + 1);
                String keyValue = key.substring(spaceIndex);
                setValues(keyName, keyValue);
                key = new StringBuilder();
            }

        }
    }

    public void setValues(String keyName, String keyValue){
        if (keyName.contains("Name")) {
            name = keyValue.trim();
        }else if (keyName.contains("Age")) {
            age = Integer.parseInt(keyValue.trim());
        }else if (keyName.contains("Email")) {
            email = keyValue.trim();
        }else if (keyName.contains("Phone")) {
            phone = Long.parseLong(keyValue.trim());
        }
    }
}

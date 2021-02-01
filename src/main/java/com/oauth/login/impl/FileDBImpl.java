package com.oauth.login.impl;

import com.oauth.login.entity.UserEntity;
import com.oauth.login.repository.IConnectionRepo;
import com.oauth.login.util.PasswordUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class FileDBImpl implements IConnectionRepo {
    private static final String fileName = "UsersData.txt";
    private static final List<UserEntity> usersList;

    static {
        usersList = new ArrayList<>();
        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public FileDBImpl() {
    }

    public static void readData() throws IOException {
        try {
            File file = new File(IConnectionRepo.class.getResource("/dataupload/" + fileName).getFile());
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] userData = data.trim().split(",");
                String email = userData[0];
                String plainTxtPassword = userData[1];
                createUser(email, plainTxtPassword);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
    }

    public Optional<UserEntity> getUserByEmail(String email) {
        return usersList.stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

    private static void createUser(String email, String plainTxtPassword) {
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setEmail(email);
        user.setPassword(PasswordUtils.generateBCrypt(plainTxtPassword));
        usersList.add(user);
    }

}

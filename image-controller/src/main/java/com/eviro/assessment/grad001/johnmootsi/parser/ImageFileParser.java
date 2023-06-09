package com.eviro.assessment.grad001.johnmootsi.parser;

import com.eviro.assessment.grad001.johnmootsi.model.AccountProfile;
import com.eviro.assessment.grad001.johnmootsi.service.AccountProfileService;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;


public class ImageFileParser implements FileParser {
    // Other methods

    @Override
    public void parseCSV(File csvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String name = values[0];
                String surname = values[1];
                String imageFormat = values[2];
                String imageData = values[3];

                File imageFile = convertCSVDataToImage(imageData);
                URI imageUri = createImageLink(imageFile);

                AccountProfile accountProfile = new AccountProfile();
                accountProfile.setAccountHolderName(name);
                accountProfile.setAccountHolderSurname(surname);
                accountProfile.setHttpImageLink(imageUri.toString());

                AccountProfileService accountProfileService = new AccountProfileService();
                accountProfileService.saveAccountProfile(accountProfile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @Override
    public File convertCSVDataToImage(String base64ImageData) {
        // Decode the base64 image data
        byte[] decodedImg = Base64.getDecoder().decode(base64ImageData.getBytes(StandardCharsets.UTF_8));

        // Save the decoded image data to a file
        File outputFile = new File("path/to/output/image.png");
        try {
            Files.write(outputFile.toPath(), decodedImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputFile;
    }

    @Override
    public URI createImageLink(File fileImage) {
        String fileName = fileImage.getName();
        return URI.create("/v1/api/image/" + fileName);
    }
}


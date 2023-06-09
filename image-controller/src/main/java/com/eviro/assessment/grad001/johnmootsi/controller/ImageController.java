package com.eviro.assessment.grad001.johnmootsi.controller;

import com.eviro.assessment.grad001.johnmootsi.service.AccountProfileService;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api/image")
public class ImageController {

    @GetMapping("/test")
    public String hello() {
        return "Testing Hello";
    }

    @GetMapping(value = "/{name}/{surname}/{\\w\\.\\w")
    public FileSystemResource gethttpImageLink(@PathVariable String name,@PathVariable String surname) {
        // Retrieve the image file path based on the name and surname
        AccountProfileService accountProfileService = new AccountProfileService();
        String imagePath = accountProfileService.getImageFilePathFromDatabase(name, surname);

        // Return the image file as a FileSystemResource
        return new FileSystemResource(imagePath);
    }
}

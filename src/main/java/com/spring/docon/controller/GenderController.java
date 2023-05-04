package com.spring.docon.controller;

import com.spring.docon.entity.GenderEntity;
import com.spring.docon.mapper.GenderMapper;
import com.spring.docon.repository.GenderRepository;
import com.spring.docon.service.GenderService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
public class GenderController {


    private final GenderService genderService;

    @Autowired
    public GenderController( GenderService genderService){
        this.genderService=genderService;
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "500", description = "System error")
    @GetMapping(path = "/genders")
    public ResponseEntity<List<GenderEntity>> getGender(){
        List<GenderEntity> gender=genderService.getGender();
        return new ResponseEntity<>(gender, HttpStatus.OK);
    }
}


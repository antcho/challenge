package com.anthony.backend.controller;

import com.anthony.backend.BusinessException;
import com.anthony.backend.service.CompanyService;
import com.anthony.backend.service.DepositService;
import com.anthony.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ActionController {
    private final DepositService depositService;
    private final UserService userService;
    private final CompanyService companyService;

    @PostMapping("/action/distributeGift")
    public ResponseEntity distributeGift(@RequestParam("companyId") int companyId,
                                           @RequestParam("userId") int userId,
                                           @RequestParam("value") float value) {
        try {
            depositService.distributeGift(companyService.getCompanyById(companyId), userService.getUserById(userId), value);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }

    @PostMapping("/action/distributeMeal")
    public ResponseEntity distributeMeal(@RequestParam("companyId") int companyId,
                                         @RequestParam("userId") int userId,
                                         @RequestParam("value") float value) {

        try {
            depositService.distributeMeal(companyService.getCompanyById(companyId), userService.getUserById(userId), value);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}

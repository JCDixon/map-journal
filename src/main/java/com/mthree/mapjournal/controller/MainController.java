package com.mthree.mapjournal.controller;

import com.mthree.mapjournal.dao.LocationRepository;
import com.mthree.mapjournal.dao.PinRepository;
import com.mthree.mapjournal.dao.TagRepository;
import com.mthree.mapjournal.dao.TripRepository;
import com.mthree.mapjournal.dto.Location;
import com.mthree.mapjournal.dto.Pin;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author john
 */
@Controller

public class MainController {

    Set<ConstraintViolation<Pin>> pinViolations = new HashSet<>();
    Set<ConstraintViolation<Location>> locViolations = new HashSet<>();

    @Autowired
    PinRepository pinRepo;

    @Autowired
    LocationRepository locRepo;

    @Autowired
    TagRepository tagRepo;

    @Autowired
    TripRepository tripRepo;

    @GetMapping("/MapJournal")
    public String MapJournal(Model model) {
        model.addAttribute("allpins", pinRepo.findAll());
        return "MapJournal";
    }
    
    @GetMapping("/allPins")
    public ResponseEntity<List<Pin>> allPins() {
        return ResponseEntity.ok(pinRepo.findAll());
    }

    @PostMapping("addPin")
    public String addPin(HttpServletRequest request) {
        Location pinLocation = new Location();
        BigDecimal latitude = new BigDecimal(request.getParameter("latitude"));
        pinLocation.setLatitude(latitude);
        BigDecimal longitude = new BigDecimal(request.getParameter("longitude"));
        pinLocation.setLongitude(longitude);

        Pin newPin = new Pin();
        newPin.setName(request.getParameter("name"));
        newPin.setMoment(request.getParameter("moment"));
        newPin.setDescription(request.getParameter("description"));
        newPin.setComments(request.getParameter("comments"));

        Validator pinValidator = Validation.buildDefaultValidatorFactory().getValidator();
        Validator locValidator = Validation.buildDefaultValidatorFactory().getValidator();
        pinViolations = pinValidator.validate(newPin);
        locViolations = locValidator.validate(pinLocation);
        if (!pinViolations.isEmpty() || !locViolations.isEmpty()) {
            return "redirect:/MapJournal";
        }

        pinLocation = locRepo.save(pinLocation);
        newPin.setLocation(pinLocation);
        pinRepo.save(newPin);
        return "redirect:/MapJournal";
    }

    @PostMapping("editPin")
    public String editPin(HttpServletRequest request) {
        Pin pin = pinRepo.getById(Integer.parseInt(request.getParameter("ExistingPinEditBox_id")));
        pin.setName(request.getParameter("ExistingPinEditBox_pin_name"));
        pin.setMoment(request.getParameter("ExistingPinEditBox_pin_date"));
        pin.setDescription(request.getParameter("ExistingPinEditBox_description"));
        pin.setComments(request.getParameter("ExistingPinEditBox_pin_comments"));

        Location updateLoc = locRepo.getById(pin.getLocation().getLocation_id());
        BigDecimal latitude = new BigDecimal(request.getParameter("ExistingPinEditBox_latitude"));
        updateLoc.setLatitude(latitude);
        BigDecimal longitude = new BigDecimal(request.getParameter("ExistingPinEditBox_longitude"));
        updateLoc.setLongitude(longitude);
        Validator pinValidator = Validation.buildDefaultValidatorFactory().getValidator();
        Validator locValidator = Validation.buildDefaultValidatorFactory().getValidator();
        pinViolations = pinValidator.validate(pin);
        locViolations = locValidator.validate(updateLoc);

        if (!pinViolations.isEmpty() || !locViolations.isEmpty()) {
            return "redirect:/MapJournal";
        }
        updateLoc = locRepo.save(updateLoc);
        pin.setLocation(updateLoc);
        pinRepo.save(pin);
        return "redirect:/MapJournal";
    }

    @GetMapping("deletePin")
    public String deletePin(HttpServletRequest request) {
        pinRepo.deleteById(Integer.parseInt(request.getParameter("DeletePinConfirmationBox_id")));
        return "redirect:/MapJournal";
    }
}

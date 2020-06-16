package com.eg.yafi.controller;

import com.eg.yafi.dto.CreateThread;
import com.eg.yafi.dto.CreateTopic;
import com.eg.yafi.dto.CreateUser;
import com.eg.yafi.service.MainService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/")
    public String greeting() {
        return "Welcome to YAFI";
    }

    @PostMapping("/topic")
    public void createTopic(@RequestBody CreateTopic createTopic){
        mainService.createTopic(createTopic);
    }

    @PostMapping("/user")
    public void createTopic(@RequestBody CreateUser createUser){
        mainService.createUser(createUser);
    }

    @PostMapping("/thread")
    public void createThread(@RequestBody CreateThread createThread){
        mainService.createThread(createThread);
    }

//    @PostMapping("/company")
//    public Long createCompany(@RequestBody CreateCompany createCompany){
//        Long result = mainService.createCompany(createCompany);
//
//        return result;
//    }
//
//    @GetMapping("/companies")
//    public ReadCompanyList readCompanyList(@RequestParam(required = false) String companyName){
//        ReadCompanyList result = null;
//
//        if(companyName != null && !companyName.isEmpty())
//            result = mainService.readCompanies(companyName);
//        else
//            result = mainService.readCompanies();
//
//        return result;
//    }
//
//    @PostMapping("/airport")
//    public Long createAirport(@RequestBody CreateAirport createAirport) {
//        Long result = mainService.createAirport(createAirport);
//
//        return result;
//    }
//
//    @GetMapping("/airports")
//    public ReadAirportList readAirportList(@RequestParam(required = false) Long cityId){
//        ReadAirportList result = null;
//
//        if (cityId != null)
//            result = mainService.readAirports(cityId);
//        else
//            result = mainService.readAirports();
//
//        return result;
//    }
//
//    @PostMapping("/route")
//    public Long createRoute(@RequestBody CreateRoute createRoute) {
//        Long result = mainService.createRoute(createRoute);
//
//        return result;
//    }
//
//    @GetMapping("/routes")
//    public ReadRouteList readRouteList(@RequestParam(required = false)  Long fromId,
//                                       @RequestParam(required = false) Long toId){
//        ReadRouteList result = null;
//
//        result = mainService.readRoutes(fromId, toId);
//
//        return result;
//    }
//
//    @PostMapping("/companyflight")
//    public Long createCompanyFlight(@RequestBody CreateCompanyFlight createCompanyFlight) {
//        Long result = mainService.createCompanyFlight(createCompanyFlight);
//
//        return result;
//    }
//
//    @GetMapping("/companyflights")
//    public ReadCompanyFlightList readCompanyFlightList(@RequestParam(required = false)  Long companyId,
//                                               @RequestParam(required = false) Long routeId,
//                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime departure,
//                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime arrival){
//        ReadCompanyFlightList result = null;
//
//        result = mainService.readCompanyFlights(companyId, routeId, departure, arrival);
//
//        return result;
//    }
//
//    @PostMapping("/ticket")
//    public Long buyTicket(@RequestBody BuyTicket buyTicket) {
//        Long result = mainService.buyTicket(buyTicket);
//
//        return result;
//    }
//
//    @DeleteMapping("/ticket/{ticketId}")
//    public Long deleteTicket(@PathVariable long ticketId){
//        Long result = mainService.deleteTicket(ticketId);
//
//        return result;
//    }
//
//    @GetMapping("/ticket/{ticketId}")
//    public ReadTicket readTicket(@PathVariable long ticketId){
//        ReadTicket readTicket = mainService.readTicket(ticketId);
//
//        return readTicket;
//    }

}

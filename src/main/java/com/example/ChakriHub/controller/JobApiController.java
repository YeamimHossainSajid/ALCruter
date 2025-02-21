//package com.example.ChakriHub.controller;
//
//import com.example.ChakriHub.service.impl.JobsApiService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/jobs")
//public class JobApiController {
//
//    private final JobsApiService jobApiService;
//
//    public JobApiController(JobsApiService jobApiService) {
//        this.jobApiService = jobApiService;
//    }
//
//    @GetMapping("/fetch")
//    public String getJobs() {
//        return jobApiService.fetchJobs();
//    }
//}
//

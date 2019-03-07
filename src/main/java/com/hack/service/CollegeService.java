package com.hack.service;

import com.hack.entity.College;

import java.util.List;

public interface CollegeService {
    List<College>findAll()throws Exception;
}

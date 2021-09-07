package com.gbep.masterservice.service;

import com.gbep.masterservice.entity.Microservice;
import com.gbep.masterservice.entity.Status;
import com.gbep.masterservice.entity.UserConfig;
import com.gbep.masterservice.repository.UserConfigRepo;
import com.netflix.discovery.converters.Auto;
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class MasterService {
    @Autowired
    private UserConfigRepo repo;

    @Autowired
    private RestTemplate restTemplate;


    public boolean createNewGame(String name, String user_id) {
        if (repo.findById(user_id).isPresent()) {
            UserConfig user = repo.findById(user_id).get();

            user.setService(Microservice.valueOf(name));
            user.setStatus(Status.WAITING_GAME);

            List<String> questions = restTemplate.getForObject("http://" + name + '/')

        } else {

        }

        return true;
    }

    public List<String> chooseGame(String ms_name) {
        List<String> datasets = restTemplate.getForObject("http://" + ms_name + '/')
    }
}

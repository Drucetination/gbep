package com.gbep.masterservice.VO;

import lombok.Data;

import java.util.List;

@Data
public class SentenceDataset {
    private String id;
    private String name;
    private List<Pair> data;
}
package com.eg.yafi.service;

import com.eg.yafi.projection.ReadDailyTopic;
import com.eg.yafi.projection.ReadPopularTopics;
import com.eg.yafi.projection.ReadTopic;
import com.eg.yafi.repo.ThreadRepo;
import com.eg.yafi.repo.TopicRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class TopicQueryService {
    private final TopicRepo topicRepo;
    private final ThreadRepo threadRepo;

    public TopicQueryService(TopicRepo topicRepo, ThreadRepo threadRepo) {
        this.topicRepo = topicRepo;
        this.threadRepo = threadRepo;
    }

    public Page<ReadTopic> searchTopicByName(String topicName, Pageable pageable) {
        Page<ReadTopic> pageReadTopic = topicRepo.findTopicByNameRO(topicName, pageable);

        return pageReadTopic;
    }

    public ReadPopularTopics readPopularTopics() {
        List<Object[]> oList = topicRepo.findPopularTopicsRO();

        List<ReadTopic> readTopics = new ArrayList<>();

        for(Object[] o : oList){
            ReadTopic rt = new ReadTopic(((BigInteger)o[0]).longValue(), (String)o[1], (String)o[2]);
            readTopics.add(rt);
        }

        return new ReadPopularTopics(readTopics);
    }

    public ReadTopic readTopic(long topicId) {
        ReadTopic rt = topicRepo.findTopicByIdRO(topicId);

        return rt;
    }

    public  List<ReadDailyTopic> getDailyTopics() {
        LocalDateTime since = LocalDateTime.now().minusHours(24);
        Pageable pageable = PageRequest.of(0, 10);
        List<ReadDailyTopic> readDailyTopicList = threadRepo.readDailyTopicList(since, pageable);

        return readDailyTopicList;
    }
}

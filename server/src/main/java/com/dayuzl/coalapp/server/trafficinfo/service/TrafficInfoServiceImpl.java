package com.dayuzl.coalapp.server.trafficinfo.service;

import com.dayuzl.coalapp.server.trafficinfo.domain.TrafficInfo;
import com.dayuzl.coalapp.server.trafficinfo.repository.TrafficInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TrafficInfoServiceImpl implements TrafficInfoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TrafficInfoRepository trafficInfoRepository;

    @Override
    @CacheEvict(value="trafficInfoCache",allEntries=true)
    public void save(TrafficInfo trafficInfo) {
        trafficInfoRepository.save(trafficInfo);
    }

    @Override
    @Cacheable(value = "trafficInfoCache", key="#traffic",unless="!(#result!=null)")
    public Page<TrafficInfo> findPage(TrafficInfo traffic){

        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageRequest = new PageRequest(traffic.getPageNumber(), traffic.getPageSize(), sort);

        Page<TrafficInfo> trafficInfos = trafficInfoRepository.findAll(pageRequest);

        return trafficInfos;
    }

    @CacheEvict(value="trafficInfoCache",allEntries=true)
    @Scheduled(fixedDelay = 2*60*1000)
    public void clearCache(){
        logger.info("now clean traffic info list cache");
    }
}

package com.dayuzl.coalapp.server.dailynews.controller;

import com.dayuzl.coalapp.server.dailynews.domain.DailyNews;
import com.dayuzl.coalapp.server.dailynews.service.DailyNewsService;
import com.dayuzl.coalapp.server.framework.domain.PageParam;
import com.dayuzl.coalapp.server.framework.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 信息部 controller
 */

@RestController
@RequestMapping("/app/news")
public class DailyNewsController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DailyNewsService dailyNewsService;

    @RequestMapping("/addDailyNews")
    public void saveDailyNews(@RequestBody DailyNews dailyNews){

        logger.info("saveDailyNews : request param (dailyNews: " + dailyNews +" )");

        dailyNewsService.save(dailyNews);
    }

    @RequestMapping("/getDailyNewsList")
    public String getDailyNewsList(@RequestBody PageParam page){

        logger.info("request DailyNews");

        Page<DailyNews> result = dailyNewsService.findPage(page);

        return ResponseUtils.toJSONString(result);
    }
}

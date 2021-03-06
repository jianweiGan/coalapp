package com.dayuzl.coalapp.spider.example.home;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
import com.dayuzl.coalapp.spider.example.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bin.yao on 2017/4/20.
 */
@Service("homePipeline")
public class HomePipeline implements Pipeline<Home>{

    @Autowired
    private CommonDao commonDao;

    @Override
    public void process(Home home) {

        List<BookNode> bookNodes = home.getBookNode();
        for(BookNode bookNode : bookNodes) {
            String name = bookNode.getName();
            String href = bookNode.getHref();
            commonDao.saveBook(name,href);
            HttpRequest currRequest = home.getRequest();
            SchedulerContext.into(currRequest.subRequest(href));
        }
    }
}

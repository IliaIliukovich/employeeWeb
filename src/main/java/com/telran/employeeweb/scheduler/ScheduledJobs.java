package com.telran.employeeweb.scheduler;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledJobs {

    @Scheduled(fixedRate = 10000, timeUnit = TimeUnit.MILLISECONDS)
    public void scheduledTaskOne(){
        System.out.println("Doing task at fixedRate ... " + new Date());
    }


    @Scheduled(cron = "*/5 * * * * *")
    public void scheduledTaskTwo(){
        System.out.println("Doing task with cron ... " + new Date());
    }

    @Scheduled(cron = "*/20 * * * * *")
    public void getRss(){
        try {
            URL feedSource = new URL("https://mountainsview.site/category/gornye-pohody/feed/");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            System.out.println("Title: " + feed.getTitle());
            System.out.println("Description: " + feed.getDescription());
            List<SyndEntry> entries = feed.getEntries();
            entries.forEach(syndEntry -> {
                System.out.println(syndEntry.getTitle());
//                System.out.println(syndEntry);
            });
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
    }

}

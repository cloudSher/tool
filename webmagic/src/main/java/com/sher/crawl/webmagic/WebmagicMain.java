package com.sher.crawl.webmagic;

import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cloudsher on 2016/6/2.
 */
public class WebmagicMain {



    public static void main(String args[]){
        Spider.create(new GithubRepo()).addUrl("http://www.meizitu.com/")
                .addPipeline(new ImagePiple("D:\\webmagic\\")).thread(1).run();
    }


    static class GithubRepo implements PageProcessor{

        private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

        public void process(Page page) {
            page.addTargetRequests(page.getHtml().links().regex("(http://meizitu.com/\\w+/\\w+)").all());
//            page.putField("author", page.getUrl().regex("https://meizitu\\.com/(\\w+)/.*").toString());
//            page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//            if (page.getResultItems().get("name")==null){
//                //skip this page
//                page.setSkip(true);
//            }
//            page.addTargetRequests(page.getHtml().xpath("//img/@src").links().all());
            page.putField("images", page.getHtml().xpath("//img/@src").all());
        }

        public Site getSite() {
            return site;
        }


    }

    static class ImagePiple  extends FilePersistentBase implements Pipeline{


        ImagePiple(String path){
            setPath(path);
        }


        @Override
        public void process(ResultItems resultItems, Task task) {
            System.out.println(task.getUUID()+"============"+resultItems.get("images"));
            String path = this.path + PATH_SEPERATOR + "mm" + PATH_SEPERATOR;
            if(resultItems!=null){
                List<String> list = resultItems.get("images");

                for(int i = 0,len = list.size();i<len;i++ ){
                    String url = list.get(i);
                    System.out.println(url);
                    output(path+ new Random(47).nextInt(1000000)  +".jpg", url);
                }
            }
        }

        public void output(String path,String url){
            try{
                FileOutputStream out = new FileOutputStream(path);
                URL u = new URL(url);
                URLConnection connection = u.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                int len = 0;
                while ((len = stream.read())!=-1){
                    out.write(len);
                }
                out.flush();
                System.out.println("===���===="+url);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}

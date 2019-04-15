package com.zylear.j2eelab.concurrent;

import com.google.common.base.Optional;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by xiezongyu on 2019/4/9.
 */
public class Demo1 {


    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(1);
        LinkedList<String> objects = new LinkedList<>();

        for (int i = 0; i < 1000; i++) {
            objects.add(String.valueOf(i));
        }
        CrmForkTask crmForkTask = new CrmForkTask(objects, 1);
        //阻塞获取fork结果
        try {
            List<String> list = pool.submit(crmForkTask).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static class CrmForkTask extends RecursiveTask<List<String>> {

        private List<String> beans;

        private int forkCount;

        public CrmForkTask(List<String> beans, int forkCount) {
            this.beans = beans;
            this.forkCount = forkCount;
        }

        @Override
        protected List<String> compute() {
//            throw new RuntimeException("ddf");
            if (beans.isEmpty()) {
                return Collections.emptyList();
            }
            List<String> list = new LinkedList<>();
            if (this.beans.size() <= forkCount) {
                for (String toutiaoUserSignatureInfo : this.beans) {
                    List<String> pullCrmRecordBeanList = pullCrmData(toutiaoUserSignatureInfo);
                    list.addAll(pullCrmRecordBeanList);
                }
                return list;
            }
            int middle = (0 + this.beans.size()) / 2;
            List<String> beforeList = this.beans.subList(0, middle);
            List<String> afterList = this.beans.subList(middle, this.beans.size());
            CrmForkTask beforeFork = new CrmForkTask(beforeList, this.forkCount);
            CrmForkTask afterFork = new CrmForkTask(afterList, this.forkCount);
            beforeFork.fork();
            afterFork.fork();
            list.addAll(beforeFork.join());
            list.addAll(afterFork.join());
            return list;
        }
    }


    public static List<String> pullCrmData(String userSignatureInfo){
//        LOGGER.info("start pull crm ,the userSignature Id {}",userSignatureInfo.getId());
        String timeStamp = String.valueOf(System.currentTimeMillis()/ 1000L);
        //一天调用一次,取当天日期
        int page=1;
        int count = 1;
        List<String> list = new LinkedList<>();
        int retry =0;
        do {
            Map<String,String> param = new HashMap<>(4);
            String pullCrmDataResponse = null;
            try {
//                String response = httpClientManagerV2.execGetRequestWithParamsAndHeaders(UrlConstant.PULL_CRM_DATA, param, header);
//                pullCrmDataResponse = JSONObject.parseObject(response, PullCrmDataResponse.class);
//                if (!pullCrmDataResponse.getStatus().equals(CRM_OK)) {
//                    JSONObject jsonObject=JSONObject.parseObject(response);
//                    if(jsonObject.getString("msg").contains("操作过于频繁")){
//                        //ignore and retry
//                        //可能出现操作频繁，先加入到队列中，若队列也失败只能放到第二天获取
//                        if( ++retry== RETRY_COUNT){
//                            LOGGER.info("retry max count :{},userSignatureId",retry,userSignatureInfo.getId());
//                            addDelayPull(userSignatureInfo);
//                            break;
//                        }
//                        Thread.sleep(5000);
//                        LOGGER.warn("visit toutiao crm fail, retry user sign id {} ," +
//                                "response warn :{}",userSignatureInfo.getId(),jsonObject.toJSONString());
//                        continue;
//                    }else {
                        throw new RuntimeException("pull crm data error:");
//                    }
//                }
            }catch (Exception e){
                //让接下来能继续拉取线索数据 这里将异常捕获
                System.out.println("pull crm data error the userSignatureInfo id");
//                LOGGER.error("pull crm data error the userSignatureInfo id :"+userSignatureInfo.getId() +", error :"+e);
                //ignore
            }
            if(Optional.fromNullable(pullCrmDataResponse).isPresent()){
                //第一次获取数量总数
                if(page==1){
//                    count=pullCrmDataResponse.getCount();
                    System.out.println("the userSignatureInfo Id:{} mapping crm count:{}");
                }
//                PullCrmRecordBean bean=new PullCrmRecordBean(userSignatureInfo.getId(),pullCrmDataResponse);
                list.add("s");
            }
            count -=100;
            page++;
        }while ( count>0);
        //小于重试次数任务已经成功了，更新账户的最后执行时间,失败放到下次执行
//        if(retry < RETRY_COUNT){
//            LOGGER.info("update id {},retry{}",userSignatureInfo.getId(),retry);
//            toutiaoUserSignatureInfoService.updateUserSignatureInfo(userSignatureInfo.getId(),new Date());
//        }
        return list;
    }
}




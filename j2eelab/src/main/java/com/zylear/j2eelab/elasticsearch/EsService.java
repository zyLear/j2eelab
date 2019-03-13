package com.zylear.j2eelab.elasticsearch;

import com.zylear.j2eelab.elasticsearch.common.EsTransportClient;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import javax.swing.plaf.ButtonUI;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiezongyu on 2019/3/12.
 */
public class EsService {

    public static void main(String[] args) {

        EsTransportClient esTransportClient = new EsTransportClient("elasticsearch", "172.20.13.216", 9300);
//        EsTransportClient esTransportClient = new EsTransportClient("elasticsearch", "127.0.0.1", 9200);

//        createIndex(esTransportClient);
        udpate(esTransportClient);
//        query(esTransportClient);

    }

    private static void query(EsTransportClient esTransportClient) {
        Client client = esTransportClient.getClient();
        SearchRequestBuilder builder = client.prepareSearch("test_index");
        builder.setTypes("test_type");
//        HashMap<String, String> stringStringHashMap = new HashMap<>();
//        stringStringHashMap.put("title", "ti");
        builder.setQuery(buildQuery());
        builder.setFrom(0).setSize(1);
        builder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        builder.setTrackScores(true);
//        builder.addField("_source");


        SearchResponse response = builder.execute().actionGet(10000, TimeUnit.MILLISECONDS);
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
//        List<CmsInfoDocument> documents = new ArrayList<>(searchHits.length);
//        String source = "";
        for (SearchHit searchHit : searchHits) {
            String source = searchHit.getSourceAsString();
            System.out.println(source);
//            TestDocument doc = JsonUtil.getObjectFromJson(source, TestDocument.class);
//            documents.add(doc);
//            if(isTestEnv && logger.isDebugEnabled()){
//                logger.debug("cmsInfoId:{}, score:{}", doc.getId(), searchHit.getScore());
//            }
        }


    }

    private static QueryBuilder buildQuery() {

        BoolQueryBuilder builder = QueryBuilders.boolQuery();



        BoolFilterBuilder boolFilterBuilder = FilterBuilders.boolFilter();
        OrFilterBuilder orFilter = FilterBuilders.orFilter();
        orFilter.add(FilterBuilders.termFilter("id", 3));
        orFilter.add(FilterBuilders.termFilter("id", 4));
        boolFilterBuilder.must(orFilter);
        builder.must(QueryBuilders.filteredQuery(QueryBuilders.matchAllQuery(), boolFilterBuilder));

//        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery();
//        disMaxQueryBuilder.add(QueryBuilders.matchQuery("title", "xie").analyzer("ik_strip_html")).boost(5);
//        disMaxQueryBuilder.tieBreaker(0.3f);
//        builder.must(disMaxQueryBuilder);

        return builder;
    }

//    public static QueryBuilder getQuery() {
////        BoolQueryBuilder builder = QueryBuilders.boolQuery();
////        builder.
////        buildFilterQuery(builder,parameter);
////        buildMatchQuery(builder);
//
//
//    }


    private static void createIndex(EsTransportClient esTransportClient) {
        Client client = esTransportClient.getClient();
        XContentBuilder mapping = null;
        try {
            mapping = XContentFactory.jsonBuilder();
            mapping.startObject().startObject("test_type");
            mapping.startObject("properties");
            buildFields(mapping);
//            buildQuestionName(mapping);
//            buildQuestionDeleted(mapping);
            mapping.endObject();
            mapping.endObject().endObject();
        }catch (Exception e){
            e.printStackTrace();
        }

//        if (retryCount > CREATE_INDEX_FAIL_RETRY_TIME){
//            return false;
//        }

        try {
            boolean indexExistence = client.admin().indices().prepareExists("test_index").execute().actionGet().isExists();
            if (indexExistence) {
                System.out.println("exist ");
            }else {
            CreateIndexResponse response = client.admin().indices().prepareCreate("test_index")
                    .addMapping("test_type", mapping).get(TimeValue.timeValueSeconds(60L));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
//            logger.error("fail init create index:{}, in retry time:{}", indexName, retryCount, ex);
//            boolean indexExistence = isExistIndex(indexName);
//            if (indexExistence) {
//                return true;
//            }
//            return createIndex(indexName, indexType, mapping, retryCount + 1);
        }

//         createIndex("test_index", "test_type", mapping);
    }

    private static void buildFields(XContentBuilder builder) {
        try {
            builder.startObject("id")
                    .field("type", "long")
                    .field("store", "no")
                    .field("index", "not_analyzed")
                    .endObject();

            builder.startObject("titile")
                    .field("type", "string")
                    .field("store", "no")
                    .field("index", "not_analyzed")
                    .endObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void udpate(EsTransportClient esTransportClient) {
        Client client = esTransportClient.getClient();

        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();

        TestDocument testDocument = new TestDocument();
        testDocument.setId(5L);
//        testDocument.setContent("con");
//        testDocument.setPost_time(new Date());
        testDocument.setTitle("come");
        XContentBuilder xContentBuilder = getTestDocumentBuilder(testDocument);

        UpdateRequestBuilder updateRequestBuilder = client
                .prepareUpdate("test_index", "test_type", String.valueOf(testDocument.getId()))
                .setDoc(xContentBuilder)
                .setRetryOnConflict(12)
                .setDocAsUpsert(true);
        bulkRequestBuilder.add(updateRequestBuilder);
        BulkResponse bulkItemResponses = bulkRequestBuilder.execute().actionGet();
        for (BulkItemResponse response : bulkItemResponses.getItems()) {
            if (response.isFailed()) {
                System.out.println("failed");
            } else {
                System.out.println("success");
            }
        }
    }


    public static XContentBuilder getTestDocumentBuilder(TestDocument testDocument) {
        try {
            Field[] fields = TestDocument.class.getDeclaredFields();

            XContentBuilder doc = XContentFactory.jsonBuilder().startObject();
            for (Field field : fields) {
                field.setAccessible(true);
                doc.field(field.getName(), field.get(testDocument));
            }
            doc.endObject();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    public static class TestDocument {
        private Long id;
        private String title;
//        private String content;
//        private Date post_time;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//
//        public Date getPost_time() {
//            return post_time;
//        }
//
//        public void setPost_time(Date post_time) {
//            this.post_time = post_time;
//        }
    }









//    public static void main(String[] args) throws IllegalAccessException {
//        TestDocument testDocument = new TestDocument();
//        testDocument.setId(1L);
//        testDocument.setContent("con");
//        testDocument.setPost_time(new Date());
//        testDocument.setTitle("ti");
//
//        Field[] fields = TestDocument.class.getDeclaredFields();
//
//        for (Field field : fields) {
//            field.setAccessible(true);
//            System.out.println(field.get(testDocument));
//
//        }
//
//    }

}

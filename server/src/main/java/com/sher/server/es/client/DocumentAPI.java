package com.sher.server.es.client;

import com.sher.server.es.client.model.DocumentIndex;
import org.elasticsearch.action.DocumentRequest;
import org.elasticsearch.action.bulk.*;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * Created by cloudsher on 2016/5/18.
 *  �ĵ�API  �������ĵ�����CRUD
 *  ���ݽṹ��Json
 */
public class DocumentAPI {

    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     *  �������
     * @return
     */
    public IndexResponse index(String index,String type,String id,String source){
        return client.prepareIndex(index,type,id)
                    .setSource(source)
                    .get();

    }

    /**
     * get source api
     * <p>
     * Operation Threading
     * The options are to execute the operation on a different thread, or to execute it on the calling thread (note that the API is still async).
     * By default, operationThreaded is set to true which means the operation is executed on a different thread.
     *
     * @param index
     * @param type
     * @param id
     * @return
     */
    public GetResponse get(String index,String type,String id){
        return client.prepareGet(index,type,id)
                .setOperationThreaded(false)
                .get();
    }


    /**
     *  delete Api
     *
     * @param index
     * @param type
     * @param id
     * @return
     */
    public DeleteResponse delete(String index,String type,String id){
        return client.prepareDelete(index,type,id)
                       .get();
    }


    /**
     *  update API for updateRequest
     *
     * @param index
     * @param type
     * @param id
     * @param source
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public UpdateResponse update(String index,String type,String id,String source) throws ExecutionException, InterruptedException {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index).index(type).index(id).doc(source);
        return client.update(updateRequest).get();
    }


    /**
     *  prepareUpdate method for doc
     *
     * @param index
     * @param type
     * @param id
     * @param obj
     * @return
     */
    public UpdateResponse prepareUpdate(String index,String type,String id,Object obj){
        return client.prepareUpdate(index, type, id)
//                .setScript(new Script("ctx._source.gender = \"male\""  , ScriptService.ScriptType.INLINE, null, null))
                .setDoc(obj)
                .get();

    }


    /**
     *  multi get api
     *
     * @param indexes
     * @return
     */
    public MultiGetResponse multiGet(DocumentIndex[] indexes){
        MultiGetRequestBuilder builder = client.prepareMultiGet();
        Arrays.asList(indexes).forEach(index -> builder.add(index.getIndex(),index.getType(),index.getId()));
        return builder.get();
    }


    /**
     *  bulk request
     *
     * @param request
     * @return
     */
    public BulkResponse bulk(DocumentRequest[] request){
        BulkRequestBuilder builder = client.prepareBulk();
        Arrays.asList(request).stream()
                .forEach(req -> {
                    if(req instanceof IndexRequest)
                        builder.add((IndexRequest)req);
                    else if(req instanceof UpdateRequest)
                        builder.add((UpdateRequest)req);
                    else if(req instanceof DeleteRequest)
                        builder.add((DeleteRequest)req);
                });
        return builder.get();
    }


    /**
     *  bulkProcessor api
     *
     * @param requests
     */
    public void bulkProcessor(DocumentRequest[] requests){
        BulkProcessor bulkProcessor = BulkProcessor.builder(
                client,
                new BulkProcessor.Listener() {
                    @Override
                    public void beforeBulk(long executionId, BulkRequest request) {

                    }

                    @Override
                    public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {

                    }

                    @Override
                    public void afterBulk(long executionId, BulkRequest request, Throwable failure) {

                    }
                })
                .setBulkActions(10000)
                .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                .setConcurrentRequests(1)
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100),3))
                .build();
        Arrays.asList(requests).forEach(req ->{
            if(req instanceof IndexRequest)
                bulkProcessor.add((IndexRequest)req);
            else if(req instanceof UpdateRequest)
                bulkProcessor.add((UpdateRequest)req);
            else if(req instanceof DeleteRequest)
                bulkProcessor.add((DeleteRequest)req);
        });

        try {
            bulkProcessor.awaitClose(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

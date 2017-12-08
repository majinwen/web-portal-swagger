package com.toutiao.web.dao.mapper.esmapping;

import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class newHouseMapping {


    @Autowired
    private ESClientTools esClientTools;

    public void buildNewHouseMapping() {

        /**
         * 创建索引
         */
        CreateIndexRequest request = new CreateIndexRequest("newhouse");
        esClientTools.init();
        TransportClient client = esClientTools.get();
        client.admin().indices().create(request);


    }

    /**
     * 构建mapping
     * @return
     */
    public static XContentBuilder getMapping() {
        XContentBuilder mapping = null;

        try {
            mapping = jsonBuilder().startObject();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapping;
    }


}

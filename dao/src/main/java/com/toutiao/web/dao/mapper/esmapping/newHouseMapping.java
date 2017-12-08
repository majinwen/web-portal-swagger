package com.toutiao.web.dao.mapper.esmapping;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.net.InetAddress;


public class newHouseMapping {

    protected static void buildIndexMapping() throws Exception {

        Settings settings = Settings.builder().put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("47.104.96.88"), 9300));
        client.admin().indices().prepareCreate("newhouse").execute().actionGet();


        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject().startObject("xxxx99")
                .startObject("properties")
                    .startObject("districtId").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("districtName").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("areaId").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("areaName").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("subwayLine").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("subwayStation").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("totalPrice").field("type","double").field("index","not_analyzed").endObject()
                    .startObject("layoutId").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("layoutType").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("areaSize").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("tagsId").field("type","object").endObject()
                    .startObject("tagsName").field("type","object").endObject()
                    .startObject("buildFormId").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("buildingAge").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("purposeId").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("purposeName").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("isLift").field("type","integer").field("index","not_analyzed").endObject()
                    .startObject("ownershipId").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("projName").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("nickName").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("averagePrice").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("imgs").field("type","object").endObject()
                    .startObject("saledate").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("livindate").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("developer").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("developer").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("categoryName").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("rightYear").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("buildCategory").field("type","text").field("index","not_analyzed").endObject()
                    .startObject("layoutList").field("type","object").endObject()
                    .endObject().endObject().endObject();
        PutMappingRequest mappingRequest = Requests.putMappingRequest("newhouse").type("xxxx99").source(mapping);
        client.admin().indices().putMapping(mappingRequest).actionGet();

    }

    public static void main(String[] args) throws Exception {

        buildIndexMapping();

    }





//    @Autowired
//    private ESClientTools esClientTools;
//
//    public void buildNewHouseMapping() {
//
//        /**
//         * 创建索引
//         */
//
//
//
//    }
//
//    /**
//     * 构建mapping
//     * @return
//     */
//    public static XContentBuilder getMapping() {
//        XContentBuilder mapping = null;
//
//        try {
//            mapping = jsonBuilder().startObject()
//                    .startObject("_id").field("index","not_analyzed").field("path","newcode").endObject()
//                    .startObject("properties")
//                    .startObject("districtId").field("type","text").field("index","not_analyzed").endObject()
//                    .startObject("districtName").field("type","text").field("index","not_analyzed").endObject()
//                    .startObject("areaId").field("type","text").field("index","not_analyzed").endObject()
//                    .startObject("areaName").field("type","text").field("index","not_analyzed").endObject()
//                    .startObject("subwayLine").field("type","text").field("index","not_analyzed").endObject()
//                    .startObject("subwayStation").field("type","text").field("index","not_analyzed").endObject()
//                    .startObject("totalPrice").field("type","double").field("index","not_analyzed").endObject()
//                    .startObject("layoutId").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("layoutType").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("areaSize").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("tagsId").field("type","object").field("index","not_analyzed").endObject()
//                    .startObject("tagsName").field("type","object").field("index","not_analyzed").endObject()
//                    .startObject("buildFormId").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("buildingAge").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("purposeId").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("purposeName").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("isLift").field("type","integer").field("index","not_analyzed").endObject()
//                    .startObject("ownershipId").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("projName").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("nickName").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("averagePrice").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("imgs").field("type","object").field("index","not_analyzed").endObject()
//                    .startObject("saledate").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("livindate").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("developer").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("developer").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("categoryName").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("rightYear").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("buildCategory").field("type","string").field("index","not_analyzed").endObject()
//                    .startObject("layoutList").field("type","object").field("index","not_analyzed").endObject()
//                    .endObject().endObject();
//
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//        return mapping;
//    }
//
//    public static void createBangMapping(){
//        PutMappingRequest mappingRequest = Requests.putMappingRequest("newhouse").type("xxxx99").source(newHouseMapping.getMapping());
//        esClientTools.admin().indices().putMapping(mappingRequest).actionGet();
//
//    }
//
//
//    public static void main(String[] args) throws Exception {
//
//        CreateIndexRequest request = new CreateIndexRequest("newhouse");
//        esClientTools.init();
//        TransportClient client = esClientTools.get();
//        client.admin().indices().create(request);
//
//        newHouseMapping.createBangMapping();
//
//    }


}

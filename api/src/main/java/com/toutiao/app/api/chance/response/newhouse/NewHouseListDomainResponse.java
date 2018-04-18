package com.toutiao.app.api.chance.response.newhouse;

import lombok.Data;

import java.util.List;
@Data
public class NewHouseListDomainResponse {


    private  List<NewHosueListResponse> newHosueList;
    private long totalCount;

}

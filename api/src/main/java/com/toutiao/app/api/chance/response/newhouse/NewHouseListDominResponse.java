package com.toutiao.app.api.chance.response.newhouse;

import lombok.Data;

import java.util.List;
@Data
public class NewHouseListDominResponse {


    private  List<NewHosueListResponse> newHosueList;
    private long totalCount;

}

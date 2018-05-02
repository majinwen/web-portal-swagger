package com.toutiao.app.domain.newhouse;


import lombok.Data;

import java.util.List;

@Data
public class NewHouseListDomain {


    private List<NewHouseListDo> data;

    private  long totalCount;


}

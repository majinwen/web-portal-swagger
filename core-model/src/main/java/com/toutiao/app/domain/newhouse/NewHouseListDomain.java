package com.toutiao.app.domain.newhouse;


import lombok.Data;

import java.util.List;

@Data
public class NewHouseListDomain {


    private List<NewHouseListDo> listDoList;

    private  long totalCount;


}

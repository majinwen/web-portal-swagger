package com.toutiao.app.domain.newhouse;


import lombok.Data;

import java.util.List;

@Data
public class NewHouseListVo {


    private List<NewHouseListDo> listDoList;

    private  long TotalCount;


}

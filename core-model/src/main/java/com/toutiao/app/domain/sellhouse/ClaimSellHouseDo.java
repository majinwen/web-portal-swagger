package com.toutiao.app.domain.sellhouse;

import lombok.Data;

@Data
public class ClaimSellHouseDo {


    /**
     * "claimHouseTitle","claimHousePhotoTitle","claimTags","claimTagsName","claimHouseId",is_claim
     */

    private  Integer isClaim;

    private  String claimHouseTitle;

    private  String claimHousePhotoTitle;

    private  String claimTags;

    private   String [] claimTagsName;

    private  String claimHouseId;
}

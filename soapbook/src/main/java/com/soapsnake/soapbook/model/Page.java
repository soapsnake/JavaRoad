package com.soapsnake.soapbook.model;

import java.util.List;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-01
 */
public class Page {
    private Integer pageId;
    private String name;
    private String description;
    private String type;
    private int totalMembers;
    private List<Recommendation> recommendation;

    private List<Recommendation> getRecommendation() {
        return null;
    }
}

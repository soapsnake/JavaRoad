package com.soapsnake.soapbook.model;

import java.util.List;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-01
 */
public class Profile {
    private byte[] profilePicture;
    private byte[] coverPhoto;
    private String gender;

    private List<Work> workExperiences;
    private List<Education> educations;
    private List<Place> places;
    private List<Stat> stats;

    public boolean addWorkExperience(Work work) {
        return false;
    }

    public boolean addEducation(Education education) {
        return false;
    }

    public boolean addPlace(Place place) {
        return false;
    }
}

package com.joe.mvc.dao;

import com.joe.mvc.model.DeveloperModel;

import java.util.List;

public interface DeveloperDao {

    List<DeveloperModel> getDevelopers();

    DeveloperModel getDeveloper(String id);

    boolean updateDeveloper(String id, String name,String site);

    boolean deleteDeveloper(String id);
}

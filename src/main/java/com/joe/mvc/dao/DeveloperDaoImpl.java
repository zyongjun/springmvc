package com.joe.mvc.dao;

import com.joe.mvc.model.DeveloperModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("developerDaoImpl")
public class DeveloperDaoImpl implements DeveloperDao{
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<DeveloperModel> getDevelopers() {
        String sql = "select * from developer";
        return query(sql);
    }

    @Override
    public DeveloperModel getDeveloper(String id) {
        List<DeveloperModel> list = query("select * from developer where id = "+id);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean addDeveloper(DeveloperModel model) {
        try {
            String sql = "INSERT INTO developer(name,site,avatar) VALUES(" +
                    "'" + model.getName() + "','" + model.getSite() + "','" + model.getAvatar() + "'" +
                    ")";
            System.out.println("add sql =="+sql);
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateDeveloper(String id, String name, String site) {
        try {
            String sql = "UPDATE developer SET ";
            if (name != null) {
                sql += "name = '"+name+"', ";
            }
            if (site != null) {
                sql += "site = '"+site+"' ";
            }
            if(sql.trim().endsWith(",")){
                sql=sql.substring(0,sql.lastIndexOf(",")).trim()+" ";
            }
            sql+= "where id="+id;
            jdbcTemplate.update(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDeveloper(String id) {
        try {
            String sql = "DELETE FROM developer where id = " + id;
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<DeveloperModel> query(String sql){
        final List<DeveloperModel> developerModelList = new ArrayList<>();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                String avatar = resultSet.getString(4);
                DeveloperModel model = new DeveloperModel();
                model.setId(id);
                model.setSite(site);
                model.setName(name);
                model.setAvatar(avatar);
                developerModelList.add(model);
            }
        });
        return developerModelList;
    }
}

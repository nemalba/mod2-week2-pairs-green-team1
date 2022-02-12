package com.techelevator.dao;

import com.techelevator.model.Site;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcSiteDao implements SiteDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcSiteDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*
    List all sites in park that allow RVs
The application needs the ability to list all sites in a park—across all campgrounds—that allow RVs.

A site includes an ID, site number, maximum occupancy, if it's accessible, maximum RV length, and if it has utilities.

A site with 0 listed for max_rv_length indicates the site doesn't allow RVs.
     */
    @Override
    public List<Site> getSitesThatAllowRVs(int parkId) {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT site_id, campground_id, site_number, max_occupancy,  accessible, max_rv_length, utilities FROM site WHERE max_rv_length != 0;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()){
            Site site = mapRowToSite(results);
            sites.add(site);
        }
        return sites;

        //return new ArrayList<>();
    }

    private Site mapRowToSite(SqlRowSet results) {
        Site site = new Site();
        site.setSiteId(results.getInt("site_id"));
        site.setCampgroundId(results.getInt("campground_id"));
        site.setSiteNumber(results.getInt("site_number"));
        site.setMaxOccupancy(results.getInt("max_occupancy"));
        site.setAccessible(results.getBoolean("accessible"));
        site.setMaxRvLength(results.getInt("max_rv_length"));
        site.setUtilities(results.getBoolean("utilities"));
        return site;
    }
}

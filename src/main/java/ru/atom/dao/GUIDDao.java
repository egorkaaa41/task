package ru.atom.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.atom.model.GUID;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class GUIDDao {
    private static final Logger log = LoggerFactory.getLogger(DbConnector.class);
    private static final String INSERT_GUID_TEMPLATE =
            "insert into task (guid, time, status) " +
                    "values ('%s', now(), '%s');";
    private static final String UPDATE_GUID_TEMPLATE =
            "UPDATE task " +
                    "SET " +
                    "time = now()," +
                    "status = '%s'" +
                    "WHERE guid = '%s';";
    private static final String SELECT_ALL_GUID_WHERE =
            "select * " +
                    "from task " +
                    "where ";
    public void insert(GUID guid) {
        try (Connection con = DbConnector.getConnection();
             Statement stm = con.createStatement()
        ) {
            stm.execute(String.format(INSERT_GUID_TEMPLATE, guid.getGuid(), guid.getStatus()));
        } catch (SQLException e) {
            log.error("Failed to create guid {}", guid.getGuid(), e);
        }
    }
    public void UpDate(GUID guid) {
        try (Connection con = DbConnector.getConnection();
             Statement stm = con.createStatement()
        ) {
            stm.executeUpdate(String.format(UPDATE_GUID_TEMPLATE, guid.getStatus(),guid.getGuid()));
        } catch (SQLException e) {
            log.error("Failed to update guid {}", guid.getGuid(), e);
        }
    }
    public List<GUID> GetAllWhere(String guid){
        List<GUID> persons = new ArrayList<>();
        try (Connection con = DbConnector.getConnection();
             Statement stm = con.createStatement()
        ) {

            String condition = String.join(" and ", guid);
            ResultSet rs = stm.executeQuery(SELECT_ALL_GUID_WHERE + condition);
            while (rs.next()) {
                persons.add(mapToGUID(rs));
            }
        } catch (SQLException e) {
            log.error("Failed to getAll where.", e);
            return Collections.emptyList();
        }

        return persons;

    }
    private static GUID mapToGUID(ResultSet rs) throws SQLException {
        return new GUID()
                .setId(rs.getString("guid"))
                .setTimestamp(rs.getDate("time"))
                .setStatus(rs.getString("status"));
    }

}

package ru.atom.model;

import java.util.Date;

public class GUID {
    private String guid;
    private Date timestamp;
    private String status;


    public String getGuid() {
        return guid;
    }

    public GUID setId(String guid) {
        this.guid = guid;
        return this;
    }
    public Date getTimestamp() {
        return timestamp;
    }

    public GUID setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    public String getStatus(){
        return status;
    }
    public GUID setStatus(String status){
        this.status = status;
        return this;
    }





    @Override
    public String toString() {
        return "User{" +
                "id=" + guid +
                ", status='" + status + '\'' +
                '}';
    }
}

package com.custom.bdyx.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 变电站Vo
 *
 */
public class SubstationVo implements Serializable{
    private String id;
    private String name;
    private String thumb;
    private String img;
    private String statusId;
    private String statusName;
    private List<Record> records;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public static class Record implements Serializable{
        private String id;
        private String tourTime;
        private String tourName;
        private String tourStatusName;
        private String tourStatusId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTourTime() {
            return tourTime;
        }

        public void setTourTime(String tourTime) {
            this.tourTime = tourTime;
        }

        public String getTourName() {
            return tourName;
        }

        public void setTourName(String tourName) {
            this.tourName = tourName;
        }

        public String getTourStatusName() {
            return tourStatusName;
        }

        public void setTourStatusName(String tourStatusName) {
            this.tourStatusName = tourStatusName;
        }

        public String getTourStatusId() {
            return tourStatusId;
        }

        public void setTourStatusId(String tourStatusId) {
            this.tourStatusId = tourStatusId;
        }
    }
}

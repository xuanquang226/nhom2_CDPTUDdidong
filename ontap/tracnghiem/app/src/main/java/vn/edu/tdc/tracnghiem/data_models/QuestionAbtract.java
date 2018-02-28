package vn.edu.tdc.tracnghiem.data_models;

import java.io.Serializable;

/**
 * Created by IT on 10/4/2017.
 */

public class QuestionAbtract implements Serializable{
    final public static String CAU_GHEP_DOI = "ghepdoi";
    final public static String CAU_NHIEU_LUA_CHON_MOT_DAP_AN = "nhieuluachon_motdapan";
    final public static String CAU_NHIEU_LUA_CHON_NHIEU_DAP_AN = "nhieuluachon_nhieudapan";
    final public static String CAU_DUNG_SAI = "dungsai";

    protected String type ="abstract";

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getPoints(){
        int point =0;
        return 0;
    }
}

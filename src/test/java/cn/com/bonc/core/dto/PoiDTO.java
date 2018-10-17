package cn.com.bonc.core.dto;

import cn.com.bonc.core.annotation.FieldDesc;
import cn.com.bonc.core.annotation.FieldPlaceArray;

import java.util.List;

/**
 * POI对应的信息
 *
 * @author
 * @create 2018-05-14 18:24
 **/

public class PoiDTO {

    private String levelId;

    @FieldPlaceArray(place = 1, desc = "百度id")
    @FieldDesc(desc = "百度id")
    private String bdId;


    @FieldPlaceArray(place = 2, desc = "地址")
    @FieldDesc(desc = "地址")
    private String address;


    @FieldPlaceArray(place = 3, desc = "区域id")
    @FieldDesc(desc = "区域id")
    private String areaId;


    @FieldPlaceArray(place = 4, desc = " 区域名称")
    @FieldDesc(desc = "区域名称")
    private String areaName;


    @FieldPlaceArray(place = 5, desc = "catalogID")
    @FieldDesc(desc = "catalogID")
    private String catalogId;


    @FieldPlaceArray(place = 6, desc = "cla标签")
    @FieldDesc(desc = "cla标签")

    private String claTagName;



    @FieldPlaceArray(place = 7, desc = "di标签")
    @FieldDesc(desc = "di标签")

    private String diTagName;



    @FieldPlaceArray(place = 8, desc = "ext类型")
    @FieldDesc(desc = "ext类型")
    private String extType;



    @FieldPlaceArray(place = 9, desc = "名称")
    @FieldDesc(desc = "名称")
    private String name;



    @FieldPlaceArray(place = 10, desc = " 时间")
    @FieldDesc(desc = "时间")
    private String dateTime;



    @FieldPlaceArray(place = 11, desc = "经度")
    @FieldDesc(desc = "经度")
    private String lng;



    @FieldPlaceArray(place = 12, desc = "纬度")
    @FieldDesc(desc = "纬度")
    private String lat;


    @FieldDesc(desc = "经纬度")
    private String lngLat;


    @FieldPlaceArray(place = 13, desc = "是否有aoi")
    @FieldDesc(desc = "是否有aoi")

    private String hasAio;

    @FieldPlaceArray(place = 14, desc = "Primary_id")
    @FieldDesc(desc = "Primary_id")

    private String primaryId;

    @FieldPlaceArray(place = 15, desc = "show标签")
    @FieldDesc(desc = "show标签")

    private String showTag;

    @FieldPlaceArray(place = 16, desc = "std标签")
    @FieldDesc(desc = "std标签")
    private String stdTag;

    @FieldPlaceArray(place = 17, desc = "街景ID")
    @FieldDesc(desc = "街景ID")
    private String streetId;

    @FieldPlaceArray(place = 18, desc = "标签")
    @FieldDesc(desc = "标签")
    private String tagName;

    @FieldPlaceArray(place = 19, desc = "zip")
    @FieldDesc(desc = "zip")
    private String zipName;

    @FieldPlaceArray(place = 20, desc = "票价")
    @FieldDesc(desc = "票价")
    private String price;

    @FieldPlaceArray(place = 21, desc = "景点等级")
    @FieldDesc(desc = "景点等级")
    private String scenicLevle;

    @FieldPlaceArray(place = 22, desc = "营业时间")
    @FieldDesc(desc = "营业时间")
    private String openTime;

    @FieldPlaceArray(place = 23, desc = "简介")
    @FieldDesc(desc = "简介")
    private String desc;

    @FieldPlaceArray(place = 24, desc = "detail标签")
    @FieldDesc(desc = "detail标签")
    private String detailTagName;

    @FieldPlaceArray(place = 25, desc = "aoi")
    @FieldDesc(desc = "aoi")
    private String aio;

    @FieldPlaceArray(place = 26, desc = "评分")
    @FieldDesc(desc = "评分")
    private String score;

    @FieldPlaceArray(place = 27, desc = "weighted标签")
    @FieldDesc(desc = "weighted标签")
    private String weightedTagName;

    @FieldPlaceArray(place = 28, desc = "营业时间")
    @FieldDesc(desc = "营业时间")
    private String openTime1;

    @FieldPlaceArray(place = 29, desc = "酒店设施")
    @FieldDesc(desc = "酒店设施")
    private String hotelFacilities;

    @FieldPlaceArray(place = 30, desc = "酒店服务")
    @FieldDesc(desc = "酒店服务")
    private String hotelService;

    @FieldPlaceArray(place = 31, desc = "内部服务")
    @FieldDesc(desc = "内部服务")
    private String hotelInnerService;

    @FieldPlaceArray(place = 32, desc = "特色服务")
    @FieldDesc(desc = "特色服务")
    private String specialService;

    @FieldPlaceArray(place = 33, desc = "介绍")
    @FieldDesc(desc = "介绍")
    private String desc1;


    @FieldPlaceArray(place = 34, desc = "服务评分")
    @FieldDesc(desc = "服务评分")
    private String scoreNum;


    @FieldPlaceArray(place = 35, desc = "最佳时间")
    @FieldDesc(desc = "最佳时间")
    private String bestTime;

    @FieldPlaceArray(place = 36, desc = "建议时间")
    @FieldDesc(desc = "建议时间")
    private String suggestTime;

    @FieldPlaceArray(place = 37, desc = "墨卡托经度*100")
    @FieldDesc(desc = "墨卡托经度")
    private String mercatorLngHundred;

    @FieldPlaceArray(place = 38, desc = "墨卡托纬度*100")
    @FieldDesc(desc = "墨卡托纬度")
    private String mercatorLatHundred;

    @FieldPlaceArray(place = 39, desc = "墨卡托经度1")
    @FieldDesc(desc = "墨卡托经度1")
    private String mercatorLng1;

    @FieldPlaceArray(place = 40, desc = "墨卡托纬度1")
    @FieldDesc(desc = "墨卡托纬度1")
    private String mercatorLat1;

    @FieldPlaceArray(place = 41, desc = "电话")
    @FieldDesc(desc = "电话")
    private String phone;

    @FieldPlaceArray(place = 42, desc = "市ID")
    @FieldDesc(desc = "市ID")
    private String cityId;

    @FieldPlaceArray(place = 43, desc = "省ID")
    @FieldDesc(desc = "省ID")
    private String provinceId;

    @FieldPlaceArray(place = 44, desc = "转为百度坐标AOI")
    @FieldDesc(desc = "转为百度坐标AOI")
    private String bdAoi;

    @FieldPlaceArray(place = 45, desc = "百度坐标经度")
    @FieldDesc(desc = "百度坐标经度")
    private String bdLng;

    @FieldPlaceArray(place = 46, desc = "百度坐标纬度")
    @FieldDesc(desc = "百度坐标纬度")
    private String bdLat;

    @FieldPlaceArray(place = 47, desc = "转为WGS经坐标AOI")
    @FieldDesc(desc = "转为WGS经坐标AOI")
    private String wgsAoi;

    @FieldPlaceArray(place = 48, desc = "WGS坐标经度")
    @FieldDesc(desc = "WGS坐标经度")
    private String wgsLng;

    @FieldPlaceArray(place = 49, desc = " WGS坐标纬度")
    @FieldDesc(desc = "WGS坐标纬度")
    private String wgsLat;

    @FieldPlaceArray(place = 50, desc = "标签")
    @FieldDesc(desc = "标签")
    private String tagName1;

    @FieldPlaceArray(place = 51, desc = " 标签ID")
    @FieldDesc(desc = "标签ID")
    private String tagId;

    @FieldDesc(desc = "WGS坐标经度")
    private String typeId;

    @FieldDesc(desc = "WGS坐标经度")
    private String typeName;

    /**查找到符合条件的相似的数据*/
    private List<PoiDTO> similarPoi;

    /**地址相似度*/
    @FieldDesc(desc = "用于查询的ID")
    private String compareId;

    /**地址相似度*/
    private double addressDegree;

    /**aoiName相似度*/
    private double nameDegree;

    /**tagName相似度*/
    private double tagNameDegree;

    /**地址相似度*/
    @FieldDesc(desc = "地址相似度")
    private String addressDegree1;

    /**aoiName相似度*/
    @FieldDesc(desc = "aoiName相似度")
    private String nameDegree1;

    /**tagName相似度*/
    @FieldDesc(desc = "tagName相似度")
    private String tagNameDegree1;

    /**合并的类型*/
    @FieldDesc(desc = "合并的类型")
    private String mergeType;

    @FieldDesc(desc = "合并的Id")
    private String mergeId;

    @FieldDesc(desc = "查询的距离")
    private String distance;

    @FieldDesc(desc = "总的相似度")
    private String allDegree;

    private String allTagName;

    public String getLevelId() {
        return levelId;
    }

    public String getBdId() {
        return bdId;
    }

    public String getAddress() {
        return address;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public String getClaTagName() {
        return claTagName;
    }

    public String getDiTagName() {
        return diTagName;
    }

    public String getExtType() {
        return extType;
    }

    public String getName() {
        return name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    public String getHasAio() {
        return hasAio;
    }

    public String getPrimaryId() {
        return primaryId;
    }

    public String getShowTag() {
        return showTag;
    }

    public String getStdTag() {
        return stdTag;
    }

    public String getStreetId() {
        return streetId;
    }

    public String getTagName() {
        return tagName;
    }

    public String getZipName() {
        return zipName;
    }

    public String getPrice() {
        return price;
    }

    public String getScenicLevle() {
        return scenicLevle;
    }

    public String getDesc() {
        return desc;
    }

    public String getDetailTagName() {
        return detailTagName;
    }

    public String getAio() {
        return aio;
    }

    public String getScore() {
        return score;
    }

    public String getWeightedTagName() {
        return weightedTagName;
    }



    public String getHotelFacilities() {
        return hotelFacilities;
    }

    public String getHotelService() {
        return hotelService;
    }

    public String getHotelInnerService() {
        return hotelInnerService;
    }

    public String getSpecialService() {
        return specialService;
    }

    public String getDesc1() {
        return desc1;
    }

    public String getScoreNum() {
        return scoreNum;
    }

    public String getBestTime() {
        return bestTime;
    }

    public String getSuggestTime() {
        return suggestTime;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getOpenTime1() {
        return openTime1;
    }

    public String getMercatorLngHundred() {
        return mercatorLngHundred;
    }

    public String getMercatorLatHundred() {
        return mercatorLatHundred;
    }

    public String getMercatorLng1() {
        return mercatorLng1;
    }

    public String getMercatorLat1() {
        return mercatorLat1;
    }

    public String getPhone() {
        return phone;
    }

    public String getCityId() {
        return cityId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public String getBdAoi() {
        return bdAoi;
    }

    public String getBdLng() {
        return bdLng;
    }

    public String getBdLat() {
        return bdLat;
    }

    public String getWgsAoi() {
        return wgsAoi;
    }

    public String getWgsLng() {
        return wgsLng;
    }

    public String getWgsLat() {
        return wgsLat;
    }

    public String getTagName1() {
        return tagName1;
    }

    public String getTagId() {
        return tagId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public void setBdId(String bdId) {
        this.bdId = bdId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public void setClaTagName(String claTagName) {
        this.claTagName = claTagName;
    }

    public void setDiTagName(String diTagName) {
        this.diTagName = diTagName;
    }

    public void setExtType(String extType) {
        this.extType = extType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLngLat(String lngLat) {
        this.lngLat = lngLat;
    }

    public void setHasAio(String hasAio) {
        this.hasAio = hasAio;
    }

    public void setPrimaryId(String primaryId) {
        this.primaryId = primaryId;
    }

    public void setShowTag(String showTag) {
        this.showTag = showTag;
    }

    public void setStdTag(String stdTag) {
        this.stdTag = stdTag;
    }

    public void setStreetId(String streetId) {
        this.streetId = streetId;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setZipName(String zipName) {
        this.zipName = zipName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setScenicLevle(String scenicLevle) {
        this.scenicLevle = scenicLevle;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDetailTagName(String detailTagName) {
        this.detailTagName = detailTagName;
    }

    public void setAio(String aio) {
        this.aio = aio;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setWeightedTagName(String weightedTagName) {
        this.weightedTagName = weightedTagName;
    }

    public void setOpenTime1(String openTime1) {
        this.openTime1 = openTime1;
    }

    public void setHotelFacilities(String hotelFacilities) {
        this.hotelFacilities = hotelFacilities;
    }

    public void setHotelService(String hotelService) {
        this.hotelService = hotelService;
    }

    public void setHotelInnerService(String hotelInnerService) {
        this.hotelInnerService = hotelInnerService;
    }


    public void setSpecialService(String specialService) {
        this.specialService = specialService;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public void setScoreNum(String scoreNum) {
        this.scoreNum = scoreNum;
    }

    public void setBestTime(String bestTime) {
        this.bestTime = bestTime;
    }

    public void setSuggestTime(String suggestTime) {
        this.suggestTime = suggestTime;
    }

    public void setMercatorLngHundred(String mercatorLngHundred) {
        this.mercatorLngHundred = mercatorLngHundred;
    }

    public void setMercatorLatHundred(String mercatorLatHundred) {
        this.mercatorLatHundred = mercatorLatHundred;
    }

    public void setMercatorLng1(String mercatorLng1) {
        this.mercatorLng1 = mercatorLng1;
    }

    public void setMercatorLat1(String mercatorLat1) {
        this.mercatorLat1 = mercatorLat1;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public void setBdAoi(String bdAoi) {
        this.bdAoi = bdAoi;
    }

    public void setBdLng(String bdLng) {
        this.bdLng = bdLng;
    }

    public void setBdLat(String bdLat) {
        this.bdLat = bdLat;
    }

    public void setWgsAoi(String wgsAoi) {
        this.wgsAoi = wgsAoi;
    }

    public void setWgsLng(String wgsLng) {
        this.wgsLng = wgsLng;
    }

    public void setWgsLat(String wgsLat) {
        this.wgsLat = wgsLat;
    }

    public void setTagName1(String tagName1) {
        this.tagName1 = tagName1;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getLngLat() {
        return lngLat;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNameDegree1() {
        return nameDegree1;
    }

    public void setNameDegree1(String nameDegree1) {
        this.nameDegree1 = nameDegree1;
    }

    public String getTagNameDegree1() {
        return tagNameDegree1;
    }

    public void setTagNameDegree1(String tagNameDegree1) {
        this.tagNameDegree1 = tagNameDegree1;
    }

    public String getAddressDegree1() {
        return addressDegree1;
    }

    public void setAddressDegree1(String addressDegree1) {
        this.addressDegree1 = addressDegree1;
    }

    public List<PoiDTO> getSimilarPoi() {
        return similarPoi;
    }

    public void setSimilarPoi(List<PoiDTO> similarPoi) {
        this.similarPoi = similarPoi;
    }

    public double getAddressDegree() {
        return addressDegree;
    }

    public void setAddressDegree(double addressDegree) {
        this.addressDegree = addressDegree;
    }

    public double getNameDegree() {
        return nameDegree;
    }

    public void setNameDegree(double nameDegree) {
        this.nameDegree = nameDegree;
    }

    public double getTagNameDegree() {
        return tagNameDegree;
    }

    public void setTagNameDegree(double tagNameDegree) {
        this.tagNameDegree = tagNameDegree;
    }

    public String getMergeType() {
        return mergeType;
    }

    public void setMergeType(String mergeType) {
        this.mergeType = mergeType;
    }

    public String getMergeId() {
        return mergeId == null ? null : mergeId.trim();
    }

    public void setMergeId(String mergeId) {
        this.mergeId = mergeId == null ? null : mergeId.trim();
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCompareId() {
        return compareId == null ? null : compareId.trim();
    }

    public void setCompareId(String compareId) {
        this.compareId = compareId;
    }

    public String getAllDegree() {
        return allDegree;
    }

    public void setAllDegree(String allDegree) {
        this.allDegree = allDegree;
    }

    public String getAllTagName() {
        return allTagName;
    }

    public void setAllTagName(String allTagName) {
        this.allTagName = allTagName;
    }
}

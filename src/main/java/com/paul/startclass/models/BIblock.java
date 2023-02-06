package com.paul.startclass.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "b_iblock", schema = "sova", catalog = "")
public class BIblock {
    private int id;
    private Timestamp timestampX;
    private String iblockTypeId;
    private String lid;
    private String code;
    private String apiCode;
    private String name;
    private String active;
    private int sort;
    private String listPageUrl;
    private String detailPageUrl;
    private String sectionPageUrl;
    private String canonicalPageUrl;
    private Integer picture;
    private String description;
    private String descriptionType;
    private int rssTtl;
    private String rssActive;
    private String rssFileActive;
    private Integer rssFileLimit;
    private Integer rssFileDays;
    private String rssYandexActive;
    private String xmlId;
    private String tmpId;
    private String indexElement;
    private String indexSection;
    private String workflow;
    private String bizproc;
    private String sectionChooser;
    private String listMode;
    private String rightsMode;
    private String sectionProperty;
    private String propertyIndex;
    private int version;
    private int lastConvElement;
    private Integer socnetGroupId;
    private String editFileBefore;
    private String editFileAfter;
    private String sectionsName;
    private String sectionName;
    private String elementsName;
    private String elementName;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TIMESTAMP_X", nullable = false)
    public Timestamp getTimestampX() {
        return timestampX;
    }

    public void setTimestampX(Timestamp timestampX) {
        this.timestampX = timestampX;
    }

    @Basic
    @Column(name = "IBLOCK_TYPE_ID", nullable = false, length = 50)
    public String getIblockTypeId() {
        return iblockTypeId;
    }

    public void setIblockTypeId(String iblockTypeId) {
        this.iblockTypeId = iblockTypeId;
    }

    @Basic
    @Column(name = "LID", nullable = false, length = 2)
    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    @Basic
    @Column(name = "CODE", nullable = true, length = 50)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "API_CODE", nullable = true, length = 50)
    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ACTIVE", nullable = false, length = 1)
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Basic
    @Column(name = "SORT", nullable = false)
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "LIST_PAGE_URL", nullable = true, length = 255)
    public String getListPageUrl() {
        return listPageUrl;
    }

    public void setListPageUrl(String listPageUrl) {
        this.listPageUrl = listPageUrl;
    }

    @Basic
    @Column(name = "DETAIL_PAGE_URL", nullable = true, length = 255)
    public String getDetailPageUrl() {
        return detailPageUrl;
    }

    public void setDetailPageUrl(String detailPageUrl) {
        this.detailPageUrl = detailPageUrl;
    }

    @Basic
    @Column(name = "SECTION_PAGE_URL", nullable = true, length = 255)
    public String getSectionPageUrl() {
        return sectionPageUrl;
    }

    public void setSectionPageUrl(String sectionPageUrl) {
        this.sectionPageUrl = sectionPageUrl;
    }

    @Basic
    @Column(name = "CANONICAL_PAGE_URL", nullable = true, length = 255)
    public String getCanonicalPageUrl() {
        return canonicalPageUrl;
    }

    public void setCanonicalPageUrl(String canonicalPageUrl) {
        this.canonicalPageUrl = canonicalPageUrl;
    }

    @Basic
    @Column(name = "PICTURE", nullable = true)
    public Integer getPicture() {
        return picture;
    }

    public void setPicture(Integer picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "DESCRIPTION_TYPE", nullable = false, length = 4)
    public String getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(String descriptionType) {
        this.descriptionType = descriptionType;
    }

    @Basic
    @Column(name = "RSS_TTL", nullable = false)
    public int getRssTtl() {
        return rssTtl;
    }

    public void setRssTtl(int rssTtl) {
        this.rssTtl = rssTtl;
    }

    @Basic
    @Column(name = "RSS_ACTIVE", nullable = false, length = 1)
    public String getRssActive() {
        return rssActive;
    }

    public void setRssActive(String rssActive) {
        this.rssActive = rssActive;
    }

    @Basic
    @Column(name = "RSS_FILE_ACTIVE", nullable = false, length = 1)
    public String getRssFileActive() {
        return rssFileActive;
    }

    public void setRssFileActive(String rssFileActive) {
        this.rssFileActive = rssFileActive;
    }

    @Basic
    @Column(name = "RSS_FILE_LIMIT", nullable = true)
    public Integer getRssFileLimit() {
        return rssFileLimit;
    }

    public void setRssFileLimit(Integer rssFileLimit) {
        this.rssFileLimit = rssFileLimit;
    }

    @Basic
    @Column(name = "RSS_FILE_DAYS", nullable = true)
    public Integer getRssFileDays() {
        return rssFileDays;
    }

    public void setRssFileDays(Integer rssFileDays) {
        this.rssFileDays = rssFileDays;
    }

    @Basic
    @Column(name = "RSS_YANDEX_ACTIVE", nullable = false, length = 1)
    public String getRssYandexActive() {
        return rssYandexActive;
    }

    public void setRssYandexActive(String rssYandexActive) {
        this.rssYandexActive = rssYandexActive;
    }

    @Basic
    @Column(name = "XML_ID", nullable = true, length = 255)
    public String getXmlId() {
        return xmlId;
    }

    public void setXmlId(String xmlId) {
        this.xmlId = xmlId;
    }

    @Basic
    @Column(name = "TMP_ID", nullable = true, length = 40)
    public String getTmpId() {
        return tmpId;
    }

    public void setTmpId(String tmpId) {
        this.tmpId = tmpId;
    }

    @Basic
    @Column(name = "INDEX_ELEMENT", nullable = false, length = 1)
    public String getIndexElement() {
        return indexElement;
    }

    public void setIndexElement(String indexElement) {
        this.indexElement = indexElement;
    }

    @Basic
    @Column(name = "INDEX_SECTION", nullable = false, length = 1)
    public String getIndexSection() {
        return indexSection;
    }

    public void setIndexSection(String indexSection) {
        this.indexSection = indexSection;
    }

    @Basic
    @Column(name = "WORKFLOW", nullable = false, length = 1)
    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    @Basic
    @Column(name = "BIZPROC", nullable = false, length = 1)
    public String getBizproc() {
        return bizproc;
    }

    public void setBizproc(String bizproc) {
        this.bizproc = bizproc;
    }

    @Basic
    @Column(name = "SECTION_CHOOSER", nullable = true, length = 1)
    public String getSectionChooser() {
        return sectionChooser;
    }

    public void setSectionChooser(String sectionChooser) {
        this.sectionChooser = sectionChooser;
    }

    @Basic
    @Column(name = "LIST_MODE", nullable = true, length = 1)
    public String getListMode() {
        return listMode;
    }

    public void setListMode(String listMode) {
        this.listMode = listMode;
    }

    @Basic
    @Column(name = "RIGHTS_MODE", nullable = true, length = 1)
    public String getRightsMode() {
        return rightsMode;
    }

    public void setRightsMode(String rightsMode) {
        this.rightsMode = rightsMode;
    }

    @Basic
    @Column(name = "SECTION_PROPERTY", nullable = true, length = 1)
    public String getSectionProperty() {
        return sectionProperty;
    }

    public void setSectionProperty(String sectionProperty) {
        this.sectionProperty = sectionProperty;
    }

    @Basic
    @Column(name = "PROPERTY_INDEX", nullable = true, length = 1)
    public String getPropertyIndex() {
        return propertyIndex;
    }

    public void setPropertyIndex(String propertyIndex) {
        this.propertyIndex = propertyIndex;
    }

    @Basic
    @Column(name = "VERSION", nullable = false)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Basic
    @Column(name = "LAST_CONV_ELEMENT", nullable = false)
    public int getLastConvElement() {
        return lastConvElement;
    }

    public void setLastConvElement(int lastConvElement) {
        this.lastConvElement = lastConvElement;
    }

    @Basic
    @Column(name = "SOCNET_GROUP_ID", nullable = true)
    public Integer getSocnetGroupId() {
        return socnetGroupId;
    }

    public void setSocnetGroupId(Integer socnetGroupId) {
        this.socnetGroupId = socnetGroupId;
    }

    @Basic
    @Column(name = "EDIT_FILE_BEFORE", nullable = true, length = 255)
    public String getEditFileBefore() {
        return editFileBefore;
    }

    public void setEditFileBefore(String editFileBefore) {
        this.editFileBefore = editFileBefore;
    }

    @Basic
    @Column(name = "EDIT_FILE_AFTER", nullable = true, length = 255)
    public String getEditFileAfter() {
        return editFileAfter;
    }

    public void setEditFileAfter(String editFileAfter) {
        this.editFileAfter = editFileAfter;
    }

    @Basic
    @Column(name = "SECTIONS_NAME", nullable = true, length = 100)
    public String getSectionsName() {
        return sectionsName;
    }

    public void setSectionsName(String sectionsName) {
        this.sectionsName = sectionsName;
    }

    @Basic
    @Column(name = "SECTION_NAME", nullable = true, length = 100)
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Basic
    @Column(name = "ELEMENTS_NAME", nullable = true, length = 100)
    public String getElementsName() {
        return elementsName;
    }

    public void setElementsName(String elementsName) {
        this.elementsName = elementsName;
    }

    @Basic
    @Column(name = "ELEMENT_NAME", nullable = true, length = 100)
    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BIblock bIblock = (BIblock) o;
        return id == bIblock.id && sort == bIblock.sort && rssTtl == bIblock.rssTtl && version == bIblock.version && lastConvElement == bIblock.lastConvElement && Objects.equals(timestampX, bIblock.timestampX) && Objects.equals(iblockTypeId, bIblock.iblockTypeId) && Objects.equals(lid, bIblock.lid) && Objects.equals(code, bIblock.code) && Objects.equals(apiCode, bIblock.apiCode) && Objects.equals(name, bIblock.name) && Objects.equals(active, bIblock.active) && Objects.equals(listPageUrl, bIblock.listPageUrl) && Objects.equals(detailPageUrl, bIblock.detailPageUrl) && Objects.equals(sectionPageUrl, bIblock.sectionPageUrl) && Objects.equals(canonicalPageUrl, bIblock.canonicalPageUrl) && Objects.equals(picture, bIblock.picture) && Objects.equals(description, bIblock.description) && Objects.equals(descriptionType, bIblock.descriptionType) && Objects.equals(rssActive, bIblock.rssActive) && Objects.equals(rssFileActive, bIblock.rssFileActive) && Objects.equals(rssFileLimit, bIblock.rssFileLimit) && Objects.equals(rssFileDays, bIblock.rssFileDays) && Objects.equals(rssYandexActive, bIblock.rssYandexActive) && Objects.equals(xmlId, bIblock.xmlId) && Objects.equals(tmpId, bIblock.tmpId) && Objects.equals(indexElement, bIblock.indexElement) && Objects.equals(indexSection, bIblock.indexSection) && Objects.equals(workflow, bIblock.workflow) && Objects.equals(bizproc, bIblock.bizproc) && Objects.equals(sectionChooser, bIblock.sectionChooser) && Objects.equals(listMode, bIblock.listMode) && Objects.equals(rightsMode, bIblock.rightsMode) && Objects.equals(sectionProperty, bIblock.sectionProperty) && Objects.equals(propertyIndex, bIblock.propertyIndex) && Objects.equals(socnetGroupId, bIblock.socnetGroupId) && Objects.equals(editFileBefore, bIblock.editFileBefore) && Objects.equals(editFileAfter, bIblock.editFileAfter) && Objects.equals(sectionsName, bIblock.sectionsName) && Objects.equals(sectionName, bIblock.sectionName) && Objects.equals(elementsName, bIblock.elementsName) && Objects.equals(elementName, bIblock.elementName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestampX, iblockTypeId, lid, code, apiCode, name, active, sort, listPageUrl, detailPageUrl, sectionPageUrl, canonicalPageUrl, picture, description, descriptionType, rssTtl, rssActive, rssFileActive, rssFileLimit, rssFileDays, rssYandexActive, xmlId, tmpId, indexElement, indexSection, workflow, bizproc, sectionChooser, listMode, rightsMode, sectionProperty, propertyIndex, version, lastConvElement, socnetGroupId, editFileBefore, editFileAfter, sectionsName, sectionName, elementsName, elementName);
    }
}

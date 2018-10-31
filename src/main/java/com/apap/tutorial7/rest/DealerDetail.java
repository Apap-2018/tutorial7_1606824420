package com.apap.tutorial7.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DealerDetail {
 private String status;
 
 @JsonProperty("building-license")
 private Integer buildingLicense;
 
 @JsonProperty("valid-until")
 private Integer validUntil;
 
 public String getStatus() {
  return status;
 }

 public Integer getBuildingLicense() {
  return buildingLicense;
 }

 public void setBuildingLicense(Integer buildingLicense) {
  this.buildingLicense = buildingLicense;
 }

 public Integer getValidUntil() {
  return validUntil;
 }

 public void setValidUntil(Integer validUntil) {
  this.validUntil = validUntil;
 }

 public void setStatus(String status) {
  this.status = status;
 }
}
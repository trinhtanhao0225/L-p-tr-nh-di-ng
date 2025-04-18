package com.example.listview;

public class MonHoc {
 private String name;
 private String desc;

 public MonHoc(String name, String desc, int pic) {
  this.name = name;
  this.desc = desc;
  this.pic = pic;
 }

 public void setName(String name) {
  this.name = name;
 }

 public void setDesc(String desc) {
  this.desc = desc;
 }

 public void setPic(int pic) {
  this.pic = pic;
 }

 public String getName() {
  return name;
 }

 public String getDesc() {
  return desc;
 }

 public int getPic() {
  return pic;
 }

 private int pic;
}
package com.tconnect.entity;

import java.util.Arrays;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection="test")
public class TestEntity {
	@Id
	@JsonIgnore
	private ObjectId _id;
	
	@Transient
	private String _class;
	
	public String get_class() {
		return _class;
	}
	public void set_class(String _class) {
		this._class = _class;
	}
	private String name;
//	private String roll;
	private int[] marks;
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public String getRoll() {
//		return roll;
//	}
//	public void setRoll(String roll) {
//		this.roll = roll;
//	}
	public int[] getMarks() {
		return marks;
	}
	public void setMarks(int[] marks) {
		this.marks = marks;
	}
	@Override
	public String toString() {
		return "TestEntity [_id=" + _id + ", name=" + name + ", marks=" + Arrays.toString(marks) + "]";
	}
	
	
}

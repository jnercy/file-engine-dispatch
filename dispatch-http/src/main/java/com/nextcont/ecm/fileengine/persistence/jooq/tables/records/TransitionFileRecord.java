/**
 * This class is generated by jOOQ
 */
package com.nextcont.ecm.fileengine.persistence.jooq.tables.records;


import com.nextcont.ecm.fileengine.persistence.jooq.tables.TransitionFile;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TransitionFileRecord extends TableRecordImpl<TransitionFileRecord> implements Record8<String, String, String, Long, String, String, String, String> {

	private static final long serialVersionUID = 1587142478;

	/**
	 * Setter for <code>ecm.transition_file.globalId</code>. 杞崲id锛屽搴旇〃transition涓璯lobalid
	 */
	public void setGlobalid(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ecm.transition_file.globalId</code>. 杞崲id锛屽搴旇〃transition涓璯lobalid
	 */
	public String getGlobalid() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>ecm.transition_file.url</code>. 返回给caller的文件url
	 */
	public void setUrl(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ecm.transition_file.url</code>. 返回给caller的文件url
	 */
	public String getUrl() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>ecm.transition_file.type</code>. 转换的文件类型:缩略图|预览图|主文件
	 */
	public void setType(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>ecm.transition_file.type</code>. 转换的文件类型:缩略图|预览图|主文件
	 */
	public String getType() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>ecm.transition_file.duration</code>. 转换用时
	 */
	public void setDuration(Long value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>ecm.transition_file.duration</code>. 转换用时
	 */
	public Long getDuration() {
		return (Long) getValue(3);
	}

	/**
	 * Setter for <code>ecm.transition_file.location</code>. 存放位置：本地|Cloud
	 */
	public void setLocation(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>ecm.transition_file.location</code>. 存放位置：本地|Cloud
	 */
	public String getLocation() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>ecm.transition_file.fullpath</code>. 对应存放位置的完整路径
	 */
	public void setFullpath(String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>ecm.transition_file.fullpath</code>. 对应存放位置的完整路径
	 */
	public String getFullpath() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>ecm.transition_file.status</code>. 状态
	 */
	public void setStatus(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>ecm.transition_file.status</code>. 状态
	 */
	public String getStatus() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>ecm.transition_file.errormsg</code>. 错误信息
	 */
	public void setErrormsg(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>ecm.transition_file.errormsg</code>. 错误信息
	 */
	public String getErrormsg() {
		return (String) getValue(7);
	}

	// -------------------------------------------------------------------------
	// Record8 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row8<String, String, String, Long, String, String, String, String> fieldsRow() {
		return (Row8) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row8<String, String, String, Long, String, String, String, String> valuesRow() {
		return (Row8) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return TransitionFile.TRANSITION_FILE.GLOBALID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return TransitionFile.TRANSITION_FILE.URL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return TransitionFile.TRANSITION_FILE.TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field4() {
		return TransitionFile.TRANSITION_FILE.DURATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return TransitionFile.TRANSITION_FILE.LOCATION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field6() {
		return TransitionFile.TRANSITION_FILE.FULLPATH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return TransitionFile.TRANSITION_FILE.STATUS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field8() {
		return TransitionFile.TRANSITION_FILE.ERRORMSG;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getGlobalid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getUrl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value4() {
		return getDuration();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getLocation();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value6() {
		return getFullpath();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getStatus();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value8() {
		return getErrormsg();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionFileRecord value1(String value) {
		setGlobalid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionFileRecord value2(String value) {
		setUrl(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionFileRecord value3(String value) {
		setType(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionFileRecord value4(Long value) {
		setDuration(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionFileRecord value5(String value) {
		setLocation(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionFileRecord value6(String value) {
		setFullpath(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionFileRecord value7(String value) {
		setStatus(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionFileRecord value8(String value) {
		setErrormsg(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionFileRecord values(String value1, String value2, String value3, Long value4, String value5, String value6, String value7, String value8) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TransitionFileRecord
	 */
	public TransitionFileRecord() {
		super(TransitionFile.TRANSITION_FILE);
	}

	/**
	 * Create a detached, initialised TransitionFileRecord
	 */
	public TransitionFileRecord(String globalid, String url, String type, Long duration, String location, String fullpath, String status, String errormsg) {
		super(TransitionFile.TRANSITION_FILE);

		setValue(0, globalid);
		setValue(1, url);
		setValue(2, type);
		setValue(3, duration);
		setValue(4, location);
		setValue(5, fullpath);
		setValue(6, status);
		setValue(7, errormsg);
	}
}

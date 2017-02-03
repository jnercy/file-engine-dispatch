/**
 * This class is generated by jOOQ
 */
package com.nextcont.ecm.fileengine.persistence.jooq.tables.records;


import com.nextcont.ecm.fileengine.persistence.jooq.tables.Transition;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;


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
public class TransitionRecord extends UpdatableRecordImpl<TransitionRecord> implements Record12<String, Timestamp, String, String, String, Long, String, String, String, String, String, Integer> {

	private static final long serialVersionUID = -1857747794;

	/**
	 * Setter for <code>ecm.transition.globalId</code>. 请求id
	 */
	public void setGlobalid(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ecm.transition.globalId</code>. 请求id
	 */
	public String getGlobalid() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>ecm.transition.createdate</code>. 创建时间
	 */
	public void setCreatedate(Timestamp value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ecm.transition.createdate</code>. 创建时间
	 */
	public Timestamp getCreatedate() {
		return (Timestamp) getValue(1);
	}

	/**
	 * Setter for <code>ecm.transition.requesttype</code>. 请求类型:HTTP|WEBSOCKET
	 */
	public void setRequesttype(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>ecm.transition.requesttype</code>. 请求类型:HTTP|WEBSOCKET
	 */
	public String getRequesttype() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>ecm.transition.clientid</code>. caller client id
	 */
	public void setClientid(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>ecm.transition.clientid</code>. caller client id
	 */
	public String getClientid() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>ecm.transition.filename</code>. 文件名称
	 */
	public void setFilename(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>ecm.transition.filename</code>. 文件名称
	 */
	public String getFilename() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>ecm.transition.length</code>. 文件长度
	 */
	public void setLength(Long value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>ecm.transition.length</code>. 文件长度
	 */
	public Long getLength() {
		return (Long) getValue(5);
	}

	/**
	 * Setter for <code>ecm.transition.mimetype</code>. 文件mime-type
	 */
	public void setMimetype(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>ecm.transition.mimetype</code>. 文件mime-type
	 */
	public String getMimetype() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>ecm.transition.source</code>. 文件来源:HTTP URL|FTP|FILE UPLOAD|CHUNCKED FILE UPLOAD
	 */
	public void setSource(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>ecm.transition.source</code>. 文件来源:HTTP URL|FTP|FILE UPLOAD|CHUNCKED FILE UPLOAD
	 */
	public String getSource() {
		return (String) getValue(7);
	}

	/**
	 * Setter for <code>ecm.transition.callbackurl</code>. 回调URL
	 */
	public void setCallbackurl(String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>ecm.transition.callbackurl</code>. 回调URL
	 */
	public String getCallbackurl() {
		return (String) getValue(8);
	}

	/**
	 * Setter for <code>ecm.transition.md5digest</code>. 文件的md5摘要
	 */
	public void setMd5digest(String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>ecm.transition.md5digest</code>. 文件的md5摘要
	 */
	public String getMd5digest() {
		return (String) getValue(9);
	}

	/**
	 * Setter for <code>ecm.transition.alijobid</code>. 阿里转码jobid
	 */
	public void setAlijobid(String value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>ecm.transition.alijobid</code>. 阿里转码jobid
	 */
	public String getAlijobid() {
		return (String) getValue(10);
	}

	/**
	 * Setter for <code>ecm.transition.filesize</code>.
	 */
	public void setFilesize(Integer value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>ecm.transition.filesize</code>.
	 */
	public Integer getFilesize() {
		return (Integer) getValue(11);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<String> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record12 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row12<String, Timestamp, String, String, String, Long, String, String, String, String, String, Integer> fieldsRow() {
		return (Row12) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row12<String, Timestamp, String, String, String, Long, String, String, String, String, String, Integer> valuesRow() {
		return (Row12) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return Transition.TRANSITION.GLOBALID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field2() {
		return Transition.TRANSITION.CREATEDATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Transition.TRANSITION.REQUESTTYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return Transition.TRANSITION.CLIENTID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return Transition.TRANSITION.FILENAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Long> field6() {
		return Transition.TRANSITION.LENGTH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return Transition.TRANSITION.MIMETYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field8() {
		return Transition.TRANSITION.SOURCE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field9() {
		return Transition.TRANSITION.CALLBACKURL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field10() {
		return Transition.TRANSITION.MD5DIGEST;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field11() {
		return Transition.TRANSITION.ALIJOBID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field12() {
		return Transition.TRANSITION.FILESIZE;
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
	public Timestamp value2() {
		return getCreatedate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getRequesttype();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getClientid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getFilename();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long value6() {
		return getLength();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getMimetype();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value8() {
		return getSource();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value9() {
		return getCallbackurl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value10() {
		return getMd5digest();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value11() {
		return getAlijobid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value12() {
		return getFilesize();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value1(String value) {
		setGlobalid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value2(Timestamp value) {
		setCreatedate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value3(String value) {
		setRequesttype(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value4(String value) {
		setClientid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value5(String value) {
		setFilename(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value6(Long value) {
		setLength(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value7(String value) {
		setMimetype(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value8(String value) {
		setSource(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value9(String value) {
		setCallbackurl(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value10(String value) {
		setMd5digest(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value11(String value) {
		setAlijobid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord value12(Integer value) {
		setFilesize(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionRecord values(String value1, Timestamp value2, String value3, String value4, String value5, Long value6, String value7, String value8, String value9, String value10, String value11, Integer value12) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		value10(value10);
		value11(value11);
		value12(value12);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TransitionRecord
	 */
	public TransitionRecord() {
		super(Transition.TRANSITION);
	}

	/**
	 * Create a detached, initialised TransitionRecord
	 */
	public TransitionRecord(String globalid, Timestamp createdate, String requesttype, String clientid, String filename, Long length, String mimetype, String source, String callbackurl, String md5digest, String alijobid, Integer filesize) {
		super(Transition.TRANSITION);

		setValue(0, globalid);
		setValue(1, createdate);
		setValue(2, requesttype);
		setValue(3, clientid);
		setValue(4, filename);
		setValue(5, length);
		setValue(6, mimetype);
		setValue(7, source);
		setValue(8, callbackurl);
		setValue(9, md5digest);
		setValue(10, alijobid);
		setValue(11, filesize);
	}
}
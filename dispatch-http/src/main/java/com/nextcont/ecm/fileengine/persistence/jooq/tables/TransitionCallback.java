/**
 * This class is generated by jOOQ
 */
package com.nextcont.ecm.fileengine.persistence.jooq.tables;


import com.nextcont.ecm.fileengine.persistence.jooq.Ecm;
import com.nextcont.ecm.fileengine.persistence.jooq.tables.records.TransitionCallbackRecord;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;


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
public class TransitionCallback extends TableImpl<TransitionCallbackRecord> {

	private static final long serialVersionUID = 196390244;

	/**
	 * The reference instance of <code>ecm.transition_callback</code>
	 */
	public static final TransitionCallback TRANSITION_CALLBACK = new TransitionCallback();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<TransitionCallbackRecord> getRecordType() {
		return TransitionCallbackRecord.class;
	}

	/**
	 * The column <code>ecm.transition_callback.globalId</code>.
	 */
	public final TableField<TransitionCallbackRecord, String> GLOBALID = createField("globalId", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");

	/**
	 * The column <code>ecm.transition_callback.status</code>.
	 */
	public final TableField<TransitionCallbackRecord, String> STATUS = createField("status", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false), this, "");

	/**
	 * The column <code>ecm.transition_callback.info</code>.
	 */
	public final TableField<TransitionCallbackRecord, String> INFO = createField("info", org.jooq.impl.SQLDataType.VARCHAR.length(1000).nullable(false), this, "");

	/**
	 * The column <code>ecm.transition_callback.callbackUrl</code>.
	 */
	public final TableField<TransitionCallbackRecord, String> CALLBACKURL = createField("callbackUrl", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

	/**
	 * The column <code>ecm.transition_callback.createTime</code>.
	 */
	public final TableField<TransitionCallbackRecord, Timestamp> CREATETIME = createField("createTime", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

	/**
	 * Create a <code>ecm.transition_callback</code> table reference
	 */
	public TransitionCallback() {
		this("transition_callback", null);
	}

	/**
	 * Create an aliased <code>ecm.transition_callback</code> table reference
	 */
	public TransitionCallback(String alias) {
		this(alias, TRANSITION_CALLBACK);
	}

	private TransitionCallback(String alias, Table<TransitionCallbackRecord> aliased) {
		this(alias, aliased, null);
	}

	private TransitionCallback(String alias, Table<TransitionCallbackRecord> aliased, Field<?>[] parameters) {
		super(alias, Ecm.ECM, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransitionCallback as(String alias) {
		return new TransitionCallback(alias, this);
	}

	/**
	 * Rename this table
	 */
	public TransitionCallback rename(String name) {
		return new TransitionCallback(name, null);
	}
}

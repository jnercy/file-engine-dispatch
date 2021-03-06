/**
 * This class is generated by jOOQ
 */
package com.nextcont.ecm.fileengine.persistence.jooq;


import com.nextcont.ecm.fileengine.persistence.jooq.tables.FastdfsFilerecord;
import com.nextcont.ecm.fileengine.persistence.jooq.tables.Meqaupload;
import com.nextcont.ecm.fileengine.persistence.jooq.tables.Transition;
import com.nextcont.ecm.fileengine.persistence.jooq.tables.records.FastdfsFilerecordRecord;
import com.nextcont.ecm.fileengine.persistence.jooq.tables.records.MeqauploadRecord;
import com.nextcont.ecm.fileengine.persistence.jooq.tables.records.TransitionRecord;

import javax.annotation.Generated;

import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>ecm</code> 
 * schema
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final UniqueKey<FastdfsFilerecordRecord> KEY_FASTDFS_FILERECORD_PRIMARY = UniqueKeys0.KEY_FASTDFS_FILERECORD_PRIMARY;
	public static final UniqueKey<MeqauploadRecord> KEY_MEQAUPLOAD_PRIMARY = UniqueKeys0.KEY_MEQAUPLOAD_PRIMARY;
	public static final UniqueKey<TransitionRecord> KEY_TRANSITION_PRIMARY = UniqueKeys0.KEY_TRANSITION_PRIMARY;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class UniqueKeys0 extends AbstractKeys {
		public static final UniqueKey<FastdfsFilerecordRecord> KEY_FASTDFS_FILERECORD_PRIMARY = createUniqueKey(FastdfsFilerecord.FASTDFS_FILERECORD, FastdfsFilerecord.FASTDFS_FILERECORD.GLOBALID);
		public static final UniqueKey<MeqauploadRecord> KEY_MEQAUPLOAD_PRIMARY = createUniqueKey(Meqaupload.MEQAUPLOAD, Meqaupload.MEQAUPLOAD.GLOBALID);
		public static final UniqueKey<TransitionRecord> KEY_TRANSITION_PRIMARY = createUniqueKey(Transition.TRANSITION, Transition.TRANSITION.GLOBALID);
	}
}

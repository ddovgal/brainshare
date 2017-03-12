/*
 * This file is generated by jOOQ.
*/
package ua.ddovgal.brainshareApi.jooq.tables;


import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.jooq.types.ULong;
import ua.ddovgal.brainshareApi.jooq.BrainshareDev;
import ua.ddovgal.brainshareApi.jooq.Keys;
import ua.ddovgal.brainshareApi.jooq.tables.records.RNotificationWithoutReasonReferenceRecord;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.9.1"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class TNotificationWithoutReasonReference extends TableImpl<RNotificationWithoutReasonReferenceRecord> {

    /**
     * The reference instance of <code>brainshare_dev.notification_without_reason_reference</code>
     */
    public static final TNotificationWithoutReasonReference NOTIFICATION_WITHOUT_REASON_REFERENCE = new TNotificationWithoutReasonReference();
    private static final long serialVersionUID = -1566760752;
    /**
     * The column <code>brainshare_dev.notification_without_reason_reference.notification_id</code>.
     */
    public final TableField<RNotificationWithoutReasonReferenceRecord, ULong> NOTIFICATION_ID = createField("notification_id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.notification_without_reason_reference.reason_data_json</code>.
     */
    public final TableField<RNotificationWithoutReasonReferenceRecord, Object> REASON_DATA_JSON = createField("reason_data_json", org.jooq.impl.DefaultDataType.getDefaultDataType("json"), this, "");

    /**
     * Create a <code>brainshare_dev.notification_without_reason_reference</code> table reference
     */
    public TNotificationWithoutReasonReference() {
        this("notification_without_reason_reference", null);
    }

    /**
     * Create an aliased <code>brainshare_dev.notification_without_reason_reference</code> table reference
     */
    public TNotificationWithoutReasonReference(String alias) {
        this(alias, NOTIFICATION_WITHOUT_REASON_REFERENCE);
    }

    private TNotificationWithoutReasonReference(String alias, Table<RNotificationWithoutReasonReferenceRecord> aliased) {
        this(alias, aliased, null);
    }

    private TNotificationWithoutReasonReference(String alias, Table<RNotificationWithoutReasonReferenceRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RNotificationWithoutReasonReferenceRecord> getRecordType() {
        return RNotificationWithoutReasonReferenceRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return BrainshareDev.BRAINSHARE_DEV;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RNotificationWithoutReasonReferenceRecord> getPrimaryKey() {
        return Keys.KEY_NOTIFICATION_WITHOUT_REASON_REFERENCE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RNotificationWithoutReasonReferenceRecord>> getKeys() {
        return Arrays.<UniqueKey<RNotificationWithoutReasonReferenceRecord>>asList(Keys.KEY_NOTIFICATION_WITHOUT_REASON_REFERENCE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<RNotificationWithoutReasonReferenceRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RNotificationWithoutReasonReferenceRecord, ?>>asList(Keys.FK_NOTIFICATION_WITHOUT_REASON_REFERENCE_NOTIFICATION_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TNotificationWithoutReasonReference as(String alias) {
        return new TNotificationWithoutReasonReference(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TNotificationWithoutReasonReference rename(String name) {
        return new TNotificationWithoutReasonReference(name, null);
    }
}
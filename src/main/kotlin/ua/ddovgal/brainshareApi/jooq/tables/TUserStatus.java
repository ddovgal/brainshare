/*
 * This file is generated by jOOQ.
*/
package ua.ddovgal.brainshareApi.jooq.tables;


import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;
import ua.ddovgal.brainshareApi.jooq.BrainshareDev;
import ua.ddovgal.brainshareApi.jooq.Keys;
import ua.ddovgal.brainshareApi.jooq.tables.records.RUserStatusRecord;

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
public class TUserStatus extends TableImpl<RUserStatusRecord> {

    /**
     * The reference instance of <code>brainshare_dev.user_status</code>
     */
    public static final TUserStatus USER_STATUS = new TUserStatus();
    private static final long serialVersionUID = -2103095569;
    /**
     * The column <code>brainshare_dev.user_status.id</code>.
     */
    public final TableField<RUserStatusRecord, UInteger> ID = createField("id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.user_status.status_name</code>.
     */
    public final TableField<RUserStatusRecord, String> STATUS_NAME = createField("status_name", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.user_status.description</code>.
     */
    public final TableField<RUserStatusRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.user_status.low_border</code>.
     */
    public final TableField<RUserStatusRecord, Integer> LOW_BORDER = createField("low_border", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>brainshare_dev.user_status</code> table reference
     */
    public TUserStatus() {
        this("user_status", null);
    }

    /**
     * Create an aliased <code>brainshare_dev.user_status</code> table reference
     */
    public TUserStatus(String alias) {
        this(alias, USER_STATUS);
    }

    private TUserStatus(String alias, Table<RUserStatusRecord> aliased) {
        this(alias, aliased, null);
    }

    private TUserStatus(String alias, Table<RUserStatusRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RUserStatusRecord> getRecordType() {
        return RUserStatusRecord.class;
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
    public UniqueKey<RUserStatusRecord> getPrimaryKey() {
        return Keys.KEY_USER_STATUS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RUserStatusRecord>> getKeys() {
        return Arrays.<UniqueKey<RUserStatusRecord>>asList(Keys.KEY_USER_STATUS_PRIMARY, Keys.KEY_USER_STATUS_STATUS_NAME_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserStatus as(String alias) {
        return new TUserStatus(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TUserStatus rename(String name) {
        return new TUserStatus(name, null);
    }
}
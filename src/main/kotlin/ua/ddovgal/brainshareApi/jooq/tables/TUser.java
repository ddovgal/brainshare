/*
 * This file is generated by jOOQ.
*/
package ua.ddovgal.brainshareApi.jooq.tables;


import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;
import org.jooq.types.ULong;
import ua.ddovgal.brainshareApi.jooq.BrainshareDev;
import ua.ddovgal.brainshareApi.jooq.Keys;
import ua.ddovgal.brainshareApi.jooq.tables.records.RUserRecord;

import javax.annotation.Generated;
import java.sql.Date;
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
public class TUser extends TableImpl<RUserRecord> {

    /**
     * The reference instance of <code>brainshare_dev.user</code>
     */
    public static final TUser USER = new TUser();
    private static final long serialVersionUID = 1257006767;
    /**
     * The column <code>brainshare_dev.user.id</code>.
     */
    public final TableField<RUserRecord, ULong> ID = createField("id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.user.first_name</code>.
     */
    public final TableField<RUserRecord, String> FIRST_NAME = createField("first_name", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");
    /**
     * The column <code>brainshare_dev.user.last_name</code>.
     */
    public final TableField<RUserRecord, String> LAST_NAME = createField("last_name", org.jooq.impl.SQLDataType.VARCHAR.length(50), this, "");
    /**
     * The column <code>brainshare_dev.user.avatar</code>.
     */
    public final TableField<RUserRecord, ULong> AVATAR = createField("avatar", org.jooq.impl.SQLDataType.BIGINTUNSIGNED, this, "");
    /**
     * The column <code>brainshare_dev.user.email</code>.
     */
    public final TableField<RUserRecord, String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.user.login</code>.
     */
    public final TableField<RUserRecord, String> LOGIN = createField("login", org.jooq.impl.SQLDataType.VARCHAR.length(50).nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.user.password</code>.
     */
    public final TableField<RUserRecord, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.user.registration_date</code>.
     */
    public final TableField<RUserRecord, Date> REGISTRATION_DATE = createField("registration_date", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.user.social_links_json</code>.
     */
    public final TableField<RUserRecord, Object> SOCIAL_LINKS_JSON = createField("social_links_json", org.jooq.impl.DefaultDataType.getDefaultDataType("json"), this, "");
    /**
     * The column <code>brainshare_dev.user.ratio</code>.
     */
    public final TableField<RUserRecord, Double> RATIO = createField("ratio", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.user.penaly_count</code>.
     */
    public final TableField<RUserRecord, Boolean> PENALY_COUNT = createField("penaly_count", org.jooq.impl.SQLDataType.BIT.nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.user.status_id</code>.
     */
    public final TableField<RUserRecord, UInteger> STATUS_ID = createField("status_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * Create a <code>brainshare_dev.user</code> table reference
     */
    public TUser() {
        this("user", null);
    }

    /**
     * Create an aliased <code>brainshare_dev.user</code> table reference
     */
    public TUser(String alias) {
        this(alias, USER);
    }

    private TUser(String alias, Table<RUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private TUser(String alias, Table<RUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RUserRecord> getRecordType() {
        return RUserRecord.class;
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
    public Identity<RUserRecord, ULong> getIdentity() {
        return Keys.IDENTITY_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RUserRecord> getPrimaryKey() {
        return Keys.KEY_USER_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RUserRecord>> getKeys() {
        return Arrays.<UniqueKey<RUserRecord>>asList(Keys.KEY_USER_PRIMARY, Keys.KEY_USER_AVATAR_UNIQUE, Keys.KEY_USER_EMAIL_UNIQUE, Keys.KEY_USER_LOGIN_UNIQUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<RUserRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RUserRecord, ?>>asList(Keys.FK_USER_AVATAR_ID, Keys.FK_USER_STATUS_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUser as(String alias) {
        return new TUser(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TUser rename(String name) {
        return new TUser(name, null);
    }
}
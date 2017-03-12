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
import ua.ddovgal.brainshareApi.jooq.tables.records.RInCommentaryModeratorRequestRecord;

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
public class TInCommentaryModeratorRequest extends TableImpl<RInCommentaryModeratorRequestRecord> {

    /**
     * The reference instance of <code>brainshare_dev.in_commentary_moderator_request</code>
     */
    public static final TInCommentaryModeratorRequest IN_COMMENTARY_MODERATOR_REQUEST = new TInCommentaryModeratorRequest();
    private static final long serialVersionUID = 1713235264;
    /**
     * The column <code>brainshare_dev.in_commentary_moderator_request.id</code>.
     */
    public final TableField<RInCommentaryModeratorRequestRecord, ULong> ID = createField("id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.in_commentary_moderator_request.conmmentary_id</code>.
     */
    public final TableField<RInCommentaryModeratorRequestRecord, ULong> CONMMENTARY_ID = createField("conmmentary_id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.in_commentary_moderator_request.material_id</code>.
     */
    public final TableField<RInCommentaryModeratorRequestRecord, ULong> MATERIAL_ID = createField("material_id", org.jooq.impl.SQLDataType.BIGINTUNSIGNED.nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.in_commentary_moderator_request.section_of_material_id</code>.
     */
    public final TableField<RInCommentaryModeratorRequestRecord, UInteger> SECTION_OF_MATERIAL_ID = createField("section_of_material_id", org.jooq.impl.SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");
    /**
     * The column <code>brainshare_dev.in_commentary_moderator_request.is_concidered_by_someone</code>.
     */
    public final TableField<RInCommentaryModeratorRequestRecord, Byte> IS_CONCIDERED_BY_SOMEONE = createField("is_concidered_by_someone", org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "");

    /**
     * Create a <code>brainshare_dev.in_commentary_moderator_request</code> table reference
     */
    public TInCommentaryModeratorRequest() {
        this("in_commentary_moderator_request", null);
    }

    /**
     * Create an aliased <code>brainshare_dev.in_commentary_moderator_request</code> table reference
     */
    public TInCommentaryModeratorRequest(String alias) {
        this(alias, IN_COMMENTARY_MODERATOR_REQUEST);
    }

    private TInCommentaryModeratorRequest(String alias, Table<RInCommentaryModeratorRequestRecord> aliased) {
        this(alias, aliased, null);
    }

    private TInCommentaryModeratorRequest(String alias, Table<RInCommentaryModeratorRequestRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RInCommentaryModeratorRequestRecord> getRecordType() {
        return RInCommentaryModeratorRequestRecord.class;
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
    public Identity<RInCommentaryModeratorRequestRecord, ULong> getIdentity() {
        return Keys.IDENTITY_IN_COMMENTARY_MODERATOR_REQUEST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RInCommentaryModeratorRequestRecord> getPrimaryKey() {
        return Keys.KEY_IN_COMMENTARY_MODERATOR_REQUEST_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RInCommentaryModeratorRequestRecord>> getKeys() {
        return Arrays.<UniqueKey<RInCommentaryModeratorRequestRecord>>asList(Keys.KEY_IN_COMMENTARY_MODERATOR_REQUEST_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<RInCommentaryModeratorRequestRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RInCommentaryModeratorRequestRecord, ?>>asList(Keys.FK_IN_COMMENTARY_MODERATOR_REQUEST_COMMENTARY_ID, Keys.FK_IN_COMMENTARY_MODERATOR_REQUEST_MATERIAL_ID, Keys.FK_IN_COMMENTARY_MODERATOR_REQUEST_SECTION_OF_MATERIAL_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TInCommentaryModeratorRequest as(String alias) {
        return new TInCommentaryModeratorRequest(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TInCommentaryModeratorRequest rename(String name) {
        return new TInCommentaryModeratorRequest(name, null);
    }
}
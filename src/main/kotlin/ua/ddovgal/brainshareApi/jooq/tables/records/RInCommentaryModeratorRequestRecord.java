/*
 * This file is generated by jOOQ.
*/
package ua.ddovgal.brainshareApi.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;
import org.jooq.types.ULong;
import ua.ddovgal.brainshareApi.jooq.tables.TInCommentaryModeratorRequest;

import javax.annotation.Generated;


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
public class RInCommentaryModeratorRequestRecord extends UpdatableRecordImpl<RInCommentaryModeratorRequestRecord> implements Record5<ULong, ULong, ULong, UInteger, Byte> {

    private static final long serialVersionUID = -336396214;

    /**
     * Create a detached RInCommentaryModeratorRequestRecord
     */
    public RInCommentaryModeratorRequestRecord() {
        super(TInCommentaryModeratorRequest.IN_COMMENTARY_MODERATOR_REQUEST);
    }

    /**
     * Create a detached, initialised RInCommentaryModeratorRequestRecord
     */
    public RInCommentaryModeratorRequestRecord(ULong id, ULong conmmentaryId, ULong materialId, UInteger sectionOfMaterialId, Byte isConcideredBySomeone) {
        super(TInCommentaryModeratorRequest.IN_COMMENTARY_MODERATOR_REQUEST);

        set(0, id);
        set(1, conmmentaryId);
        set(2, materialId);
        set(3, sectionOfMaterialId);
        set(4, isConcideredBySomeone);
    }

    /**
     * Getter for <code>brainshare_dev.in_commentary_moderator_request.id</code>.
     */
    public ULong getId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>brainshare_dev.in_commentary_moderator_request.id</code>.
     */
    public void setId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>brainshare_dev.in_commentary_moderator_request.conmmentary_id</code>.
     */
    public ULong getConmmentaryId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>brainshare_dev.in_commentary_moderator_request.conmmentary_id</code>.
     */
    public void setConmmentaryId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>brainshare_dev.in_commentary_moderator_request.material_id</code>.
     */
    public ULong getMaterialId() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>brainshare_dev.in_commentary_moderator_request.material_id</code>.
     */
    public void setMaterialId(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>brainshare_dev.in_commentary_moderator_request.section_of_material_id</code>.
     */
    public UInteger getSectionOfMaterialId() {
        return (UInteger) get(3);
    }

    /**
     * Setter for <code>brainshare_dev.in_commentary_moderator_request.section_of_material_id</code>.
     */
    public void setSectionOfMaterialId(UInteger value) {
        set(3, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>brainshare_dev.in_commentary_moderator_request.is_concidered_by_someone</code>.
     */
    public Byte getIsConcideredBySomeone() {
        return (Byte) get(4);
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>brainshare_dev.in_commentary_moderator_request.is_concidered_by_someone</code>.
     */
    public void setIsConcideredBySomeone(Byte value) {
        set(4, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<ULong> key() {
        return (Record1) super.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<ULong, ULong, ULong, UInteger, Byte> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<ULong, ULong, ULong, UInteger, Byte> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field1() {
        return TInCommentaryModeratorRequest.IN_COMMENTARY_MODERATOR_REQUEST.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field2() {
        return TInCommentaryModeratorRequest.IN_COMMENTARY_MODERATOR_REQUEST.CONMMENTARY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field3() {
        return TInCommentaryModeratorRequest.IN_COMMENTARY_MODERATOR_REQUEST.MATERIAL_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field4() {
        return TInCommentaryModeratorRequest.IN_COMMENTARY_MODERATOR_REQUEST.SECTION_OF_MATERIAL_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Byte> field5() {
        return TInCommentaryModeratorRequest.IN_COMMENTARY_MODERATOR_REQUEST.IS_CONCIDERED_BY_SOMEONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value2() {
        return getConmmentaryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value3() {
        return getMaterialId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value4() {
        return getSectionOfMaterialId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte value5() {
        return getIsConcideredBySomeone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RInCommentaryModeratorRequestRecord value1(ULong value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RInCommentaryModeratorRequestRecord value2(ULong value) {
        setConmmentaryId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RInCommentaryModeratorRequestRecord value3(ULong value) {
        setMaterialId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RInCommentaryModeratorRequestRecord value4(UInteger value) {
        setSectionOfMaterialId(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public RInCommentaryModeratorRequestRecord value5(Byte value) {
        setIsConcideredBySomeone(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RInCommentaryModeratorRequestRecord values(ULong value1, ULong value2, ULong value3, UInteger value4, Byte value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }
}
/*
 * This file is generated by jOOQ.
*/
package ua.ddovgal.brainshareApi.jooq.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UInteger;
import org.jooq.types.ULong;
import ua.ddovgal.brainshareApi.jooq.tables.TCommentary;

import javax.annotation.Generated;
import java.sql.Timestamp;


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
public class RCommentaryRecord extends UpdatableRecordImpl<RCommentaryRecord> implements Record9<ULong, ULong, ULong, ULong, Integer, UInteger, UInteger, Timestamp, ULong> {

    private static final long serialVersionUID = -692162234;

    /**
     * Create a detached RCommentaryRecord
     */
    public RCommentaryRecord() {
        super(TCommentary.COMMENTARY);
    }

    /**
     * Create a detached, initialised RCommentaryRecord
     */
    public RCommentaryRecord(ULong id, ULong authorUserId, ULong materialId, ULong parentCommentaryId, Integer ratio, UInteger positiveMarkCount, UInteger negativeMarkCount, Timestamp creationDatetime, ULong contentDataId) {
        super(TCommentary.COMMENTARY);

        set(0, id);
        set(1, authorUserId);
        set(2, materialId);
        set(3, parentCommentaryId);
        set(4, ratio);
        set(5, positiveMarkCount);
        set(6, negativeMarkCount);
        set(7, creationDatetime);
        set(8, contentDataId);
    }

    /**
     * Getter for <code>brainshare_dev.commentary.id</code>.
     */
    public ULong getId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>brainshare_dev.commentary.id</code>.
     */
    public void setId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>brainshare_dev.commentary.author_user_id</code>.
     */
    public ULong getAuthorUserId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>brainshare_dev.commentary.author_user_id</code>.
     */
    public void setAuthorUserId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>brainshare_dev.commentary.material_id</code>.
     */
    public ULong getMaterialId() {
        return (ULong) get(2);
    }

    /**
     * Setter for <code>brainshare_dev.commentary.material_id</code>.
     */
    public void setMaterialId(ULong value) {
        set(2, value);
    }

    /**
     * Getter for <code>brainshare_dev.commentary.parent_commentary_id</code>.
     */
    public ULong getParentCommentaryId() {
        return (ULong) get(3);
    }

    /**
     * Setter for <code>brainshare_dev.commentary.parent_commentary_id</code>.
     */
    public void setParentCommentaryId(ULong value) {
        set(3, value);
    }

    /**
     * Getter for <code>brainshare_dev.commentary.ratio</code>.
     */
    public Integer getRatio() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>brainshare_dev.commentary.ratio</code>.
     */
    public void setRatio(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>brainshare_dev.commentary.positive_mark_count</code>.
     */
    public UInteger getPositiveMarkCount() {
        return (UInteger) get(5);
    }

    /**
     * Setter for <code>brainshare_dev.commentary.positive_mark_count</code>.
     */
    public void setPositiveMarkCount(UInteger value) {
        set(5, value);
    }

    /**
     * Getter for <code>brainshare_dev.commentary.negative_mark_count</code>.
     */
    public UInteger getNegativeMarkCount() {
        return (UInteger) get(6);
    }

    /**
     * Setter for <code>brainshare_dev.commentary.negative_mark_count</code>.
     */
    public void setNegativeMarkCount(UInteger value) {
        set(6, value);
    }

    /**
     * Getter for <code>brainshare_dev.commentary.creation_datetime</code>.
     */
    public Timestamp getCreationDatetime() {
        return (Timestamp) get(7);
    }

    /**
     * Setter for <code>brainshare_dev.commentary.creation_datetime</code>.
     */
    public void setCreationDatetime(Timestamp value) {
        set(7, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>brainshare_dev.commentary.content_data_id</code>.
     */
    public ULong getContentDataId() {
        return (ULong) get(8);
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>brainshare_dev.commentary.content_data_id</code>.
     */
    public void setContentDataId(ULong value) {
        set(8, value);
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
    public Row9<ULong, ULong, ULong, ULong, Integer, UInteger, UInteger, Timestamp, ULong> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row9<ULong, ULong, ULong, ULong, Integer, UInteger, UInteger, Timestamp, ULong> valuesRow() {
        return (Row9) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field1() {
        return TCommentary.COMMENTARY.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field2() {
        return TCommentary.COMMENTARY.AUTHOR_USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field3() {
        return TCommentary.COMMENTARY.MATERIAL_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field4() {
        return TCommentary.COMMENTARY.PARENT_COMMENTARY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return TCommentary.COMMENTARY.RATIO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field6() {
        return TCommentary.COMMENTARY.POSITIVE_MARK_COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field7() {
        return TCommentary.COMMENTARY.NEGATIVE_MARK_COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field8() {
        return TCommentary.COMMENTARY.CREATION_DATETIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field9() {
        return TCommentary.COMMENTARY.CONTENT_DATA_ID;
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
        return getAuthorUserId();
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
    public ULong value4() {
        return getParentCommentaryId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getRatio();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value6() {
        return getPositiveMarkCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value7() {
        return getNegativeMarkCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value8() {
        return getCreationDatetime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ULong value9() {
        return getContentDataId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RCommentaryRecord value1(ULong value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RCommentaryRecord value2(ULong value) {
        setAuthorUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RCommentaryRecord value3(ULong value) {
        setMaterialId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RCommentaryRecord value4(ULong value) {
        setParentCommentaryId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RCommentaryRecord value5(Integer value) {
        setRatio(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RCommentaryRecord value6(UInteger value) {
        setPositiveMarkCount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RCommentaryRecord value7(UInteger value) {
        setNegativeMarkCount(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RCommentaryRecord value8(Timestamp value) {
        setCreationDatetime(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public RCommentaryRecord value9(ULong value) {
        setContentDataId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RCommentaryRecord values(ULong value1, ULong value2, ULong value3, ULong value4, Integer value5, UInteger value6, UInteger value7, Timestamp value8, ULong value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }
}
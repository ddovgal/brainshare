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
import ua.ddovgal.brainshareApi.jooq.tables.TGenericNotification;

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
public class RGenericNotificationRecord extends UpdatableRecordImpl<RGenericNotificationRecord> implements Record5<ULong, ULong, Timestamp, UInteger, Byte> {

    private static final long serialVersionUID = 1951320832;

    /**
     * Create a detached RGenericNotificationRecord
     */
    public RGenericNotificationRecord() {
        super(TGenericNotification.GENERIC_NOTIFICATION);
    }

    /**
     * Create a detached, initialised RGenericNotificationRecord
     */
    public RGenericNotificationRecord(ULong id, ULong receiverUserId, Timestamp creationDatetime, UInteger typeId, Byte isCheckedByReceiver) {
        super(TGenericNotification.GENERIC_NOTIFICATION);

        set(0, id);
        set(1, receiverUserId);
        set(2, creationDatetime);
        set(3, typeId);
        set(4, isCheckedByReceiver);
    }

    /**
     * Getter for <code>brainshare_dev.generic_notification.id</code>.
     */
    public ULong getId() {
        return (ULong) get(0);
    }

    /**
     * Setter for <code>brainshare_dev.generic_notification.id</code>.
     */
    public void setId(ULong value) {
        set(0, value);
    }

    /**
     * Getter for <code>brainshare_dev.generic_notification.receiver_user_id</code>.
     */
    public ULong getReceiverUserId() {
        return (ULong) get(1);
    }

    /**
     * Setter for <code>brainshare_dev.generic_notification.receiver_user_id</code>.
     */
    public void setReceiverUserId(ULong value) {
        set(1, value);
    }

    /**
     * Getter for <code>brainshare_dev.generic_notification.creation_datetime</code>.
     */
    public Timestamp getCreationDatetime() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>brainshare_dev.generic_notification.creation_datetime</code>.
     */
    public void setCreationDatetime(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>brainshare_dev.generic_notification.type_id</code>.
     */
    public UInteger getTypeId() {
        return (UInteger) get(3);
    }

    /**
     * Setter for <code>brainshare_dev.generic_notification.type_id</code>.
     */
    public void setTypeId(UInteger value) {
        set(3, value);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>brainshare_dev.generic_notification.is_checked_by_receiver</code>.
     */
    public Byte getIsCheckedByReceiver() {
        return (Byte) get(4);
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * Setter for <code>brainshare_dev.generic_notification.is_checked_by_receiver</code>.
     */
    public void setIsCheckedByReceiver(Byte value) {
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
    public Row5<ULong, ULong, Timestamp, UInteger, Byte> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<ULong, ULong, Timestamp, UInteger, Byte> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field1() {
        return TGenericNotification.GENERIC_NOTIFICATION.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<ULong> field2() {
        return TGenericNotification.GENERIC_NOTIFICATION.RECEIVER_USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field3() {
        return TGenericNotification.GENERIC_NOTIFICATION.CREATION_DATETIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field4() {
        return TGenericNotification.GENERIC_NOTIFICATION.TYPE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Byte> field5() {
        return TGenericNotification.GENERIC_NOTIFICATION.IS_CHECKED_BY_RECEIVER;
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
        return getReceiverUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value3() {
        return getCreationDatetime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value4() {
        return getTypeId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte value5() {
        return getIsCheckedByReceiver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RGenericNotificationRecord value1(ULong value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RGenericNotificationRecord value2(ULong value) {
        setReceiverUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RGenericNotificationRecord value3(Timestamp value) {
        setCreationDatetime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RGenericNotificationRecord value4(UInteger value) {
        setTypeId(value);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public RGenericNotificationRecord value5(Byte value) {
        setIsCheckedByReceiver(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RGenericNotificationRecord values(ULong value1, ULong value2, Timestamp value3, UInteger value4, Byte value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }
}
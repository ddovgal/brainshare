/*
 * This file is generated by jOOQ.
*/
package ua.ddovgal.brainshareApi.jooq.tables.pojos;


import org.jooq.types.UInteger;
import org.jooq.types.ULong;

import javax.annotation.Generated;
import java.io.Serializable;
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
public class GenericNotification implements Serializable {

    private static final long serialVersionUID = 2000533197;

    private ULong id;
    private ULong receiverUserId;
    private Timestamp creationDatetime;
    private UInteger typeId;
    private Byte isCheckedByReceiver;

    public GenericNotification() {
    }

    public GenericNotification(GenericNotification value) {
        this.id = value.id;
        this.receiverUserId = value.receiverUserId;
        this.creationDatetime = value.creationDatetime;
        this.typeId = value.typeId;
        this.isCheckedByReceiver = value.isCheckedByReceiver;
    }

    public GenericNotification(
            ULong id,
            ULong receiverUserId,
            Timestamp creationDatetime,
            UInteger typeId,
            Byte isCheckedByReceiver
    ) {
        this.id = id;
        this.receiverUserId = receiverUserId;
        this.creationDatetime = creationDatetime;
        this.typeId = typeId;
        this.isCheckedByReceiver = isCheckedByReceiver;
    }

    public ULong getId() {
        return this.id;
    }

    public void setId(ULong id) {
        this.id = id;
    }

    public ULong getReceiverUserId() {
        return this.receiverUserId;
    }

    public void setReceiverUserId(ULong receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public Timestamp getCreationDatetime() {
        return this.creationDatetime;
    }

    public void setCreationDatetime(Timestamp creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public UInteger getTypeId() {
        return this.typeId;
    }

    public void setTypeId(UInteger typeId) {
        this.typeId = typeId;
    }

    public Byte getIsCheckedByReceiver() {
        return this.isCheckedByReceiver;
    }

    public void setIsCheckedByReceiver(Byte isCheckedByReceiver) {
        this.isCheckedByReceiver = isCheckedByReceiver;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GenericNotification (");

        sb.append(id);
        sb.append(", ").append(receiverUserId);
        sb.append(", ").append(creationDatetime);
        sb.append(", ").append(typeId);
        sb.append(", ").append(isCheckedByReceiver);

        sb.append(")");
        return sb.toString();
    }
}
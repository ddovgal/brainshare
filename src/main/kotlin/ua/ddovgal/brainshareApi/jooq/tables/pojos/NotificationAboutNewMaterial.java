/*
 * This file is generated by jOOQ.
*/
package ua.ddovgal.brainshareApi.jooq.tables.pojos;


import org.jooq.types.ULong;

import javax.annotation.Generated;
import java.io.Serializable;


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
public class NotificationAboutNewMaterial implements Serializable {

    private static final long serialVersionUID = 2108711971;

    private ULong notificationId;
    private ULong reasonId;

    public NotificationAboutNewMaterial() {
    }

    public NotificationAboutNewMaterial(NotificationAboutNewMaterial value) {
        this.notificationId = value.notificationId;
        this.reasonId = value.reasonId;
    }

    public NotificationAboutNewMaterial(
            ULong notificationId,
            ULong reasonId
    ) {
        this.notificationId = notificationId;
        this.reasonId = reasonId;
    }

    public ULong getNotificationId() {
        return this.notificationId;
    }

    public void setNotificationId(ULong notificationId) {
        this.notificationId = notificationId;
    }

    public ULong getReasonId() {
        return this.reasonId;
    }

    public void setReasonId(ULong reasonId) {
        this.reasonId = reasonId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("NotificationAboutNewMaterial (");

        sb.append(notificationId);
        sb.append(", ").append(reasonId);

        sb.append(")");
        return sb.toString();
    }
}
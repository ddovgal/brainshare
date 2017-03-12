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
public class MaterialMark implements Serializable {

    private static final long serialVersionUID = 968211269;

    private ULong materialId;
    private ULong setterUserId;
    private Byte isUpMark;

    public MaterialMark() {
    }

    public MaterialMark(MaterialMark value) {
        this.materialId = value.materialId;
        this.setterUserId = value.setterUserId;
        this.isUpMark = value.isUpMark;
    }

    public MaterialMark(
            ULong materialId,
            ULong setterUserId,
            Byte isUpMark
    ) {
        this.materialId = materialId;
        this.setterUserId = setterUserId;
        this.isUpMark = isUpMark;
    }

    public ULong getMaterialId() {
        return this.materialId;
    }

    public void setMaterialId(ULong materialId) {
        this.materialId = materialId;
    }

    public ULong getSetterUserId() {
        return this.setterUserId;
    }

    public void setSetterUserId(ULong setterUserId) {
        this.setterUserId = setterUserId;
    }

    public Byte getIsUpMark() {
        return this.isUpMark;
    }

    public void setIsUpMark(Byte isUpMark) {
        this.isUpMark = isUpMark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MaterialMark (");

        sb.append(materialId);
        sb.append(", ").append(setterUserId);
        sb.append(", ").append(isUpMark);

        sb.append(")");
        return sb.toString();
    }
}
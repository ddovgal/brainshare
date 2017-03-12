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
public class MaterialAttachment implements Serializable {

    private static final long serialVersionUID = -1202863525;

    private ULong materialId;
    private ULong objectStorageFileId;

    public MaterialAttachment() {
    }

    public MaterialAttachment(MaterialAttachment value) {
        this.materialId = value.materialId;
        this.objectStorageFileId = value.objectStorageFileId;
    }

    public MaterialAttachment(
            ULong materialId,
            ULong objectStorageFileId
    ) {
        this.materialId = materialId;
        this.objectStorageFileId = objectStorageFileId;
    }

    public ULong getMaterialId() {
        return this.materialId;
    }

    public void setMaterialId(ULong materialId) {
        this.materialId = materialId;
    }

    public ULong getObjectStorageFileId() {
        return this.objectStorageFileId;
    }

    public void setObjectStorageFileId(ULong objectStorageFileId) {
        this.objectStorageFileId = objectStorageFileId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MaterialAttachment (");

        sb.append(materialId);
        sb.append(", ").append(objectStorageFileId);

        sb.append(")");
        return sb.toString();
    }
}
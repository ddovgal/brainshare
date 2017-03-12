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
public class MaterialView implements Serializable {

    private static final long serialVersionUID = 1982208458;

    private ULong viewerUserId;
    private ULong materialId;

    public MaterialView() {
    }

    public MaterialView(MaterialView value) {
        this.viewerUserId = value.viewerUserId;
        this.materialId = value.materialId;
    }

    public MaterialView(
            ULong viewerUserId,
            ULong materialId
    ) {
        this.viewerUserId = viewerUserId;
        this.materialId = materialId;
    }

    public ULong getViewerUserId() {
        return this.viewerUserId;
    }

    public void setViewerUserId(ULong viewerUserId) {
        this.viewerUserId = viewerUserId;
    }

    public ULong getMaterialId() {
        return this.materialId;
    }

    public void setMaterialId(ULong materialId) {
        this.materialId = materialId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MaterialView (");

        sb.append(viewerUserId);
        sb.append(", ").append(materialId);

        sb.append(")");
        return sb.toString();
    }
}
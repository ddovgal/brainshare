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
public class ObjectStorageFile implements Serializable {

    private static final long serialVersionUID = 1230479193;

    private ULong id;
    private String bucket;
    private String inBucketFileId;

    public ObjectStorageFile() {
    }

    public ObjectStorageFile(ObjectStorageFile value) {
        this.id = value.id;
        this.bucket = value.bucket;
        this.inBucketFileId = value.inBucketFileId;
    }

    public ObjectStorageFile(
            ULong id,
            String bucket,
            String inBucketFileId
    ) {
        this.id = id;
        this.bucket = bucket;
        this.inBucketFileId = inBucketFileId;
    }

    public ULong getId() {
        return this.id;
    }

    public void setId(ULong id) {
        this.id = id;
    }

    public String getBucket() {
        return this.bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getInBucketFileId() {
        return this.inBucketFileId;
    }

    public void setInBucketFileId(String inBucketFileId) {
        this.inBucketFileId = inBucketFileId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ObjectStorageFile (");

        sb.append(id);
        sb.append(", ").append(bucket);
        sb.append(", ").append(inBucketFileId);

        sb.append(")");
        return sb.toString();
    }
}
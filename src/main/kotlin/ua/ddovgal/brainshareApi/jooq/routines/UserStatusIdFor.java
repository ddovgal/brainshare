/*
 * This file is generated by jOOQ.
*/
package ua.ddovgal.brainshareApi.jooq.routines;


import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import ua.ddovgal.brainshareApi.jooq.BrainshareDev;

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
public class UserStatusIdFor extends AbstractRoutine<Integer> {

    /**
     * The parameter <code>brainshare_dev.user_status_id_for.RETURN_VALUE</code>.
     */
    public static final Parameter<Integer> RETURN_VALUE = createParameter("RETURN_VALUE", org.jooq.impl.SQLDataType.INTEGER, false, false);
    /**
     * The parameter <code>brainshare_dev.user_status_id_for.user_ratio</code>.
     */
    public static final Parameter<Integer> USER_RATIO = createParameter("user_ratio", org.jooq.impl.SQLDataType.INTEGER, false, false);
    private static final long serialVersionUID = -189308800;

    /**
     * Create a new routine call instance
     */
    public UserStatusIdFor() {
        super("user_status_id_for", BrainshareDev.BRAINSHARE_DEV, org.jooq.impl.SQLDataType.INTEGER);

        setReturnParameter(RETURN_VALUE);
        addInParameter(USER_RATIO);
    }

    /**
     * Set the <code>user_ratio</code> parameter IN value to the routine
     */
    public void setUserRatio(Integer value) {
        setValue(USER_RATIO, value);
    }

    /**
     * Set the <code>user_ratio</code> parameter to the function to be used with a {@link org.jooq.Select} statement
     */
    public void setUserRatio(Field<Integer> field) {
        setField(USER_RATIO, field);
    }
}
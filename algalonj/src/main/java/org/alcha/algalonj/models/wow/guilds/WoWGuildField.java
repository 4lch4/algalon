package org.alcha.algalonj.models.wow.guilds;

import org.alcha.algalonj.interfaces.FieldName;
import org.alcha.algalonj.network.WoWRequest;

/**
 * <p>Created by Alcha on 8/8/2017.</p>
 *
 * <p>Represents a field to be used in a request against the Battle.net API. The fields are appended to
 * the end of the URL and are used to request more information.</p>
 *
 * <p>For example, when requesting information on a particular guild, if you want their
 * achievements, guild news, or member list, you must append a field to the end of the URL. Instead
 * of manually doing this, {@link WoWGuildField} can be combined with a {@link WoWRequest} object
 * to help generate the proper query.</p>
 */
public class WoWGuildField {
    private static final String LOG_TAG = "WoWGuildField";

    /**
     * Represents the name of a {@link WoWGuildField}. This is stored as an {@link Enum} to ensure
     * users of Algalon don't include typos in URL requests.
     */
    public enum Name implements FieldName {
        Members,
        Achievements,
        News,
        Challenge;

        /**
         * Gets the {@link WoWGuildField.Name} of the current object and turns it into the "slug"
         * used in the Battle.net API. Ensures the name is lowercase and all spaces or underscores
         * are removed or replaced with their proper character ({@code e.g. space = %20}).
         *
         * @return slug value used for the URL in requests
         */
        @Override
        public String getSlug() {
            return name().toLowerCase().replace("_", "");
        }

        /**
         * Generates a String to be used for displaying the {@link WoWGuildField.Name}. Underscores
         * are replaced with spaces and casing is left as is.
         *
         * @return String value of the WoWGuildField name
         */
        @Override
        public String toString() {
            return name().replace('_', ' ');
        }
    }

    /** Stores the name of the Field */
    private Name mFieldName;

    /**
     * Sets the field name for the enum object so it can be used later in generating slugs for
     * requests.
     *
     * @param fieldName {@link WoWGuildField.Name} you want the object to represent
     */
    void setFieldName(Name fieldName) {
        mFieldName = fieldName;
    }

    /**
     * Get the current field name of the object.
     *
     * @return {@link WoWGuildField.Name} of the object
     */
    public Name getFieldName() {
        return mFieldName;
    }

    /**
     * Accepts an Array of {@link WoWGuildField.Name} objects and concatenates their slug values
     * into a single String. Generally used to add the field names to the end of a URL.
     *
     * @param fieldNames Array of field names you wish to concatenate
     *
     * @return String containing all fields separated by URL safe commas
     */
    public static String slugifyFieldNames(Name[] fieldNames) {
        String temp = "";

        for (Name field : fieldNames) {
            temp += field.getSlug() + "%2C";
        }

        return temp;
    }
}

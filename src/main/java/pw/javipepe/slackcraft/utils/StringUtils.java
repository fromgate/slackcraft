package pw.javipepe.slackcraft.utils;

import java.util.Collection;

/**
 * @author Javi
 */
public class StringUtils {

    /**
     * Converts a list of strings to make a nice English list as a string.
     *
     * @param list   List of strings to concatenate.
     * @param prefix Prefix to add before each element in the resulting string.
     * @param suffix Suffix to add after each element in the resulting string.
     * @return String version of the list of strings.
     */
    public static String listToEnglishCompound(Collection<?> list, String prefix, String suffix) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for(Object str : list) {
            if(i != 0) {
                if(i == list.size() - 1) {
                    builder.append(" and ");
                } else {
                    builder.append(", ");
                }
            }

            builder.append(prefix).append(str).append(suffix);
            i++;
        }

        return builder.toString();
    }
}

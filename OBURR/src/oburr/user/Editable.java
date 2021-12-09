/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.user;

public interface Editable {

    void setIsEditable(boolean isEditable);
    boolean check(String password);
    boolean getIsEditable();

}

/**
 *@CenkOlcay
 *java version 16.0.1
 */

package oburr.user;

public interface Editable {

    void lock();
    void unlock();
    boolean check(String password);
    boolean getIsEditable();

}

package project_4;

/**
 * The interface that multiple objects implement 
 *  @author KJ Wang, Mehdi Kamal
 */
public interface Customizable {
	/**
	 * This is a boolean to check if object is added correctly
	 * @param obj the object that is being added
	 * @return true if added correctly, false otherwise
	 */
    boolean add(Object obj);
    
    /**
     * This is a boolean to check if object is removed correctly
     * @param obj if object is removed
     * @return true if removed correctly, false otherwise
     */
    boolean remove(Object obj);
}

package com.waes.scalable.web.diff.api;

/**
 * Diff method types. 
 */
public enum DiffMethod {

	LEFT(Values.LEFT),
	
	RIGHT(Values.RIGHT);
	
	/**
	 * Instantiates a new diff method.
	 *
	 * @param val the val
	 */
	DiffMethod(int val) {
        this.value = val;
    }

    private int value;

    /**
     * Gets the value.
     *
     * @return the value
     */
    public int getValue(){
        return value;
    }
    
    /**
     * From value.
     *
     * @param val the val
     * @return the string
     */
    public static String fromValue(int val) {
    	switch (val) {
			case Values.LEFT:
				return "LEFT";
				
			case Values.RIGHT:
				return "RIGTH";
		}
    	return "UNDEFINED";
    }
    
    /**
     * The Class Values.
     */
    public static class Values {
        
        /** The Constant LEFT. */
        public static final int LEFT = 1;
        
        /** The Constant RIGHT. */
        public static final int RIGHT = 2;
    }
}

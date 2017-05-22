package com.waes.scalable.web.diff.api;

public enum DiffMethod {

	LEFT(Values.LEFT),
	RIGHT(Values.RIGHT);
	
	DiffMethod(int val) {
        this.value = val;
    }

    private int value;

    public int getValue(){
        return value;
    }
    
    public static String fromValue(int val) {
    	switch (val) {
			case Values.LEFT:
				return "LEFT";
				
			case Values.RIGHT:
				return "RIGTH";
		}
    	return "UNDEFINED";
    }
    
    public static class Values {
        public static final int LEFT = 1;
        public static final int RIGHT = 2;
    }
}

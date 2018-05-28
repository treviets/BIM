package com.redsun.hrm.utils;

public enum TaskEnums {
	BACKLOG(15),
    TODO(16),
    INPROCESS(17),
    DONE(18),
    BLOCK(19),
    REOPEN(20);
	
    private int code;

	TaskEnums(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}

package com.nokk.editoon.exception;

public enum ErrorCode {
	// Common
	InternalServer(500, "U001", "Internal Server Exception"),
	Unknown(520, "U002", "Unknown Exception"),
    Unauthorized_Account(401, "A001", "Unauthorized"),
	ExpiredToken(406, "T001", "ExpiredToken"),
	Forbidden_User(403, "A002", "Forbidden");
	
    private final String code;
    private final String message;
    private final int status;

    private ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}
}

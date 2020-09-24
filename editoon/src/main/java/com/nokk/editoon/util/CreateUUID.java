package com.nokk.editoon.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CreateUUID {
	public static String createUUID(String fileExtension) {
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		return dateFormat.format(new Date()) + '_' + UUID.randomUUID().toString().replace("-", "").substring(0, 10) + '.' + fileExtension;
	}
}

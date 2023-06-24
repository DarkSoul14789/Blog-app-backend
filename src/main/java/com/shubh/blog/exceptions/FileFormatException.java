package com.shubh.blog.exceptions;

import java.io.IOException;

@SuppressWarnings("serial")
public class FileFormatException extends IOException{
	String formatName;
	
	public FileFormatException( String formatName) {
		super(String.format("%s file extension is not permitted",formatName));
		this.formatName = formatName;
	}
}

package com.te.carmaintenance.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminRequest {
	private String username;
	private String password;

}

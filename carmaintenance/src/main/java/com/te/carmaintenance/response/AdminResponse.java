package com.te.carmaintenance.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminResponse {
	private String message;
	private boolean error;
	private String token;
	private String role;

}

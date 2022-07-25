package com.example.demo.binding;

import lombok.Data;

@Data
public class UnlockAccForm {

	private String email;
	private String temporaryPswd;
	private String newPswd;
	private String confirmPswd;
}

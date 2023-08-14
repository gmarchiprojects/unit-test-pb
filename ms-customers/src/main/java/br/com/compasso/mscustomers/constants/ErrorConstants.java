package br.com.compasso.mscustomers.constants;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class ErrorConstants {

    public static final String CUSTOMER_NOT_FOUND = "[MS-CUSTOMER] Customer Not Found";
    public static final String CUSTOMER_ALREADY_EXISTS = "[MS-CUSTOMER] Customer Already Exists";
}

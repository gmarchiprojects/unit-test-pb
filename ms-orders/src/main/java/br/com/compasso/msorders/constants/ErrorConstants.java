package br.com.compasso.msorders.constants;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class ErrorConstants {

    public static final String CUSTOMER_NOT_FOUND = "[MS-ORDERS] Customer Not Found";
    public static final String ORDER_ALREADY_EXISTS = "[MS-ORDERS] Order Already Exists";
    public static final String ORDER_NOT_FOUND = "[MS-ORDERS] Order Not Found";
}

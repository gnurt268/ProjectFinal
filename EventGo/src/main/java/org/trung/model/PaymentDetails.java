package org.trung.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentDetails {

    private String paymentMethod;
    private String status;
    private String paymentId;
    private String vnpayPaymentLinkId;
    private String vnpayPaymentLinkReferenceld;
    private String vnpayPaymentLinkStatus;
    private String vnpayPaymentId;
    public PaymentDetails() {

    }
}

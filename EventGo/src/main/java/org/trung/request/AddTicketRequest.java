package org.trung.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddTicketRequest {
    private Long eventId;
    private String type;
    private int quantity;
    private Integer price;
    public AddTicketRequest() {

    }
}

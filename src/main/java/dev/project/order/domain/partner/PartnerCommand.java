package dev.project.order.domain.partner;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class PartnerCommand {
    private final String partnerName;
    private final String businessNo;
    private final String email;

    public Partner toEntity() {
        return Partner.builder()
                .partnerName(getPartnerName())
                .businessNo(getBusinessNo())
                .email(getEmail())
                .build();
    }
}

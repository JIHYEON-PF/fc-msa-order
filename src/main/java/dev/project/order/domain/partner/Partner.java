package dev.project.order.domain.partner;

import dev.project.order.common.util.TokenGenerator;
import dev.project.order.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Getter
@NoArgsConstructor
@Entity
@Table(name = "partners")
public class Partner extends AbstractEntity {

    private static final String PREFIX_PARTNER = "ptn_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String partnerToken;

    private String partnerName;
    private String businessNo;
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;


    @Builder
    public Partner(String partnerName, String email, String businessNo) {
        if (StringUtils.isEmpty(partnerName)) throw new RuntimeException("empty PartnerName");
        if (StringUtils.isEmpty(businessNo)) throw new RuntimeException("empty BusinessNo");
        if (StringUtils.isEmpty(email)) throw new RuntimeException("empty Email");


        this.partnerToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_PARTNER);
        this.partnerName = partnerName;
        this.email = email;
        this.businessNo = businessNo;
        this.status = Status.ENABLE;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ENABLE("활성화"),
        DISABLE("비활성화");

        private final String description;
    }

    public void enable() {
        this.status = Status.ENABLE;
    }

    public void disable() {
        this.status = Status.DISABLE;
    }
}

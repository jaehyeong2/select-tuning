package jjfactory.selecttuning.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderCreate {
    private Long memberId;
    private Long itemId;
    private int count;

    @Builder
    public OrderCreate(Long memberId, Long itemId, int count) {
        this.memberId = memberId;
        this.itemId = itemId;
        this.count = count;
    }
}

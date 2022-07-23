package jjfactory.selecttuning.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum DeleteStatus {
    DELETED("Y"),
    NON_DELETED("N");

    private String boolValue;

    DeleteStatus(String str) {
        this.boolValue = str;
    }
}

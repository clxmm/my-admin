package org.clxmm.autocode.common.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class KeyValue<K, V> {

    private K key;
    private V value;

}


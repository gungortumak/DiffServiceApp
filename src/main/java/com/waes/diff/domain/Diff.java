package com.waes.diff.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Data Model For Diffs
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Diff implements Serializable {
    @Id
    private Integer id;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] leftSide;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] rightSide;
}
package com.example.crm.domain.dto.in;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CustomerNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteInput implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -9132966961718508527L;

    @ApiModelProperty(dataType="Long", value="record key.optional when add new record")
    private Long customerNoteId;
    @ApiModelProperty(dataType="String", value="customer note", required=true)
    private String note; 
    @ApiModelProperty(dataType="Long", value="version number, optional when add new record")
    private Long version;
    @ApiModelProperty(dataType="Long", value="customer id",required=true)
    private Long customerId;
}
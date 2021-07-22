package com.honeycomb.demo.entity.po;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Liuh
 */
@Document(collection = "rf_demo")
@Data
public class RfDemoPO {

    @JsonProperty("id")
    private String id;
    @JsonProperty("uid")
    private String uid;
    @JsonProperty("car_park_id")
    private String carParkId;
    @JsonProperty("create_time")
    private String createTime;
}

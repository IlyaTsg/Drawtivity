package com.ETU.api.dtos.lti;

import com.ETU.api.entities.Point;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Проверка задачи от LTI пользователя")
public class LtiSolutionRequest {
    @Schema(type = "string", description = "URL сервиса оценок", example = "https://saltire.lti.app/platform/outcomes/s452ae4d1a7a828b81b4743a66e222b33")
    private String outcome_service_url;
    @Schema(type = "string", description = "Ключ потребителя", example = "jisc.ac.uk")
    private String oauth_consumer_key;
    @Schema(type = "string", description = "Ключ ресурса результата", example = "UzMyOTQ0NzY6Ojo0Mjk3ODUyMjY6OjoyOTEyMw==")
    private String lis_result_sourcedid;
    @Schema(type = "int", example = "2")
    private Integer task_id;
    @Schema(example = "[\n" +
            "            {\n" +
            "                \"x\": 10.0,\n" +
            "                \"y\": 10.0\n" +
            "            },\n" +
            "            {\n" +
            "                \"x\": 15.0,\n" +
            "                \"y\": 15.0\n" +
            "            }]")
    private List<Point> points;

    public LtiSolutionRequest(String outcome_service_url, String oauth_consumer_key, String lis_result_sourcedid, Integer task_id, List<Point> points) {
        this.outcome_service_url = outcome_service_url;
        this.oauth_consumer_key = oauth_consumer_key;
        this.lis_result_sourcedid = lis_result_sourcedid;
        this.task_id = task_id;
        this.points = points;
    }
}

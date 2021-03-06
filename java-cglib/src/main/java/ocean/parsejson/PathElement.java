package ocean.parsejson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yancy
 * @date 2019/8/18
 */
@Setter
@Getter
@Builder
@EqualsAndHashCode
public class PathElement {


    private String level;

    private String path;

    private String pathType;

    private String elemetnKey;

    private String childPath;

    private String parentPath;

    private String parentType;

    private JsonObject leafNode;

    private JsonObject jsonObject;

    private JsonArray jsonArray;

    private JsonElement jsonElement;

    @Override
    public String toString() {
        return path;
    }
}


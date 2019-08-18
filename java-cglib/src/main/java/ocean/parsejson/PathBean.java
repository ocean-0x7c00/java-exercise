package ocean.parsejson;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yancy
 * @date 2019/8/18
 */
@Setter
@Getter
@Builder
public class PathBean {
    private String level;

    private List<PathElement> beans;
}

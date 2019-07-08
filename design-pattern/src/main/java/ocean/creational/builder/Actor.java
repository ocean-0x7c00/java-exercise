package ocean.creational.builder;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ocean
 * @date 2019/7/8
 */
@Setter
@Getter
public class Actor {
    /**
     * 角色类型
     */
    private String type;

    /**
     * 性别
     */
    private String sex;

    /**
     * 脸型
     */
    private String face;

    /**
     * 服装
     */
    private String costume;

    /**
     * 发型
     */
    private String hairstyle;
}

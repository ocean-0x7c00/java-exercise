package ocean.creational.builder;

/**
 * 游戏角色创建控制器：指挥者
 *
 * @author ocean
 * @date 2019/7/8
 */
public class ActorController {
    public Actor construct(ActorBuilder ab) {
        Actor actor;
        ab.buildType();
        ab.buildSex();
        ab.buildFace();
        ab.buildCostume();
        ab.buildHairstyle();
        actor = ab.createActor();
        return actor;

    }
}

package ocean.creational.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author ocean
 * @date 2019/7/8
 */
public class Client {
    public static void main(String[] args) {
        //针对抽象建造者编程
        ActorBuilder actorBuilder = new DevilBuilder();

        //通过指挥者创建完整的建造者对象
        ActorController actorController = new ActorController();
        Actor actor = actorController.construct(actorBuilder);


        System.out.println(actor.getType() + "的外观：");

        System.out.println("性别：" + actor.getSex());

        System.out.println("面容：" + actor.getFace());

        System.out.println("服装：" + actor.getCostume());

        System.out.println("发型：" + actor.getHairstyle());

        Gson gson = new Gson();
        Gson build = new GsonBuilder().create();

    }
}

package top.ittqy.websocket.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Author: 27258
 * @Date: 2025/10/10
 */
@Service
public class WordsService {
    private static final String[] Words_LIST = {
            "生活不止眼前的苟且，还有诗和远方。",
            "成功的花，人们只惊羡它现时的明艳！",
            "当你凝视深渊时，深渊也在凝视你。",
            "Stay hungry, stay foolish.",
            "你必须非常努力，才能看起来毫不费力。",
            "不要等待机会，而要创造机会。",
            "每一次失败，都是离成功更近一步。",
            "世界会向那些有目标和远见的人让路。",
            "行动是治愈恐惧的良药。",
            "你的时间有限，别浪费在别人的生活里。"
    };

    private final Random random = new Random();

    public String getRandomHitokoto() {
        return Words_LIST[random.nextInt(Words_LIST.length)];
    }
}

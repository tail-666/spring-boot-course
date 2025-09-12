package top.codertqy.config.controller;

import enums.ExpressStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 27258
 * @Date: 2025/9/12
 */
@RestController
@RequestMapping("/express")
public class ExpressController {
    /**
     * 获取快递状态
     * @param status
     * @return
     */
    @GetMapping("/{status}")
    public String getPrice(@PathVariable ExpressStatus status) {
        return "快递状态：" + status.getStatus();
    }


    /**
     * 获取所有快递状态
     * @return
     */
    @GetMapping("/statuses")
    public List<Map<String,String>> getStatuses() {
        List<Map<String,String>> list = new ArrayList<>();
        for (ExpressStatus status : ExpressStatus.values()) {
            HashMap<String, String> item = new HashMap<>();
            item.put("value", status.name());
            item.put("label", status.getStatus());
            list.add(item);
        }
        return list;
    }

    /**
     * 更新快递状态
     * @param orderNo
     * @param status
     * @return
     */
    @PostMapping("/update")
    public String updateExpress(@RequestParam String orderNo, @RequestParam ExpressStatus status) {
        return "快递单号：" + orderNo + "，状态已更新为：" + status.getStatus();
    }
}

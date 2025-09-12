package top.codertqy.config.controller;

import enums.DrinkType;
import org.springframework.web.bind.annotation.*;
import top.codertqy.config.pojo.ApiResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 27258
 * @Date: 2025/9/12
 */
@RestController
@RequestMapping("/drink")
public class DrinkController {
    /**
     * 选择饮料
     *
     * @param type 饮料类型
     * @return 饮料信息
     */
    @GetMapping("/{type}")
    public ApiResponse<Map<String, Object>> chooseDrink(@PathVariable String type) {
        try {
            // 将参数转为枚举
            DrinkType drink = DrinkType.valueOf(type.toUpperCase());
            Map<String, Object> result = new HashMap<>();
            result.put("name", drink.getLabel());
            result.put("price", drink.getPrice());
            // 返回结果
            return ApiResponse.success("选择成功", result);
        } catch (IllegalArgumentException e) {
            return ApiResponse.error("饮料类型错误");
        }
    }

    /**
     * 获取菜单
     *
     * @return ApiResponse
     */
    @GetMapping("/menu")
    public ApiResponse<List<Map<String, Object>>> getMenu() {
        List<Map<String, Object>> menu = new ArrayList<>();
        for (DrinkType drink : DrinkType.values()) {
            Map<String, Object> item = new HashMap<>();
            item.put("name", drink.getLabel());
            item.put("price", drink.getPrice());
            menu.add(item);
        }
        return ApiResponse.success("菜单获取成功", menu);
    }

    /**
     * 下单
     *
     * @param type 饮料类型
     * @param num  数量
     * @return 订单信息
     */
    @GetMapping("/order")
    public ApiResponse<Map<String, Object>> orderDrink(@RequestParam String type, @RequestParam int num) {
        try {
            // 将参数转为枚举
            DrinkType drink = DrinkType.valueOf(type.toUpperCase());
            // 计算总价
            double total = drink.getPrice() * num;
            HashMap<String, Object> result = new HashMap<>();
            result.put("name", drink.getLabel());
            result.put("num", num);
            result.put("unitPrice", drink.getPrice());
            result.put("totalPrice", total);
            // 模拟下单
            return ApiResponse.success("下单成功", result);
        } catch (IllegalArgumentException e) {
            return ApiResponse.error("饮料类型错误");
        }
    }
}

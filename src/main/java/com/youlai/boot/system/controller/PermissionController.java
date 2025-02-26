package com.youlai.boot.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class PermissionController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate; // 使用String类型的RedisTemplate

    @Autowired
    private ObjectMapper objectMapper; // 使用Jackson的ObjectMapper

    // 生成示例数据的接口
    @GetMapping("/generateSampleData")
    public String generateSampleData() throws IOException {
        // 存储用户权限前缀，格式是 Map<前缀, List<路径>>
        Map<String, List<String>> userPermissions = Map.of(
                "/base", Arrays.asList("/base/aa", "/base/bb"),
                "/expense", Arrays.asList("/expense/aa", "/expense/bb"),
                "/acct", Arrays.asList("/acct/aa")
        );

        // 将 Map 转换为 JSON 字符串
        String permissionsJson = objectMapper.writeValueAsString(userPermissions);

        // 存储到 Redis 中
        redisTemplate.opsForValue().set("user:user_123:permissions", permissionsJson);

        return "Sample data generated successfully!";
    }

    // 查询用户是否有权限访问某路径的接口
    @GetMapping("/checkAccess")
    public boolean checkAccess(@RequestParam String userId, @RequestParam String requestPath) throws IOException {
        // 从 Redis 获取用户权限的 JSON 字符串
        String permissionsJson = redisTemplate.opsForValue().get("user:" + userId + ":permissions");

        if (permissionsJson != null) {
            // 将 JSON 字符串转换回 Map<String, List<String>>
            Map<String, List<String>> permissionsMap = objectMapper.readValue(permissionsJson, Map.class);

            // 检查路径是否匹配
            for (Map.Entry<String, List<String>> entry : permissionsMap.entrySet()) {
                String pathPrefix = entry.getKey();
                List<String> paths = entry.getValue();

                if (requestPath.startsWith(pathPrefix)) {
                    // 检查路径是否在前缀对应的路径列表中
                    return paths.contains(requestPath);
                }
            }
        }

        return false; // 没有权限
    }
}

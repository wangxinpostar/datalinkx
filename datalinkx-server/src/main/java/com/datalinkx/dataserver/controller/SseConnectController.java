package com.datalinkx.dataserver.controller;


import com.datalinkx.sse.config.SseEmitterServer;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequestMapping("/api/sse")
public class SseConnectController {

    @ApiOperation("获取SSE链接实例")
    @GetMapping("/connect/{pageId}")
    public SseEmitter connect(@PathVariable String pageId) {
        return SseEmitterServer.connect(pageId);
    }

    @ApiOperation("关闭SSE链接实例")
    @GetMapping("/connect/closed/{pageId}")
    public void closed(@PathVariable String pageId) {
        SseEmitterServer.removeUser(pageId);
    }
}

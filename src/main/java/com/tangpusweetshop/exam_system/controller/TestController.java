package com.tangpusweetshop.exam_system.controller;

import com.tangpusweetshop.exam_system.model.User;
import com.tangpusweetshop.exam_system.model.req.TestCreateReq;
import com.tangpusweetshop.exam_system.model.resp.Resp;
import com.tangpusweetshop.exam_system.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService){
        this.testService=testService;
    }

    @PostMapping("/create")
    Resp testCreate(User user, @RequestBody TestCreateReq testCreateReq){
        if (!(user.getType().equals("admin")||user.getType().equals("principal"))){
            return getNoPermissionResp(user.getType());
        }
        return testService.createTest(testCreateReq);
    }

    @GetMapping("/get")
    Resp testGet(User user,@RequestParam(value = "TestID") String testId){
        return testService.getTest(testId);
    }

    @GetMapping("/answer")
    Resp testAnswer(User user,@RequestParam(value = "TestID") String testId){
//        if (!(user.getType().equals("admin")||user.getType().equals("principal"))){
//            return getNoPermissionResp(user.getType());
//        }
        return testService.getTestAnswer(testId);
    }

    @GetMapping("/list")
    Resp testLists(User user,@RequestParam(value = "Offset") int offset,@RequestParam(value = "Limit") int limit){
        return testService.listTests(offset, limit);
    }

    Resp getNoPermissionResp(String type){
        Resp resp=new Resp();
        resp.setStatus("Error");
        resp.setError(type+" do not have permission use this api");
        return resp;
    }
}

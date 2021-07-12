package com.tangpusweetshop.exam_system.controller;

import com.tangpusweetshop.exam_system.model.User;
import com.tangpusweetshop.exam_system.model.req.QuestionCreateReq;
import com.tangpusweetshop.exam_system.model.resp.Resp;
import com.tangpusweetshop.exam_system.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService=questionService;
    }

    @PostMapping("/create")
    Resp questionCreate(User user, @RequestBody QuestionCreateReq req){
        if (!(user.getType().equals("admin")||user.getType().equals("principal"))){
            return getNoPermissionResp(user.getType());
        }
        return questionService.questionCreate(req);
    }

    @GetMapping("/delete")
    Resp questionDelete(User user,@RequestParam(value = "QuestionID") String questionId){
        if (!(user.getType().equals("admin")||user.getType().equals("principal"))){
            return getNoPermissionResp(user.getType());
        }
        return questionService.questionDelete(questionId);
    }

    @PostMapping("/update")
    Resp questionUpdate(User user,@RequestBody QuestionCreateReq req,@RequestParam(value = "QuestionID") String questionId){
        if (!(user.getType().equals("admin")||user.getType().equals("principal"))){
            return getNoPermissionResp(user.getType());
        }
        return questionService.questionUpdate(req,questionId);
    }

    @GetMapping("/list")
    Resp questionList(User user,@RequestParam(value = "Limit") int limit,@RequestParam(value = "Offset") int offset){
        return questionService.questionList(offset,limit);
    }

    @GetMapping("/get")
    Resp questionGet(User user,@RequestParam(value = "QuestionID") String questionId){
        return questionService.questionGet(questionId);
    }

    @GetMapping("/delete/list")
    Resp questionDisableList(User user,@RequestParam(value = "Limit") int limit,@RequestParam(value = "Offset") int offset){
        if (!user.getType().equals("admin")){
            return getNoPermissionResp(user.getType());
        }
        return questionService.questionDisableList(offset,limit);
    }

    @GetMapping("/delete/verify")
    Resp questionDeleteVerify(User user,@RequestParam(value = "QuestionID") String questionId,@RequestParam(value = "Submit") int submit){
        if (!user.getType().equals("admin")){
            return getNoPermissionResp(user.getType());
        }
        return questionService.questionDeleteVerify(questionId,submit);
    }

    @GetMapping("/answer")
    Resp questionAnswer(User user,@RequestParam(value = "QuestionID") String questionId){
//        if (!(user.getType().equals("admin")||user.getType().equals("principal"))){
//            return getNoPermissionResp(user.getType());
//        }
        return questionService.questionAnswer(questionId);
    }

    Resp getNoPermissionResp(String type){
        Resp resp=new Resp();
        resp.setStatus("Error");
        resp.setError(type+" do not have permission use this api");
        return resp;
    }
}

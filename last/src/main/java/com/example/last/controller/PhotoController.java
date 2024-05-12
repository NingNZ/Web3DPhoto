package com.example.last.controller;

import com.example.last.service.PhotoService;
import com.example.last.util.Range;
import com.example.last.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@CrossOrigin
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;
    @PostMapping("/upload")
    private Result upload (@RequestParam MultipartFile file) throws IOException{
        Range range= photoService.Countall();
        System.out.println(range);
        if(range.Sum==10000) return Result.Failure("上传失败，存储已满");
        int dest=range.last+1;
        String name =file.getOriginalFilename(); //获取图片的名称
        FileOutputStream picOutput = null;//设置存储路径
        try {
            picOutput = new FileOutputStream("D:\\code\\IdeaProject\\last\\src\\main\\resources\\static\\"+dest+".jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        picOutput.write(file.getBytes());//获取字节流直接写入到磁盘内
        picOutput.close();//关闭字节流
        return photoService.insert(dest);
    }
    @GetMapping("/delete")
    private  Result delete(@RequestParam("id") int id){
        System.out.println(id);
        int last=photoService.Countall().last;
        photoService.delete(id);
        File nowfile=new File("D:\\code\\IdeaProject\\last\\src\\main\\resources\\static\\"+id+".jpg");
        nowfile.delete();
        photoService.Modify(id,last);
        File lastfile=new File("D:\\code\\IdeaProject\\last\\src\\main\\resources\\static\\"+last+".jpg");
        if(id==last){
            nowfile.delete();
            return Result.OK("图片删除成功");
        }
        else{
            nowfile.delete();
            if(lastfile.renameTo(nowfile)) {
                return Result.OK("图片删除成功");
            }else return Result.Failure("图片删除错误");
        }
    }
    @GetMapping("/countall")
    private Range responseAll(){
        return photoService.Countall();
    }

}


